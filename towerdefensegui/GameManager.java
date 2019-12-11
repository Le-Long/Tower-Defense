/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefensegui;

import Control.Control;
import Control.Shop;
import GameEntity.AirEnemy;
import GameEntity.Enemy;
import GameEntity.EnemyManager;
import GameTile.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author TA
 */
public class GameManager implements java.io.Serializable {

	int testNumber = 1;
	private int playerScore = 0;
	private int playerGold;
	private int screenX;
	private int screenY;
	private EnemyManager enemyManager;
	private TowerManager towerManager;
	private Shop shop;
	private Grid grid;
	private Control control;
	private int minute;
	private int second;
	private int frameRate;
	private String time;
	private int remainingChances;
	private boolean gameLost;
	private boolean gameWon;
	private int numOfWaveEnemy;
	private ArrayList<Enemy> explosion;

	public GameManager(int level) {
		gameLost = false;
		gameWon = false;
		remainingChances = 10;
		minute = 0;
		second = 0;
		frameRate = 0;
		time = minute + ":" + second;
		playerGold = 10000;
		numOfWaveEnemy = 0;
		explosion = new ArrayList<Enemy>();
		shop = Shop.getInstance();

		int[][][] test = {
			{
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{1, 1, 1, 1, 0, 0, 0, 0, 0},
				{0, 0, 0, 1, 0, 0, 0, 0, 0},
				{0, 0, 0, 1, 0, 0, 0, 0, 0},
				{0, 0, 0, 1, 0, 0, 0, 0, 0},
				{0, 0, 0, 1, 0, 0, 0, 0, 0},
				{0, 0, 0, 1, 1, 1, 1, 1, 0},
				{0, 0, 0, 0, 0, 0, 0, 1, 0},
				{0, 0, 0, 0, 0, 1, 1, 1, 0},
				{0, 0, 0, 0, 0, 1, 0, 0, 0},
				{0, 0, 0, 0, 0, 1, 1, 1, 0},
				{0, 0, 0, 0, 0, 0, 0, 1, 0},
				{0, 0, 0, 0, 0, 0, 0, 1, 0}
			},
			{
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{1, 1, 1, 1, 0, 0, 0, 0, 0},
				{0, 0, 0, 1, 0, 0, 0, 0, 0},
				{0, 0, 0, 1, 0, 0, 0, 0, 0},
				{0, 0, 0, 1, 0, 0, 0, 0, 0},
				{0, 0, 0, 1, 1, 1, 1, 1, 0},
				{0, 0, 0, 0, 0, 0, 0, 1, 0},
				{0, 0, 0, 0, 0, 0, 0, 1, 0},
				{0, 0, 0, 0, 0, 0, 0, 1, 0},
				{0, 0, 0, 0, 0, 0, 0, 1, 0},
				{0, 0, 0, 0, 0, 0, 0, 1, 0},
				{0, 0, 0, 0, 0, 0, 0, 1, 0},
				{0, 0, 0, 0, 0, 0, 0, 1, 0}
			},
			{
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{1, 1, 1, 1, 1, 1, 1, 1, 0},
				{0, 0, 0, 0, 0, 0, 0, 1, 0},
				{0, 0, 0, 0, 0, 0, 0, 1, 0},
				{0, 0, 0, 0, 0, 0, 0, 1, 0},
				{0, 0, 0, 0, 0, 0, 0, 1, 0},
				{0, 0, 0, 0, 0, 0, 0, 1, 0},
				{0, 0, 0, 0, 0, 0, 0, 1, 0},
				{0, 0, 0, 0, 0, 0, 0, 1, 0},
				{0, 0, 0, 0, 0, 0, 0, 1, 0},
				{0, 0, 0, 0, 0, 0, 0, 1, 0},
				{0, 0, 0, 0, 0, 0, 0, 1, 0},
				{0, 0, 0, 0, 0, 0, 0, 1, 0}
			},
		};

		//RandomMapGenerator random = new RandomMapGenerator();
		grid = new Grid(test[level - 1]);
		//grid = new Grid(random.randomMap());
		control = new Control();
		enemyManager = new EnemyManager();
		towerManager = new TowerManager();
		screenX = grid.getGridSlotSize() * grid.getGridWidth();
		screenY = grid.getGridSlotSize() * grid.getGridHeight();
		


	}

	//////////////////////////////////////////////////////////////////////////////
	//////////////////////////////UPDATES/////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////
	public void updateObjects() {
		int delay = 100; // ~5 updates per second
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Checking if game is lost
                if (remainingChances < 1) {
                    gameLost = true;
                }
				//Checking if game is won
                if (enemyManager.getWaveNo() == 4 && enemyManager.enemyList.size() == 0) {
                    gameWon = true;
                }
                if (gameLost || gameWon) {
                    return;
                }
				updateTime();
				updateWave();
				updateEnemies(); // updating enemies
				updateTowerTargets(); // updating targets
				updateUserInputs(); // updating the controller
				updateProjectiles();//updating the projectiles
				updateExplosion();
			}
		};
		new Timer(delay, taskPerformer).start();
	}

	//UPDATE TIME
	private void updateTime() {
		frameRate++;
		if (frameRate % 5 == 0) {
			second++;
			if (second > 59) {
				minute++;
				second = 0;
			}
		}
		time = minute + ":" + second;
	}

	//UPDATING WAVES
	public void updateWave() {
		//Truong's coding part
		if (frameRate % 10 == 0) {
			if (enemyManager.getWaveNo() != 3) {
				if (numOfWaveEnemy < 5) {
					enemyManager.initializeEnemies();
					numOfWaveEnemy++;
				} else if (enemyManager.enemyList.isEmpty()) {
					numOfWaveEnemy = 0;
					enemyManager.setWaveNo(enemyManager.getWaveNo() + 1);
				}
			} else {
				enemyManager.initializeEnemies();
				enemyManager.setWaveNo(enemyManager.getWaveNo() + 1);
			}
		}
	}

	//UPDATE ENEMIES
	private void updateEnemies() {
		//update alive enemies
		for (int i = 0; i < enemyManager.enemyList.size(); i++) {
			if (!(enemyManager.enemyList.get(i).isAlive)) {
				explosion.add(enemyManager.enemyList.get(i));
				playerScore = playerScore + enemyManager.enemyList.get(i).getResourceGiven() * 3;
				playerGold = playerGold + enemyManager.enemyList.get(i).getResource();
				enemyManager.enemyList.get(i).playEnemyDie();
				enemyManager.killEnemy(i);
			}
		}
		if (enemyManager.enemyCount != 0) {
			for (int i = 0; i < enemyManager.enemyList.size(); i++) {
				if (enemyManager.enemyList.get(i).getVelocityX() == -enemyManager.enemyList.get(i)
					.getSpeed() && enemyManager.enemyList.get(i).getVelocityY() == 0) {
					enemyManager.enemyList.get(i).setEnemyImage(0);
                    if (enemyManager.enemyList.get(i).isHasSidePath()) {
                        enemyManager.enemyList.get(i).setEnemySidePathImage(0);
                    }
				} else if (
					enemyManager.enemyList.get(i).getVelocityX() == enemyManager.enemyList.get(i)
						.getSpeed() && enemyManager.enemyList.get(i).getVelocityY() == 0) {
					enemyManager.enemyList.get(i).setEnemyImage(3);
                    if (enemyManager.enemyList.get(i).isHasSidePath()) {
                        enemyManager.enemyList.get(i).setEnemySidePathImage(3);
                    }
				} else if (enemyManager.enemyList.get(i).getVelocityX() == 0
					&& enemyManager.enemyList.get(i).getVelocityY() == -enemyManager.enemyList
					.get(i).getSpeed()) {
					enemyManager.enemyList.get(i).setEnemyImage(1);
                    if (enemyManager.enemyList.get(i).isHasSidePath()) {
                        enemyManager.enemyList.get(i).setEnemySidePathImage(1);
                    }
				} else {
					enemyManager.enemyList.get(i).setEnemyImage(2);
                    if (enemyManager.enemyList.get(i).isHasSidePath()) {
                        enemyManager.enemyList.get(i).setEnemySidePathImage(2);
                    }
				}

			}
		}
		for (int i = 0; i < enemyManager.enemyList.size(); i++) {
			double hppercent = ((double) enemyManager.enemyList.get(i).getHealth()
				/ (double) enemyManager.enemyList.get(i).getMaxHealth());
			enemyManager.enemyList.get(i).setEnemyHB(hppercent);
			int gridX = enemyManager.enemyList.get(i).locX / 64;
			int gridY = enemyManager.enemyList.get(i).locY / 64;
            if (enemyManager.enemyList.get(i).getVelocityY() == -enemyManager.enemyList.get(i)
                .getSpeed() && grid.getGridSlot(gridX + 1, gridY) instanceof Road
                && enemyManager.enemyList.get(i).getLocY() > 5 + gridY * grid.getGridSlotSize()) {
                gridY++;
            }

			if (gridX == grid.getGridWidth() - 1
				&& enemyManager.enemyList.get(i).getVelocityX() == enemyManager.enemyList.get(i)
				.getSpeed()) {
				enemyManager.enemyList.remove(i);
				remainingChances--;
				if (remainingChances < 1) {
					gameLost = true;
				}

				continue;
			}

			int switchCase;
			if (gridY + 1 < 9) {
                if (gridY == 0) {
                    switchCase = -1; //start
                } else if (grid.getGridSlot(gridX, gridY + 1) instanceof Road
                    && enemyManager.enemyList.get(i).getVelocityY() == enemyManager.enemyList.get(i)
                    .getSpeed()) {
                    switchCase = 1; //DOWN
                } else if (grid.getGridSlot(gridX, gridY - 1) instanceof Road
                    && enemyManager.enemyList.get(i).getVelocityY() != enemyManager.enemyList.get(i)
                    .getSpeed() && !(grid.getGridSlot(gridX + 1, gridY) instanceof Road)) {
                    switchCase = 2; //UP
                } else if (grid.getGridSlot(gridX + 1, gridY) instanceof Road) {
                    switchCase = 3; //RIGHT
                } else {
                    switchCase = 0; //DEFAULT
                }
			} else {
                if (grid.getGridSlot(gridX + 1, gridY) instanceof Road) {
                    switchCase = 3; //RIGHT
                } else {
                    switchCase = 2; //UP
                }

			}
			switch (switchCase) {
				case -1: {
					enemyManager.enemyList.get(i)
						.setVelocity(0, (enemyManager.enemyList.get(i).getSpeed()));
					enemyManager.enemyList.get(i).move(enemyManager.enemyList.get(i).getVelocityX(),
						enemyManager.enemyList.get(i).getVelocityY());
					break;
				}
				case 1: {
					enemyManager.enemyList.get(i)
						.setVelocity(0, (enemyManager.enemyList.get(i).getSpeed()));
					enemyManager.enemyList.get(i).move(enemyManager.enemyList.get(i).getVelocityX(),
						enemyManager.enemyList.get(i).getVelocityY());
					break;
				}
				case 2: {
					enemyManager.enemyList.get(i)
						.setVelocity(0, -(enemyManager.enemyList.get(i).getSpeed()));
					enemyManager.enemyList.get(i).move(enemyManager.enemyList.get(i).getVelocityX(),
						enemyManager.enemyList.get(i).getVelocityY());
					break;
				}
				case 3: {
					enemyManager.enemyList.get(i)
						.setVelocity((enemyManager.enemyList.get(i).getSpeed()), 0);
					enemyManager.enemyList.get(i).move(enemyManager.enemyList.get(i).getVelocityX(),
						enemyManager.enemyList.get(i).getVelocityY());
					break;
				}
				default: {
					enemyManager.enemyList.get(i)
						.setVelocity(0, (enemyManager.enemyList.get(i).getSpeed()));
					enemyManager.enemyList.get(i).move(enemyManager.enemyList.get(i).getVelocityX(),
						enemyManager.enemyList.get(i).getVelocityY());
					break;
				}
			}
		}
	}

	//UPDATE EXPLOSION
	private void updateExplosion() {
		for (int i = 0; i < explosion.size(); i++) {
			int curImgNum = explosion.get(i).getEnemyImageNumber();
            if (curImgNum < 4) {
                explosion.get(i).setEnemyImage(4);
            } else if (curImgNum == 9) {
                explosion.remove(i);
            } else {
                explosion.get(i).setEnemyImage(curImgNum + 1);
            }
		}
	}

	//UPDATING TOWER TARGETS
	private void updateTowerTargets() {
		for (int i = 0; i < towerManager.towerList.size(); i++) {
			for (int j = 0; j < enemyManager.enemyList.size(); j++) {

				if (!towerManager.towerList.get(i).hasTarget()
					&&
					Math.sqrt((towerManager.towerList.get(i).getLocX() - enemyManager.enemyList
						.get(j).locX) *
						(towerManager.towerList.get(i).getLocX() - enemyManager.enemyList
							.get(j).locX) +
						(towerManager.towerList.get(i).getLocY() - enemyManager.enemyList
							.get(j).locY) *
							(towerManager.towerList.get(i).getLocY() - enemyManager.enemyList
								.get(j).locY))
						< towerManager.towerList.get(i).getTowerRange()
				) {
					if (enemyManager.enemyList.get(j).isAlive) {

						if (enemyManager.enemyList.get(j) instanceof AirEnemy) {
							if (towerManager.towerList.get(i) instanceof SniperTower) {
								towerManager.towerList.get(i)
									.setTarget(enemyManager.enemyList.get(j));
							}
						} else {
							if (!(towerManager.towerList.get(i) instanceof SniperTower)) {
								towerManager.towerList.get(i)
									.setTarget(enemyManager.enemyList.get(j));
							}
						}
						break;
					}

				} else if (towerManager.towerList.get(i).hasTarget()) {
					if (!towerManager.towerList.get(i).getTarget().isAlive) {
						towerManager.towerList.get(i).clearTarget();
						return;
					}

					if (Math.sqrt(
						(towerManager.towerList.get(i).getLocX() - towerManager.towerList.get(i)
							.getTarget().locX) *
							(towerManager.towerList.get(i).getLocX() - towerManager.towerList.get(i)
								.getTarget().locX) +
							(towerManager.towerList.get(i).getLocY() - towerManager.towerList.get(i)
								.getTarget().locY) *
								(towerManager.towerList.get(i).getLocY() - towerManager.towerList
									.get(i).getTarget().locY))
						> towerManager.towerList.get(i).getTowerRange()
					) {
						towerManager.towerList.get(i).clearTarget();
						break;
					}
				}
			}
		}
	}

	//UPDATE PROJECTILES
	private void updateProjectiles() {
		int time = frameRate;
        if (towerManager.towerList.size() == 0) {
            return;
        }
		for (int i = 0; i < towerManager.towerList.size(); i++) {
			for (int j = 0; j < towerManager.towerList.get(i).getProjectilesSpawned().size(); j++) {
				towerManager.towerList.get(i).getProjectilesSpawned().get(j).update();
                if (towerManager.towerList.get(i).getTarget() == null) {
                    towerManager.towerList.get(i).getProjectilesSpawned().remove(j);
                }
			}
		}
	}

	private void updateUserInputs() {
		boolean b;
		int gridNoX = (control.getMouseX()) / 64;
		int gridNoY = (control.getMouseY()) / 64;
        if (control.getMouseX() == 0 && control.getMouseY() == 0) {
            return;
        }
		if (control.getMouseY() > screenY) {
			shop.buyTower(control.getMouseX(), control.getMouseY(), playerGold);
			control.setMouseX(0);
			control.setMouseY(0);
			return;
		}
		if (grid.getGridSlot(gridNoX, gridNoY) instanceof Mountain && control.getMouseX() != 0
			&& control.getMouseY() != 0) {
			if (((Mountain) grid.getGridSlot(gridNoX, gridNoY)).hasTower
				&& ((Mountain) grid.getGridSlot(gridNoX, gridNoY)).getTower().isUpgraded()
				== false) {
				if (playerGold >= ((Mountain) grid.getGridSlot(gridNoX, gridNoY)).getTower()
					.getCost()) {
					((Mountain) grid.getGridSlot(gridNoX, gridNoY)).getTower().upgradeTower();
					((Mountain) grid.getGridSlot(gridNoX, gridNoY)).getTower().setUpgraded(true);
					control.setMouseX(0);
					control.setMouseY(0);
					playerGold =
						playerGold - ((Mountain) grid.getGridSlot(gridNoX, gridNoY)).getTower()
							.getCost();
					return;
				}
			}
		}

        if (!(grid.getGridSlot(gridNoX, gridNoY) instanceof Mountain)) {
            return;
        }
		b = ((Mountain) grid.getGridSlot(gridNoX, gridNoY))
			.mouseHitThisSlot(shop.getTowerBought(), shop.getTowerToPlace(),
				gridNoX * grid.getGridHeight(), gridNoY * grid.getGridWidth());
		if (shop.getTowerToPlace() != null) {
			if (b && playerGold >= shop.getTowerToPlace().getCost()) {
				towerManager.addTower(shop.getTowerToPlace());
				shop.getTowerToPlace().setLocX(gridNoX * 64);
				shop.getTowerToPlace().setLocY(gridNoY * 64);
				shop.setTowerBought(false);
				control.setMouseX(0);
				control.setMouseY(0);
				playerGold = playerGold - shop.getTowerToPlace().getCost();
				shop.setTowerToPlace(null);
			}
		}
	}

	//getters
	public Grid getGrid() {
		return grid;
	}

	public EnemyManager getEnemyManager() {
		return enemyManager;
	}

	public TowerManager getTowerManager() {
		return towerManager;
	}

	public Shop getShop() {
		return shop;
	}

	public Control getControl() {
		return control;
	}

	public String getTime() {
		return time;
	}

	public int getPlayerGold() {
		return playerGold;
	}

	public int getRemainingChances() {
		return remainingChances;
	}

	public boolean getIsGameWon() {
		return gameWon;
	}

	public boolean getIsGameLost() {
		return gameLost;
	}

	public ArrayList<Enemy> getExplosion() {
		return explosion;
	}
}

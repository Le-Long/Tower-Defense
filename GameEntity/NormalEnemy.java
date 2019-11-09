package GameEntity;

public class NormalEnemy extends Enemy{

    public NormalEnemy(int locX, int locY) {
        super(locX, locY);
        setSpeed(12);
        setArmor(5);
        setHealth(500);
        setMaxHealth(500);
        setResourceGiven(50);
        setHasSidePath(false);

        enemyImageBuffer[0] = "/images/towerDefense_tile245_left.png";
        enemyImageBuffer[1] = "/images/towerDefense_tile245_up.png";
        enemyImageBuffer[2] = "/images/towerDefense_tile245_down.png";
        enemyImageBuffer[3] = "/images/towerDefense_tile245_right.png";
    }
}

package GameEntity;

public class TankerEnemy extends Enemy{

    public TankerEnemy(int locX, int locY) {
        super(locX, locY);
        setSpeed(8);
        setArmor(20);
        setHealth(800);
        setMaxHealth(800);
        setResourceGiven(100);
        setHasSidePath(true);

        enemyImageBuffer[0] = "/images/towerDefense_tile268_left.png";
        enemyImageBuffer[1] = "/images/towerDefense_tile268_up.png";
        enemyImageBuffer[2] = "/images/towerDefense_tile268_down.png";
        enemyImageBuffer[3] = "/images/towerDefense_tile268_right.png";

        enemySidePathImageBuffer[0] = "/images/towerDefense_tile291_left.png";
        enemySidePathImageBuffer[1] = "/images/towerDefense_tile291_up.png";
        enemySidePathImageBuffer[2] = "/images/towerDefense_tile291_down.png";
        enemySidePathImageBuffer[3] = "/images/towerDefense_tile291_right.png";
    }
}

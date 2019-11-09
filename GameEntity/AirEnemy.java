package GameEntity;

public class AirEnemy extends Enemy{

    public AirEnemy(int locX, int locY) {
        super(locX, locY);
        setSpeed(10);
        setArmor(10);
        setHealth(500);
        setMaxHealth(500);
        setResourceGiven(100);
        setHasSidePath(true);

        enemyImageBuffer[0] = "/images/towerDefense_tile270_left.png";
        enemyImageBuffer[1] = "/images/towerDefense_tile270_up.png";
        enemyImageBuffer[2] = "/images/towerDefense_tile270_down.png";
        enemyImageBuffer[3] = "/images/towerDefense_tile270_right.png";

        enemySidePathImageBuffer[0] = "/images/towerDefense_tile293_left.png";
        enemySidePathImageBuffer[1] = "/images/towerDefense_tile293_up.png";
        enemySidePathImageBuffer[2] = "/images/towerDefense_tile293_down.png";
        enemySidePathImageBuffer[3] = "/images/towerDefense_tile293_right.png";

    }
}

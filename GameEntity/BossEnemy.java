package GameEntity;

public class BossEnemy extends Enemy {

    public BossEnemy(int locX, int locY) {
        super(locX, locY);
        setSpeed(7);
        setArmor(30);
        setHealth(2000);
        setMaxHealth(2000);
        setResourceGiven(500);
        setHasSidePath(true);

        enemyImageBuffer[0] = "/images/towerDefense_tile269_left.png";
        enemyImageBuffer[1] = "/images/towerDefense_tile269_up.png";
        enemyImageBuffer[2] = "/images/towerDefense_tile269_down.png";
        enemyImageBuffer[3] = "/images/towerDefense_tile269_right.png";

        enemySidePathImageBuffer[0] = "/images/towerDefense_tile292_left.png";
        enemySidePathImageBuffer[1] = "/images/towerDefense_tile292_up.png";
        enemySidePathImageBuffer[2] = "/images/towerDefense_tile292_down.png";
        enemySidePathImageBuffer[3] = "/images/towerDefense_tile292_right.png";
    }
}

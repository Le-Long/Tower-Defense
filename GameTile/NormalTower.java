package GameTile;

public class NormalTower extends Tower {
    public NormalTower() {
        super();
        setImage("/images/towerDefense_NormalTower.png");
        for (int i=0;  i < 4; i++){
            projectilesImageBuffer[i]="/images/towerDefense_tile272.png";
        }
        setCost(200);
        setDamage(100);
        setTowerRange(150);
        setAttackSpeed(25);
        setProjectileType(0);
    }
}

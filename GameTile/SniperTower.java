package GameTile;

public class SniperTower extends Tower {
    public SniperTower() {
        super();
        setImage("/images/towerDefense_SniperTower.png");
        for (int i=0;  i < 4; i++){
            projectilesImageBuffer[i]="/images/towerDefense_tile272.png";
        }
        setCost(400);
        setDamage(70);
        setTowerRange(200);
        setAttackSpeed(25);
        setProjectileType(1);
    }
}

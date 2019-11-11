package GameTile;

public class SniperTower extends Tower {
    public SniperTower(){
        super();
        setImage("/images/towerDefense_SniperTower");
        setCost(400);
        setDamage(70);
        setTowerRange(200);
        setAttackSpeed(25);
        setProjectileType(1);
    }
}

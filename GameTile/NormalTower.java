package GameTile;

public class NormalTower extends Tower {
    public NormalTower(){
        super();
        setImage("/images/towerDefense_NormalTower");
        setCost(200);
        setDamage(100);
        setTowerRange(150);
        setAttackSpeed(25);
        setProjectileType(0);
    }
}

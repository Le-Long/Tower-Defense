package GameTile;

public class MachinegunTower extends Tower {
    public MachinegunTower(){
        super();
        setImage("/images/towerDefense_MachinegunTower");
        setCost(500);
        setDamage(100);
        setTowerRange(120);
        setAttackSpeed(25);
        setProjectileType(2);
    }
}

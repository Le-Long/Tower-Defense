package GameTile;

public class MachinegunTower extends Tower {
    public MachinegunTower() {
        super();
        setImage("/images/towerDefense_MachinegunTower.png");
        for (int i=0;  i < 4; i++){
            projectilesImageBuffer[i]="/images/towerDefense_tile272.png";
        }
        setCost(500);
        setDamage(100);
        setTowerRange(120);
        setAttackSpeed(25);
        setProjectileType(2);
    }
}

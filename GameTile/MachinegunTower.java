package GameTile;

import java.io.IOException;

import javax.imageio.ImageIO;

public class MachinegunTower extends Tower {
    public MachinegunTower() {
        super();
        setImage("/images/towerDefense_tile182.png");
        setTowerTurretImageFile("/images/towerDefense_tile203.png");
        try {
            projectilesImageBuffer = ImageIO.read(getClass().getResourceAsStream("/images/towerDefense_tile272.png"));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        setCost(500);
        setDamage(100);
        setTowerRange(120);
        setAttackSpeed(25);
        setProjectileType(2);
    }
}

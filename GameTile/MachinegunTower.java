package GameTile;

import javax.imageio.ImageIO;
import java.io.IOException;

public class MachinegunTower extends Tower {
    public MachinegunTower() {
        super();
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/images/towerDefense_tile182.png")));
            setTowerTurretImageFile(ImageIO.read(getClass().getResourceAsStream("/images/towerDefense_tile203.png")));
            projectilesImageBuffer = ImageIO.read(getClass().getResourceAsStream("/images/towerDefense_tile272.png"));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        setCost(500);
        setDamage(80);
        setTowerRange(160);
        setAttackSpeed(3);
        setProjectileType(2);
    }
}
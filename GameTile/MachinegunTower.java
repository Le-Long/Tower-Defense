package GameTile;

import javax.imageio.ImageIO;
import java.io.IOException;

public class MachinegunTower extends Tower {
    public MachinegunTower() {
        super();
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/images/towerDefense_tile182.png")));
            setTowerTurretImageFile(ImageIO.read(getClass().getResourceAsStream("/images/towerDefense_tile203.png")));
            setProjectilesImageBuffer(ImageIO.read(getClass().getResourceAsStream("/images/towerDefense_tile272.png")));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        upgradedPath[0] = "/images/towerDefense_tile204.png";
        upgradedPath[1] = "/images/towerDefense_tile273.png";
        setCost(500);
        setDamage(80);
        setTowerRange(160);
        setAttackSpeed(3);
        setProjectileType(2);
    }
}
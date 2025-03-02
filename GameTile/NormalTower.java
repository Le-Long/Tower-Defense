package GameTile;

import javax.imageio.ImageIO;
import java.io.IOException;

public class NormalTower extends Tower {
    public NormalTower() {
        super();
        try {
            setImage(getClass().getResource("/images/towerDefense_tile180.png"));
            setTowerTurretImageFile(getClass().getResource("/images/towerDefense_tile249.png"));
            setProjectilesImageBuffer(getClass().getResource("/images/towerDefense_tile272.png"));
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        upgradedPath[0] = "/images/towerDefense_tile250.png";
        upgradedPath[1] = "/images/towerDefense_tile273.png";
        setCost(200);
        setDamage(100);
        setTowerRange(150);
        setAttackSpeed(2);
        setProjectileType(0);
    }
}
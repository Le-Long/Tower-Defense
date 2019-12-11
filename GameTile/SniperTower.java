package GameTile;

import javax.imageio.ImageIO;
import java.io.IOException;

public class SniperTower extends Tower {
    public SniperTower() {
        super();
        try {
            setImage(getClass().getResource("/images/towerDefense_tile181.png"));
            setTowerTurretImageFile(getClass().getResource("/images/towerDefense_tile205.png"));
            setProjectilesImageBuffer(getClass().getResource("/images/towerDefense_tile251.png"));
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        upgradedPath[0] = "/images/towerDefense_tile206.png";
        upgradedPath[1] = "/images/towerDefense_tile252.png";
        setCost(400);
        setDamage(70);
        setTowerRange(200);
        setAttackSpeed(2);
        setProjectileType(1);
    }
}

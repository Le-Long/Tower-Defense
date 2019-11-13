package GameTile;

import javax.imageio.ImageIO;
import java.io.IOException;

public class SniperTower extends Tower {
    public SniperTower() {
        super();
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/images/towerDefense_tile181.png")));
            setTowerTurretImageFile(ImageIO.read(getClass().getResourceAsStream("/images/towerDefense_tile205.png")));
            projectilesImageBuffer = ImageIO.read(getClass().getResourceAsStream("/images/towerDefense_tile272.png"));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        setCost(400);
        setDamage(70);
        setTowerRange(200);
        setAttackSpeed(2);
        setProjectileType(1);
    }
}

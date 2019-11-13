package GameTile;

import javax.imageio.ImageIO;
import java.io.IOException;

public class NormalTower extends Tower {
    public NormalTower() {
        super();
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/images/towerDefense_tile180.png")));
            setTowerTurretImageFile(ImageIO.read(getClass().getResourceAsStream("/images/towerDefense_tile249.png")));
            projectilesImageBuffer = ImageIO.read(getClass().getResourceAsStream("/images/towerDefense_tile272.png"));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        setCost(200);
        setDamage(100);
        setTowerRange(150);
        setAttackSpeed(2);
        setProjectileType(0);
    }
}
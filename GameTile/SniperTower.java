package GameTile;

import java.io.IOException;

import javax.imageio.ImageIO;

public class SniperTower extends Tower {
    public SniperTower() {
        super();
        setImage("/images/towerDefense_SniperTower.png");
        try {
            projectilesImageBuffer = ImageIO.read(getClass().getResourceAsStream("/images/towerDefense_tile272.png"));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        setCost(400);
        setDamage(70);
        setTowerRange(200);
        setAttackSpeed(25);
        setProjectileType(1);
    }
}

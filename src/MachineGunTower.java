import java.awt.*;
/**
 * MachineGunTower.java
 * A tower with unique properties that shoots enemies
 * @author Kyle To, Eric Ke
 * Last Updated: January 19 2019
 */
public class MachineGunTower extends RifleTower {

    public MachineGunTower(double x, double y, Game game) {
        super(x, y, game);
        setGroundTargeting(true);
        setAirTargeting(false);
        setDamage(50);
        setFireRate(0.3);
        setRange(new Circle(x, y, 290));
        setImage(Toolkit.getDefaultToolkit().getImage("resources/Towers/MachineGunBody.png"));
    }

}
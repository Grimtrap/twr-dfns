import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * ShrapnelTower.java
 * A tower with unique properties that shoots enemies
 * @author Eric Ke, Kyle To
 * Last Updated: January 19 2019
 */
public class ShrapnelTower extends Tower {

    public ShrapnelTower(double x, double y, Game game) {
        super(x, y, game);
        setGroundTargeting(true);
        setAirTargeting(false);
        setDamage(20);
        setFireRate(1);
        setRange(new Circle(x, y, 100));
        setDamageType(DamageTypes.EXPLOSIVE);
        setImage(Toolkit.getDefaultToolkit().getImage("resources/Towers/ShrapnelTower.png"));
    }

    public void attack(double elapsedTime) {

        if (getWithin() != null) {
            LinkedList<Enemy> within = getWithin();
            for(int i = 0; i < within.size(); i++){
                within.get(i).takeDmg(getDamage(), getDamageType());
            }
        }
    }
}
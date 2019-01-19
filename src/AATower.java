import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AATower extends RifleTower {

    public AATower(double x, double y, Game game) {
        super(x, y, game);
        setGroundTargeting(false);
        setAirTargeting(true);
        setDamage(50);
        setCost(250);
        setFireRate(0.2);
        setProjectileExplosionRadius(100);
        setRange(new Circle(x, y, 350));
        setProjectileSpeed(5000);
        setImage(Toolkit.getDefaultToolkit().getImage("resources/Towers/AAGunBody.png"));
    }

}
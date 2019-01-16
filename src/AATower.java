import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AATower extends Tower {

    public AATower(double x, double y) {
        super(x, y);
        setGroundTargeting(false);
        setAirTargeting(true);
        setDamage(10);
        setFireRate(85);
        setRange(new Circle(x, y, 100));
        //setProjectileImagePath();
        //setDamageType();
        setProjectileSpeed(50);
        setProjectileExplosionRadius(0);
        setImage(Toolkit.getDefaultToolkit().getImage("resources/Towers/AAGunBody.png"));
    }

    public void attack() {
        //create an array of enemies within its range
        //setWithin(findTargets());
        //fires at the enemy closest to base
        if (getWithin() != null) {
            setTarget(getWithin().getFirst());
            //Creates action listener updates projectile based on timer
            ActionListener shoot = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setTargetX(getWithin().get(0).getX());
                    setTargetY(getWithin().get(0).getY());
                    new Projectile(getProjectileImagePath(), getDamageType(), getDamage(), getProjectileSpeed(), getProjectileExplosionRadius(), getX(), getY(), getTargetX(), getTargetY(), getTarget());
                }
            };
            Timer t = new Timer((int) (getFireRate()), shoot);
            t.start();
        }
    }
}
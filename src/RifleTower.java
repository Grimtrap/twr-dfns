import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RifleTower extends Tower {

    public RifleTower(double x, double y) {
        super(x, y);
        setGroundTargeting(true);
        setAirTargeting(true);
        //setDamage();
        //setFireRate();
        //setRange(new Circle(x, y, ));
        //setProjectileImagePath();
        //setDamageType();
        //setProjectileSpeed();
        setProjectileExplosionRadius(0);
    }

    public void attack(){
        //create an array of enemies within its range
        findTargets();
        //fires at the enemy closest to base
        //Creates action listener updates projectile based on timer
        ActionListener shoot = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new Projectile();
            }
        };
        Timer t = new Timer ((int)(getFireRate()), shoot);
        t.start();
    }
}
import javax.swing.*;
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
        //setRange(new Circle(x, y, ));
        //setProjectileImagePath();
        //setDamageType();
        //setProjectileSpeed();
        setProjectileExplosionRadius(0);
    }

    public void attack(){
        //create an array of enemies within its range
        setEnemies(findTargets());
        //fires at the enemy closest to base
        this.setTargetX(this.getEnemies().get(0).getX());
        this.setTargetY(this.getEnemies().get(0).getY());
        this.setTarget(this.getEnemies().get(0));
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
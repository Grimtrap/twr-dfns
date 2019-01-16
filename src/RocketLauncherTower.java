import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RocketLauncherTower extends Tower {

    public RocketLauncherTower(double x, double y) {
        super(x, y);
        setGroundTargeting(true);
        setAirTargeting(true);
        setDamage(150);
        setFireRate(1000);
        //setRange(new Circle(x, y, ));
        //setProjectileImagePath();
        //setDamageType();
        //setProjectileSpeed();
        //setProjectileExplosionRadius();
    }

    public void attack(){
        //create an array of enemies within its range
        setEnemies(findTargets());
        //fires at the enemy closest to base
        this.setTarget(this.getEnemies().get(0));
        //Creates action listener updates projectile based on timer
        ActionListener shoot = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTargetX(getEnemies().get(0).getX());
                setTargetY(getEnemies().get(0).getY());
                new Projectile(getProjectileImagePath(), getDamageType(), getDamage(), getProjectileSpeed(), getProjectileExplosionRadius(),getX(), getY(), getTargetX(), getTargetY(),getTarget());
            }
        };
        Timer t = new Timer ((int)(getFireRate()), shoot);
        t.start();
    }
}
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
        //setRange(new Circle(x, y, ));
        //setProjectileImagePath();
        //setDamageType();
        //setProjectileSpeed();
        setProjectileExplosionRadius(0);
        setImage(Toolkit.getDefaultToolkit().getImage("resources/Towers/AAGunBody.png"));
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
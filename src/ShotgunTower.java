import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShotgunTower extends Tower {

    public ShotgunTower(double x, double y) {
        super(x, y);
        setGroundTargeting(true);
        setAirTargeting(false);
        setDamage(30);
        setFireRate(1000);
        //setRange(new Circle(x, y, ));
        //setProjectileImagePath();
        //setDamageType();
        //setProjectileSpeed();
        setProjectileExplosionRadius(0);
        setImage(Toolkit.getDefaultToolkit().getImage("resources/Towers/ShotgunBody.png"));
    }

    public void attack(){
        //create an array of enemies within its range
        setWithin(findTargets());
        //fires at the enemy closest to base
        if(getWithin() != null) {
            this.setTarget(this.getWithin().get(0));
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
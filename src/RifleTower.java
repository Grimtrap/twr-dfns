import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RifleTower extends Tower {

    public RifleTower(double x, double y) {
        super(x, y);
        setGroundTargeting(true);
        setAirTargeting(true);
        setDamage(30);
        setFireRate(2000);
        setRange(new Circle(x, y, 1100));
        //setProjectileImagePath();
        //setDamageType();
        setProjectileSpeed(1);
        setProjectileExplosionRadius(0);
        setImage(Toolkit.getDefaultToolkit().getImage("resources/Towers/BasicGunBody.png"));
    }

    public void attack() {
        setTarget(getWithin().getFirst());
        Projectile temp;
        Thread t;
        temp = new Projectile(getProjectileImagePath(), getDamageType(), getDamage(), getProjectileSpeed(), getProjectileExplosionRadius(), getX(), getY(), getTargetX(), getTargetY(), getTarget());
        t = new ProjectileThread(temp);
        t.start();
        getPro().add(temp);
        getShots().add(t);
    }
}
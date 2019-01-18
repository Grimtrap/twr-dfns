import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;

public class RifleTower extends Tower {
    double timeShot;
    int count;

    public RifleTower(double x, double y) {
        super(x, y);
        setGroundTargeting(true);
        setAirTargeting(true);
        setDamage(50);
        setFireRate(100000000);
        setRange(new Circle(x, y, 1100));
        //setProjectileImagePath();
        //setDamageType();
        setProjectileSpeed(1);
        setProjectileExplosionRadius(0);
        setImage(Toolkit.getDefaultToolkit().getImage("resources/Towers/BasicGunBody.png"));
        setClock(new Clock());
        timeShot = getFireRate();
        count = 0;
    }

    public void attack(double elapsedTime) {
        if (timeShot <= 0) {
            setTarget(getWithin().getFirst());
            Projectile temp;
            ProjectileThread p;
            temp = new Projectile(this, getProjectileImagePath(), getDamageType(), getDamage(), getProjectileSpeed(), getProjectileExplosionRadius(), getX(), getY(), getTargetX(), getTargetY(), getTarget());
            p = new ProjectileThread(temp);
            p.start();
            getPro().add(temp);
            getShots().add(p);
            timeShot = getFireRate();
            count++;
            System.out.println(count);
        }else {
            timeShot = timeShot - elapsedTime;
            //System.out.println(timeShot);
        }
    }
}
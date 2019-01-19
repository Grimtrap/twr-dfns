import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RocketLauncherTower extends Tower {

    public RocketLauncherTower(double x, double y, Game game) {
        super(x, y, game);
        setGroundTargeting(true);
        setAirTargeting(true);
        setDamage(150);
        setFireRate(2.5);
        setRange(new Circle(x, y, 100));
        //setProjectileImagePath();
        //setDamageType();
        setProjectileSpeed(50);
        //setProjectileExplosionRadius();
        setImage(Toolkit.getDefaultToolkit().getImage("resources/Towers/SAMMissileBody.png"));
    }

    public void attack(double elapsedTime){
        //create an array of enemies within its range
        //setWithin(findTargets());
        //fires at the enemy closest to base
        if(getWithin() != null) {
            setTarget(getWithin().getFirst());
            //Creates action listener updates projectile based on timer
            ActionListener shoot = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setTargetX(getWithin().get(0).getX());
                    setTargetY(getWithin().get(0).getY());
                    //new Projectile(getProjectileImagePath(), getDamageType(), getDamage(), getProjectileSpeed(), getProjectileExplosionRadius(), getX(), getY(), getTargetX(), getTargetY(), getTarget());
                }
            };
            Timer t = new Timer((int) (getFireRate()), shoot);
            t.start();
        }
    }
}
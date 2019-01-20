import java.awt.*;

/**
 * LaserTower.java
 * A tower with unique properties that shoots enemies
 * @author Kyle To, Eric Ke
 * Last Updated: December 19 2019
 */
public class LaserTower extends Tower {

    public LaserTower(double x, double y, Game game) {
        super(x, y, game);
        setGroundTargeting(true);
        setAirTargeting(true);
        setDamage(25);
        setCost(600);
        setDamageType(DamageTypes.PIERCE);
        setFireRate(0.1);
        setRange(new Circle(x, y, 350));
        setImage(Toolkit.getDefaultToolkit().getImage("resources/Towers/LaserGunBody.png"));
    }

    public void attack(double elapsedTime){
        if (getAttackTime() <= 0 && !getWithin().isEmpty()) {
            setTarget(getWithin().getFirst());
            getTarget().takeDmg(getDamage(), getDamageType());
            setAttackTime(getFireRate());
            SoundPlayer.playSound("resources/Projectiles/LaserGun.wav");
        }else {
            setAttackTime(getAttackTime() - elapsedTime);
        }
    }
}
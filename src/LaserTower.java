import java.awt.*;

/**
 * LaserTower.java
 * A tower with unique properties that shoots enemies
 * @author Kyle To, Eric Ke, Michael T.
 * Last Updated: December 19 2019
 */
public class LaserTower extends Tower {

    /**
     * creates a new tower
     * @param x the tower's x position
     * @param y the tower's y position
     * @param game the game that the tower is in
     */
    public LaserTower(double x, double y, Game game) {
        super(x, y, game);
        setGroundTargeting(true);
        setAirTargeting(true);
        setDamage(25);
        setCost(700);
        setDamageType(DamageTypes.PIERCE);
        setFireRate(0.1);
        setRange(new Circle(x, y, 350));
        setImage(Toolkit.getDefaultToolkit().getImage("resources/Towers/LaserGunBody.png"));
    }

    /**
     * attacks the target
     * @param elapsedTime time passed since last check
     */
    @Override
    public void attack(double elapsedTime){
        //this is special because it doesn't shoot a projectile
        //it just instantly deals damage
        if (getAttackTime() <= 0 && !getWithin().isEmpty()) {
            setTarget(getWithin().getFirst());
            getTarget().takeDmg(getDamage(), getDamageType());
            setAttackTime(getFireRate());
            SoundPlayer.playSound("LaserGun.wav");
        }else {
            setAttackTime(getAttackTime() - elapsedTime);
        }
    }

    /**
     * draws the tower and beam
     * @param g paintComponent graphics
     */
    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(Color.RED);
        if(getTarget() != null && getGame().getEnemies().contains(getTarget())) {
            //draws a beam, it's pretty cool
            g.drawLine((int) this.getX(), (int) this.getY(), (int) getTarget().getX(), (int) getTarget().getY());
        }

    }
}
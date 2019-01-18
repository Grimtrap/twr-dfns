import java.awt.*;
import java.util.LinkedList;

public class RifleTower extends Tower {
    int count;
    private double fireRate;

    public RifleTower(double x, double y, Game game) {
        super(x, y, game);
        setGroundTargeting(true);
        setAirTargeting(true);
        setDamage(50);
        this.fireRate = 0.5;
        setRange(new Circle(x, y, 1100));
        //setProjectileImagePath();
        //setDamageType();
        setProjectileSpeed(600);
        setProjectileExplosionRadius(0);
        setImage(Toolkit.getDefaultToolkit().getImage("resources/Towers/BasicGunBody.png"));
        setClock(new Clock());
        count = 0;
    }

    @Override
    public void update(double timeElapsed) {
        findTargets();
        attack(timeElapsed);
        LinkedList<Projectile> projectiles = getProjectiles();
        for(int i = 0; i < projectiles.size(); i++) {
            if(!projectiles.get(i).isActive()) {
                projectiles.remove(i);
            } else {
                projectiles.get(i).update(timeElapsed);
            }
        }
    }

    public void attack(double elapsedTime) {
        if (getAttackTime() <= 0 && !getWithin().isEmpty()) {
            setTarget(getWithin().getFirst());
            getProjectiles().add(
                    new Projectile(
                            this, getProjectileImagePath(), getDamageType(), getDamage(), getProjectileSpeed(), getProjectileExplosionRadius(), getX(), getY(), getTargetX(), getTargetY(), getTarget()
                    )
            );
            setAttackTime(fireRate);
            count++;
            System.out.println(count);
        }else {
            setAttackTime(getAttackTime() - elapsedTime);
        }
    }
}
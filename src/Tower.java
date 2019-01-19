import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;

abstract class Tower {

    private Image image;
    private Clock clock;
    private double x;
    private Game game;
    private int cost;
    private double y;
    private double targetX;
    private double targetY;
    private double dmg;
    private double rate;
    private double attackTime;
    private boolean groundTargeting;
    private boolean airTargeting;
    private Circle range;
    private LinkedList<Enemy> enemies = new LinkedList<>(); // stores all enemies in range of tower
    private LinkedList<Enemy> within;
    private LinkedList<Projectile> projectiles;
    private LinkedList<Thread> shots;
    private Enemy target;
    private String projectileImagePath;
    private byte damageType;
    private double projectileSpeed;
    private double projectileExplosionRadius;

    public Tower (double x, double y, Game game){
        this.x = x;
        this.y = y;
        this.game = game;
        enemies = game.getEnemies();
        within = new LinkedList<>();
        projectiles = new LinkedList<>();
        shots = new LinkedList<>();
    }

    public void draw(Graphics g){
        g.setColor(Color.RED);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform backup = g2d.getTransform();
        if(target!=null) {
            AffineTransform a = AffineTransform.getRotateInstance(Calculations.getAngle(x, y, target.getX(), target.getY()), x, y);
            g2d.setTransform(a);
        }
        g2d.drawImage(image, (int)Calculations.Center(x,64), (int)Calculations.Center(y,64), null);
        g2d.setTransform(backup);
    }

    public void setDamage(double dmg){
        this.dmg = dmg;
    }

    public double getDamage(){
        return this.dmg;
    }

    public void setFireRate(double rate){
        this.rate = rate;
    }

    public double getFireRate(){
        return this.rate;
    }

    public void update(double timeElapsed) {
        findTargets();
        attack(timeElapsed);
        LinkedList<Projectile> projectiles = getProjectiles();
        for(int i = 0; i < projectiles.size(); i++) {
            Projectile current = projectiles.get(i);
            if(!current.isActive()) {
                projectiles.remove(i);
            } else {
                current.update(timeElapsed);
            }
        }
    }

    public synchronized void findTargets(){
        LinkedList<Enemy> within = new LinkedList<>();

        if(!enemies.isEmpty()) {
            for (int i = 0; i < enemies.size(); i++) {
                try {
                    if (enemies.get(i) != null) {
                        Enemy current;

                        try {
                            current = enemies.get(i);
                        } catch (Exception e) {
                            break;
                        }

                        if(canTarget(current)) {
                            if (Math.hypot((current.getX() - x), (current.getY() - y)) <= range.getRadius()) {
                                if (!current.hasReachedEnd() && !(current.getCurrentHealth() <= 0)) {
                                    within.add(current);
                                }
                            }
                        }
                    }
                } catch(NullPointerException e1) {
                    i--;
                }
            }
        }

        this.setWithin(within);
    }

    private boolean canTarget(Enemy enemy) {
        if(groundTargeting && airTargeting) {
            return true;
        } else {
            Attributes a = enemy.getAttributes();
            if((a.isFlying() && airTargeting) || (!a.isFlying() && groundTargeting)) {
                return true;
            }
        }

        return false;
    }

    abstract void attack(double elapsedTime);

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDmg() {
        return dmg;
    }

    public void setDmg(double dmg) {
        this.dmg = dmg;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public boolean isGroundTargeting() {
        return groundTargeting;
    }

    public void setGroundTargeting(boolean groundTargeting) {
        this.groundTargeting = groundTargeting;
    }

    public boolean isAirTargeting() {
        return airTargeting;
    }

    public void setAirTargeting(boolean airTargeting) {
        this.airTargeting = airTargeting;
    }

    public Circle getRange() {
        return range;
    }

    public void setRange(Circle range) {
        this.range = range;
    }

    public LinkedList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(LinkedList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public String getProjectileImagePath() {
        return projectileImagePath;
    }

    public void setProjectileImagePath(String projectileImagePath) {
        this.projectileImagePath = projectileImagePath;
    }

    public byte getDamageType() {
        return damageType;
    }

    public void setDamageType(byte damageType) {
        this.damageType = damageType;
    }

    public double getProjectileSpeed() {
        return projectileSpeed;
    }

    public void setProjectileSpeed(double projectileSpeed) {
        this.projectileSpeed = projectileSpeed;
    }

    public double getProjectileExplosionRadius() {
        return projectileExplosionRadius;
    }

    public void setProjectileExplosionRadius(double projectileExplosionRadius) {
        this.projectileExplosionRadius = projectileExplosionRadius;
    }

    public double getTargetX() {
        return targetX;
    }

    public void setTargetX(double targetX) {
        this.targetX = targetX;
    }

    public double getTargetY() {
        return targetY;
    }

    public void setTargetY(double targetY) {
        this.targetY = targetY;
    }

    public Enemy getTarget() {
        return target;
    }

    public void setTarget(Enemy target) {
        this.target = target;
    }

    public LinkedList<Enemy> getWithin() {
        return within;
    }

    public void setWithin(LinkedList<Enemy> within) {
        this.within = within;
    }

    public LinkedList<Thread> getShots() {
        return shots;
    }

    public void setShots(LinkedList<Thread> shots) {
        this.shots = shots;
    }

    public LinkedList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(LinkedList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    public Clock getClock() {
        return clock;
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    public double getAttackTime() {
        return attackTime;
    }

    public void setAttackTime(double attackTime) {
        this.attackTime = attackTime;
    }

    public Game getGame() {
        return game;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}

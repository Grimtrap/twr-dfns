import java.awt.*;
import java.util.LinkedList;

abstract class Tower {

    private Image image;
    private Clock clock;
    private double x;
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
        enemies = game.getEnemies();
        within = new LinkedList<>();
        projectiles = new LinkedList<>();
        shots = new LinkedList<>();
    }

    public void draw(Graphics g){
        g.drawImage(image, (int) x, (int) y, null);
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

    public void update(double timeElapsed){
    }

    public synchronized void findTargets(){
        LinkedList<Enemy> within = new LinkedList<>();
        for (int i = 0; i < enemies.size(); i++){
            if (Math.hypot((enemies.get(i).getX() -x), (enemies.get(i).getY() - y)) <= range.getRadius()){
                if(enemies.get(i) != null && !enemies.get(i).hasReachedEnd() && !(enemies.get(i).getCurrentHealth() <=0)) {
                    try {
                        within.add(enemies.get(i));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
        this.setWithin(within);
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
}

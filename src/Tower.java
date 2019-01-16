import java.awt.*;
import java.util.ArrayList;

abstract class Tower {

    private Image image;
    private double x;
    private double y;
    private double targetX;
    private double targetY;
    private double dmg;
    private double rate;
    private boolean groundTargeting;
    private boolean airTargeting;
    private Circle range;
    private ArrayList<Enemy> enemies = new ArrayList<>(); // stores all enemies in range of tower
    private Enemy target;
    private String projectileImagePath;
    private DamageTypes damageType;
    private double projectileSpeed;
    private double projectileExplosionRadius;

    public Tower (double x, double y){
        this.x = x;
        this.y = y;
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

    public ArrayList<Enemy> findTargets(){
        ArrayList<Enemy> all = new ArrayList<>(); //import array of all enemies in game
        ArrayList<Enemy> within = new ArrayList<>();
        for (int i = 0; i < all.size(); i++){
            if (Math.pow((all.get(i).getX() - x),2) + Math.pow((all.get(i).getY() - y),2) < Math.pow((range.getRadius() - x),2)){
                within.add(all.get(i));
            }
        }
        return within;
    }

    abstract void attack();

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

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public String getProjectileImagePath() {
        return projectileImagePath;
    }

    public void setProjectileImagePath(String projectileImagePath) {
        this.projectileImagePath = projectileImagePath;
    }

    public DamageTypes getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageTypes damageType) {
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
}

import java.awt.*;
import java.util.ArrayList;

abstract class Tower {

    Image image;
    double x;
    double y;
    double dmg;
    double rate;
    boolean groundTargeting;
    boolean airTargeting;
    Circle range;
    ArrayList<Enemy> enemies = new ArrayList<>(); // stores all enemies in range of tower

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

    public void findTargets(){
    }

    abstract void attack();

}

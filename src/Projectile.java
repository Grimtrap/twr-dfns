import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Projectile {
    private Tower tower;
    private double x; // x
    private double y; // y
    private double ex; // enemy x
    private double ey; // enemy y
    private String imagePath;
    private byte damageType;
    private Enemy target;
    private double[] slow;
    private double[] burn;
    private double damage;
    private double speed;
    private double explosionRadius;
    private boolean active = true;
    private boolean drawn = false;
    private Rectangle boundingBox;

    private Point objectP = new Point();

    /*public Projectile (String imagePath, byte damageType, double[] slow, double[] burn, double damage, double speed, double explosionRadius, double x, double y, double ex, double ey){
        this.imagePath = imagePath;
        this.damageType = damageType;
        this.slow = slow;
        this.burn = burn;
        this.damage = damage;
        this.speed = speed;
        this.explosionRadius = explosionRadius;
        this.x = x;
        this.y = y;
        this.ex = ex;
        this.ey = ey;

        //Creates action listener updates projectile based on timer
        ActionListener travel = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        };
        Timer t = new Timer (50, travel);
        t.start();

    }*/

    public Projectile (Tower tower, String imagePath, byte damageType, double damage, double speed, double explosionRadius, double x, double y, double ex, double ey, Enemy target){
        this.tower = tower;
        this.imagePath = imagePath;
        this.damageType = damageType;
        this.damage = damage;
        this.speed = speed;
        this.explosionRadius = explosionRadius;
        this.x = x;
        this.y = y;
        this.ex = ex;
        this.ey = ey;
        this.target = target;

        //Creates action listener updates projectile based on timer
        /*ActionListener travel = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        };
        Timer t = new Timer (50, travel);
        t.start()*/

    }

    public void update(double elapsedTime){
        double dy = target.getY() - y;
        double dx = target.getX() - x;
        double distance;

        distance = Math.sqrt(dx * dx + dy * dy);

        x += (int)(dx/distance * speed)*elapsedTime;
        y += (int)(dy/distance * speed)*elapsedTime;
        if(target == null || target.hasReachedEnd() || target.getCurrentHealth() <= 0) {
            this.setActive(false);
        }
        if (this.hit(x,y,this.target.getBoundingBox())){
            target.takeDmg(damage, damageType);
            this.setActive(false);
            // need to stop timer and remove projectile from screen
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval((int)(x), (int)(y), 50, 50); //draw image
    }

    private boolean hit(double x, double y, Rectangle box){
        return x > box.x && x < (box.x + box.width) && y > box.y && y < (box.y + box.height);
    }

    public boolean isDrawn() {
        return drawn;
    }

    public void setDrawn(boolean drawn) {
        this.drawn = drawn;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Tower getTower() {
        return tower;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
    }
}

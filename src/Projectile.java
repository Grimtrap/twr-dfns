import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Projectile extends JComponent {
    double x; // x
    double y; // y
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

    public Projectile (String imagePath, byte damageType, double damage, double speed, double explosionRadius, double x, double y, double ex, double ey, Enemy target){
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
        ActionListener travel = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        };
        Timer t = new Timer (50, travel);
        t.start();

    }

    public void update(){
        objectP = this.getLocation();
        double xDirection =  5 * Math.cos(-(Math.atan2(target.getX() - x, target.getY() - y))+ 90);
        double yDirection = 5 * Math.sin(-(Math.atan2(target.getX() - x, target.getY() - y))+ 90);
        x = (objectP.x + (int)(xDirection * speed));
        y = (objectP.y + (int)(yDirection * speed));
        setLocation((int)(x),(int)(y));
        if (this.hit(x,y,this.target.getBoundingBox())){
            target.takeDmg(damage, damageType);
            this.setActive(false);
            // need to stop timer and remove projectile from screen
        }else {
            repaint();
        }
    }

    public void draw(Graphics g) {
        super.paintComponent(g);
        g.fillOval((int)(x), (int)(y), 5, 5); //draw image
    }

    private boolean hit(double x, double y, Rectangle box){
        return x < box.x && x > (box.x + box.width) && y > box.y && y < (box.y + box.height);
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
}

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
    private DamageTypes damageType;
    private double[] slow;
    private double[] burn;
    private double damage;
    private double speed;
    private double explosionRadius;

    private Point objectP = new Point();

    public Projectile (String imagePath, DamageTypes damageType, double[] slow, double[] burn, double damage, double speed, double explosionRadius, double x, double y, double ex, double ey){
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
        Timer t = new Timer (500, travel);
        t.start();

    }

    public Projectile (String imagePath, DamageTypes damageType, double damage, double speed, double explosionRadius, double x, double y, double ex, double ey){
        this.imagePath = imagePath;
        this.damageType = damageType;
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
        Timer t = new Timer (500, travel);
        t.start();

    }

    public void update(){
        objectP = this.getLocation();
        double xDirection =  5 * Math.cos(-(Math.atan2(ex - x, ey - y))+ 90);
        double yDirection = 5 * Math.sin(-(Math.atan2(ex - x, ey - y))+ 90);
        x = (objectP.x + (int)(xDirection * speed));
        y = (objectP.y + (int)(yDirection * speed));
        setLocation((int)(x),(int)(y));
        repaint();
    }

    public void draw(Graphics g) {
        super.paintComponent(g);
        g.fillOval((int)(x), (int)(y), 50, 50); //draw image
    }
}

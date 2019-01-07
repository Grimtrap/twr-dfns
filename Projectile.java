import javax.swing.*;
import java.awt.*;

public class Projectile extends JComponent {
    double x; // tower x
    double y; // tower y
    double ex; // enemy x
    double ey; // enemy y
    String imagePath;
    String damageTypeAttributes;
    double damage;
    double speed;
    double explosionRadius;

    private Point objectP = new Point();

    public Projectile (String imagePath, String damageTypeAttributes, double damage, double speed, double explosionRadius, double x, double y, double ex, double ey){
        this.imagePath = imagePath;
        this.damageTypeAttributes = damageTypeAttributes;
        this.damage = damage;
        this.speed = speed;
        this.explosionRadius = explosionRadius;
        this.x = x;
        this.y = y;
        this.ex = ex;
        this.ey = ey;
    }

    public void update(){
        objectP = this.getLocation();
        double xDirection =  5* Math.cos(-(Math.atan2(ex - x, ex - y))+ 90);
        double yDirection = 5* Math.sin(-(Math.atan2(ex - x, ex - y))+ 90);
        x = (objectP.x + (int)(xDirection));
        y = (objectP.y + (int)(yDirection));
        setLocation((int)(x),(int)(y));

        repaint();
    }
    public void draw(Graphics g) {
        super.paintComponent(g);
        g.fillOval(0, 0, 50, 50);
    }
}

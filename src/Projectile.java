import sun.awt.image.ImageWatched;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

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
    private BufferedImage image;
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

    public Projectile (Tower tower, String imagePath, byte damageType, double damage, double speed, double explosionRadius, double x, double y, double ex, double ey){
        this.tower = tower;
        this.imagePath = imagePath;
        this.damageType = damageType;
        this.damage = damage;
        this.speed = speed;
        this.explosionRadius = explosionRadius;
        this.x = x;
        this.y = y;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.ex = ex;
        this.ey = ey;

    }

    public Projectile (Tower tower, String imagePath, byte damageType, double damage, double speed, double explosionRadius, double x, double y, double ex, double ey, Enemy target){
        this.tower = tower;
        this.imagePath = imagePath;
        this.damageType = damageType;
        this.damage = damage;
        this.speed = speed;
        this.explosionRadius = explosionRadius;
        this.x = x;
        this.y = y;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.ex = ex;
        this.ey = ey;
        this.target = target;

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
            if(this.explosionRadius <= 0) {
                target.takeDmg(damage, damageType);
                if (burn != null) {
                    target.becomeBurned(burn[0], burn[1]);
                }
                if (slow != null) {
                    target.becomeSlowed(slow[0], slow[1]);
                }
                this.setActive(false);
            } else {
                LinkedList<Enemy> nearby = findWithin(tower.getEnemies());
                for(int i = 0; i < nearby.size(); i++) {
                    nearby.get(i).takeDmg(damage, damageType);
                        if (burn != null) {
                            nearby.get(i).becomeBurned(burn[0], burn[1]);
                        }
                        if (slow != null) {
                            nearby.get(i).becomeSlowed(slow[0], slow[1]);
                        }
                }
                this.setActive(false);
            }
            // need to stop timer and remove projectile from screen
        }
    }

    private LinkedList<Enemy> findWithin(LinkedList<Enemy> enemies) {
        //find enemy within explosion radius
        LinkedList<Enemy> within = new LinkedList<>();
        for(int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i) != null) {
                Enemy current;
                try {
                    current = enemies.get(i);
                } catch (Exception e) {
                    break;
                }
                if (Math.hypot((current.getX() - x), (current.getY() - y)) <= explosionRadius) {
                    if (!current.hasReachedEnd() && !(current.getCurrentHealth() <= 0)) { //extreme paranoid error checking
                        within.add(current);
                    }
                }
            }
        }

        return within;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform backup = g2d.getTransform();
        AffineTransform a = AffineTransform.getRotateInstance(Calculations.getAngle(x,y,target.getX(),target.getY()), x, y);
        g2d.setTransform(a);
        g2d.drawImage(image, (int)x, (int)y, null);
        g2d.setTransform(backup);
    }

    private boolean hit(double x, double y, Rectangle box){
        return x > box.x && x < (box.x + box.width) && y > box.y && y < (box.y + box.height);
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

    public double[] getSlow() {
        return slow;
    }

    public void setSlow(double[] slow) {
        this.slow = slow;
    }

    public double[] getBurn() {
        return burn;
    }

    public void setBurn(double[] burn) {
        this.burn = burn;
    }
}

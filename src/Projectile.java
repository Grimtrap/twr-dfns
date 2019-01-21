//import sun.awt.image.ImageWatched;

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

/**
 * Projectile.java
 * bullets which the towers shoot to deal damage
 * @author Kyle To, edited by Eric Ke
 */
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
    private boolean active;


    /**
     * creates a projectile from the tower
     * @param tower the tower that shot it
     * @param imagePath the image path of the projectile
     * @param damageType what damage it deals
     * @param damage how much damage it deals
     * @param speed how fast it travels
     * @param explosionRadius how large it explodes
     * @param x x coordinate
     * @param y y coordinate
     * @param ex enemy x
     * @param ey enemy y
     * @param target the target to attack
     */
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
        active = true;

    }


    /**
     * updates the projectile, moves it, rotates it, etc.
     * @param elapsedTime time passed since last check
     */
    public void update(double elapsedTime){
        double dy = target.getY() - y;
        double dx = target.getX() - x;
        double distance;

        distance = Math.sqrt(dx * dx + dy * dy);

        x += (int)(dx/distance * speed)*elapsedTime; //gets the x speed by dividing x distance by entire distance to use as a ratio
        y += (int)(dy/distance * speed)*elapsedTime; //same as above but for y speed
        if(target == null || target.hasReachedEnd() || target.getCurrentHealth() <= 0) {
            this.setActive(false); //mark the projectile for removal when it is no longer needed
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

    /**
     * draws the projectile on screen
     * @param g paintComponent graphics
     */
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform backup = g2d.getTransform();
        AffineTransform a = AffineTransform.getRotateInstance(Calculations.getAngle(x,y,target.getX(),target.getY()), x, y); //rotates projectile based on angle
        g2d.setTransform(a);
        g2d.drawImage(image, (int)x, (int)y, null);
        g2d.setTransform(backup);
    }

    private boolean hit(double x, double y, Rectangle box){
        return x > box.x && x < (box.x + box.width) && y > box.y && y < (box.y + box.height);
    }

    /**
     * gives whether a projectile is active on te screen or not
     * @return if the projectile is active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * sets if projectile is active
     * @param active the state to set it to
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * gets the tower that shot the projectile
     * @return the tower that shot the projectile
     */
    public Tower getTower() {
        return tower;
    }

    /**
     * sets the tower that shot the projectile
     * @param tower the tower that shot the projectile
     */
    public void setTower(Tower tower) {
        this.tower = tower;
    }

    /**
     * sets how much the projectile slows
     * @param slow a double array containing duration, then power
     */
    public void setSlow(double[] slow) {
        this.slow = slow;
    }

    /**
     * sets how much the projectile burns
     * @param burn a double array containing duration, then power
     */
    public void setBurn(double[] burn) {
        this.burn = burn;
    }
}

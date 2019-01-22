import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Enemy.java
 * enemy that you have to kill with towers in the game
 * @author Eric Ke, Kyle To
 * Date: December 26, 2018
 * Last Updated: January 19, 2019
 */
public class Enemy implements Cloneable {

    private String imagePath;

    private BufferedImage image;

    private double maxHealth;
    private double currentHealth;
    private double speed;

    private double[] slow;
    private double[] burn;

    private boolean reachedEnd;

    private int goldGranted;
    private Attributes attributes;
    private Queue<Pathing> pathings;
    private Pathing currentPathing;

    private Rectangle boundingBox;

    private double x;
    private double y;
    private double distanceLeft;

    /**
     * creates a new enemy for the game
     * @param imageName name of the image
     * @param maxHealth maximum health it can have
     * @param speed how fast it moves
     * @param goldGranted how much gold granted on death
     * @param attributes its special traits
     * @param pathingQueue the path it should take
     */
    public Enemy(String imageName, double maxHealth, double speed, int goldGranted, Attributes attributes, LinkedList<Pathing> pathingQueue){

        this.imagePath = imageName;

        try {
            image = ImageIO.read(new File("resources/Enemies/" + imagePath + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.reachedEnd = false;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.speed = speed;
        this.goldGranted = goldGranted;
        this.attributes = attributes.clone();
        this.pathings = clonePaths(pathingQueue);
        this.currentPathing = pathings.poll();
        this.distanceLeft = currentPathing.getDistance();
        this.slow = new double[]{0,0}; //duration, then power
        this.burn = new double[]{0,0};
        this.boundingBox = new Rectangle((int)(x), (int)(y),100,100);
    }

    private LinkedList<Pathing> clonePaths(Queue<Pathing> pathingQueue) {
        LinkedList<Pathing> path = new LinkedList<Pathing>();
        for(Pathing p: pathingQueue) {
            path.add(p.clone());
        }
        return path;
    }

    /**
     * clones the enemy and all its attributes and paths, etc.
     * @return a clone of the enemy
     */
    public Enemy clone() {
        try {
            Enemy enemy = (Enemy)super.clone();
            enemy.setSlow(new double[]{0,0});
            enemy.setBurn(new double[]{0,0});
            enemy.setPathings(this.clonePaths(pathings));
            enemy.setAttributes(this.attributes.clone());
            return enemy;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * updates the enemy's state
     * @param timeElapsed time since last check
     * @return whether it has updated or not
     */
    public boolean update(double timeElapsed) {
        if(currentHealth > 0) {
            try {
                move(timeElapsed);
                burnDmg(timeElapsed);
            } catch (NullPointerException e) {
                reachedEnd = true;
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * if the enemy has reached the end
     * @return whether the enemy has reached the end or not
     */
    public boolean hasReachedEnd() {
        return reachedEnd;
    }

    /**
     * sets coordinates of the enemy
     * @param x x coordinate
     * @param y y coordinate
     */
    public void setCoords(double x, double y) {
        this.x = x;
        this.y = y;
    }

    private void burnDmg(double timeElapsed) {
        if(burn[0] > 0) {
            takeDmg(burn[1]*timeElapsed, DamageTypes.EXPLOSIVE);
            burn[0] -= timeElapsed;
        }
    }

    private void move(double timeElapsed) {


        byte direction = currentPathing.getDirection();

        if(slow[0] > 0) {
            if(direction == Directions.NORTH){
                this.y -= (speed*timeElapsed*100)/(slow[1] + 1);
            } else if(direction==Directions.SOUTH) {
                this.y += (speed*timeElapsed*100)/(slow[1] + 1);
            } else if(direction==Directions.EAST) {
                this.x += (speed*timeElapsed*100)/(slow[1] + 1);
            } else if(direction==Directions.WEST) {
                this.x -= (speed*timeElapsed*100)/(slow[1] + 1);
            }

            this.distanceLeft -= (speed*timeElapsed*100)/(slow[1] + 1);

            slow[0] -= timeElapsed;

        } else {
            if (direction == Directions.NORTH) {
                this.y -= speed * timeElapsed * 100;
            } else if (direction == Directions.SOUTH) {
                this.y += speed * timeElapsed * 100;
            } else if (direction == Directions.EAST) {
                this.x += speed * timeElapsed * 100;
            } else if (direction == Directions.WEST) {
                this.x -= speed * timeElapsed * 100;
            }

            this.distanceLeft -= (speed*timeElapsed*100);
        }

        if(this.distanceLeft <= 0) {
            if(direction == 0){
                this.y += distanceLeft;
            } else if(direction==1) {
                this.y -= distanceLeft;
            } else if(direction==2) {
                this.x -= distanceLeft;
            } else if(direction==3) {
                this.x += distanceLeft;
            }
            currentPathing = pathings.poll();
            distanceLeft = currentPathing.getDistance();
        }

        boundingBox.setLocation((int)x,(int)y);
    }

    /**
     * draws the enemy on the screen with proper orientation
     * @param g the graphics
     */
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (currentPathing != null){
            AffineTransform backup = g2d.getTransform();
            AffineTransform a = AffineTransform.getRotateInstance(Math.PI*0.5*currentPathing.getDirection(), x, y);
            g2d.setTransform(a);
            g2d.drawImage(image, (int)x, (int)y, null);
            g2d.setTransform(backup);
         }
            g.setColor(Color.RED);
            g.fillRect((int) this.x, (int) this.y - 20, 60, 5);
            g.setColor(Color.GREEN);
            g.fillRect((int) this.x, (int) this.y - 20, (int) (60 * (currentHealth / maxHealth)), 5);
    }

    /**
     * makes the enemy lose health based on damage and damage type
     * @param damage damage dealt
     * @param dmgType type of damage
     */
    public void takeDmg(double damage, byte dmgType) {
        if (attributes.getShielding() > 0 && dmgType != DamageTypes.PIERCE) {
            attributes.setShielding(attributes.getShielding() - calculateDmgTaken(damage, dmgType));
        } else {
            currentHealth -= calculateDmgTaken(damage, dmgType);
        }
    }


    private double calculateDmgTaken(double dmg, byte dmgType) {
        return dmg* Calculations.calcDmg(attributes.getDmgResist(dmgType));
    }

    /**
     * applies slow
     * @param amt duration of slow
     * @param power power of slow
     */
    public void becomeSlowed(double amt, double power) {
        this.slow[0] = amt* Calculations.calcDmg(attributes.getSlowResist());
        this.slow[1] = power;
    }

    /**
     * applies burn
     * @param amt duration of burn
     * @param power power of burn
     */
    public void becomeBurned(double amt, double power) {
        this.burn[0] = amt* Calculations.calcDmg(attributes.getBurnResist());
        this.burn[1] = power;
    }

    //getters and setters are below

    /**
     * gets current health
     * @return current health
     */
    public double getCurrentHealth() {
        return currentHealth;
    }

    /**
     * gets special attributes
     * @return the enemy's attributes
     */
    public Attributes getAttributes() {
        return attributes;
    }

    /**
     * sets the attributes of the enemy
     * @param attributes the attributes to set it to
     */
    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    /**
     * sets path for the enemy to take
     * @param pathings the path to take
     */
    public void setPathings(LinkedList<Pathing> pathings) {
        this.pathings = pathings;
    }

    /**
     * gets the hitbox of the enemy
     * @return the rectangle which is the hitbox
     */
    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    /**
     * gets x position of the enemy
     * @return the x position
     */
    public double getX() {
        return x;
    }

    /**
     * sets x position of the enemy
     * @param x the x position to set it to
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * gets y position of the enemy
     * @return the y position
     */
    public double getY() {
        return y;
    }

    /**
     * sets y position of the enemy
     * @param y the x position to set it to
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * gets the gold granted
     * @return gold granted by the enemy
     */
    public int getGoldGranted() {
        return goldGranted;
    }

    /**
     * gets the slow
     * @return array containing slow power and duration
     */
    public double[] getSlow() {
        return slow;
    }

    /**
     * sets slow amount
     * @param slow duration and power to set it to
     */
    public void setSlow(double[] slow) {
        this.slow = slow;
    }

    /**
     * gets the burn
     * @return array containing burn power and duration
     */
    public double[] getBurn() {
        return burn;
    }

    /**
     * sets burn amount
     * @param burn duration and power to set it to
     */
    public void setBurn(double[] burn) {
        this.burn = burn;
    }
}

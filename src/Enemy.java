import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Queue;

public class Enemy {

    private String imagePath;

    private BufferedImage image;

    private double maxHealth;
    private double currentHealth;
    private double speed;

    private double[] slow;
    private double[] burn;

    private int goldGranted;
    private Attributes attributes;
    private Queue<Pathing> pathings;
    private Pathing currentPathing;

    private Rectangle boundingBox;

    private double x;
    private double y;
    private double distanceLeft;

    public Enemy(int difficulty) {

    }

    public Enemy(String imagePath, double maxHealth, double speed, int goldGranted, Attributes attributes, Queue<Pathing> pathingQueue) throws IOException {

        this.imagePath = imagePath;
        image = new BufferedImage(75, 75, BufferedImage.TYPE_INT_ARGB);
        ImageIO.write(image, "png", new File("resources/" + imagePath));
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.speed = speed;
        this.goldGranted = goldGranted;
        this.attributes = attributes;
        this.pathings = pathingQueue;
        this.currentPathing = pathings.poll();
        this.slow = new double[]{0,0};
        this.burn = new double[]{0,0};
        this.boundingBox = new Rectangle((int)x, (int)y,50,50); //placeholder values replace later
        tempSetCoordTest();
    }

    private void tempSetCoordTest() {
        this.x = 300;
        this.y = 300;
    }

    public boolean update(double timeElapsed) {
        if(currentHealth > 0) {
            move(timeElapsed);
            burnDmg(timeElapsed);
            return true;
        } else {
            return false;
        }
    }

    public void setCoords(double x, double y) {
        this.x = x;
        this.y = y;
    }

    private void burnDmg(double timeElapsed) {
        if(burn[0] > 0) {
            takeDmg(burn[1]*timeElapsed*100, DamageTypes.EXPLOSIVE);
            burn[0] -= timeElapsed;
        }
    }

    private void move(double timeElapsed) {


        byte direction = currentPathing.getDirection();

        if(slow[0] > 0) {
            if(direction == 0){
                this.y -= (speed*timeElapsed*100)/(slow[1] + 1);
            } else if(direction==1) {
                this.y += (speed*timeElapsed*100)/(slow[1] + 1);
            } else if(direction==2) {
                this.x += (speed*timeElapsed*100)/(slow[1] + 1);
            } else if(direction==3) {
                this.x -= (speed*timeElapsed*100)/(slow[1] + 1);
            }

            this.distanceLeft -= (speed*timeElapsed*100)/(slow[1] + 1);

            slow[0] -= timeElapsed;

        } else {
            if (direction == 0) {
                this.y -= speed * timeElapsed * 100;
            } else if (direction == 1) {
                this.y += speed * timeElapsed * 100;
            } else if (direction == 2) {
                this.x += speed * timeElapsed * 100;
            } else if (direction == 3) {
                this.x -= speed * timeElapsed * 100;
            }

            this.distanceLeft -= (speed*timeElapsed*100);
        }

        if(this.currentPathing.getDistance() <= 0) {
            if(direction == 0){
                this.y -= currentPathing.getDistance();
            } else if(direction==1) {
                this.y += currentPathing.getDistance();
            } else if(direction==2) {
                this.x += currentPathing.getDistance();
            } else if(direction==3) {
                this.x -= currentPathing.getDistance();
            }
            currentPathing = pathings.poll();
            distanceLeft = currentPathing.getDistance();
        }
    }

    public void draw(Graphics g) {
        g.drawImage(image, (int)x, (int)y, null);
    }

    public void takeDmg(double damage, byte dmgType) {
        if (attributes.getShielding() > 0 && dmgType != DamageTypes.PIERCE) {
            attributes.setShielding(attributes.getShielding() - calculateDmgTaken(damage, dmgType));
        } else {
            currentHealth -= calculateDmgTaken(damage, dmgType);
        }
    }

    //fill out this method later
    private double calculateDmgTaken(double dmg, byte dmgType) {
        return dmg* Calculations.calcDmg(attributes.getDmgResist(dmgType));
    }

    public void becomeSlowed(double amt, double power) {
        this.slow[0] = amt* Calculations.calcDmg(attributes.getSlowResist());
        this.slow[1] = power;
    }

    public void becomeBurned(double amt, double power) {
        this.burn[0] = amt* Calculations.calcDmg(attributes.getBurnResist());
        this.slow[1] = power;
    }

    //getters and setters are below

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(double currentHealth) {
        this.currentHealth = currentHealth;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getGoldGranted() {
        return goldGranted;
    }

    public void setGoldGranted(int goldGranted) {
        this.goldGranted = goldGranted;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public Queue<Pathing> getPathings() {
        return pathings;
    }

    public void setPathings(Queue<Pathing> pathings) {
        this.pathings = pathings;
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

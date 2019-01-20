/**
 * Pathing.java
 * tells an enemy which way to walk and for how long
 * @author Eric Ke
 * Date: 2018/12/31
 */
public class Pathing implements Cloneable{

    private byte direction;
    private double distance;

    /**
     * creates a pathing
     * @param distance how far to travel
     * @param direction which way to travel
     */
    public Pathing(double distance, byte direction) {
        this.distance = distance;
        this.direction = direction;
    }

    /**
     * clones pathing
     * @return a clone of the pathing
     */
    public Pathing clone() {
        try {
            return (Pathing)super.clone();
        } catch(CloneNotSupportedException e) {
            System.err.println("Clone not supported");
        }
        return null;
    }

    /**
     * gets the direction
     * @return the direction
     */
    public byte getDirection() {
        return direction;
    }

    /**
     * gets how far the pathing goes
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }
}

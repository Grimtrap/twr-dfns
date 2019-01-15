public class Pathing implements Cloneable{

    private byte direction;
    private double distance;

    public Pathing(double distance, byte direction) {
        this.distance = distance;
        this.direction = direction;
    }

    public Pathing clone() {
        try {
            return (Pathing)super.clone();
        } catch(CloneNotSupportedException e) {
            System.err.println("Clone not supported");
        }
        return null;
    }

    public byte getDirection() {
        return direction;
    }

    public double getDistance() {
        return distance;
    }
}

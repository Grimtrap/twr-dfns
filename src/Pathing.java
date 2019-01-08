public class Pathing {

    private byte direction;
    private double distance;

    public Pathing(double distance, byte direction) {
        this.distance = distance;
        this.direction = direction;
    }

    public byte getDirection() {
        return direction;
    }

    public double getDistance() {
        return distance;
    }
}

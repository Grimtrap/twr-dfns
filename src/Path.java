public class Path {

    private byte direction;
    private double distance;

    public Path(double distance, byte direction) {
        this.distance = distance;
        this.direction = direction;
    }

    public byte getDirection() {
        return direction;
    }
}

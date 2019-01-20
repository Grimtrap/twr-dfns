/**
 * Calculations.java
 * Class that contains methods for easy calculations
 * @author Eric Ke
 * Date: 2018/12/27
 */
public class Calculations {


    /**
     * calculates final damage or status effect duration with a specific formula
     * @param resistValue amount of resistance
     * @return the finished calculation
     */
    public static double calcDmg(double resistValue) {
        if(resistValue >= 0) {
            return 10 / (resistValue + 10);
        } else {
            return -(resistValue/10) + 1;
        }
    }

    /**
     * gives the angle between two points in radians
     * @param x1 x coordinate of first point
     * @param y1 y coordinate of first point
     * @param x2 x coordinate of second point
     * @param y2 y coordinate of second point
     * @return angle formed by two points in radians
     */
    public static double getAngle(double x1, double y1, double x2, double y2) {
        double angle = Math.PI/2 + Math.atan2(y2-y1, x2-x1);

        if(angle < 0) {
            angle += 2*Math.PI;
        }

        return angle;
    }

    /**
     * gets the edge of a coordinate so that something is drawn centered
     * @param n original coordinate
     * @param size size of item
     * @return new coordinate
     */
    public static double Center(double n, double size) {
        return n-size/2;
    }

}

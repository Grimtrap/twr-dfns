/**
 * Box.java
 * Box for the bounding box of towers
 * @author Kyle To
 * Last Updated: January 19 2019
 */

public class Box {
    private double x;
    private double y;
    private double length;
    private double width;

    public Box (double x, double y, double length, double width){
        this.x = x;
        this.y = y;
        this.length = length;
        this.width = width;
    }

    public boolean intersects(double tx, double ty){
        if (x < tx && (x + length) > tx && y < ty && (y + length) > ty){
            return true;
        }else{
            return false;
        }
    }
}

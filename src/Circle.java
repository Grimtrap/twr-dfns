/**
 * Circle.java
 * Circle for the range of towers
 * @author Kyle To
 * Last Updated: January 19 2019
 */
public class Circle {
    private double x;
    private double y;
    private double radius;

    /**
     * creates a new circle
     * @param x x coordinate of circle
     * @param y y coordinate of circle
     * @param radius radius of circle
     */
    public Circle(double x, double y, double radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    /**
     * get the x position of the circle
     * @return the x coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * sets the x position of the circle
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * get the y position of the circle
     * @return the y coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * sets the y position of the circle
     */
    public void setY(double y) {
        this.y = y;
    }


    /**
     * get the radius size of the circle
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }
}

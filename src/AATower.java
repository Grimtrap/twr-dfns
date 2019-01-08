public class AATower extends Tower {

    public AATower(double x, double y) {
        super(x, y);
        this.groundTargeting = false;
        this.airTargeting = true;
        this.dmg = 5;
        this.rate = 5;
        this.range = new Circle(x, y, 5);
    }

    public void attack(){

    }
}
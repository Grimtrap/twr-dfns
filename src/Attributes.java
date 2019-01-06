public class Attributes {

    private double[] dmgResists;
    private double slowResist;
    private double burnResist;
    private double shielding;
    private double regen;
    private boolean flying;

    public Attributes() {
        dmgResists = new double[3];
    }

    public double getDmgResist(int dmgType) {
        return dmgResists[dmgType];
    }

    public double getSlowResist() {
        return slowResist;
    }

    public void setSlowResist(double slowResist) {
        this.slowResist = slowResist;
    }

    public double getBurnResist() {
        return burnResist;
    }

    public void setBurnResist(double burnResist) {
        this.burnResist = burnResist;
    }

    public double getShielding() {
        return shielding;
    }

    public void setShielding(double shielding) {
        this.shielding = shielding;
    }

    public double getRegen() {
        return regen;
    }

    public void setRegen(double regen) {
        this.regen = regen;
    }

    public boolean isFlying() {
        return flying;
    }

    public void setFlying(boolean flying) {
        this.flying = flying;
    }
}

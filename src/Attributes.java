public class Attributes implements Cloneable{

    private double[] dmgResists;
    private double slowResist;
    private double burnResist;
    private double shielding;
    private double regen;
    private boolean flying;

    /**
     * creates a new set of attributes
     */
    public Attributes() {
        dmgResists = new double[]{0,0,0};
        slowResist = 0;
        burnResist = 0;
        shielding = 0;
        regen = 0;
        flying = false;
    }

    /**
     * clones the set of attributes
     * @return
     */
    @Override
    public Attributes clone() {
        try {
            return (Attributes)super.clone();
        } catch(CloneNotSupportedException e) {
            System.err.println("clone not supported");
        }
        return null;
    }

    /**
     * returns damage resist for a specific type of damage
     * @param dmgType the type of dmg
     * @return resistance of damage
     */
    public double getDmgResist(int dmgType) {
        return dmgResists[dmgType];
    }

    /**
     * returns slow resistance
     * @return slow resistance
     */
    public double getSlowResist() {
        return slowResist;
    }

    /**
     * sets amount of slow resist
     * @param slowResist amount to set it to
     */
    public void setSlowResist(double slowResist) {
        this.slowResist = slowResist;
    }

    /**
     * returns burn resistance
     * @return burn resistance
     */
    public double getBurnResist() {
        return burnResist;
    }

    /**
     * sets amount of burn resist
     * @param burnResist amount to set it to
     */
    public void setBurnResist(double burnResist) {
        this.burnResist = burnResist;
    }

    /**
     * gets shielding amount
     * @return amount of shield
     */
    public double getShielding() {
        return shielding;
    }

    /**
     * sets amount of shielding
     * @param shielding amount of shielding to give
     */
    public void setShielding(double shielding) {
        this.shielding = shielding;
    }

    /**
     * sets amount of regenration
     * @param regen amount of regen to give it
     */
    public void setRegen(double regen) {
        this.regen = regen;
    }

    /**
     * gives whether it is flying or not
     * @return whether it flies
     */
    public boolean isFlying() {
        return flying;
    }

    /**
     * sets if it flies
     * @param flying if the current enemy flies
     */
    public void setFlying(boolean flying) {
        this.flying = flying;
    }

    /**
     * sets resistance for damage types
     * @param dmgResists array containing resistance values for each damage type
     */
    public void setDmgResists(double[] dmgResists) {
        this.dmgResists = dmgResists;
    }
}

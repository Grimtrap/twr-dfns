import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShotgunTower extends Tower {

    public ShotgunTower(double x, double y) {
        super(x, y);
        this.groundTargeting = true;
        this.airTargeting = false;
        this.dmg = 5;
        this.rate = 5;
        this.range = new Circle(x, y, 5);
    }

    public void attack(){
        //need to create an array of enemies within its range
        //fires at the enemy closest to base
        //Creates action listener updates projectile based on timer
        ActionListener shoot = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new Projectile();
            }
        };
        Timer t = new Timer ((int)(rate), shoot);
        t.start();
    }
}
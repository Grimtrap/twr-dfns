import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MachineGunTower extends RifleTower {

    public MachineGunTower(double x, double y, Game game) {
        super(x, y, game);
        setGroundTargeting(true);
        setAirTargeting(false);
        setDamage(50);
        setFireRate(0.3);
        setRange(new Circle(x, y, 290));
        setImage(Toolkit.getDefaultToolkit().getImage("resources/Towers/MachineGunBody.png"));
    }

}
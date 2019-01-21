import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TowerStats.java
 * Displays stats of a selected tower
 * @author Kyle To
 * Last Updated: January 19 2019
 */
public class TowerStats extends JFrame implements ActionListener{
        JLabel description;

    /**
     * creates a window to display stats of each tower
     */
    public TowerStats(){
            super("Tower Stats");

            String[] towerStrings = { "Rifle", "Machine Gun", "Shotgun", "Sniper", "Shrapnel", "Missile", "Flamethrower", "Laser", "Cryo", "AA Gun" };

            JComboBox towerList = new JComboBox(towerStrings);
            towerList.setSelectedIndex(0);
            towerList.addActionListener(this);

            description = new JLabel();
            description.setHorizontalAlignment(JLabel.CENTER);
            updateLabel(towerStrings[towerList.getSelectedIndex()]);

            add(towerList, BorderLayout.PAGE_START);
            add(towerList, BorderLayout.PAGE_END);
            setSize(300,200);
            setVisible(true);
        }

    /** Listens to the combo box. */
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String tower = (String)cb.getSelectedItem();
        updateLabel(tower);
    }

    private void updateLabel(String tower) {
        if (tower.equals("Rifle")){
            description.setText("<html>Rifle<br/>Range: 300<br/>Rate of Fire: 1s<br/>Damage: 60<br/>DamageType: Normal<br/>Status Ailments: None<br/>Targets: Ground and Air</html>");
        }else if (tower.equals("Machine Gun")){
            description.setText("<html>Machine Gun<br/>Range: 290<br/>Rate of Fire: 0.15s<br/>Damage: 25<br/>DamageType: Normal<br/>Status Ailments: None<br/>Targets: Ground</html>");
        }else if (tower.equals("Shotgun")){
            description.setText("<html>Shotgun<br/>Range: 200<br/>Rate of Fire: 2s<br/>Damage: 85<br/>DamageType: Normal<br/>Status Ailments: None<br/>Targets: Ground</html>");
        }else if (tower.equals("Sniper")){
            description.setText("<html>Sniper<br/>Range: 600<br/>Rate of Fire: 2s<br/>Damage: 160<br/>DamageType: Pierce<br/>Status Ailments: None<br/>Targets: Ground and Air</html>");
        }else if (tower.equals("Shrapnel")){
            description.setText("<html>Shrapnel<br/>Range: 170<br/>Rate of Fire: 1.2s<br/>Damage: 76<br/>DamageType: Explosive<br/>Status Ailments: None<br/>Targets: Ground</html>");
        }else if (tower.equals("Missile")){
            description.setText("<html>Missile<br/>Range: 300<br/>Rate of Fire: 2.2s<br/>Damage: 105<br/>DamageType: Explosive<br/>Status Ailments: None<br/>Targets: Ground</html>");
        }else if (tower.equals("Flamethrower")){
            description.setText("<html>Flamethrower<br/>Range: 300<br/>Rate of Fire: 0.09s<br/>Damage: 10<br/>DamageType: Explosive<br/>Status Ailments: Burn<br/>Targets: Ground</html>");
        }else if (tower.equals("Laser")){
            description.setText("<html>Laser<br/>Range: 300<br/>Rate of Fire: 0.1s<br/>Damage: 22<br/>DamageType: Pierce<br/>Status Ailments: None<br/>Targets: Ground and Air</html>");
        }else if (tower.equals("Cryo")){
            description.setText("<html>Cryo<br/>Range: 300<br/>Rate of Fire: 0.3s<br/>Damage: 7<br/>DamageType: Normal<br/>Status Ailments: Slow<br/>Targets: Ground and Air</html>");
        }else if (tower.equals("AA Gun")){
            description.setText("<html>AA Gun<br/>Range: 350<br/>Rate of Fire: 0.5s<br/>Damage: 50<br/>DamageType: Normal<br/>Status Ailments: None<br/>Targets: Air</html>");
        }else if (tower.equals("SAM Launcher")){
            description.setText("<html>SAM Launcher<br/>Range: 300<br/>Rate of Fire: 2.2s<br/>Damage: 105<br/>DamageType: Explosive<br/>Status Ailments: None<br/>Targets: Air</html>");
        }
        this.add(description);
        repaint();
    }

}

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
        if (tower == "Rifle"){
            description.setText("<html>Rifle<br/>Range: 400<br/>Rate of Fire: 1s<br/>Damage: 60<br/>DamageType: Normal<br/>Status Ailments: None</html>");
        }else if (tower == "Machine Gun"){
            description.setText("<html>Machine Gun<br/>Range: 290<br/>Rate of Fire: 0.15s<br/>Damage: 25<br/>DamageType: Normal<br/>Status Ailments: None</html>");
        }else if (tower == "Shotgun"){
            description.setText("<html>Shotgun<br/>Range: 200<br/>Rate of Fire: 2s<br/>Damage: 85<br/>DamageType: Normal<br/>Status Ailments: None</html>");
        }else if (tower == "Sniper"){
            description.setText("<html>Sniper<br/>Range: 500<br/>Rate of Fire: 2s<br/>Damage: 160<br/>DamageType: Pierce<br/>Status Ailments: None</html>");
        }else if (tower == "Shrapnel"){
            description.setText("<html>Shrapnel<br/>Range: 170<br/>Rate of Fire: 1.5s<br/>Damage: 76<br/>DamageType: Explosive<br/>Status Ailments: None</html>");
        }else if (tower == "Missile"){
            description.setText("<html>Missile<br/>Range: 400<br/>Rate of Fire: 2.2s<br/>Damage: 150<br/>DamageType: Explosive<br/>Status Ailments: None</html>");
        }else if (tower == "Flamethrower"){
            description.setText("<html>Flamethrower<br/>Range: 300<br/>Rate of Fire: 0.09s<br/>Damage: 10<br/>DamageType: Explosive<br/>Status Ailments: Burn</html>");
        }else if (tower == "Laser"){
            description.setText("<html>Laser<br/>Range: 300<br/>Rate of Fire: 0.1s<br/>Damage: 25<br/>DamageType: Pierce<br/>Status Ailments: None</html>");
        }else if (tower == "Cryo"){
            description.setText("<html>Cryo<br/>Range: 300<br/>Rate of Fire: 0.3s<br/>Damage: 7<br/>DamageType: Normal<br/>Status Ailments: Slow</html>");
        }else if (tower == "AA Gun"){
            description.setText("<html>AA Gun<br/>Range: 350<br/>Rate of Fire: 0.5s<br/>Damage: 50<br/>DamageType: Normal<br/>Status Ailments: None</html>");
        }else if (tower == "SAM Launcher"){
            description.setText("<html>SAM Launcher<br/>Range: 400<br/>Rate of Fire: 2.2s<br/>Damage: 150<br/>DamageType: Explosive<br/>Status Ailments: None</html>");
        }
        this.add(description);
        repaint();
    }

}

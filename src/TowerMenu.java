import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class TowerMenu extends Frame {
    private JButton BasicGun;
    private JButton MachineGun;
    private JButton ShotGun;
    private JButton SniperRifle;
    private JButton MissileLauncher;
    private JButton FlameThrower;
    private JButton LaserGun;
    private JButton CryoGun;
    private JButton ShrapnelTower;
    private JButton AAGun;
    private JButton SAMLauncher;

    public TowerMenu(){
        ImageIcon BasicGunImage = new ImageIcon("resources/Towers/BasicGunBody.png");
        ImageIcon MachineGunImage = new ImageIcon("resources/Towers/MachineGunBody.png");
        ImageIcon ShotGunImage = new ImageIcon("resources/Towers/ShotgunBody.png");
        ImageIcon SniperRifleImage = new ImageIcon("resources/Towers/SniperRifleBody.png");
        ImageIcon MissileLauncherImage = new ImageIcon("resources/Towers/MissileLauncherBody.png");
        ImageIcon FlameThrowerImage = new ImageIcon("resources/Towers/FlamethrowerBody.png");
        ImageIcon LaserGunImage = new ImageIcon("resources/Towers/LaserGunBody.png");
        ImageIcon CryoGunImage = new ImageIcon("resources/Towers/CryoGunBody.png");
        ImageIcon ShrapnelTowerImage = new ImageIcon("resources/Towers/ShrapnelTower.png");
        ImageIcon AAGunImage = new ImageIcon("resources/Towers/AAGunBody.png");
        ImageIcon SAMLauncherImage = new ImageIcon("resources/Towers/SAMMissileBody.png");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        BasicGun = new JButton("Basic Gun", BasicGunImage);
        MachineGun = new JButton("Machine Gun", MachineGunImage);
        ShotGun = new JButton("Shotgun", ShotGunImage);
        SniperRifle = new JButton("Sniper Rifle", SniperRifleImage);
        MissileLauncher = new JButton("Missile Launcher", MissileLauncherImage);
        FlameThrower = new JButton("Flamethrower", FlameThrowerImage);
        LaserGun = new JButton("Laser Gun", LaserGunImage);
        CryoGun = new JButton("Cryo Gun", CryoGunImage);
        ShrapnelTower = new JButton("Shrapnel Tower", ShrapnelTowerImage);
        AAGun = new JButton("AA Gun", AAGunImage);
        SAMLauncher = new JButton("SAM Launcher", SAMLauncherImage);
        add(BasicGun);
        add(MachineGun);
        add(ShotGun);
        add(SniperRifle);
        add(MissileLauncher);
        add(FlameThrower);
        add(LaserGun);
        add(CryoGun);
        add(ShrapnelTower);
        add(AAGun);
        add(SAMLauncher);
        setSize(400,1000);
        setVisible(true);
        // This closing method is temporary
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                dispose();
            }
        });

    }

    public static void main(String[] args) {
        TowerMenu menu = new TowerMenu();
    }
}

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
    private JButton sellButton;

    public TowerMenu(){
        ImageIcon BasicGunImage = new ImageIcon("resources/Towers/BasicGunBody.png");
        ImageIcon MachineGunImage = new ImageIcon("resources/Towers/MachineGunBody.png");
        ImageIcon ShotGunImage = new ImageIcon("resources/Towers/ShotgunBody.png");
        ImageIcon SniperRifleImage = new ImageIcon("resources/Towers/SniperRifleBody.png");
        ImageIcon ShrapnelTowerImage = new ImageIcon("resources/Towers/ShrapnelTower.png");
        ImageIcon MissileLauncherImage = new ImageIcon("resources/Towers/MissileLauncherBody.png");
        ImageIcon FlameThrowerImage = new ImageIcon("resources/Towers/FlamethrowerBody.png");
        ImageIcon LaserGunImage = new ImageIcon("resources/Towers/LaserGunBody.png");
        ImageIcon CryoGunImage = new ImageIcon("resources/Towers/CryoGunBody.png");
        ImageIcon AAGunImage = new ImageIcon("resources/Towers/AAGunBody.png");
        ImageIcon SAMLauncherImage = new ImageIcon("resources/Towers/SAMMissileBody.png");
        ImageIcon GoldImage = new ImageIcon("resources/Gold.png");
        setLayout(new GridLayout(10, 2));

        BasicGun = new JButton("BASIC GUN", BasicGunImage);
        MachineGun = new JButton("MACHINE GUN", MachineGunImage);
        ShotGun = new JButton("SHOTGUN", ShotGunImage);
        SniperRifle = new JButton("SNIPER RIFLE", SniperRifleImage);
        ShrapnelTower = new JButton("SHRAPNEL TOWER", ShrapnelTowerImage);
        MissileLauncher = new JButton("MISSILE LAUNCHER", MissileLauncherImage);
        FlameThrower = new JButton("FLAMETHROWER", FlameThrowerImage);
        LaserGun = new JButton("LASER GUN", LaserGunImage);
        CryoGun = new JButton("CRYO GUN", CryoGunImage);
        AAGun = new JButton("AA GUN", AAGunImage);
        SAMLauncher = new JButton("SAM LAUNCHER", SAMLauncherImage);
        sellButton = new JButton("SELL", GoldImage);
        add(BasicGun);
        add(MachineGun);
        add(ShotGun);
        add(SniperRifle);
        add(ShrapnelTower);
        add(MissileLauncher);
        add(FlameThrower);
        add(LaserGun);
        add(CryoGun);
        add(AAGun);
        add(SAMLauncher);
        add(sellButton);
        setSize(500,1080);
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

    //public static void main(String[] args) {
        //TowerMenu menu = new TowerMenu();
    //}
}

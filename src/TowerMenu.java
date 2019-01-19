import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * TowerMenu.java
 * does menu things
 * @author Michael T., Kyle T.
 * Last Updated: January 19, 2019
 */
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
    private Game game;

    public TowerMenu(Game game){
        this.game = game;
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
        BasicGun.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.setSelected(new RifleTower(0,0,game));
        }
        } );
        MachineGun = new JButton("MACHINE GUN", MachineGunImage);
        MachineGun.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.setSelected(new MachineGunTower(0,0,game));
            }
        } );
        ShotGun = new JButton("SHOTGUN", ShotGunImage);
        ShotGun.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.setSelected(new ShotgunTower(0,0,game));
            }
        } );
        SniperRifle = new JButton("SNIPER RIFLE", SniperRifleImage);
        SniperRifle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.setSelected(new SniperTower(0,0,game));
            }
        } );
        ShrapnelTower = new JButton("SHRAPNEL TOWER", ShrapnelTowerImage);
        ShrapnelTower.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.setSelected(new ShrapnelTower(0,0,game));
            }
        } );
        MissileLauncher = new JButton("MISSILE LAUNCHER", MissileLauncherImage);
        MissileLauncher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.setSelected(new RocketLauncherTower(0,0,game));
            }
        } );
        FlameThrower = new JButton("FLAMETHROWER", FlameThrowerImage);
        FlameThrower.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.setSelected(new FlamethrowerTower(0,0,game));
            }
        } );
        LaserGun = new JButton("LASER GUN", LaserGunImage);
        LaserGun.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.setSelected(new LaserTower(0,0,game));
            }
        } );
        CryoGun = new JButton("CRYO GUN", CryoGunImage);
        CryoGun.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.setSelected(new CryoGunTower(0,0,game));
            }
        } );
        AAGun = new JButton("AA GUN", AAGunImage);
        AAGun.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.setSelected(new AATower(0,0,game));
            }
        } );
        /*SAMLauncher = new JButton("SAM LAUNCHER", SAMLauncherImage);
        SAMLauncher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.setSelected(new RifleTower(0,0,game));
            }
        } );*/
        sellButton = new JButton("SELL", GoldImage);
        sellButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //insert code here
            }
        } );
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
        //add(SAMLauncher);
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
}

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class TowerMenu extends Frame {
    private Button BasicGun;
    private Button MachineGun;
    private Button ShotGun;
    private Button SniperRifle;
    private Button MissileLauncher;
    private Button FlameThrower;
    private Button LaserGun;
    private Button CryoGun;
    private Button ShrapnelTower;
    private Button AAGun;
    private Button SAMLauncher;

    public TowerMenu(){
        File BasicGunFile = new File("BasicGunBody.png");
        try{
            BufferedImage BasicGunImage = ImageIO.read(getClass().getResource("resources/lati.png"));
            //BufferedImage BasicGunImage = ImageIO.read(BasicGunFile);
        }catch (Exception ex){
            System.out.println("Can't find file");
        }
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        BasicGun = new Button("Basic Gun");
        MachineGun = new Button("Machine Gun");
        ShotGun = new Button("Shotgun");
        SniperRifle = new Button("Sniper Rifle");
        MissileLauncher = new Button("Missile Launcher");
        FlameThrower = new Button("Flamethrower");
        LaserGun = new Button("Laser Gun");
        CryoGun = new Button("Cryo Gun");
        ShrapnelTower = new Button("Shrapnel Tower");
        AAGun = new Button("AA Gun");
        SAMLauncher = new Button("SAM Launcher");
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

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

public class MainMenu extends JFrame {

    private BufferedImage title;
    private JLabel titleLabel;
    private JTextField inputBox;
    private JButton startButton;
    private JButton instructions;
    private JFrame thisFrame;

    public MainMenu() {
        thisFrame = this;
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(1,2));

        try {
            title = ImageIO.read(new File("resources/menutitle.png"));
        } catch(IOException e1) {
            System.out.println("Title not found");
        }

        startButton = new JButton("Start!");
        instructions = new JButton("Instructions");

        inputBox = new JTextField();
        titleLabel = new JLabel();
        titleLabel.setIcon(new ImageIcon(title));

        startButton.addActionListener(e -> {
            try {
                new Game(inputBox.getText());
                dispose();
            } catch(Exception e1) {
                JOptionPane.showMessageDialog(thisFrame,"File doesn't exist");
            }

        });

        instructions.addActionListener(e -> JOptionPane.showMessageDialog(thisFrame, "" +
                "Welcome to Tower Defense" +
                "\n\nPlace Towers by clicking on the menu, then on the map" +
                "\nEach tower has unique properties" +
                "\nTowers cost gold, which you get by killing enemies" +
                "\n\nEnemies have randomized health/speed, and gain access to special traits after wave 10" +
                "\n\nList of special traits:" +
                "\n- Regeneration" +
                "\n- Shielding" +
                "\n- Flying" +
                "\n\n In addition to that, each tower has a specific damage type:" +
                        "\nNormal, Pierce, and Explosive" +
                "\nEnemies may also resist or be weak to each damage type" +
                        "\nYou lose lives if the enemy reaches the end. You lose if you lose all lives" +
                        "\n\nGood luck"
                ));

        this.add(titleLabel, BorderLayout.NORTH);
        this.add(inputBox, BorderLayout.CENTER);
        this.add(new JLabel("Map Name:"), BorderLayout.WEST);
        buttonPanel.add(startButton);
        buttonPanel.add(instructions);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setSize(500,410);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        SoundPlayer.playSound("MenuMusic.wav");

    }

}

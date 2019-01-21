import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * MainMenu.java
 * The main menu of the game
 * @author Eric Ke, Kyle To, Michael T.
 * Date: 2019/1/20
 */
public class MainMenu extends JFrame {

    private BufferedImage title;
    private JLabel titleLabel;
    private JTextField inputBox;
    private JButton startButton;
    private JButton instructions;
    private JFrame thisFrame;

    /**
     * makes a main menu for users to start the game with
     */
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

        //start button
        startButton.addActionListener(e -> {
            if (new File("maps/" + inputBox.getText() + ".txt").exists()) {
                new Game(inputBox.getText());
                dispose();
            } else {
                JOptionPane.showMessageDialog(thisFrame, "File doesn't exist");
            }
        });

        //instruction button creates this pane
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
                "\n\nGood luck" +
                "\n\nTo create your own map: " +
                "\nCreate a text file inside the maps folder" +
                "\nThe first line are the x and y coordinate, separated by a space" +
                "\nThe next lines are the cardinal direction, followed by the distance to go through" +
                "\n\nExample:" +
                "\n\n0 500" +
                "\ns 500" +
                "\ne 200" +
                "\nn 200" +
                "\nw 100"
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
        this.pack();
        SoundPlayer.playMusic();

    }

}

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainMenu extends JFrame {

    private BufferedImage title;
    private JLabel titleLabel;
    private JTextField inputBox;
    private JButton startButton;
    private JFrame thisFrame;

    public MainMenu() {
        thisFrame = this;
        setLayout(new BorderLayout());
        try {
            title = ImageIO.read(new File("resources/menutitle.png"));
        } catch(IOException e1) {
            System.out.println("Title not found");
        }


        startButton = new JButton("Start!");
        inputBox = new JTextField();
        titleLabel = new JLabel();
        titleLabel.setIcon(new ImageIcon(title));

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Game(inputBox.getText());
                } catch(Exception e1) {
                    JOptionPane.showMessageDialog(thisFrame,"File doesn't exist");
                }

            }
        });

        this.add(titleLabel, BorderLayout.NORTH);
        this.add(inputBox, BorderLayout.CENTER);
        this.add(new JLabel("Map Name:"), BorderLayout.WEST);
        this.add(startButton, BorderLayout.SOUTH);

        this.setSize(500,410);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * EndScreen.java
 * Screen for when game ends
 * @author Kyle To, Eric Ke, Michael T.
 * Last Updated: January 19 2019
 */

public class EndScreen extends Frame {
    private Game game;
    private JLabel label;
    private JButton button;
    private String soundName;

    public EndScreen(Game game) {
        this.game = game;
        setLayout(new GridLayout(2, 1));
        label = new JLabel("Game Over",SwingConstants.CENTER);
        button = new JButton("Return to Main Menu");
        SoundPlayer.playSound("GameOver.wav");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MainMenu();
                setVisible(false);
                dispose();
            }
        } );

        add(label);
        add(button);

        setSize(500,500);
        setVisible(true);
    }
}

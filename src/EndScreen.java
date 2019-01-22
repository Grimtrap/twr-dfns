import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * EndScreen.java
 * Screen for when game ends
 * @author Kyle To, Eric Ke
 * Last Updated: January 19 2019
 */

public class EndScreen extends Frame {
    private Game game;
    private JLabel label;
    private JButton button;

    /**
     * creates a game over screen
     * @param game the game itself
     */
    public EndScreen(Game game) {
        this.game = game;
        setLayout(new GridLayout(2, 1));

        label = new JLabel("Game Over",SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 48));
        label.setForeground(Color.RED);
        button = new JButton("Return to Main Menu");
        SoundPlayer.playSound("GameOver.wav");
        button.addActionListener(e -> {
            new MainMenu();
            setVisible(false);
            dispose();
        });

        add(label);
        add(button);

        setSize(500,500);
        setVisible(true);
    }
}

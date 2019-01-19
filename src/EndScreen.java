import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EndScreen extends Frame {
    private Game game;
    private JLabel label;
    private JButton button;

    public EndScreen(Game game) {
        this.game = game;
        setLayout(new GridLayout(2, 1));

        label = new JLabel("Game Over",SwingConstants.CENTER);
        button = new JButton("Return to Main Menu");
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

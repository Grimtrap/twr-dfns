import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class GameFrame extends JFrame {

    private LinkedList<Enemy> enemies;
    private LinkedList<Tower> towers;

    public GameFrame(LinkedList<Enemy> enemies, LinkedList<Tower> towers){
        super("Tower Defense lmao");

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.enemies = enemies;
        this.towers = towers;
        this.setSize(1920,1080);
        this.add(new GamePanel());
        this.setVisible(true);


    }

    private class GamePanel extends JPanel {
        private Clock clock;
        private double duration;

        public GamePanel() {
            clock = new Clock();
            duration = 0.5;
        }



        protected void paintComponent(Graphics g) {

            super.paintComponent(g);
            setDoubleBuffered(true);
            g.drawRect(0,400,500,500);

            for(Enemy e: enemies) {
                e.draw(g);
            }

            /*
            for(Tower twr: towers) {
                twr.draw(g)
            }
            */

            repaint();
        }
    }


}

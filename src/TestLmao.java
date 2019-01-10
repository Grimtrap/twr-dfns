import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class TestLmao extends JFrame {

    private Map map;

    public TestLmao(String mapName) throws IOException {
        super("Twr Dfns lmao");

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.map = new Map(mapName);
        this.setSize(1920,1080);
        this.add(new GamePanel());
        this.setVisible(true);


    }

    private class GamePanel extends JPanel {
        private Clock clock;
        private LinkedList<Enemy> enemies = new LinkedList<Enemy>();

        public GamePanel() throws IOException {
            clock = new Clock();
            Enemy a = new Enemy("lati.png", 2,2,2, new Attributes(), map.getPathings());
            a.setCoords(0,500);
            enemies.add(a);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawRect(0,0,500,500);
            setDoubleBuffered(true);
            clock.update();

            for(Enemy e: enemies) {
                e.update(clock.getElapsedTime());
                e.draw(g);
            }

            repaint();
        }
    }


}

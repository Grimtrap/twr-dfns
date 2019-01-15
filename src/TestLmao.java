import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class TestLmao extends JFrame {

    private Map map;
    private String mapName;

    public TestLmao(String mapName) throws IOException {
        super("Tower Defense lmao");

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.mapName = mapName;
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


        }

        public void paintComponent(Graphics g) {

            double duration = 200;
            EnemySpawner spawner = new EnemySpawner(mapName);
            super.paintComponent(g);
            g.drawRect(0,0,500,500);
            setDoubleBuffered(true);
            clock.update();

            duration -= clock.getElapsedTime();

            if(duration <= 0) {
                duration = 200;
            }

            for(Enemy e: enemies) {
                e.update(clock.getElapsedTime());
                e.draw(g);
            }

            repaint();
        }
    }


}

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
        private EnemySpawner spawner;
        private int currentWave;


        public GamePanel() throws IOException {
            clock = new Clock();

            spawner = new EnemySpawner(mapName);
            spawner.generateWave(0);
            enemies.add(spawner.getNextEnemy(0));
        }

        public void paintComponent(Graphics g) {

            double duration = 5;
            super.paintComponent(g);
            g.drawRect(0,0,500,500);
            setDoubleBuffered(true);
            clock.update();

            duration -= clock.getElapsedTime();

            if(duration <= 0 && spawner.canSpawnMore()) {
                duration = 5;
            }

            for(Enemy e: enemies) {
                e.update(clock.getElapsedTime());
                e.draw(g);
            }

            repaint();
        }
    }


}

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
        private double duration;
        private LinkedList<Enemy> enemies = new LinkedList<Enemy>();
        private EnemySpawner spawner;
        private int currentWave;
        private LinkedList<Tower> towers = new LinkedList<>();


        public GamePanel() {
            clock = new Clock();
            duration = 0;
            spawner = new EnemySpawner(mapName);
            spawner.generateWave(0);
            towers.add(new AATower(0,0));
            towers.add(new RifleTower(100,0));
            towers.add(new MachineGunTower(500,0));
        }

        public void paintComponent(Graphics g) {

            super.paintComponent(g);
            setDoubleBuffered(true);
            clock.update();

            duration -= clock.getElapsedTime();

            for(Tower t: towers){
                t.setEnemies(enemies);
                t.draw(g);
                //t.attack();
            }

            if(duration <= 0 && spawner.canSpawnMore()) {
                spawnEnemy();
                duration = 0.5;
            }

            for(Enemy e: enemies) {
                e.update(clock.getElapsedTime());
                e.draw(g);
            }

            repaint();
        }

        public void spawnEnemy() {
            enemies.add(spawner.getNextEnemy(0));
        }
    }


}

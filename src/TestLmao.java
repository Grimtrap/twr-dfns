import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class TestLmao extends JFrame {

    private Map map;
    private String mapName;
    private LinkedList<Enemy> enemies;

    public TestLmao(String mapName, LinkedList<Enemy> enemies){
        super("Tower Defense lmao");

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.mapName = mapName;
        this.map = new Map(mapName);
        this.enemies = enemies;
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
            clock.update();

            duration -= clock.getElapsedTime();

            g.drawRect(0,400,500,500);


            for(Enemy e: enemies) {
                e.draw(g);
            }

            repaint();
        }
    }


}

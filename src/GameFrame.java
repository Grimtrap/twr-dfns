import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

/**
 * GameFrame.java
 * JFrame where the game is drawn
 * @author Eric Ke, Kyle To
 * Last Updated: January 19 2019
 */

public class GameFrame extends JFrame {

    private LinkedList<Enemy> enemies;
    private LinkedList<Tower> towers;
    private Map map;
    private double[][] pathCoords;
    private Game game;
    JButton startWave;
    GamePanel gamePanel;

    public GameFrame(LinkedList<Enemy> enemies, LinkedList<Tower> towers, Map map, Game game){
        super("Tower Defense");

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.map = map;
        this.enemies = enemies;
        this.towers = towers;
        this.game = game;

        startWave = new JButton("Start Wave");
        startWave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.getSpawner().generateWave(0);
            }
        } );

        this.setSize(1920,1080);
        gamePanel = new GamePanel();
        gamePanel.add(startWave);
        this.add(gamePanel);
        coordsForPathing();
        this.setVisible(true);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                if (game.getSelected() != null){
                    int x = me.getX();
                    int y = me.getY();
                    game.getSelected().setX(x);
                    game.getSelected().setY(y);
                    if(game.getGold() >= game.getSelected().getCost()) {
                        towers.add(game.getSelected());
                        TowersThread t = new TowersThread(towers, enemies);
                        t.start();
                        game.getTowerThread().interrupt();
                        game.setTowerThread(t);
                        game.spendGold(game.getSelected().getCost());
                        game.setSelected(null);
                    }else{
                        System.out.println("Not enough Gold");
                    }
                }else if(game.isSelling()){
                    //System.out.println("selling");
                    int x = me.getX();
                    int y = me.getY();
                    for(int i = 0; i < towers.size(); i++) {
                        if (towers.get(i).getBox().intersects(x,y)) {
                            game.gainGold(towers.get(i).getCost());
                            towers.remove(towers.get(i));
                            TowersThread t = new TowersThread(towers, enemies);
                            t.start();
                            game.getTowerThread().interrupt();
                            game.setTowerThread(t);
                            game.setSellSelected(null);
                            game.setSelling(false);
                        }
                    }
                }
            }
        });

    }

    private void coordsForPathing() {
        //gets coordinates so that the path can be drawn on later
        LinkedList<Pathing> pathings = map.getPathings();
        pathCoords = new double[pathings.size()+1][2];
        double[] start = map.getStart();
        pathCoords[0][0] = 0+start[0];
        pathCoords[0][1] = 0+start[1];
        for(int i = 0; i < pathCoords.length-1; i++) {
            Pathing current = pathings.get(i);
            if(current.getDirection() == Directions.EAST) {
                pathCoords[i+1][0] = pathCoords[i][0] + current.getDistance();
                pathCoords[i+1][1] = pathCoords[i][1];
            } else if (current.getDirection() == Directions.WEST) {
                pathCoords[i+1][0] = pathCoords[i][0] - current.getDistance();
                pathCoords[i+1][1] = pathCoords[i][1];
            } else if (current.getDirection() == Directions.NORTH) {
                pathCoords[i+1][0] = pathCoords[i][0];
                pathCoords[i+1][1] = pathCoords[i][1] - current.getDistance();

            } else if (current.getDirection() == Directions.SOUTH) {
                pathCoords[i+1][0] = pathCoords[i][0];
                pathCoords[i+1][1] = pathCoords[i][1] + current.getDistance();
            }
        }
    }

    private class GamePanel extends JPanel {
        private Clock clock;
        private double duration;

        public GamePanel() {
            clock = new Clock();
            duration = 0.5;
        }


        protected synchronized void paintComponent(Graphics g) {

            super.paintComponent(g);
            setDoubleBuffered(true);
            g.drawRect(0,400,500,500);
            g.drawString("Gold: " + Integer.toString((int)(game.getGold())) + " Lives: " + Integer.toString((int)(game.getLivesLeft())), 5,15);

            drawPath(g);

            synchronized (enemies) { //don't use foreach here, it causes weird exception
                for(int i = 0; i<enemies.size(); i++) {
                    enemies.get(i).draw(g);
                }
            }

            synchronized (towers){
                for(int i = 0; i<towers.size(); i++) {
                    towers.get(i).draw(g);
                    if(!towers.get(i).getProjectiles().isEmpty()) {
                        for (int j = 0; j < towers.get(i).getProjectiles().size(); j++) {
                            towers.get(i).getProjectiles().get(j).draw(g);
                        }
                    }
                }
            }

            if(game.getLivesLeft() == 0) {
                new EndScreen(game);
                setVisible(false);
                dispose();
                game.getTowerMenu().setVisible(false);
                game.getTowerMenu().dispose();
            }

            repaint();
        }

        private void drawPath(Graphics g) {
            g.setColor(Color.DARK_GRAY);
            for(int i=0; i<pathCoords.length-1; i++) {
                LinkedList<Pathing> pathings = map.getPathings();
                if(pathings.get(i).getDirection() == Directions.EAST || pathings.get(i).getDirection() == Directions.WEST) {
                    g.fillRect((int)pathCoords[i][0], (int)pathCoords[i][1], (int)(pathCoords[i+1][0]-pathCoords[i][0]), 100);
                } else {
                    g.fillRect((int)pathCoords[i][0], (int)pathCoords[i][1], 100, (int)(pathCoords[i+1][1] - pathCoords[i][1]));
                }
            }
        }
    }

}

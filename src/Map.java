import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Map
 * Has a path for the enemies to follow
 */
public class Map {

    private LinkedList<Pathing> pathings = new LinkedList<Pathing>();
    private double[] start;
    private String mapName;
    private File mapFile;


    /**
     * makes a map for the game
     * @param mapName name of the file/map without extension
     */
    public Map(String mapName) {
        this.mapName = mapName;
        mapFile = new File("maps/" + mapName + ".txt");
        start = new double[2];
        construct();
    }

    /**
     * Constructs a map from the text file
     */
    private void construct() {
        Scanner in;
        try {
            in = new Scanner(mapFile);

            String[] rawCoords = in.nextLine().split(" ");
            start[0] = Double.parseDouble(rawCoords[0]); //numbers of the first line are coords
            start[1] = Double.parseDouble(rawCoords[1]);
            while (in.hasNextLine()) {
                String[] current = in.nextLine().split(" ");
                System.out.println(current[0] + " " + current[1]);
                pathings.add(new Pathing(Double.parseDouble(current[1]), toDirection(current[0].charAt(0))));
            }
        } catch(FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    private byte toDirection(char direction) {
        if(direction == 'n') {
            return Directions.NORTH;
        } else if(direction == 's') {
            return Directions.SOUTH;
        } else if(direction == 'e') {
            return Directions.EAST;
        } else if(direction == 'w') {
            return Directions.WEST;
        }
        return -1;
    }

    /**
     * gets the pathings that the map has for enemies to walk on
     * @return the path the enemies must walk along
     */
    public LinkedList<Pathing> getPathings() {
        LinkedList<Pathing> path = new LinkedList<Pathing>();
        for(Pathing p: pathings) {
            path.add(p.clone());
        }
        return path;
    }

    /**
     * gets the start of the map
     * @return x and y coordinate of the start
     */
    public double[] getStart() {
        return start;
    }
}

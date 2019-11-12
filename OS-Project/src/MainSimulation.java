import javax.swing.*;
import java.util.*;

public class MainSimulation{
    public static final int WIN_WIDTH = 1280;
    public static final int WIN_HEIGHT = 720;
//    private final ArrayList<Screen> screens;

    private JFrame window;
    private MainSimulation2 sortArray;

    public MainSimulation() {
//        screens = new ArrayList<Screen>();
        window = new JFrame("Simulation");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        window.setSize(1280, 720);


        sortArray = new MainSimulation2();
        window.add(sortArray);
        window.pack();
        sortArray.repaint();
        window.setVisible(true);
    }
}



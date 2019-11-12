import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MainSimulation2 extends JPanel {
    public static final int WIN_WIDTH = 1280;
    public static final int WIN_HEIGHT = 720;
    private static final int BAR_WIDTH=8;
    private static final int NUM_BARS=MainSimulation.WIN_WIDTH/BAR_WIDTH;
    private int[] array= new int[1000];

    public MainSimulation2(){
        for(int i=0;i<NUM_BARS;i++){
            array[i]=i;
        }
        shuffleArray();
//        setBackground(Color.darkGray);
        new Bubble().runSort(array);
    }

    private void shuffleArray(){
        Random rng =new Random();
        for(int i=0;i<NUM_BARS;i++){
            int swapWithIndex = rng.nextInt(NUM_BARS-1);
            int temp=array[i];
            array[i]=array[swapWithIndex];
            array[swapWithIndex]=temp;
        }
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D graphics = (Graphics2D)g;
        super.paintComponent(graphics);

        graphics.setColor(Color.BLACK);
        for(int i=0;i<NUM_BARS;i++){
            int height = array[i]*2;
            int xBegin = i+(BAR_WIDTH-i)*i;
            int yBegin = WIN_HEIGHT-height;
            graphics.fillRect(i+BAR_WIDTH*i,0,BAR_WIDTH,array[i]);
        }

//        graphics.setColor(Color.red);
//        graphics.drawRect(0,0,WIN_WIDTH,WIN_HEIGHT);
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(WIN_WIDTH,WIN_HEIGHT);
    }

}

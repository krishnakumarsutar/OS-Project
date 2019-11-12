import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.lang.*;

public class SortApp extends JFrame{

    private JTextField Elements;
    private JPanel MainFrame;
    private JLabel EleLabel;
    private JLabel SortName;
    private JComboBox SorOptions;
    private JButton SorButton;
    private JScrollPane SimTex;
    private JTextArea Simulation;
    private JButton file;
    private JButton OpenSortFile;
    private PrintStream standardOut;
    private Integer met=11,ele=0;
    private ArrayList<Integer> arr = new ArrayList<Integer>();
    private String FileAddr="";
    private String data="";
    private String FileDir="";
    private String FileName="";
    public static int blockWidth;
    public static int sleep = 1000;
    public static int sortDataCount;
    public static ArrayList<Integer> toBeSorted;
    public static VisualizerFrame frame;

    static int atoi(String str) {
        if (str == null || str.length() < 1)
            return 0;


        str = str.trim();

        char flag = '+';


        int i = 0;
        if (str.charAt(0) == '-') {
            flag = '-';
            i++;
        } else if (str.charAt(0) == '+') {
            i++;
        }

        double result = 0;


        while (str.length() > i && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            result = result * 10 + (str.charAt(i) - '0');
            i++;
        }

        if (flag == '-')
            result = -result;

        return (int) result;
    }

    public void write (ArrayList<Integer> ar) throws IOException{
        BufferedWriter outputWriter = null;
        outputWriter = new BufferedWriter(new FileWriter(FileDir+"/Sorted_"+FileName));
        for (int i = 0; i < ar.size(); i++) {
            outputWriter.write(Integer.toString(ar.get(i)));
            outputWriter.write(" ");
        }
        outputWriter.flush();
        outputWriter.close();
    }

    public void open() throws IOException {
        //text file, should be opening in default text editor
        File file = new File(FileDir+"/Sorted_"+FileName);

        //first check if Desktop is supported by Platform or not
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }

        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) desktop.open(file);

//        //let's try to open PDF file
//        file = new File("/Users/pankaj/java.pdf");
//        if(file.exists()) desktop.open(file);
    }

    public SortApp() {



            SorOptions.addItem(new combo("   MergeSort",11));
            SorOptions.addItem(new combo("   QuickSort",22));
            SorOptions.addItem(new combo("   BrickSort",33));
            SorOptions.addItem(new combo("   BitonicSort",44));
            SorOptions.addItem(new combo("   EnumerationSort",55));
            SorOptions.addItem(new combo("   TimSort",66));
            SorOptions.addItem(new combo("   ExternalSort",77));

            OpenSortFile.setEnabled(false);


        SorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
//                MainSimulation sim = new MainSimulation();
//                sim.MainSimulation();

                frame = new VisualizerFrame();

                if(ele==0){
                Simulation.setText(null);
                String elep = Elements.getText().toString();
                String[] eleSpl = elep.split(" ");
//                for(int i=0;i<eleSpl.length;i++)
//                System.out.println(eleSpl.length);
                toBeSorted = new ArrayList<Integer>();
                sortDataCount=eleSpl.length;
                blockWidth=(int)Math.max(Math.floor(500/eleSpl.length), 1);
                for(int i = 0; i<eleSpl.length; i++){
                    toBeSorted.add(atoi(eleSpl[i]));
                    }
                    try{
                        frame.preDrawArray(toBeSorted);
                    }
                    catch (Exception e){
                        System.out.println("error");
                    }


                switch (met){
                    case 11:
                        MergeSort sor2 = new MergeSort(Simulation,eleSpl,frame,arr);
                        arr.clear();
                        break;

                    case 22:
                        QuickSort sor1 = new QuickSort(eleSpl,Simulation,arr);
                        arr.clear();
                        break;

                    case 33:
                        BrickSort sor3 = new BrickSort(eleSpl,Simulation,arr,frame);
                        arr.clear();
                        break;

                    case 44:
                        BitonicSort sor4 = new BitonicSort(Simulation,eleSpl,arr);
                        arr.clear();
                        break;

                    case 55:
                        EnumerationSort sor5 = new EnumerationSort(Simulation,eleSpl,arr);
                        arr.clear();
                        break;

                    case 66:
                        TimSort sor6 = new TimSort(Simulation,arr,eleSpl);
                        arr.clear();
                        break;

                    case 77:
                        ExternalSort1 sor7 = new ExternalSort1(Simulation,FileAddr,FileDir);
                        break;
                }}
                else{
                    Simulation.setText(null);
                    String[] eleSpl = data.split(" ");
                    toBeSorted = new ArrayList<Integer>();
                    sortDataCount=eleSpl.length;
                    blockWidth=(int)Math.max(Math.floor(500/eleSpl.length), 1);
                    for(int i = 0; i<eleSpl.length; i++)
                        toBeSorted.add(atoi(eleSpl[i]));
                    try{
                        frame.preDrawArray(toBeSorted);
                    }
                    catch (Exception e){
                        System.out.println("error");
                    }


                    switch (met){
                        case 11:
                            MergeSort sor2 = new MergeSort(Simulation,eleSpl,frame,arr);
                            System.out.println(arr);
                            try{
                                write(arr);

                            }
                            catch (Exception e){
                                System.out.println("Couldn't read file");
                            }
                            arr.clear();
                            ele=0;
                            OpenSortFile.setEnabled(true);
                            break;

                        case 22:
                            QuickSort sor1 = new QuickSort(eleSpl,Simulation,arr);
                            try{
                                write(arr);

                            }
                            catch (Exception e){
                                System.out.println("Couldn't read file");
                            }
                            arr.clear();
                            ele=0;
                            OpenSortFile.setEnabled(true);
                            break;

                        case 33:
                            BrickSort sor3 = new BrickSort(eleSpl,Simulation,arr,frame);
                            try{
                                write(arr);

                            }
                            catch (Exception e){
                                System.out.println("Couldn't read file");
                            }
                            arr.clear();
                            ele=0;
                            OpenSortFile.setEnabled(true);
                            break;

                        case 44:
                            BitonicSort sor4 = new BitonicSort(Simulation,eleSpl,arr);
                            try{
                                write(arr);

                            }
                            catch (Exception e){
                                System.out.println("Couldn't read file");
                            }
                            arr.clear();
                            ele=0;
                            OpenSortFile.setEnabled(true);
                            break;

                        case 55:
                            EnumerationSort sor5 = new EnumerationSort(Simulation,eleSpl,arr);
                            try{
                                write(arr);

                            }
                            catch (Exception e){
                                System.out.println("Couldn't read file");
                            }
                            arr.clear();
                            ele=0;
                            OpenSortFile.setEnabled(true);
                            break;

                        case 66:
                            TimSort sor6 = new TimSort(Simulation,arr,eleSpl);
                            try{
                                write(arr);

                            }
                            catch (Exception e){
                                System.out.println("Couldn't read file");
                            }
                            arr.clear();
                            ele=0;
                            OpenSortFile.setEnabled(true);
                            break;

                        case 77:
                            ExternalSort1 sor7 = new ExternalSort1(Simulation,FileAddr,FileDir);
                            break;
                    }
                }

            }
        });
        SorOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Object item = SorOptions.getSelectedItem();
                met = ((combo)item).getValue();
                System.out.println("The Selected method is "+((combo)item).getKey());
            }
        });

        file.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ele=1;

                JFileChooser chooser = new JFileChooser();
                int returnVal = chooser.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    FileAddr = chooser.getSelectedFile().getAbsolutePath();
                    FileDir = chooser.getSelectedFile().getParent();
                    FileName = chooser.getSelectedFile().getName();
                    System.out.println(FileAddr);
                }
                try{
                    data = new String(Files.readAllBytes(Paths.get(FileAddr)));

                }
                catch (Exception e){
                    System.out.println("Couldn't read file");
                }
            }
        });
        OpenSortFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    open();
                }
                catch (Exception e){
                    System.out.println("Couldn't read file");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sort App");
        frame.setContentPane(new SortApp().MainFrame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000,500));
        frame.pack();
        frame.setVisible(true);
    }
}

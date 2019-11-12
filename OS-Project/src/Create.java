import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.*;
import java.util.*;
import javax.swing.*;

public class Create extends Thread{
    private JTextArea Simulation;
    private int low,high,file;
    private String FileDir;
    private ArrayList<Integer> arr;

    public void write() throws IOException {
        BufferedWriter outputWriter = null;
        outputWriter = new BufferedWriter(new FileWriter(FileDir+"/"+Integer.toString(file)));
        for (int i = low; i <=high; i++) {
            outputWriter.write(Integer.toString(arr.get(i)));
            outputWriter.write(" ");
        }
        outputWriter.flush();
        outputWriter.close();
    }

    public Create(JTextArea simulation, int low, int high, ArrayList<Integer> ar,int file,String FileDir) {
        Simulation = simulation;
        this.low = low;
        this.high = high;
        this.arr = ar;
        this.file=file;
        this.FileDir = FileDir;

    }

    public void run() {

        for (int i = low + 1; i <= high; i++)
        {
            int temp = arr.get(i);
            int j = i - 1;
            while (j >= low && arr.get(j) > temp)
            {
                arr.set(j+1,arr.get(j));
                j--;
            }
            arr.set(j+1,temp);
        }

        try {
            write();
        }catch (Exception e) {
            System.out.println("error");
        }
        }
    }

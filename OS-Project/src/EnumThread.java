import javax.swing.*;
import java.lang.*;
import java.util.*;
import javax.swing.*;

public class EnumThread extends Thread{
    private ArrayList<Integer> arr,arr1;
    Integer i,n;
    private JTextArea Simulation;

    public EnumThread(ArrayList<Integer> arr, ArrayList<Integer> arr1, Integer i, Integer n, JTextArea simulation) {
        this.arr = arr;
        this.arr1 = arr1;
        this.i = i;
        this.n = n;
        this.Simulation = simulation;
    }

    public void run(){
        Integer x = arr.get(i);
        Integer pos = 0;
        for (int j=0;j<n;j++){
            if(x > arr.get(j)){
                pos++;
            }
        }
        arr1.set(pos,x);
    }
}

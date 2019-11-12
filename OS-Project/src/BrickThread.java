import javax.swing.*;
import java.lang.*;
import java.util.*;
import javax.swing.*;

public class BrickThread extends Thread{
    private ArrayList<Integer> arr;
    private Integer tmp;
    private JTextArea Simulation;

//    public void gettmp(Integer x){
//        tmp=x;
//    }

    public BrickThread(ArrayList<Integer> arr, JTextArea ta,Integer tmp) {
        this.arr = arr;
        this.Simulation = ta;
        this.tmp = tmp;
    }

    public void run(){
        Simulation.append(Integer.toString(tmp)+" ");
        Integer ind = tmp;
//        tmp = tmp + 2;

        if ((arr.get(ind) > arr.get(ind+1)) && (ind + 1 < arr.size())){
            Integer t = arr.get(ind);
            arr.set(ind,arr.get(ind+1));
            arr.set(ind+1,t);
        }
    }
}

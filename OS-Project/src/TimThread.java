import javax.swing.*;
import java.lang.*;
import java.util.*;
import javax.swing.*;

public class TimThread extends Thread{
    private JTextArea Simulation;
    private ArrayList<Integer> arr;
    int left, right;

    public TimThread(ArrayList<Integer> arr,int left,int right){
        this.arr = arr;
        this.left = left;
        this.right = right;
    }

    public void run() {
        for (int i = left + 1; i <= right; i++)
        {
            int temp = arr.get(i);
            int j = i - 1;
            while (j >= left && arr.get(j) > temp)
            {
                arr.set(j+1,arr.get(j));
                j--;
            }
            arr.set(j+1,temp);
        }
    }
}

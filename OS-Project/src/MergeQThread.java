import javax.swing.*;
import java.lang.*;
import java.util.*;
import javax.swing.*;

public class MergeQThread extends Thread{
    private ArrayList<Integer> arr;
    private int left,right;
    private JTextArea Simulation;

    public MergeQThread(ArrayList<Integer> a, int l, int r, JTextArea ta){
        this.arr=a;
        this.left=l;
        this.right=r;
        this.Simulation = ta;
    }int k;

    public int partition(ArrayList<Integer> arrr,int low,int high){
        Simulation.append(">> Now partitioning the pivot element such that numbers greater than pivot lie on right of it and less than pivot lie in left\n>> After the Partition \""+Integer.toString(arrr.get(high))+"\" is at its right position\n>> ");
        int pivot = arrr.get(high);
        int t,i = (low - 1);
        for (int j = low; j <= high- 1; j++)
        {
            if (arrr.get(j) <= pivot)
            {
                i++;
                t=arrr.get(i);
                arrr.set(i,arrr.get(j));
                arrr.set(j,t);
            }
        }
        t=arrr.get(i+1);
        arrr.set(i+1,arrr.get(high));
        arrr.set(high,t);
        for (int j=low;j<=high;j++){
            k = arr.get(j);
            Simulation.append(Integer.toString(k)+" ");
        }Simulation.append("\n");
        return (i + 1);
    }

    public void run(){
        if(left<right){
            Simulation.append("\n>> Considering the Last element as pivot - "+Integer.toString(arr.get(right))+"\n");
            int p = partition(arr,left,right);
            Simulation.append(">> Applying the QuickSort on the left no.s to pivot "+"\""+Integer.toString(arr.get(p))+"\""+" i.e. - ( ");
            for (int j=left;j<=p-1;j++){
                k = arr.get(j);
                Simulation.append(Integer.toString(k)+" ");
            }Simulation.append(")\n");
            MergeQThread tid1 = new MergeQThread(arr,left,p-1,Simulation);
            tid1.start();

            Simulation.append(">> Applying the QuickSort on the right no.s to pivot "+"\""+Integer.toString(arr.get(p))+"\""+" i.e. - ( ");
            for (int j=p+1;j<=right;j++){
                k = arr.get(j);
                Simulation.append(Integer.toString(k)+" ");
            }Simulation.append(")\n");
            MergeQThread tid2 = new MergeQThread(arr,p+1,right,Simulation);
            tid2.start();

            try {
                tid1.join();
            }
            catch (Exception e){
                System.out.println("tid1 failure");
            }

            try {
                tid2.join();
            }
            catch (Exception e){
                System.out.println("tid2 failure");
            }
        }
    }

}

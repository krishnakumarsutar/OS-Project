import javax.swing.*;
import java.lang.*;
import java.util.*;

public class MergeThread extends Thread{
    private int me,le,re,z;
    private  ArrayList<Integer> arrr;
    private JTextArea Simulation;
    private VisualizerFrame frame;

    public MergeThread(ArrayList<Integer> ar,int l,int r,JTextArea ta,int z,VisualizerFrame frame){
        this.arrr=ar;
        this.le=l;
        this.re=r;
        this.Simulation = ta;
        this.z=z;
        this.frame=frame;
    }

    public void merge(ArrayList<Integer> arr,int left,int right,int mid){
        mid = left + (right - left)/2;
        int i,j,k;
        int n1=mid-left+1;
        int n2=right-mid;
        int[] L = new int[n1];
        int[] R = new int[n2];

        for (i = 0; i < n1; i++)
            L[i] = arr.get(left + i);
        for (j = 0; j < n2; j++)
            R[j] = arr.get(mid + 1+ j);

        i = 0;
        j = 0;
        k = left;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                arr.set(k,L[i]);
                i++;
            }
            else
            {
                arr.set(k,R[j]);
                j++;
            }
            k++;
        }
        while (i < n1)
        {
            arr.set(k,L[i]);
            i++;
            k++;
        }
        while (j < n2)
        {
            arr.set(k,R[j]);
            j++;
            k++;
        }
    }

    public void run(){

        if(le<re){
            Simulation.append("\n>> Let's divide this data into two halves - ( ");
            me=le+(re-le)/2;
            for (int j=le;j<=me;j++){
                Simulation.append(Integer.toString(arrr.get(j))+" ");
            }Simulation.append(") & ( ");
            for (int j=me+1;j<=re;j++){
                Simulation.append(Integer.toString(arrr.get(j))+" ");
            }Simulation.append(")\n");
            Simulation.append(">> Applying MergeSort on left half - ( ");
            z++;
            for (int j=le;j<=me;j++){
                Simulation.append(Integer.toString(arrr.get(j))+" ");
            }Simulation.append(")\n");

            MergeThread tid1 = new MergeThread(arrr,le,me,Simulation,z,frame);
            tid1.start();

            try {
                tid1.join();
            }
            catch (Exception e){
                System.out.println("tid1 failure");
            }
            frame.reDrawArray(arrr);
            System.out.println("sleep");
            try {
                Thread.sleep(SortApp.sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Simulation.append(">> Applying MergeSort on right half - ( ");z++;
            for (int j=me+1;j<=re;j++){
                Simulation.append(Integer.toString(arrr.get(j))+" ");
            }Simulation.append(")\n");

            MergeThread tid2 = new MergeThread(arrr,me+1,re,Simulation,z,frame);
            tid2.start();


            try {
                tid2.join();
            }
            catch (Exception e){
                System.out.println("tid2 failure");
            }
//            frame.reDrawArray(arrr);
//            try {
//                Thread.sleep(SortApp.sleep);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            Simulation.append(">> Merging the left and right sorted halves - ( ");
            for (int j=le;j<=me;j++){
                Simulation.append(Integer.toString(arrr.get(j))+" ");
            }Simulation.append(") & ( ");
            for (int j=me+1;j<=re;j++){
                Simulation.append(Integer.toString(arrr.get(j))+" ");
            }Simulation.append(")\n");
//            frame.reDrawArray(arrr);
//            try {
//                Thread.sleep(SortApp.sleep);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            merge(arrr,le,re,me);
//            frame.reDrawArray(arrr);
//            try {
//                Thread.sleep(SortApp.sleep);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}

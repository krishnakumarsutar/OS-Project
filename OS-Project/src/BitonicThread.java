import javax.swing.*;
import java.lang.*;
import java.util.*;
import javax.swing.*;

public class BitonicThread extends Thread{
    private int low, dir, cnt;
    private ArrayList<Integer> ar;
    private JTextArea Simulation;

    public BitonicThread(ArrayList<Integer> ar, int low, int cnt, int dir,JTextArea ta) {
        this.low = low;
        this.dir = dir;
        this.cnt = cnt;
        this.ar = ar;
        this.Simulation = ta;
    }

    private void compAndSwap(ArrayList<Integer> a, int i, int j) {
        int temp = a.get(i);
        int jValue = a.get(j);
        if ((temp > jValue && dir == 1) || (temp < jValue && dir == 0)) {
            // Swapping elements
            a.set(i,jValue);
            a.set(j,temp);
        }
    }

    private void bitonicMerge(ArrayList<Integer> a, int low, int cnt, int dir) {

        if (cnt < 2)
            return;
        else if (((low + cnt) < 0) || (low > a.size()))
            return;

        int k = cnt / 2;
        for (int i = low; i < (low + k); i++)
            if ((i < 0) || ((i + k) > a.size()))
                continue;
            else if ((a.get(i) > a.get(i + k)&&dir==1)||(a.get(i) < a.get(i + k)&&dir==0))
                compAndSwap(a, i, i + k);

        bitonicMerge(a, low, k, dir);
        bitonicMerge(a, low + k, k, dir);
    }

    public void run() {
        if(cnt<2)
            return;
        else if ((cnt & -cnt) != cnt) // handle non-power of 2
        {
            int n = cnt;
            while ((n & -n) != n)
                n &= (n - 1);
            BitonicThread tid1 = new BitonicThread(ar, low, cnt - n,1,Simulation);
            tid1.start();
            BitonicThread tid2 = new BitonicThread(ar, low + (cnt - n), n, 0,Simulation);
            tid2.start();

            try {
                tid1.join();
            } catch (Exception e) {
                System.out.println("tid1 failure");
            }

            try {
                tid2.join();
            } catch (Exception e) {
                System.out.println("tid2 failure");
            }

            bitonicMerge(ar, low + cnt - n - n, n + n, dir);
            return;
        }
        int k = cnt / 2;

        // sort in ascending order since dir here is 1
        BitonicThread tid1 = new BitonicThread(ar, low, k, 1,Simulation);
        tid1.start();
        // bitonicSort(a, low, k, 1);

        // sort in descending order since dir here is 0
        BitonicThread tid2 = new BitonicThread(ar, low + k, k, 0,Simulation);
        tid2.start();
        // bitonicSort(a, low + k, k, 0);
        try {
            tid1.join();
        } catch (Exception e) {
            System.out.println("tid1 failure");
        }

        try {
            tid2.join();
        } catch (Exception e) {
            System.out.println("tid2 failure");
        }
        // Will merge wole sequence in ascending order
        // since dir=1.
        bitonicMerge(ar, low, cnt, dir);
    }
}

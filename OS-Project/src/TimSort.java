import javax.swing.*;
import java.util.*;
import java.lang.*;

public class TimSort {
    static int RUN = 32;
    private JTextArea ta = new JTextArea();
    private ArrayList<Integer> arr = new ArrayList<Integer>();
    String[] args;

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

    public static void merge(ArrayList<Integer> arr, int l,int m, int r)
    {
        // original array is broken in two parts
        // left and right array
        int len1 = m - l + 1, len2 = r - m;
        int[] left = new int[len1];
        int[] right = new int[len2];
        for (int x = 0; x < len1; x++)
        {
            left[x] = arr.get(l+x);
        }
        for (int x = 0; x < len2; x++)
        {
            right[x] = arr.get(m+1+x);
        }

        int i = 0;
        int j = 0;
        int k = l;

        // after comparing, we merge those two array
        // in larger sub array
        while (i < len1 && j < len2)
        {
            if (left[i] <= right[j])
            {
                arr.set(k,left[i]);
                i++;
            }
            else
            {
                arr.set(k,right[j]);
                j++;
            }
            k++;
        }

        // copy remaining elements of left, if any
        while (i < len1)
        {
            arr.set(k,left[i]);
            k++;
            i++;
        }

        // copy remaining element of right, if any
        while (j < len2)
        {
            arr.set(k,right[j]);
            k++;
            j++;
        }
    }


    public static void timSort(ArrayList<Integer> arr, int n)
    {

        // Sort individual subarrays of size RUN

        TimThread a[] = new TimThread[n];

        for (int i = 0; i < n; i += RUN)
        {
            a[i] = new TimThread(arr,i,Math.min((i+31),(n-1)));
            a[i].start();
            //insertionSort(arr, i, Math.min((i + 31), (n - 1)));
        }


        for (int i = 0; i < n; i += RUN)
        {
            try {
                a[i].join();
            } catch (Exception e) {
                System.out.println("thread"+i+" failure");
            }
        }


        // start merging from size RUN (or 32). It will merge
        // to form size 64, then 128, 256 and so on ....
        for (int size = RUN; size < n; size = 2 * size)
        {

            // pick starting point of left sub array. We
            // are going to merge arr[left..left+size-1]
            // and arr[left+size, left+2*size-1]
            // After every merge, we increase left by 2*size
            for (int left = 0; left < n; left += 2 * size)
            {

                // find ending point of left sub array
                // mid+1 is starting point of right sub array
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (n - 1));

                // merge sub array arr[left.....mid] &
                // arr[mid+1....right]
                merge(arr, left, mid, right);
            }
        }
    }

    public TimSort(JTextArea ta, ArrayList<Integer> arr, String[] args) {
        this.ta = ta;
        this.arr = arr;
        this.args = args;
        int k;

        if (args.length > 0) {
            for(String val:args) {
                String str = val;
                arr.add(atoi(str));
            }
        }

        ta.append(">> The Given set of Numbers - ");
        for (int i=0;i<arr.size();i++){
            k = arr.get(i);
            ta.append(Integer.toString(k)+" ");
        }ta.append("\n");

        timSort(arr,arr.size());

        ta.append("\n"+">> The Final Sorted Array - ");
        for (int i=0;i<arr.size();i++){
            k = arr.get(i);
            ta.append(Integer.toString(k)+" ");
        }ta.append("\n");
    }
}

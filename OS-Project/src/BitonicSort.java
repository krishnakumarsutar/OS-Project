import javax.swing.*;
import java.util.*;
import java.lang.*;

public class BitonicSort {
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

    public BitonicSort(JTextArea ta, String[] args,ArrayList<Integer> ar) {
        this.ta = ta;
        this.args = args;
        this.arr = ar;
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

        BitonicThread tid = new BitonicThread(arr,0,arr.size(),1,ta);
        tid.start();

        try {
            tid.join();
        }
        catch (Exception e){
            System.out.println("tid failure");
        }

        ta.append("\n"+">> The Final Sorted Array - ");
        for (int i=0;i<arr.size();i++){
            k = arr.get(i);
            ta.append(Integer.toString(k)+" ");
        }ta.append("\n");
    }
}

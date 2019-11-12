import javax.swing.*;
import java.util.*;
import java.lang.*;

public class EnumerationSort {
    private JTextArea ta = new JTextArea();
    private ArrayList<Integer> arr = new ArrayList<Integer>();
    private ArrayList<Integer> arr1 = new ArrayList<Integer>();
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

    public EnumerationSort(JTextArea ta, String[] args,ArrayList<Integer> ar) {
        this.ta = ta;
        this.args = args;
        this.arr1 = ar;
        //this.arr1 = ar;
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

        for (int i=0;i<arr.size();i++){
            arr1.add(-666);
        }

        EnumThread[] tid;
        tid = new EnumThread[arr.size()];

        for (int i=0;i<arr.size();i++){
            tid[i] = new EnumThread(arr,arr1,i,arr.size(),ta);
            tid[i].start();
        }

        for (int i=0;i<arr.size();i++){
            try {
                tid[i].join();
            }
            catch (Exception e){
                System.out.println("tid failure");}
        }

        for (int i=0;i<arr.size();i++){
            if(arr1.get(i)==-666){
                arr1.set(i,arr1.get(i-1));
            }
        }

//        arr=arr1;

        ta.append("\n"+">> The Final Sorted Array - ");
        for (int i=0;i<arr1.size();i++){
            k = arr1.get(i);
            ta.append(Integer.toString(k)+" ");
        }ta.append("\n");
    }
}

import javax.swing.*;
import java.util.*;
import java.lang.*;

public class BrickSort {
    private JTextArea ta = new JTextArea();
    private ArrayList<Integer> arr = new ArrayList<Integer>();
    private VisualizerFrame frame;
    String[] args;
    int tp;

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

    public BrickSort(String[] args,JTextArea ta,ArrayList<Integer> ar,VisualizerFrame frame) {
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

        if(arr.size()%2==0){
        ta.append(">> Now we will sort in pairs i.e odd indexed pairs and even indexed pairs - \n>>> Even pairs - ");
        for(int i=0;i<arr.size();i=i+2){
            ta.append("( "+Integer.toString(arr.get(i))+" "+Integer.toString(arr.get(i+1))+" ) ");
        }ta.append("\n>>> Odd pairs - ");
        for(int i=1;i<arr.size()-1;i=i+2){
            ta.append("( "+Integer.toString(arr.get(i))+" "+Integer.toString(arr.get(i+1))+" ) ");
        }ta.append("\n");}
        else{
            ta.append(">> Now we will sort in pairs i.e odd indexed pairs and even indexed pairs - \n>>> Even pairs - ");
            for(int i=0;i<arr.size()-1;i=i+2){
                ta.append("( "+Integer.toString(arr.get(i))+" "+Integer.toString(arr.get(i+1))+" ) ");
            }ta.append("\n>>> Odd pairs - ");
            for(int i=1;i<=arr.size()-1;i=i+2){
                ta.append("( "+Integer.toString(arr.get(i))+" "+Integer.toString(arr.get(i+1))+" ) ");
            }ta.append("\n");
        }


        BrickThread[] tid;
        tid = new BrickThread[(arr.size()+1)/2];

        int i,j;

        for (i=1;i<=arr.size();i++){
            if(i%2==1){
                tp=0;
                for (j=0;j<(arr.size()+1)/2;j++){
                    tid[j] = new BrickThread(arr,ta,tp);
                    tid[j].start();
                    tp=tp+2;
                }

                for (j=0;j<(arr.size()+1)/2;j++){
                    try {
                        tid[j].join();
                    }
                    catch (Exception e){
                        System.out.println("tid failure");}
                }
            }

            else {
                tp=1;
                for (j=0;j<((arr.size()+1)/2)-1;j++){
                    tid[j] = new BrickThread(arr,ta,tp);
                    tid[j].start();
                    tp=tp+2;
                }

                for (j=0;j<((arr.size()+1)/2)-1;j++){
                    try {
                        tid[j].join();
                    }
                    catch (Exception e){
                        System.out.println("tid failure");}
                }
            }
        }

        ta.append("\n"+">> The Final Sorted Array - ");
        for (int p=0;p<arr.size();p++){
            k = arr.get(p);
            ta.append(Integer.toString(k)+" ");
        }ta.append("\n");
    }
}

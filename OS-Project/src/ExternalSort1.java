import javax.swing.*;
import java.io.*;
import java.util.*;
import java.lang.*;

public class ExternalSort1 {
    private JTextArea ta = new JTextArea();
    ArrayList<Integer> arr = new ArrayList();
    private String FileAddr;
    private String FileDir;
    String[] args;
    String[] Num;
    int num,no=0,n;

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

    public void Arr(){
        if (Num.length > 0){
            for(String val:Num) {
                String str = val;
                arr.add(atoi(str));
            }
        }
    }

    public int count() throws IOException{
        File f1 = new File(FileAddr);
        FileReader fr=new FileReader(f1);
        BufferedReader br = new BufferedReader(fr);
        String s;
        //int n,no=0;
        while((s=br.readLine())!=null){
            Num = s.split(" ");
            n = Num.length;
            no = no + n;
        }
        ta.append(">> "+Integer.toString(no)+"\n");
        if(no%100!=0)
            return (no/100)+1;
        else
            return (no/100);

    }

    public void cre() throws IOException{
        num = count();
        Arr();
        int m,k=0,more_input = 1;
        int next_output_file = 0;

        Create cr[] = new Create[num];

        for(int i=0;i<num;i++){
               if(no/100<1)
                   m = no%100;
               else
                   m=99;
               cr[i] = new Create(ta,k,k+m,arr,i,FileDir);
               cr[i].start();
               k=k+100;
               no=no-100;
            }

        for(int i=0;i<num;i++){
            try {
                cr[i].join();
            }catch (Exception e){
                System.out.println("error");
            }
        }

    }



    public ExternalSort1(JTextArea ta, String fileAddr, String fileDir) {
        this.ta = ta;
        this.FileAddr = fileAddr;
        this.FileDir = fileDir;

        try {
            cre();
        }catch (Exception e) {
            System.out.println("error1");
        }
        //System.out.println(num+" "+FileDir);
        try {
            MergeFile.MergeFiles(FileDir+"/output1.txt",num,FileDir);
        }catch (Exception e) {
            System.out.println("eerror2");
        }
        }
    }


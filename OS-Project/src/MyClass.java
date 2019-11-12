import java.util.*;
import javax.swing.*;

public class MyClass {
    public static void main(String[] args) {
        System.out.println("Enter the no. of entries - ");
        Scanner myObj = new Scanner(System.in);
        int n = myObj.nextInt();

        int[] array = new int[n];

        for(int i=0;i<array.length;i++){
            array[i]=myObj.nextInt();
        }

        MyThread new1 = new MyThread(array,0,n-1,n);

        new1.start();

        try{
        new1.join();
        }
        catch (InterruptedException ee){
            System.out.println("Error thread join");
        }
//        for(int i=0;i<array.length;i++){
//            System.out.print(array[i]+" ");
//        }
//        System.out.println();


    }
}

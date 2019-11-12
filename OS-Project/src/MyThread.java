import java.lang.*;

public class MyThread extends Thread {
    int[] ar;
    int l,r,n;

    public MyThread(int[] arr, int left, int right, int length){
        this.ar=arr;
        this.l=left;
        this.r=right;
        this.n=length;
    }

    public void run(){
        for(int i=0;i<n;i++){
            System.out.print(ar[i]+" ");
        }
    }
}

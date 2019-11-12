public class Bubble implements Algo{
    @Override
    public void runSort(int[] data){
        int len = data.length;
        for (int i=0;i<len;i++){
            for(int j=0;j<len-i;j++){
                if(data[i]>data[j]){
                    int temp = data[j];
                    data[j]=data[i];
                    data[i]=temp;
                }
            }
        }
    }
}

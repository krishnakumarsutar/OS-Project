import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;import java.io.File;
public class MergeFile {

    static void MergeFiles(String output_file, int k,String FileDir) throws IOException
    {
        //FileReader in[] = new FileReader[k];
        Scanner in[] = new Scanner[k];
        System.out.println("K: "+k);
        //int i;
        for (int i = 0; i < k; i++)
        {
//            String fileName;
//
//            // convert i to string
//            //snprintf(fileName, sizeof(fileName), "%d", i);
//            fileName = String.valueOf(i);

            // Open output files in read mode.
            File text = new File(FileDir+"/"+i);
            in[i] = new Scanner(text);
//            try
//            {
//                System.out.println("*");
//                in[i] = new FileReader(FileDir+"/"+Integer.toString(i));
//            }
//            catch (FileNotFoundException fe)
//            {
//                System.out.println("File not found");
            //}
        }


//        System.out.println("All InIfdsfskdfsjfhs");
//        for(int l = 0; l < 100; l++){
//            System.out.println(in[l].toString());
//        }

        // FINAL OUTPUT FILE
        //FILE *out = openFile(output_file, "w");
        FileWriter out = null;
        try
        {
            out = new FileWriter(output_file);
        }
        catch (FileNotFoundException fe)
        {
            System.out.println("File not found");
        }

        // Create a min heap with k heap nodes.  Every heap node
        // has first element of scratch output file
        MinHeapNode[] harr = new MinHeapNode[k];
        //ArrayList<MinHeapNode> harr = new MinHeapNode();
        int i;
        Scanner fr = null;

        for (i = 0; i < k; i++)
        {
            // break if no output file is empty and
            // index i will be no. of input files
//            if (fscanf(in[i], "%d ", &harr[i].element) != 1)
//            break;
            fr = in[i];
            int ch;

            //BufferedReader br = new BufferedReader(fr);
//            if ((ch=fr.read())!=-1){
//                char c = (char)ch;
//                System.out.println((char)ch);
//                harr[i].element = Integer.parseInt(Character.toString(c));
//            }else
//                break;
            String st;

            if (fr.hasNext()){
                //list.add(file.nextInt());
//                if(!fr.hasNextInt()){
//                    System.out.println("NOt int");
//                }
                //System.out.println(fr.nextInt()+" "+i);
                //System.out.println("Hello "+ fr.next());
                int temp = fr.nextInt();
                MinHeapNode temp1 = new MinHeapNode();
                temp1.element = temp;
                temp1.i = i;
                harr[i] = temp1;
                System.out.println(harr[i].element +" * "+i);
                //fr.next();
            }
            else break;

//            while ((st = br.readLine()) != null) {
//                String[] arrOfStr = st.split(" ");
//                int test = Integer.parseInt(arrOfStr[0]);
//                harr[i].element = test;
////                for(int l = 0; l < arrOfStr.length; l++){
////                    //System.out.println(arrOfStr[l]);
////
////                    System.out.println("HELLO");
////                    //harr[i].element = Integer.parseInt(arrOfStr[l]);
////
////                }
//            }

//            System.out.println("i "+i);
          //harr[i].i = i; // Index of scratch output file
        }


        System.out.println("All InI");
//        for(int l = 0; l < 100; l++){
//            System.out.println(harr[l].element+" "+harr[l].i);
//        }

        MinHeap hp = new MinHeap(harr, i); // Create the heap


        int count = 0;

        // Now one by one get the minimum element from min
        // heap and replace it with next element.
        // run till all filled input files reach EOF

        while (count != i)
        {
            // Get the minimum element and store it in output file
            MinHeapNode root = hp.getMin();
            System.out.println("I: "+i+" Count: "+count);
            System.out.println("Element: "+root.element);
            //fprintf(out, "%d ", root.element);
            //System.out.println(root.element);
            out.write(Integer.toString(root.element)+" ");

            // Find the next element that will replace current
            // root of heap. The next element belongs to same
            // input file as the current min element.
//            if (fscanf(in[root.i], "%d ", &root.element) != 1)
//            {
//                root.element = INT_MAX;
//                count++;
//            }

            fr = in[root.i];
            int ch;
            if (!fr.hasNextInt())
            {
                root.element = 2147483647;
                count++;
            }else{
                root.element = fr.nextInt();
            }

            //System.out.println("ROOT: "+fr.nextInt());


            // Replace root with next element of input file
            hp.replaceMin(root);
        }

        // close input and output files
        for (i = 0; i < k; i++)
            in[i].close();

        out.close();
        fr.close();
    }
}

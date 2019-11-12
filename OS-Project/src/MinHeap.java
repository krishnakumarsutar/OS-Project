class MinHeap
{
    MinHeapNode harr[];
    int heap_size;     // size of min heap

    public int left(int i) { return (2 * i + 1); }

    // to get index of right child of node at index i
    public int right(int i) { return (2 * i + 2); }

    // to get the root
    public MinHeapNode getMin() { return harr[0]; }

    // to replace root with new node x and heapify()
    // new root
    public void replaceMin(MinHeapNode x)
    {
        harr[0] = x;
        MinHeapify(0);
    }
    // Constructor: creates a min heap of given size
    //MinHeap(MinHeapNode a[], int size);

    public MinHeap(MinHeapNode a[], int size)
    {
        this.harr = a;
        this.heap_size = size;
        int i = (heap_size - 1) / 2;
//
//        System.out.println("All InI");
//        for(int l = 0; l < 100; l++){
//            System.out.println(harr[l].element+" "+harr[l].i);
//        }
        while (i >= 0)
        {
            MinHeapify(i);
            i--;
        }

    }

    public void MinHeapify(int i)
    {
        int l = left(i);
        int r = right(i);
        int smallest = i;
        if (l < heap_size && harr[l].element < harr[i].element)
            smallest = l;
        if (r < heap_size && harr[r].element < harr[smallest].element)
            smallest = r;
        if (smallest != i)
        {
            // swap(&harr[i], &harr[smallest]);
            MinHeapNode temp = harr[i];
            harr[i] = harr[smallest];
            harr[smallest] = temp;
            MinHeapify(smallest);
        }
    }
};
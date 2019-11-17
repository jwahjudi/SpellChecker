import java.util.ArrayList;

public class BinaryHeap implements Tree{


    String a[];
    int size;

    public BinaryHeap() {
        a = new String[10];
        size = 0;
    }

    public boolean find(String item)
    {
        int low=0;
        int high = a.length-1;
        while(low<=high){
            int mid = (low+high)/2;
            if(a[mid]==item)
                return true;
            else if(a[mid].compareTo(item) > 0)
                high = mid -1;
            else
                low = mid+1;
        }
        return false;
    }

    public void insert(String item) {
        if (size == a.length)
            growArray();
        a[size++] = item;
        int child = size - 1;
        int parent = (child - 1) / 2;
        while (parent >= 0 && a[parent].compareTo(a[child]) > 0) {
            String temp = a[parent];
            a[parent] = a[child];
            a[child] = temp;
            child = parent;
            parent = (child - 1) / 2;
        }
    }

    public void checkWord(String item, ArrayList list)
    {
        return;
    }

    public void growArray() {
        String[] temp = new String[a.length * 2];
        for (int index = 0; index < a.length; index++)
            temp[index] = a[index];
        a = temp;
    }
}
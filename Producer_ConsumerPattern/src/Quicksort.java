import java.util.Arrays;
import java.util.Random;

public class Quicksort {


    static void quicksort(int[] arr, int low, int high){
        if( low >= high || arr.length <= 1||arr == null ){
            // stop
            return;
        }
        int i = low;
        int j = high;
        int pivot = arr[high];
        j--;
        while (i<=j){
            //find i --- the first index bigger than pivot
            //     j --- the first index smaller than pivot
            while(arr[i] < pivot ){
                ++i;
            }
            while(j>=0 && arr[j] > pivot ){
                --j;
            }
            if(i<j){
                swap(arr,i,j);
                ++i;
                --j;
            }
            else if (i==j){
                i++;
            }
        }
        swap(arr,j+1,high);
        quicksort(arr,low,j);
        quicksort(arr,i+1,high);

    }
    static void swap (int[] a , int low , int high){
        if (low>=0 && high <= a.length && low <= high){
             int temp = a[low];
             a[low] = a[high];
             a[high] = temp;
        }
        else {
            System.out.println("Swap Error!");
            return ;
        }
    }


    public static void main(String[] args) {
//        int[] arr = {2,1,0,5,8,7,6,3};
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {

            arr[i] = new Random().nextInt(200);
        }
//        swap(arr,2,5);
        quicksort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}

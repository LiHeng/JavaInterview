package sort;

public class Sorting {

    public static void printArray(int arr[]){
        System.out.print("[");
        for (int i: arr) {
            System.out.print(i+" ");
        }
        System.out.println("]");
    }

    public static void swap(int arr[], int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j]  = tmp;
    }

    public static void quickSort(int arr[], int low, int high){
        if (arr==null||arr.length==0)
            return;
        if (low>=high)
            return;

        int middle = low + (high-low)/2;
        int pivot = arr[middle];

        int i=low, j=high;
        while (i<=j){
            while (arr[i]<pivot)
                i++;
            while (arr[j]>pivot)
                j--;
            if (i<=j){
                swap(arr,i,j);
                i++;
                j--;
            }
        }

        if (low<j)
            quickSort(arr,low,j);
        if (high>i)
            quickSort(arr,i,high);
    }

    public static int partition(int arr[], int start, int end){
        System.out.println(start+" "+end);
        int pivot = arr[start];
        int p = start+1;
        int q = end;
        while (p<=q){
            while (p<=q&&arr[p]<pivot){
                p++;
            }
            while (p<=q&&arr[q]>=pivot){
                q--;
            }
            if (p<q){
                swap(arr,p,q);
            }
        }
        swap(arr,start,q);
        return q;
    }

    public static void quickSort2(int arr[],int low, int high){
        if (low>=high){
            return;
        }
        int mid = partition(arr,low,high);
        quickSort2(arr,low,mid-1);
        quickSort2(arr,mid+1,high);
    }

    public static void mergeSort(int[] a, int low, int high){
        int mid = low + (high-low)/2;
        if (low<high){
            mergeSort(a, low, mid);
            mergeSort(a, mid+1, high);
            merge(a,low,mid,high);
        }
    }

    public static void merge(int[] a, int low, int mid, int high){
        int[] tmp = new int[high-low+1];
        int i = low;
        int j = mid+1;
        int k = 0;
        while (i<=mid&&j<=high){
            if (a[i]<a[j]){
                tmp[k++] = a[i++];
            }else {
                tmp[k++] = a[j++];
            }
        }
        while (i<=mid){
            tmp[k++] = a[i++];
        }
        while (j<=high){
            tmp[k++] = a[j++];
        }

        for (int l = 0; l < tmp.length; l++) {
            a[l+low] = tmp[l];
        }
    }


    public static void main(String[] args) {
        int a[] = new int[]{4,5,8,4,23,6,7,89,1};
        quickSort2(a,0,a.length-1);
        printArray(a);

        a = new int[]{4,5,8,4,23,6,7,89,1};
        mergeSort(a,0,a.length-1);
        printArray(a);
    }

}

import java.util.ArrayList;
import java.util.List;

public class MinHeap<T extends Comparable<T>> {

    private List<T> mHeap;        // 存放堆的数组

    public MinHeap(){
        mHeap = new ArrayList<>();
    }

    private void filterDown(int start, int end){

        T tmp = mHeap.get(start);    //需要往下沉的元素

        int current = start;
        while (current<end){
            int left = 2*current+1;
            int right = 2*current+2;

            if (left>end){
                break;
            }

            //T min = mHeap.get(left);
            int next = left;
            if (right<=end&&mHeap.get(right).compareTo(mHeap.get(left))<0){
                next = right;
            }

            if (tmp.compareTo(mHeap.get(next))<=0){
                break;
            }else {
                mHeap.set(current,mHeap.get(next));
                current = next;
            }
        }

        mHeap.set(current, tmp);
    }

    private void filterUp(int start){
        int c = start;            // 当前节点(current)的位置
        T tmp = mHeap.get(c);        // 需要上浮的元素

        while (c>0){
            int p = (c-1)/2;        // 父(parent)结点的位置
            int cmp = mHeap.get(p).compareTo(tmp);
            if(cmp <= 0)
                break;
            else {
                mHeap.set(c,mHeap.get(p));
                c = p;
            }
        }
        mHeap.set(c,tmp);
    }

    public void insert(T data){
        int size = mHeap.size();
        mHeap.add(data);

        filterUp(size);
    }

    public T remove(){
        if (mHeap.isEmpty()){
            return null;
        }

        T tmp = mHeap.get(0);
        int size = mHeap.size();
        mHeap.set(0, mHeap.get(size-1));
        mHeap.remove(size-1);

        if (mHeap.size()>1) {
            filterDown(0, mHeap.size() - 1);
        }
        return tmp;
    }

    public boolean isEmpty(){
        return mHeap.isEmpty();
    }

    public void showHeap(){
        mHeap.forEach(System.out::println);
    }

    public static void main(String[] args) {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.insert(3);
        heap.insert(5);
        heap.insert(2);
        heap.insert(9);
        heap.insert(20);

        while (!heap.isEmpty()){
            System.out.println(heap.remove());
        }
    }


}

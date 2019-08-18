import java.util.Arrays;
import java.util.Comparator;

/**
 * @description: 构建一个堆（大根堆和小跟堆）
 * i 节点的孩子节点为 2i+1 和 2i+2, i 节点的父节点为 (i-1)/2，最后一个非叶子节点为 n/2-1
 * 大根堆从小到大排序，小根堆从大到小排序
 * 建堆有两种方式：
 * 1. 自顶向下，从根节点开始，一个个地把节点插入堆中，当把一个新的节点插入堆中时，需要对堆进行调整。需要遍历n个节点，每次调整堆的复杂度为logn，所以总的建堆复杂度为O(nlogn)
 * 2. 自底向上，从最后一个非叶子结点开始进行判断其子树是否满足堆的性质。如果满足就继续判断下一个点。否则，如果子树里面某个子结点有最大元素，则交换他们，并依次递归判断其子树是否仍满足堆性质。
 * 时间复杂度为O(n)
 * @author: Daniel
 * @create: 2019-03-27-21-54
 **/
public class Heap<E> {
    Object[] heap;
    int size = 0;
    private final Comparator<? super E> comparator;
    private static final int DEFAULT_INITIAL_CAPACITY = 10;

    public Heap(Comparator<? super E> comparator) {
        this.comparator = comparator;
        heap = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public Heap(Object[] nums, Comparator<? super E> comparator) {
        heap = nums;
        size = nums.length;
        this.comparator = comparator;
        heapify();
    }

    public void offer(E val) {
        int i = size;
        if(i >= heap.length)
            grow(i << 1);
        size = i + 1;
        if(i == 0)
            heap[0] = val;
        else
            siftUp(i,val); // 调整堆
    }

    private void grow(int newCapacity) {
        heap = Arrays.copyOf(heap,newCapacity);
    }

    // 上浮，子节点比父节点小，就需要上浮
    private void siftUp(int k, E val) {
        while(k > 0) {
            int parent = (k - 1) >>> 1; // 父节点的索引 比如 索引为1,2 的子节点的父节点索引为 0
            Object e = heap[parent];
            if(comparator.compare((E)e,val) <= 0)  // 如果子节点大于等于父节点，不需要调整，退出循环
                break;
            // 如果子节点比父节点小，交换这两个节点，交换后可能比新的父节点小，继续调整
            heap[k] = e;
            k = parent;
        }
        heap[k] = val;
    }
    // 下沉，如果父节点比子节点大，就需要下沉
    private void siftDown(int k, E val) {
        int half = size >>> 1; // 堆是完全二叉树，不需要考虑叶子节点
        while(k < half) { // k = size/2 - 1
            int child = (k << 1) + 1; // 2k+1
            Object c = heap[child]; // 先假设其为子节点中的最小者
            int right = child + 1; // 2k+2
            if(right < size && comparator.compare((E)c,(E)heap[right]) > 0) { // 比较得到两个子节点中的最小者
                c = heap[right];
                child = right;
            }
            if(comparator.compare((E)val,(E)c) <= 0) // 如果父节点比子节点中的最小者还小，不需要调整
                break;
            heap[k] = c; // 否则就交换父节点和子节点中的最小者，交换后可能比新的子节点小，继续调整
            k = child; // 调整新的节点
        }
        heap[k] = val;
    }

    private void heapify() {
        // 从最后一个非叶子节点开始进行下沉操作
        for(int i = (size >>> 1) - 1; i >= 0; i--) // 只考虑非叶子节点，如果一个节点的两个子节点都已经是堆有序，那么下沉操作可以使得以这个节点为父节点的堆有序
            siftDown(i, (E)heap[i]);
    }

    public void printHeap() {
        Object[] array = Arrays.copyOf(heap,size);
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        // 构建一个最大堆
        Heap<Integer> maxHeap = new Heap<>((o1, o2) -> o2.compareTo(o1));
        maxHeap.offer(4);
        maxHeap.offer(5);
        maxHeap.offer(6);
        maxHeap.offer(3);
        maxHeap.offer(2);
        maxHeap.offer(8);
        maxHeap.offer(7);
        maxHeap.printHeap();
        // 构建一个最小堆
        Integer[] nums = {4,2,5,7,8,1,3};
        Heap<Integer> minHeap = new Heap<>(nums, (o1,o2) -> o1.compareTo(o2));
        System.out.println(Arrays.toString(nums));
    }
}

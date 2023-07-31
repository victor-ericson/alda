//Victor Ericson vier1798

package alda.heap;

public class DHeap<T extends Comparable<? super T>> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int DEFAULT_CHILDREN = 2;
    private int childrenCount;
    private int currentSize;
    private T[] array;
    private final int ENLARGE_FACTOR = 2;
    private final int ENLARGE_ADDITION = 1;
    private final int GROWTH_FACTOR = 11;
    private final int GROWTH_DIVISOR = 10;

    public DHeap() {
        this(DEFAULT_CHILDREN);
    }

    public DHeap(int children) {
        if (children < 2)
            throw new IllegalArgumentException();
        childrenCount = children;
        currentSize = 0;
        array = (T[]) new Comparable[DEFAULT_CAPACITY];
    }

    public DHeap(T[] items) {
        currentSize = items.length;
        array = (T[]) new Comparable[(currentSize + 2) * 11 / 10];

        int i = 1;
        for (T item : items)
            array[i++] = item;
        buildHeap();
    }

    public int size() {
        return currentSize;
    }

    public T get(int index) {
        return array[index];
    }

    public void insert(T x) {
        if (currentSize == array.length - 1)
            enlargeArray(array.length * 2 + 1);

        // Percolate up
        int hole = ++currentSize;
        for (array[0] = x; hole > 1 && x.compareTo(array[parentIndex(hole)]) < 0; hole = parentIndex(hole))
            array[hole] = array[parentIndex(hole)];
        array[hole] = x;
    }


    private void enlargeArray(int newSize) {
        T[] old = array;
        array = (T[]) new Comparable[newSize];
        for (int i = 0; i < old.length; i++)
            array[i] = old[i];
    }

    public T findMin() {
        if (isEmpty())
            throw new UnderflowException();
        return array[1];
    }

    public int parentIndex(int index) {
        if (index <= 1) {
            throw new IllegalArgumentException("No parent for index 1");
        }
        if (childrenCount > 2) {
            return Math.round((float) index / childrenCount);
        }
        return index / 2;
    }

    public int firstChildIndex(int index) {
        if (index < 1)
            throw new IllegalArgumentException("No children for index 0");
        if (childrenCount > 2) {
            return index * 2 + (index - 1) * (childrenCount - 2);
        }
        return index * 2;

    }

    public T deleteMin() {
        if (isEmpty())
            throw new UnderflowException();

        T minItem = findMin();
        array[1] = array[currentSize--];
        percolateDown(1);

        return minItem;
    }

    private void buildHeap() {
        for (int i = currentSize / 2; i > 0; i--)
            percolateDown(i);
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void makeEmpty() {
        currentSize = 0;
    }

    private void percolateDown(int hole) {
        int child;
        T tmp = array[hole];

        for (; firstChildIndex(hole) <= currentSize; hole = child) {
            child = firstChildIndex(hole);
            int lowestIndex = child;

            for (int i = 0; i < childrenCount && child + i <= currentSize; i++) {
                if (array[child + i].compareTo(array[lowestIndex]) < 0)
                    lowestIndex = child + i;
            }
            if (array[lowestIndex].compareTo(tmp) < 0) {
                array[hole] = array[lowestIndex];
            } else {
                break;
            }
            child = lowestIndex;

        }
        array[hole] = tmp;
    }

    public String toString() {
        if (this.isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 1; i <= currentSize; i++) {
            sb.append(array[i]).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        int numItems = 10000;
        DHeap<Integer> h = new DHeap<>();
        int i = 37;

        for (i = 37; i != 0; i = (i + 37) % numItems)
            h.insert(i);
        for (i = 1; i < numItems; i++)
            if (h.deleteMin() != i)
                System.out.println("Oops! " + i);
    }
}
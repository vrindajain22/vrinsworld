import java.util.ArrayList;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {
    private int first;
    private int last;
    private T[] array;
    private int size;

    public ArrayDeque() {
        /*Built the constructor to create an array size 8, have a starting and ending index*/
        array = (T[]) new Object[8];
        first = 4;
        last = 5;
        size = 0;
    }



    @Override
    public void addFirst(T x) {
        if (size == array.length) {
            resizing(array.length * 2);
        }
        array[first] = x;
        first = (first - 1 + array.length) % array.length;
        size++;
    }

    @Override
    public void addLast(T x) {
        if (size == array.length) {
            resizing(array.length * 2);
        }
        /* NextLast.prev or size -1= where we insert the last item. If 0, no insertion,if 1 insert at 0*/
        array[last] = x;
        last = (last + 1) % array.length;
        size++;
    }

    @Override
    public List<T> toList() {
        ArrayList<T> a = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            a.add(array[(first + i + 1) % array.length]);
        }
        return a;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (!isEmpty()) {
            first = (first + 1) % array.length;
            T rem = array[first];
            array[first] = null;
            size--;
            if ((double) size / array.length < 0.25 && array.length > 8) {
                resizing(array.length / 2);
            }
            return rem;
        }
        return null;
    }

    @Override
    public T removeLast() {
        if (!isEmpty()) {
            last = (last - 1 + array.length) % array.length;
            T rem = array[last];
            array[last] = null;
            size--;
            if ((double) size / array.length < 0.25 && array.length > 8) {
                resizing(array.length / 2);
            }
            return rem;
        }
        return null;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        int nextfind = first;
        for (int i = 0; i < index; i++) {
            nextfind = (nextfind + 1) % array.length;
        }
        return array[(nextfind + 1) % array.length];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
    private void resizing(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            first = (first + 1) % array.length;
            newArray[i] = array[first];
        }
        first = capacity - 1;
        array = newArray;
        last = size;
        /*circular array implementation*/
    }

}

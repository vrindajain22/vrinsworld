import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque<T> implements Deque<T> {

    public class Node {
        private T value;
        private Node prev;
        private Node next;

        public Node(Node prevNode, Node nextNode, T nodeValue) {
            //We need to store the values in an actual place, not just an address.
            prev = prevNode;
            next = nextNode;
            value = nodeValue;
        }

    }

    int length = 0;
    //Null node that references the front (.next) and back (.prev) of our list.
    Node sentinel = new Node(null, null, null);


    @Override
    public void addFirst(T x) {
        //If sentinel node.next is null, the list is empty.
        if (sentinel.next == null) {
            Node newNode = new Node(sentinel, sentinel, x);
            sentinel.next = newNode;
            sentinel.prev = newNode;
            length++;
        } else {
            Node newNode = new Node(sentinel, sentinel.next, x);
            sentinel.next.prev = newNode;
            sentinel.next = newNode;
            length++;
        }
    }

    @Override
    public void addLast(T x) {
        if (sentinel.prev == null) {
            Node newNode = new Node(sentinel, sentinel, x);
            sentinel.next = newNode;
            sentinel.prev = newNode;
            length++;
        } else {
            Node newNode = new Node(sentinel.prev, sentinel, x);
            sentinel.prev.next = newNode;
            sentinel.prev = newNode;
            length++;
        }

    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node currentNode = sentinel.next;

        while (currentNode != null && currentNode != sentinel) {
            returnList.add(currentNode.value);
            currentNode = currentNode.next;
        }
        return returnList;

    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public T removeFirst() {
        if (size() == 0) {
            return null;
        } else {
            T value = sentinel.next.value;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            length--;
            return value;

        }
    }

    @Override
    public T removeLast() {
        if (size() == 0) {
            return null;
        } else {
            T value = sentinel.prev.value;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            length--;
            return value;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            return null;
        } else if (index >= size()) {
            return null;
        } else {
            int counter = 0;
            Node currentNode = sentinel.next;

            while (currentNode != null && currentNode != sentinel && counter != index) {
                currentNode = currentNode.next;
                counter++;
            }

            return currentNode.value;
        }

    }

    @Override
    public T getRecursive(int index) {
        //Out of bounds checking.
        if (index < 0) {
            return null;
        } else if (index >= size()) {
            return null;
        }
        return helper(sentinel.next, index);
    }

    public T helper(Node nodeCheck, int index) {
        //Check if node 1 is sentinel which is null.
        if (nodeCheck == sentinel) {
            return null;
            //check if node is null (to be safe)
        } else if (nodeCheck == null) {
            return null;
        }
        if (index == 0) {
            //return the node value at 0 index
            return nodeCheck.value;
        }
        nodeCheck = nodeCheck.next;
        //Work way down to index 0 - recursive leap of faith.
        return helper(nodeCheck, index - 1);
    }
}


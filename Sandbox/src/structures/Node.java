package structures;

/**
 * Created by ichung on 3/27/2015.
 */
public class Node<K> {
    private K value;
    private Node<K> next;

    public Node(K value) {
        this.value = value;
        this.next = null;
    }

    public Node<K> getNext() {
        return next;
    }

    public void setNext(Node<K> next) {
        this.next = next;
    }

    public K getValue() {
        return value;
    }
}

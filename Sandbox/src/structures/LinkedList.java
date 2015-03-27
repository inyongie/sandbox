package structures;

/**
 * Created by ichung on 3/27/2015.
 */
public class LinkedList {
    private Node root;

    public LinkedList(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public void remove(Node n) {}

    public void add(Node n) {
        Node node = root;
        if(node.getNext() != null) node = node.getNext();
        node.setNext(n);
    }
}

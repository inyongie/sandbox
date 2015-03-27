package structures;

import javax.swing.plaf.synth.SynthTextAreaUI;

/**
 * Created by ichung on 3/27/2015.
 */
public class CustomLinkedList {
    private Node root;

    public CustomLinkedList(Node root) {
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

    public static void main(String[] args) {
        CustomLinkedList cLinkedList = new CustomLinkedList(new Node<Integer>(1));
        cLinkedList.add(new Node<Integer>(2));

        Node<Integer> n = cLinkedList.getRoot();
        while(n != null) {
            System.out.println(n.getValue());
            n = n.getNext();
        }
    }
}

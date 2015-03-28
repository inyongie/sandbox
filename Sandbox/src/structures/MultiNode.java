package structures;

import java.util.ArrayList;

/**
 * Created by ichung on 3/27/2015.
 */
public class MultiNode<K extends Comparable> {
    private K value;
    private ArrayList<MultiNode<K>> next;

    public MultiNode(K value) {
        this.value = value;
        this.next = null;
    }

    public ArrayList<MultiNode<K>> getNext() {
        return next;
    }

    public void setNext(ArrayList<MultiNode<K>> next) {
        this.next = next;
    }

    public void addToNext(MultiNode<K> node) {
        if(this.next == null) {
            this.setNext(new ArrayList<MultiNode<K>>());
        }
        this.next.add(node);
    }

    public boolean containsInNext(K value) {
        if(this.next == null) return false;

        for(MultiNode<K> n : this.next) {
            if(n.getValue().equals(value)) {
                return true;
            }
        }

        return false;
    }

    public MultiNode<K> getNextNode(K value) {
        for(MultiNode<K> n : this.next) {
            if(n.getValue().equals(value)) {
                return n;
            }
        }
        return null;
    }

    public K getValue() {
        return value;
    }

}

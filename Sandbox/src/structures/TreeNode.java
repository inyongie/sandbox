package structures;

/**
 * Created by ichung on 4/3/2015.
 */
public class TreeNode<T> {
    // What does a Tree need?
    // - Value
    // - Left Node
    // - Right Node
    private T _value;
    private TreeNode _leftNode;
    private TreeNode _rightNode;

    // Constructor
    // Needs
    // - Value of the Node
    // - Sets Left Node and Right Node as null
    public TreeNode(T value) {
        _value = value;
        _leftNode = null;
        _rightNode = null;
    }

    public TreeNode() {
        this(null);
    }

    // Members

    // Something to get/set the Node value
    public void setValue(T value) {
        _value = value;
    }

    public T getValue() {
        return _value;
    }


    // Something to get/set Left Node
    public void setLeftNode(TreeNode leftNode) {
        _leftNode = leftNode;
    }

    public TreeNode getLeftNode() {
        return _leftNode;
    }


    // Something to get/set Right Node
    public void setRightNode(TreeNode rightNode) {
        _rightNode = rightNode;
    }

    public TreeNode getRightNode() {
        return _rightNode;
    }

}

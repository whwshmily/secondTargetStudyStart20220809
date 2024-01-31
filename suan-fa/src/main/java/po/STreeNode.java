package po;

public class STreeNode<T> {

    private T value;

    private STreeNode<T> left;

    private STreeNode<T> right;

    private STreeNode<T> parent;

    public STreeNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public STreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(STreeNode<T> left) {
        this.left = left;
    }

    public STreeNode<T> getRight() {
        return right;
    }

    public void setRight(STreeNode<T> right) {
        this.right = right;
    }

    public STreeNode<T> getParent() {
        return parent;
    }

    public void setParent(STreeNode<T> parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "STreeNode{" +
                "value=" + value +
                '}';
    }
}

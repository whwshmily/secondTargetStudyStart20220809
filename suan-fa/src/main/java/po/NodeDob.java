package po;

//@Data
public class NodeDob<T> {
    private T value;
    private NodeDob<T> prev;
    private NodeDob<T> next;

    public NodeDob(T value, NodeDob<T> prev, NodeDob<T> next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }

    public NodeDob() {

    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public NodeDob<T> getPrev() {
        return prev;
    }

    public void setPrev(NodeDob<T> prev) {
        this.prev = prev;
    }

    public NodeDob<T> getNext() {
        return next;
    }

    public void setNext(NodeDob<T> next) {
        this.next = next;
    }
}

package node;

import po.Node;
import po.NodeDob;

/**
 * 用链表实现栈
 */
public class NodeCode1 {

    /**
     * 用双向链表实现栈
     * 每一次添加元素都会放在头部，每一次弹出都从头部弹出
     *
     * @param <T>
     */
    private static class MyStack1<T> {
        private NodeDob<T> head;
        private NodeDob<T> last;
        private int size;

        public void add(T value) {
            NodeDob<T> nodeDob = new NodeDob<T>(value, null, null);
            if (isEmpty()) {
                head = nodeDob;
                last = nodeDob;
                size++;
                return;
            }
            size++;
            NodeDob<T> next = head;
            head = nodeDob;
            head.setNext(next);
            next.setPrev(head);
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public T peek() {
            if (isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            return head.getValue();
        }

        public T pop() {
            if (isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            T result = head.getValue();
            head = head.getNext();
            size--;
            if (size == 0) {
                last = null;
            }
            return result;
        }

        public String toSting() {
            if (isEmpty()) {
                return "";
            }
            StringBuilder sb = new StringBuilder("[");
            NodeDob<T> cur = last;
            while (cur != null) {
                sb.append(cur.getValue());
                sb.append(" ");
                cur = cur.getPrev();
            }
            sb.delete(sb.length() - 1, sb.length());
            sb.append("]");
            return sb.toString();
        }
    }

    // 用双向链表实现栈 每一次都加到尾部,每一次都从尾部取出
    private static class MyStack2<T> {
        private NodeDob<T> head;
        private NodeDob<T> last;
        private int size;

        public void add(T value) {
            NodeDob<T> nodeDob = new NodeDob<T>(value, null, null);
            if (isEmpty()) {
                head = nodeDob;
                last = nodeDob;
                size++;
                return;
            }
            size++;
            nodeDob.setPrev(last);
            last.setNext(nodeDob);
            last = nodeDob;

        }

        public boolean isEmpty() {
            return size == 0;
        }

        public T peek() {
            if (isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            return last.getValue();
        }

        public T pop() {
            if (isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            size--;
            NodeDob<T> cur = last;
            last = last.getPrev();
            T result = cur.getValue();
            cur.setPrev(null);
            if (last != null) {
                last.setNext(null);
            }
            return result;
        }

        public String toSting() {
            if (isEmpty()) {
                return "";
            }
            StringBuilder sb = new StringBuilder("[");
            NodeDob<T> cur = head;
            while (cur != null) {
                sb.append(cur.getValue());
                sb.append(" ");
                cur = cur.getNext();
            }
            sb.delete(sb.length() - 1, sb.length());
            sb.append("]");
            return sb.toString();
        }
    }

    //用单方向链表 每一次添加元素都会放在头部，每一次弹出都从头部弹出
    private static class MyStack3<T> {
        private Node<T> head;
        private int size;

        public void add(T value) {
            Node<T> nodeDob = new Node<T>(value, null);
            if (isEmpty()) {
                head = nodeDob;
                size++;
                return;
            }
            size++;
            nodeDob.setNext(head);
            head = nodeDob;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public T peek() {
            if (isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            return head.getValue();
        }

        public T pop() {
            if (isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            size--;
            Node<T> cur = head;
            T result = cur.getValue();
            head = head.getNext();
            return result;
        }

        public String toSting() {
            if (isEmpty()) {
                return "";
            }
            StringBuilder sb = new StringBuilder("[");
            Node<T> cur = reverse(head);
            Node<T> h = cur;
            while (cur != null) {
                sb.append(cur.getValue());
                sb.append(" ");
                cur = cur.getNext();
            }
            sb.delete(sb.length() - 1, sb.length());
            sb.append("]");
            head = reverse(h);
            return sb.toString();
        }

        private Node<T> reverse(Node<T> head) {
            Node<T> cur = null;
            while (head != null) {
                Node<T> next = head.getNext();
                head.setNext(cur);
                cur = head;
                head = next;
            }
            return cur;
        }
    }

    //用单向链表实现队列
    private static class MyQueue<T> {
        private Node<T> head;
        private Node<T> last;
        private int size;

        public void add(T value) {
            Node<T> nodeDob = new Node<T>(value, null);
            if (isEmpty()) {
                head = nodeDob;
                last = nodeDob;
                size++;
                return;
            }
            size++;
            nodeDob.setNext(head);
            head = nodeDob;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public T peek() {
            if (isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            return last.getValue();
        }

        public T pop() {
            if (isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            size--;
            T result;
            if (size == 0) {
                result = head.getValue();
                head = last = null;
                return result;
            }
            Node<T> cur = head;
            for (int i = 0; i < size - 1; i++) {
                cur = cur.getNext();
            }
            last = cur;
            result = cur.getNext().getValue();
            last.setNext(null);
            return result;
        }

        public String toSting() {
            if (isEmpty()) {
                return "";
            }
            StringBuilder sb = new StringBuilder("[");
            Node<T> cur = reverse(head);
            Node<T> h = cur;
            while (cur != null) {
                sb.append(cur.getValue());
                sb.append(" ");
                cur = cur.getNext();
            }
            sb.delete(sb.length() - 1, sb.length());
            sb.append("]");
            head = reverse(h);
            return sb.toString();
        }

        private Node<T> reverse(Node<T> head) {
            Node<T> cur = null;
            while (head != null) {
                Node<T> next = head.getNext();
                head.setNext(cur);
                cur = head;
                head = next;
            }
            return cur;
        }
    }


    public static void main(String[] args) {
        MyQueue<Integer> stack = new MyQueue<Integer>();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);
        System.out.println(stack.peek());
        System.out.println(stack.toSting());
        System.out.println(stack.size);
        System.out.println(stack.head);
        System.out.println(stack.last);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
//            System.out.println(stack.peek() + "---->" + stack.size);
        }
        System.out.println(stack.last);
        System.out.println(stack.head);
    }
}

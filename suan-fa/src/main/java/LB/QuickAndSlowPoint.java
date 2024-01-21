package LB;

import po.Node;

//快慢指针问题
public class QuickAndSlowPoint {

    //输入链表头节点,奇数长度返回中点,偶数长度返回上中点
    public static Node getCenterNode1(Node h) {
        while (h == null || h.getNext() == null) {
            return h;
        }
        Node slow = h;
        Node quick = slow.getNext();
        while (quick != null && quick.getNext() != null) {
            slow = slow.getNext();
            quick = quick.getNext().getNext();
        }
        return slow;
    }

    //输入链表头节点,奇数长度返回中点,偶数长度返回下中点
    public static Node getCenterNode2(Node h) {
        while (h == null || h.getNext() == null) {
            return h;
        }
        Node slow = h;
        Node quick = slow;
        while (quick != null && quick.getNext() != null) {
            slow = slow.getNext();
            quick = quick.getNext().getNext();
        }
        return slow;
    }

    //输入链表头节点,奇数长度返回中点前一个,偶数长度返回下中点前一个
    public static Node getCenterNode3(Node h) {
        while (h == null || h.getNext() == null || h.getNext().getNext() == null) {
            return h;
        }
        Node slow = h;
        Node quick = slow.getNext().getNext();
        while (quick != null && quick.getNext() != null) {
            slow = slow.getNext();
            quick = quick.getNext().getNext();
        }
        return slow;
    }

    //输入链表头节点,奇数长度返回中点前一个,偶数长度返回上中点前一个
    public static Node getCenterNode4(Node h) {
        while (h == null || h.getNext() == null || h.getNext().getNext() == null) {
            return h;
        }
        Node slow = h;
        Node quick = slow.getNext().getNext().getNext();
        while (quick != null && quick.getNext() != null) {
            slow = slow.getNext();
            quick = quick.getNext().getNext();
        }
        return slow;
    }

    public static void main(String[] args) {
        Node h = new Node(1);
        Node h1 = new Node(2);
        Node h2 = new Node(3);
        Node h3 = new Node(4);
        Node h4 = new Node(5);
        Node h5 = new Node(6);
        Node h6 = new Node(7);
        h.setNext(h1);
        h1.setNext(h2);
        h2.setNext(h3);
        h3.setNext(h4);
//        h4.setNext(h5);
//        h5.setNext(h6);
//        System.out.println(h);
        System.out.println(getCenterNode4(h));
        System.out.println(getCenterNode1(h));
    }
}

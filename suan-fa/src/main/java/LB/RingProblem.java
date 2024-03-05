package LB;

import po.Node;

import java.util.HashSet;
import java.util.Set;

//环形相遇问题
public class RingProblem {


    /**
     * 快慢指针 快指针 一次走两步 慢指针 一次一步
     * 如果快指针 走完没有null 说明成环
     * 当两个指针相遇时  让快指针从头开始走 再次相遇就是交点
     */
    public static Node getRingNode(Node h) {
        if (h == null || h.getNext() == null || h.getNext().getNext() == null) {
            return null;
        }
        //刚开始 快慢指针分别走一步 防止第一步的时候和判断条件冲突
        Node slow = h.getNext();
        Node fast = slow.getNext();
        while (slow != fast) {
            if (fast.getNext() == null || fast.getNext().getNext() == null) {
                return null;
            }
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        fast = h;
        while (fast != slow) {
            fast = fast.getNext();
            slow = slow.getNext();
        }
        return fast;
    }

    //找到第一个相交的点
    public static Node getRingNode1(Node h) {
        if (h == null) {
            return null;
        }
        Set<Node> set = new HashSet<Node>();
        while (h != null) {
            if (set.contains(h)) {
                return h;
            }
            set.add(h);
            h = h.getNext();
        }
        return null;
    }

    /**
     * 给定两个可能有环也可能无环的链表，头节点head1 head2
     * 如果两个链表相交请返回第一个节点 没有返回null
     * <p>
     * 如果一个有环 一个无环 这种情况没有交点
     */

    public static Node isLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getRingNode(head1);
        Node loop2 = getRingNode(head2);

        //两个都无环
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        //两个都有环
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, head2, loop1, loop2);
        }
        return null;
    }

    /**
     * 两个都成环
     * 三种情况
     * 当loop1 != loop2 有两种情况
     * 不相交
     * 相交点在环上
     * 这两种情况  从loop1开始找 如果再次回到loop1的位置都没有碰到loop2 就不相交
     * 如果碰到了 返回loop1 或 loop2都可以
     * 第三种情况 相交点不在环上 loop1 == loop2
     * 可以看作时 两个都是以 loop结尾的链表找相交点 可以用都不成环的方式处理
     */
    private static Node bothLoop(Node head1, Node head2, Node loop1, Node loop2) {
        if (loop1 != loop2) {
            Node cur = loop1.getNext();
            while (cur != loop1) {
                if (cur == loop2) {
                    return loop1;
                }
                cur = cur.getNext();
            }
            return null;
        }

        Node cur1 = head1;
        Node cur2 = head2;

        int n = 0;
        while (cur1 != loop1) {
            cur1 = cur1.getNext();
            n++;
        }

        while (cur2 != loop1) {
            n--;
            cur2 = cur2.getNext();
        }
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n > 0) {
            cur1 = cur1.getNext();
            n--;
        }
        while (cur1 != cur2) {
            cur1 = cur1.getNext();
            cur2 = cur2.getNext();
        }
        return cur1;
    }

    /**
     * 两个都无环
     * 先统计链表的长度 然后让长的链表先走 走到和短的链表一样长
     * 然后判断 两个链表的值是否相等 相等的位置就是相交点
     * 假设两个链表不相交 相交点就是null  也是正确的
     */
    private static Node noLoop(Node head1, Node head2) {
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while (cur1.getNext() != null) {
            n++;
            cur1 = cur1.getNext();
        }
        while (cur2.getNext() != null) {
            n--;
            cur2 = cur2.getNext();
        }
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n > 0) {
            cur1 = cur1.getNext();
            n--;
        }
        while (cur1 != cur2) {
            cur1 = cur1.getNext();
            cur2 = cur2.getNext();
        }
        return cur1;
    }

    public static void main(String[] args) {
        Node h = new Node(1);
        Node h1 = new Node(2);
        Node h2 = new Node(3);
        Node h3 = new Node(4);
        Node h4 = new Node(5);
        Node h5 = new Node(6);
        Node h6 = new Node(7);
        Node h7 = new Node(7);
        Node h8 = new Node(8);
        Node h9 = new Node(9);
        h.setNext(h1);
        h1.setNext(h2);
        h2.setNext(h3);
        h3.setNext(h4);
        h4.setNext(h5);
        h9.setNext(h8);
        h8.setNext(h7);
        h7.setNext(h6);
        h5.setNext(h3);
        h6.setNext(h1);
//        h6.setNext(h2);
//        h5.setNext(h6);
//        h6.setNext(h3);
//        System.out.println(he);
        System.out.println(getRingNode1(h9));
        System.out.println(getRingNode(h));
        System.out.println(isLoop(h, h9));
    }

}

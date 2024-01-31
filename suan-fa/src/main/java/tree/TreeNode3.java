package tree;

import po.STreeNode;
import po.TreeNode;
import util.TreeUtil;

/**
 * 树 节点右三个指针 左 右 父 告诉其中一个节点 返回后继节点--中序打印的下一个节点
 * 前驱节点 中序的前一个节点
 */
public class TreeNode3 {


    /**
     * 树 节点右三个指针 左 右 父 告诉其中一个节点 返回后继节点--中序打印的下一个节点
     * 当前节点如果是头节点--如果他有右节点 那么后继节点就是他的右节点的最左孩子
     * 如果当前节点是头节点的左孩子 那么 后继节点就是他的头节点
     * 如果当前节点是头节点的右孩子 那么他的后继节点就是 往上面找他的父节点 如果他的父节点是
     * 他的父节点的左孩子 那么 父节点的父节点就是后继节点
     * <p>
     * 因为当前节点是父节点右孩子说明 他的父节点已经打印过来 该打印自己--右孩子
     * 如果父节点是他的父节点的左孩子 ---按照顺序该打印父节的父节点了
     * 如果父节点是他的父节点的右孩子---和自己节点场景一致 需要父节点是他的父节点的左孩子
     */
    public static STreeNode getAfterNode(STreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.getRight() != null) {
            node = node.getRight();
            while (node != null && node.getLeft() != null) {
                node = node.getLeft();
            }
            return node;
        } else if (node.getParent() != null) {
            STreeNode parent = node.getParent();
            if (parent.getLeft() == node) {
                return parent;
            } else {
                while (parent != null && parent.getParent() != null && parent == parent.getParent().getRight()) {
                    parent = parent.getParent();
                }
                return parent == null ? null : parent.getParent();
            }
        }

        return null;
    }

    /**
     * 树 节点右三个指针 左 右 父 告诉其中一个节点 返回中继节点--中序打印的前一个节点
     * 如果我是头节点 我的前一节就是我的左孩子的最右节点
     * 如果我是左孩子 那么我的前一节点就是我的父节点 如果是他父节点的右孩子 那么 前一节点
     * 就是我的父节点的父节点
     * 如果我是右孩子 没有左孩子  就是我的头节点
     * 有左孩子那么我的前一节点就是我的左孩子的最右节点
     */
    public static STreeNode getBeforeNode(STreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.getLeft() != null) {
            node = node.getLeft();
            while (node!=null && node.getRight() != null){
                node = node.getRight();
            }
            return node;
        } else if (node.getParent() != null) {
            STreeNode parent = node.getParent();
            if (parent.getRight() == node) {
                if (node.getLeft() == null) {
                    return parent;
                } else {
                    node = node.getLeft();
                    while (node != null && node.getRight() != null) {
                        node = node.getRight();
                        if (node.getLeft() != null && node.getRight() == null) {
                            node = node.getLeft();
                        }
                    }
                    return node;
                }
            } else {
                while (parent != null && parent.getParent() != null && parent.getParent().getLeft() == parent) {
                    parent = parent.getParent();
                }
                return parent == null ? null : parent.getParent();
            }


        }
        return null;
    }

    public static void main(String[] args) {
        STreeNode node1 = new STreeNode(1);
        STreeNode node2 = new STreeNode(2);
        STreeNode node3 = new STreeNode(3);
        STreeNode node4 = new STreeNode(4);
        STreeNode node5 = new STreeNode(5);
        STreeNode node6 = new STreeNode(6);
        STreeNode node7 = new STreeNode(7);
        STreeNode node8 = new STreeNode(8);
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setParent(node1);
        node3.setParent(node1);
        node2.setLeft(node4);
        node4.setParent(node2);
        node3.setRight(node5);
        node5.setParent(node3);
        node4.setLeft(node6);
        node4.setRight(node7);
        node5.setLeft(node8);
        node8.setParent(node5);
        node6.setParent(node4);
        node7.setParent(node4);
//        zS(node1);
//        System.out.println();
//        System.out.println(node8);
//        System.out.println(getBeforeNode(node8));
        STreeNode randomTree = getRandomTree();
        STreeNode node = getRandomNode(randomTree, 5, 0);
        zS(randomTree);
        System.out.println();
        System.out.println(node);
        System.out.println(getAfterNode(node));
        System.out.println(getBeforeNode(node));
    }

    public static STreeNode getRandomTree() {
        TreeNode<Integer> head = TreeUtil.getRandomTree(30, 100);
        return xsT(head);
    }

    public static STreeNode xsT(TreeNode<Integer> head) {
        if (head == null) {
            return null;
        }
        STreeNode h = new STreeNode(head.getValue());
        STreeNode left = xsT(head.getLeft());
        STreeNode right = xsT(head.getRight());
        h.setLeft(left);
        h.setRight(right);
        if (left != null) {
            left.setParent(h);
        }
        if (right != null) {
            right.setParent(h);
        }
        return h;
    }

    public static STreeNode getRandomNode(STreeNode head, int count, int cur) {
        if (head == null) {
            return null;
        }
        if (cur == count) {
            return head;
        }
        boolean left = (int) (Math.random() * 10) < 5;
        if (left && head.getLeft() != null) {
            return getRandomNode(head.getLeft(), count, ++cur);
        } else if (head.getRight() != null) {
            return getRandomNode(head.getRight(), count, ++cur);
        }
        return getRandomNode(head.getParent(), count, ++cur);
    }

    public static void zS(STreeNode head) {
        if (head == null) {
            return;
        }
        zS(head.getLeft());
        System.out.print(head.getValue() + " ");
        zS(head.getRight());
    }

}

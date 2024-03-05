package tree;

import lombok.AllArgsConstructor;
import po.TreeNode;
import util.TreeUtil;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

//是不是完全二叉树
public class IsComTree {


    /**
     * 非递归
     * 如果一个节点有右孩子没有左孩子 那不是
     * 如果一个节点的 那么他后序的节点都是叶子节点
     */
    public static boolean isComTree1(TreeNode head) {
        if (head == null) {
            return true;
        }
        boolean isLeaf = false;
        Queue<TreeNode> queue = new LinkedBlockingQueue<TreeNode>();
        queue.add(head);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if ((isLeaf && (poll.getLeft() != null || poll.getRight() != null)) || (poll.getLeft() == null && poll.getRight() != null)) {
                return false;
            }


            if (poll.getLeft() == null || poll.getRight() == null) {
                isLeaf = true;
            }
            if (poll.getLeft() != null) {
                queue.add(poll.getLeft());
            }
            if (poll.getRight() != null) {
                queue.add(poll.getRight());
            }
        }
        return true;
    }

    public static boolean isComTree2(TreeNode head) {
        return process(head).isComTree;
    }

    private static InfoBo process(TreeNode head) {
        if (head == null) {
            return new InfoBo(true, true, 0);
        }
        InfoBo left = process(head.getLeft());
        InfoBo right = process(head.getRight());
        int height = Math.max(left.height, right.height) + 1;
        boolean isComTree = left.isFullTree && right.isFullTree && left.height == right.height;
        boolean isFullTree = left.isFullTree && right.isFullTree && left.height == right.height;
        if (
                (left.isComTree && right.isFullTree && left.height - right.height == 1) ||
                 (left.isFullTree && (right.isFullTree || right.isComTree) && left.height == right.height)
        ) {
            isComTree = true;
        }
        return new InfoBo(isComTree, isFullTree, height);
    }


    @AllArgsConstructor
    private static class InfoBo {
        private boolean isComTree;//是否平衡二叉树
        private boolean isFullTree;//是否满二叉树
        private int height;//高度
    }

    //87 423 118 608 360
    public static void main(String[] args) {
        for (int i = 0; i < 10000000; i++) {
            TreeNode<Integer> head = TreeUtil.getRandomTree(10, 1000);
            boolean comTree1 = isComTree1(head);
            boolean comTree2 = isComTree2(head);
            if (comTree1 != comTree2) {
                TreeNode1.zX(head);
                System.out.println();
                System.out.println("comTree1:" + comTree1);
                System.out.println("comTree2:" + comTree2);
                break;
            }
        }
    }
}

package tree;

import lombok.AllArgsConstructor;
import po.TreeNode;
import util.TreeUtil;

//我是搜索二叉树  最大的就是我
//我不是 最大的就是左边会右边
public class MaxSearchTree {

    public static void maxSearchTree(TreeNode head) {
        searchTreeBo process = process(head);
        System.out.println("node:" + process.node);
        System.out.println("counts:" + process.counts);
        System.out.println("isSearchTree:" + process.isSearchTree);
    }

    private static searchTreeBo process(TreeNode head) {
        if (head == null) {
            return null;
        }
        searchTreeBo left = process(head.getLeft());
        searchTreeBo right = process(head.getRight());
        boolean isSearch = false;
        int leftValue = (Integer) head.getValue();
        int rightValue = (Integer) head.getValue();
        int counts = 1;
        TreeNode node = null;
        if (left != null) {
            leftValue = Math.max(leftValue, left.leftValue);
            rightValue = Math.min(rightValue, left.rightValue);
            counts = Math.max(counts, left.counts);
            node = left.node;
        }
        if (right != null) {
            leftValue = Math.max(leftValue, right.leftValue);
            rightValue = Math.min(rightValue, right.rightValue);
            counts = Math.max(counts, right.counts);
            node = right.node;
        }
        if (right != null && left != null) {
            node = right.counts > left.counts ? right.node : left.node;
        }

        if ((left == null || left.isSearchTree) && (right == null || right.isSearchTree)
                && (left == null || left.leftValue < (Integer) head.getValue())
                && (right == null || right.rightValue > (Integer) head.getValue())
        ) {
            isSearch = true;
            counts = 1;
            if (left != null) {
                counts += left.counts;
            }
            if (right != null) {
                counts += right.counts;
            }
            node = head;
        }


        return new searchTreeBo(isSearch, node, counts, leftValue, rightValue);
    }

    @AllArgsConstructor
    private static class searchTreeBo {
        private boolean isSearchTree;
        private TreeNode node;
        private int counts;
        private int leftValue;
        private int rightValue;
    }

    public static void main(String[] args) {
        TreeNode<Integer> head = TreeUtil.getRandomTree(30, 1000);
        TreeNode1.xX(head);
        System.out.println();
        maxSearchTree(head);
    }

}

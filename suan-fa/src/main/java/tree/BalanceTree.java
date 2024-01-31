package tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import po.TreeNode;
import util.TreeUtil;

//平衡二叉树 判断一个树是否是平衡二叉树
//在一颗二叉树中每一颗子树的左树高度和右树的高度，高度差不超过1
public class BalanceTree {


    public static boolean isBalanceTree(TreeNode head) {
        return process(head).isBalance;
    }

    private static BalanceTreeT process(TreeNode head) {
        if (head == null) {
            return new BalanceTreeT(true, 0);
        }
        BalanceTreeT left = process(head.getLeft());
        BalanceTreeT right = process(head.getRight());
        int height = Math.max(left.height, right.height) + 1;
        boolean isBalance = left.isBalance && right.isBalance && Math.abs(right.height - left.height) <= 1;
        return new BalanceTreeT(isBalance, height);
    }

    public static void main(String[] args) {
        TreeNode<Integer> head = TreeUtil.getRandomTree(10, 100);
        TreeNode1.zX(head);
        System.out.println();
        System.out.println(isBalanceTree(head));
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class BalanceTreeT {
        private boolean isBalance;
        private int height;
    }
}

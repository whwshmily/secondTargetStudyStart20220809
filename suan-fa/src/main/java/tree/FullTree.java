package tree;

import lombok.AllArgsConstructor;
import po.TreeNode;

/**
 * 满二叉树
 */
public class FullTree {


    public static boolean isFullTree(TreeNode head) {
        FullTreeBo bo = process(head);
        return Math.pow(2, bo.height) - 1 == bo.counts;
    }

    private static FullTreeBo process(TreeNode head) {
        if (head == null) {
            return new FullTreeBo(0, 0);
        }
        FullTreeBo left = process(head.getLeft());
        FullTreeBo right = process(head.getRight());
        int counts = left.counts + right.counts + 1;
        int height = Math.max(left.height, right.height) + 1;
        return new FullTreeBo(counts, height);
    }


    @AllArgsConstructor
    private static class FullTreeBo {
        int counts;
        int height;
    }


}

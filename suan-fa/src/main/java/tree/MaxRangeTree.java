package tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import po.TreeNode;
import util.TreeUtil;

public class MaxRangeTree {


    public static void maxRangeTree(TreeNode head) {
        RangeTreeBo process = process(head);
        System.out.println("node:"+process.node);
        System.out.println("maxRange:"+process.maxRange);
    }


    private static RangeTreeBo process(TreeNode head) {
        if (head == null) {
            return new RangeTreeBo(null, 0, 0);
        }
        /**
         * 和我无关 左边和右边的高度最大的一个
         * 和我有关  左边的高度 +右边高度 + 1
         */
        RangeTreeBo left = process(head.getLeft());
        RangeTreeBo right = process(head.getRight());

        int maxRange = left.height + right.height + 1;
        int height = Math.max(left.height, right.height) + 1;
        TreeNode node = left.maxRange > right.maxRange ? left.node : right.node;

        if (Math.max(left.maxRange, right.maxRange) < maxRange) {
            node = head;
        } else {
            maxRange = Math.max(left.maxRange, right.maxRange);
        }

        return new RangeTreeBo(node, height, maxRange);
    }


    @Data
    @AllArgsConstructor
    private static class RangeTreeBo {
        private TreeNode node;
        private int height;
        private int maxRange;
    }

    public static void main(String[] args) {
        TreeNode<Integer> head = TreeUtil.getRandomTree(30, 1000);
        TreeNode1.xX(head);
        System.out.println();
        maxRangeTree(head);
    }

}

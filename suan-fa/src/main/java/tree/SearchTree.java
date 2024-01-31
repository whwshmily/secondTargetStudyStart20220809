package tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import po.TreeNode;
import util.TreeUtil;

/**
 * 搜索二叉树，左树都比我小 右树都比我大 没有重复 所有的子树都满足
 */
public class SearchTree {


    public static boolean isSearchTree(TreeNode head) {
        return process(head).isSearchTree;
    }

    public static SearchTreeBo process(TreeNode head) {
        if (head == null) {
            return new SearchTreeBo(true, null, null);
        }
        SearchTreeBo left = process(head.getLeft());
        SearchTreeBo right = process(head.getRight());
        int value = (Integer) head.getValue();
        boolean isSearchTree = left.isSearchTree && right.isSearchTree;
        int leftValue = value;
        int rightValue = value;
        if (left.leftMaxMinValue != null && value <= left.leftMaxMinValue) {
            isSearchTree = false;
        }
        if (right.rightMinMaxValue != null && value >= right.rightMinMaxValue) {
            isSearchTree = false;
        }
        //父节点如果作为左节点 他的最大值应该是左右两棵树的最大值
        //作为右节点 同理
        if (left.leftMaxMinValue != null) {
            leftValue = Math.max(leftValue, left.leftMaxMinValue);
            rightValue = Math.min(rightValue, left.leftMaxMinValue);
        }
        if (left.rightMinMaxValue != null) {
            leftValue = Math.max(leftValue, left.rightMinMaxValue);
            rightValue = Math.min(rightValue, left.rightMinMaxValue);
        }
        if (right.rightMinMaxValue != null) {
            rightValue = Math.min(rightValue, right.rightMinMaxValue);
            leftValue = Math.max(leftValue, right.rightMinMaxValue);
        }
        if (right.leftMaxMinValue != null) {
            rightValue = Math.min(rightValue, right.leftMaxMinValue);
            leftValue = Math.max(leftValue, right.leftMaxMinValue);
        }
//        System.out.println("left:" + left);
//        System.out.println("right:" + right);
//        System.out.println("isSearchTree:" + isSearchTree + " leftValue:" + leftValue + " rightValue:" + rightValue);
        return new SearchTreeBo(isSearchTree, leftValue, rightValue);
    }

    public static boolean isST(TreeNode head) {
        return doProcess(head).isSearchTree;
    }

    public static SearchTreeBo doProcess(TreeNode node) {
        if (node == null) {
            return null;
        }
        SearchTreeBo left = doProcess(node.getLeft());
        SearchTreeBo right = doProcess(node.getRight());
        boolean isSearchTree = true;

        int leftValue = (Integer) node.getValue();
        int rightValue = (Integer) node.getValue();
        if (left != null) {
            isSearchTree = left.isSearchTree;
            isSearchTree = (leftValue > left.leftMaxMinValue) && isSearchTree;

        }
        if (right != null) {
            isSearchTree = (rightValue < right.rightMinMaxValue) && isSearchTree;
            isSearchTree = isSearchTree && right.isSearchTree;
        }
        if (left != null) {
            leftValue = Math.max(leftValue, left.leftMaxMinValue);
            rightValue = Math.min(rightValue, left.rightMinMaxValue);
        }
        if (right != null) {
            leftValue = Math.max(leftValue, right.leftMaxMinValue);
            rightValue = Math.min(rightValue, right.rightMinMaxValue);
        }
//        System.out.println("isSearch:" + isSearchTree + " left:" + leftValue + " right:" + rightValue + " value:" + node.getValue());
        return new SearchTreeBo(isSearchTree, leftValue, rightValue);

    }

    @AllArgsConstructor
    @Data
    private static class SearchTreeBo {
        private boolean isSearchTree;
        private Integer leftMaxMinValue;
        private Integer rightMinMaxValue;
    }

    public static void main(String[] args) {
//        TreeNode head = new TreeNode(50);
//        TreeNode node1 = new TreeNode(19);
//        TreeNode node2 = new TreeNode(33);
//        TreeNode node3 = new TreeNode(40);
//        TreeNode node4 = new TreeNode(10);
//        TreeNode node5 = new TreeNode(91);
//        head.setLeft(node2);
//        head.setRight(node5);
//        node2.setLeft(node1);
//        node5.setLeft(node4);
//        node2.setRight(node3);
//        TreeNode1.zX(head);
//        System.out.println();
//        System.out.println(isST(head));

//        TreeNode head = new TreeNode(50);
//        TreeNode node1 = new TreeNode(4);
//        TreeNode node2 = new TreeNode(35);
//        TreeNode node3 = new TreeNode(60);
//        TreeNode node4 = new TreeNode(51);
//        head.setLeft(node1);
////        node1.setRight(node2);
//        head.setRight(node4);
//        node4.setLeft(node2);
//        node4.setRight(node3);
//        TreeNode1.zX(head);
//        System.out.println();
//        System.out.println(isST(head));

        for (int i = 0; i < 10000000; i++) {
            TreeNode<Integer> head = TreeUtil.getRandomTree(15, 100);
            boolean searchTree = isSearchTree(head);
            boolean validBST = isValidBST(head);
            boolean result = isST(head);
//            boolean result = true;
            if (searchTree != validBST || searchTree != result) {
                TreeNode1.zX(head);
                System.out.println();
                System.out.println(searchTree);
                System.out.println(validBST);
                System.out.println(result);
                break;
            }
        }

    }


    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean isValidBST(TreeNode node, int lower, int upper) {
        if (node == null) {
            return true;
        }
        if ((Integer) node.getValue() <= lower || (Integer) node.getValue() >= upper) {
            return false;
        }
        return isValidBST(node.getLeft(), lower, (Integer) node.getValue()) && isValidBST(node.getRight(), (Integer) node.getValue(), upper);
    }
}

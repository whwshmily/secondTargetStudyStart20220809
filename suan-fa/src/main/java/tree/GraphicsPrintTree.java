package tree;

import po.TreeNode;
import util.TreeUtil;

/**
 * 如何打印一棵树 ---图形化 输出
 */
public class GraphicsPrintTree {


    public static void graphicsPrintTree(TreeNode head, int height, String d, int width) {
        if (head == null) {
            return;
        }
        graphicsPrintTree(head.getLeft(), height + 1, "D", width);
        String content = d + head.getValue() + d;
        int leftLength = (width - content.length()) / 2;
        String value = printSpace(leftLength) + content + printSpace(width - content.length() - leftLength);
        System.out.println(printSpace(height * width) + value);

        graphicsPrintTree(head.getRight(), height + 1, "U", width);
    }


    private static String printSpace(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        TreeNode<Integer> head = TreeUtil.getRandomTree(10, 100);
        TreeNode1.zX(head);
        System.out.println();
        System.out.println("print tree");
        graphicsPrintTree(head, 0, "H", 17);
    }
}

package tree;

import po.TreeNode;

/**
 * 二叉树的顺序 ---递归序
 * 打印一颗二叉树的先序 中序 后序
 * 先序 任何一颗树 子树 都是按照 头 左 右 的顺序输出
 * 中序 左 头 右
 * 后序 左 右 头
 * <p>
 * 每一个节点都会经过三次
 * 当第一次经过这个节点就打印  先序
 * 当第二次经过这个节点就打印  中序
 * 当第三次经过这个节点就打印  后序
 */
public class TreeNode1 {

    //先序
    public static void xX(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.getValue() + " ");
        xX(head.getLeft());
        xX(head.getRight());
    }

    //中序
    public static void zX(TreeNode head) {
        if (head == null) {
            return;
        }
        zX(head.getLeft());
        System.out.print(head.getValue() + " ");
        zX(head.getRight());
    }

    //后序
    public static void hX(TreeNode head) {
        if (head == null) {
            return;
        }
        hX(head.getLeft());
        hX(head.getRight());
        System.out.print(head.getValue() + " ");
    }

}

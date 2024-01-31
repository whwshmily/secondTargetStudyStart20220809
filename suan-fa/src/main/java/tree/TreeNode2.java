package tree;

import po.TreeNode;

import java.util.Stack;

//非递归实现二叉树的先序 中序 和 后序
public class TreeNode2 {

    /**
     * 先序
     * 压栈进栈
     * 进入循环的时候 第一次直接打印
     * 然后看右孩子  有没有 有就压进去
     * 然后看左孩子  有就压进去
     * 然后就会先弹出左孩子  后弹出右孩子
     * 弹出来就顺序就是 头左右
     */
    public static void xX(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(head);
        while (!stack.isEmpty()) {

            TreeNode pop = stack.pop();
            System.out.print(pop.getValue() + " ");
            if (pop.getRight() != null) {
                stack.push(pop.getRight());
            }
            if (pop.getLeft() != null) {
                stack.push(pop.getLeft());
            }
        }
    }

    /**
     * 这次先加入左孩子 后加入右孩子
     * 这样的顺序 就是 头 右 左
     * 这次不打印 按照顺序加入到另一个栈中
     * 然后依次弹出 就是 后序  左 右 头
     */
    public static void hX(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
        stack1.push(head);
        while (!stack1.isEmpty()) {
            TreeNode pop = stack1.pop();
            stack2.push(pop);
            if (pop.getLeft() != null) {
                stack1.push(pop.getLeft());
            }
            if (pop.getRight() != null) {
                stack1.push(pop.getRight());
            }
        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().getValue() + " ");
        }
    }

    /**
     * 后序
     * 先把头节点 加入到栈中  然后进入循环
     * 然后每一次 先把栈中的节点看一次
     * c是当前要处理的节点  h是上一次处理的节点
     * 先让 c  = stack.peek() 第一次弹出的时候 c=h
     * 然后判断c的左孩子不为空 就先处理左孩子
     * 右孩子不为空就 在处理右孩子
     * 最后处理当前节点 头节点
     * <p>
     * 第一次处理节点 头节点
     * 会先看一下头节点 然后看他有没有左孩子
     */
    public static void hX1(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(head);
        TreeNode c = null;
        while (!stack.isEmpty()) {
            c = stack.peek();
            /**
             * 先看一下有没有左孩子 上一次处理的是不是左孩子 和 右孩子 如果处理过了右孩子
             * 说明左孩子也处理了
             */
            if (c.getLeft() != null && head != c.getLeft() && head != c.getRight()) {
                stack.push(c.getLeft());
                //判断右孩子不为空 不为空就处理右孩子
            } else if (c.getRight() != null && head != c.getRight()) {
                stack.push(c.getRight());
                //处理当前节点 头节点
            } else {
                System.out.print(stack.pop().getValue() + " ");
                head = c;
            }
        }
    }

    /**
     * 先处理头节点 然后一直找他的左节点  找到的就加入栈中
     * 找不到下一个左节点 说明当前节点是头节点
     * 直接输出 然后  再去处理一下右节点
     */
    public static void zX(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> nodes = new Stack<TreeNode>();
        while (!nodes.isEmpty() || head != null) {
            if (head != null) {
                nodes.push(head);
                head = head.getLeft();
            } else {
                TreeNode pop = nodes.pop();
                System.out.print(pop.getValue() + " ");
                head = pop.getRight();
            }
        }
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(7);
        node.setLeft(node4);
        node.setRight(node3);
        node3.setRight(node1);
        node4.setLeft(node2);
        node1.setLeft(node5);
        node2.setRight(node6);
        hX(node);
        System.out.println();
        TreeNode1.hX(node);
        System.out.println();
        hX1(node);
        System.out.println();
        zX(node);
        System.out.println();
        TreeNode1.zX(node);
    }

}

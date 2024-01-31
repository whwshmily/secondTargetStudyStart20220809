package tree;

import po.TreeNode;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

//按层级打印二叉树
public class LayerTree {


    public static void layerPrintTree(TreeNode head){
        if(head == null){
            return;
        }
        Queue<TreeNode> queue = new LinkedBlockingQueue<TreeNode>();
        queue.add(head);
        while (!queue.isEmpty()){
            TreeNode poll = queue.poll();
            System.out.print(poll.getValue()+" ");
            if(poll.getLeft() != null){
                queue.add(poll.getLeft());
            }
            if(poll.getRight() != null){
                queue.add(poll.getRight());
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
        layerPrintTree(node);
    }


}

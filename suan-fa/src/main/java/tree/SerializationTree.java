package tree;

import po.TreeNode;
import util.TreeUtil;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

//二叉树的序列化和反序列化
public class SerializationTree {


    //先序的序列化
    public static void xST(TreeNode head, Queue<String> nodes) {
        if (head == null) {
            nodes.add("null");
            return;
        }
        nodes.add(String.valueOf(head.getValue()));
        xST(head.getLeft(),nodes);
        xST(head.getRight(),nodes);
    }

    //先序的反序列化
    public static TreeNode fXST(Queue<String> nodes) {
        if(nodes.isEmpty()){
            return null;
        }
        String value = nodes.poll();
        if("null".equals(value)){
            return null;
        }
        TreeNode head = new TreeNode(Integer.parseInt(value));
        TreeNode leftNode = fXST(nodes);
        TreeNode rightNode = fXST(nodes);
        head.setLeft(leftNode);
        head.setRight(rightNode);
        return head;
    }

    //中序的序列化
    public static void zST(TreeNode head, Queue<String> nodes) {
        if (head == null) {
            nodes.add("null");
            return;
        }
        xST(head.getLeft(),nodes);
        nodes.add(String.valueOf(head.getValue()));
        xST(head.getRight(),nodes);
    }
    //中序的反序列化
    public static TreeNode fZST(Queue<String> nodes) {
        if(nodes.isEmpty()){
            return null;
        }
        TreeNode leftNode = fXST(nodes);
        String value = nodes.poll();
        if("null".equals(value)) {
            return null;
        }
        TreeNode head = new TreeNode(Integer.parseInt(value));
        TreeNode rightNode = fXST(nodes);
        head.setLeft(leftNode);
        head.setRight(rightNode);
        return head;
    }

    //后序的序列化
    public static void hST(TreeNode head, Queue<String> nodes) {
        if (head == null) {
            nodes.add("null");
            return;
        }
        xST(head.getLeft(),nodes);
        xST(head.getRight(),nodes);
        nodes.add(String.valueOf(head.getValue()));
    }
    //后序的反序列化
    public static TreeNode fHST(Queue<String> nodes) {
        if(nodes.isEmpty()){
            return null;
        }
        TreeNode leftNode = fXST(nodes);
        TreeNode rightNode = fXST(nodes);
        String value = nodes.poll();
        if("null".equals(value)) {
            return null;
        }
        TreeNode head = new TreeNode(Integer.parseInt(value));
        head.setLeft(leftNode);
        head.setRight(rightNode);
        return head;
    }


    public static void main(String[] args) {
        TreeNode<Integer> head = TreeUtil.getRandomTree(30, 100);
        Queue<String> nodes = new LinkedBlockingQueue<String>();
        hST(head,nodes);
        TreeNode node = fHST(nodes);
        TreeNode1.hX(head);
        System.out.println();
        TreeNode1.hX(node);
    }

}

package util;

import po.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {


    //节点的个数 节点的最大值
    public static TreeNode<Integer> getRandomTree(int nodeCount, int maxValue) {
        if (nodeCount <= 0) {
            return null;
        }
        if (nodeCount == 1) {
            return new TreeNode<Integer>((int) (Math.random() * maxValue));
        }
        int length = (int) (Math.random() * nodeCount);
        List<TreeNode<Integer>> nodes = new ArrayList<TreeNode<Integer>>();
        nodes.add(new TreeNode<Integer>((int) (Math.random() * maxValue)));
        for (int i = 1; i < length; i++) {
            TreeNode<Integer> node = getRandomNode(nodes, i);
            int value = (int) (Math.random() * maxValue);
            if(node.getLeft() == null){
                TreeNode<Integer> leftNode = new TreeNode<Integer>(value);
                nodes.add(leftNode);
                node.setLeft(leftNode);
                continue;
            }
            if(node.getRight() == null){
                TreeNode<Integer> rightNode = new TreeNode<Integer>(value);
                nodes.add(rightNode);
                node.setRight(rightNode);
            }
        }
        return nodes.get(0);
    }

    public static TreeNode<Integer> getRandomNode(List<TreeNode<Integer>> nodes, int index) {
        if (nodes == null || nodes.size() == 0 || index <= 0) {
            return null;
        }
        int randomIndex = (int) (Math.random() * index);
        TreeNode<Integer> node = nodes.get(randomIndex);
        if(node.getRight() != null && node.getLeft() != null){
            node = getRandomNode(nodes,index);
        }
        return node;
    }

}

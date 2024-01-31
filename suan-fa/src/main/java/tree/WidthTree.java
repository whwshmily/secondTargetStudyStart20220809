package tree;

import po.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 找到树最宽那一层有多少节点 最大宽度
 */
public class WidthTree {

    /**
     * 因为是往队列里面添加 按照先进先出
     * 所以当curlevel 和当前节点的level不相等的时候 这一层的个数就统计好了
     */
    public static int maxLevelLayer(TreeNode head) {
        if (head == null) {
            return 0;
        }
        Queue<TreeNode> nodes = new LinkedBlockingQueue<TreeNode>();
        nodes.add(head);
        Map<TreeNode, Integer> levelMap = new HashMap<TreeNode, Integer>();
        levelMap.put(head, 1);
        int curLevel = 1;
        int max = 0;
        int curLevelCount = 0;
        while (!nodes.isEmpty()) {
            TreeNode curNode = nodes.poll();
            int level = levelMap.get(curNode);
            if (curNode.getLeft() != null) {
                levelMap.put(curNode.getLeft(), level + 1);
                nodes.add(curNode.getLeft());
            }
            if (curNode.getRight() != null) {
                levelMap.put(curNode.getRight(), level + 1);
                nodes.add(curNode.getRight());
            }
            if (level == curLevel) {
                curLevelCount++;
            } else {
                max = Math.max(max, curLevelCount);
                curLevelCount = 1;
                curLevel++;
            }
        }
        //最后一个节点没有计算这个 所以要取两者的最大值
        return Math.max(max, curLevelCount);
    }

    public static int maxLevelLayer1(TreeNode head) {
        if (head == null) {
            return 0;
        }
        Queue<TreeNode> nodes = new LinkedBlockingQueue<TreeNode>();
        nodes.add(head);
        TreeNode curEnd = head;
        TreeNode nextEnd = null;
        int max = 0;
        int curLevelCount = 0;
        while (!nodes.isEmpty()) {
            TreeNode curNode = nodes.poll();
            if (curNode.getLeft() != null) {
                nodes.add(curNode.getLeft());
                nextEnd = curNode.getLeft();
            }
            if (curNode.getRight() != null) {
                nodes.add(curNode.getRight());
                nextEnd = curNode.getRight();
            }
            if (curNode != curEnd) {
                curLevelCount++;
            } else {
                max = Math.max(max, ++curLevelCount);
                curEnd = nextEnd;
                curLevelCount = 0;
            }
        }
        return max;
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
        System.out.println(maxLevelLayer(node));
        System.out.println(maxLevelLayer1(node));
    }
}

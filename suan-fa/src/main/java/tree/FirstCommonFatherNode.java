package tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import po.TreeNode;
import util.TreeUtil;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

//给定一个二叉树的头节点，和另外两个节点 返回  a和b最低公共祖先 往上面找最先遇到的父类
public class FirstCommonFatherNode {

    public static TreeNode firstCommonFatherNode1(TreeNode head, TreeNode a, TreeNode b) {
        if (head == null || a == null || b == null) {
            return null;
        }
        Map<TreeNode, TreeNode> map = new HashMap<TreeNode, TreeNode>();
        Queue<TreeNode> queue = new LinkedBlockingQueue<TreeNode>();
        queue.add(head);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            TreeNode left = poll.getLeft();
            TreeNode right = poll.getRight();
            if (left != null) {
                map.put(left, poll);
                queue.add(left);
            }
            if (right != null) {
                map.put(right, poll);
                queue.add(right);
            }
        }
        //如果是头节点 集合里面没东西的 怎么处理 或者节点就压根不在上面
        Set<TreeNode> set = new HashSet<TreeNode>();
        TreeNode node = a;
        while (node != null) {
            set.add(node);
            node = map.get(node);
        }
//        System.out.println("map:" + map);
        node = b;
        while (!set.contains(node) && node != null) {
            node = map.get(node);
        }
        return node;
    }


    public static TreeNode firstCommonFatherNode2(TreeNode head, TreeNode a, TreeNode b) {
//        System.out.println("start----->");
//        System.out.println("head----->" + head);
//        System.out.println("a----->" + a);
//        System.out.println("b----->" + b);
        return process(head, a, b).node;
    }

    private static InfoBo process(TreeNode head, TreeNode a, TreeNode b) {
        if (head == null || a == null || b == null) {
            return new InfoBo(null, false, false);
        }
        InfoBo left = process(head.getLeft(), a, b);
        InfoBo right = process(head.getRight(), a, b);
//        System.out.println("left:" + left);
//        System.out.println("right:" + right);
//        System.out.println("a==head:" + (a == head));
//        System.out.println("b==head:" + (b == head));
//        System.out.println("head:" + head);
        InfoBo infoBo = new InfoBo(null, false, false);
        if (left.node != null) {
            infoBo.node = left.node;
            return infoBo;
        }

        if (right.node != null) {
            infoBo.node = right.node;
            return infoBo;
        }

        if (
                (left.isA && right.isB)
                        || (left.isB && right.isA)
                        || (left.isA && head == b)
                        || (left.isB && head == a)
                        || (right.isA && head == b)
                        || (right.isB && head == a)
                        || (head == a && head == b)
        ) {
//            System.out.println("head:--->" + head);
            infoBo.node = head;
            return infoBo;
        }
        infoBo.isA = (head == a) || left.isA || right.isA;
        infoBo.isB = (head == b) || right.isB || left.isB;
        return infoBo;
    }


    @AllArgsConstructor
    @Data
    private static class InfoBo {
        TreeNode node;
        boolean isA;
        boolean isB;
    }

    public static void main(String[] args) {
//        TreeNode head = new TreeNode(638);
//        TreeNode node1 = new TreeNode(914);
//        TreeNode node2 = new TreeNode(914);
//        TreeNode node3 = new TreeNode(100);
//        head.setLeft(node1);
//        head.setRight(node2);
//        TreeNode a = node1;
//        TreeNode b = node2;
//        TreeNode node11 = firstCommonFatherNode1(head, a, b);
//        TreeNode node12 = firstCommonFatherNode1(head, a, b);
//        System.out.println(head);
//        System.out.println(node11);
//        System.out.println(node12);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            TreeNode<Integer> head = TreeUtil.getRandomTree(10, 1000);
            List<TreeNode> list = TreeUtil.getNodeList(head);
            if (list.size() == 0) {
                continue;
            }
            int firstIndex = (int) (Math.random() * list.size());
            int secondIndex = (int) (Math.random() * list.size());
            while (secondIndex == firstIndex && list.size() > 1) {
                secondIndex = (int) (Math.random() * list.size());
            }
            TreeNode a = list.get(firstIndex);
            TreeNode b;
            if (secondIndex % 2 == 0) {
                b = new TreeNode(-1);
            } else {
                b = list.get(secondIndex);
            }
            TreeNode node1 = firstCommonFatherNode1(head, a, b);
            TreeNode node2 = firstCommonFatherNode2(head, a, b);
            if (node1 != node2) {
//                System.out.println(node1 == node2);
                System.out.println("--->:" + (a == b));
                System.out.println("head:" + head);
                System.out.println("a:" + a);
                System.out.println("b:" + b);
                System.out.println("node1:" + node1);
                System.out.println("node2:" + node2);
                break;
            }
            if (i % 9999 == 0) {
                System.out.println("i:" + i);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("end:" + (end - start) / 1000);
    }

}

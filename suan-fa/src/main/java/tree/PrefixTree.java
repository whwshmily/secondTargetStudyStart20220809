package tree;

import lombok.Data;
import po.PrefixNode;

//前缀树
@Data
public class PrefixTree {


    /**
     * 单个字符串中,字符从前到后的加到一颗多叉树上
     * 字符放在路上,节点上有专属的数据项 pass 和end
     * 所有样本都这样添加,没有路就新建,有路就复用
     * 沿途节点的pass值加1,每个字符串结束时来到的节点end值加1
     */

    private PrefixNode root = new PrefixNode();


    public void add(String target) {
        if (target == null || "".equals(target)) {
            return;
        }
        PrefixNode node = root;
        node.setPass(node.getPass() + 1);
        int index = 0;
        char[] chars = target.toCharArray();
        for (char aChar : chars) {
            index = aChar - 'a';
            if (node.getChildren()[index] == null) {
                node.getChildren()[index] = new PrefixNode();
            }
            node = node.getChildren()[index];
            node.setPass(node.getPass() + 1);
        }
        node.setEnd(node.getEnd() + 1);
    }

    public int getStringTimes(String values) {
        if (values == null || "".equals(values)) {
            return -1;
        }
        PrefixNode node = root;
        char[] chars = values.toCharArray();
        for (char aChar : chars) {
            if (node.getChildren()[aChar - 'a'] == null) {
                return 0;
            }
            node = node.getChildren()[aChar - 'a'];
        }
        return node.getEnd();
    }


    public static void main(String[] args) {
        PrefixTree head = new PrefixTree();
        head.add("abc");
        head.add("abcd");
        head.add("asjhkljk");
        head.add("hsjkjskjbalh");
        head.add("jkbnskjbngj");
        System.out.println(head.getStringTimes("abc"));
    }
}

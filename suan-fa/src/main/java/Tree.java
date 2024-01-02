import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tree {

    int value;
    Tree left;
    Tree right;

    public static void main(String[] args) {
        Tree head = new Tree();
        Tree left1 = new Tree();
        Tree left11 = new Tree();
        Tree left12 = new Tree();
        Tree right1 = new Tree();
        Tree right11 = new Tree();
        Tree right12 = new Tree();
        head.value = 0;
        head.left = left1;
        head.right = right1;
        left1.value = 1;
        left1.left = left11;
        left1.right = left12;
        left11.value = 3;
        left12.value = 4;
        right1.value = 2;
        right1.left = right11;
        right1.right = right12;
        right11.value = 5;
        right12.value = 6;
        printTree(head);
    }



    private static void printTree(Tree head){
        if(head == null){
            return;
        }
        //先序 头左右
        System.out.print(head.value+" ");
        printTree(head.left);
        //中序 左 头 右
        System.out.print(head.value+" ");
        printTree(head.right);
        //后序 左右头
        System.out.print(head.value+" ");
    }


}

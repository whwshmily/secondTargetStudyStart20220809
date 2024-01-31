package po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeNode<T> {

    private T value;

    private TreeNode<T> left;

    private TreeNode<T> right;

    public TreeNode(T value) {
        this.value = value;
    }


}

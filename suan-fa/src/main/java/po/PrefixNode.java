package po;

import lombok.Data;

//前缀树
@Data
public class PrefixNode {

    private PrefixNode[] children = new PrefixNode[26];

    private int pass;

    private int end;

}

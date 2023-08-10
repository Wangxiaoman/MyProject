package leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个 N 叉树，返回其节点值的前序遍历。
 * @author xiaomanwang
 */

public class PreorderSolution {
    public List<Integer> preorder(UndirectedGraphNode root) {
        // write your code here
        List<Integer> result = new ArrayList<>();
        if(root != null) {
            result.add(root.label);
        }
        if(root.neighbors == null) {
            return result;
        }
        for(UndirectedGraphNode nb : root.neighbors) {
            result.addAll(preorder(nb));
        }
        return result;
    }
}


class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;

    UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}

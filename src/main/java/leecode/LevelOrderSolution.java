package leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaomanwang 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
 */
public class LevelOrderSolution {
    /**
     * @param root: the tree root
     * @return: the order level of this tree
     */
    public List<List<Integer>> levelOrder(UndirectedGraphNode root) {
        List<List<Integer>> result = new ArrayList<>();
        List<UndirectedGraphNode> levels = Arrays.asList(root);
        result.add(getLabels(levels));
        while (levels != null) {
            levels = getNextLevel(levels);
            if (levels != null && !levels.isEmpty()) {
                result.add(getLabels(levels));
            }
        }
        return result;
    }

    private List<Integer> getLabels(List<UndirectedGraphNode> nodes) {
        if (nodes != null) {
            return nodes.stream().filter(n -> n!=null).map(n -> n.label).collect(Collectors.toList());
        }
        return null;
    }

    public List<UndirectedGraphNode> getNextLevel(List<UndirectedGraphNode> levels) {
        if (levels == null || levels.isEmpty()) {
            return null;
        }
        List<UndirectedGraphNode> result = new ArrayList<>();
        for (UndirectedGraphNode node : levels) {
            if (node.neighbors != null) {
                result.addAll(node.neighbors);
            }
        }
        return result;
    }

    private static class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    }
}

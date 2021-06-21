package leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// reach-all-nodes
public class ReachAllNodes {
    
    public static Set<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        Set<Integer> fromInt = new HashSet<>();
        
        for(List<Integer> edge : edges) {
            fromInt.add(edge.get(0));
        }
        
        for(List<Integer> edge : edges) {
            fromInt.remove(edge.get(1));
        }
        
        return fromInt;
    }
    
    public static void main(String[] args) {
        //n = 6, edges = [[0,1],[0,2],[2,5],[3,4],[4,2]]
            
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(Arrays.asList(0,1));
        edges.add(Arrays.asList(0,2));
        edges.add(Arrays.asList(2,5));
        edges.add(Arrays.asList(3,4));
        edges.add(Arrays.asList(4,2));
        System.out.println(findSmallestSetOfVertices(6,edges));
    }
}

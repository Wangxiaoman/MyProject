package leecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

public class SortedDocument {
    /**
     * @param docs a list of documents
     * @return an inverted index
     */
    public Map<String, List<Integer>> invertedIndex(List<Document> docs) {
        // Write your code here
        Map<String, Set<Integer>> result = new HashMap<>();
        for(Document doc : docs) {
            String[] words = doc.content.split("\\s+");
            for(String word : words) {
                Set<Integer> ids = result.getOrDefault(word, new TreeSet<>());
                ids.add(doc.id);
                result.put(word, ids);
            }
        }
        Map<String,List<Integer>> r = new HashMap<>();
        for(Entry<String, Set<Integer>> entry : result.entrySet()) {
            r.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        return r;
    }
}


class Document {
    public int id;
    public String content;
}

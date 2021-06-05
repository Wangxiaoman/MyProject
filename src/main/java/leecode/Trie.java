package leecode;
// 208. 实现 Trie (前缀树)

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Trie {
    private TrieNode node;
    
    /** Initialize your data structure here. */
    public Trie() {}
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        if(node == null) {
            node = new TrieNode();
            TrieNode temp = node;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                TrieNode cnode = new TrieNode(c, i==(word.length()-1));
                List<TrieNode> ts = new ArrayList<>();
                ts.add(cnode);
                temp.setTs(ts);
                temp = cnode;
            }
        } else {
            TrieNode temp = node;
            for (int i = 0; i < word.length(); i++) {
                List<TrieNode> ts = temp.getTs();
                char c = word.charAt(i);
                
                if(ts != null) {
                    TrieNode nt = temp.contains(c);
                    if(nt != null) {
                        temp = nt;
                        if(i==(word.length()-1)) {
                            temp.setF(true);
                        }
                    }else {
                        TrieNode cnode = new TrieNode(c, i==(word.length()-1));
                        ts.add(cnode);
                        temp = cnode;
                    } 
                } else {
                    TrieNode cnode = new TrieNode(c, i==(word.length()-1));
                    ts = new ArrayList<>();
                    ts.add(cnode);
                    temp.setTs(ts);
                    temp = cnode;
                }
            }
        }
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if(node == null) {
            return false;
        }
        TrieNode temp = node;
        boolean finish = false;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(temp.getTs() != null) {
                TrieNode n = temp.contains(c);
                if(n != null) {
                    if(i == (word.length() - 1) && n.f) {
                        finish = true;
                    }
                    temp = n;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return finish;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if(node == null) {
            return false;
        }
        
        TrieNode temp = node;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(temp.getTs() != null) {
                TrieNode n = temp.contains(c);
                if(n != null) {
                    temp = n;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("app");
        trie.insert("apple");
        trie.insert("beer");
        trie.insert("add");
        trie.insert("jam");
        trie.insert("rental");

        System.out.println(trie.search("apps"));
        System.out.println(trie.search("app"));
        System.out.println(trie.search("ad"));
        System.out.println(trie.search("applepie"));
        System.out.println(trie.search("rest"));
        System.out.println(trie.search("jan"));
        System.out.println(trie.search("rent"));
        System.out.println(trie.search("beer"));
        System.out.println(trie.search("jam"));
        
    }
    
    private static class TrieNode {
        private char c;
        private boolean f ;
        private List<TrieNode> ts;
        
        public TrieNode() {
        }
        
        public TrieNode(char c, boolean f) {
            this.c = c;
            this.f = f;
        }
        
        public void setF(boolean f) {
            this.f = f;
        }
        
        public void setTs(List<TrieNode> ts) {
            this.ts = ts;
        }
        
        public List<TrieNode> getTs(){
            return this.ts;
        }
        
        public TrieNode contains(char c) {
            if(ts != null) {
                for(TrieNode t : ts) {
                    if(Objects.equals(c, t.c)) {
                        return t;
                    }
                }
            }
            return null;
        }
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
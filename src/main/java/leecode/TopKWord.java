package leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

// 692. 前K个高频单词
public class TopKWord {
    public static void main(String[] args) {
        String[] words = new String[] {"i", "love", "leetcode", "i", "love", "coding"};
        System.out.println(topKFrequent(words,2));
        
        words = new String[] {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        System.out.println(topKFrequent(words,4));
        
        words = new String[] {"a", "aa", "aaa"};
        System.out.println(topKFrequent(words,3));
        
    }
    
    public static List<String> topKFrequent(String[] words, int k) {
        List<String> wordList = Arrays.asList(words);
        Collections.sort(wordList);
        
        List<WordCount> wcList = new ArrayList<WordCount>(k);
        
        int counter = 1;
        String w = words[0];
        for (int i = 1; i < words.length; i++) {
            
            if(Objects.equals(words[i], w)) {
                counter += 1;
            } else {
                WordCount wc = new WordCount(w, counter);
                wcList.add(wc);
                counter = 1;
                w = words[i];
            }
            
            if(Objects.equals(i, words.length-1)) {
                if(Objects.equals(words[i], w)) {
                    WordCount wc = new WordCount(w, counter);
                    wcList.add(wc);
                } else {
                    WordCount wc = new WordCount(words[i], 1);
                    wcList.add(wc);
                }
            }
        }
        
        Collections.sort(wcList, new Comparator<WordCount>() {
            @Override
            public int compare(WordCount o1, WordCount o2) {
                return o2.count - o1.count;
            }
        });

        List<String> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(wcList.get(i).word);
        }
        
        return result;
    }
    
    static class WordCount{
        private String word;
        private int count;
        
        public WordCount(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }
}

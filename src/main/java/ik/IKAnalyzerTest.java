package ik;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

public class IKAnalyzerTest {
    public static void main(String[] args) {
        segMore("看来词库还挺丰富的，速度非常快。以后分词就用IK了，简单好用。就讲到这里了，顺便AD下，大家多多支持本人新站：礼品网,"
                + "看来词库还挺丰富的，速度非常快。以后分词就用IK了"
                + "看来词库还挺丰富的，速度非常快。以后分词就用IK了，简单好用。就讲到这里了");
    }

    public static void segMore(String text) {
        System.out.println("智能切分:"+segText(text, true));
        System.out.println("细粒度切分"+segText(text, false));
        System.out.println(segTextBuffer(text, true));
    }
    
    private static String segTextBuffer(String text, boolean useSmart) {
        StringBuffer result = new StringBuffer();
        IKSegmenter ik = new IKSegmenter(new StringReader(text), useSmart);
        try {
            Lexeme word = null;
            while ((word = ik.next()) != null) {
                result.append(word.getLexemeText()).append(" ");
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return result.toString();
    }

    private static String segText(String text, boolean useSmart) {
        Map<String,Integer> result = new ConcurrentHashMap<>();
        IKSegmenter ik = new IKSegmenter(new StringReader(text), useSmart);
        try {
            Lexeme word = null;
            while ((word = ik.next()) != null) {
               String wordText = word.getLexemeText();
               if(result.containsKey(wordText)){
                   result.put(wordText, result.get(wordText)+1);
               }else{
                   result.put(wordText, 1);
               }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return result.toString();
    }
}

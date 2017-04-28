package ik;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.chenlb.mmseg4j.ComplexSeg;
import com.chenlb.mmseg4j.Dictionary;
import com.chenlb.mmseg4j.MMSeg;
import com.chenlb.mmseg4j.MaxWordSeg;
import com.chenlb.mmseg4j.Seg;
import com.chenlb.mmseg4j.SimpleSeg;
import com.chenlb.mmseg4j.Word;

/**
 * 
 * @author xiaoman
 * 暂时MMSeg4j没有停用词库
 *
 */
public class MMSeg4jTest {
    
    private static final Dictionary DIC = Dictionary.getInstance();
    private static final SimpleSeg SIMPLE_SEG = new SimpleSeg(DIC);
    private static final ComplexSeg COMPLEX_SEG = new ComplexSeg(DIC);
    private static final MaxWordSeg MAX_WORD_SEG = new MaxWordSeg(DIC);

    
    
    public static Map<String, String> segMore(String text) {
        Map<String, String> map = new HashMap<>();
        map.put(SIMPLE_SEG.getClass().getSimpleName(), segText(text, SIMPLE_SEG));
        map.put(COMPLEX_SEG.getClass().getSimpleName(), segText(text, COMPLEX_SEG));
        map.put(MAX_WORD_SEG.getClass().getSimpleName(), segText(text, MAX_WORD_SEG));
        return map;
    }
    private static String segText(String text, Seg seg) {
        StringBuilder result = new StringBuilder();
        MMSeg mmSeg = new MMSeg(new StringReader(text), seg);        
        try {
            Word word = null;
            while((word=mmSeg.next())!=null) {       
                result.append(word.getString()).append(" ");
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return result.toString();
    }
    
    public static void main(String[] args) {
        String text = "看来词库还挺丰富的，速度非常快。以后分词就用IK了，简单好用。就讲到这里了，顺便AD下，大家多多支持本人新站：礼品网";
        System.out.println(JSONObject.toJSON(segMore(text)));
    }
}

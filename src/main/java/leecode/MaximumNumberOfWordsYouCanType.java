package leecode;

public class MaximumNumberOfWordsYouCanType {
    
    public static int canBeTypedWords(String text, String brokenLetters) {
        String[] textList = text.split(" ");
        int counter = 0;
        for (String t : textList) {
            boolean c = false;
            for (int i = 0; i < brokenLetters.length(); i++) {
                if (t.contains(String.valueOf(brokenLetters.charAt(i)))) {
                    c = true;
                    break;
                }
            }
            if (!c) {
                counter++;
            }
        }
        return counter;
    }
    
    public static void main(String[] args) {
        System.out.println(canBeTypedWords("hello world","ad"));
        System.out.println(canBeTypedWords("leet code","lt"));
        System.out.println(canBeTypedWords("leet code","e"));
    }
}

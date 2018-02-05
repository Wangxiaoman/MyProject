/**
 * 
 */
package leecode;

/**
 * @author linkedme 657
 *
 */
public class JudgeRouteCircle {
    public static boolean judgeCircle(String moves) {
        int[] position = new int[]{0,0};
        for(int i=0;i<moves.length();i++){
            char step = moves.charAt(i);
            
            if('U' == step){
                position[0] = position[0] + 1;
            }
            if('D' == step){
                position[0] = position[0] - 1;
            }
            if('L' == step){
                position[1] = position[1] + 1;
            }
            if('R' == step){
                position[1] = position[1] - 1;
            }
        }
        
        if(position[0] == 0 && position[1] == 0){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(judgeCircle("LL"));
        System.out.println(judgeCircle("UD"));
        System.out.println(judgeCircle("UDLR"));
    }
}

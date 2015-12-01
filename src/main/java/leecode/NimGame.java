package leecode;
/**
 * 
* @ClassName: NimGame 
* @Description: 2个玩家，搬石头，每次玩家可以搬走1-3块石头，判断是否你可以取胜
* @author xiaoman 
* @date 2015年11月30日 下午2:06:46 
*
 */
public class NimGame {
	public static boolean canWinNim(int n) {
		if(n %4 == 0){
			return false;
		}
		return true;
    }
	
	
	public static void main(String[] args) {
		System.out.println(canWinNim(15));
	}
}

package leecode;

// 1688. 比赛中的配对次数
public class RacePair {
    public static void main(String[] args) {
        System.out.println(new RacePair().numberOfMatches(7));
        System.out.println(new RacePair().numberOfMatches(14));
    }
    
    private int sum = 0;
    public int numberOfMatches(int n) {
        if(n <= 1) {
            return 0;
        }
        if(n == 2) {
            return sum + 1;
        } else {
            int currentTeam = 0;
            if(n % 2 == 1) {
                currentTeam = (n-1)/2;
                sum += currentTeam;
                return numberOfMatches(currentTeam + 1);
            } else {
                currentTeam = n/2;
                sum += currentTeam;
                return numberOfMatches(currentTeam);
            }
        }
    }
}

package leecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class FriendshipService {
    private Map<Integer,Set<Integer>> followMap;
    private Map<Integer,Set<Integer>> followingMap;
    
    public FriendshipService() {
        // do initialization if necessary
        followMap = new HashMap<>();
        followingMap = new HashMap<>();
    }

    /*
     * @param user_id: An integer
     * @return: all followers and sort by user_id
     */
    public List<Integer> getFollowers(int user_id) {
        // write your code here
        Set<Integer> set = followMap.get(user_id);
        if(set != null && !set.isEmpty()) {
            return new ArrayList<>(set);
        }
        return Collections.emptyList();
    }

    /*
     * @param user_id: An integer
     * @return: all followings and sort by user_id
     */
    public List<Integer> getFollowings(int user_id) {
        // write your code here
        Set<Integer> set = followingMap.get(user_id);
        if(set != null && !set.isEmpty()) {
            return new ArrayList<>(set);
        }
        return Collections.emptyList();
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void follow(int to_user_id, int from_user_id) {
        // write your code here
        Set<Integer> fset = followMap.getOrDefault(to_user_id, new TreeSet<>());
        fset.add(from_user_id);
        followMap.put(to_user_id, fset);
        
        Set<Integer> fiset = followingMap.getOrDefault(from_user_id, new TreeSet<>());
        fiset.add(to_user_id);
        followingMap.put(from_user_id, fiset);
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void unfollow(int to_user_id, int from_user_id) {
        // write your code here
        Set<Integer> fset = followMap.get(to_user_id);
        if(fset != null && !fset.isEmpty()) {
            fset.remove(from_user_id);
        }
        
        Set<Integer> fiset = followingMap.get(from_user_id);
        if(fiset != null && !fiset.isEmpty()) {
            fiset.remove(to_user_id);
        }
    }
    
    public static void main(String[] args) {
        FriendshipService f = new FriendshipService();
        f.follow(1, 3);
        System.out.println(f.getFollowers(1)); 
        System.out.println(f.getFollowings(3)); 
        f.follow(2, 3);
        System.out.println(f.getFollowings(3)); 
        f.unfollow(1, 3);
        System.out.println(f.getFollowings(3)); 
    }
}

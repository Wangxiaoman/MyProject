package leecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author xiaomanwang postTweet(user_id, tweet_text). 发布一条推特. getTimeline(user_id).
 *         获得给定用户最新发布的十条推特，按照发布时间从最近的到之前排序 getNewsFeed(user_id).
 *         获得给定用户的朋友或者他自己发布的最新十条推特，从发布时间最近到之前排序 follow(from_user_id, to_user_id). from_user_id 关注
 *         to_user_id. unfollow(from_user_id, to_user_id). from_user_id 取消关注 to_user_id.
 *
 */
public class MiniTwitter {
    private Map<Integer, Set<Integer>> followMap = null;
    private Map<Integer, List<TweetSorted>> tweetMap = null;
    private int counter = 0;

    public MiniTwitter() {
        // do intialization if necessary
        followMap = new HashMap<>();
        tweetMap = new HashMap<>();
    }

    /*
     * @param user_id: An integer
     * 
     * @param tweet_text: a string
     * 
     * @return: a tweet
     */
    public Tweet postTweet(int user_id, String tweet_text) {
        // write your code here
        List<TweetSorted> ts = tweetMap.getOrDefault(user_id, new ArrayList<>());
        TweetSorted tsd = new TweetSorted(counter++, user_id, tweet_text);
        ts.add(tsd);
        tweetMap.put(user_id, ts);
        return tsd.t;
    }

    public List<TweetSorted> getTimeLineTs(int user_id){
        List<TweetSorted> list = tweetMap.get(user_id);
        if (list != null) {
            List<TweetSorted> ts = new ArrayList<>(
                    list.subList(list.size() > 10 ? list.size() - 10 : 0, list.size()));
            Collections.reverse(ts);
            return ts;
        }
        return null;
    }
    
    /*
     * @param user_id: An integer
     * 
     * @return: a list of 10 new feeds recently and sort by timeline
     */
    public List<Tweet> getTimeline(int user_id) {
        // write your code here
        List<TweetSorted> list = getTimeLineTs(user_id);
        if (list != null) {
            return list.stream().map(tsd -> tsd.t).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    /*
     * @param user_id: An integer
     * 
     * @return: a list of 10 new posts recently and sort by timeline
     */
    public List<Tweet> getNewsFeed(int user_id) {
        // write your code here
        List<TweetSorted> tsList = new ArrayList<>();
        List<TweetSorted> userList = getTimeLineTs(user_id);
        if (userList != null) {
            tsList.addAll(userList);
        }
        Set<Integer> followList = followMap.get(user_id);
        if (followList != null) {
            for (Integer fid : followList) {
                List<TweetSorted> flist = getTimeLineTs(fid);
                if (flist != null && !flist.isEmpty()) {
                    tsList.addAll(flist);
                }
            }
        }

        if (!tsList.isEmpty()) {
            Collections.sort(tsList, new Comparator<TweetSorted>() {
                @Override
                public int compare(TweetSorted o1, TweetSorted o2) {
                    return o2.id - o1.id;
                }
            });
            List<Tweet> tlist = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                if(tsList.size() <= i) {
                    return tlist;
                }
                tlist.add(tsList.get(i).t);
            }
            return tlist;
        }
        return Collections.emptyList();
    }

    /*
     * @param from_user_id: An integer
     * 
     * @param to_user_id: An integer
     * 
     * @return: nothing
     */
    public void follow(int from_user_id, int to_user_id) {
        // write your code here
        Set<Integer> followList = followMap.getOrDefault(from_user_id, new HashSet<>());
        followList.add(to_user_id);
        followMap.put(from_user_id, followList);
    }

    /*
     * @param from_user_id: An integer
     * 
     * @param to_user_id: An integer
     * 
     * @return: nothing
     */
    public void unfollow(int from_user_id, int to_user_id) {
        // write your code here
        Set<Integer> followList = followMap.get(from_user_id);
        if (followList != null) {
            followList.remove(to_user_id);
            followMap.put(from_user_id, followList);
        }
    }
    
    public static void main(String[] args) {
        MiniTwitter t = new MiniTwitter();
        t.postTweet(1, "LintCode is Good!!!");
        t.getNewsFeed(1);
        t.getTimeline(1);
        t.follow(2, 1);
        t.postTweet(1, "LintCode is best!!!");
        System.out.println(t.getNewsFeed(2));
        t.unfollow(2, 1);
        t.getNewsFeed(2);
    }
}


class TweetSorted {
    public int id;
    public Tweet t;
    public TweetSorted(int id, int user_id, String text) {
        this.t = Tweet.create(user_id, text);
        this.id = id;
    }
    @Override
    public String toString() {
        return "TweetSorted [id=" + id + ", t=" + t + "]";
    }
}

class Tweet {
    public int id;
    public int user_id;
    public String text;
    
    public Tweet(int user_id, String text) {
        this.user_id = user_id;
        this.text = text;
    }

    public static Tweet create(int user_id, String tweet_text) {
        // This will create a new tweet object,
        // and auto fill id
        return new Tweet(user_id,tweet_text);
    }

    @Override
    public String toString() {
        return "Tweet [id=" + id + ", user_id=" + user_id + ", text=" + text + "]";
    }
}

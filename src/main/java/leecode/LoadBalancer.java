package leecode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import qlm.QLMJsonMain;

/**
 * @author xiaomanwang
 * 为网站实现一个负载均衡器，提供如下的 3 个功能：
 * 1.添加一台新的服务器到整个集群中 => add(server_id)。
 * 2.从集群中删除一个服务器 => remove(server_id)。
 * 3.在集群中随机（等概率）选择一个有效的服务器 => pick()。
 * 最开始时，集群中一台服务器都没有。每次 pick() 调用你需要在集群中随机返回一个 server_id。
 */
public class LoadBalancer {
    public Set<Integer> serverIds = null;
    
    public LoadBalancer() {
        // do intialization if necessary
        serverIds = new TreeSet<>();
    }

    /*
     * @param server_id: add a new server to the cluster
     * @return: nothing
     */
    public void add(int server_id) {
        serverIds.add(server_id);
    }

    /*
     * @param server_id: server_id remove a bad server from the cluster
     * @return: nothing
     */
    public void remove(int server_id) {
        serverIds.remove(server_id);
    }

    /*
     * @return: pick a server in the cluster randomly with equal probability
     */
    public int pick() {
        if(serverIds.isEmpty()) {
            return 0;
        }
        List<Integer> ids = new ArrayList<>(serverIds);
        int random = new Random().nextInt();
        int index = Math.abs(random % ids.size());
        return ids.get(index);
    }
    
}

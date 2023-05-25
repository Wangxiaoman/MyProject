package leecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author xiaomanwang
 *  在 Consistent Hashing I 中我们介绍了一个比较简单的一致性哈希算法，这个简单的版本有两个缺陷：

    增加一台机器之后，数据全部从其中一台机器过来，这一台机器的读负载过大，对正常的服务会造成影响。
    当增加到3台机器的时候，每台服务器的负载量不均衡，为1:1:2。
    为了解决这个问题，引入了 micro-shards 的概念，一个更好的算法是这样：
    
    将 360° 的区间分得更细。从 0~359 变为一个 0 ~ n-1 的区间，将这个区间首尾相接，连成一个圆。
    当加入一台新的机器的时候，随机选择在圆周中撒 k 个点，代表这台机器的 k 个 micro-shards。
    每个数据在圆周上也对应一个点，这个点通过一个 hash function 来计算。
    一个数据该属于哪台机器负责管理，是按照该数据对应的圆周上的点在圆上顺时针碰到的第一个 micro-shard 点所属的机器来决定。
 */
public class ConsistentHash {
    private int k = 0;
    private List<Integer> shardMahineIds;
    private List<Integer> canUserShardIds;
    public ConsistentHash(int k, List<Integer> shardIds, List<Integer> canUserShardIds) {
        this.k = k;
        this.shardMahineIds = shardIds;
        this.canUserShardIds = canUserShardIds;
    }
    
    /*
     * @param n: a positive integer
     * @param k: a positive integer
     * @return: a Solution object
     */
    public static ConsistentHash create(int n, int k) {
        // Write your code here
        List<Integer> shardIds = new ArrayList<>(n);
        List<Integer> canUserShardIds = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            shardIds.add(0);
            canUserShardIds.add(i);
        }
        Collections.shuffle(canUserShardIds);
        return new ConsistentHash(k,shardIds,canUserShardIds);
    }

    /*
     * @param machine_id: An integer
     * @return: a list of shard ids
     */
    public List<Integer> addMachine(int machine_id) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int shardId = canUserShardIds.get(i);
            this.shardMahineIds.set(shardId, machine_id);
            result.add(shardId);
        }
        canUserShardIds = canUserShardIds.subList(k, canUserShardIds.size());
        return result;
    }

    /*
     * @param hashcode: An integer
     * @return: A machine id
     */
    public int getMachineIdByHashCode(int hashcode) {
        int lastestIndex = hashcode % shardMahineIds.size();
        for (int i = 0; i < shardMahineIds.size(); i++) {
            int index = (lastestIndex + i) % shardMahineIds.size();
            int mid = shardMahineIds.get(index);
            if (mid > 0) {
                return mid;
            }
        }
        return 0;
    }
    
    public static void main(String[] args) {
        ConsistentHash c = create(10, 5);
        c.addMachine(1);
        c.getMachineIdByHashCode(4);
        c.addMachine(2);
        c.getMachineIdByHashCode(0);
        c.getMachineIdByHashCode(1);
        c.getMachineIdByHashCode(2);
        c.getMachineIdByHashCode(3);
        c.getMachineIdByHashCode(4);
        c.getMachineIdByHashCode(5);
        c.getMachineIdByHashCode(6);
        c.getMachineIdByHashCode(7);
        c.getMachineIdByHashCode(8);
        c.getMachineIdByHashCode(9);
    }
}

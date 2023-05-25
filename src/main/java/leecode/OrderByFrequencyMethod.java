package leecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * @author xiaomanwang 给定一个 int 数组，统计数组中每个数字出现的次数，并按数字出现的次数进行排序（从大到小）， 将结果以 Map 返回，其中 key为数字出现的次数，
 *         value 为出现过 key 次的所有数字集合（从小到大排序）。
 */
public class OrderByFrequencyMethod {
    public Map<Integer, List<Integer>> orderByFrequency(int[] nums) {
        // write your code here
        Map<Integer, Integer> ncMap = new HashMap<>();
        for (int num : nums) {
            Integer c = ncMap.getOrDefault(num, 0);
            ncMap.put(num, c + 1);
        }

        List<NumberCount> ncList = new ArrayList<>();
        for (Entry<Integer, Integer> entry : ncMap.entrySet()) {
            ncList.add(new NumberCount(entry.getKey(), entry.getValue()));
        }

        Collections.sort(ncList, new Comparator<NumberCount>() {
            @Override
            public int compare(NumberCount o1, NumberCount o2) {
                return o2.c - o1.c;
            }
        });

        return ncList.stream().collect(Collectors.groupingBy(NumberCount::getC,LinkedHashMap::new,
                Collectors.mapping(NumberCount::getN, Collectors.toList())));
    }
}


class NumberCount {
    public int n;
    public int c;

    public int getC() {
        return c;
    }

    public int getN() {
        return n;
    }

    public NumberCount(int num, int count) {
        this.n = num;
        this.c = count;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + n;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NumberCount other = (NumberCount) obj;
        if (n != other.n)
            return false;
        return true;
    }
}

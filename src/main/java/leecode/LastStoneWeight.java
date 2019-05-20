package leecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.util.CollectionUtils;

/**
 * 1046. Last Stone Weight
 * 
 * @author xiaomanwang
 *
 */
public class LastStoneWeight {
	public static int lastStoneWeight(int[] stones) {
		if (stones.length == 1) {
			return stones[0];
		}
		List<Integer> inputs = new ArrayList<>();
		for (int i : stones) {
			inputs.add(i);
		}

		List<Integer> outputs = getList(inputs);
		if (outputs.size() == 2)
			return outputs.get(1) - outputs.get(0);
		if (outputs.size() == 1) {
			return outputs.get(0);
		}
		return 0;
	}

	public static List<Integer> getList(List<Integer> inputs) {
		Collections.sort(inputs);
		System.out.println(inputs);
		if (inputs.size() <= 2) {
			return inputs;
		} else {
			int max = inputs.get(inputs.size() - 1);
			int second = inputs.get(inputs.size() - 2);
			inputs.remove(inputs.size() - 1);
			inputs.remove(inputs.size() - 1);

			if (max - second > 0) {
				inputs.add(max - second);
			}
			return getList(inputs);
		}
	}

	public static void main(String[] args) {
		// System.out.println(lastStoneWeight(new int[]{2,7,4,1,8,1}));
		// System.out.println(lastStoneWeight(new int[]{2,3,5,1,123,12}));
//		System.out.println(lastStoneWeight(new int[] { 10, 4, 2, 3, 5, 7, 4, 2, 6 }));
//		System.out.println(lastStoneWeight(new int[] { 4,3,4,3,2 }));
		System.out.println(lastStoneWeight(new int[] { 3,7,2 }));
	}
}

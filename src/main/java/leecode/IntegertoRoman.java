package leecode;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Objects;

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D
 * and M. Symbol Value I 1 V 5 X 10 L 50 C 100 D 500 M 1000
 * 
 * 将罗马数字转化为阿拉伯数字
 *
 */
public class IntegertoRoman {
	private static final Map<Character, Integer> romans;

	static {
		romans = new HashMap<>();
		romans.put('I', 1);
		romans.put('V', 5);
		romans.put('X', 10);
		romans.put('L', 50);
		romans.put('C', 100);
		romans.put('D', 500);
		romans.put('M', 1000);
	}

	public static int getNumber(String roman) {
		int result = 0;
		int i = 0;
		while (true) {
			char current = roman.charAt(i);
			int currentNumber = romans.get(current);
			if (Objects.equal(i, roman.length() - 1)) {
				return result + currentNumber;
			} else {
				char next = roman.charAt(i + 1);
				int nextNumber = romans.get(next);

				if (currentNumber >= nextNumber) {
					result += currentNumber;
					i++;
				} else {
					result += nextNumber - currentNumber;
					i = i + 2;
					if (Objects.equal(i, roman.length())) {
						return result;
					}
				}
			}
		}
	}

	public static String getRoman(int h) {
		StringBuffer sb = new StringBuffer();
		int t = 0;
		if (h >= 1000) {
			t = h / 1000;
			sb.append(getRepeatRoman('M', t));
			h = h % 1000;
			if (h == 0) {
				return sb.toString();
			}
		}

		if (h >= 100) {
			t = h / 100;
			sb.append(getHunderdNumber(h));
			h = h % 100;
			if (h == 0) {
				return sb.toString();
			}
		}

		if (h >= 10) {
			t = h / 10;
			sb.append(getTenNumber(h));
			h = h % 10;
			if (h == 0) {
				return sb.toString();
			}
		}
		return sb.append(getSingleNumber(h)).toString();
	}

	public static String getRepeatRoman(Character c, int count) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < count; i++) {
			result.append(c);
		}
		return result.toString();
	}

	public static String getHunderdNumber(int hunNumber) {
		int number = hunNumber / 100;
		StringBuffer sb = new StringBuffer();
		if (number <= 3) {
			return sb.append(getRepeatRoman('C', number)).toString();
		}
		if (number == 4) {
			return sb.append("CD").toString();
		}
		if (number >= 5 && number <= 8) {
			return sb.append("D").append(getRepeatRoman('C', number - 5)).toString();
		}
		if (number == 9) {
			return sb.append("CM").toString();
		}
		if(number == 10){
			return sb.append("M").toString();
		}
		return sb.toString();
	}

	public static String getTenNumber(int tenNumber) {
		int number = tenNumber / 10;
		StringBuffer sb = new StringBuffer();
		if (number <= 3) {
			return sb.append(getRepeatRoman('X', number)).toString();
		}
		if (number == 4) {
			return sb.append("XL").toString();
		}
		if (number >= 5 && number <= 8) {
			return sb.append("L").append(getRepeatRoman('X', number - 5)).toString();
		}
		if (number == 9) {
			return sb.append("XC").toString();
		}
		if (number == 10) {
			return sb.append("C").toString();
		}
		return sb.toString();
	}

	public static String getSingleNumber(int number) {
		StringBuffer sb = new StringBuffer();
		if (number <= 3) {
			return sb.append(getRepeatRoman('I', number)).toString();
		}
		if (number == 4) {
			return sb.append("IV").toString();
		}
		if (number >= 5 && number <= 8) {
			return sb.append("V").append(getRepeatRoman('I', number - 5)).toString();
		}
		if (number == 9) {
			return sb.append("IX").toString();
		}
		if (number == 10) {
			return sb.append("X").toString();
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(getRoman(3));
		System.out.println(getRoman(4));
		System.out.println(getRoman(9));
		System.out.println(getRoman(312));
		System.out.println(getRoman(58));
		System.out.println(getRoman(1994));
		System.out.println(getRoman(10));
		System.out.println(getRoman(100));
		System.out.println(getRoman(1000));
	}

}

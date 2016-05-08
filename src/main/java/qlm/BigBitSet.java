package qlm;

import java.util.BitSet;

public class BigBitSet {
  // integer的数字存放在这个set中
  private BitSet integerSet = new BitSet();

  // 大于integer的数字，分高地位，分别存放在这两个set中
  private BitSet beyondIntegrSetH = new BitSet();// 高位图
  private BitSet beyondIntegrSetL = new BitSet();// 低位图

  public int getHigh(long l) {
    // long l = 900030065410220000L;
    int j = 1000000000;
    if (l > 1000000000000000000L) {
      System.out.println("Out range!");
      return 0;
    } else {
      l = l / j; // 整除，去掉后9位
      // System.out.println((int)l);
      return (int) l;
    }
  }

  public int getLow(long l) {
    // long l = 900030065410220000L;
    int j = 1000000000;
    if (l > 1000000000000000000L) {
      System.out.println("Out range!");
      return 0;
    } else {
      l = l % j; // 取余数，去掉前9位
      return (int) l;
    }
  }

  public void setComp(long l) {
    if (l > Integer.MAX_VALUE) {
      beyondIntegrSetH.set(getHigh(l));
      beyondIntegrSetL.set(getLow(l));
    } else {
      integerSet.set((int) l);
    }
  }

  public boolean getComp(long l) {
    if (l > Integer.MAX_VALUE) {
      return beyondIntegrSetH.get(getHigh(l)) && beyondIntegrSetL.get(getLow(l));
    } else {
      return integerSet.get((int) l);
    }
  }

  public long cardinality() {
    System.out.println("integer size : "+integerSet.cardinality());
    System.out.println("beyond integer size : "+beyondIntegrSetH.cardinality());
    return Long.valueOf(integerSet.cardinality()) + Long.valueOf(beyondIntegrSetH.cardinality());
  }

  public static void main(String[] args) {
    BigBitSet set = new BigBitSet();
    set.setComp(10000);
    set.setComp(10000890800l);
    
    System.out.println(set.getComp(10000));
    System.out.println(set.getComp(10000890800l));
  }
}

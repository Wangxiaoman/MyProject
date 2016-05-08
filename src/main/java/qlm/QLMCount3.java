package qlm;

public class QLMCount3 {
  /**
   * @param args
   */
  public static void main(String[] args) {
    BigBitSet bitSetL = new BigBitSet();
    
    Gen rand = new Gen();
    long a = System.currentTimeMillis();
    long max = 0;
    int size = 0;
    // long maxSize = 50000000000L;
    long maxSize = 5000000000L;
    for (long i = 0; i < maxSize; i++) {
      // System.out.println(rand.next());
      long ans = rand.next();
      if (ans > max) max = ans;
      if(!bitSetL.getComp(ans)){
        size++;
        bitSetL.setComp(ans);
      }
    }
    long b = System.currentTimeMillis();
    System.out.println((b - a) + "ms");
    System.out.println(max);
    System.out.println("size == " + bitSetL.cardinality());
    System.out.println("real size == " + size);
  }
}

class Gen {
  private static final long twist(long u, long v) {
    return (((u & 0x80000000L) | (v & 0x7fffffffL)) >> 1) ^ ((v & 1) == 1 ? 0x9908b0dfL : 0);
  }

  private long[] state = new long[624];
  private int left = 1;

  public Gen() {
    for (int j = 1; j < 624; j++) {
      state[j] = (1812433253L * (state[j - 1] ^ (state[j - 1] >> 30)) + j);
      state[j] &= 0xfffffffffL;
    }
  }

  public void next_state() {
    int p = 0;
    left = 624;
    for (int j = 228; --j > 0; p++)
      state[p] = state[p + 397] ^ twist(state[p], state[p + 1]);

    for (int j = 397; --j > 0; p++)
      state[p] = state[p - 227] ^ twist(state[p], state[p + 1]);

    state[p] = state[p - 227] ^ twist(state[p], state[0]);
  }

  public long next() {
    if (--left == 0) next_state();
    return state[624 - left];
  }
}

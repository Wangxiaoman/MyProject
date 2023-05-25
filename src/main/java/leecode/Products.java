package leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author xiaomanwang 这里有一个普通的商品类，我们从数据库中读取了仓库（storage）商品的信息和在售（sale）商品信息，信息中包含了商品的名称和数量。 Products
 *         类通过构造方法获取这些信息，它提供了 4 个方法：
 * 
 *         onSale(key, n)：将仓库中的 key 商品上架 n 个，成为在售商品，返回本次是否有货物被上架，有则为 true sellOut(key, n)：出售货架上的 key
 *         商品 n 个，返回值是此次卖出商品的数量 readStorage：查看当前仓库商品信息，请按照 {key1=value1, key2=value2} 的格式返回一个字符串
 *         readSale：查看当前在售商品信息，请按照 {key1=value1, key2=value2} 的格式返回一个字符串 如果 sale 里某个商品已经卖光，请从 map
 *         中删除对应的 key。storage 中则不用。 请实现 Products 类。
 *
 */
public class Products {
    Map<String, Integer> storage;
    Map<String, Integer> sale;

    // write your code here

    public Products(Map<String, Integer> storage, Map<String, Integer> sale) {
        this.sale = sale;
        this.storage = storage;
    }

    public boolean onSale(String key, int n) {
        int keyNumber = storage.getOrDefault(key, 0);
        if (keyNumber >= 0 && n >= 0) {
            int saleNumber = sale.getOrDefault(key, 0);
            if (n >= keyNumber) {
                storage.put(key, 0);
                sale.put(key, saleNumber + keyNumber);
            } else {
                storage.put(key, keyNumber - n);
                sale.put(key, saleNumber + n);
            }
            return true;
        }
        return false;
    }

    public static void saleByFunc(Consumer<Integer> c) {
        for (int i = 0; i < 10; i++) {
            c.accept(i);
        }
    }
    public static void main(String[] args) {
        saleByFunc(x -> System.out.println("consumer:"+x));
        
        List<Integer> l = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,9,10));
        Spliterator<Integer> a = l.stream().spliterator();
        Spliterator<Integer> b = a.trySplit();
        Spliterator<Integer> c = b.trySplit();
        System.out.print("a:");
        a.forEachRemaining(x -> System.out.print(x+","));
        System.out.println();
        System.out.print("b:");
        b.forEachRemaining(x -> System.out.print(x+","));
        System.out.println();
        System.out.print("c:");
        c.forEachRemaining(x -> System.out.print(x+","));
    }

    public int sellOut(String key, int n) {
        int saleNumber = sale.getOrDefault(key, 0);
        if (saleNumber > n) {
            sale.put(key, saleNumber - n);
            return n;
        } else {
            sale.remove(key);
            return saleNumber;
        }
    }

    public String readStorage() {
        return storage.toString();
    }

    public String readSale() {
        return sale.toString();
    }
}

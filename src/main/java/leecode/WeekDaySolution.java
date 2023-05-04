package leecode;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.poi.ss.formula.functions.T;

public class WeekDaySolution {

    static String[] day = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
    static Map<String, String> map = new ConcurrentHashMap<>();
    static AtomicInteger counter = new AtomicInteger(0);
    static LocalDate startDate = LocalDate.of(2021, 10, 8);

    static Thread[] getWeekDay() throws Exception {
        Thread[] weekDay = new Thread[7];
        for (int i = 0; i < weekDay.length; i++) {
            weekDay[i] = new Thread(() -> {
                do {
                    LocalDate currDate = startDate.plusDays(counter.getAndIncrement());
                    String key = currDate.format(DateTimeFormatter.ofPattern("yyyy-M-d"));
                    String value = day[currDate.get(ChronoField.DAY_OF_WEEK) == 7 ? 0 : currDate.get(ChronoField.DAY_OF_WEEK)];
                    map.put(key, value);
                } while (counter.get() < 7000);
                
//                int c = counter.getAndIncrement();
//                while (c < 7000) {
//                    LocalDate currDate = startDate.plusDays(c);
//                    String key = currDate.format(DateTimeFormatter.ofPattern("yyyy-M-d"));
//                    String value = day[currDate.get(ChronoField.DAY_OF_WEEK) == 7 ? 0 : currDate.get(ChronoField.DAY_OF_WEEK)];
//                    map.put(key, value);
//                    c = counter.getAndIncrement();
//                }
            });
        }
        return weekDay;
    }
    
    public static void main(String[] args) throws Exception {
        Thread[] threads = getWeekDay();
        for(Thread t : threads) {
            t.start();
        }
        
        for(Thread t : threads) {
            t.join();
        }
        
        System.out.println(map.size());
    }
}

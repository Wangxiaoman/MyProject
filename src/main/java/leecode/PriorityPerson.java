package leecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PriorityPerson {
    public static void priority(List<Person> list) {
        Collections.sort(list, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if ((o1.age < 5 || o1.age > 60) && (o2.age >= 5 && o2.age <= 60)) {
                    return -1;
                } else if ((o2.age < 5 || o2.age > 60) && (o1.age >= 5 && o1.age <= 60)) {
                    return 1;
                } else {
                    return o1.number - o2.number;
                }
            }
        });
        
    }

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person("Jack", 21, 0));
        list.add(new Person("Jimi", 69, 1));
        list.add(new Person("July", 52, 2));
        list.add(new Person("July", 42, 3));
        list.add(new Person("Jane", 70, 4));

        priority(list);
        for (Person p : list) {
            System.out.println(p);
        }
    }

    static class Person {
        String name;
        int age;
        int number;

        public Person(String name, int age, int number) {
            this.name = name;
            this.age = age;
            this.number = number;
        }

        public String toString() {
            return "number = " + number + ", name = " + name + ", age = " + age;
        }

    }
}

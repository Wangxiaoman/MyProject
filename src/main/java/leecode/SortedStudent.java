package leecode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SortedStudent {
    public static Map<Student, Integer> getSortMap(List<Student> list) {
        TreeMap<Student, Integer> result = new TreeMap<>();
        for(int i=0;i<list.size();i++) {
            result.put(new CompStudent(list.get(i).name,list.get(i).age), i);
        }
        return result;
    }
    
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student("a",1));
        list.add(new Student("b",1));
        list.add(new Student("c",3));
        list.add(new Student("c1",3));
        list.add(new Student("c",3));
        System.out.println(getSortMap(list));
    }
}

class CompStudent extends Student implements Comparable<Student>{
    public CompStudent(String name, int age) {
        super(name, age);
    }

    @Override
    public int compareTo(Student o) {
        int compAge = this.age - o.age;
        if(compAge == 0) {
             return this.name.compareTo(o.name);
        } else {
            return compAge;
        }
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + age;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Student other = (Student) obj;
        if (age != other.age)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}

class Student{
    String name;
    int age;
    public Student(String name, int age) {
        this.name= name;
        this.age = age;
    }
    public String toString() {
        return "[name: " + name + ", age: " + age + "]";
    }
}
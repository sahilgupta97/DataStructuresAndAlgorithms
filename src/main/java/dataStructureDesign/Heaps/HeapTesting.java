package dataStructureDesign.Heaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class HeapTesting {
    static class Student implements Comparable<Student> {
        String name;
        int marks;

        Student(String name, int marks) {
            this.marks = marks;
            this.name = name;
        }

        public String toString() {
            return "Student : " + this.name + " Marks : " + this.marks;
        }

        @Override
        public int compareTo(Student o) {
            // TODO Auto-generated method stub
            return this.marks - o.marks;
        }
    }

    static class Mcomp implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            // TODO Auto-generated method stub
            return o2.marks - o1.marks;
        }

    }

    public static void main(String[] args) {

        Student s1 = new Student("Sahil", 100);
        Student s2 = new Student("David", 0);
        Student s3 = new Student("Mohit", 99);
        Student s4 = new Student("Aman", 11);
        Student s5 = new Student("Adil", 10);

        List<Student> students = new ArrayList<>(Arrays.asList(s1,s2,s3,s4,s5));
        dataStructureDesign.Heaps.Heap<Student> maxHeap = new Heap<>(students, (st1, st2) -> st2.marks - st1.marks);

        System.out.println(maxHeap.poll());
        System.out.println(maxHeap.peek());
        maxHeap.offer(new Student("Divyank", 1000));
        System.out.println(maxHeap.peek());
    }
}

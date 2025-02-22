import java.io.*;
import java.util.*;

// Solution for a comparator problem in hackerrank https://www.hackerrank.com/challenges/java-sort/problem?isFullScreen=true
public class Solution1 {

    public static void main(String[] args) {
        Solution1 sol = new Solution1();
        List <Student> students = sol.readInput();
        CgpaComparator cgpaComparator = sol.new CgpaComparator();
        NameComparator nameComparator = sol.new NameComparator();
        Collections.sort(students, cgpaComparator);
        Collections.reverse(students);
        Collections.sort(students, nameComparator);
        for(Student student : students){
            System.out.println(student.getName());
        }
    }
    
    
    
    private List<Student> readInput(){
        Scanner sc = new Scanner(System.in);
        List<Student> list = new ArrayList();
        
        while(sc.hasNext()){
            String input = sc.nextLine();
            
            String [] parts = input.split(" ");
            if(parts.length > 1){
            
         Student st = new Student(Integer.parseInt(parts[0]), 
         parts[1], Double.parseDouble(parts[2]));
         list.add(st);
        }}
        return list;
    }
    
   class NameComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        if (Double.compare(s1.getCgpa(), s2.getCgpa()) == 0) {
            return s1.getName().compareTo(s2.getName());
        }
        return 0; // If CGPAs are not equal, do not change the order
    }
}
    class CgpaComparator implements Comparator<Student>{
        @Override
        public int compare(Student s1, Student s2){
            return Double.compare(s1.getCgpa(), s2.getCgpa());
        }
    }
    
    class Student {
    int id;
    String name;
    double cgpa;
    public Student(int id, String name, double cgpa){
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
        
    }
    private int getId(){ return this.id;}
    private String getName(){return this.name;}
    private double getCgpa(){return this.cgpa;}
}}

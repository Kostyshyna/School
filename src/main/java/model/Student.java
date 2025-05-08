package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.*;

public class Student {
    private String id;
    private String name;
    private List<StudentDiscipline> disciplines = new ArrayList<>();
    public Student(){}
    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<StudentDiscipline> getDisciplines() { return disciplines; }
    public void setDisciplines(List<StudentDiscipline> disciplines) { this.disciplines = disciplines; }

    public void assignDiscipline(Discipline discipline, double grade) {
        disciplines.add(new StudentDiscipline(discipline, grade));
    }
    public void removeDiscipline(Discipline discipline, int grade) {
        disciplines.remove(new StudentDiscipline(discipline, grade));
    }
    public StudentDiscipline searchForDiscipline(Discipline ds){
        for (StudentDiscipline disc : disciplines){
            if(ds==disc.discipline) return disc;
        }
        return null;
    }
    public void doTest(Discipline ds){
            for(StudentDiscipline disc :disciplines){
                if( ds.getName().equals(disc.discipline.getName()) && disc.getGrade()<100.00){
                   double pointsLeft = 100- disc.getGrade();
                   double result = Math.random()*pointsLeft;
                   disc.setGrade(Math.ceil(disc.getGrade()+result));
                    System.out.println("Grade for the test:" + result);
                    System.out.println("Discipline grade:" + disc.getGrade());
                   return;
                }

        }
         throw new IllegalArgumentException("Discipline grade is rather too high or discipline is missing");


    }
    public double calculateAverage() {
        int i = 0;
        double res = 0.0;
        for(StudentDiscipline dsc : disciplines){
            res+= dsc.getGrade();;
            i++;
        }
        return (res / i);
    }
    @JsonIgnore
    public String getStudentOverallInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append("Student ID: " + id + "\n");
        sb.append("Student Name: " + name + "\n");
        sb.append("Student Average: " + calculateAverage() + "\n");
        sb.append("Student Disciplines: " +  "\n");
        for(StudentDiscipline disc : disciplines){
            sb.append(disc.getDisciplineOverallInfo());
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

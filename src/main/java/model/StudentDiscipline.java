package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class StudentDiscipline {
    public Discipline discipline;
    private double grade;
    public StudentDiscipline(){};
    public StudentDiscipline(Discipline discipline, double grade) {
        this.discipline = discipline;
        this.grade = grade;
    }
    public double getGrade() { return grade; }
    public void setGrade(double grade) {
        if(grade <=100 && grade >= 0) this.grade = grade;
    }
    @JsonIgnore
    public String getDisciplineOverallInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append("Discipline Name: " + discipline.getName() + "\n");
        sb.append("Grade: " + grade + "\n");
        return sb.toString();
    }
}

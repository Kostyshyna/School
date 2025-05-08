package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class Discipline {
    private String name;
    private double grade;
    public Discipline(){

    }
    public Discipline(String name ) {
        this.name = name;
        this.grade = 0;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getGrade() { return grade; }
    public void setGrade(double grade) { this.grade = grade; }
    @JsonIgnore
    public String getMainDisciplineOverallInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append("Discipline Name: " + name + "\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discipline that = (Discipline) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

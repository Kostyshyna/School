package model;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
public class School {
    private String name;
    //hhh
    private ObjectMapper d = new ObjectMapper();
    private List<Student> students = new ArrayList<>();
    //hhh
    private String id;
    private List<Discipline> disciplines = new ArrayList<>();
    public School(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public School(){}
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Student> getStudents() { return students; }
    public void setStudents(List<Student> students) { this.students = students; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public List<Discipline> getDisciplines(){
        return disciplines;
    }

    public void addDisciplines(Discipline discipline) {
        if(disciplines.contains(discipline)){
            throw new IllegalArgumentException("This discipline is already in the list");
        }
        this.disciplines.add(discipline);
    }
    public void removeDisciplines(Discipline discipline) {
        if(disciplines.contains(discipline)) {
            this.disciplines.remove(discipline);
        } else{
            throw  new IllegalArgumentException("This school does not contain such discipline");
        }
    }

    public void addStudent(Student student) {
        if(students.contains(student)){
            throw new IllegalArgumentException("This student has already been enrolled in this school");
        }
        students.add(student);
    }
    public void removeStudent(Student student) {
        if(students.contains(student)) {
            students.remove(student);
        } else {
            throw new IllegalArgumentException("The student is not a member of this school");
        }
    }
    public double calculateAverageGrade() {
        return students.stream()
                .mapToDouble(Student::calculateAverage)
                .average()
                .orElse(0.0);
    }
    public String getSchoolOverallInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append("School ID: " + id + "\n");
        sb.append("School Name: " + name + "\n");
        sb.append("\nStudents: \n");
        for (Student st : students) {
            sb.append(st.getStudentOverallInfo() + "\n");
        }
        sb.append("\nDisciplines: \n");
        for(Discipline disc : disciplines) {
            sb.append(disc.getMainDisciplineOverallInfo() + "\n");
        }
        return sb.toString();
    }
    public void setObjectMapper(ObjectMapper mapper) {
        this.d = mapper;
    }
    private Supplier<FileWriter> fileWriterSupplierDisciplines = () -> {
        try {
            return new FileWriter("C:\\Lab_1_School\\School3\\src\\main\\resources\\disciplines.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };

    public void setFileWriterSupplierDisciplines(Supplier<FileWriter> fileWriterSupplier) {
        this.fileWriterSupplierDisciplines = fileWriterSupplier;
    }
    private Supplier<FileWriter> fileWriterSupplierStudents = () -> {
        try {
            return new FileWriter("C:\\Lab_1_School\\School3\\src\\main\\resources\\students.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };
    public void exportDisciplines(boolean needsSort) throws IOException {
        if(needsSort) {
            disciplines.sort(Comparator.comparing(Discipline::getName));
        }
        try (FileWriter discWriter = fileWriterSupplierDisciplines.get()) {
            discWriter.write(d.writeValueAsString(disciplines));
        }
    }
    public void importDisciplines() throws IOException {
        List<Discipline> importedDisciplines = d.readValue(new File("C:\\Lab_1_School\\School3\\src\\main\\resources\\disciplines.json"),
                new TypeReference<List<Discipline>>() {});
        for (Discipline disc : importedDisciplines) {
            if (!disciplines.contains(disc)) {
                disciplines.add(disc);
            }
        }
    }
    public void exportStudents() throws IOException {
        students.sort(Comparator.comparing(Student::getId));
        try (FileWriter studentsWriter = fileWriterSupplierStudents.get()) {
            studentsWriter.write(d.writeValueAsString(students));
        }

    }
    public void importStudents() throws IOException {
        List<Student> importedStudents = d.readValue(new File("C:\\Lab_1_School\\School3\\src\\main\\resources\\students.json"),
                new TypeReference<List<Student>>() {});
        for (Student s : importedStudents) {
            if (!students.contains(s)) {
                students.add(s);
            }
        }
    }
    public void setFileWriterSupplierStudents(Supplier<FileWriter> fileWriterSupplier) {
        this.fileWriterSupplierStudents = fileWriterSupplier;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        School school = (School) obj;
        return Objects.equals(id, school.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

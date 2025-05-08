package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class basic_Test {
    private School sch;
    @BeforeEach
    void setUp() throws Exception {
        sch = new School("1234567", "Test School");
    }
    @Test
    void addStudent(){
        Student st = new Student("123678","John");
        sch.addStudent(st);
        Assertions.assertTrue(sch.getStudents().contains(st));
    }
    @Test
    void removeStudent(){
        Student st = new Student("123678","John");
        sch.addStudent(st);
        sch.removeStudent(st);
        Assertions.assertFalse(sch.getStudents().contains(st));
    }
    @Test
    void addStudent_Already_Exists(){
        Student st = new Student("123456", "John");
        sch.addStudent(st);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {sch.addStudent(st);});
    }
    @Test
    void removeStudent_Doesnt_Exist(){
        Student st = new Student("123456", "John");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {sch.removeStudent(st);});
    }
    @Test
    void addDiscipline(){
        Discipline dsc = new Discipline("Math");
        sch.addDisciplines(dsc);
        Assertions.assertTrue(sch.getDisciplines().contains(dsc));
    }
    @Test
    void removeDiscipline(){
        Discipline dsc = new Discipline("Math");
        sch.addDisciplines(dsc);
        sch.removeDisciplines(dsc);
        Assertions.assertFalse(sch.getDisciplines().contains(dsc));
    }
    @Test
    void addDiscipline_Already_Exists(){
        Discipline dsc = new Discipline("Math");
        sch.addDisciplines(dsc);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {sch.addDisciplines(dsc);});
    }
    @Test
    void removeDiscipline_Doesnt_Exist(){
        Discipline dsc = new Discipline("Math");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {sch.removeDisciplines(dsc);});
    }
}

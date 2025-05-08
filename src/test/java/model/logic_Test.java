package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
class logic_Test {
    private School sch;
    private Student st1;
    private Student st2;
    private Discipline dsc1;
    private Discipline dsc2;
    @BeforeEach
    void setUp() throws Exception {
        sch = new School("1234567", "Test School");
         dsc1 = new Discipline("Math");
         dsc2 = new Discipline("Art");
         st1 = new Student("09876","John");
         st2 = new Student("14567", "Ann");
        sch.addStudent(st1);
        sch.addStudent(st2);
        sch.addDisciplines(dsc1);
        sch.addDisciplines(dsc2);
    }
    @Test
    void countStudentAvg(){
        st1.assignDiscipline(dsc1,80);
        st1.assignDiscipline(dsc2,60);
        Assertions.assertEquals(70, st1.calculateAverage());
        }
    @Test
    void countSchoolAvg(){
        st1.assignDiscipline(dsc1,80);
        st1.assignDiscipline(dsc2,60);
        st2.assignDiscipline(dsc1,50);
        st2.assignDiscipline(dsc2,50);
        Assertions.assertEquals(60,sch.calculateAverageGrade());
    }

    void doTest(){
        st1.assignDiscipline(dsc1,80);
        st1.doTest(dsc1);
        Assertions.assertTrue(st1.searchForDiscipline(dsc1).getGrade()<=100);
        Assertions.assertTrue(st1.searchForDiscipline(dsc1).getGrade()>=80);
    }
    @Test
    void doTest_tooHighGrade(){
        st1.assignDiscipline(dsc1,100);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {st1.doTest(dsc2);});
    }

}

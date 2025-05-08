package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
class import_export_Test {
    private School sch;
    private ObjectMapper mockObjectMapper;
    @BeforeEach
    void setUp(){
        sch = new School("12344","Test School");
        mockObjectMapper = mock(ObjectMapper.class);
        sch.setObjectMapper(mockObjectMapper);
    }
    @Test
    void exportDisciplines() throws IOException{
        when(mockObjectMapper.writeValueAsString(sch.getDisciplines())).thenReturn("Mocked version");
        FileWriter mockWriter = mock(FileWriter.class);
        sch.setFileWriterSupplierDisciplines(()->mockWriter);
        sch.exportDisciplines(false);
        verify(mockWriter,times(1)).write(anyString());
        verify(mockWriter,times(1)).close();
    }
    @Test
    void importDisciplines() throws IOException {
        List<Discipline> mockDisciplines = Arrays.asList(
                new Discipline("Math"),
                new Discipline("Art")
        );
        when(mockObjectMapper.readValue(any(File.class),ArgumentMatchers.<TypeReference<List<Discipline>>>any())).thenReturn(mockDisciplines);
        sch.importDisciplines();
        Assertions.assertTrue(sch.getDisciplines().containsAll(mockDisciplines));
    }
    @Test
    void exportStudents() throws IOException{
        when(mockObjectMapper.writeValueAsString(sch.getStudents())).thenReturn("Mocked version");
        FileWriter mockWriter = mock(FileWriter.class);
        sch.setFileWriterSupplierStudents(()->mockWriter);
        sch.exportStudents();
        verify(mockWriter,times(1)).write(anyString());
        verify(mockWriter,times(1)).close();
    }
    @Test
    void importStudents() throws IOException {
        List<Student> mockStudents = Arrays.asList(
                new Student("123","John"),
                new Student("1233", "Ann")
        );
        when(mockObjectMapper.readValue(any(File.class),ArgumentMatchers.<TypeReference<List<Student>>>any())).thenReturn(mockStudents);
        sch.importStudents();
        Assertions.assertTrue(sch.getStudents().containsAll(mockStudents));
    }


}

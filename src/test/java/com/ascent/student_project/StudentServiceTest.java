package com.ascent.student_project;

import com.ascent.student_project.entity.Student;
import com.ascent.student_project.repository.StudentRepo;
import com.ascent.student_project.service.StudentService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class StudentServiceTest {

    @Mock
    private StudentRepo studentRepo;

    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        studentService = new StudentService(studentRepo);
    }

    @Test
    public void testUpdateStudent() {
        int studentId = 1;
        Student exestingStudent = new Student(studentId, "Rohit", 80.0F, "ECE");
        Student updatedStudent = new Student(studentId, "Rohit C Asundi", 58.0f, "ECE");

        when(studentRepo.findById(studentId)).thenReturn(Optional.of(exestingStudent));
        studentService.updateStudent(studentId, updatedStudent);
        verify(studentRepo, times(1)).save(updatedStudent);
    }

    @Test
    public void testGetStudentById(){
        int studentId =1;
        Student getStudent = new Student(studentId, "Rohit", 69.0F, "ECE");
        when(studentRepo.findById(studentId)).thenReturn(Optional.of(getStudent));
        Student actualStudent = studentService.getStudentById(studentId);
        assertNotNull(actualStudent);
        assertEquals(actualStudent, getStudent);
    }

    @Test
    public void testCreateStudent(){
        Student createStudent = new Student(1, "Rohit", 69.0F, "ECE");
        studentService.createStudent(createStudent);
        verify(studentRepo, atLeastOnce()).save(createStudent);
    }

    @Test
    public void testGetAllStudents(){
        List<Student> getStudent = new ArrayList<>();
        getStudent.add(new Student(1, "Rohit", 69.0F, "ECE"));
        getStudent.add(new Student(2, "Rohan", 68.0F, "EEE"));

          when(studentRepo.findAll()).thenReturn(getStudent);

          List<Student> actualStudents = studentService.getAllStudents();

          assertEquals(getStudent.size(), actualStudents.size());
          assertEquals(getStudent, actualStudents);
    }

    @Test
    public void testDeleteStudent(){
        int studentId = 1;
        Student getStudent = new Student(studentId, "Rohit", 69.0F, "ECE");
        when(studentRepo.findById(studentId)).thenReturn(Optional.of(getStudent));
        StudentService spyStudentService = spy(studentService);
        studentService.deleteStudent(studentId);
        verify(studentRepo,atLeastOnce()).delete(getStudent);
        int nonExistingStudentId = 999;
        when(studentRepo.findById(nonExistingStudentId)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> studentService.deleteStudent(nonExistingStudentId));

    }

}

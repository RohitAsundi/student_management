package com.ascent.student_project.controller;
import com.ascent.student_project.entity.Student;
import com.ascent.student_project.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    StudentRepo studentRepo;

    //get all the students data
    @GetMapping("/students")
    public List<Student> getAllStudents() {
           List<Student> students = studentRepo.findAll();
           return students;
    }

    //get all the students data by ID
    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable int id){
        Student student = studentRepo.findById(id).get();
        return student;
    }

    // Creating or Adding new student details
    @PostMapping("/student/add")
    public String createStudent(@RequestBody Student student){
        studentRepo.save(student);
        return "Create Successfully";
    }

//    Updating the student data by id
    @PutMapping("/student/update/{id}")
    public String updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
        Optional<Student> optionalStudent = studentRepo.findById(id);
            Student student = optionalStudent.get();
            student.setName(updatedStudent.getName());
            student.setPercentage(updatedStudent.getPercentage());
            student.setBranch(updatedStudent.getBranch());
            studentRepo.save(student);
        return "Updated Successfully";
    }

    @DeleteMapping("/student/delete/{id}")
    public String deleteStudent (@PathVariable int id){
        Student student = studentRepo.findById(id).get();
        studentRepo.delete(student);
        return "Delete Successfully";
    }
}

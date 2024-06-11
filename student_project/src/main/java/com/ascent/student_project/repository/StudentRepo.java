package com.ascent.student_project.repository;

import com.ascent.student_project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepo extends JpaRepository<Student,Integer> {

}

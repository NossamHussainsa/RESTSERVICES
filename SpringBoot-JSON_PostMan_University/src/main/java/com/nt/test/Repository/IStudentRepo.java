package com.nt.test.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.test.Entity.Student;
@Repository
public interface IStudentRepo extends JpaRepository<Student, Integer> {

}

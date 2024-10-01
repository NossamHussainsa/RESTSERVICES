package com.nt.test.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.test.Entity.Course;
@Repository
public interface IcoursesRepo extends JpaRepository<Course, Integer> {

}

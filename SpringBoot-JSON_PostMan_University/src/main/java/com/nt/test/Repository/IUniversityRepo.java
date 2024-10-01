package com.nt.test.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.test.Entity.University;
@Repository
public interface IUniversityRepo extends JpaRepository<University, Integer> {

}

package com.nt.test.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.test.Entity.College;
@Repository
public interface ICollegeRepo extends JpaRepository<College, Integer> {

}

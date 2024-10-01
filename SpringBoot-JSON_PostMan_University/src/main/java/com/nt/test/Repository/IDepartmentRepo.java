package com.nt.test.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.test.Entity.Department;
@Repository
public interface IDepartmentRepo extends JpaRepository<Department, Integer> {

}

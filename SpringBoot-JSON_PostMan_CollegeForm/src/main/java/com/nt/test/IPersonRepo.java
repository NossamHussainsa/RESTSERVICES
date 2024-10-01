package com.nt.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.test.Entity.Person;
@Repository
public interface IPersonRepo extends JpaRepository<Person, Integer> {

}

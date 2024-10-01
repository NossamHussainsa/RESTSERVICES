package com.nt.test.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.test.IPersonRepo;
import com.nt.test.Entity.Person;

@Service
public class ServiceImpl implements Iservice{
	@Autowired
	private IPersonRepo repo;

	@Override
	public Person createPerson(Person person) {
		return repo.save(person);
	}

}

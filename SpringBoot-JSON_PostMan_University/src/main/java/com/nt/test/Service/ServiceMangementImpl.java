package com.nt.test.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.test.Entity.College;
import com.nt.test.Entity.Course;
import com.nt.test.Entity.Department;
import com.nt.test.Entity.Student;
import com.nt.test.Entity.University;
import com.nt.test.Repository.ICollegeRepo;
import com.nt.test.Repository.IDepartmentRepo;
import com.nt.test.Repository.IStudentRepo;
import com.nt.test.Repository.IUniversityRepo;
import com.nt.test.Repository.IcoursesRepo;
@Service
public class ServiceMangementImpl implements IServiceManagent {
@Autowired
private IUniversityRepo Unirepo;
@Autowired
private IDepartmentRepo Deprepo;
@Autowired
private ICollegeRepo Clgrepo;
@Autowired
private IcoursesRepo CouRepo;
@Autowired
private IStudentRepo stuRepo;
	@Override
	public University adduniversity(University university) {
		if(university.getColleges()!=null)
		{
			for(College college:university.getColleges()) {
				if(college.getDepartments()!=null) {
					for(Department department:college.getDepartments()) {
						if(department.getCourses()!=null) {
							for(Course course:department.getCourses()) {
								if(course.getStudents()!=null) {
									for(Student student:course.getStudents()) {
										stuRepo.save(student);
									}
								}
								CouRepo.save(course);
							}
						}
						Deprepo.save(department);
					}
				}
				Clgrepo.save(college);
			}
		}
	return	Unirepo.save(university);

	}

}

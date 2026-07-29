package com.luv2code.springboot.thymeleafdemo.service;

import java.util.List;
import java.util.Optional;

import com.luv2code.springboot.thymeleafdemo.dao.SequenceRepository;
import com.luv2code.springboot.thymeleafdemo.entity.SequenceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.thymeleafdemo.dao.EmployeeRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	private SequenceRepository sequenceRepository;
	
	@Autowired
	public EmployeeServiceImpl(
			EmployeeRepository theEmployeeRepository,
			SequenceRepository theSequenceRepository) {
		employeeRepository = theEmployeeRepository;
		sequenceRepository = theSequenceRepository;
	}
	
	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	public Employee findById(int theId) {
		Optional<Employee> result = employeeRepository.findById(theId);
		
		Employee theEmployee = null;
		
		if (result.isPresent()) {
			theEmployee = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find employee id - " + theId);
		}
		
		return theEmployee;
	}

	@Override
	public Employee save(Employee theEmployee) {

		SequenceEntity entity = new SequenceEntity();

		System.out.println("save前 : " + entity.getSequenceNumber());

		sequenceRepository.save(entity);

		System.out.println("save後 : " + entity.getSequenceNumber());

		Employee employee = employeeRepository.save(theEmployee);
		return employee;
	}

	@Override
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}

}







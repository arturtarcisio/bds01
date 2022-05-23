package com.devsuperior.bds01.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds01.dto.EmployeeDTO;
import com.devsuperior.bds01.entities.Department;
import com.devsuperior.bds01.entities.Employee;
import com.devsuperior.bds01.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	
	private final EmployeeRepository empRepository;

	@Autowired
	public EmployeeService(EmployeeRepository empRepository) {
		this.empRepository = empRepository;
	}
	
	@Transactional(readOnly = true)
	public Page<EmployeeDTO> findAllPaged (Pageable pageable) {		
		return empRepository.findAll(pageable)
				.map(EmployeeDTO::new);
	}
	
	@Transactional
	public EmployeeDTO insert(EmployeeDTO dto) {
		Employee entity = new Employee();
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setDepartment(new Department(dto.getDepartmentId(), null));
		
		entity = empRepository
				.saveAndFlush(entity);		
		
		return new EmployeeDTO(entity);
		
	}
	
	

}

package com.devsuperior.bds01.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds01.dto.DepartmentDTO;
import com.devsuperior.bds01.repositories.DepartmentRepository;

@Service
public class DepartmentService {
	
	private final DepartmentRepository depRepository;

	@Autowired
	public DepartmentService(DepartmentRepository depRepository) {
		this.depRepository = depRepository;
	}	
		
	@Transactional(readOnly = true)
	public List<DepartmentDTO> findAll () {			
		return depRepository.findAll(Sort.by("name"))
				.stream()
				.map(DepartmentDTO::new)
				.collect(Collectors.toList());
	}

}

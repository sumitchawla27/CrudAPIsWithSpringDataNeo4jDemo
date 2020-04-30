package com.example.crudapiswithneo4j.controller;


import com.example.crudapiswithneo4j.dtos.PersonDTO;
import com.example.crudapiswithneo4j.exceptionHandling.ResourceNotFoundException;
import com.example.crudapiswithneo4j.entity.Person;
import com.example.crudapiswithneo4j.repository.PersonRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/persons")
public class AppRestController {

	@Autowired
	PersonRepository personRepository;

	@Autowired
	ModelMapper modelMapper;

	@PostMapping
	public Person createPerson(@Valid @RequestBody PersonDTO personDTO) {
		Person person = new Person(personDTO.getName(), personDTO.getEmail(), personDTO.getPhoneNumber());
		return personRepository.save(person);
	}

	@PutMapping("/{id}")
	public Person updatePerson(@PathVariable("id") Long id, @Valid @RequestBody PersonDTO personDTO) {
		Person person = personRepository.findByIdAndDeleted(id, false).orElseThrow(() -> (new ResourceNotFoundException("Resource not found")));
		modelMapper.map(personDTO,person);
		return personRepository.save(person);
	}

	@PutMapping("/{id}/{teammateId}")
	public Person setConnectionWithOtherPerson(@PathVariable("id") Long id, @PathVariable("teammateId") Long teammateId ) {
		Person person = personRepository.findByIdAndDeleted(id,false).orElseThrow(() -> (new ResourceNotFoundException("Resource not found")));
		Person teamMate = personRepository.findByIdAndDeleted(teammateId,false).orElseThrow(() -> (new ResourceNotFoundException("TeamMate not found")));
		person.worksWith(teamMate);
		return personRepository.save(person);
	}

	@GetMapping("/{id}")
	public Person fetchPerson(@PathVariable("id") Long id) {
		return personRepository.findByIdAndDeleted(id, false).orElseThrow(() -> (new ResourceNotFoundException("Resource not found")));
	}

	//we are doing soft delete of Person Node entities
	@DeleteMapping("/{id}")
	public void deletePerson(@PathVariable("id") Long id) {
		Person person = personRepository.findById(id).orElseThrow(() -> (new ResourceNotFoundException("Resource not found")));
		person.setDeleted(true);
		personRepository.save(person);
	}
}

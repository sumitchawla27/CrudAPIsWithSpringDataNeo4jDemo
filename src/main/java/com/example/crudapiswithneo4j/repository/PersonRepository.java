package com.example.crudapiswithneo4j.repository;

import com.example.crudapiswithneo4j.entity.Person;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

	Optional<Person> findByIdAndDeleted(Long id, Boolean deleted);
}

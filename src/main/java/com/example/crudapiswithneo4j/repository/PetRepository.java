package com.example.crudapiswithneo4j.repository;

import com.example.crudapiswithneo4j.entity.Pet;

import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {

	Pet findByPetName(String petName);
}

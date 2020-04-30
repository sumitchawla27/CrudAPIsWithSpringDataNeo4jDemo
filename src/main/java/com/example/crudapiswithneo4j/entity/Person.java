package com.example.crudapiswithneo4j.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@NodeEntity
@Data
public class Person {

	@Id @GeneratedValue private Long id;

	@NotBlank
	private String name;

	@NotBlank
	@Email
	@Index(unique = true)
	private String email;

	@NotBlank
	@Size(min = 10)
	private String phoneNumber;

	@CreatedDate
	private LocalDateTime creationDateTime;

	@LastModifiedDate
	private LocalDateTime lastModifiedDateTime;

	private Boolean deleted = false;

	public Person() {
		// Empty constructor
	}

	public Person(String name, String email, String phoneNumber) {
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	@Relationship(type = "OWNS", direction = Relationship.UNDIRECTED)
	public Set<Pet> pets;


	public void addPet(Pet pet) {
		if (pets == null) {
			pets = new HashSet<>();
		}
		pets.add(pet);
	}


}

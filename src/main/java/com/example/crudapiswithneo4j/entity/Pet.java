package com.example.crudapiswithneo4j.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@NodeEntity
@Data
public class Pet {
	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	private String petName;
}

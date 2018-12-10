package com.example.b2w.starwars.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.b2w.starwars.entitys.Planeta;

public interface PlanetaRepository extends MongoRepository<Planeta, String> {
  
	Planeta findBy_id(ObjectId _id);
	
	@Query(value="{ 'nome' : ?0 }")
	  List<Planeta> findByNome(String nome);
		 
}


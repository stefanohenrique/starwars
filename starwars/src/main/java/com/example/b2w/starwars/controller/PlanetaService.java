package com.example.b2w.starwars.controller;

import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.b2w.starwars.entitys.Planeta;
import com.example.b2w.starwars.entitys.PlanetaDTO;
import com.example.b2w.starwars.repositories.PlanetaRepository;

public class PlanetaService {
	
	public PlanetaRepository aRepository;
	
	public PlanetaService() {};
	
	public PlanetaService(PlanetaRepository prRepository) {
		this.aRepository = prRepository;
	}
	
	public List<PlanetaDTO> getAllPlanetas() {
		PlanetaTaskActivity lPlanetaTaskActivity = new PlanetaTaskActivity(aRepository);
		
		return lPlanetaTaskActivity.getAllPlanetas();
	}
	
	public PlanetaDTO findPlanetaById(ObjectId id) {
		PlanetaTaskActivity lPlanetaTaskActivity = new PlanetaTaskActivity(aRepository);
  
		return lPlanetaTaskActivity.findPlanetaById(id);
	}
	
	public List<PlanetaDTO> findPlanetaByNome(String nome) {
		PlanetaTaskActivity lPlanetaTaskActivity = new PlanetaTaskActivity(aRepository);
		  
		return lPlanetaTaskActivity.findPlanetaByNome(nome);
	}
	
	
	public String createPlaneta(@Valid @RequestBody Planeta planeta) {
		
		PlanetaTaskActivity lPlanetaTaskActivity = new PlanetaTaskActivity(aRepository);
		  
		return lPlanetaTaskActivity.createPlaneta(planeta);
	}
	
	public String deletePlaneta(@PathVariable ObjectId id) {
		
		PlanetaTaskActivity lPlanetaTaskActivity = new PlanetaTaskActivity(aRepository);
		  
		return lPlanetaTaskActivity.deletePlaneta(id);
	}

}

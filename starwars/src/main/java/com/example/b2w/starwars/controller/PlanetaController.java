package com.example.b2w.starwars.controller;

import java.util.List;
import javax.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.b2w.starwars.entitys.Planeta;
import com.example.b2w.starwars.entitys.PlanetaDTO;
import com.example.b2w.starwars.repositories.PlanetaRepository;


@RestController
@RequestMapping("/planeta")
public class PlanetaController {
  
	@Autowired
	private PlanetaRepository repository;
	
	@RequestMapping(value = "getAll/", method = RequestMethod.GET)
	public List<PlanetaDTO> getAllPlanetas() {
		
		PlanetaService lPlanetaService = new PlanetaService(repository);
		return lPlanetaService.getAllPlanetas();
	}
	
	@RequestMapping(value = "findById/{id}", method = RequestMethod.GET)
	public PlanetaDTO findPlanetaById(@PathVariable("id") ObjectId id) {
		
		PlanetaService lPlanetaService = new PlanetaService(repository);
		return lPlanetaService.findPlanetaById(id);	  
	}

	@RequestMapping(value = "/findByName/{nome}", method = RequestMethod.GET)
	public List<PlanetaDTO> findPlanetaByNome(@PathVariable("nome") String nome) {

		PlanetaService lPlanetaService = new PlanetaService(repository);
		return lPlanetaService.findPlanetaByNome(nome);	  

	}
	 
	@RequestMapping(value = "create/", method = RequestMethod.POST)
	public String createPlaneta(@Valid @RequestBody Planeta planeta) {

		PlanetaService lPlanetaService = new PlanetaService(repository);
		return lPlanetaService.createPlaneta(planeta);	  		
	}
	 
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	public String deletePlaneta(@PathVariable ObjectId id) {

		PlanetaService lPlanetaService = new PlanetaService(repository);
		return lPlanetaService.deletePlaneta(id);	  		
	}
}



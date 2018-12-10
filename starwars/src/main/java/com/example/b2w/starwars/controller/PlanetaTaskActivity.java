package com.example.b2w.starwars.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.b2w.starwars.entitys.Planeta;
import com.example.b2w.starwars.entitys.PlanetaDTO;
import com.example.b2w.starwars.repositories.PlanetaRepository;

public class PlanetaTaskActivity {
	
	private static final String URLSEARCH = "https://swapi.co/api/planets/?search=";
	private static final String URLSEARCH_FINAL = "&format=json";
	private static int HTTP_COD_SUCESSO = 200;
	private final String USER_AGENT = "Mozilla/5.0";
	private Integer count = 0;
	private PlanetaRepository aRepository;
	
	public PlanetaTaskActivity() {};
	
	public PlanetaTaskActivity( PlanetaRepository prPlanetaRepository) {
		this.aRepository = prPlanetaRepository;
	}
	
	
	public List<PlanetaDTO> getAllPlanetas() {
		List<Planeta> lList = aRepository.findAll();
		List<PlanetaDTO> lcoDTO = new ArrayList<PlanetaDTO>();
		for (Planeta iPlaneta : lList) {
			PlanetaDTO lDTO = new PlanetaDTO(iPlaneta);
			lcoDTO.add(lDTO);
		}
		  return lcoDTO;
	}
	
	public PlanetaDTO findPlanetaById(@PathVariable("id") ObjectId id) {
		Planeta lPlaneta = aRepository.findBy_id(id);
		if(lPlaneta != null) {
			PlanetaDTO lDTO = new PlanetaDTO(lPlaneta);
			return lDTO;
		}else
			return null;
	}
	
	public List<PlanetaDTO> findPlanetaByNome(@PathVariable("nome") String nome) {
		List<Planeta> lList = aRepository.findByNome(nome);
		List<PlanetaDTO> lcoDTO = new ArrayList<PlanetaDTO>();
		for (Planeta iPlaneta : lList) {
			PlanetaDTO lDTO = new PlanetaDTO(iPlaneta);
			lcoDTO.add(lDTO);
		}
		return lcoDTO;
	}
	
	public String createPlaneta(@Valid @RequestBody Planeta planeta) {
		if(planeta.getClima() != null && !planeta.getClima().equals("") && planeta.getNome() != null && !planeta.getNome().equals("") 
				&& planeta.getTerreno() != null && !planeta.getTerreno().equals("")) {
			planeta.set_id(ObjectId.get());
			aRepository.save(planeta);
			return "Planeta criado com Sucesso";
		}
		else
			return "Planeta não criado";
	}
	
	public String deletePlaneta(@PathVariable ObjectId id) {
		Planeta lPlaneta = aRepository.findBy_id(id);
		if(lPlaneta != null) {
			aRepository.delete(lPlaneta);
			return "Planeta Apagado com Sucesso";
		}else
			return "Planeta Não Existe";
	}
	
	public Integer getFilmesByPlaneta(String prPlaneta) {
	
		try {
			 
			String lStringPartial = URLSEARCH.concat(prPlaneta);
			String lStringFinal = lStringPartial.concat(URLSEARCH_FINAL);
				        
	        URL url = new URL(lStringFinal);
	        HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", USER_AGENT);
	        
			if (con.getResponseCode() != HTTP_COD_SUCESSO) {
	            throw new RuntimeException("HTTP error code : "+ con.getResponseCode());
	        }
			
			
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
		    JSONObject my_obj = new JSONObject(response.toString());
			 
		    count = my_obj.getInt("count");
	
		    con.disconnect();
		
		    } catch (MalformedURLException e) {
		        e.printStackTrace();
		    } catch (IOException ex) {
		        ex.printStackTrace();
		    }
		

				return count;
		}
}

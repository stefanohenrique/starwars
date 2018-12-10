package com.example.b2w.starwars.entitys;

import com.example.b2w.starwars.controller.PlanetaTaskActivity;

public class PlanetaDTO {

	  public String _id;
	  public String nome;
	  public String clima;
	  public String terreno;
	  public Integer filmes;
	  
	  public PlanetaDTO(Planeta prPlaneta) {
		    this._id = prPlaneta.get_id();
		    this.nome = prPlaneta.getNome();
		    this.clima = prPlaneta.getClima();
		    this.terreno = prPlaneta.getTerreno();
		    this.filmes = getFilmes();
		  }
	
	  
	  
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getClima() {
		return clima;
	}
	
	public void setClima(String clima) {
		this.clima = clima;
	}
	
	public String getTerreno() {
		return terreno;
	}
	
	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}
	
	public Integer getFilmes() {
		PlanetaTaskActivity lPlanetaTaskActivity = new PlanetaTaskActivity();
		Integer filmes = lPlanetaTaskActivity.getFilmesByPlaneta(nome);
		return filmes;
	}
    
}

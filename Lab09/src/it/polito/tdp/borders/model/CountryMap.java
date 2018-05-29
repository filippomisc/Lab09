package it.polito.tdp.borders.model;

import java.util.*;

public class CountryMap {
	
	private Map<Integer,Country> countryMap;

	public CountryMap() {
		
		this.countryMap = new HashMap<>();
	}

	
	
	public Map<Integer, Country> getCountryMap() {
		return countryMap;
	}
	
	public Country getByID(int id) {
		return this.countryMap.get(id);
	}
	
//	public Country getByName(Country c) {
//		
//		return this.countryMap.ge
//		
//		
//	}
	

}

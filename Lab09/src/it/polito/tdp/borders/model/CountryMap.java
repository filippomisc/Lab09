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



	public Country getByObj(Country c) {
		Country cOld = this.countryMap.get(c.getCodiceContinente());
		if (cOld == null) {
			this.countryMap.put(c.getCodiceContinente(), c);
			return c;
		}
		return cOld;
	}
	
	public void put(Country c, int id) {
		this.countryMap.put(id, c);
	}
//	public Country getByName(Country c) {
//		
//		return this.countryMap.ge
//		
//		
//	}
	

}

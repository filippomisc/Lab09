package it.polito.tdp.borders.db;

import java.util.List;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.CountryMap;
import it.polito.tdp.borders.model.Border;

public class TestDAO {

	public static void main(String[] args) {

		BordersDAO dao = new BordersDAO();

		CountryMap cM = new CountryMap();

		List<Country> cs = dao.loadAllCountries(cM);
		System.out.println("nazioni: " + cs.size());
//		List<Country> countries = dao.loadAllCountries();
		
		List<Border> b = dao.getCountryPairs(1993);
		System.out.println("borders: " + b.size());
	}
}

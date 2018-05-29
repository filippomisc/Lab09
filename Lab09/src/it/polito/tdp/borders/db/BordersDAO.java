package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.CountryMap;

public class BordersDAO {
										//int cCode
	public List<Country> loadAllCountries(CountryMap cm) {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY ccode";
		List<Country> result = new ArrayList<Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				String name = rs.getString("StateNme");
				String abbr = rs.getString("StateAbb");
				int code = rs.getInt("ccode");
				
				Country c = new Country(code, abbr, name);
				System.out.format("%d %s %s\n", code, abbr, name);
				
				result.add(cm.getByObj(c));
			}

			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Border> getCountryPairs(int anno) {

		System.out.println("TODO -- BordersDAO -- getCountryPairs(int anno)");
		return new ArrayList<Border>();
	}
}

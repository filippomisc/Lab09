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
//				System.out.format("%d %s %s\n", code, abbr, name);
				
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

		String sql = "SELECT contiguity.state1no as s1, contiguity.state2no as s2\r\n" + 
				"FROM contiguity\r\n" + 
				"WHERE contiguity.conttype = 1\r\n" + 
				"AND contiguity.year<?\r\n" + 
				"AND contiguity.state1no<contiguity.state2no";
		
		List<Border> result = new ArrayList<Border>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				int state1no = rs.getInt("s1");
				int state2no  = rs.getInt("s2");
				
				Border b = new Border(state1no, state2no);
//				System.out.format("%d %s %s\n", code, abbr, name);
				
				result.add(b);
			}

			conn.close();
			
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
		
//		System.out.println("TODO -- BordersDAO -- getCountryPairs(int anno)");
//		return new ArrayList<Border>();
	}
	
	
	
}

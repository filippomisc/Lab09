package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.CountryIdMap;

public class BordersDAO {

	public List<Country> loadAllCountries(CountryIdMap cm) {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateNme";
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

	public List<Border> getCountryPairs(CountryIdMap cm, int anno) {

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
				
				Country c1 = cm.getByID(state1no);
				Country c2 = cm.getByID(state2no);
				
				// Just check that c1 and c2 object really exist, otherwise skip them
				if (c1 != null && c2 != null) {
					
					Border b = new Border(c1, c2);
					result.add(b);
					
				} else {
					System.out.println("Error skipping " + String.valueOf(state1no) + " - " + String.valueOf(state2no));
				}
							
//				System.out.format("%d %s %s\n", code, abbr, name);
				
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

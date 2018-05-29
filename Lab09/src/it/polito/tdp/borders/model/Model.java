package it.polito.tdp.borders.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private List<Country> countries;
	private Graph<Country, DefaultEdge> graph; //la Classe Border 
	private BordersDAO dao;

	public Model() {
		
		dao = new BordersDAO();
		
		
	}

	public void createGraph(int anno) {
		// TODO Auto-generated method stub
		
		//leggere la lista di oggetti
		this.countries = dao.loadAllCountries(CountryMap);
		
		
		//creare il grafo SimpleGraph()
		
		this.graph = new SimpleGraph<>(DefaultEdge.class);
		
		//aggiungere i vertici
		Graphs.addAllVertices(this.graph, this.countries);
		
		
		//aggiungere archi
		
		
		
	}

}

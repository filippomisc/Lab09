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
	private List<Border> borders;
	private CountryMap cM;
	


	public Model() {
		
		dao = new BordersDAO();
	}

	public void createGraph(int anno) {
		
		//leggere la lista di oggetti
		CountryMap cM = new CountryMap();
		this.countries = dao.loadAllCountries(cM);//lista di continenti
		
		
		//creare il grafo SimpleGraph()
		this.graph = new SimpleGraph<>(DefaultEdge.class);
		
		//aggiungere i vertici
		
		Graphs.addAllVertices(this.graph, this.countries);
//		System.out.println("vertici creati: " + this.graph.vertexSet().size());
		
		//aggiungere archi
		
		borders = dao.getCountryPairs(anno);
		
		for(Border bs : this.borders) {

//			Country c1 = countries.get(bs.getState1no());
//			Country c2 = countries.get(bs.getState2no());
			
			Country c1 = cM.getByID(bs.getState1no());
			Country c2 = cM.getByID(bs.getState2no());

			
			this.graph.addEdge(c1, c2);
			
	

			System.out.println(c1.getAbbrevStato()+" - "+c2.getAbbrevStato());
			
		}
				System.out.println(String.format("vertici creati: %d - archi creati: %d\n", this.graph.vertexSet().size(), this.graph.edgeSet().size()));
	}

	public void SetNumberNeighboringStates (Country c,int anno) {
		//this.createGraph(anno);
		List<Country> vicini = Graphs.neighborListOf(this.graph, this.cM.getByObj(c));
		System.out.println(vicini.size());
		this.cM.getByObj(c).setNumVicini(vicini.size());
		
	}

	public Graph<Country, DefaultEdge> getGraph() {
		return graph;
	}

	public List<Country> getCountries() {
		return countries;
	}

	
}

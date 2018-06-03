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
	private CountryIdMap cM;
	


	public Model() {
		
		dao = new BordersDAO();
		cM = new CountryIdMap();
	}

	public void createGraph(int anno) {
		
		//leggere la lista di oggetti
		this.countries = dao.loadAllCountries(cM);//lista di continenti
		
		
		//creare il grafo SimpleGraph()
		this.graph = new SimpleGraph<>(DefaultEdge.class);
		
		//aggiungere i vertici
		
		Graphs.addAllVertices(this.graph, this.countries);
//		System.out.println("vertici creati: " + this.graph.vertexSet().size());
		
		//aggiungere archi
		
		borders = dao.getCountryPairs(this.cM, anno);
		
		for(Border bs : this.borders) {
			//se non avessimo avuto un iDMap....

//			Country c1 = countries.get(bs.getState1no());
//			Country c2 = countries.get(bs.getState2no());
//			
//			Country c1 = cM.getByID(bs.getState1no());
//			Country c2 = cM.getByID(bs.getState2no());
			
			Country c1 = cM.getByObj(bs.getC1());
			Country c2 = cM.getByObj(bs.getC2());

			this.graph.addEdge(c1, c2);
			
//			System.out.println(c1.getAbbrevStato()+" - "+c2.getAbbrevStato());
			
		}
		
				System.out.println(String.format("vertici creati: %d - archi creati: %d\n", this.graph.vertexSet().size(), this.graph.edgeSet().size()));
				System.out.println();

				
	}

	public void SetNumberNeighboringStates (Country c,int anno) {
		//this.createGraph(anno);
		List<Country> vicini = Graphs.neighborListOf(this.graph, this.cM.getByObj(c));
//		System.out.println(vicini.size());
		this.cM.getByObj(c).setNumVicini(vicini.size());
		
	}

	public Graph<Country, DefaultEdge> getGraph() {
		return graph;
	}

	
	
	
	public List<Country> getCountries() {
		if(this.countries==null) {
			return new ArrayList<Country>();
			
		}
		return this.countries;
	}

	public String stampaVicini(int anno) {

		List<Country> vertici = getCountries();
		
		String result = "";
		
		for(Country c : vertici) {		
			SetNumberNeighboringStates(c,anno);

			result += c.toStringWithVicini();
		}	
		return result;
	}
	
	public String stampaVicini(Country c, int annoInt) {
		
		return null;
	}

	public int getVertex() {
		return this.graph.vertexSet().size();
	}
	
	public int getEdge() {
		return this.graph.edgeSet().size();
	}

	public List<Country> getVicini(Country c) {
		
		
		Country c1 = cM.getByObj(c);
		
		return Graphs.neighborListOf(this.graph, c1);
		
	}

	

	
}

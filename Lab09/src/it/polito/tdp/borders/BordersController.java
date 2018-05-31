/**
 * Skeleton for 'Borders.fxml' Controller Class
 */

package it.polito.tdp.borders;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BordersController {

	Model m;
	List<Country> country;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML
	private Button btnVicini;
	
	@FXML
	private ComboBox<Country> boxPaesi;
	
	@FXML // fx:id="txtAnno"
	private TextField txtAnno; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	
	
	
	
	/*
	 *non serve un evento per popolare la comboBox. possiamo farlo 
	 *direttamente in doCalcolaConfini() dopo aver creato il grafo
	 */
	private void setComboPaesi(){
		
//		country = m.getCountries();
//		
//		Collections.sort(country);
//		
//		this.boxPaesi.getItems().addAll(country);
//		
	}
	@FXML
	void doCalcolaConfini(ActionEvent event) {

		String anno = this.txtAnno.getText();
		int annoInt = 0;
		
		try {
			
			annoInt = Integer.parseInt(anno);
			
		}catch(NumberFormatException e) {
//			this.Reset(event);
			this.txtResult.setText("il formato inserito non è corretto\n");
			return;
		}
		
		if(annoInt < 1816 || annoInt > 2006) {
			this.txtResult.setText("l'anno inserito non è presente nel DB\n");
		}else {

			
		m.createGraph(annoInt);
		txtResult.appendText(String.format("Grafo creato con %d vertici e %d archi.\n", m.getVertex(), m.getEdge()));
		txtResult.appendText("\n");

		txtResult.appendText(m.getVicini());
		
		//this.setComboPaesi();
		
		country = m.getCountries();
		this.boxPaesi.getItems().addAll(country);
		
		}
		
	}
	
    @FXML
    void trovaTuttiIVicini(ActionEvent event) {
    	
    }

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Borders.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Borders.fxml'.";
	}

	public void setModel(Model m) {
		this.m = m; 
	}
}

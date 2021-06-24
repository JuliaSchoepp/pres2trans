package control;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Journal;
import model.Transaction;

/**
 * Controller - aktualisiert Observable List, lädt und speichert
 * @author Wiener, Goldfuß, Schöpp
 * @version 11.0.9, 06/2021
 */

public class Controller {
	
	public Journal journal;
	
	public Controller() {
		this.journal = new Journal();
	}
	
	// Load an existing csv file into the table view
	public ObservableList<Transaction> load(Stage primaryStage) {
		// FileChooser, um Datei auszuwählen
		FileChooser ch = new FileChooser();
		ch.setTitle("Datei öffnen...");
		File file = ch.showOpenDialog(primaryStage);
		this.journal.open(file);
		// Achtung: Liste wird ersetzt, nicht angehängt (falls schon Transaktion manuell eingefügt wurde)
		ObservableList<Transaction> tableData = FXCollections.observableArrayList(this.journal.getTransactions());
		return tableData;
	}
	
	// save the updated journal
	public void save(Stage primaryStage) {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showSaveDialog(primaryStage);
		String text = this.journal.toString();
		
		if (file != null) {
			try {
				FileWriter writer = new FileWriter(file);
				writer.write(text);
				writer.close();
			} catch (IOException ex) {
				System.out.println("Fehler beim Speichern!");
			}
		}
	}
	
	// Submit transaction data entered in the textfield to view and model
	public Transaction submit(String text, String debitAc, String amount, String creditAc) {
		Transaction t = new Transaction(text, debitAc, Double.valueOf(amount), creditAc, Double.valueOf(amount));
		System.out.println(t);
		this.journal.addTransaction(t);
		return t;
	}

}

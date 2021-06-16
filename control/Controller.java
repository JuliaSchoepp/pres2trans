package control;
import java.io.File;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Journal;
import model.Transaction;

public class Controller {

	public static ObservableList<Transaction> load(Stage primaryStage) {
		FileChooser ch = new FileChooser();
		ch.setTitle("Datei öffnen...");
		File file = ch.showOpenDialog(primaryStage);
		if (file == null) return null;
		Journal journal = new Journal();
		journal.open(file);
		ObservableList<Transaction> tableData = FXCollections.observableArrayList(journal.getTransactions());
		
		return tableData;
	}
	

	public static void save() {
		// TODO Auto-generated method stub
	}
	
	public static void loadTableData(Journal journal){
		
	}


	public static Object neu() {
		// Eingabemaske
		// Objekt erzeugen
		// Zu Journal
		// Zu Observable List
		// Journal über vorhandene Methode abspeichern
		// TableView updaten?
		return null;
	}

}

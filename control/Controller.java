package control;
import java.io.File;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Journal;
import model.Transaction;

public class Controller {
	
	public Journal journal;
	
	
	public ObservableList<Transaction> load(Stage primaryStage) {
		this.journal = new Journal();
		// FileChooser, um Datei auszuwählen
		FileChooser ch = new FileChooser();
		ch.setTitle("Datei öffnen...");
		File file = ch.showOpenDialog(primaryStage);
		if (file == null) return null;
		this.journal.open(file);
		ObservableList<Transaction> tableData = FXCollections.observableArrayList(this.journal.getTransactions());
		return tableData;
	}
	

	public void save() {
		// TODO Auto-generated method stub
	}
	



	public void submit(String text, String debitAc, String amount, String creditAc) {
		Transaction t = new Transaction(text, debitAc, Double.valueOf(amount), creditAc, Double.valueOf(amount));
		System.out.println("Submit");
		this.journal.addTransaction(t);
		
	}

}

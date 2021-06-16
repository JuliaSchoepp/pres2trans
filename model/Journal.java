package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Journal {
	
	ArrayList<Transaction> journal = new ArrayList<Transaction>();	// list with all transactions due to the journal
	

	public Journal() {
	}
	
	// a method which takes a transaction properties as parameters and add them to the list
	public void addTransaction (String text, String debitAccount, double debitAmount, String creditAccount, double creditAmount) {
		Transaction t = new Transaction(text, debitAccount, debitAmount, creditAccount, creditAmount);
		journal.add(t);	
	}
	
	// method which adds a transaction object to the list
	public void addTransaction (Transaction t) {
		journal.add(t);
	}
	
	
	//a method to get the balance of a specific account
	public double getBalance (String account, double startingBalance) {	
		double balance = startingBalance;
		for (Transaction t : journal) {
			if (t.getCreditAccount().equals(account))
				balance += t.getCreditAmount();
			if(t.getDebitAccount().equals(account))
				balance -= t.getDebitAmount();
		}
		return balance;
	}
	
	//a method to print out all the transactions according to the account
	public void getTransactions(String account) {
		for (Transaction t: journal) {
			if (t.getCreditAccount().equals(account) ||t.getDebitAccount().equals(account))
				System.out.println(t.toString());
		}
	}
	
	
	// getTransactions playing arount with Streams API
	public List<Transaction> getTransactionsList(String account) {
		return journal.stream()
					.filter(t -> t.getCreditAccount().equals(account)||t.getCreditAccount().equals(account))
					.collect(Collectors.toList());
	}
	
	public ArrayList<Transaction> getTransactions(){
		return this.journal;
	}
	
	@Override
	public String toString() {
		StringBuilder text = new StringBuilder();
		for (Transaction t : journal) {
			text.append(t.getNr() + "\t");
			text.append(t.getDate() + "\t");
			text.append(t.getText() + "\t");
			text.append(t.getDebitAccount()+"\t");
			text.append(t.getDebitAmount()+"\t");
			text.append(t.getCreditAccount()+"\t");
			text.append(t.getCreditAmount()+"\n");
		}
		return text.toString();
	}
	
	
	
	// method to save the journal in a file
	public void save () {
		try {
				//String erstellen
				String text = this.toString();
							
				//Ausgabestrom erzeugen
				BufferedWriter bWriter = new BufferedWriter (new FileWriter("journal.csv"));
				
				bWriter.write(text);
				
				bWriter.close();
				
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// method to save the journal in a file, the user chooses the path and the filename
	public void saveName () {
		try {
				
			//Eingabestrom erzeugen	
			BufferedReader bReader = new BufferedReader(new InputStreamReader (System.in));
			
			System.out.println("SAVING : Please insert path: ");
			
			String path = bReader.readLine();
			
			System.out.println("SAVING : Please insert filename: ");
			
			path += File.separator;
			path += bReader.readLine()+".csv";
			
			//String erstellen
			String text = this.toString();
						
			//Ausgabestrom erzeugen
			BufferedWriter bWriter = new BufferedWriter (new FileWriter(path));
			
			bWriter.write(text);
			
			bWriter.close();
			
			System.out.println("Saving completed - Filename = " + path);
				
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//load a journal file and print the entries on the console
	public void open(File file) {
		
		try {

		//Dateieingabestrom erzeugen, um Datei auszulesen
		BufferedReader bReader = new BufferedReader(new InputStreamReader (new FileInputStream(file)));
			
		//Zeilenweise einlesen, in Journal speichern & ausgeben
		String line;
		while (bReader.ready()) {
			line = bReader.readLine();
			//System.out.println(line);
			String[] tr = line.split("\t");
			
			// build transaction object and add to Journal
			int nr = Integer.valueOf(tr[0]);
			LocalDate date = LocalDate.parse(tr[1]);
			String text = tr[2];
			String debitAccount = tr[3];
			double debitAmount = Double.valueOf(tr[4]);
			String creditAccount = tr[5];
			double creditAmount = Double.valueOf(tr[6]);
			Transaction t = new Transaction(nr, text, date, debitAccount, debitAmount, creditAccount, creditAmount);
			this.addTransaction(t);
	    }
		
		bReader.close();
		
			
		} catch (Exception e) {
			System.out.println("Fehler - bitte überprüfen, ob Dateipfad korrekt ist.");
			e.printStackTrace();
		}
		
	}
	
	public static ArrayList<Transaction> getTestData(){
		Journal testJournal = new Journal();
		testJournal.addTransaction("Buchung 1", "sales", 250, "supplies", 250);
		testJournal.addTransaction("Buchung 2", "cash", 120, "bank", 120);
		testJournal.addTransaction("Buchung 3", "cash", 300, "sales", 300);
		testJournal.addTransaction("Buchung 4", "bank", 50, "cash", 50);
		return testJournal.getTransactions();
	}
	
	public static void main(String[] args) {
		Journal testJournal = new Journal();
		testJournal.addTransaction("Buchung 1", "sales", 250, "supplies", 250);
		testJournal.addTransaction("Buchung 2", "cash", 120, "bank", 120);
		testJournal.addTransaction("Buchung 3", "cash", 300, "sales", 300);
		testJournal.addTransaction("Buchung 4", "bank", 50, "cash", 50);
		
		System.out.println(testJournal);
		testJournal.save();
	}

}

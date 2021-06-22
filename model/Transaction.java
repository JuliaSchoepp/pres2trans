package model;

import java.time.LocalDate;

/**
 * Datenklasse für Transaktion
 * @author Wiener, Goldfuß, Schöpp
 * @version 11.0.9, 06/2021
 */

public class Transaction {
	
	private static int nrCounter= 0;
	private int nr;    		      	// number of transaction
	private LocalDate date; 		// transaction date
	private String text;	        // transaction text
	private String debitAccount;  	// the debit account
	private double debitAmount;   	// debit amount
	private String creditAccount;   // the credit account
	private double creditAmount;    // debit amount
	
	public Transaction (String text, String debitAccount, double debitAmount, String creditAccount, double creditAmount) {
		this.text = text;
		this.debitAccount = debitAccount;
		this.debitAmount = debitAmount;
		this.creditAccount = creditAccount;
		this.creditAmount = creditAmount;
		this.date = LocalDate.now();
		this.nr = nrCounter;
		nrCounter++;
	}
	
	// constructor incl date for reading in file
	public Transaction (int nr, String text, LocalDate date, String debitAccount, double debitAmount, String creditAccount, double creditAmount) {
		this.nr = nr;
		this.text = text;
		this.debitAccount = debitAccount;
		this.debitAmount = debitAmount;
		this.creditAccount = creditAccount;
		this.creditAmount = creditAmount;
		this.date = date;
		nrCounter++;
	}
	
	public int getNr() {
		return nr;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getText() {
		return text;
	}

	public String getDebitAccount() {
		return debitAccount;
	}

	public double getDebitAmount() {
		return debitAmount;
	}

	public String getCreditAccount() {
		return creditAccount;
	}

	public double getCreditAmount() {
		return creditAmount;
	}

	public static int getNrCounter() {
		return nrCounter;
	}

	public static void setNrCounter(int nrCounter) {
		Transaction.nrCounter = nrCounter;
	}

	public void setNr(int nr) {
		this.nr = nr;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setDebitAccount(String debitAccount) {
		this.debitAccount = debitAccount;
	}

	public void setDebitAmount(double debitAmount) {
		this.debitAmount = debitAmount;
	}

	public void setCreditAccount(String creditAccount) {
		this.creditAccount = creditAccount;
	}

	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}

	@Override
	public String toString() {
		return ("Nummer: " + this.nr + 
				" Datum: " + this.date +
				" Text: " + this.text +
				" DebitAccount: " + this.debitAccount +
				" DebitAmount: " + this.debitAmount +
				" CreditAccount: " + this.creditAccount +
				" CreditAmount: " + this.creditAmount);
	}
	
	public static void main(String[] args) {
		// for testing
		Transaction test = new Transaction ("Transaction test ", "Cash ", 100, "Sales ", 160.30 );
		System.out.println (test);
	}
}

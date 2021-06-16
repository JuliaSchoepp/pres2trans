package view;
	
import java.time.LocalDate;

import control.Controller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.Journal;
import model.Transaction;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private TableView<Transaction> table = new TableView<>();
	private ObservableList<Transaction> tableData;
	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			
			// Top Menu
			MenuBar bar = new MenuBar();
			Menu save = new Menu("Datei");
			MenuItem laden = new MenuItem("Laden");
			laden.setOnAction(e -> {
				this.tableData = Controller.load(primaryStage);
			});
			MenuItem speichern = new MenuItem("Speichern");
			speichern.setOnAction(e -> Controller.save());
			MenuItem neu = new MenuItem("Neue Transaktion");
			neu.setOnAction(e -> Controller.neu());
			save.getItems().addAll(laden,speichern, neu);
			bar.getMenus().add(save);
			root.setTop(bar);
			
			// Table View: Columns
			TableColumn<Transaction, Integer> nrCol = new TableColumn<>("Nr.");
			nrCol.setMinWidth(100);
			nrCol.setCellValueFactory(new PropertyValueFactory<>("nr"));
			TableColumn<Transaction, LocalDate> dateCol = new TableColumn<>("Datum");
			dateCol.setMinWidth(150);
			dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
			TableColumn<Transaction, String> textCol = new TableColumn<>("Text");
			textCol.setMinWidth(200);
			textCol.setCellValueFactory(new PropertyValueFactory<>("text"));
			TableColumn<Transaction, String> debAcCol = new TableColumn<>("Debit Account");
			debAcCol.setMinWidth(200);
			debAcCol.setCellValueFactory(new PropertyValueFactory<>("debitAccount"));
			TableColumn<Transaction, Double> debAmCol = new TableColumn<>("Debit Amount");
			debAmCol.setMinWidth(200);
			debAmCol.setCellValueFactory(new PropertyValueFactory<>("debitAmount"));
			TableColumn<Transaction, String> credAcCol = new TableColumn<>("Credit Account");
			credAcCol.setMinWidth(200);
			credAcCol.setCellValueFactory(new PropertyValueFactory<>("creditAccount"));
			TableColumn<Transaction, Double> credAmCol = new TableColumn<>("Credit Amount");
			credAmCol.setMinWidth(200);
			credAmCol.setCellValueFactory(new PropertyValueFactory<>("creditAmount"));
			
			table.setItems(this.tableData);
			table.getColumns().addAll(nrCol,dateCol, textCol,debAcCol, debAmCol, credAcCol, credAmCol);
			
			root.setCenter(table);
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setWidth(1250);
			primaryStage.setTitle("Übersicht Transaktionen");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

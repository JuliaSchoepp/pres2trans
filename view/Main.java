package view;
	
import java.time.LocalDate;

import control.Controller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import model.Journal;
import model.Transaction;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class Main extends Application {
	
	private TableView<Transaction> table = new TableView<>();
	private ObservableList<Transaction> tableData;
	private Stage primaryStage;
	private Controller controller = new Controller();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			
			// Top Menu
			MenuBar bar = new MenuBar();
			Menu save = new Menu("File");
			MenuItem laden = new MenuItem("Load");
			laden.setOnAction(e -> {
				this.tableData = this.controller.load(primaryStage);
				table.setItems(this.tableData);
			});
			MenuItem speichern = new MenuItem("Save");
			speichern.setOnAction(e -> controller.save());
			
			save.getItems().addAll(laden,speichern);
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
			
			GridPane pane = new GridPane();
			Label text = new Label("Text");
			pane.add(text, 0, 0);
			Label debitAc = new Label("Debit Account");
			pane.add(debitAc, 1, 0);
			Label amount = new Label("Amount");
			pane.add(amount, 2, 0);
			Label creditAc = new Label("Credit Account");
			pane.add(creditAc, 3, 0);
			
			TextField textF = new TextField();
			pane.add(textF, 0, 1);
			TextField debitAcF = new TextField();
			pane.add(debitAcF, 1, 1);
			TextField amountF = new TextField();
			pane.add(amountF, 2, 1);
			TextField creditAcF = new TextField();
			pane.add(creditAcF, 3, 1);
			
			Button submit = new Button("Submit");
			pane.add(submit, 0, 2);
			submit.setOnAction(e -> {
				//Controller.submit(textF.getText(), debitAcF.getText(), amountF.getText(), creditAcF.getText());
				System.out.println("submit");
				});
			
			pane.setPadding(new Insets(10,10,10,10));
			pane.setVgap(8);
			pane.setHgap(10);
			
			root.setBottom(pane);
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

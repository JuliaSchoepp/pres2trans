package view;
	
import control.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Transaction;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private TableView<Transaction> table = new TableView<>();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			
			// Top Menu
			MenuBar bar = new MenuBar();
			Menu save = new Menu("Datei");
			MenuItem laden = new MenuItem("Laden");
			laden.setOnAction(e -> Controller.load());
			MenuItem speichern = new MenuItem("Speichern");
			laden.setOnAction(e -> Controller.save());
			save.getItems().addAll(laden,speichern);
			bar.getMenus().add(save);
			root.setTop(bar);
			
			// Table View
			table.setEditable(true);
			TableColumn nrCol = new TableColumn("Nr.");
			nrCol.setMinWidth(100);
			TableColumn dateCol = new TableColumn("Datum");
			dateCol.setMinWidth(150);
			TableColumn textCol = new TableColumn("Text");
			textCol.setMinWidth(200);
			TableColumn debAcCol = new TableColumn("Debit Account");
			debAcCol.setMinWidth(200);
			TableColumn debAmCol = new TableColumn("Debit Amount");
			debAmCol.setMinWidth(200);
			TableColumn credAcCol = new TableColumn("Credit Account");
			credAcCol.setMinWidth(200);
			TableColumn credAmCol = new TableColumn("Credit Amount");
			credAmCol.setMinWidth(200);
			
			
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

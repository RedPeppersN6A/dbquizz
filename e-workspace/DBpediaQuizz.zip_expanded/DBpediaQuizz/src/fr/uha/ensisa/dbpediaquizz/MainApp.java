package fr.uha.ensisa.dbpediaquizz;

import java.io.IOException;

import fr.uha.ensisa.dbpediaquizz.view.QuestionOverviewController;
import fr.uha.ensisa.dbpediaquizz.view.ScoreDialogController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private AnchorPane quizzOverview;

	public MainApp() {
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Quizz");

		initQuizzOverview();
	}

	/**
	 * Initializes the layout.
	 */
	public void initQuizzOverview() {
		try {
			// Load quizz layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/QuizzOverview.fxml"));
			quizzOverview = loader.load();
			
			QuestionOverviewController controller = loader.getController();
	        controller.setMainApp(this);

			// Show the scene containing the quizz layout.
			Scene scene = new Scene(quizzOverview);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void showScoreDialog (int Score, String message) {
		try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/ScoreDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Score");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Set the person into the controller.
	        ScoreDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setVariable(Score, message);

	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
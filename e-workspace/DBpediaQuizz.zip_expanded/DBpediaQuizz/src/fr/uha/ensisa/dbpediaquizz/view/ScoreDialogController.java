package fr.uha.ensisa.dbpediaquizz.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ScoreDialogController {

    @FXML
    private Label scorePoint;
    @FXML
    private Label messageLabel;
    
    private Stage dialogStage;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setVariable (int score, String message){
    	scorePoint.setText(String.valueOf(score));
    	messageLabel.setText(message);
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        this.dialogStage.close();
    }
}
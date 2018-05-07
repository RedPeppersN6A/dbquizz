package fr.uha.ensisa.dbpediaquizz.view;

import java.util.ArrayList;

import fr.uha.ensisa.dbpediaquizz.MainApp;
import fr.uha.ensisa.dbpediaquizz.questionsBean.QuestionBean;
import fr.uha.ensisa.dbpediaquizz.questionsBean.QuestionFactoryBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;

public class QuestionOverviewController {

	protected QuestionBean currentQuestion;
	private int currentQuestion_index=0;
	private int score=0;
	private ArrayList<QuestionBean> generatedQuestion = new ArrayList<QuestionBean>();
	private boolean isGenerated = false;
	private int NB_QUESTIONS=10;

	@FXML
	private ProgressBar starting;

	@FXML
	private Label categoryLabel;
	@FXML
	private Label nbrQuestionsLabel;
	@FXML
	private Label enonceLabel;
	@FXML
	private Label resultatLabel;
	@FXML
	private Label bonneReponseLabel;
	@FXML
	private Label avancementLabel;

	@FXML
	private Button validerB;
	@FXML
	private Button questionSuivanteB;
	@FXML
	private Button nouvellePartieB;
	@FXML
	private Button quitterB;

	@FXML
	private RadioButton reponseRB00;
	@FXML
	private RadioButton reponseRB01;
	@FXML
	private RadioButton reponseRB10;
	@FXML
	private RadioButton reponseRB11;

	@FXML
	private Slider categoryS;

	final ToggleGroup group = new ToggleGroup();

	private void showQuestionDetails(QuestionBean question) {

		if (question != null) {
			// Fill the labels
			categoryLabel.setText(String.valueOf(question.getCategorie()));
			enonceLabel.setText(question.getEnonce());
			reponseRB00.setText(question.getReponse(0));
			reponseRB01.setText(question.getReponse(1));
			reponseRB10.setText(question.getReponse(2));
			reponseRB11.setText(question.getReponse(3));
			reponseRB00.setUserData(question.getReponse(0));
			reponseRB01.setUserData(question.getReponse(1));
			reponseRB10.setUserData(question.getReponse(2));
			reponseRB11.setUserData(question.getReponse(3));
		} else {
			categoryLabel.setText("");
			enonceLabel.setText("");
			reponseRB00.setText("");
			reponseRB01.setText("");
			reponseRB10.setText("");
			reponseRB11.setText("");
			avancementLabel.setText("00/00");
			resultatLabel.setText("");
			bonneReponseLabel.setText("");
		}
	}

	// Reference to the main application.
	private MainApp mainApp;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public QuestionOverviewController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		showQuestionDetails(null);
		nbrQuestionsLabel.setText(String.valueOf((int)categoryS.getValue()));
		reponseRB00.setToggleGroup(group);
		reponseRB01.setToggleGroup(group);
		reponseRB10.setToggleGroup(group);
		reponseRB11.setToggleGroup(group);
	}

	@FXML
	private void slide() {
		nbrQuestionsLabel.setText(String.valueOf((int)categoryS.getValue()));
		this.NB_QUESTIONS=(int) categoryS.getValue();
		
	}
	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	@FXML
	private void begin() {
		int indexQuestion = 0;
		currentQuestion_index=0;
		generatedQuestion.clear();
		while(indexQuestion<NB_QUESTIONS)
		{
			indexQuestion++;
			starting.setProgress((indexQuestion-1)*100/NB_QUESTIONS);
			generatedQuestion.add(QuestionFactoryBean.createQuestion());
		}
		currentQuestion = generatedQuestion.get(currentQuestion_index);
		showQuestionDetails(currentQuestion);
		avancementLabel.setText(currentQuestion_index+1 + "/" + NB_QUESTIONS);

		resultatLabel.setText("");
		bonneReponseLabel.setText("");
		
		reponseRB00.setDisable(false);
		reponseRB01.setDisable(false);
		reponseRB10.setDisable(false);
		reponseRB11.setDisable(false);

		isGenerated = true;
	}
	@FXML
	private void validate() {
		if (group.getSelectedToggle() != null) {
			if(group.getSelectedToggle().getUserData().equals(currentQuestion.getReponse(currentQuestion.getBonneReponseIndex()))) {
				resultatLabel.setText("Bien joué");
				score+=1;
			}
			else {
				resultatLabel.setText("Raté");
				bonneReponseLabel.setText(currentQuestion.getReponse(currentQuestion.getBonneReponseIndex()));
			}
		}
		reponseRB00.setDisable(true);
		reponseRB01.setDisable(true);
		reponseRB10.setDisable(true);
		reponseRB11.setDisable(true);
	}
	@FXML
	private void nextQuestion() {
		if(currentQuestion_index<NB_QUESTIONS-1 && isGenerated) {
			currentQuestion_index++;
			currentQuestion = generatedQuestion.get(currentQuestion_index);
			showQuestionDetails(currentQuestion);
			avancementLabel.setText(currentQuestion_index+1 + "/" + NB_QUESTIONS);

			resultatLabel.setText("");
			bonneReponseLabel.setText("");
			
			reponseRB00.setDisable(false);
			reponseRB01.setDisable(false);
			reponseRB10.setDisable(false);
			reponseRB11.setDisable(false);
		}else if(currentQuestion_index==NB_QUESTIONS-1) {
			mainApp.showScoreDialog(score, "Enfin Fini ! Facile !");
			starting.setProgress(0);
		}
	}
	@FXML
	private void Quit() {
		System.exit(0);     
	}
}

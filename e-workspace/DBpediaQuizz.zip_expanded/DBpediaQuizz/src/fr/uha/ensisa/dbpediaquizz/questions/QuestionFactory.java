package fr.uha.ensisa.dbpediaquizz.questions;

public class QuestionFactory {

	public static Question createQuestion()
	{
		int questionType= (int)(Math.random()*8);
		Question question;
		switch(questionType)
		{
			case 0 : 	question=new QuestionCapitale();
						break;
			case 1 :	question=new QuestionRoiEtPredecesseur();
						break;			
			case 2 : 	question= new QuestionChampionnatFranceFootball();
						break;
			case 3 :	question= new QuestionChanteur();
						break;
			case 4 :    question= new QuestionLitterature();
						break;
			case 5 :	question= new QuestionCompany();
						break;
			case 6 :    question= new QuestionCoordonnees();
						break;
			default :   question = new QuestionFiction();
		}
		return question;
	}
	
}

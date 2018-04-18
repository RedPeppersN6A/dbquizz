package fr.uha.ensisa.dbpediaquizz.questions;

public class QuestionFactory {

	public static Question createQuestion()
	{
		int questionType= (int)(Math.random()*5);
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
			default :   question= new QuestionLitterature();
			
		}
		//question= new QuestionChampionnatFranceFootball();
		//question=new QuestionCapitale();
		return question;
	}
	
}

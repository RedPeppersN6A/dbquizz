package fr.uha.ensisa.dbpediaquizz.questions;

public class QuestionFactory {

	public static Question createQuestion()
	{
		int questionType= (int)(Math.random()*4);
		Question question;
		switch(questionType)
		{
			case 0 : 	question=new QuestionCapitale();
						break;
			case 1 :	question=new QuestionRoiEtPredecesseur();
						break;			
			case 2 : 	question= new QuestionChampionnatFranceFootball();
						break;
			default :	question= new QuestionChanteur();
		}
		//question= new QuestionChampionnatFranceFootball();
		//question=new QuestionCapitale();
		return question;
	}
	
}

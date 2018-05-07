package fr.uha.ensisa.dbpediaquizz.questionsBean;

public class QuestionFactoryBean {

	public static QuestionBean createQuestion()
	{
		int questionType= (int)(Math.random()*8);
		QuestionBean question;
		switch(questionType)
		{
			case 0 : 	question=new QuestionCapitaleBean();
						break;
			case 1 :	question=new QuestionRoiEtPredecesseurBean();
						break;			
			case 2 : 	question= new QuestionChampionnatFranceFootballBean();
						break;
			case 3 :	question= new QuestionChanteurBean();
						break;
			case 4 :    question= new QuestionLitteratureBean();
						break;
			case 5 :	question= new QuestionCompanyBean();
						break;
			case 6 :    question= new QuestionCoordonneesBean();
						break;
			default :   question = new QuestionFictionBean();
		}
		return question;
	}
	
}

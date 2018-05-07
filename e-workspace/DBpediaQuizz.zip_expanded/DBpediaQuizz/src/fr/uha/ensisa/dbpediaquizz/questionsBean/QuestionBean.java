package fr.uha.ensisa.dbpediaquizz.questionsBean;

import java.util.Arrays;

import fr.uha.ensisa.dbpediaquizz.util.Constantes;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.BooleanProperty;

public abstract class QuestionBean {

	protected StringProperty categorie;
	protected StringProperty enonce;
	protected String bonneReponse;
	protected String[] mauvaisesReponses;
	protected BooleanProperty choixBP;
	protected String[] reponses= new String[Constantes.NB_REPONSES];
	protected int bonneReponseIndex = (int)(Math.random()*Constantes.NB_REPONSES);

	public QuestionBean(int categorie)
	{
		this.categorie=new SimpleStringProperty(Constantes.CATEGORIES[categorie]);
		this.mauvaisesReponses=new String[3];
	}

	protected boolean reponseAbsente(String nouvelleReponse)
	{
		boolean absent=true;
		if((this.bonneReponse).equalsIgnoreCase(nouvelleReponse))
		{
			absent=false;
		}
		else
		{
			for(int i=0; i<mauvaisesReponses.length; i++)
			{
				if(this.mauvaisesReponses[i]!=null&&this.mauvaisesReponses[i].equalsIgnoreCase(nouvelleReponse))
				{
					absent=false;
				}
			}
		}
		return absent;
	}

	public String getCategorie() {
		return categorie.get();
	}
	public StringProperty categorieProperty() {
		return categorie;
	}
	public StringProperty enonceProperty() {
		return enonce;
	}

	public String getEnonce() {
		return enonce.get();
	}

	public String getReponse(int i) {

		Arrays.fill(reponses, null);
		reponses[bonneReponseIndex]=bonneReponse;
		int mauvaisesReponsesPlacees=0;
		while(mauvaisesReponsesPlacees<Constantes.NB_REPONSES-1)
		{
			int index=(int)(Math.random()*Constantes.NB_REPONSES);
			if(reponses[index]==null)
			{
				reponses[index]=mauvaisesReponses[mauvaisesReponsesPlacees];
				mauvaisesReponsesPlacees++;
			}
		}
		return reponses[i];
	}
	public int getBonneReponseIndex() {
		return this.bonneReponseIndex;
	}
}

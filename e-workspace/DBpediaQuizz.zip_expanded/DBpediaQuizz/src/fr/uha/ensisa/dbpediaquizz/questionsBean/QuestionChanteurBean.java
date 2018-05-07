package fr.uha.ensisa.dbpediaquizz.questionsBean;

import java.util.List;

import org.apache.jena.query.QuerySolution;

import fr.uha.ensisa.dbpediaquizz.util.Constantes;
import fr.uha.ensisa.dbpediaquizz.util.DBpediaQuery;
import javafx.beans.property.SimpleStringProperty;

public class QuestionChanteurBean extends QuestionBean {

	public QuestionChanteurBean()
	{
		super(Constantes.MUSIQUE);
		//Récupère toute les chanteurs et chansons
		String requete = "select distinct ?nomSinger ?nomSingle where {?singer <http://dbpedia.org/ontology/artist> ?single. "
				+ "?singer <http://www.w3.org/2000/01/rdf-schema#label> ?nomSinger. "
				+ "?single <http://www.w3.org/2000/01/rdf-schema#label> ?nomSingle. "
				+ "FILTER (lang(?nomSingle) = 'fr') "
				+ "FILTER (lang(?nomSinger) = 'fr')}";
		List<QuerySolution> chanteurs = DBpediaQuery.execRequete(requete);
		QuerySolution ligne = chanteurs.get((int)(Math.random()*chanteurs.size()));

		if(Math.random()<0.5)
		{

			this.enonce = new SimpleStringProperty("Qui a chanté cette chanson : "+ligne.getLiteral("?nomSingle").getString()+" ?");
			this.bonneReponse= ligne.getLiteral("?nomSinger").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = chanteurs.get((int)(Math.random()*chanteurs.size()));
				if(reponseAbsente(ligne.getLiteral("?nomSinger").getString()))
				{
					this.mauvaisesReponses[index]=ligne.getLiteral("?nomSinger").getString();
					index++;
				}
			}
		}
		else
		{

			this.enonce = new SimpleStringProperty("Quel chanson a chanté "+ligne.getLiteral("?nomSinger").getString()+" ?");
			this.bonneReponse= ligne.getLiteral("?nomSingle").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = chanteurs.get((int)(Math.random()*chanteurs.size()));
				if(!this.bonneReponse.equalsIgnoreCase(ligne.getLiteral("?nomSingle").getString()))
				{
					this.mauvaisesReponses[index]=ligne.getLiteral("?nomSingle").getString();
					index++;
				}
			}
		}


	}

}

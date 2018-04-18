package fr.uha.ensisa.dbpediaquizz.questions;

import java.util.List;

import org.apache.jena.query.QuerySolution;

import fr.uha.ensisa.dbpediaquizz.util.Constantes;
import fr.uha.ensisa.dbpediaquizz.util.DBpediaQuery;

public class QuestionLitterature extends Question {

	public QuestionLitterature()
	{
		super(Constantes.LITTERATURE);
		//Récupère toutes les capitales
		String requete = "PREFIX dbo:<http://dbpedia.org/ontology/> "
							+"PREFIX dbr:<http://dbpedia.org/resource>"
							+ "select ?titre ?auteur"
							+ "WHERE{ ?book a dbo:Book . "
								+ "?book rdfs:label ?titre . "
								+ "?book dbpedia-owl:author ?auteurlong ."
								+ "?auteurlong rdfs:label ?auteur"
								+ "FILTER(lang(?titre) ='fr')"
								+ "FILTER(lang(?auteur) ='fr')"
							+"}";
		List<QuerySolution> capitales = DBpediaQuery.execRequete(requete);
		QuerySolution ligne = capitales.get((int)(Math.random()*capitales.size()));

		if(Math.random()<0.5)
		{

			this.enonce = "Qu'as ecrit "+ligne.getLiteral("?auteut").getString()+" parmit les livre suivant?";
			this.bonneReponse= ligne.getLiteral("?titre").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = capitales.get((int)(Math.random()*capitales.size()));
				if(reponseAbsente(ligne.getLiteral("?titre").getString()))
				{
					this.mauvaisesReponses[index]=ligne.getLiteral("?titre").getString();
					index++;
				}
			}
		}
		else
		{

			this.enonce = "Qu'as écrit "+ligne.getLiteral("?titre").getString()+" ?";
			this.bonneReponse= ligne.getLiteral("?auteur").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = capitales.get((int)(Math.random()*capitales.size()));
				if(!this.bonneReponse.equalsIgnoreCase(ligne.getLiteral("?auteur").getString()))
				{
					this.mauvaisesReponses[index]=ligne.getLiteral("?auteur").getString();
					index++;
				}
			}
		}


	}

}

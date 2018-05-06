package fr.uha.ensisa.dbpediaquizz.questions;

import java.util.List;

import org.apache.jena.query.QuerySolution;

import fr.uha.ensisa.dbpediaquizz.util.Constantes;
import fr.uha.ensisa.dbpediaquizz.util.DBpediaQuery;

public class QuestionLitterature extends Question {

	public QuestionLitterature()
	{
		super(Constantes.LITTERATURE);
		//Récupère tous les livres et auteur
		String requete = "PREFIX dbo:<http://dbpedia.org/ontology/> "
							+"select ?titre ?auteur "
							+"WHERE{ ?book a <http://dbpedia.org/ontology/Book>. "
								+ "?book <http://www.w3.org/2000/01/rdf-schema#label> ?titre. "
								+ "?book <http://dbpedia.org/ontology/author> ?auteurlong. "
								+ "?auteurlong <http://www.w3.org/2000/01/rdf-schema#label> ?auteur "
								+ "FILTER(lang(?titre) ='fr')."
								+ "FILTER(lang(?auteur) ='fr')}";
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

			this.enonce = "Qu'as �crit "+ligne.getLiteral("?titre").getString()+" ?";
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

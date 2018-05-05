package fr.uha.ensisa.dbpediaquizz.questions;

import java.util.List;

import org.apache.jena.query.QuerySolution;

import fr.uha.ensisa.dbpediaquizz.util.Constantes;
import fr.uha.ensisa.dbpediaquizz.util.DBpediaQuery;

public class QuestionCoordonnees extends Question{
	
	
	public QuestionCoordonnees()
	{
		super(Constantes.COORDONNEE);
		//Récupère toutes les capitales et leur coordonnées
		String requete = "select distinct ?nomVille ?pos where {"
                                +"?pays <http://dbpedia.org/ontology/capital> ?ville."
								+"?pays <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://dbpedia.org/ontology/Country>. " 
								+"?ville <http://www.w3.org/2000/01/rdf-schema#label> ?nomVille."
                                +"?ville <http://www.georss.org/georss/point> ?pos."
                                +"FILTER (lang(?nomVille) = 'fr')"

						 +"}";
		List<QuerySolution> locVille = DBpediaQuery.execRequete(requete);
		QuerySolution ligne1 = locVille.get((int)(Math.random()*locVille.size()));
		QuerySolution ligne2 = locVille.get((int)(Math.random()*locVille.size()));
		String[] pos1=ligne1.getLiteral("?pos").getString().split(" ");
		double lat1= Double.parseDouble(pos1[0]);
		double long1= Double.parseDouble(pos1[1]);
		String[] pos2=ligne2.getLiteral("?pos").getString().split(" ");
		double lat2= Double.parseDouble(pos2[0]);
		double long2= Double.parseDouble(pos2[1]);
		double dist=60*Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(long1- long2));
			
		if(Math.random()<0.5)
		{
			this.enonce = "Quel et la distance entre "+ligne1.getLiteral("?nomVille").getString()+" et" +ligne2.getLiteral("?nomVille").getString()+ "?";
			this.bonneReponse=""+ dist;

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{	
				//ligne1 = locVille.get((int)(Math.random()*locVille.size()));
				if(reponseAbsente(ligne1.getLiteral("?titre").getString()))
				{
					this.mauvaisesReponses[index]=(Math.random()*2*10000000) + "";//2*10^7 arc recouvrant la moitier de la terre
					index++;
				}
			}
		}
		else
		{

			this.enonce = "Quel ville se trouve à "+ dist + "m de la ville "+ ligne1.getLiteral("?nomVille").getString()+" ?";
			this.bonneReponse= ligne2.getLiteral("?nomVille").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne1 = locVille.get((int)(Math.random()*locVille.size()));
				if(!this.bonneReponse.equalsIgnoreCase(ligne1.getLiteral("?nomVille").getString()))
				{
					this.mauvaisesReponses[index]=ligne1.getLiteral("?nomVille").getString();
					index++;
				}
			}
		}


	}

}

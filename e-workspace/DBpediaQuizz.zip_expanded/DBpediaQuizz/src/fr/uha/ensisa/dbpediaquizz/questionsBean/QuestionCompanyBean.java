package fr.uha.ensisa.dbpediaquizz.questionsBean;

import java.util.List;

import org.apache.jena.query.QuerySolution;

import fr.uha.ensisa.dbpediaquizz.util.Constantes;
import fr.uha.ensisa.dbpediaquizz.util.DBpediaQuery;
import javafx.beans.property.SimpleStringProperty;

public class QuestionCompanyBean extends QuestionBean {

	public QuestionCompanyBean()
	{
		super(Constantes.SOCIETE);
		//Récupère toutes les dates de création des companies répertoriés
		String requete = "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>"
									+ "select distinct ?nomCompany ?Year where {?Company a <http://dbpedia.org/ontology/Company>. "
									+ "?Company <http://fr.dbpedia.org/property/dateDeCréation> ?Year. "
									+ "?Company <http://fr.dbpedia.org/property/nom> ?nomCompany. "
									+ "FILTER (datatype(?Year) = xsd:integer). "
									+ "FILTER (lang(?nomCompany) = 'fr')}";
		List<QuerySolution> company = DBpediaQuery.execRequete(requete);
		QuerySolution ligne = company.get((int)(Math.random()*company.size()));



			this.enonce = new SimpleStringProperty("Quelle est la date de création de l'entreprise : "+ligne.getLiteral("?nomCompany").getString()+" ?");
			this.bonneReponse= ligne.getLiteral("?Year").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = company.get((int)(Math.random()*company.size()));
				if(reponseAbsente(ligne.getLiteral("?Year").getString()))
				{
					this.mauvaisesReponses[index]=ligne.getLiteral("?Year").getString();
					index++;
				}
			}
		


	}

}

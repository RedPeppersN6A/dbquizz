package fr.uha.ensisa.dbpediaquizz.questionsBean;

import java.util.List;

import org.apache.jena.query.QuerySolution;

import fr.uha.ensisa.dbpediaquizz.util.Constantes;
import fr.uha.ensisa.dbpediaquizz.util.DBpediaQuery;
import javafx.beans.property.SimpleStringProperty;

public class QuestionRoiEtPredecesseurBean extends QuestionBean {

	public QuestionRoiEtPredecesseurBean()
	{
		super(Constantes.HISTOIRE);
		//Récupère tous les rois et leurs prédecesseurs
		String requete = "select distinct ?nomRoi ?nomPredecesseur where {?roi a <http://dbpedia.org/ontology/Royalty>. "
																		+ "?roi <http://fr.dbpedia.org/property/prédécesseur> ?pred. "
																		+ "?roi <http://www.w3.org/2000/01/rdf-schema#label> ?nomRoi. "
																		+ "?pred <http://www.w3.org/2000/01/rdf-schema#label> ?nomPredecesseur. "
																		+ "FILTER(lang(?nomRoi)='fr'). "
																		+ "FILTER(lang(?nomPredecesseur)='fr').}";
		List<QuerySolution> lignees = DBpediaQuery.execRequete(requete);
		QuerySolution ligne = lignees.get((int)(Math.random()*lignees.size()));


			this.enonce = new SimpleStringProperty("Quel est le prédecesseur du roi "+ligne.getLiteral("?nomRoi").getString()+" ?");
			this.bonneReponse= ligne.getLiteral("?nomPredecesseur").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = lignees.get((int)(Math.random()*lignees.size()));
				if(reponseAbsente(ligne.getLiteral("?nomPredecesseur").getString()))
				{
					this.mauvaisesReponses[index]=ligne.getLiteral("?nomPredecesseur").getString();
					index++;
				}
			}

	}

}

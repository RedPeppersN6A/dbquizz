package fr.uha.ensisa.dbpediaquizz.questionsBean;

import java.util.List;

import org.apache.jena.query.QuerySolution;

import fr.uha.ensisa.dbpediaquizz.util.Constantes;
import fr.uha.ensisa.dbpediaquizz.util.DBpediaQuery;
import javafx.beans.property.SimpleStringProperty;

public class QuestionFictionBean extends QuestionBean {
	
	public QuestionFictionBean()
	{
		super(Constantes.FICTION);
		//Récupère toute les chanteurs et chansons
		String requete = "select distinct ?nompf ?nomespece where {?pf <http://dbpedia.org/ontology/species> ?espece. "
				+ "?pf a <http://dbpedia.org/ontology/FictionalCharacter>.  "
				+ "?pf <http://www.w3.org/2000/01/rdf-schema#label> ?nompf. "
				+ "?espece <http://www.w3.org/2000/01/rdf-schema#label> ?nomespece. "
				+ "FILTER(lang(?nompf)='fr'). "
				+ "FILTER(lang(?nomespece)='fr')}";
		List<QuerySolution> chanteurs = DBpediaQuery.execRequete(requete);
		QuerySolution ligne = chanteurs.get((int)(Math.random()*chanteurs.size()));

			this.enonce = new SimpleStringProperty("Quel est l'épèce de ce personnage de fiction : "+ligne.getLiteral("?nompf").getString()+" ?");
			this.bonneReponse= ligne.getLiteral("?nomespece").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = chanteurs.get((int)(Math.random()*chanteurs.size()));
				if(reponseAbsente(ligne.getLiteral("?nomespece").getString()))
				{
					this.mauvaisesReponses[index]=ligne.getLiteral("?nomespece").getString();
					index++;
				}
			}

	}

}

package army.metier;

import java.util.List;

import army.model.Arme;

import army.model.Guerrier;

public interface IInternaute {
	
	List<Guerrier> listGuerriers();
	Guerrier getGuerrier(Long idg);
	List<Arme> listArmes();
	List<Arme> armesParMotCle(String mc);
	List<Arme> armesParGuerrier(Long idg);
	List<Arme> armesSelectionnes();
	Arme getArme(Long ida);
	//Commande enregistrerCommande(Panier p, Client c);


	
	

}

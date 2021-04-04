package army.metier;

import army.model.Arme;

public interface IAdminArme extends IInternaute {
	
	void ajouterArme(Arme a);
	void supprimerArme(Arme a);
	void modifierArme(Arme a);



}

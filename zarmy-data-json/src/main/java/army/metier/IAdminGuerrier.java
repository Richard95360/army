package army.metier;

import java.util.List;

import army.model.Guerrier;

import army.model.User;

public interface IAdminGuerrier extends IAdminArme {
	
	void ajouterGuerrier(Guerrier g);
	void supprimerGuerrier(Guerrier g);
	void modifierGuerrier(Guerrier g);
	void ajouterUser(User u);
//	void attribuerRole(Role r, Long idUser);
	List<User> listUsers();
	User getUser(Long idu);
	User getUser(String username);
	void modifierUser(User u);
	void supprimerUser(User u);
//	List<Role> listRoles();
	
	
	
}





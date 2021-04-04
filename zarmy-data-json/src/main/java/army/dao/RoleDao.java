package army.dao;

import army.model.Role;

public interface RoleDao extends Dao<Role, Long> {
	
	void attribuerRole(Role r, Long idUser);

}

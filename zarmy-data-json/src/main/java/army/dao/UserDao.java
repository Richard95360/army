package army.dao;

import army.model.User;

public interface UserDao extends Dao<User,Long>{
	
	User find(String username);

	User checkLogin(String username, String password);

}


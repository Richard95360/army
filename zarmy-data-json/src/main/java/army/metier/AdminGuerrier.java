package army.metier;

import java.util.List;


import army.dao.UserDao;
import army.model.Guerrier;
import army.model.User;



 public class AdminGuerrier extends AdminArme implements IAdminGuerrier  {

//	@Autowired
//	private ProduitDao daoProduit;
//	@Autowired
//	private CategorieDao daoCategorie;
//	@Autowired
//	private UserDao daoUser;
	
//	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//	private static CategorieDao categorieDao = context.getBean(CategorieDao.class);
//	private static ProduitDao produitDao = context.getBean(ProduitDao.class);
//	private static UserDao userDao = context.getBean(UserDao.class);

//	private ClassPathXmlApplicationContext context;
	private UserDao userDao;
//	private RoleDao roleDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
//	public void setRoleDao(RoleDao roleDao) {
//		this.roleDao = roleDao;
//	}

//	public AdminCategorie() {
//		context = new ClassPathXmlApplicationContext("applicationContext.xml");
//		categorieDao = context.getBean(CategorieDao.class);
//		produitDao = context.getBean(ProduitDao.class);
//		userDao = context.getBean(UserDao.class);
//	}

	@Override
	public void ajouterGuerrier(Guerrier g) {
		guerrierDao.create(g);
	}

	@Override
	public void supprimerGuerrier(Guerrier g) {
		guerrierDao.delete(g);
	}

	@Override
	public void modifierGuerrier(Guerrier g) {
		guerrierDao.update(g);
	}

	@Override
	public void ajouterUser(User u) {
		userDao.create(u);
	}

//	@Override
//	public void attribuerRole(Role r, Long idUser) {
//		roleDao.attribuerRole(r, idUser);
//	}

	@Override
	public List<User> listUsers() {
		return userDao.findAll();
	}

	@Override
	public User getUser(Long idu) {
		return userDao.find(idu);
	}

	@Override
	public User getUser(String username) {
		return userDao.find(username);
	}

	@Override
	public void modifierUser(User u) {
		userDao.update(u);
	}

	@Override
	public void supprimerUser(User u) {
		userDao.delete(u);
	}
	
//	@Override
//	public List<Role> listRoles() {
//		return roleDao.findAll();
//	}

}

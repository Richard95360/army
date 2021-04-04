package army.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import army.dao.CivilDao;
import army.model.Civil;

public class TestCivil {
	
	private static ClassPathXmlApplicationContext context;

	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");

		m1();

		context.close();

	}

	private static void m1() {
		Civil civ1 = new Civil("Richard","Pompier");
		
		CivilDao dci = context.getBean(CivilDao.class);
		dci.create(civ1);
		
		
		
	}

}

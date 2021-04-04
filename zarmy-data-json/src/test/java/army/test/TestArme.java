package army.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import army.dao.ArmeDao;
import army.model.Arme;

public class TestArme {
	
	private static ClassPathXmlApplicationContext context;


	public static void main(String[] args) {
		
		context = new ClassPathXmlApplicationContext("applicationContext.xml");

		m1();

		context.close();
		

	}


	private static void m1() {
		

		
		Arme ar1 = new Arme();
		
		
		
		
		
		
		ArmeDao dam = context.getBean(ArmeDao.class);
		
		dam.create(ar1);
		
	}

}

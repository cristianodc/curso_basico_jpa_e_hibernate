package hibernate.e.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.boot.model.relational.InitCommand;

public class HibernateUtil {

	public static EntityManagerFactory factory = null;
	static {
		
		init();
	}
	
	private static void init() 
		{
			try {
				if(factory == null )
					{
						factory = Persistence.createEntityManagerFactory("jpa-e-hibernate");
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	public static EntityManager getEntityManager() 
		{
			return factory.createEntityManager(); /*Prove a parte de persistencia*/
		
		}
	
	public static Object getPrimaryKey(Object entity) {
		
		return factory.getPersistenceUnitUtil().getIdentifier(entity);
	}
	
	
}

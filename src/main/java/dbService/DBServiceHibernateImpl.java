package dbService;


import java.sql.Connection;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import utils.TimeHelper;
import base.Address;
import base.DataAccessObject;
import base.MessageSystem;

public class DBServiceHibernateImpl implements DataAccessObject {

	private final MessageSystem messageSystem;
	private final Address address;
	private ServiceRegistryBuilder builder = new ServiceRegistryBuilder();
    private ServiceRegistry serviceRegistry = builder.buildServiceRegistry();
    private SessionFactory sessionFactory;
    private Configuration configuration;
	
	public DBServiceHibernateImpl(MessageSystem msgSystem, Configuration configuration) {
		this.address = new Address();
		this.messageSystem = msgSystem;
		msgSystem.addService(this, "DBService");
		
	    configuration.setProperty("hibernate.connection.username", "checkers");
        configuration.setProperty("hibernate.connection.password", "QSQ9D9BUBW93DK8A7H9FPXOB5OLOP84BA4CJRWK96VN0GPVC6P");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/checkers");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        
        if (configuration.getProperties() != null) {
        		builder.applySettings(configuration.getProperties());
        		configuration.addAnnotatedClass(UserDataSet.class);
        }
        		
      
        
        serviceRegistry = builder.buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	@Override
	public Address getAddress() {
		return this.address;
	}

	@Override
	public void run() {
		while (true) {
			messageSystem.execForAbonent(this);
			TimeHelper.sleep(200);
		}
	}

	@Override
	public MessageSystem getMessageSystem() {
		return this.messageSystem;
	}

	@Override
	public UserDataSet getUDS(String login, String password) {
		
		org.hibernate.Session session = null;
		
		if (sessionFactory != null)
			session  = sessionFactory.openSession();
		
		UserDataSet userDataSet = null;
		if (session != null)
			userDataSet = (UserDataSet)session.createCriteria
                        (UserDataSet.class).add(Restrictions.eq("Name", login)).uniqueResult();
        
        return userDataSet;
	}

	@Override
	public boolean addUDS(String login, String password) {
		org.hibernate.Session session  = sessionFactory.openSession();
        UserDataSet userDataSet = (UserDataSet)session.createCriteria
                        (UserDataSet.class).add(Restrictions.eq("Name", login)).uniqueResult();
        session.close();
        
        if (userDataSet == null){
            userDataSet = new UserDataSet ();
            session  = sessionFactory.openSession();
            org.hibernate.Transaction trx = session.beginTransaction();
            trx.commit();
            session.close();
        }
        
        return (userDataSet != null);
	}

	@Override
	public void updateUsers(List<UserDataSet> users) {
		ListIterator<UserDataSet> li = users.listIterator();
		while(li.hasNext()){
			UserDataSet user = li.next();
			
			if (sessionFactory != null) {
				Session session = sessionFactory.openSession();
				session.save(user);
				org.hibernate.Transaction trx = session.beginTransaction();
				trx.commit();
				session.close();
			}
		}
		
	}
	
	

}

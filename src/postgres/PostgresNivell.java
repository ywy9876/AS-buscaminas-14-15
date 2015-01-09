package postgres;

import java.io.IOException;
import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;

import model.Nivell;
import model.MyHibernateUtil;

public class PostgresNivell {
	
	public ArrayList<Nivell> getAll() {
		Session session = MyHibernateUtil.getSessionFactory().openSession();
		
		ArrayList<Nivell> nivells = (ArrayList<Nivell>) session.createQuery("from " + Nivell.class.getSimpleName()).list();
				
		session.close();
		
		return nivells;
	}
	
}

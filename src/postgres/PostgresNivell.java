package postgres;

import java.io.IOException;
import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;

import model.Nivell;
import model.MyHibernateUtil;
import model.UsuariRegistrat;

public class PostgresNivell {
	
	public ArrayList<Nivell> getAll() {
		Session session = MyHibernateUtil.getSessionFactory().openSession();
		
		ArrayList<Nivell> nivells = (ArrayList<Nivell>) session.createQuery("from " + Nivell.class.getSimpleName()).list();
				
		session.close();
		
		return nivells;
	}
	
	public Nivell getNivell(String niv) {
		Session session = MyHibernateUtil.getSessionFactory().openSession();
		
		Nivell nivell = (Nivell) session.get(Nivell.class, niv);
		
		session.close();
		return nivell;
	}
	
}

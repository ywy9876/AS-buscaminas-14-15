package postgres;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.Casella;
import model.Partida;
import model.MyHibernateUtil;

public class PostgresPartida extends PostgresGeneral<Partida> {
	
	
	public ArrayList<Partida> getAll() {
		Session session = MyHibernateUtil.getSessionFactory().openSession();
		
		ArrayList<Partida> partides = (ArrayList<Partida>) session.createQuery("from " + Partida.class.getSimpleName()).list();
		
		session.close();
		
		return partides;
	}
	
	public Partida getPartida(int idPartida) {
		Session session = MyHibernateUtil.getSessionFactory().openSession();
		
		Partida partida = (Partida) session.get(Partida.class, idPartida);
		
		session.close();
		return partida;
	}
	
	/*public Casella[][] getCaselles() {
		Session session = MyHibernateUtil.getSessionFactory().openSession();
		/*Casella[][] caselles;
		Casella[][] caselles = (Casella[][]) session.createQuery("from " + Partida.class.getSimpleName()).list();
		
		session.close();
		
		return partides;
	}*/
	
	@Override
	public Partida store(Partida p) throws Exception {
		SessionFactory sessionFactory = MyHibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		session.save(p);
		Casella[][] caselles = p.getCaselles();
		for(Casella[] casella : caselles)
			for(Casella c : casella) session.save(c);
		session.getTransaction().commit();
		
		session.close();
		
		return p;
	}
	
	public void update(Casella[][] caselles) throws Exception{
			
			SessionFactory sessionFactory = MyHibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			
			session.beginTransaction();
			for(Casella[] casella : caselles)
				for(Casella c : casella) session.merge(c);
			session.getTransaction().commit();
			
			session.close();
			
		}
	}
	


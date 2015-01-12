package postgres;

import java.io.IOException;

import org.hibernate.Query;
import org.hibernate.Session;

import model.UsuariRegistrat;
import model.MyHibernateUtil;

public class PostgresUsuariRegistrat {
	@SuppressWarnings("unchecked")
	public UsuariRegistrat getUsuari(String user) throws IOException{
		Session session = MyHibernateUtil.getSessionFactory().openSession();
		
		UsuariRegistrat usuari = (UsuariRegistrat) session.get(UsuariRegistrat.class, user);
		
		if (usuari == null) {
			session.close();
			throw new IOException("No existeix l'usuari");
		}
		
		session.close();
		return usuari;
	}
	
}

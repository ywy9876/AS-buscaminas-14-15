package postgres;


import org.hibernate.Session;

import model.Jugador;
import model.MyHibernateUtil;

public class PostgresJugador extends PostgresGeneral<Jugador> {
	
	
	
	public boolean exists(String username) {
		Session session = MyHibernateUtil.getSessionFactory().openSession();
		
		Jugador j = (Jugador) session.get(Jugador.class, username);
		
		session.close();
		if (j != null) return true;
		else return false;
		
	}
}
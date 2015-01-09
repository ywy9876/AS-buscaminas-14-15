package tests;

import java.util.List;
 
import org.hibernate.Query;
import org.hibernate.Session;
import model.*;
 
public class TestNivell {
 
    public static void main(String[] args) {
        Session session = MyHibernateUtil.getSessionFactory().getCurrentSession();
 
        session.beginTransaction();
 
        createNivell(session);
 
        queryNivell(session);
 
    }
 
    private static void queryNivell(Session session) {
        Query query = session.createQuery("from Nivell");                 
        List <Nivell>list = query.list();
        java.util.Iterator<Nivell> iter = list.iterator();
        while (iter.hasNext()) {
 
            Nivell niv = iter.next();
            System.out.println("Nivell: \"" + niv.getNom() +"\", " + niv.getNombreCasellesxColumna() +"\", " + niv.getNombreCasellesxFila());
 
        }
 
        session.getTransaction().commit();
 
    }
 
    public static void createNivell(Session session) {
        Nivell niv = new Nivell("Avançat", 100, 100, 50); 
 
        session.save(niv);
    }
}
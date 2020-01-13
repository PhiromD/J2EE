package fr.onepiecetheorie.services;
import java.util.List;





import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.MAX;
import org.hibernate.Transaction;
import java.util.Date;

import java.util.ArrayList;
import java.util.Collection;

import fr.onepiecetheorie.hibernate.utils.HibernateConnect;
import fr.onepiecetheorie.pojos.*;



public class TheorieService {


	public List<Theorie> getAll() throws Exception{

		List<Theorie> theories = new ArrayList<>();
		
		
		Transaction tx = null; 
		try(Session session = HibernateConnect.getSessionFactory().openSession()) {

			tx = session.beginTransaction();
			String result = "from Theorie ";
			javax.persistence.Query query = session.createQuery(result);
			theories = query.getResultList();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} 
		return theories;


	}
	public List<Theorie> getAllByMembre(int idUser) throws Exception{

		List<Theorie> theories = new ArrayList<>();
		
		Transaction tx = null; 
		try (Session session = HibernateConnect.getSessionFactory().openSession()){

			tx = session.beginTransaction();
			String result = "SELECT T FROM Theorie T JOIN T.userForum U where U.id = :id";
			javax.persistence.Query query = session.createQuery(result);
			query.setParameter("id", idUser);
			theories = query.getResultList();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} 

		return theories;


	}
	public Theorie createTheorie(Theorie theorie) throws Exception {

		
		Transaction tx = null; 
		try(Session session = HibernateConnect.getSessionFactory().openSession()) {

			tx = session.beginTransaction();
			
			int id = (Integer)session.save(theorie);
			theorie.setIdTheorie(id);
			session.flush(); 
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		return theorie;

	}


	public Theorie getTheorieFromId(int idTheorie) throws Exception{
		Theorie theorie=null;
		
		Transaction tx = null; 
		try(Session session = HibernateConnect.getSessionFactory().openSession()) {

			tx = session.beginTransaction();
			theorie = (Theorie)session.get(Theorie.class, idTheorie); 
			Collection<ImageTheorie> images = theorie.getImageTheorie();
			for (ImageTheorie image : images)
			{
			  theorie.addImageTheorie(image);
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} 

		return theorie;
	}
	public Theorie getLastTheorie() throws Exception{

		Theorie theorie = new Theorie();
		
		
		Transaction tx = null; 
		try(Session session = HibernateConnect.getSessionFactory().openSession()) {

			tx = session.beginTransaction();
			String result = " FROM Theorie T WHERE max(T.idTheorie)";
			javax.persistence.Query query = session.createQuery(result);
			theorie = (Theorie)query.getSingleResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} 
		return theorie;
	}
	public void updateTheorie(Theorie theorie) {
		
		Transaction tx = null; 
	
		try(Session session = HibernateConnect.getSessionFactory().openSession()) {

			tx = session.beginTransaction();
			session.update(theorie);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} 
			
	}
	
	
}

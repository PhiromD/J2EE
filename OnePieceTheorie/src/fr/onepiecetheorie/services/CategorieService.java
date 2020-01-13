package fr.onepiecetheorie.services;

import java.util.List;




import org.hibernate.Session;

import org.hibernate.Transaction;



import java.util.ArrayList;
import java.util.Collection;

import fr.onepiecetheorie.hibernate.utils.HibernateConnect;
import fr.onepiecetheorie.pojos.Categorie;
import fr.onepiecetheorie.pojos.Theorie;
import fr.onepiecetheorie.pojos.UserForum;




public class CategorieService {






	public List<Categorie> getAll() throws Exception{

		List<Categorie> categories = new ArrayList<>();
		
		Transaction tx = null;       
		try(Session session = HibernateConnect.getSessionFactory().openSession()) {
			tx = session.getTransaction();
			tx.begin();
		categories = session.createQuery("FROM Categorie").getResultList();
			tx.commit();
			
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} 
		
		return categories;


	}
	public Categorie getCategorieFromIdTheorie() throws Exception{
		Categorie categorie=null;
		
		Transaction tx = null; 
		try(Session session = HibernateConnect.getSessionFactory().openSession()) {

			tx = session.beginTransaction();
			categorie = (Categorie)session.get(Categorie.class, 3);
			Collection<Theorie> theories = categorie.getTheorie();
			
			for (Theorie theorie: theories)
			{
			   categorie.addTheorie(theorie);
			}
			
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return categorie;
	}
	public Categorie getCategorieFromIdUser() throws Exception{
		Categorie categorie=null;
		
		Transaction tx = null; 
		try(Session session = HibernateConnect.getSessionFactory().openSession()) {

			tx = session.beginTransaction();
			categorie = (Categorie)session.get(Categorie.class, 2);
			Collection<UserForum> users = categorie.getUser();
			
			for (UserForum userForum: users)
			{
			   categorie.addUser(userForum);
			}
			
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return categorie;
	}


}

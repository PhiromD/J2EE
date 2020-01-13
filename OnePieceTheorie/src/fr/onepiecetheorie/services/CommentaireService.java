/**
 * 
 */
package fr.onepiecetheorie.services;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import org.hibernate.Transaction;






import fr.onepiecetheorie.hibernate.utils.HibernateConnect;
import fr.onepiecetheorie.pojos.*;


/**
 * @author phirom
 *
 */
public class CommentaireService {



	public List<Commentaire> getAllCommentaireByTheorie(int idTheorie) throws Exception{
		List<Commentaire> commentaires = null;

		 
		try (Session session = HibernateConnect.getSessionFactory().openSession()){
			
			String result = "SELECT C from Commentaire C join C.theorie R where R.id = :id";
			javax.persistence.Query query = session.createQuery(result);
			query.setParameter("id", idTheorie);
			commentaires = query.getResultList();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		return commentaires;

	}
	public Commentaire createCommentaireByTheorie(Commentaire commentaire,Theorie theorie) throws Exception {


		Transaction tx = null; 
		try(Session session = HibernateConnect.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			commentaire.setCommentaireTheorie(theorie);
			session.save(commentaire);
			session.flush(); 
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} 


		return commentaire;

	}
	public int moyTheorie(int idTheorie) throws Exception{
		int moy = 0;
		
		try (Session session = HibernateConnect.getSessionFactory().openSession()){
			
			Query query = session.createQuery("SELECT floor(avg(c.note))  from Commentaire C  JOIN C.theorie CT where CT.idTheorie = :id ");
			query.setParameter("id", idTheorie);
			moy = (Integer) query.getSingleResult();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		return moy;
		
    }



}

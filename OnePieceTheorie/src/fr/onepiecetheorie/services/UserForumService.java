package fr.onepiecetheorie.services;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



import java.util.ArrayList;
import java.util.Date;

import fr.onepiecetheorie.hibernate.utils.HibernateConnect;
import fr.onepiecetheorie.pojos.Theorie;
import fr.onepiecetheorie.pojos.UserForum;





public class UserForumService {

	public boolean authenticateUser(String pseudo, String mdp) throws Exception {
		UserForum user = existUser(pseudo,mdp);         
		if(user!=null && user.getPseudo().equals(pseudo) && user.getMdp().equals(mdp)){
			return true;
		}else{
			return false;
		}
	}
	public List<UserForum> getAll() throws Exception{
		List<UserForum> users = new ArrayList<>();

		Transaction tx = null; 
		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			String result = "from UserForum ";
			javax.persistence.Query query = session.createQuery(result);
			users = query.getResultList();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} 
		return users;

	}

	public UserForum createMembre(UserForum user) throws Exception {


		Transaction tx = null; 
		try(Session session = HibernateConnect.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			session.save(user);
			session.flush();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} 
		return user;

	}
	public UserForum existUser(String pseudo,String mdp) throws Exception{
		UserForum user = null;

		Transaction tx = null; 
		try (Session session = HibernateConnect.getSessionFactory().openSession()){

			tx = session.beginTransaction();
			javax.persistence.Query query = session.createQuery("from UserForum U where (U.pseudo,U.mdp) = (:pseudo,:mdp)");
			query.setParameter("pseudo", pseudo);
			query.setParameter("mdp", mdp);
			user =(UserForum) query.getSingleResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} 
		return user;
	}
	public UserForum existJoinUser(int idUser) throws Exception{
       
        UserForum user = null;
		Transaction tx = null; 
		try (Session session = HibernateConnect.getSessionFactory().openSession()){

			tx = session.beginTransaction();
		    user = (UserForum)session.get(UserForum.class, idUser); 
			java.util.Collection<Theorie> theories = user.getRecettes();
			for (Theorie theorie : theories)
			{
			   user.addTheorie(theorie);
			}
			
			/*for(Membre m : membreJoin) {
				if(m.getIdMembre()==idMembre) {
					return m;
					
				}
				
			}*/
			/*Iterator iterator = membreJoin.iterator();
			int i = 0;
			while (iterator.hasNext()) {

				Membre membreIte[] = (Membre[]) iterator.next();
				if(membreIte[i].getIdMembre()==idMembre) {
					membre= membreIte[i];
					return membre;
				}
				i++;
			}*/
			

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}     


        return user;

	}


	public UserForum existPseudo(String pseudo) throws Exception{
		UserForum user = null;

		
		try(Session session = HibernateConnect.getSessionFactory().openSession()) {

			
			javax.persistence.Query query = session.createQuery("from UserForum U where U.pseudo = :pseudo");
			query.setParameter("pseudo", pseudo);

			user =(UserForum) query.getSingleResult();
			
		} catch (NoResultException nre) {
			
		}
		return user;
	}


	public UserForum getUserFromId(int id) throws Exception{
		UserForum user=null;

	
		try(Session session = HibernateConnect.getSessionFactory().openSession()) {

			
			javax.persistence.Query query = session.createQuery("from UserForum U where U.id = :id");
			query.setParameter("id", id);

			user =(UserForum) query.getSingleResult();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return user;
	}

	public void updateUser(UserForum user,UserForum user2) throws Exception{

		int id=user.getIdUser();

		Transaction tx = null; 
		try(Session session = HibernateConnect.getSessionFactory().openSession()) {

			tx = session.beginTransaction();
			UserForum membreupdate = (UserForum) session.load(UserForum.class,id);
			membreupdate.setPseudo(user2.getPseudo());
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteUser(UserForum user) throws Exception{

		int id=user.getIdUser();

		Transaction tx = null; 
		try (Session session = HibernateConnect.getSessionFactory().openSession()){

			tx = session.beginTransaction();
			UserForum userDelete = (UserForum) session.load(UserForum.class,id);
			session.delete(userDelete);
			session.flush();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} 
	}
public void updateUser(UserForum userForum) {
		
		Transaction tx = null; 
	
		try(Session session = HibernateConnect.getSessionFactory().openSession()) {

			tx = session.beginTransaction();
			session.update(userForum);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} 
			
	}
}

package fr.onepiecetheorie.services;
import java.util.List;




import org.hibernate.Session;

import org.hibernate.Transaction;



import java.util.ArrayList;


import fr.onepiecetheorie.hibernate.utils.HibernateConnect;

import fr.onepiecetheorie.pojos.Mugiwara;

public class MugiwaraService {	
	public List<Mugiwara> getAll() throws Exception{

	List<Mugiwara> mugiwaras = new ArrayList<>();
	
	Transaction tx = null;       
	try(Session session = HibernateConnect.getSessionFactory().openSession()) {
		tx = session.beginTransaction();
		String result = "FROM Mugiwara ";
		javax.persistence.Query query = session.createQuery(result);
		mugiwaras = query.getResultList();
		tx.commit();
		
	} catch (Exception e) {
		if (tx != null) {
			tx.rollback();
		}
		e.printStackTrace();
	} 
	
	return mugiwaras;


}
public Mugiwara getMugiwaraFromId(int idMugi) throws Exception{
	Mugiwara mugiwara=null;
	
	Transaction tx = null; 
	try(Session session = HibernateConnect.getSessionFactory().openSession()) {

		tx = session.beginTransaction();
		javax.persistence.Query query = session.createQuery("FROM Mugiwara M where M.idMugiwara = :id");
		query.setParameter("id", idMugi);

		mugiwara =(Mugiwara) query.getSingleResult();
		tx.commit();
	} catch (Exception e) {
		if (tx != null) {
			tx.rollback();
		}
		e.printStackTrace();
	}
	return mugiwara;
}


}



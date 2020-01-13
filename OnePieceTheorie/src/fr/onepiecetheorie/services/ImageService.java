package fr.onepiecetheorie.services;



import java.util.Collection;

import org.hibernate.HibernateException;

import org.hibernate.Session;

import org.hibernate.Transaction;


import fr.onepiecetheorie.hibernate.utils.HibernateConnect;
import fr.onepiecetheorie.pojos.*;


public class ImageService {
	
	
	
	public Image SaveImageUser(Image image)
	{
		Session session = HibernateConnect.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			session.save(image);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		return image;
	}
	public ImageTheorie saveImageTheorie(ImageTheorie imageTheorie)
	{
		Session session = HibernateConnect.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			session.save(imageTheorie);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		return imageTheorie;
	}
	


	public Collection<ImageTheorie> getImageTheorie(int idTheorie)
	{
		Collection<ImageTheorie> images=null;
		Session session = HibernateConnect.getSessionFactory().openSession();
		try {
			String result = "SELECT I from ImageTheorie I join I.theorie R where R.id = :id";
			javax.persistence.Query query = session.createQuery(result);
			query.setParameter("id", idTheorie);
			images = query.getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		return images;
	}
	
	public Image getImageUser(int idMembre)
	{
		Image image=null;
		Session session = HibernateConnect.getSessionFactory().openSession();
		try {
			String result = "SELECT I from Image I join I.user R where R.id = :id";
			javax.persistence.Query query = session.createQuery(result);
			query.setParameter("id", idMembre);
			image = (Image)query.getSingleResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		return image;
	}

}

package fr.onepiecetheorie.test;
/**

 * 
 */


import java.util.Date;


/**
 * @author phirom

 *
 */

import org.hibernate.Session;


import org.hibernate.SessionFactory;

import org.hibernate.Transaction;

import org.hibernate.cfg.Configuration;



import fr.onepiecetheorie.pojos.*;



class TestHibernate {



    protected Session session;

    protected SessionFactory sessionFactory;


    public static void main(String args[]) throws Exception {

        SessionFactory sf = new Configuration().configure().buildSessionFactory();

        SessionFactory sessionFactory = sf;

        Session session = sessionFactory.openSession();



        Transaction tx = null;

        try {

            tx = session.beginTransaction();
            Mugiwara membreTest = new Mugiwara("Luffy", "Monkey D", new Date(), "Chapeau de paille", "1 500 000 000");
    		
    		// On test à partir de membretest now...
    		// action (Act)
    		
    		
    		//membreTest.addCommentaire(commentaire);

    		
    		
    		
    		
    		session.save(membreTest);
    		session.flush();
    		tx.commit();
    		
            



        } catch (Exception e) {

            if (tx != null) {

                tx.rollback();

            }

            throw e;

        } finally {

            session.close();

        }



        sessionFactory.close();

    }


    }
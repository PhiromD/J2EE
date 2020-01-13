package fr.onepiecetheorie.hibernate.utils;


import java.io.File;


 
import org.hibernate.cfg.Configuration;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction; 
 

 
public class HibernateConnect {
 
    private static SessionFactory sessionFactory ;
    
    public static SessionFactory getSessionFactory() { 
        if (sessionFactory == null) { 
            try { 
                SessionFactory sf = new Configuration().configure().buildSessionFactory(); 
                sessionFactory = sf; 
                return sessionFactory; 
 
            } catch (Exception e) { 
                e.printStackTrace(); 
            } 
        } 
        return sessionFactory; 
    }
}
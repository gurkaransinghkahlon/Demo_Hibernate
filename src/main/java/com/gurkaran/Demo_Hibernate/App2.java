package com.gurkaran.Demo_Hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App2 {
    public static void main( String[] args ) 
    {
    	Configuration config = new Configuration().configure().addAnnotatedClass(Laptop.class);
    	SessionFactory sf = config.buildSessionFactory();
    	Session session = sf.openSession();
    	session.beginTransaction();
    	
    	Laptop lap = session.get(Laptop.class, 9);      //Gives us OBJECT, Get always hit the database even when it is not called
    	//Laptop lap = session.load(Laptop.class, 4);       //Gives us PROXY OBJECT, Load will not hit the database when it is not called
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println(lap);
    	
    	
    /*	Laptop l = new Laptop();
    	l.setLid(6);
    	l.setLname("TOSHIBA");
    	
    	session.save(l);     //Goes to Persistant state
    	l.setLname("SONY");     //Still new value updated after save in database
    	
    	session.remove(l);
    	
    	session.getTransaction().commit();
    	
    	session.detach(l);
    	l.setLname("Chrome");      //Goes to transient state, changes not saved in database
   		
   		*/
    
    }

}

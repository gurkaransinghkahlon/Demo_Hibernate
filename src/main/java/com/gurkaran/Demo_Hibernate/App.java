package com.gurkaran.Demo_Hibernate;


import java.util.List;
import java.util.Map;

import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) 
    {
    
    /*	AlienName aname = new AlienName();
    	aname.setFname("Gurkaran");
    	aname.setMname("singh");
    	aname.setLname("Kahlon");
    	
    	//Alien gk = null; // no need to create object to get data from sql
       Alien gk = new Alien();
        gk.setAid(102);
        gk.setAname(aname);
        gk.setColor("green");       */
    	
    	
    	//For illustration of many to one and many to may relation in hibernate
    /*	Laptop laptop = new Laptop();
    	laptop.setLid(102);
    	laptop.setLname("gsk");
    	
    	
    	Student s = new Student();
    	s.setRollno(2);
    	s.setName("fabio");
    	s.setMarks(45);
    	s.getLaptop().add(laptop);
    	laptop.setStudent(s);   */
    	
    	
        
        //no need to jdbc to persist data, use hibernate
        
      //  Configuration con = new Configuration().configure().addAnnotatedClass(Alien.class);
        
    	//for onetomany or manytoone mapping
    	
    	
    	//laptop.getStudent().add(s);          //for many to many relations
    	
    	
    	Student s = null;
    	
    	Configuration con = new Configuration().configure().addAnnotatedClass(Student.class).addAnnotatedClass(Laptop.class);
        
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        
        Transaction tx = session.beginTransaction();
        
        Query q1 = session.createQuery("from Student where rollno=1");
        q1.setCacheable(true);
        s= (Student)q1.uniqueResult();
       // s = (Student) session.get(Student.class, 1);  //1 is the primary key
        System.out.println(s);
        
        
        
        s = (Student) session.get(Student.class, 1);  //1 is the primary key  //First level cache fetched since same session
        System.out.println(s);
        
        session.getTransaction().commit();
        session.close();
        
        Session session2 = sf.openSession();
        session2.beginTransaction();
        Query q2 = session2.createQuery("from Student where rollno=1");
        q2.setCacheable(true);
        s = (Student)q2.uniqueResult();
        System.out.println(s);
        
        Query q4 = session2.createQuery("select rollno, name, marks from Student where rollno=1 and marks > 40");
        q4.setCacheable(true);
        Object[] stud = (Object[])q4.uniqueResult();
        for(Object o : stud) {
        	System.out.println(o);
        }
        System.out.println("Another way: "+stud[0]+" "+stud[1]+" "+stud[2]);
        
        int mrks = 40;
        Query q5 = session2.createQuery("select rollno, name, marks from Student s where s.marks >"+mrks);//alias
        List<Object[]> studentss = (List<Object[]>) q5.list(); 
        for(Object[] ss : studentss) {
        	System.out.println("Another way: "+ss[0]+" "+ss[1]+" "+ss[2]);
        }
        Query q6 = session2.createQuery("select sum(marks) from Student s where s.marks > :mrks");//alias
        q6.setParameter("mrks", mrks);           //Like that of PreparedStatement in JDBC
        Long marks = (Long) q6.uniqueResult(); 
   
        System.out.println("Marks sum: "+marks);
           
        NativeQuery sql_q1 = session2.createSQLQuery("select * from Student where marks>40");
        sql_q1.addEntity(Student.class);   //Query will know it will return object of type student
        List<Student> st_query_objs = sql_q1.list();
        for(Student o: st_query_objs) {
        	System.out.println("SQL_Queries :  "+o);
        }
        
        NativeQuery sql_q2 = session2.createSQLQuery("select name, marks from Student where marks>40");
        sql_q2.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);  //as name says give us key-value pair  //With a ResultTransformer it is possible to define how the results of a query should be handled
        List st_query_objs_2 = sql_q2.list();
        
        for(Object o: st_query_objs_2) {
        	Map m =(Map)o;
        	System.out.println(m.get("name")+" marks "+m.get("marks"));
        }
        
        Query q3 = session2.createQuery("from Student");
        List<Student> students = q3.list();        //No need of resultSet, instead we got List
        for(Student student : students) {
        	System.out.println(student);
        }
      //  s = (Student) session2.get(Student.class, 1);  //Different session
       
        
        session2.getTransaction().commit();
        session2.close();
        
      //  session.save(laptop);
       // session.save(s);               //Persistant state
        
        //gk = session.get(Alien.class, 101);  //to get data from sql
        // session.save(gk);
       
       // session.beginTransaction();
        //Student s2 = session.get(Student.class, 1); //until here didnot fetch list of laptop..
        
       // System.out.println(s2.getName());
       // System.out.println(s2.getLaptop());
//    
//        List<Laptop> laps = s2.getLaptop();       //Got laptop only when needed -- LAZY (Fetch Type By Default)
//        
//       for(Laptop l : laps) {
//        	System.out.println(l); 
//        }

       
        //System.out.println(gk);
    }
}

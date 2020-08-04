package com.cursos_online;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.cursos_online.entidades.Curso;
import com.cursos_online.entidades.Estudiante;


public class Main {

	
		static final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		
		static SessionFactory sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
		
		public static void main(String[] args){
			Curso cur1 = new Curso("Fundamentos de java",0);
			Curso cur2 = new Curso("Hibernate",0);
			
			ingresarCurso(cur1);
			ingresarCurso(cur2);
			
			Estudiante est1 = new Estudiante(0, "Gabriel", "Jara");
			Estudiante est2 = new Estudiante(0, "ANDRES","Jacome");
			
			ingresarEstudiante(est1);
			ingresarEstudiante(est2);
			
			List<Curso>cursos= getCursos();
				
		    for(Curso temp:cursos) {
					System.out.println(temp);
			}
			
		    List<Estudiante>estudiantes= getEstudiantes();
			
		    for(Estudiante temp:estudiantes) {
					System.out.println(temp);
			}
		    
		    List<Estudiante> estudiantes1 =getEstudiantesPorNombre("Lucas");
		    for(Estudiante e: estudiantes1) 
		    {
		    	System.out.println(e);
		    }
		    
		    //eliminarEstudiante(1);
		    //eliminarCurso(1);
		    
		    Curso curso1 = new Curso("Auditoria",1);
		    modificarCurso(curso1);
		    
		    Estudiante estudiante1 = new Estudiante(3,"J","P");
		    modificarEstudiante(estudiante1);
		    
		}		
		
	
	static void ingresarCurso(Curso curso) 
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(curso);
		
		session.getTransaction().commit();
		session.close();
		
		
	}
	
	static void ingresarEstudiante(Estudiante estudiante) 
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(estudiante);
		
		session.getTransaction().commit();
		session.close();
		
		System.out.println(estudiante.getId());
	}
	
	static List <Curso>getCursos(){
		Session session =sessionFactory.openSession();
		List<Curso>cursos = session.createQuery("from Curso",Curso.class).list();
		return cursos;
	}
	
	static List <Estudiante> getEstudiantes(){
		Session session =sessionFactory.openSession();
		List<Estudiante>estudiantes = session.createQuery("from Estudiante",Estudiante.class).list();
		return estudiantes;
	}
	
	static List<Estudiante> getEstudiantesPorNombre(String nombre)
	{
		Session session =sessionFactory.openSession();
		Query query =session
				.createQuery("from Estudiante where nombre=:nombre");
		query.setParameter("nombre", nombre);
		
		List<Estudiante> estudiantes1 = (List<Estudiante>)query.getResultList();
		return estudiantes1;
	}
	
	
	
	
	
	static void modificarCurso(Curso curso) 
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(curso);
		
		session.getTransaction().commit();
		session.close();
		
	}
	static void eliminarCurso(int id) 
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(id);
		
		session.getTransaction().commit();
		session.close();
		
		System.out.println(id);
		
		
	}
	static void modificarEstudiante(Estudiante estudiante) 
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(estudiante);
		
		session.getTransaction().commit();
		session.close();
		
		
	}
	static void eliminarEstudiante(int id) 
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(id);
		
		session.getTransaction().commit();
		session.close();
		
		System.out.println(id);
		
	}
	
	
	

}

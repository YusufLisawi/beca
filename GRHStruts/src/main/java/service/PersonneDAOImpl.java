package service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.PersonnelDAO;
import dao.TypePersonnel;
import model.Personnel;
import util.HibernateUtil;

public class PersonneDAOImpl implements PersonnelDAO{
	private static final Logger logger = Logger.getLogger(PersonneDAOImpl.class.getName());

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return HibernateUtil.getSessionFactory();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Could not create SessionFactory", e);
			throw new IllegalStateException("Could not create SessionFactory");
		}
	}

	@Override
	public void addPersonnel(Personnel personnel) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.persist(personnel);
		session.getTransaction().commit();
		logger.info("Personnel saved successfully, Personnel Details="+personnel);
	}

	@Override
	public void updatePersonnel(Personnel personnel) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.merge(personnel);
		session.getTransaction().commit();
		logger.info("Personnel updated successfully, Personnel Details="+personnel);
	}

	@Override
	public List<Personnel> listPersonnel() {
		List<Personnel> allPersonnel = new ArrayList<>();
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		allPersonnel = session.createQuery("select p from Personnel p", Personnel.class).list();
		session.getTransaction().commit();
		return allPersonnel;
	}

	@Override
	public List<Personnel> getPersonnelByKeyword(String keyWord) {
		List<Personnel> allPersonnel = new ArrayList<>();
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		String query = "from Personnel p WHERE p.nom LIKE '%"+keyWord+"%' OR p.nom LIKE '%"+keyWord+"%'";
		allPersonnel = session.createQuery(query, Personnel.class).list();
		session.getTransaction().commit();
		return allPersonnel;
	}

	@Override
	public List<Personnel> getPersonnelByType(TypePersonnel type) {
		List<Personnel> allPersonnel;
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		String query;
		if (type == TypePersonnel.Administrateur)
			allPersonnel = session.createQuery("select a from Administrateur a", Personnel.class).list();
		else
			allPersonnel = session.createQuery("select d from Developpeur d", Personnel.class).list();
		session.getTransaction().commit();
		return allPersonnel;
	}

	@Override
	public Personnel getPersonnelById(long id) {
		Session session = this.sessionFactory.getCurrentSession();	
		session.beginTransaction();
		Personnel personnel = (Personnel) session.getReference(Personnel.class, Long.valueOf(id));
		session.getTransaction().commit();
		logger.info("Personnel successfully loaded, Personnel details="+personnel);
		return personnel;
	}

	@Override
	public void removePersonnel(long id) {
		Session session = this.sessionFactory.getCurrentSession();	
		session.beginTransaction();
		Personnel personnel = (Personnel) session.getReference(Personnel.class, Long.valueOf(id));
		String infos = personnel.toString();
		if(personnel != null) {
			session.remove(personnel);
		}
		session.getTransaction().commit();
		logger.info("Personnel successfully deleted, Personnel details="+infos);
	}
	
	
}

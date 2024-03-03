package org.nttdata.ecommvcjstl.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.nttdata.ecommvcjstl.dao.CategorieDAO;
import org.nttdata.ecommvcjstl.model.Categorie;
import org.nttdata.ecommvcjstl.util.HibernateUtil;


public class CategorieDAOImpl implements CategorieDAO {
	private static final Logger logger = Logger.getLogger(CategorieDAOImpl.class.getName());

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
	public void addCategorie(Categorie categorie) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(categorie);
		session.getTransaction().commit();
		logger.info("Categorie saved successfully, Categorie Details="+categorie);
	}

	@Override
	public void updateCategorie(Categorie categorie) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.update(categorie);
		session.getTransaction().commit();
		logger.info("Categorie updated successfully, Categorie Details="+categorie);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Categorie> listCategories() {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Categorie> CategoriesList = session.createQuery("from Categorie").list();
		session.getTransaction().commit();
		
		return CategoriesList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Categorie> selectCatByKeyword(String keyWord) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Categorie> CategoriesList = session.createQuery("from Categorie c WHERE c.nom LIKE '%"+keyWord+"%'").list();
		session.getTransaction().commit();
		
		return CategoriesList;
	}
	@Override
	public Categorie getCategorieById(int id) {
		Session session = this.sessionFactory.getCurrentSession();	
		session.beginTransaction();
		Categorie categorie = (Categorie) session.load(Categorie.class, Integer.valueOf(id));
		session.getTransaction().commit();
		//logger.info("Categorie loaded successfully, Categorie details="+categorie);
		return categorie;
	}

	@Override
	public void removeCategorie(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		Categorie categorie = (Categorie) session.load(Categorie.class, Integer.valueOf(id));
		
		if(null != categorie){
			session.delete(categorie);
		}
		session.getTransaction().commit();
		logger.info("Categorie deleted successfully, Categorie details="+categorie);
	}
}

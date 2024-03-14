package service;

import dao.PersonnelDAO;
import dao.ProjetDAO;
import dao.TypePersonnel;
import model.Personnel;
import model.Projet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjetDAOImpl implements ProjetDAO {
	private static final Logger logger = Logger.getLogger(ProjetDAOImpl.class.getName());

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
	public void addProjet(Projet projet) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.persist(projet);
		session.getTransaction().commit();
		logger.info("Projet saved successfully, Projet Details=" + projet);
	}

	@Override
	public void updateProjet(Projet projet) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.merge(projet);
		session.getTransaction().commit();
		logger.info("Projet updated successfully, Projet Details=" + projet);
	}

	@Override
	public List<Projet> listProjet() {
		List<Projet> allProjets = new ArrayList<>();
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		allProjets = session.createQuery("select p from Projet p", Projet.class).list();
		session.getTransaction().commit();
		return allProjets;
	}

	@Override
	public List<Projet> getProjetByKeyword(String keyWord) {
		List<Projet> allProjets = new ArrayList<>();
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		String query = "from Projet p WHERE p.nom LIKE '%" + keyWord + "%' OR p.type LIKE '%" + keyWord + "%'";
		allProjets = session.createQuery(query, Projet.class).list();
		session.getTransaction().commit();
		return allProjets;
	}

	@Override
	public List<Projet> getProjetByType(String type) {
		List<Projet> allProjets;
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		allProjets = session.createQuery("select p from Projet p WHERE p.type = :type", Projet.class)
				.setParameter("type", type).list();
		session.getTransaction().commit();
		return allProjets;
	}

	@Override
	public Projet getProjetById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		Projet projet = (Projet) session.getReference(Projet.class, Long.valueOf(id));
		session.getTransaction().commit();
		logger.info("Projet successfully loaded, Projet details=" + projet);
		return projet;
	}

	@Override
	public void removeProjet(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		Projet projet = (Projet) session.getReference(Projet.class, Long.valueOf(id));
		String infos = projet.toString();
		if (projet != null) {
			session.remove(projet);
		}
		session.getTransaction().commit();
		logger.info("Projet successfully deleted, Projet details=" + infos);
	}
}

package org.nttdata.ecomjstl.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.nttdata.ecomjstl.dao.ProduitDAO;
import org.nttdata.ecomjstl.model.Categorie;
import org.nttdata.ecomjstl.model.Produit;
import org.nttdata.ecomjstl.util.HibernateUtil;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProduitDAOImpl implements ProduitDAO {
    private static final Logger logger = Logger.getLogger(ProduitDAOImpl.class.getName());

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
    public void addProduit(Produit categorie) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(categorie);
        session.getTransaction().commit();
        logger.info("Produit saved successfully, Produit Details=" + categorie);
    }

    @Override
    public void updateProduit(Produit categorie) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(categorie);
        session.getTransaction().commit();
        logger.info("Produit updated successfully, Produit Details=" + categorie);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Produit> listProduits() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Produit> ProduitsList = session.createQuery("from Produit").list();
        session.getTransaction().commit();

        return ProduitsList;
    }

    @Override
    public Produit getProduitById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Produit categorie = (Produit) session.load(Produit.class, Integer.valueOf(id));
        session.getTransaction().commit();
        //logger.info("Produit loaded successfully, Produit details="+categorie);
        return categorie;
    }

    @Override
    public void removeProduit(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Produit categorie = (Produit) session.load(Produit.class, Integer.valueOf(id));

        if (null != categorie) {
            session.delete(categorie);
        }
        session.getTransaction().commit();
        logger.info("Produit deleted successfully, Produit details=" + categorie);
    }

    @Override
    public List<Produit> selectProduitsByKeyword(String keyWord) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Produit> produitsList = session.createQuery("from Produit p WHERE p.designation LIKE '%" + keyWord + "%'").list();
        session.getTransaction().commit();

        return produitsList;
    }
}

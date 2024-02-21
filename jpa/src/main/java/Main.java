import entities.Administrateur;
import entities.Developpeur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        try {

            EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("jpaDemo");

            EntityManager entitymanager = emfactory.createEntityManager();
            entitymanager.getTransaction().begin();

            Developpeur dev1 = new Developpeur();
//            dev1.setId(201);
            dev1.setNom("Youssef");
            dev1.setCin("M253184");
            dev1.setLangage("Java");

            Developpeur dev2 = new Developpeur();
//            dev1.setId(202);
            dev2.setNom("Bouchra");
            dev2.setCin("I342278");
            dev2.setLangage("Python");

            Administrateur admin1 = new Administrateur();
//            admin1.setId(201);
            admin1.setNom("Oussama");
            admin1.setCin("H98654");
            admin1.setFonction("Comptable");

            Administrateur admin2 = new Administrateur();
//            admin2.setId(201);
            admin2.setNom("Marwa");
            admin2.setCin("LE40052");
            admin2.setFonction("GRH");

            entitymanager.persist(dev1);
            entitymanager.persist(dev2);


            entitymanager.persist(admin1);
            entitymanager.persist(admin2);
            entitymanager.getTransaction().commit();


            entitymanager.close();
            emfactory.close();

//		      entitymanager.getTransaction( ).begin( );
//
//		      Categorie categorie = new Categorie( );
//		      categorie.setNom("JPA Category");
//		      categorie.setDescription("Category Created with JPA");
//		      entitymanager.persist( categorie );
//
//		      categorie  = entitymanager.find( Categorie.class, 1 );

//		      //before update
//		      System.out.println( categorie );
//		      categorie.setDescription("Category UPDATED with JPA");
//		      entitymanager.getTransaction( ).commit( );
//
//		      //after update
//		      System.out.println( categorie );
//		      entitymanager.getTransaction( ).commit( );

//		      System.out.println(categorie);
//		      entitymanager.getTransaction( ).commit( );
//		      System.out.println( "\nProducts of this Category :\n" );
//		      categorie.getProduits().stream().forEach(p -> System.out.println(p));
//
//		      Query query = entitymanager.createNamedQuery("find category by id");
//		  	  query.setParameter("id", 1);
//		  	  Categorie categ = (Categorie) query.getSingleResult();
//
//
//		      Query query2 = entitymanager.createNamedQuery("Produit.findAll");
//		  		query.setParameter("id", 123);
//		  		List<Produit> list = query2.getResultList( );
//
//
        } catch (Exception e) {
            System.out.println("Error in main function: \n" + e);
            e.printStackTrace();
        }
    }
}

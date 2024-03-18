package beans;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import model.Categorie;
import model.Produit;
import org.primefaces.model.file.UploadedFile;
import service.CategorieDAOImpl;
import service.ProduitDAOImpl;

@ManagedBean(name = "adminProduits", eager = true)
@SessionScoped
public class AdminProduits implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Produit> allProduits;
    private List<Produit> filteredProduits;
    private List<Produit> categoryProducts;
    private List<Categorie> allCategories;
    private UploadedFile file;
    private Produit selectedProduit;
    private Produit produitToAdd = new Produit();
    private int produit;
    private Categorie selectedCategorie;
    private ProduitDAOImpl prodDao = new ProduitDAOImpl();
    private boolean editMode = false;
    private boolean addMode = false;
    private CategorieDAOImpl categDao = new CategorieDAOImpl();
    private int categorie;
    private ProduitDAOImpl produitService = new ProduitDAOImpl();

    private static final String UPLOAD_DIR = "uploads";

    @PostConstruct
    public void init() {
        allProduits = getAllProduits();
    }

    public void edit() {
        System.out.println("edit clicked");
        editMode = true;
        addMode = false;
    }

    public void cancelUpdate() {
        editMode = false;
    }

    public void prepareAdd() {
        addMode = true;
        editMode = false;
    }

    public void cancelAdd() {
        produitToAdd = new Produit();
        addMode = false;
    }

    public void addProduit() {
        if (produitToAdd != null) {
            produitToAdd.setCategorie(selectedCategorie);
            if (file != null) {
                String fileName = saveFile();
                if (fileName == null) {
                    addMessage(FacesMessage.SEVERITY_WARN, "Ajout échoué", "Erreur lors de l'ajout de le produit");
                    return;
                }
                FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
                produitToAdd.setPhoto(UPLOAD_DIR + File.separator + fileName);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
            produitService.addProduit(produitToAdd);
            System.out.println("Ajout de la catégorie avec Succès");

            addMessage(FacesMessage.SEVERITY_INFO, "Ajout Réussi", "Ajout de produit avec Succès");
        } else {
            addMessage(FacesMessage.SEVERITY_WARN, "Ajout échoué", "Erreur lors de l'ajout de le produit");
        }
        addMode = false;
        produitToAdd = new Produit();
    }

    public List<Categorie> getAllCategories() {
        allCategories = categDao.listCategories();
        return allCategories;
    }

    private String saveFile() {
        try {
            String applicationPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("");
            System.out.println(applicationPath);
            File uploadsDir = new File(applicationPath + File.separator + UPLOAD_DIR);
            if (!uploadsDir.exists()) {
                uploadsDir.mkdir();
            }
            String fileName = file.getFileName();
            File newFile = new File(uploadsDir, fileName);
            try (InputStream input = file.getInputStream()) {
                Files.copy(input, newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateProduit() {
        if (selectedProduit != null) {
            selectedProduit.setCategorie(selectedCategorie);
            System.out.println("Updating... : " + selectedProduit);
            if (file != null) {
                String fileName = saveFile();
                if (fileName == null) {
                    addMessage(FacesMessage.SEVERITY_WARN, "Ajout échoué", "Erreur lors de l'ajout de le produit");
                    return;
                }
                FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
                selectedProduit.setPhoto(UPLOAD_DIR + File.separator + fileName);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
            produitService.updateProduit(selectedProduit);
            System.out.println("Modification de produit avec Succès");
            addMessage(FacesMessage.SEVERITY_INFO, "Modification Réussie", "Modification de produit avec Succès");
        } else {
            addMessage(FacesMessage.SEVERITY_WARN, "Modification échouée", "Erreur lors de la modification de produit");
        }
        editMode = false;
        selectedProduit = new Produit();
    }

    public void deleteProduit() {
        produitService.removeProduit(selectedProduit.getId());
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("msgDel", new FacesMessage(FacesMessage.SEVERITY_INFO, "Produit Supprimée", selectedProduit.toString()));
        allProduits = getAllProduits();
    }

    //    !!
    public List<Produit> getCategoryProducts() {
        Categorie categ = categDao.getCategorieById(categorie);
        categoryProducts = new ArrayList<Produit>(categ.getProduits());
        return categoryProducts;
    }


//	public List<Produit> getCategoryProducts() {
//		return categoryProducts;
//	}

    public void setAllCategories(List<Categorie> allCategories) {
        this.allCategories = allCategories;
    }

    public void setCategoryProducts(List<Produit> categoryProducts) {
        this.categoryProducts = categoryProducts;
    }

    public List<Produit> getAllProduits() {
        allProduits = prodDao.listProduits();
        return allProduits;
    }


    public Produit getSelectedProduit() {
        return selectedProduit;
    }

    public void setSelectedProduit(Produit selectedProduit) {
        this.selectedProduit = selectedProduit;
    }

    public Produit getProduitToAdd() {
        return produitToAdd;
    }

    public void setProduitToAdd(Produit produitToAdd) {
        this.produitToAdd = produitToAdd;
    }

    public boolean isEditMode() {
        return editMode;
    }


    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }


    public boolean isAddMode() {
        return addMode;
    }


    public void setAddMode(boolean addMode) {
        this.addMode = addMode;
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public List<Produit> getFilteredProduits() {
        return filteredProduits;
    }

    public void setFilteredProduits(List<Produit> filteredProducts) {
        this.filteredProduits = filteredProducts;
    }

    public int getProduit() {
        return produit;
    }

    public void setProduit(int produit) {
        this.produit = produit;
    }

    public ProduitDAOImpl getProdDao() {
        return prodDao;
    }

    public void setProdDao(ProduitDAOImpl prodDao) {
        this.prodDao = prodDao;
    }

    public ProduitDAOImpl getProduitService() {
        return produitService;
    }

    public void setProduitService(ProduitDAOImpl produitService) {
        this.produitService = produitService;
    }

    public void setAllProduits(List<Produit> allProduits) {
        this.allProduits = allProduits;
    }

    public Categorie getSelectedCategorie() {
        return selectedCategorie;
    }
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void setSelectedCategorie(Categorie selectedCategorie) {
        this.selectedCategorie = selectedCategorie;
    }

}
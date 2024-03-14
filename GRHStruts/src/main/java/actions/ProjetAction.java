package actions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import model.Developpeur;
import model.Personnel;
import model.Projet;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;
import service.PersonneDAOImpl;
import service.ProjetDAOImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ParentPackage(value = "grh-ntt-data")
@InterceptorRef("jsonValidationWorkflowStack")
@Result(location = "projet.jsp", name = "success")
@Validations(requiredStrings = {
        @RequiredStringValidator(fieldName = "projet.type", type = ValidatorType.FIELD, message = "Le champ type est requis"),
        @RequiredStringValidator(fieldName = "projet.nom", type = ValidatorType.FIELD, message = "La Champ Nom est requis"),
})
public class ProjetAction extends ActionSupport {

    private Projet projet;
    private final ProjetDAOImpl projetService = new ProjetDAOImpl();
    private final PersonneDAOImpl personneService = new PersonneDAOImpl();
    private List<Projet> listProjets;
    private Map<Long, Personnel> mapPersonnels = new HashMap<>();
    private Map<Long, Personnel> mapPersonnelsAffected = new HashMap<>();
    private List<Long> listIdsAffected = new ArrayList<>();
    private List<Long> listIds = new ArrayList<>();
    private List<Personnel> listPersonnelsAffected = new ArrayList<>();
    private List<Personnel> listPersonnels = new ArrayList<>();
    private String id;

    @Action(value = "/add-projet",
            results = {
                    @Result(name = "input", location = "projet.jsp"),
            }
    )
    public String addProjet() throws Exception {
        addActionMessage("Project added successfully");
        projet.setPersonnels(listPersonnelsAffected);
        projetService.addProjet(projet);
        return SUCCESS;
    }
    @Action(value = "/affecter-employes",
            results = {
                    @Result(name = "success", location = "projet.jsp"),
                    @Result(name = "input", location = "projet.jsp"),
            }
    )
    @SkipValidation
    public String affecter() throws Exception {
        addActionMessage("Project added successfully");
        System.out.println(listIdsAffected);
        return SUCCESS;
    }

    @Action(value = "/delete-projet", results = {
            @Result(name = "success", location = "projet.jsp"),
            @Result(name = "input", location = "projet.jsp")
    })
    @SkipValidation
    public String deleteProjet() throws Exception {
        if (id != null) {
            projetService.removeProjet(Integer.parseInt(id));
        }
        listProjets = projetService.listProjet();
        return SUCCESS;
    }

    public Projet getProjet() {
        return projet;
    }


    public void setProjet(Projet projet) {
        this.projet = projet;
    }


    public List<Projet> getListProjets() {
        listProjets = projetService.listProjet();
        return listProjets;
    }

    public void setListProjets(List<Projet> listProjets) {
        this.listProjets = listProjets;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<Long, Personnel> getMapPersonnels() {
        List<Personnel> listPersonnels= personneService.listPersonnel();
        for (Personnel personnel : listPersonnels) {
            mapPersonnels.put(personnel.getId(), personnel);
        }
        return mapPersonnels;
    }

    public Map<Long, Personnel> getMapPersonnelsAffected() {
        List<Personnel> listPersonnels = personneService.listPersonnel();
        for (Personnel personnel : listPersonnels) {
            mapPersonnelsAffected.put(personnel.getId(), personnel);
        }
        return new HashMap<>();
    }

    public void setListIdsAffected(List<Long> listIdsAffected) {
        this.listIdsAffected = listIdsAffected;
        for (Long id : listIdsAffected) {
            System.out.println(id);
            Personnel personnel = personneService.getPersonnelById(id);
            listPersonnelsAffected.add(personnel);
        }
    }


    public void setListIds(List<Long> listIds) {
        this.listIds = listIds;
        for (Long id : listIds) {
            System.out.println(id);
            Personnel personnel = personneService.getPersonnelById(id);
            listPersonnels.add(personnel);
        }
    }
}

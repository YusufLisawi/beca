package actions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import dao.TypePersonnel;
import model.Developpeur;
import model.Personnel;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;
import service.PersonneDAOImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@ParentPackage(value = "grh-ntt-data")
@InterceptorRef("jsonValidationWorkflowStack")
@Result(location = "developpeur.jsp", name = "success")
@Validations(requiredStrings = {
        @RequiredStringValidator(fieldName = "dev.nom", type = ValidatorType.FIELD, message = "Le champ Nom est requis"),
        @RequiredStringValidator(fieldName = "dev.prenom", type = ValidatorType.FIELD, message = "La Champ Prénom est requis"),
}, emails = {
        @EmailValidator(type = ValidatorType.SIMPLE, fieldName = "dev.email", message = "Veuillez entrer un email valide.")
})
public class DeveloppeurAction extends ActionSupport {

    private Developpeur dev;
    private String dateNaissance;
    private String dateEmbauche;
    private File photo;
    private String photoContentType;
    private String photoFileName;
    private final PersonneDAOImpl personneService = new PersonneDAOImpl();
    private List<Personnel> listPersonnels;

    private String id;

    @Action(value = "/add-dev",
    interceptorRefs = {
            @InterceptorRef(value = "fileUpload", params = {"maximumSize", "2097152", "allowedTypes", "image/jpeg,image/png,image/gif"}),
            @InterceptorRef("jsonValidationWorkflowStack")
    },
    results = {
            @Result(name = "input", location = "developpeur.jsp"),
    }
    )
    public String addDev() throws Exception {
        try {
            String applicationPath = ServletActionContext.getServletContext().getRealPath("");
            System.out.println(applicationPath);
            File uploadsDir = new File(applicationPath + File.separator + "uploads");
            if (!uploadsDir.exists()) {
                uploadsDir.mkdir();
            }
            photoFileName = UUID.randomUUID() + photoFileName;
            if (photo != null) {
                FileInputStream fis = new FileInputStream(photo);
                FileOutputStream fos = new FileOutputStream(uploadsDir + File.separator + photoFileName);
                byte[] buffer = new byte[1024];
                while (fis.read(buffer) != -1) {
                    fos.write(buffer);
                }
                fos.close();
                fis.close();
                dev.setPhoto(photoFileName);
            }
        } catch (IOException e) {
            addActionError("Error uploading file");
            e.printStackTrace();
        }
        addActionMessage("Thank you for Registration!");
        personneService.addPersonnel(dev);
        return SUCCESS;
    }

    @Action(value = "/delete-dev", results = {
            @Result(name = "success", location = "developpeur.jsp"),
            @Result(name = "input", location = "developpeur.jsp")
    })
    @SkipValidation
    public String deleteDev() throws Exception {
        System.out.println(id);
        if (id != null) {
            personneService.removePersonnel(Integer.parseInt(id));
        }
        listPersonnels = personneService.getPersonnelByType(TypePersonnel.DEVELOPPEUR);
        return SUCCESS;
    }

    public Developpeur getDev() {
        return dev;
    }


    public void setDev(Developpeur dev) {
        this.dev = dev;
    }


    public File getPhoto() {
        return photo;
    }


    public void setPhoto(File photo) {
        this.photo = photo;
    }


    public String getPhotoContentType() {
        return photoContentType;
    }


    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }


    public String getPhotoFileName() {
        System.out.println("Setter PhotoFileName");
        return photoFileName;
    }


    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }


    public String getDateNaissance() {
        return dateNaissance;
    }


    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
        if (dateNaissance != null && !dateNaissance.equals("")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            this.dev.setDateNaissance(LocalDate.parse(dateNaissance, formatter));
        }
    }


    public String getDateEmbauche() {
        return dateEmbauche;
    }


    public void setDateEmbauche(String dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
        if (dateEmbauche != null && !dateEmbauche.equals("")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            this.dev.setDateEmbauche(LocalDate.parse(dateEmbauche, formatter));
        }
    }

    public List<Personnel> getListPersonnels() {
        listPersonnels = personneService.getPersonnelByType(TypePersonnel.DEVELOPPEUR);
        return listPersonnels;
    }

    public void setListPersonnels(List<Personnel> listPersonnels) {
        this.listPersonnels = listPersonnels;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        System.out.println(id);
        this.id = id;
    }
}

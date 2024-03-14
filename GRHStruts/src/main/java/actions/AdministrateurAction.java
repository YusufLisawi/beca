/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package actions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.*;

import dao.TypePersonnel;
import model.Administrateur;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.Personnel;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;
import service.PersonneDAOImpl;

@ParentPackage(value = "grh-ntt-data")
@InterceptorRef("jsonValidationWorkflowStack")
@Result(location = "administrateur.jsp", name = "success")
public class AdministrateurAction extends ActionSupport {

    private Administrateur admin;
    private String dateNaissance;
    private String dateEmbauche;
    private File photo;
    private String photoContentType;
    private String photoFileName;
    private final PersonneDAOImpl personneService = new PersonneDAOImpl();
    private List<Personnel> listPersonnels;

    private String id;

    @Action(value = "/add-admin",
    interceptorRefs = {
            @InterceptorRef(value = "fileUpload", params = {"maximumSize", "2097152", "allowedTypes", "image/jpeg,image/png,image/gif"}),
            @InterceptorRef("jsonValidationWorkflowStack")
    },
    results = {
            @Result(name = "input", location = "administrateur.jsp"),
    }
    )
    @Validations(requiredStrings = {
            @RequiredStringValidator(fieldName = "admin.nom", type = ValidatorType.FIELD, message = "Le champ Nom est requis"),
            @RequiredStringValidator(fieldName = "admin.prenom", type = ValidatorType.FIELD, message = "La Champ Prénom est requis"),
            @RequiredStringValidator(fieldName = "admin.fonction", type = ValidatorType.FIELD, message = "La Champ Fonction est requis")
    }, emails = {
            @EmailValidator(type = ValidatorType.SIMPLE, fieldName = "admin.email", message = "Veuillez entrer un email valide.")
    })
    public String addAdmin() throws Exception {
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
                admin.setPhoto(photoFileName);
            }
        } catch (IOException e) {
            addActionError("Error uploading file");
            e.printStackTrace();
        }
        addActionMessage("Thank you for Registration!");
        personneService.addPersonnel(admin);
        return SUCCESS;
    }

    @Action(value = "/delete-admin", results = {
            @Result(name = "success", location = "administrateur.jsp"),
            @Result(name = "input", location = "administrateur.jsp")
    })
    @SkipValidation
    public String deleteAdmin() throws Exception {
        System.out.println(id);
        if (id != null) {
            personneService.removePersonnel(Integer.parseInt(id));
        }
        listPersonnels = personneService.getPersonnelByType(TypePersonnel.Administrateur);
        return SUCCESS;
    }

    public Administrateur getAdmin() {
        return admin;
    }


    public void setAdmin(Administrateur admin) {
        this.admin = admin;
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
            this.admin.setDateNaissance(LocalDate.parse(dateNaissance, formatter));
        }
    }


    public String getDateEmbauche() {
        return dateEmbauche;
    }


    public void setDateEmbauche(String dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
        if (dateEmbauche != null && !dateEmbauche.equals("")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            this.admin.setDateEmbauche(LocalDate.parse(dateEmbauche, formatter));
        }
    }

    public List<Personnel> getListPersonnels() {
        listPersonnels = personneService.getPersonnelByType(TypePersonnel.Administrateur);
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

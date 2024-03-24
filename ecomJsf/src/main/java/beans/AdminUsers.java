package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Response;
import model.User;
import model.Produit;

@ManagedBean(name = "adminUsers", eager = true)
@SessionScoped
public class AdminUsers implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<User> allUsers;
    private List<User> filteredUsers;
    private User selectedUser;
    private User userToAdd = new User();
    private int user;
    private boolean editMode = false;
    private boolean addMode = false;
    private UserRestBean userRestBean = new UserRestBean();


    @PostConstruct
    public void init() {
        allUsers = userRestBean.getUsers();
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
        userToAdd = new User();
    }

    public void cancelAdd() {
        addMode = false;
        userToAdd = new User();
    }

    public void addUser() {
        if (userToAdd != null) {
            Response res = userRestBean.addUser(userToAdd);
            addMessage(FacesMessage.SEVERITY_INFO, res.isStatus() ? "Ajout Réussi" : "Ajout échouée", res.getMessage());
        } else {
            addMessage(FacesMessage.SEVERITY_WARN, "Ajout échoué", "Erreur lors de l'ajout de l'utilisateur");
        }
        addMode = false;
    }

    public void updateUser() {
        if (selectedUser != null) {
            Response res = userRestBean.updateUser(selectedUser);
            if (res != null)
                addMessage(FacesMessage.SEVERITY_INFO, res.isStatus() ? "Modification Réussie" : "Modification échouée", res.getMessage());
            else
                addMessage(FacesMessage.SEVERITY_WARN, "Modification échouée", "Erreur lors de la modification de l'utilisateur");
        } else {
            addMessage(FacesMessage.SEVERITY_WARN, "Modification échouée", "Erreur lors de la modification de l'utilisateur");
        }
        editMode = false;
    }

    public void deleteSelectedUser() {
        Response res = userRestBean.deleteUser(selectedUser.getId());
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("msgDel", new FacesMessage(FacesMessage.SEVERITY_INFO, res.getMessage(), selectedUser.toString()));
        allUsers = getAllUsers();
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }


    public List<User> getFilteredUsers() {
        return filteredUsers;
    }

    public void setFilteredUsers(List<User> filteredUsers) {
        this.filteredUsers = filteredUsers;
    }

    public User getSelectedUser() {
        return selectedUser;
    }


    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }


    public User getUserToAdd() {
        return userToAdd;
    }


    public void setUserToAdd(User userToAdd) {
        this.userToAdd = userToAdd;
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
        System.out.println("addMode = " + addMode);
        this.addMode = addMode;
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public List<User> getAllUsers() {
        allUsers = userRestBean.getUsers();
        return allUsers;
    }

    public UserRestBean getUserRestBean() {
        return userRestBean;
    }
}

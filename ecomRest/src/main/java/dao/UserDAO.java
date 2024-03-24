package dao;

import java.util.List;
import model.User;

public interface UserDAO {
    public void addUser(User user);
    public void updateUser(User user);
    public List<User> listUsers();
    public User getUserById(int id);
    public void removeUser(int id);
    public User getUserByName(String nom);
    public User getUserByNameAndPassword(String nom, String mdp);
}

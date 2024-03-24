package service;

import model.Response;
import model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public Response addUser(User u);
    public Response deleteUser(int id);
    public User getUser(int id);
    public List<User> getAllUsers();
}

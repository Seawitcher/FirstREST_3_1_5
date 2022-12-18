package com.example.FirstREST.DAO;



import com.example.FirstREST.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserDAO {


    public void add(User user);

    public List<User> getList();

    public User getUser(Long id);

    public void deleteUser(Long id);

    public void editUser(User user);

    public UserDetails getUser(String email);

    User getUserEmail(String email);
}

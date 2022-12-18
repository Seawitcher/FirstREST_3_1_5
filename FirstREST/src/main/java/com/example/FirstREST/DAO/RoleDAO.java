package com.example.FirstREST.DAO;



import com.example.FirstREST.model.Role;

import java.util.List;

public interface RoleDAO {

    boolean add(Role user);



    Role getRole(Long id);


    List<Role> getList();

    void deleteRole(Long id);

    void editRole(Role role);

    List<Role> listByName(List<String> name);

    Role findByName(String name);
}

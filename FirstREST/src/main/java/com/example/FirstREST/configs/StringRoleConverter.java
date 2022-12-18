package com.example.FirstREST.configs;


import com.example.FirstREST.model.Role;
import org.springframework.core.convert.converter.Converter;


public class StringRoleConverter implements Converter<String, Role> {

    @Override
    public Role convert(String id) {
        Role role = new Role();
        role.setId(Long.valueOf(id));
        return role;
    }
}



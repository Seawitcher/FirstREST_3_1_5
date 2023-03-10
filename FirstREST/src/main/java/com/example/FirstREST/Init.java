package com.example.FirstREST;


import com.example.FirstREST.model.Role;
import com.example.FirstREST.model.User;
import com.example.FirstREST.service.RoleService;
import com.example.FirstREST.service.UserService;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;


@Component
public class Init {



        private UserService userService;
        private RoleService roleService;

    @Autowired
    public Init(UserService userService, RoleService roleService) {
            this.userService = userService;
            this.roleService = roleService;
        }


    //    @EventListener(ApplicationReadyEvent.class)
    @PostConstruct
        public void createTable () {
            if (roleService.getList().isEmpty()) {
                Role admin = new Role(1L, "ROLE_ADMIN");
                Role user = new Role(2L, "ROLE_USER");
                roleService.add(admin);
                roleService.add(user);
                List<Role> setRole = new ArrayList<>();
                setRole.add(admin);
                User newAdmin = new User("Alex", "Zer", 20,
                        "sea@yan.ru", "$2a$12$nNj251hCDwafhk/uGw2Wtehm7whW7QWOx6rdmxLKliLl5X8QbUSC.", setRole);

                userService.add(newAdmin);

                System.out.println("hello, I have just create few users: \n" +
                        "username: Alex      password: 1 \n");
            }
        }
    }

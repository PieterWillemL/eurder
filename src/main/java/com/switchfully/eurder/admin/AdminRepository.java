package com.switchfully.eurder.admin;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class AdminRepository {
    private final HashMap<String, Admin> admins = new HashMap<>();
    public AdminRepository(){
        admins.put("admin@eurder.com", new Admin("admin@eurder.com", "password"));
    }

    public HashMap<String, Admin> getAdmins() {
        return admins;
    }
}

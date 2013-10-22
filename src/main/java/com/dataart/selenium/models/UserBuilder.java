package com.dataart.selenium.models;

import com.dataart.selenium.framework.Utils;

import java.util.UUID;

public class UserBuilder {
    public static User admin() {
        User user = new User("admin", "admin");
        user.setFname("Ivan");
        user.setLname("Petrov");
        return user;
    }

    public static User randomUser() {
        String uuid = Utils.getUUID();
        User user = new User("User_" + uuid, "admin");
        user.setFname("Ivan" + uuid);
        user.setLname("Petrov" + uuid);
        return user;
    }
}

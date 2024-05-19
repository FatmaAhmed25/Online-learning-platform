package com.example.onlinelearningplatform.entities;

import javax.persistence.*;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {

    public Admin() {
        super();
    }

    public Admin(String name, String email, String password) {
        super(name, email, password, UserRole.ADMIN);
    }


}

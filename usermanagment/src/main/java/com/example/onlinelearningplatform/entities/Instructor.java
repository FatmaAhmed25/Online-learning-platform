package com.example.onlinelearningplatform.entities;
import javax.persistence.*;

@Entity
@Table(name="instructor")
public class Instructor extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private String yearsOfExperience;


    public Instructor() {
        super();
    }
    public Instructor(String name, String email, String password, String affiliation, String bio, UserRole role,String yearsOfExperience) {
        super(name, email, password, affiliation, bio, role);
//        this.yearsOfExperience=yearsOfExperience;
    }


//    public String getYearsOfExperience() {
//        return yearsOfExperience;
//    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

package com.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

import static com.example.secure.SecurityConfig.PASSWORD_ENCODER;

/**
 * Created by THINK on 2016/10/23.
 */
@Data
@ToString(exclude = "password")
@Entity
public class Manager {

    @Id
    private String name;
    @JsonIgnore
    private String password;
    @OneToMany(mappedBy = "manager")
    Collection<MGroupHasManagers> mgroups;

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }
}

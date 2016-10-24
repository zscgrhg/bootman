package com.example.domain;

import com.example.secure.SecurityConfig;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by THINK on 2016/10/24.
 */
@Data
@ToString(exclude = {"password"})
@Entity
public class UUser implements Serializable {

    private static final long serialVersionUID = -8748293873537157101L;
    @Id
    String username;
    @JsonIgnore
    String password;
    boolean enabled;
    @ManyToMany
    Collection<GGroup> groups;

    public void setPassword(String password) {
        this.password = SecurityConfig.PASSWORD_ENCODER.encode(password);
    }
}

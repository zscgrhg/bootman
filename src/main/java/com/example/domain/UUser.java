package com.example.domain;

import com.example.secure.SecurityConfig;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by THINK on 2016/10/24.
 * {@link ToString}exclude the {@link UUser#groups} lazy Collection to avoid {@link StackOverflowError}
 */
@Data
@ToString(exclude = {"password", "groups"})
@Entity
@Access(AccessType.FIELD)
public class UUser implements Serializable {

    private static final long serialVersionUID = -8748293873537157101L;
    @Id
    String username;
    @JsonIgnore
    String password;
    boolean enabled;
    @ManyToMany
    Collection<GGroup> groups;
    @Version
    @JsonIgnore
    long version;


    public void setPassword(String password) {
        this.password = SecurityConfig.PASSWORD_ENCODER.encode(password);
    }
}

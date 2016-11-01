package com.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by THINK on 2016/10/24.
 */
@Data
@Entity
@ToString(exclude = {"users"})
public class GGroup implements Serializable {

    private static final long serialVersionUID = -3319399054490265347L;
    @Id
    String name;
    String description;
    @ManyToMany(mappedBy = "groups")
    @JsonIgnore
    Collection<UUser> users;
    @ElementCollection
    Set<String> authorities;
}

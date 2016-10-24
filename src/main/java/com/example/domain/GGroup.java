package com.example.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by THINK on 2016/10/24.
 */
@Data
@Entity
public class GGroup implements Serializable {

    private static final long serialVersionUID = -3319399054490265347L;
    @Id
    String name;
    String description;
    @ManyToMany(mappedBy = "groups")
    Collection<UUser> users;
    String[] authorities;
}

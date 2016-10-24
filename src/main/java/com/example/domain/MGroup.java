package com.example.domain;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

/**
 * Created by THINK on 2016/10/23.
 */
@Data
@Entity
public class MGroup {
    @Id
    String name;
    @OneToMany(mappedBy = "mgroup")
    Collection<MGroupHasAuthorities> authorities;
    @OneToMany(mappedBy = "mgroup")
    Collection<MGroupHasManagers> managers;
}

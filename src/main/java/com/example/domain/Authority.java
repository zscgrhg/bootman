package com.example.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

/**
 * Created by THINK on 2016/10/23.
 */
@Entity
@Data
public class Authority {
    @Id
    String name;
    @OneToMany(mappedBy = "authority")
    Collection<MGroupHasAuthorities> mgroups;
}

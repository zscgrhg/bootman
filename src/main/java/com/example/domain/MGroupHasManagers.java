package com.example.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by THINK on 2016/10/23.
 */
@Data
@Entity
public class MGroupHasManagers {
     @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private MGroup mgroup;
    @ManyToOne
    private Manager manager;
}

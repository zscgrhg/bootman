package com.example.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by THINK on 2016/10/25.
 */
@Entity
@Data
public class BBook {
    @TableGenerator(name = "TABLE_ID_GEN")
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "TABLE_ID_GEN")
    @Id
    int id;
    String name;
}

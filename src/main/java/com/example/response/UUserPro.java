package com.example.response;

import com.example.domain.GGroup;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.Set;

/**
 * Created by THINK on 2016/10/31.
 */
public interface UUserPro {

    String getUsername();
}

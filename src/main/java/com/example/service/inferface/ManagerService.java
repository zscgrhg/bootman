package com.example.service.inferface;

import com.example.domain.Manager;
import javax.transaction.Transactional;

/**
 * Created by THINK on 2016/10/23.
 */
@Transactional
public interface ManagerService  extends CrudSupport<Manager>{
    Manager loadManagerWithAuthorities(String username);
}

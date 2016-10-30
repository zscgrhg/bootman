package com.example.service.impl;

import com.example.domain.GGroup;
import com.example.service.inferface.GGroupService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * Created by THINK on 2016/10/24.
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class GGroupServiceImpl implements GGroupService{


    @PersistenceContext
    EntityManager em;
    @Override
    public Class<GGroup> getEntityClass() {
        return GGroup.class;
    }

    @Override
    public EntityManager getEntityManager() {
        System.out.println("######"+em);
        return em;
    }


}

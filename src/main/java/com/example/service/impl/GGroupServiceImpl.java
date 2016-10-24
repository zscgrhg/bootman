package com.example.service.impl;

import com.example.domain.GGroup;
import com.example.service.inferface.GGroupService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Created by THINK on 2016/10/24.
 */
@Service
@Transactional(rollbackOn = Throwable.class)
public class GGroupServiceImpl implements GGroupService {
    @PersistenceContext
    EntityManager em;
    @Override
    public Class<GGroup> getEntityClass() {
        return GGroup.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}

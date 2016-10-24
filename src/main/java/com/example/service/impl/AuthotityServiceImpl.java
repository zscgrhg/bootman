package com.example.service.impl;

import com.example.domain.Authority;
import com.example.service.inferface.AuthorityService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Created by THINK on 2016/10/24.
 */
@Service
@Transactional
public class AuthotityServiceImpl implements AuthorityService{
    @PersistenceContext
    private EntityManager em;
    @Override
    public Class<Authority> getEntityClass() {
        return Authority.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}

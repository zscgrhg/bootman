package com.example.service.impl;

import com.example.domain.UUser;
import com.example.service.inferface.UUserService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;

/**
 * Created by THINK on 2016/10/24.
 */
@Service
@Transactional(rollbackOn = Throwable.class)
public class UUserServiceImpl implements UUserService {
    @PersistenceContext
    EntityManager em;

    @Override
    public Class<UUser> getEntityClass() {
        return UUser.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }


    @Override
    public UUser loadUUserAndAuthoritiesById(final String username) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<UUser> rt = cq.from(UUser.class);

        rt.fetch("groups", JoinType.LEFT);
        cq.select(rt);
        Predicate equal = cb.equal(rt.get("username"), username);
        cq.where(equal);
        TypedQuery<UUser> query = getEntityManager().createQuery(cq);
        return query.getSingleResult();
    }
}

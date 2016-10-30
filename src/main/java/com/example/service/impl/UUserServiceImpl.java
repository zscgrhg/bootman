package com.example.service.impl;

import com.example.domain.UUser;
import com.example.service.inferface.UUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.Collection;

/**
 * Created by THINK on 2016/10/24.
 */
@Service
@Transactional(rollbackFor = Throwable.class)
@Slf4j
public class UUserServiceImpl implements UUserService {

    @PersistenceContext
    EntityManager em;
    @PersistenceUnit
    EntityManagerFactory emf;

    @Override
    public Class<UUser> getEntityClass() {
        return UUser.class;
    }


    public EntityManager getEntityManager() {

        return em;
    }


    @Override
    @Transactional
    public UUser loadUUserAndAuthoritiesById(final String username) {
        EntityManager v_entityManager = getEntityManager();
        log.debug(v_entityManager.isJoinedToTransaction() + " ");
        CriteriaBuilder cb = v_entityManager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<UUser> rt = cq.from(UUser.class);
        rt.fetch("groups", JoinType.LEFT);
        cq.select(rt);
        Predicate equal = cb.equal(rt.get("username"), username);
        cq.where(equal);
        TypedQuery<UUser> query = v_entityManager.createQuery(cq);
        return query.getSingleResult();
    }

    @Override
    public Collection<UUser> testJPQL() {

        TypedQuery<UUser> v_query = em.createQuery("SELECT u FROM UUser u", UUser.class);
        return v_query.getResultList();
    }

    public UUser T1(final String username) {
        UUser v_user = findById(username);
        v_user.setPassword("12345");
        T2(username);
        return v_user;
    }

    @Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRES_NEW)
    public UUser T2(final String username) {
        UUser v_user = findById(username);
        v_user.setPassword("123");
        return v_user;
    }


    public UUser T3(final String username) {
        UUser v_user = findById(username);
        v_user.setPassword("12345");
        em.merge(v_user);
        T4(username);
        return v_user;
    }

    @Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRES_NEW)
    public UUser T4(final String username) {
        Query v_nativeQuery = em.createNativeQuery("update uuser set password='123456' where 1=1");
        int v_i = v_nativeQuery.executeUpdate();
        em.clear();
        return new UUser();
    }

    @Override
    public UUser testRN(final String username) {
        return T1(username);
    }

    @Override
    public UUser testRN1(final String username) {
        return T3(username);
    }
}

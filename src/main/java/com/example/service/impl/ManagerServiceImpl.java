package com.example.service.impl;

import com.example.domain.Manager;
import com.example.service.inferface.ManagerService;
import org.springframework.stereotype.Service;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;

/**
 * Created by THINK on 2016/10/23.
 */
@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Class<Manager> getEntityClass() {
        return Manager.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    @Deprecated
    public Manager loadManagerWithAuthorities(String username) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Manager> rt = cq.from(Manager.class);

        rt.fetch("mgroups", JoinType.LEFT)
                .fetch("mgroup", JoinType.LEFT)
              //  .fetch("authorities", JoinType.LEFT)
               // .fetch("authority", JoinType.LEFT)
        ;
        cq.select(rt);
        Predicate equal = cb.equal(rt.get("name"), username);
        cq.where(equal);
        TypedQuery<Manager> query = getEntityManager().createQuery(cq);
        return query.getSingleResult();
    }
}

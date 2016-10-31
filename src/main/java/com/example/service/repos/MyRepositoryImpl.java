package com.example.service.repos;

import com.example.jpa.condition.ConditionList;
import com.example.jpa.tools.CUtil;
import com.example.response.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.io.Serializable;
import java.util.List;

/**
 * Created by THINK on 2016/10/31.
 */


public class MyRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> implements MyRepository<T, ID> {

    private final EntityManager entityManager;
    private final Class<T> domainClass;

    public MyRepositoryImpl(JpaEntityInformation entityInformation,
                            EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.domainClass = entityInformation.getJavaType();
    }


    @Override
    public Pageable<T> findPageByConditions(ConditionList<T> conditionList) {
        List<T> v_allByConditions = CUtil.findByConditions(entityManager, domainClass, conditionList, LockModeType.READ);

        int v_total = CUtil.countByConditions(entityManager, domainClass, conditionList);
        Pageable<T> v_pageable = new Pageable(conditionList.page, conditionList.pageSize, v_total, v_allByConditions);
        return v_pageable;
    }


}



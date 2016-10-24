package com.example.service.inferface;

import com.example.service.condition.ConditionMap;
import com.example.service.condition.Pagination;


import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

/**
 * Created by THINK on 2016/10/23.
 */

public interface Crud<T> {
    Class<T> getEntityClass();
    void create(T entity);

    void update(T entity);

    public void createAll(Collection<T> entityCollection);


    public void delete(T entity);

    public T findById(Object id);

    public List<T> findAll();

    public List<T> findRange(int from, int to);

    public int count();
    public List<T> findAllByConditionMap(ConditionMap conditionMap);
    public Pagination<T> findByConditionMap(ConditionMap conditionMap);
}

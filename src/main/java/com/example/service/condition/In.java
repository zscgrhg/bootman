package com.example.service.condition;

import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by THINK on 2016/10/23.
 */
public class In<T> implements Condition<Collection<T>>  {
    @Override
    public void apply(CriteriaBuilder cb, CriteriaQuery cq, Root rt, String path, List<Predicate> predicates, Collection<T> params) {
        Path p = getPath(rt, path);
        Predicate in = p.in(params);
        predicates.add(in);
    }
}

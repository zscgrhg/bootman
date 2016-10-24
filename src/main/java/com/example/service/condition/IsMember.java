package com.example.service.condition;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by THINK on 2016/10/23.
 */
public class IsMember<T> implements Condition<T>  {
    @Override
    public void apply(CriteriaBuilder cb, CriteriaQuery cq, Root rt, String path, List<Predicate> predicates, T params) {
        Path p = getPath(rt, path);
        Predicate isMember = cb.isMember(params, p);
        predicates.add(isMember);
    }
}

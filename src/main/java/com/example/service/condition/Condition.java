package com.example.service.condition;

import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by THINK on 2016/10/23.
 */
public interface Condition<T> {
    default   Path getPath(Root rt, String attrPath) {
        String[] path = attrPath.split("\\.");
        Path x = rt;
        for (int i = 0; i < path.length - 1; i++) {
            if (x instanceof Root) {
                x = ((Root) x).join(path[i]);
            } else {
                x = ((Join) x).join(path[i]);
            }
        }
        String attr = path[path.length - 1];
        x = x.get(attr);
        return x;
    }


    void apply(CriteriaBuilder cb, CriteriaQuery cq, Root rt, String path, List<Predicate> predicates,T params);
}

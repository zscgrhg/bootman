package com.example.service.condition;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by THINK on 2016/10/23.
 */
public class Like implements Condition<String> {
    public enum Mode {
        START, END
    }

    final Mode matchMode;

    public Like(Mode matchMode) {
        this.matchMode = matchMode;
    }

    @Override
    public void apply(CriteriaBuilder cb, CriteriaQuery cq, Root rt, String path, List<Predicate> predicates, String params) {
        Path p = getPath(rt, path);
        String t;
        switch (matchMode) {
            case START:
                t = params + "%";
                break;
            case END:
                t = "%";
                break;
            default:
                t = "%" + params + "%";
        }
        Predicate like = cb.like(p, t);
        predicates.add(like);
    }
}

package com.example.service.condition;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by THINK on 2016/10/23.
 */
public class Compare<T extends Comparable> implements Condition<T> {
    public enum C {
        NEQ, GTE, LTE, GT, LT, EQ
    }

    public static <C extends Comparable> Compare EQ(Class<C> clazz) {
        return new Compare<C>(Compare.C.EQ);
    }
    public static <C extends Comparable> Compare NEQ(Class<C> clazz) {
        return new Compare<C>(Compare.C.NEQ);
    }
    public static <C extends Comparable> Compare GTE(Class<C> clazz) {
        return new Compare<C>(Compare.C.GTE);
    }
    public static <C extends Comparable> Compare LTE(Class<C> clazz) {
        return new Compare<C>(Compare.C.LTE);
    }
    public static <C extends Comparable> Compare GT(Class<C> clazz) {
        return new Compare<C>(Compare.C.GT);
    }
    public static <C extends Comparable> Compare LT(Class<C> clazz) {
        return new Compare<C>(Compare.C.LT);
    }


    private final C c;

    public Compare(C c) {
        this.c = c;
    }

    @Override
    public void apply(CriteriaBuilder cb, CriteriaQuery cq, Root rt, String path, List<Predicate> predicates, T params) {

        Path p = getPath(rt, path);
        Predicate comp;
        switch (c) {
            case GT:
                comp = cb.greaterThan(p, params);
                break;
            case GTE:
                comp = cb.greaterThanOrEqualTo(p, params);
                break;
            case LT:
                comp = cb.lessThan(p, params);
                break;
            case LTE:
                comp = cb.lessThanOrEqualTo(p, params);
                break;
            case NEQ:
                comp = cb.notEqual(p, params);
                break;
            default:
                comp = cb.equal(p, params);

        }
        predicates.add(comp);
    }
}

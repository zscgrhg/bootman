package com.example.service.inferface;

import com.example.service.condition.Condition;
import com.example.service.condition.ConditionMap;
import com.example.service.condition.Mapper;
import com.example.service.condition.Pagination;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.Type;
import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by THINK on 2016/10/23.
 */
@Transactional(rollbackOn = Throwable.class)
public interface CrudSupport<T> extends Crud<T> {



    EntityManager getEntityManager();


    default void create(T entity) {
        getEntityManager().persist(entity);
    }


    default void update(T entity) {
        getEntityManager().merge(entity);
    }


    default void createAll(Collection<T> entityCollection) {

    }

    default void delete(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    default T findById(Object id) {
        return getEntityManager().find(getEntityClass(), id);
    }

    default List<T> findAll() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root rt = cq.from(getEntityClass());
        cq.select(rt);
        return getEntityManager().createQuery(cq).getResultList();
    }

    default List<T> findRange(int from, int to) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root rt = cq.from(getEntityClass());
        cq.select(rt);

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(to - from + 1);
        q.setFirstResult(from);
        return q.getResultList();
    }

    default int count() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(getEntityClass());
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    default List<T> findAllByConditionMap(ConditionMap<T> conditionMap) {
        CriteriaQuery cq = createQuery(conditionMap);
        Set<Root> roots = cq.getRoots();
        Root rt = roots.iterator().next();
        cq.select(rt);
        TypedQuery query = getEntityManager().createQuery(cq);
        query.setFirstResult(0);
        query.setMaxResults(Integer.MAX_VALUE);
        return query.getResultList();
    }

    default Pagination<T> findByConditionMap(ConditionMap<T> conditionMap) {
        CriteriaQuery cq = createQuery(conditionMap);
        Set<Root> roots = cq.getRoots();
        Root rt = roots.iterator().next();
        cq.select(rt);
        TypedQuery query = getEntityManager().createQuery(cq);
        Pagination<T> pagination = new Pagination<>();
        query.setFirstResult(conditionMap.getFirstResult());
        query.setMaxResults(conditionMap.getPageSize());
        pagination.setPage(conditionMap.getPage());
        pagination.setPageSize(conditionMap.getPageSize());
        pagination.setItems(query.getResultList());
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        TypedQuery count = getEntityManager().createQuery(cq);
        int c = ((Long) count.getSingleResult()).intValue();
        pagination.setTotal(c);
        return pagination;
    }

    default CriteriaQuery createQuery(ConditionMap<T> conditionMap) {
        Metamodel metamodel = getEntityManager().getMetamodel();
        EntityType<?> entityType = metamodel.entity(getEntityClass());

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root rt = cq.from(getEntityClass());
        List<Predicate> predicates = new ArrayList<>();

        Map paramsMap = conditionMap.getParamsMapp();

        List<Mapper> mapper = conditionMap.getMapper();

        for (Mapper mapper1 : mapper) {
            String key = mapper1.key;
            if (paramsMap.containsKey(key)) {
                Object value = paramsMap.get(key);
                if (value == null
                        || (value instanceof String && StringUtils.isEmpty((String) value))
                        || value instanceof Map) {
                    continue;
                } else {
                    Condition condition = mapper1.Condition;
                    String path = mapper1.path;
                    condition.apply(cb, cq, rt, path, predicates, value);
                }
            }
        }
        cq.where(predicates.toArray(new Predicate[predicates.size()]));

        cq.distinct((conditionMap).isDistinct());
        boolean hasSingleId = entityType.hasSingleIdAttribute();
        String orderBy = conditionMap.getOrderBy();
        if (null != orderBy
                && (!orderBy.trim().isEmpty())) {
            cq.orderBy(cb.asc(rt.get(orderBy)));
        } else if (hasSingleId) {
            Type<?> idType = entityType.getIdType();
            Class<?> javaType = idType.getJavaType();
            SingularAttribute<?, ?> id = entityType.getId(javaType);
            cq.orderBy(cb.desc(rt.get(id)));
        }
        return cq;
    }

}

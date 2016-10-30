package com.example.service.condition;

import java.util.*;

/**
 * Created by THINK on 2016/10/24.
 */
public class SimpleConditionMap<T> extends ConditionMap<T> {
    public final List<Mapper> mappers;
    public final Map<String, Object> paramsMap;
    public final Class<T> clazz;

    public SimpleConditionMap(Class<T> clazz, List<Mapper> m, Map<String, Object> p) {
        this.clazz = clazz;
        this.mappers = Collections.unmodifiableList(m == null ? Collections.emptyList() : m);
        this.paramsMap = Collections.unmodifiableMap(p == null ? Collections.emptyMap() : p);
    }

    public SimpleConditionMap(Class<T> clazz, Map<String, Condition> map, Map<String, Object> paramsMap) {
        this.clazz = clazz;
        List<Mapper> m = new ArrayList<>();
        for (Map.Entry<String, Condition> entry : map.entrySet()) {
            String key = entry.getKey();
            String key1 = key.substring(key.lastIndexOf(".") + 1);
            Condition value = entry.getValue();
            m.add(new Mapper(key1, key, value));
        }
        this.mappers = Collections.unmodifiableList(m);
        this.paramsMap = Collections.unmodifiableMap(paramsMap);
    }

    @Override
    public List<Mapper> getMapper() {
        return mappers;
    }

    @Override
    public Map<String, Object> getParamsMapp() {
        return paramsMap;
    }

    public static class Builder<V> {
        private List<Mapper> m = new ArrayList<>();
        private Map<String, Object> p = new HashMap<>();
        private final Class<V> clazz;

        public Builder(final Class<V> clazz) {
            this.clazz = clazz;
        }

        public <S> Builder<V> condition(String path, Condition<S> condition, S param) {
            String key = path.substring(path.lastIndexOf(".") + 1);
            m.add(new Mapper(key, path, condition));
            p.put(key, param);
            return this;
        }

        public <S> Builder<V> condition(String key, String path, Condition<S> condition, S param) {
            m.add(new Mapper(key, path, condition));
            p.put(key, param);
            return this;
        }


        public ConditionMap<V> build() {
            SimpleConditionMap simpleConditionMap = new SimpleConditionMap(clazz, m, p);
            m = new ArrayList<>();
            p = new HashMap<>();
            return simpleConditionMap;
        }
    }
}

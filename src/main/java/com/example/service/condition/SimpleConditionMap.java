package com.example.service.condition;

import java.util.*;

/**
 * Created by THINK on 2016/10/24.
 */
public class SimpleConditionMap extends ConditionMap {
    public final List<Mapper> mappers;
    public final Map<String, Object> paramsMap;

    public SimpleConditionMap(List<Mapper> m, Map<String, Object> p) {
        this.mappers = Collections.unmodifiableList(m == null ? Collections.emptyList() : m);
        this.paramsMap = Collections.unmodifiableMap(p == null ? Collections.emptyMap() : p);
    }

    public SimpleConditionMap(Map<String, Condition> map, Map<String, Object> paramsMap) {
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

    public static class Builder {
        private List<Mapper> m = new ArrayList<>();
        private Map<String, Object> p = new HashMap<>();

        public Builder condition(String path, Condition condition) {
            String key = path.substring(path.lastIndexOf(".") + 1);
            m.add(new Mapper(key, path, condition));
            return this;
        }

        public Builder condition(String key, String path, Condition condition) {
            m.add(new Mapper(key, path, condition));
            return this;
        }

        public Builder param(String key, Object value) {
            p.put(key, value);
            return this;
        }

        public ConditionMap build() {
            SimpleConditionMap simpleConditionMap = new SimpleConditionMap(m, p);
            m = new ArrayList<>();
            p = new HashMap<>();
            return simpleConditionMap;
        }
    }
}

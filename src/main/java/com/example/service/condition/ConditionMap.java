package com.example.service.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.cglib.beans.BeanMap;

import java.util.List;
import java.util.Map;

/**
 * Created by THINK on 2016/10/23.
 */
@EqualsAndHashCode
@Data
public abstract class ConditionMap<T> {
    private int page = 0;
    private int pageSize = 10;
    private boolean distinct = true;
    protected String orderBy = "";

    public int getFirstResult() {
        return page * pageSize;
    }
    public abstract List<Mapper> getMapper();
    public  Map<String,Object> getParamsMapp(){
        BeanMap map = BeanMap.create(this);
        return map;
    }

}

package com.example.service.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by THINK on 2016/10/23.
 */
@EqualsAndHashCode
public class Mapper {
    public final String key;
    public final String path;
    public final Condition Condition;

    public Mapper(String key, String path, Condition condition) {
        this.key = key;
        this.path = path;
        Condition = condition;
    }
}

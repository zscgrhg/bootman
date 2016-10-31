package com.example.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by THINK on 2016/10/30.
 */
@Data
@EqualsAndHashCode
public class Pageable<T> {
    public Pageable(final int page, final int pageSize, int total, final List<T> data) {
        this.page = page;
        this.pageSize = pageSize;
        this.total = total;
        this.data = data;
    }

    int page;
    int pageSize;
    int total;
    List<T> data;
}

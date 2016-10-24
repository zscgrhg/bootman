package com.example.service.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

/**
 * Created by THINK on 2016/3/30.
 */
@EqualsAndHashCode
@Data
public class Pagination<T> {
	private int total;
	private int page;
	private int pageSize;
	private Collection<T> items;

}

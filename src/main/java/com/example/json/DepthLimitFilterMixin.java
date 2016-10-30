package com.example.json;

import com.fasterxml.jackson.annotation.JsonFilter;

import static com.example.json.DepthLimitFilter.ID;

/**
 * Created by THINK on 2016/10/25.
 */
@JsonFilter(ID)
public class DepthLimitFilterMixin {
}

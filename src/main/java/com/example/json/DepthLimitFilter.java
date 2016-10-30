package com.example.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.type.SimpleType;

import java.util.Collections;
import java.util.Map;

/**
 * Created by THINK on 2016/10/24.
 */
public class DepthLimitFilter extends SimpleBeanPropertyFilter {
    public static final String ID = "depthLimitFilter";
    private final int limit_depth;
    private static final int DEFAULT_LIMIT = 3;
    private final Map<String, Integer> depthMap;

    /**
     * 限制JSON输出深度,默认3
     *
     * @param limit 深度限制
     * @param d     深度限制列表,key 为顶层json属性名
     */
    public DepthLimitFilter(int limit, final Map<String, Integer> d) {
        super();
        this.limit_depth = limit;
        this.depthMap = Collections.unmodifiableMap(d);
    }

    public DepthLimitFilter(final Map<String, Integer> d) {
        this(DEFAULT_LIMIT, d);
    }

    public DepthLimitFilter(int limit) {
        this(limit, Collections.emptyMap());
    }

    public DepthLimitFilter() {
        this(DEFAULT_LIMIT, Collections.emptyMap());
    }


    protected boolean include(final PropertyWriter writer, final JsonGenerator jgen) {
        int i = 1;

        JsonStreamContext sc = jgen.getOutputContext();
        String root = writer.getName();
        String root1 = root;
        String rootNotNull = writer.getName();
        if (sc != null) {
            sc = sc.getParent();
        }
        while (sc != null) {

            root1 = root;
            root = sc.getCurrentName();
            if (root1 != null) {
                i++;
            }
            if (root != null) {
                rootNotNull = root;
            }
            sc = sc.getParent();
        }

        int limit = depthMap.getOrDefault(rootNotNull, limit_depth);
        if (limit < i) {
            return false;
        } else if (limit > i) {
            return true;
        }
        JavaType v_type = writer.getType();
        if (v_type instanceof SimpleType) {
            SimpleType v_simpleType = (SimpleType) v_type;
            Class<?> v_rawClass = v_simpleType.getRawClass();
            if (v_rawClass.isPrimitive() || v_rawClass.getName().startsWith("java.lang")) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void serializeAsField(final Object pojo, final JsonGenerator jgen, final SerializerProvider provider,
                                 final PropertyWriter writer) throws Exception {

        if (include(writer, jgen)) {
            writer.serializeAsField(pojo, jgen, provider);
        }else if(jgen.canOmitFields()){
            writer.serializeAsOmittedField(pojo,jgen,provider);
        }
    }
}

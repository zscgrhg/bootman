package com.example.controller;


import com.example.service.condition.Compare;
import com.example.service.condition.ConditionMap;
import com.example.service.condition.Mapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by THINK on 2016/10/23.
 */
@RestController
@RequestMapping("api/manager")
public class UUserController extends BaseController{




    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class UUserQuery extends ConditionMap{

        String username;

        static final List<Mapper> mapers ;

        static {
            List<Mapper> m=new ArrayList<>();
            m.add(new Mapper("username","username",Compare.EQ(String.class)));
            mapers= Collections.unmodifiableList(m);
        }
        @Override
        public List<Mapper> getMapper() {
            return mapers;
        }
    }
}

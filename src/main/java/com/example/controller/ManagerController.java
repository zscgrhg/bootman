package com.example.controller;

import com.example.domain.Manager;
import com.example.service.condition.*;
import com.example.service.inferface.ManagerService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by THINK on 2016/10/23.
 */
@RestController
@RequestMapping("api/manager")
public class ManagerController {
    @Autowired
    ManagerService managerService;

    @RequestMapping("list")
    public Pagination<Manager> list(ManagerQuery managerQuery) {
        Pagination<Manager> managerPagination = managerService.findByConditionMap(managerQuery);
        return managerPagination;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class ManagerQuery extends ConditionMap{

        String name;

        static final List<Mapper> mapers ;

        static {
            List<Mapper> m=new ArrayList<>();
            m.add(new Mapper("name","name",Compare.EQ(String.class)));
            mapers= Collections.unmodifiableList(m);
        }
        @Override
        public List<Mapper> getMapper() {
            return mapers;
        }
    }
}

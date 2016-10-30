package com.example.controller;


import com.example.domain.UUser;
import com.example.service.inferface.UUserService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by THINK on 2016/10/23.
 */
@RestController
@RequestMapping("api/test")
@Validated
public class IndexController extends BaseController {
    @Autowired
    UUserService userService;

    @RequestMapping("trn")
    public RestData trn() {
        User v_currentUser = getCurrentUser();
        UUser v_uUser = userService.testRN(v_currentUser.getUsername());
        RestData v_restData=new RestData();
        v_restData.getData().add(v_uUser);
        return v_restData;
    }

    @RequestMapping("trn1")
    public RestData trn1() {
        User v_currentUser = getCurrentUser();
        UUser v_uUser = userService.testRN1(v_currentUser.getUsername());
        RestData v_restData=new RestData();
        v_restData.getData().add(v_uUser);
        return v_restData;
    }

    @RequestMapping("hello")
    public RestData hello() {
        User v_currentUser = getCurrentUser();
        RestData v_restData=new RestData();
        v_restData.getData().add(v_currentUser);
        return v_restData;
    }
    @RequestMapping("hello1")
    public RestData[] hello1() {
        User v_currentUser = getCurrentUser();
        RestData v_restData=new RestData();
        v_restData.getData().add(v_currentUser);
        return new RestData[]{v_restData};
    }

    @RequestMapping("s")
    @JsonIgnore
    public String s() {
        return "hello world";
    }

    @RequestMapping("testjpql")
    public Object test() {
        DispatcherServlet v_dispatcherServlet;
        Collection<UUser> v_uUsers = userService.testJPQL();
        return v_uUsers;
    }

    @RequestMapping("json")
    public Object testJson() {

        return new Ts();
    }

    @Data
    public static class Ts {



        String name = "a";
        Ts1 ts1 = new Ts1();
    }

    @Data
    public static class Ts1 {
        String n1 = "b";
    }
    @Data
    public static class RestData{
       private int code=200;
        private String ua="aaa";
        private Collection data=new ArrayList();
    }
}

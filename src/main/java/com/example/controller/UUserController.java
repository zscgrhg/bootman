package com.example.controller;


import com.example.domain.UUser;
import com.example.jpa.condition.ConditionList;
import com.example.response.Pageable;
import com.example.service.repos.UUserRopository;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by THINK on 2016/10/23.
 */
@RestController
@RequestMapping("api/manager")
public class UUserController extends BaseController {
    @Autowired
    UUserRopository uUserRopository;

    @RequestMapping("cond")
    public Iterable<UUser> find() {
        Iterable<UUser> v_all = uUserRopository.findAll();
        return v_all;
    }
    @RequestMapping("query")
    public UUser getByUserName() {
        UUser user1 = uUserRopository.loadUUserAndAuthoritiesById("user1");
        return user1;
    }

    @RequestMapping("cond1")
    public Pageable<UUser> find(SearchCondition searchCondition) {
        return uUserRopository.findPageByConditions(searchCondition.build());
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class SearchCondition extends ConditionList.Builder<UUser> {
        String username;
        Boolean enabled;

        @Override
        public void conditions() {
            eq("username", username);
            eq("enabled", enabled);
            maxResult(5);
            orderBy("username");
        }
    }

}

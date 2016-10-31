package com.example.runner;


import com.example.domain.GGroup;
import com.example.domain.RoleConst;
import com.example.domain.UUser;
import com.example.service.repos.GGroupRepository;
import com.example.service.repos.UUserRopository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by THINK on 2016/4/21.
 */
@Component
public class DbInit implements CommandLineRunner {

    @Autowired
    UUserRopository userRopository;
    @Autowired
    GGroupRepository groupRepository;

    @Override
    public void run(String... args) throws Exception {

        try {
            GGroup var_group = new GGroup();
            var_group.setName("user");
            var_group.setDescription("auto_create");
            HashSet<String> a = new HashSet<>();
            a.add(RoleConst.ROLE_USER);
            a.add(RoleConst.ROLE_ADMIN);
            var_group.setAuthorities(a);
            groupRepository.save(var_group);
            for (int i = 0; i < 20; i++) {
                UUser var_user = new UUser();
                var_user.setUsername("user" + i);
                var_user.setPassword("1234");
                var_user.setEnabled(true);
                List<GGroup> var_groups = new ArrayList<>();
                var_groups.add(var_group);
                var_user.setGroups(var_groups);
                userRopository.save(var_user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

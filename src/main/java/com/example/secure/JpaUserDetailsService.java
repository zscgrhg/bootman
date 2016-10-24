package com.example.secure;

import com.example.domain.Authority;
import com.example.domain.Manager;
import com.example.service.condition.*;
import com.example.service.inferface.AuthorityService;
import com.example.service.inferface.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by THINK on 2016/10/23.
 */
@Service
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    ManagerService managerService;
    @Autowired
    AuthorityService authorityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Manager manager = managerService.findById(username);
        List<GrantedAuthority> granted = new ArrayList<>();

        SimpleConditionMap.Builder builder = new SimpleConditionMap.Builder();
        ConditionMap cm = builder
                .condition("mgroups.mgroup.managers.manager.name", Compare.EQ(String.class))
                .param("name", username)
                .build();

        Pagination<Authority> authories = authorityService
                .findByConditionMap(cm);
        Collection<Authority> items = authories.getItems();
        for (Authority item : items) {
            granted.add(new SimpleGrantedAuthority(item.getName()));
        }

        return new User(manager.getName(), manager.getPassword(),
                granted);
    }
}

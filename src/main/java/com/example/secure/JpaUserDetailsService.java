package com.example.secure;


import com.example.domain.GGroup;
import com.example.domain.UUser;
import com.example.service.inferface.UUserService;
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
    UUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UUser x_user = userService.loadUUserAndAuthoritiesById(username);
        Collection<GGroup> x_groups = x_user.getGroups();
        HashSet<String> x_authorities = new HashSet<>();
        for (GGroup x_group : x_groups) {
            x_authorities.addAll(Arrays.asList(x_group.getAuthorities()));
        }
        List<GrantedAuthority> x_granted = new ArrayList<>();
        for (String x_authority : x_authorities) {
            x_granted.add(new SimpleGrantedAuthority(x_authority));
        }
        return new User(x_user.getUsername(), x_user.getPassword()
                , x_user.isEnabled()
                , true, true, true,
                x_granted);
    }
}

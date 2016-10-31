package com.example.secure;


import com.example.domain.GGroup;
import com.example.domain.UUser;
import com.example.service.repos.UUserRopository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Created by THINK on 2016/10/23.
 */
@Service
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    UUserRopository uUserRopository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UUser var_user = uUserRopository.loadUUserAndAuthoritiesById(username);
        Collection<GGroup> var_groups = var_user.getGroups();
        HashSet<String> var_roles = new HashSet<>();
        for (GGroup var_g : var_groups) {
            var_roles.addAll(var_g.getAuthorities());
        }
        List<GrantedAuthority> var_granted = new ArrayList<>();
        for (String var_r : var_roles) {
            var_granted.add(new SimpleGrantedAuthority(var_r));
        }
        return new User(var_user.getUsername(), var_user.getPassword()
                , var_user.isEnabled()
                , true, true, true,
                var_granted);
    }
}

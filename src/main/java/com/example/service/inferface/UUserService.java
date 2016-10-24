package com.example.service.inferface;

import com.example.domain.UUser;

import javax.transaction.Transactional;

/**
 * Created by THINK on 2016/10/24.
 */
@Transactional(rollbackOn = Throwable.class)
public interface UUserService extends CrudSupport<UUser> {
    UUser loadUUserAndAuthoritiesById(String username);
}

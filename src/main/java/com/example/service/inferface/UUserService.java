package com.example.service.inferface;

import com.example.domain.UUser;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collection;

/**
 * Created by THINK on 2016/10/24.
 */
@Transactional(rollbackFor = Throwable.class)
public interface UUserService extends CrudSupport<UUser> {
    UUser loadUUserAndAuthoritiesById(String username);
    Collection<UUser> testJPQL();
    UUser testRN(final String username);
    UUser testRN1(final String username);
}

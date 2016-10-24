package com.example.service.inferface;

import com.example.domain.Authority;

import javax.transaction.Transactional;

/**
 * Created by THINK on 2016/10/24.
 */
@Transactional
public interface AuthorityService extends CrudSupport<Authority>{
}

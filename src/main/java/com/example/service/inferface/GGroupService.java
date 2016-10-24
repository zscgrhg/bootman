package com.example.service.inferface;

import com.example.domain.GGroup;

import javax.transaction.Transactional;

/**
 * Created by THINK on 2016/10/24.
 */
@Transactional(rollbackOn = Throwable.class)
public interface GGroupService extends CrudSupport<GGroup> {
}

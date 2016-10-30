package com.example.service.inferface;

import com.example.domain.GGroup;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by THINK on 2016/10/24.
 */
@Transactional(rollbackFor = Throwable.class)
public interface GGroupService extends CrudSupport<GGroup> {
}

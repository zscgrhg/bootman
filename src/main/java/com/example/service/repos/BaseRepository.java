package com.example.service.repos;

import com.example.jpa.condition.ConditionList;
import com.example.response.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created by THINK on 2016/10/31.
 */


@NoRepositoryBean
@Transactional
public interface BaseRepository<T, ID extends Serializable>
        extends PagingAndSortingRepository<T, ID> {
    @Transactional
    Pageable<T> findPageByConditions(ConditionList<T> conditionList) ;

}



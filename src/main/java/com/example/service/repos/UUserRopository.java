package com.example.service.repos;

import com.example.domain.UUser;
import com.example.fetch.LoadPlans;
import com.example.response.UUserPro;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;

/**
 * Created by THINK on 2016/10/31.
 */
public interface UUserRopository extends BaseRepository<UUser, String> {
    @Lock(LockModeType.READ)
    Iterable<UUser> findAll();

    @Transactional
    @Query("select u from UUser u left join fetch u.groups g left join fetch g.authorities a where u.username = ?1")
    UUser loadUUserAndAuthoritiesByUsername(final String username);

    @Query("select u from UUser u  where u.username = ?1")
    @EntityGraph(value = LoadPlans.USER_AUTHORITIES, type = EntityGraph.EntityGraphType.LOAD)
    UUser loadUUserByGraph(final String username);


    UUserPro findByUsername(final String username);

    //@Query("select u from UUser u  where u.username = ?1")
    @EntityGraph(value = LoadPlans.USER_AUTHORITIES, type = EntityGraph.EntityGraphType.LOAD)
    UUser findOne(final String username);

}

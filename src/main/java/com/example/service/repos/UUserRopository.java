package com.example.service.repos;

import com.example.domain.UUser;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;

/**
 * Created by THINK on 2016/10/31.
 */
public interface UUserRopository extends MyRepository<UUser,String>{
    @Lock(LockModeType.READ)
    Iterable<UUser> findAll();

    @Transactional
    @Query("select u from UUser u left join fetch u.groups where u.username = ?1")
    UUser loadUUserAndAuthoritiesById(final String username);
}

package com.example.fetch;

import com.example.domain.GGroup;
import com.example.domain.UUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;

/**
 * Created by THINK on 2016/10/31.
 */
@Configuration
public class LoadPlans {

    @Bean
    Integer addLoadGraphs(EntityManager em) {
        EntityGraph<UUser> graph = em.createEntityGraph(UUser.class);
        graph.addAttributeNodes("groups");
        Subgraph<GGroup> groups = graph.addSubgraph("groups");
        groups.addAttributeNodes("authorities");
        em.getEntityManagerFactory().addNamedEntityGraph(USER_AUTHORITIES, graph);
        return 1;
    }

    public static final String USER_AUTHORITIES = "USER_AUTHORITIES";
}

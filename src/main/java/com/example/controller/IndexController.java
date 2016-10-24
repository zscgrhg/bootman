package com.example.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by THINK on 2016/10/23.
 */
@RestController
@RequestMapping("api/test")
@Validated
public class IndexController extends BaseController {
    @RequestMapping("hello")
    public User hello() {
        return getCurrentUser();
    }
}

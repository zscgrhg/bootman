package com.example;

import com.example.json.DepthLimitFilter;
import com.example.json.DepthLimitFilterMixin;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by THINK on 2016/10/23.
 */
@Configuration
@Slf4j
public class Config extends WebMvcConfigurerAdapter {
    @Autowired
    MessageSource messageSource;
    @Autowired
    LocalValidatorFactoryBean validator;

    @Override
    public Validator getValidator() {
        return validator;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource);
        return localValidatorFactoryBean;
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(LocalValidatorFactoryBean validator) {
        MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
        methodValidationPostProcessor.setValidator(validator);
        return methodValidationPostProcessor;
    }

    @Bean
    Integer configObm(FilterProvider filterProvider, ObjectMapper objectMapper) {
        objectMapper.addMixIn(Object.class, DepthLimitFilterMixin.class);
        objectMapper.setFilterProvider(filterProvider);
        return 1;
    }

    @Bean
    public FilterProvider filterProvider() {

        FilterProvider filterProvider =
                new SimpleFilterProvider()
                        .addFilter(DepthLimitFilter.ID,
                                new DepthLimitFilter(5));
        return filterProvider;
    }


    @ControllerAdvice
    @Slf4j
    public static class GlobalExceptionHandler {

    }
}



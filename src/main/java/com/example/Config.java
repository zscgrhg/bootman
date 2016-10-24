package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * Created by THINK on 2016/10/23.
 */
@Configuration
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


    @ControllerAdvice
    @Slf4j
    public static class GlobalExceptionHandler {


        @ExceptionHandler(value = {javax.validation.ConstraintViolationException.class})
        @ResponseBody
        public String validateError(HttpServletRequest req,
                                          Exception e, HttpServletResponse response) throws Exception {
            if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
                throw e;
            ConstraintViolationException ve= (ConstraintViolationException) e;
            Set<ConstraintViolation<?>> constraintViolations = ve.getConstraintViolations();
            StringBuilder stringBuilder=new StringBuilder();
            for (ConstraintViolation<?> constraintViolation : constraintViolations) {
                String message = constraintViolation.getMessage();
                stringBuilder.append(message);
            }

            return "error1"+ stringBuilder.toString();
        }
        @ExceptionHandler(value = {Exception.class})
        @ResponseBody
        public String defaultErrorHandler(HttpServletRequest req,
                                                       Exception e, HttpServletResponse response) throws Exception {
            if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
                throw e;

            return "error1";
        }
    }
}



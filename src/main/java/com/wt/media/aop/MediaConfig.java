package com.wt.media.aop;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//@Configuration
public class MediaConfig implements WebMvcConfigurer , BeanFactoryAware {

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        EmptyStringToNullModelAttributeMethodProcessor processor = new EmptyStringToNullModelAttributeMethodProcessor(true);

        RequestMappingHandlerAdapter requestMappingHandlerAdapter = beanFactory.getBean(RequestMappingHandlerAdapter.class);
        List<HandlerMethodArgumentResolver> resolvers = requestMappingHandlerAdapter.getArgumentResolvers();

        List<HandlerMethodArgumentResolver> newResolvers = new ArrayList<>();

        for (HandlerMethodArgumentResolver resolver : resolvers) {
            newResolvers.add(resolver);
        }
        processor.setBeanFactory(beanFactory);
        newResolvers.add(0, processor);
        requestMappingHandlerAdapter.setArgumentResolvers(Collections.unmodifiableList(newResolvers));
    }
}

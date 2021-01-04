package com.hugh.springboot_shiro_thymeleaf.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 自定义工具类，给不能自动注入的类提供spring的Bean对象
 *
 * Created by hugh on 2021/1/1
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    // 将对象以参数的形式回传
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    // 使用bean的名称获取容器中的Bean
    public static Object getBean(String beanName){
        return applicationContext.getBean(beanName);
    }
}

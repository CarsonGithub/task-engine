package com.code.task.engine.provider;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 公共服务提供者
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 */
@Component
public class ServiceProvider implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ServiceProvider.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clz) throws BeansException {
        return applicationContext.getBean(clz);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> tClass) {
        return applicationContext.getBeansOfType(tClass);
    }

    public static ScheduleProvider schedule() {
        return (ScheduleProvider) applicationContext.getBean(ScheduleProvider.Schedule_Provider);
    }

    public static EventProvider event() {
        return (EventProvider) applicationContext.getBean(EventProvider.Event_Provider);
    }
}

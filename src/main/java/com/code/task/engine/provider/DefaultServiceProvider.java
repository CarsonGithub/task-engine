package com.code.task.engine.provider;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 公共服务提供者
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 */
@Service(ServiceProvider.Service_Provider)
public class DefaultServiceProvider implements ServiceProvider, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DefaultServiceProvider.applicationContext = applicationContext;
    }

    public <T> T getBean(Class<T> clz) throws BeansException {
        return applicationContext.getBean(clz);
    }

    public <T> Map<String, T> getBeansOfType(Class<T> tClass) {
        return applicationContext.getBeansOfType(tClass);
    }

    public ScheduleProvider schedule() {
        return (ScheduleProvider) applicationContext.getBean(ScheduleProvider.Schedule_Provider);
    }

    public EventProvider event() {
        return (EventProvider) applicationContext.getBean(EventProvider.Event_Provider);
    }

    public LockProvider<?> lock() {
        return (LockProvider<?>) applicationContext.getBean(LockProvider.Lock_Provider);
    }

}

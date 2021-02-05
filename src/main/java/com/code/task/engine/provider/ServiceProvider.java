package com.code.task.engine.provider;

import java.util.Map;

/**
 * 公共服务提供者
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 */
public interface ServiceProvider extends IProvider {

    String Service_Provider = "serviceProvider";

    @Override
    default String getName() {
        return Service_Provider;
    }

    <T> T getBean(Class<T> clz);

    <T> Map<String, T> getBeansOfType(Class<T> tClass);

    ScheduleProvider schedule();

    EventProvider event();

    <T> LockProvider<T> lock();

}

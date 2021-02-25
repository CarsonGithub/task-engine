package com.code.task.engine.provider;

import java.util.Map;

/**
 * 公共服务提供者
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 */
public interface ServiceProvider<T, U> extends IProvider {

    String Service_Provider = "serviceProvider";

    @Override
    default String getName() {
        return Service_Provider;
    }

    <B> B getBean(String clzName);

    <B> B getBean(Class<B> clz);

    <B> Map<String, B> getBeansOfType(Class<B> tClass);

    MessageProvider<T, U> message();

    ScheduleProvider<T> schedule();

    EventProvider<T, U> event();

    <L> LockProvider<L> lock();

}

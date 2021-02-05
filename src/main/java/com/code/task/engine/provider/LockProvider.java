package com.code.task.engine.provider;

/**
 * 锁服务提供者
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 */
public interface LockProvider<T> extends IProvider {

    String Lock_Provider = "lockProvider";

    @Override
    default String getName() {
        return Lock_Provider;
    }

    T getLock(String key);

    boolean tryLock(T lock);

    void releaseLock(T lock);

}

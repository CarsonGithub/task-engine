package com.code.task.engine.function;

import java.util.function.Supplier;

/**
 * 锁功能
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public interface LockFunction<T> {

    String lockKey(Supplier<T> supplier);

    void executeWithLock(Supplier<T> supplier);

    Object getAndSetLock(Supplier<T> supplier);

    void releaseLock(Supplier<T> supplier);

}

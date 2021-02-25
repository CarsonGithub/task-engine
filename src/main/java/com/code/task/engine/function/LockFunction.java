package com.code.task.engine.function;

import java.util.function.Supplier;

/**
 * 锁功能
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public interface LockFunction<C> {

    String lockKey(Supplier<C> supplier);

    void executeWithLock(Supplier<C> supplier);

    Object getAndSetLock(Supplier<C> supplier);

    void releaseLock(Supplier<C> supplier);

}

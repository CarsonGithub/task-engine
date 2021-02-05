package com.code.task.engine.process;

import com.code.task.engine.behavior.TaskBehavior;
import com.code.task.engine.common.ITask;
import com.code.task.engine.common.TaskContext;
import com.code.task.engine.function.LockFunction;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * 任务驱动行为 增强带锁
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
@SuppressWarnings("unchecked")
public interface TaskLockProcess<T extends ITask, B extends TaskBehavior> extends TaskEventProcess<T, B>, LockFunction<TaskContext> {

    @Override
    default void executeBusiness(TaskContext taskContext) {
        if (StringUtils.isEmpty(lockKey(() -> taskContext))) {
            doExecuteBusiness(taskContext);
        } else {
            executeWithLock(() -> taskContext);
        }
    }

    @Override
    default void executeWithLock(Supplier<TaskContext> supplier) {
        Optional<Object> optional = getAndSetLock(supplier);
        if (optional.isPresent()) {
            TaskContext taskContext = supplier.get();
            try {
                boolean isLock = taskContext.serviceProvider().lock().tryLock(optional.get());
                if (isLock) {
                    doExecuteBusiness(taskContext);
                }
            } catch (Exception e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(String.format("当前锁:%s, 执行业务出错:", optional.get()), e);
            } finally {
                releaseLock(supplier);
            }
        } else {
            throw new RuntimeException("获取不到锁对象:" + lockKey(supplier));
        }
    }

    @Override
    default Optional<Object> getAndSetLock(Supplier<TaskContext> supplier) {
        Object lock = supplier.get().serviceProvider().lock().getLock(lockKey(supplier));
        supplier.get().put(lockKey(supplier), lock);
        return Optional.ofNullable(lock);
    }

    @Override
    default void releaseLock(Supplier<TaskContext> supplier) {
        supplier.get().serviceProvider().lock().releaseLock(supplier.get().get(lockKey(supplier)));
    }

}

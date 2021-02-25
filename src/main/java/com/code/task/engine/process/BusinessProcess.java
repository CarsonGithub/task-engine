package com.code.task.engine.process;

import com.code.task.engine.behavior.TaskBehavior;
import com.code.task.engine.common.ITask;
import com.code.task.engine.common.ITaskContext;
import com.code.task.engine.event.TaskEvent;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * 业务可扩展接口
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public interface BusinessProcess<T, U, K extends ITask<T>, B extends TaskBehavior<T, U>> extends TaskLockProcess<T, U, K, B> {

    /**
     * 获取前置任务事件
     */
    @Override
    default List<TaskEvent<T, U>> getPreTaskEvents(ITaskContext<T, U> taskContext) {
        return Collections.emptyList();
    }

    /**
     * 获取后置任务事件
     */
    @Override
    default List<TaskEvent<T, U>> getPostTaskEvents(ITaskContext<T, U> taskContext) {
        return Collections.emptyList();
    }

    /**
     * 锁key值
     */
    @Override
    default String lockKey(Supplier<ITaskContext<T, U>> supplier) {
        return null;
    }

}

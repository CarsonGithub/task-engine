package com.code.task.engine.process;

import com.code.task.engine.behavior.TaskBehavior;
import com.code.task.engine.common.ITask;
import com.code.task.engine.common.TaskContext;
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
public interface BusinessProcess<T extends ITask, B extends TaskBehavior> extends TaskLockProcess<T, B> {

    /**
     * 获取前置任务事件
     */
    @Override
    default List<TaskEvent> getPreTaskEvents(TaskContext taskContext) {
        return Collections.emptyList();
    }

    /**
     * 获取后置任务事件
     */
    @Override
    default List<TaskEvent> getPostTaskEvents(TaskContext taskContext) {
        return Collections.emptyList();
    }

    /**
     * 锁key值
     */
    @Override
    default String lockKey(Supplier<TaskContext> supplier) {
        return null;
    }
}

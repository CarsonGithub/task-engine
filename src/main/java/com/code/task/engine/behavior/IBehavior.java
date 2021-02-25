package com.code.task.engine.behavior;

import com.code.task.engine.common.ITaskContext;

/**
 * 行为接口
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public interface IBehavior<T, U> {

    /**
     * 所属方言
     */
    String getPhase();

    /**
     * 行为匹配
     */
    boolean support(ITaskContext<T, U> taskContext);

    /**
     * 执行驱动操作
     */
    void doExecute(ITaskContext<T, U> taskContext);

    /**
     * 驱动行为
     */
    default void execute(ITaskContext<T, U> taskContext) {
        if (support(taskContext)) {
            doExecute(taskContext);
        }
    }
}

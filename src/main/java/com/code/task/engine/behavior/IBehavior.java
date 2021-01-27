package com.code.task.engine.behavior;

import com.code.task.engine.common.TaskContext;

/**
 * 行为接口
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public interface IBehavior {

    /**
     * 所属方言
     */
    String getPhase();

    /**
     * 行为匹配
     */
    boolean support(TaskContext taskContext);

    /**
     * 执行驱动操作
     */
    void doExecute(TaskContext taskContext);

    /**
     * 驱动行为
     */
    default void execute(TaskContext taskContext) {
        if (support(taskContext)) {
            doExecute(taskContext);
        }
    }
}

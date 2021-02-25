package com.code.task.engine.behavior;

import com.code.task.engine.common.ITaskContext;
import org.springframework.util.CollectionUtils;

import java.util.Objects;

/**
 * 任务行为
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public interface TaskBehavior<T, U> extends IBehavior<T, U> {

    /**
     * 执行顺序
     */
    default int getOrder() {
        return 0;
    }

    /**
     * 任务匹配
     */
    @Override
    default boolean support(ITaskContext<T, U> taskContext) {
        return !taskContext.getIgnorePhases().contains(getPhase()) &&
                !CollectionUtils.isEmpty(taskContext.getPhases()) &&
                Objects.equals(taskContext.getPhases().get(getOrder()), getPhase());
    }

}

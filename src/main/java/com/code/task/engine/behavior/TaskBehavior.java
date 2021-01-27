package com.code.task.engine.behavior;

import com.code.task.engine.common.TaskContext;
import org.springframework.util.CollectionUtils;

import java.util.Objects;

/**
 * 任务行为
 *
 * @author Carson
 **/
public interface TaskBehavior extends IBehavior {

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
    default boolean support(TaskContext taskContext) {
        return !taskContext.getIgnorePhases().contains(getPhase()) &&
                !CollectionUtils.isEmpty(taskContext.getPhases()) &&
                Objects.equals(taskContext.getPhases().get(getOrder()), getPhase());
    }

}

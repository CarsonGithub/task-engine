package com.code.task.engine.behavior;

import com.code.task.engine.common.TaskContext;

import java.util.Objects;

/**
 * 定时调度行为
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public interface ScheduleBehavior extends TaskBehavior {

    String Init_Schedule = "Init_Schedule";

    String Notify_Schedule = "Notify_Schedule";

    String Suspend_Schedule = "Suspend_Schedule";

    String Remove_Schedule = "Remove_Schedule";

    @Override
    default boolean support(TaskContext taskContext) {
        return Objects.nonNull(taskContext.getSchedule()) &&
                !taskContext.getIgnorePhases().contains(getPhase());
    }
}

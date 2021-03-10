package com.code.task.engine.behavior;

import com.code.task.engine.common.ITaskContext;

import java.util.Objects;

/**
 * 定时调度行为
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public interface ScheduleBehavior<T, U> extends TaskBehavior<T, U> {

    String Init_Schedule = "Init_Schedule";

    String Notify_Schedule = "Notify_Schedule";

    String Suspend_Schedule = "Suspend_Schedule";

    String Remove_Schedule = "Remove_Schedule";

    @Override
    default boolean support(ITaskContext<T, U> taskContext) {
        return Objects.nonNull(taskContext.getSchedule()) &&
                !taskContext.getIgnorePhases().contains(getPhase());
    }

    @Override
    default void doExecute(ITaskContext<T, U> taskContext) {
        switch (getPhase()) {
            case Init_Schedule:
                taskContext.serviceProvider().schedule().init(taskContext.getSchedule());
                return;
            case Notify_Schedule:
                taskContext.serviceProvider().schedule().notify(taskContext.getSchedule());
                return;
            case Suspend_Schedule:
                taskContext.serviceProvider().schedule().suspend(taskContext.getSchedule());
                return;
            case Remove_Schedule:
                taskContext.serviceProvider().schedule().remove(taskContext.getSchedule());
        }
    }

}

package com.code.task.engine.behavior;

import com.code.task.engine.common.ITaskContext;

import java.util.Objects;

/**
 * 消息行为
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public interface MessageBehavior<T, U> extends TaskBehavior<T, U> {

    String Send_Message = "Send_Message";

    String Remove_Message = "Remove_Message";

    @Override
    default boolean support(ITaskContext<T, U> taskContext) {
        return Objects.nonNull(taskContext.getMessage()) &&
                !taskContext.getIgnorePhases().contains(getPhase());
    }

    @Override
    default void doExecute(ITaskContext<T, U> taskContext) {
        switch (getPhase()) {
            case Send_Message:
                taskContext.serviceProvider().<T, U>message().send(taskContext.getMessage());
                return;
            case Remove_Message:
                taskContext.serviceProvider().<T, U>message().remove(taskContext.getMessage());

        }
    }

}

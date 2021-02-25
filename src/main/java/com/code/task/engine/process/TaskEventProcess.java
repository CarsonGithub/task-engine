package com.code.task.engine.process;

import com.code.task.engine.behavior.TaskBehavior;
import com.code.task.engine.common.ITask;
import com.code.task.engine.common.ITaskContext;
import com.code.task.engine.event.TaskEvent;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Consumer;

/**
 * 任务携带事件驱动
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public interface TaskEventProcess<T, U, K extends ITask<T>, B extends TaskBehavior<T, U>> extends TaskActionProcess<T, U, K, B> {

    List<TaskEvent<T, U>> getPreTaskEvents(ITaskContext<T, U> taskContext);

    List<TaskEvent<T, U>> getPostTaskEvents(ITaskContext<T, U> taskContext);

    default <E extends TaskEvent<T, U>> void buildTaskEvent(List<E> events, Consumer<TaskEvent<T, U>> consumer) {
        if (CollectionUtils.isEmpty(events)) {
            return;
        }
        events.forEach(consumer);
    }

    @Override
    default void before(ITaskContext<T, U> taskContext) {
        doBefore(taskContext, context -> buildTaskEvent(getPreTaskEvents(context), taskContext.serviceProvider().event()::publish));
    }

    @Override
    default void after(ITaskContext<T, U> taskContext) {
        doAfter(taskContext, context -> buildTaskEvent(getPostTaskEvents(context), taskContext.serviceProvider().event()::publish));
    }
}

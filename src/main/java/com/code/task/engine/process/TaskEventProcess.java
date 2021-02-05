package com.code.task.engine.process;

import com.code.task.engine.behavior.TaskBehavior;
import com.code.task.engine.common.ITask;
import com.code.task.engine.common.TaskContext;
import com.code.task.engine.event.TaskEvent;
import com.code.task.engine.provider.ServiceProvider;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Consumer;

/**
 * 任务携带事件驱动
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public interface TaskEventProcess<T extends ITask, B extends TaskBehavior> extends TaskActionProcess<T, B> {

    List<TaskEvent> getPreTaskEvents(TaskContext taskContext);

    List<TaskEvent> getPostTaskEvents(TaskContext taskContext);

    default <E extends TaskEvent> void buildTaskEvent(List<E> events, Consumer<TaskEvent> consumer) {
        if (CollectionUtils.isEmpty(events)) {
            return;
        }
        events.forEach(consumer);
    }

    @Override
    default void before(TaskContext taskContext) {
        doBefore(taskContext, context -> buildTaskEvent(getPreTaskEvents(context), ServiceProvider.event()::publish));
    }

    @Override
    default void after(TaskContext taskContext) {
        doAfter(taskContext, context -> buildTaskEvent(getPostTaskEvents(context), ServiceProvider.event()::publish));
    }
}

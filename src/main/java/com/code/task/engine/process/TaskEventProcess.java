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
 **/
public interface TaskEventProcess<T extends ITask, B extends TaskBehavior> extends TaskProcess<T, B> {

    List<TaskEvent> getPreTaskEvents(TaskContext taskContext);

    List<TaskEvent> getPostTaskEvents(TaskContext taskContext);

    default <E extends TaskEvent> void buildTaskEvent(List<E> events, Consumer<TaskEvent> consumer) {
        if (CollectionUtils.isEmpty(events)) {
            return;
        }
        events.forEach(consumer);
    }

    @Override
    default void execute(TaskContext taskContext) {
        before(taskContext, context -> buildTaskEvent(getPreTaskEvents(context), ServiceProvider.event()::publish));
        executeBusiness(taskContext);
        after(taskContext, context -> buildTaskEvent(getPreTaskEvents(context), ServiceProvider.event()::publish));
    }
}

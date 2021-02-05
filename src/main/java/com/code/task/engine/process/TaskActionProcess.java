package com.code.task.engine.process;

import com.code.task.engine.behavior.TaskBehavior;
import com.code.task.engine.common.ITask;
import com.code.task.engine.common.TaskContext;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * 任务驱动行为注入
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public interface TaskActionProcess<T extends ITask, B extends TaskBehavior> extends TaskProcess<T, B> {

    default TaskContext doBuildContext(T task) {
        return new TaskContext(task, serviceProvider());
    }

    default void doExecuteBusiness(TaskContext taskContext) {
        Optional.of(taskContext.serviceProvider().getBeansOfType(getTaskBehaviorClass()))
                .ifPresent(map -> map.values()
                        .stream()
                        .sorted(Comparator.comparingInt(TaskBehavior::getOrder))
                        .forEach(behavior -> behavior.execute(taskContext)));
    }

    default void doBefore(TaskContext taskContext, Consumer<TaskContext> consumer) {
        Optional.ofNullable(consumer).ifPresent(c -> c.accept(taskContext));
    }

    default void doAfter(TaskContext taskContext, Consumer<TaskContext> consumer) {
        Optional.ofNullable(consumer).ifPresent(c -> c.accept(taskContext));
    }

    @Override
    default TaskContext buildContext(T task) {
        return doBuildContext(task);
    }

    @Override
    default void executeBusiness(TaskContext taskContext) {
        doExecuteBusiness(taskContext);
    }

    @Override
    default void before(TaskContext taskContext) {
        doBefore(taskContext, null);
    }

    @Override
    default void after(TaskContext taskContext) {
        doAfter(taskContext, null);
    }

}

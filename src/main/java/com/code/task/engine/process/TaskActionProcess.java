package com.code.task.engine.process;

import com.code.task.engine.behavior.TaskBehavior;
import com.code.task.engine.common.ITask;
import com.code.task.engine.common.ITaskContext;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * 任务驱动行为注入
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public interface TaskActionProcess<T, U, K extends ITask<T>, B extends TaskBehavior<T, U>> extends TaskProcess<T, U, K, B> {

    ITaskContext<T, U> doBuildContext(K task);

    default void doExecuteBusiness(ITaskContext<T, U> taskContext) {
        Optional.of(taskContext.serviceProvider().getBeansOfType(getTaskBehaviorClass()))
                .ifPresent(map -> map.values()
                        .stream()
                        .sorted(Comparator.comparingInt(TaskBehavior::getOrder))
                        .forEach(behavior -> behavior.execute(taskContext)));
    }

    default void doBefore(ITaskContext<T, U> taskContext, Consumer<ITaskContext<T, U>> consumer) {
        Optional.ofNullable(consumer).ifPresent(c -> c.accept(taskContext));
    }

    default void doAfter(ITaskContext<T, U> taskContext, Consumer<ITaskContext<T, U>> consumer) {
        Optional.ofNullable(consumer).ifPresent(c -> c.accept(taskContext));
    }

    @Override
    default ITaskContext<T, U> buildContext(K task) {
        return doBuildContext(task);
    }

    @Override
    default void executeBusiness(ITaskContext<T, U> taskContext) {
        doExecuteBusiness(taskContext);
    }

    @Override
    default void before(ITaskContext<T, U> taskContext) {
        doBefore(taskContext, null);
    }

    @Override
    default void after(ITaskContext<T, U> taskContext) {
        doAfter(taskContext, null);
    }

}

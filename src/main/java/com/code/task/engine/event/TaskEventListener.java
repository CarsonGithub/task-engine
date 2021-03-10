package com.code.task.engine.event;

import com.code.task.engine.behavior.TaskBehavior;
import com.code.task.engine.process.TaskProcess;
import com.code.task.engine.provider.ServiceProvider;

import java.util.Optional;

/**
 * 任务事件监听
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public interface TaskEventListener<T, U> {

    ServiceProvider<T, U> serviceProvider();

    void listen(TaskEvent<T, U> event);

    default void doListener(TaskEvent<T, U> event) {
        if (event.getAsync()) {
            runAsync(event);
        } else {
            runSync(event);
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    default void runSync(TaskEvent<T, U> event) {
        Class<?> clazz = event.getClazz();
        ServiceProvider<T, U> tuServiceProvider;
        try {
            tuServiceProvider = event.getSource().serviceProvider();
        } catch (Exception e) {
            tuServiceProvider = serviceProvider();
        }

        Optional.of(tuServiceProvider.getBean(clazz)).ifPresent(executor -> {
            if (executor instanceof TaskBehavior) {
                ((TaskBehavior<T, U>) executor).execute(event.getSource());
            } else if (executor instanceof TaskProcess) {
                ((TaskProcess) executor).execute(event.getSource());
            }
        });
    }

    default void runAsync(TaskEvent<T, U> event) {
        Class<?> clazz = event.getClazz();
        // todo
    }

}

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
public interface TaskEventListener {

    ServiceProvider getProvider();

    void listen(TaskEvent event);

    default void doListener(TaskEvent event) {
        if (event.getAsync()) {
            runAsync(event);
        } else {
            runSync(event);
        }
    }

    @SuppressWarnings({"rawtypes"})
    default void runSync(TaskEvent event) {
        Class<?> clazz = event.getClazz();
        Optional.of(getProvider().getBean(clazz)).ifPresent(executor -> {
            if (executor instanceof TaskBehavior) {
                ((TaskBehavior) executor).execute(event.getSource());
            } else if (executor instanceof TaskProcess) {
                ((TaskProcess) executor).execute(event.getSource());
            }
        });
    }

    default void runAsync(TaskEvent event) {
        Class<?> clazz = event.getClazz();

    }
}

package com.code.task.engine.event;

import com.code.task.engine.behavior.TaskBehavior;
import com.code.task.engine.process.TaskProcess;
import com.code.task.engine.provider.ServiceProvider;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 任务事件监听
 *
 * @author Carson
 **/
@Component
public class TaskEventListener {

    @EventListener
    public void TaskListener(TaskEvent event) {
        if (event.getAsync()) {
            runAsync(event);
        } else {
            runSync(event);
        }
    }

    @SuppressWarnings({"rawtypes"})
    private void runSync(TaskEvent event) {
        Class<?> clazz = event.getClazz();
        Optional.of(ServiceProvider.getBean(clazz)).ifPresent(executor -> {
            if (executor instanceof TaskBehavior) {
                ((TaskBehavior) executor).execute(event.getSource());
            } else if (executor instanceof TaskProcess) {
                ((TaskProcess) executor).execute(event.getSource());
            }
        });
    }

    private void runAsync(TaskEvent event) {
        Class<?> clazz = event.getClazz();

    }
}

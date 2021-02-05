package com.code.task.engine.provider;

import com.code.task.engine.event.TaskEvent;
import com.code.task.engine.event.TaskEventListener;

/**
 * 事件服务提供者
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 */

public interface EventProvider extends IProvider, TaskEventListener {

    String Event_Provider = "eventProvider";

    @Override
    default String getName() {
        return Event_Provider;
    }

    void publish(TaskEvent event);

    void publishByName(String eventClass);
}

package com.code.task.engine.provider;

import com.code.task.engine.event.TaskEvent;

/**
 * 事件服务提供者
 *
 * @author Carson
 */

public interface EventProvider extends IProvider {

    String Event_Provider = "eventProvider";

    @Override
    default String getName() {
        return Event_Provider;
    }

    void publish(TaskEvent event);

    void publishByName(String eventClass);
}

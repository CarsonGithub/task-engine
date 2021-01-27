package com.code.task.engine.event;

import com.code.task.engine.behavior.IBehavior;
import com.code.task.engine.common.TaskContext;

/**
 * 行为事件
 *
 * @author Carson
 **/
public abstract class BehaviorEvent extends DefaultTaskEvent {

    public <B extends IBehavior> BehaviorEvent(TaskContext taskContext, Class<B> clazz) {
        super(taskContext, clazz, false);
    }

    public <B extends IBehavior> BehaviorEvent(TaskContext taskContext, Class<B> clazz, Boolean isAsync) {
        super(taskContext, clazz, isAsync);
    }

}

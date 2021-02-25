package com.code.task.engine.event;

import com.code.task.engine.behavior.IBehavior;
import com.code.task.engine.common.ITaskContext;

/**
 * 行为事件
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public abstract class BehaviorEvent<T, U> extends DefaultTaskEvent<T, U> {

    public <B extends IBehavior<T, U>> BehaviorEvent(ITaskContext<T, U> taskContext, Class<B> clazz) {
        super(taskContext, clazz, false);
    }

    public <B extends IBehavior<T, U>> BehaviorEvent(ITaskContext<T, U> taskContext, Class<B> clazz, Boolean isAsync) {
        super(taskContext, clazz, isAsync);
    }

}

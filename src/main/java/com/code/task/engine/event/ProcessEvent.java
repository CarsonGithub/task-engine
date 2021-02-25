package com.code.task.engine.event;

import com.code.task.engine.common.ITaskContext;
import com.code.task.engine.process.IProcess;

/**
 * 进程事件
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
@SuppressWarnings("rawtypes")
public abstract class ProcessEvent<T, U> extends DefaultTaskEvent<T, U> {

    public <P extends IProcess> ProcessEvent(ITaskContext<T, U> taskContext, Class<P> clazz) {
        super(taskContext, clazz, false);
    }

    public <P extends IProcess> ProcessEvent(ITaskContext<T, U> taskContext, Class<P> clazz, Boolean isAsync) {
        super(taskContext, clazz, isAsync);
    }

}

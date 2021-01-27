package com.code.task.engine.event;

import com.code.task.engine.common.TaskContext;
import com.code.task.engine.process.IProcess;

/**
 * 进程事件
 *
 * @author Carson
 **/

@SuppressWarnings("rawtypes")
public abstract class ProcessEvent extends DefaultTaskEvent {

    public <P extends IProcess> ProcessEvent(TaskContext taskContext, Class<P> clazz) {
        super(taskContext, clazz, false);
    }

    public <P extends IProcess> ProcessEvent(TaskContext taskContext, Class<P> clazz, Boolean isAsync) {
        super(taskContext, clazz, isAsync);
    }

}

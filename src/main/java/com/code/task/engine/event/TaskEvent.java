package com.code.task.engine.event;

import com.code.task.engine.common.ITaskContext;

/**
 * 任务事件
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public interface TaskEvent<T, U> {

    Class<?> getClazz();

    Boolean getAsync();

    ITaskContext<T, U> getSource();
}

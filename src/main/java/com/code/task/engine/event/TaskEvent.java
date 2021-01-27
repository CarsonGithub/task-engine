package com.code.task.engine.event;

import com.code.task.engine.common.TaskContext;

/**
 * 任务事件
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public interface TaskEvent {

    Class<?> getClazz();

    Boolean getAsync();

    TaskContext getSource();
}

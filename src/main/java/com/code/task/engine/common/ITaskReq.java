package com.code.task.engine.common;

/**
 * 任务请求参数接口
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/

public interface ITaskReq<T> extends ITask<T> {

    String getProcess();

}

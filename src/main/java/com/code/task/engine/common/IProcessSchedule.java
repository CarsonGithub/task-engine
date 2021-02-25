package com.code.task.engine.common;

/**
 * 定时调度携带驱动原子
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public interface IProcessSchedule<T> extends ISchedule<T> {

    String getProcess();

}

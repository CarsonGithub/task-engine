package com.code.task.engine.common;

/**
 * 定时调度原子
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public interface ISchedule<T> {

    T getScheduleId();

    T getTaskId();

    T getParentId();

    String getCron();

}

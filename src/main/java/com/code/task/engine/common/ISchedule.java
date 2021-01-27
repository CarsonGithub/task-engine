package com.code.task.engine.common;

/**
 * 定时调度原子
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public interface ISchedule {

    Long getTaskId();

    Long getParentId();

    Long getCronTime();

}

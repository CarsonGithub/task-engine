package com.code.task.engine.common;

/**
 * 定时调度原子
 *
 * @author Carson
 **/
public interface ISchedule {

    Long getTaskId();

    Long getParentId();

    Long getCronTime();

}

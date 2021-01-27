package com.code.task.engine.common;

/**
 * 定时调度携带事件原子
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public interface IEventSchedule extends ISchedule {

    String getEventClass();

}

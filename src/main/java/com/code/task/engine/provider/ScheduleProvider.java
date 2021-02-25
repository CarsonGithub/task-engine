package com.code.task.engine.provider;

import com.code.task.engine.common.IProcessSchedule;

/**
 * 定时服务提供者
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 */

public interface ScheduleProvider<T> extends IProvider {

    String Schedule_Provider = "scheduleProvider";

    @Override
    default String getName() {
        return "scheduleProvider";
    }

    void init(IProcessSchedule<T> schedule);

    void notify(IProcessSchedule<T> schedule);

    void suspend(IProcessSchedule<T> schedule);

    void remove(IProcessSchedule<T> schedule);
}

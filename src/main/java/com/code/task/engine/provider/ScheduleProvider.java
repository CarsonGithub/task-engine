package com.code.task.engine.provider;

import com.code.task.engine.common.ISchedule;

/**
 * 定时服务提供者
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 */

public interface ScheduleProvider<T> extends IProvider {

    String Key_Schedule_Id = "scheduleId";

    String Schedule_Provider = "scheduleProvider";

    @Override
    default String getName() {
        return "scheduleProvider";
    }

    void init(ISchedule<T> schedule);

    void notify(ISchedule<T> schedule);

    void suspend(ISchedule<T> schedule);

    void remove(ISchedule<T> schedule);
}

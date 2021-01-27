package com.code.task.engine.provider;

import com.code.task.engine.common.IEventSchedule;

/**
 * 定时服务提供者
 *
 * @author Carson
 */

public interface ScheduleProvider extends IProvider {

    String Schedule_Provider = "scheduleProvider";

    @Override
    default String getName() {
        return "scheduleProvider";
    }

    void init(IEventSchedule schedule);

    void notify(IEventSchedule schedule);

    void suspend(IEventSchedule schedule);

    void remove(IEventSchedule schedule);
}

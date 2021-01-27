package com.code.task.engine.behavior.schedule;

import com.code.task.engine.behavior.ScheduleBehavior;
import com.code.task.engine.common.IEventSchedule;
import com.code.task.engine.common.TaskContext;
import com.code.task.engine.provider.ServiceProvider;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

/**
 * 启动定时
 *
 * @author Carson
 **/

@Component
public class NotifyScheduleBehavior implements ScheduleBehavior {

    @Override
    public String getPhase() {
        return Notify_Schedule;
    }

    @SneakyThrows
    @Override
    public void doExecute(TaskContext taskContext) {
        ServiceProvider.schedule().notify((IEventSchedule) taskContext.getSchedule());
    }
}

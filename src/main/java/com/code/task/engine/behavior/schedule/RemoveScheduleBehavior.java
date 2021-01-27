package com.code.task.engine.behavior.schedule;

import com.code.task.engine.behavior.ScheduleBehavior;
import com.code.task.engine.common.IEventSchedule;
import com.code.task.engine.common.TaskContext;
import com.code.task.engine.provider.ServiceProvider;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

/**
 * 挂起定时
 *
 * @author Carson
 **/

@Component
public class RemoveScheduleBehavior implements ScheduleBehavior {

    @Override
    public String getPhase() {
        return Remove_Schedule;
    }

    @SneakyThrows
    @Override
    public void doExecute(TaskContext taskContext) {
        ServiceProvider.schedule().remove((IEventSchedule) taskContext.getSchedule());
    }
}

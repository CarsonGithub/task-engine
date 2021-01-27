package com.code.task.engine.behavior.schedule;

import com.code.task.engine.behavior.ScheduleBehavior;
import com.code.task.engine.common.IEventSchedule;
import com.code.task.engine.common.TaskContext;
import com.code.task.engine.provider.ServiceProvider;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

/**
 * 初始化定时
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/

@Component
public class InitScheduleBehavior implements ScheduleBehavior {

    @Override
    public String getPhase() {
        return Init_Schedule;
    }

    @SneakyThrows
    @Override
    public void doExecute(TaskContext taskContext) {
        ServiceProvider.schedule().init((IEventSchedule) taskContext.getSchedule());
    }
}

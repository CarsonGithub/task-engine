package com.code.task.engine.event;

import com.code.task.engine.common.TaskContext;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Objects;

/**
 * 默认任务事件
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public abstract class DefaultTaskEvent extends ApplicationEvent implements TaskEvent {

    @Getter
    private final Class<?> clazz;

    @Getter
    private final TaskContext source;

    @Setter
    private Boolean async;

    public DefaultTaskEvent(TaskContext object, Class<?> clazz, Boolean isAsync) {
        super(object);
        this.clazz = clazz;
        this.source = object;
        if (Objects.isNull(isAsync)) {
            this.async = false;
        }
    }

    @Override
    public Boolean getAsync() {
        if (Objects.isNull(async)) {
            return false;
        }
        return async;
    }
}

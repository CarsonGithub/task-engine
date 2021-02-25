package com.code.task.engine.event;

import com.code.task.engine.common.ITaskContext;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * 默认任务事件
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public abstract class DefaultTaskEvent<T, U> implements TaskEvent<T, U> {

    @Getter
    private final Class<?> clazz;

    @Getter
    private final ITaskContext<T, U> source;

    @Setter
    private Boolean async;

    public DefaultTaskEvent(ITaskContext<T, U> object, Class<?> clazz, Boolean isAsync) {
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

package com.code.task.engine.common;

import com.code.task.engine.provider.ServiceProvider;

import java.util.List;

/**
 * 任务上下文
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public interface ITaskContext<T, U> {

    ITask<T> getTask();

    void setTask(ITask<T> task);

    ISchedule<T> getSchedule();

    void setSchedule(ISchedule<T> schedule);

    IMessage<T, U> getMessage();

    void setMessage(IMessage<T, U> message);

    boolean isForceStop();

    void setForceStop(boolean forceStop);

    List<String> getPhases();

    List<String> getIgnorePhases();

    ServiceProvider<T, U> serviceProvider();

    void appendIgnores(String phase);

    void put(String key, Object value);

    Object get(String key);

}

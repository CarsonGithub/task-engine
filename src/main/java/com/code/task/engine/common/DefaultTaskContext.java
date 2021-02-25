package com.code.task.engine.common;

import com.code.task.engine.provider.ServiceProvider;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * 任务上下文
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public class DefaultTaskContext implements ITaskContext<Long, Long> {

    @Getter
    private final List<String> phases;

    @Getter
    private final List<String> ignorePhases;

    @Getter
    @Setter
    private ITask<Long> task;

    @Getter
    @Setter
    private ISchedule<Long> schedule;

    @Getter
    @Setter
    private IMessage<Long, Long> message;

    @Getter
    @Setter
    private boolean forceStop;

    private final ServiceProvider<Long, Long> serviceProvider;

    private final Map<String, Object> contextPrams;

    public DefaultTaskContext(ITask<Long> task, ServiceProvider<Long, Long> serviceProvider) {
        this.task = Objects.requireNonNull(task, "任务实体不能为空!");
        this.serviceProvider = Objects.requireNonNull(serviceProvider, "服务提供者不能为空!");
        this.phases = new LinkedList<>();
        this.contextPrams = new LinkedHashMap<>(1);
        this.ignorePhases = new ArrayList<>(0);
    }

    @Override
    public ServiceProvider<Long, Long> serviceProvider() {
        return serviceProvider;
    }

    @Override
    public void appendIgnores(String phase) {
        ignorePhases.add(phase);
    }

    @Override
    public void put(String key, Object value) {
        contextPrams.put(key, value);
    }

    @Override
    public Object get(String key) {
        return contextPrams.get(key);
    }

}

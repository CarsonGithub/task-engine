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
public class TaskContext {

    @Getter
    private final List<String> phases;

    @Getter
    private final List<String> ignorePhases;

    @Getter
    @Setter
    private ITask task;

    @Getter
    @Setter
    private ISchedule schedule;

    @Getter
    @Setter
    private boolean forceStop;

    private final ServiceProvider serviceProvider;

    private final Map<String, Object> contextPrams;

    public TaskContext(ITask task, ServiceProvider serviceProvider) {
        this.task = Objects.requireNonNull(task, "任务实体不能为空!");
        this.phases = new LinkedList<>();
        this.contextPrams = new LinkedHashMap<>(1);
        this.ignorePhases = new ArrayList<>(0);
        this.serviceProvider = serviceProvider;
    }

    public ServiceProvider serviceProvider() {
        return serviceProvider;
    }

    public void appendIgnores(String phase) {
        ignorePhases.add(phase);
    }

    public void put(String key, Object value) {
        contextPrams.put(key, value);
    }

    public Object get(String key) {
        return contextPrams.get(key);
    }
}

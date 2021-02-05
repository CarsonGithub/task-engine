package com.code.task.engine.factory;

import com.code.task.engine.common.ITaskReq;
import com.code.task.engine.process.IProcess;
import com.code.task.engine.provider.ServiceProvider;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 驱动工厂
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
@SuppressWarnings({"unchecked", "rawtypes"})
@Component
@DependsOn("serviceProvider")
public class ProcessFactory {

    public static Map<String, IProcess> bMap;

    static {
        bMap = new ConcurrentHashMap<>(32);
    }

    @PostConstruct
    public void initProcess() {
        ServiceProvider.getBeansOfType(IProcess.class)
                .values()
                .forEach(process -> bMap.put(process.getPhase(), process));
    }

    public static IProcess getBehavior(String name) {
        return bMap.get(name);
    }

    public static void process(ITaskReq taskReq) {
        getBehavior(taskReq.getProcess()).process(() -> taskReq);
    }

}

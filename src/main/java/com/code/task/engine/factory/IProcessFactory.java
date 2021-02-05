package com.code.task.engine.factory;

import com.code.task.engine.common.ITaskReq;
import com.code.task.engine.process.IProcess;
import com.code.task.engine.provider.ServiceProvider;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * 驱动工厂
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
@SuppressWarnings({"unchecked", "rawtypes"})
public interface IProcessFactory {

    String Process_Factory = "processFactory";

    ServiceProvider getProvider();

    Map<String, IProcess> processMap();

    @PostConstruct
    default void initProcess() {
        getProvider().getBeansOfType(IProcess.class).values()
                .forEach(process -> processMap().put(process.getPhase(), process));
    }

    default IProcess getProcess(String name) {
        return processMap().get(name);
    }

    default void doProcess(ITaskReq taskReq) {
        getProcess(taskReq.getProcess()).process(() -> taskReq);
    }

    void process(ITaskReq taskReq);

}

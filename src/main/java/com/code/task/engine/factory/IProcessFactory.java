package com.code.task.engine.factory;

import com.code.task.engine.common.ITaskReq;
import com.code.task.engine.process.IProcess;
import com.code.task.engine.provider.ServiceProvider;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * 驱动工厂
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
@SuppressWarnings({"rawtypes"})
@DependsOn(ServiceProvider.Service_Provider)
public interface IProcessFactory<T, U, K extends ITaskReq<T>> {

    ServiceProvider<T, U> getProvider();

    Map<String, IProcess<K>> processMap();

    @PostConstruct
    default void initProcess() {
        getProvider().getBeansOfType(IProcess.class).values()
                .forEach(process -> {
                    if (Objects.nonNull(process.getPhase())) {
                        processMap().put(process.getPhase(), process);
                    }
                });
    }

    default IProcess<K> getProcess(String name) {
        return processMap().get(name);
    }

    default void doProcess(K taskReq) {
        Optional<IProcess<K>> optional = Optional.ofNullable(getProcess(taskReq.getProcess()));
        if (optional.isPresent()) {
            optional.get().process(() -> taskReq);
        } else {
            throw new RuntimeException("找不到驱动:" + taskReq.getProcess());
        }
    }

    void process(K taskReq);

}

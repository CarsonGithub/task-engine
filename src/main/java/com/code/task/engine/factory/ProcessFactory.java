package com.code.task.engine.factory;

import com.code.task.engine.common.ITaskReq;
import com.code.task.engine.process.IProcess;
import com.code.task.engine.provider.ServiceProvider;
import com.code.task.engine.provider.DefaultServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

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
@DependsOn(DefaultServiceProvider.Service_Provider)
public class ProcessFactory implements IProcessFactory {

    @Autowired
    private ServiceProvider serviceProvider;

    public static Map<String, IProcess> bMap;

    static {
        bMap = new ConcurrentHashMap<>(32);
    }

    @Override
    public ServiceProvider getProvider() {
        return serviceProvider;
    }

    @Override
    public Map<String, IProcess> processMap() {
        return bMap;
    }

    public void process(ITaskReq taskReq) {
        doProcess(taskReq);
    }

}

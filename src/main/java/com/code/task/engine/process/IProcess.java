package com.code.task.engine.process;

import java.util.function.Supplier;

/**
 * 驱动器
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public interface IProcess<T> {

    /**
     * 所属方言
     */
    String getPhase();

    /**
     * 驱动任务
     */
    void process(Supplier<T> supplier);

}

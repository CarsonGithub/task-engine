package com.code.task.engine.common;

/**
 * 消息对象
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public interface IMessage<T, U> {

    T getTaskId();

    String getTitle();

    String getContent();

    U[] getUserIds();

    Integer getMsgType();

}

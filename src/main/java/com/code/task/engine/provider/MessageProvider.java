package com.code.task.engine.provider;

import com.code.task.engine.common.IMessage;

/**
 * 消息服务提供者
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 */
public interface MessageProvider<T, U> extends IProvider {

    String Message_Provider = "messageProvider";

    @Override
    default String getName() {
        return Message_Provider;
    }

    void send(IMessage<T, U> message);

    void remove(IMessage<T, U> message);

}

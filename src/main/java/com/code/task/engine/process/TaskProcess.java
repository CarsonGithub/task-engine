package com.code.task.engine.process;

import com.code.task.engine.behavior.TaskBehavior;
import com.code.task.engine.common.ITask;
import com.code.task.engine.common.ITaskContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 任务驱动
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public interface TaskProcess<T, U, K extends ITask<T>, B extends TaskBehavior<T, U>> extends IProcess<K> {

    @SuppressWarnings("unchecked")
    default Class<B> getTaskBehaviorClass() {
        Class<?> clazz = this.getClass();
        Type[] actualTypeArguments;
        Type type;
        if (Objects.nonNull(clazz.getSuperclass())) {
            ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
            actualTypeArguments = genericSuperclass.getActualTypeArguments();
            type = actualTypeArguments[0];
        } else {
            actualTypeArguments = clazz.getGenericInterfaces();
            ParameterizedType parameterizedType = (ParameterizedType) actualTypeArguments[0];
            type = parameterizedType.getActualTypeArguments()[1];
        }
        try {
            return (Class<B>) type;
        } catch (ClassCastException e) {
            throw new RuntimeException("传入的类型" + type + "非法", e);
        } catch (Exception e) {
            throw new RuntimeException("获取泛型失败!", e);
        }
    }

    default void buildPhases(ITaskContext<T, U> taskContext) {
        Field[] fields = getTaskBehaviorClass().getDeclaredFields();
        taskContext.getPhases().clear();
        taskContext.getPhases().addAll(Arrays.stream(fields).map(Field::getName).collect(Collectors.toList()));
    }

    ITaskContext<T, U> buildContext(K task);

    void executeBusiness(ITaskContext<T, U> taskContext);

    void before(ITaskContext<T, U> taskContext);

    void after(ITaskContext<T, U> taskContext);

    @Transactional(propagation = Propagation.REQUIRED)
    default void execute(ITaskContext<T, U> taskContext) {
        buildPhases(taskContext);
        before(taskContext);
        executeBusiness(taskContext);
        after(taskContext);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    default void process(Supplier<K> supplier) {
        execute(buildContext(supplier.get()));
    }

}

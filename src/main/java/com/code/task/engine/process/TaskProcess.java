package com.code.task.engine.process;

import com.code.task.engine.behavior.TaskBehavior;
import com.code.task.engine.common.ITask;
import com.code.task.engine.common.TaskContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 任务驱动
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public interface TaskProcess<T extends ITask, B extends TaskBehavior> extends IProcess<T> {

    @SuppressWarnings("unchecked")
    default Class<B> getTaskBehaviorClass() {
        Type[] types = this.getClass().getGenericInterfaces();
        ParameterizedType parameterizedType = (ParameterizedType) types[0];
        Type type = parameterizedType.getActualTypeArguments()[1];
        try {
            return (Class<B>) type;
        } catch (ClassCastException e) {
            throw new RuntimeException("传入的类型" + type + "非法", e);
        } catch (Exception e) {
            throw new RuntimeException("获取泛型失败!", e);
        }
    }

    default void buildPhases(TaskContext taskContext) {
        Field[] fields = getTaskBehaviorClass().getDeclaredFields();
        taskContext.getPhases().addAll(Arrays.stream(fields).map(Field::getName).collect(Collectors.toList()));
    }

    TaskContext buildContext(T task);

    void executeBusiness(TaskContext taskContext);

    void before(TaskContext taskContext);

    void after(TaskContext taskContext);


    @Transactional(propagation = Propagation.REQUIRED)
    default void execute(TaskContext taskContext) {
        buildPhases(taskContext);
        before(taskContext);
        executeBusiness(taskContext);
        after(taskContext);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    default void process(Supplier<T> supplier) {
        execute(buildContext(supplier.get()));
    }

}

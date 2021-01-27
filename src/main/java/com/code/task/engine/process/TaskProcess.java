package com.code.task.engine.process;

import com.code.task.engine.behavior.TaskBehavior;
import com.code.task.engine.common.ITask;
import com.code.task.engine.common.TaskContext;
import com.code.task.engine.provider.ServiceProvider;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 任务驱动
 *
 * @author Carson
 * @github https://github.com/CarsonGithub/task-engine.git
 **/
public interface TaskProcess<T extends ITask, B extends TaskBehavior> extends IProcess<T> {

    /**
     * 获取业务行为类型
     */
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

    /**
     * 注入方言
     */
    default void buildPhases(TaskContext taskContext) {
        Field[] fields = getTaskBehaviorClass().getDeclaredFields();
        taskContext.getPhases().addAll(Arrays.stream(fields).map(Field::getName).collect(Collectors.toList()));
    }

    /**
     * 执行上下文初始化
     */
    default TaskContext doBuildContext(T task) {
        return new TaskContext(task);
    }

    /**
     * 生成上下文
     */
    default TaskContext buildContext(T task, Consumer<TaskContext> consumer) {
        TaskContext taskContext = doBuildContext(task);
        consumer.accept(taskContext);
        return taskContext;
    }

    default void executeBusiness(TaskContext taskContext) {
        Optional.of(ServiceProvider.getBeansOfType(getTaskBehaviorClass()))
                .ifPresent(map -> map.values()
                        .stream()
                        .sorted(Comparator.comparingInt(TaskBehavior::getOrder))
                        .forEach(behavior -> behavior.execute(taskContext)));
    }

    default void before(TaskContext taskContext, Consumer<TaskContext> consumer) {
        Optional.ofNullable(consumer).ifPresent(c -> c.accept(taskContext));
    }

    default void after(TaskContext taskContext, Consumer<TaskContext> consumer) {
        Optional.ofNullable(consumer).ifPresent(c -> c.accept(taskContext));
    }


    @Transactional(propagation = Propagation.REQUIRED)
    default void execute(TaskContext taskContext) {
        before(taskContext, null);
        executeBusiness(taskContext);
        after(taskContext, null);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    default void process(Supplier<T> supplier) {
        execute(buildContext(supplier.get(), this::buildPhases));
    }
}

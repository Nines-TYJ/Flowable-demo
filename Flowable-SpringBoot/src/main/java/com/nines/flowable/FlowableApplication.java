package com.nines.flowable;

import com.nines.flowable.service.MyService;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author tanyujie
 * @classname FlowableApplication
 * @description Flowable 流程引擎启动类
 * @date 2022/7/19 14:37
 * @since 1.0
 */
@RequiredArgsConstructor
@SpringBootApplication
public class FlowableApplication {

    private final MyService myService;

    public static void main(String[] args) {
        SpringApplication.run(FlowableApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(final RepositoryService repositoryService,
                                  final RuntimeService runtimeService,
                                  final TaskService taskService) {
        return commandLineRunner -> {
            //long processCount = repositoryService.createProcessDefinitionQuery().count();
            //System.out.println("过程定义的数量: " + processCount);
            //
            //long taskCount = taskService.createTaskQuery().count();
            //System.out.println("任务数: " + taskCount);
            //
            //runtimeService.startProcessInstanceByKey("oneTaskProcess");
            //taskCount = taskService.createTaskQuery().count();
            //System.out.println("流程启动后的任务数: " + taskCount);

            myService.createDemoUser();
        };
    }

}

package com.nines.flowable;

import org.flowable.engine.*;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author tanyujie
 * @classname HolidayRequest
 * @description Flowable 流程引擎
 * @date 2022/7/18 15:30
 * @since 1.0
 */
public class HolidayRequest {

    public static void main(String[] args) {
        // 初始化 Flowable 流程引擎 创建对应表
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:mysql://127.0.0.1:3306/flowable?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai&useAffectedRows=true")
                .setJdbcUsername("root")
                .setJdbcPassword("root")
                .setJdbcDriver("com.mysql.cj.jdbc.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        ProcessEngine processEngine = cfg.buildProcessEngine();
        // 通过传递 XML 文件的位置并调用deploy()方法来实际执行它来创建一个新的Deployment
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService
                .createDeployment()
                .addClasspathResource("holiday-request.bpmn20.xml")
                .deploy();
        // 通过RepositoryService创建一个新的ProcessDefinitionQuery对象 通过 API 查询来验证引擎是否知道流程定义
        ProcessDefinition processDefinition = repositoryService
                .createProcessDefinitionQuery()
                .deploymentId(deployment.getId())
                .singleResult();
        System.out.println("Found process definition : " + processDefinition.getName());

        // 启动流程实例
        Scanner scanner= new Scanner(System.in);

        System.out.println("你是谁?");
        String employee = scanner.nextLine();

        System.out.println("你想请几天假?");
        Integer nrOfHolidays = Integer.valueOf(scanner.nextLine());

        System.out.println("你为什么需要它们?");
        String description = scanner.nextLine();

        // 通过RuntimeService启动一个流程实例
        RuntimeService runtimeService = processEngine.getRuntimeService();
        Map<String, Object> variables = new HashMap<>(3);
        variables.put("employee", employee);
        variables.put("nrOfHolidays", nrOfHolidays);
        variables.put("description", description);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holiday-request", variables);

        // 要获取实际的任务列表，我们通过TaskService创建一个TaskQuery ，并将查询配置为仅返回“managers”组的任务
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("managers").list();
        System.out.println("你有" + tasks.size() + "条任务");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ")" + tasks.get(i).getName());
        }
        // 使用任务标识符，我们现在可以获得特定的流程实例变量并在屏幕上显示实际请求
        System.out.println("你想完成哪项任务?");
        int taskIndex = Integer.parseInt(scanner.nextLine());
        Task task = tasks.get(taskIndex - 1);
        Map<String, Object> processVariables = taskService.getVariables(task.getId());
        System.out.println(processVariables.get("employee") + " 想要 " + processVariables.get("nrOfHolidays") + " 天假期. 你同意吗?");
        // 通过在任务完成时传递带有“已批准”变量的映射（名称很重要，因为稍后在序列流的条件中使用它！）来模拟这一点
        boolean approved = "y".equalsIgnoreCase(scanner.nextLine());
        variables = new HashMap<>(1);
        variables.put("approved", approved);
        taskService.complete(task.getId(), variables);
    }

}

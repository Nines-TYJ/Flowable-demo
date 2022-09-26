package com.nines.flowable.service;

import com.nines.flowable.entity.Person;
import com.nines.flowable.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tanyujie
 * @classname MyService
 * @description flowable 服务
 * @date 2022/7/19 16:20
 * @since 1.0
 */
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
@Service
public class MyService {

    private final RuntimeService runtimeService;

    private final TaskService taskService;

    private final PersonRepository personRepository;

    public void startProcess(String assignee) {
        Person person = personRepository.findByUsername(assignee);
        Map<String, Object> variables = new HashMap<>(1);
        variables.put("person", person);
        runtimeService.startProcessInstanceByKey("oneTaskProcess", variables);
    }

    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }

    public void createDemoUser() {
        if (personRepository.findAll().size() == 0) {
            personRepository.save(new Person("zs", "张三", LocalDateTime.now()));
            personRepository.save(new Person("ls", "李四", LocalDateTime.now()));
        }
    }

}

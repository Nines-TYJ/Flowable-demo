package com.nines.flowable.controller;

import com.nines.flowable.entity.vo.TaskRepresentation;
import com.nines.flowable.service.MyService;
import lombok.RequiredArgsConstructor;
import org.flowable.task.api.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tanyujie
 * @classname MyRestController
 * @description flowable 接口
 * @date 2022/7/19 16:27
 * @since 1.0
 */
@RequiredArgsConstructor
@RestController
public class MyRestController {

    private final MyService myService;

    @PostMapping("/process/{assignee}")
    public void startProcessInstance(@PathVariable("assignee") String assignee) {
        myService.startProcess(assignee);
    }

    @GetMapping("/tasks")
    public List<TaskRepresentation> getTasks(String assignee) {
        List<Task> tasks = myService.getTasks(assignee);
        List<TaskRepresentation> taskRepresentationList = new ArrayList<>();
        tasks.forEach(task -> taskRepresentationList.add(new TaskRepresentation(task.getId(), task.getName())));
        return taskRepresentationList;
    }

}

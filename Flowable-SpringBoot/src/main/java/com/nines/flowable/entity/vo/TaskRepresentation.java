package com.nines.flowable.entity.vo;

import lombok.Data;

/**
 * @author tanyujie
 * @classname TaskRepresentation
 * @description 任务表示法
 * @date 2022/7/19 16:32
 * @since 1.0
 */
@Data
public class TaskRepresentation {

    private String id;

    private String name;

    public TaskRepresentation(String id, String name) {
        this.id = id;
        this.name = name;
    }
}

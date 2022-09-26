package com.nines.flowable.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @author tanyujie
 * @classname Person
 * @description Person实体类
 * @date 2022/7/19 16:55
 * @since 1.0
 */
@Data
@Entity
public class Person {

    @Id
    @GeneratedValue(generator = "myIdGeneratorConfig", strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "myIdGeneratorConfig", strategy = "com.nines.flowable.config.MyIdGeneratorConfig")
    private String id;

    private String username;

    private String nickName;

    private LocalDateTime birthDate;

    public Person() {
    }

    public Person(String username, String nickName, LocalDateTime birthDate) {
        this.username = username;
        this.nickName = nickName;
        this.birthDate = birthDate;
    }
}

package com.nines.flowable.repository;

import com.nines.flowable.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tanyujie
 * @classname PersonRepository
 * @description Person JPA Repository
 * @date 2022/7/19 17:00
 * @since 1.0
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, String> {

    /**
     * 通过username查找数据
     *
     * @param username	用户名
     * @return com.nines.flowable.entity.Person
     * @author tanyujie
     * @date 2022/7/19 17:04
     * @since v1.0
     */
    Person findByUsername(String username);

}

package com.example.demojsp.jpa;

import com.example.demojsp.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public interface StudentJPA extends
        JpaRepository<StudentEntity,Integer>,
        JpaSpecificationExecutor<StudentEntity>, Serializable {

    @Query(value = "select * from student where age > ?1", nativeQuery = true)
    List<StudentEntity> nativeQuery(int age);

    @Transactional
    @Modifying
    @Query(value = "insert into student(name,age,sex,birthday) values (?1,?2,?3,sysdate())", nativeQuery = true)
    void saveTest(String name, Integer age, String sex);
}

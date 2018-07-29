package com.example.demojsp.action;

import com.example.demojsp.entity.StudentEntity;
import com.example.demojsp.jpa.StudentJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentJPA studentJPA;

    @Value("${spring.datasource.url}")
    private String testValue;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<StudentEntity> list(){
        List<StudentEntity> stus = studentJPA.findAll();
        logger.info(stus.get(0).getBirthday().toString());
        return stus;
    }

    @RequestMapping(value = "/age", method = RequestMethod.GET)
    public List<StudentEntity> age(){
        return studentJPA.nativeQuery(20);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(){
        //studentJPA.deleteByNameAndAge("daliu", 25);
        studentJPA.deleteById(3);
        return "删除成功！";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(){
        StudentEntity stu = new StudentEntity();
        stu.setId(4);
        stu.setName("zhangsan");
        stu.setAge(25);
        stu.setSex("female");
        stu.setBirthday(new Date());
        studentJPA.save(stu);
        studentJPA.saveTest("tangyan",33,"female");
        return "添加成功！";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(){
        StudentEntity stu = studentJPA.getOne(5);
        stu.setAge(42);
        stu.setSex("male");
        studentJPA.save(stu);
        return "更新成功！";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return testValue;
    }
}

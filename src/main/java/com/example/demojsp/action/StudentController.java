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

import java.text.SimpleDateFormat;
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String d = sdf.format(stus.get(0).getBirthday());
        logger.info(d);
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
        stu.setId(7);
        stu.setName("James");
        stu.setAge(15);
        stu.setSex("male");
        stu.setBirthday(new java.sql.Date(new Date().getTime()));
        //stu.setBirthday(new Date());
        studentJPA.save(stu);
        //studentJPA.saveTest("tangyan",33,"female");
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

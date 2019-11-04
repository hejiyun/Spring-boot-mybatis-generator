package com.example.demo3.controller;

import com.example.demo3.application.StudentService;
import com.example.demo3.domain.Student;
import com.example.demo3.domain.StudentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// Controller层用来定义交互接口及返回对应的结果.
@Controller
@RequestMapping("/hello")
public class studentTest {
    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/hello1",produces="text/html;charset=UTF-8")
    ResponseEntity<String> hello() {
        return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
    }

    @GetMapping(value = "/hello2",produces="text/html;charset=UTF-8")
    ResponseEntity<Object> hello1(@RequestParam("name") String name, @RequestParam("age") Integer age, @RequestParam("id") Integer id) {
        Student st = new Student();
        st.setId(id);
        st.setName(name);
        st.setAge(age);
        studentService.insertStudent(st);
        System.out.println("添加");
        return ResponseEntity.ok(st.toString());
    }
    @GetMapping(value = "/manual",produces="text/html;charset=UTF-8")
    void manual(HttpServletResponse response, @RequestParam("id") Integer id) throws IOException {
        Student st = studentService.selectById(id);
        StudentExample st1 = new StudentExample();
        st1.createCriteria().andIdEqualTo(id);
        List<Student> sr =  studentService.selectByList(st1);
        response.setHeader("List", sr.toString());
        response.setHeader("Custom-Header", "foo");
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        response.setStatus(200);
        System.out.println(st.toString());
        System.out.println(sr);
        response.getWriter().println(sr.toString());
    }
}

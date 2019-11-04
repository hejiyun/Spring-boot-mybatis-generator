package com.example.demo3.application;

import com.example.demo3.domain.Student;
import com.example.demo3.domain.StudentExample;
import com.example.demo3.interfaces.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// service层用来处理从数据库中提取到的数据, 并提供方法在Controller层调用返回处理后的结果.
@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public int insertStudent(Student st) {return this.studentMapper.insert(st);}

    public Student selectById(Integer id) { return this.studentMapper.selectByPrimaryKey(id); }

    public List<Student> selectByList(StudentExample st) { return this.studentMapper.selectByExample(st);}
}

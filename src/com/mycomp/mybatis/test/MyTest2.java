package com.mycomp.mybatis.test;

import com.mycomp.mybatis.domain.Teacher;
import com.mycomp.mybatis.domain.TeacherExample;
import com.mycomp.mybatis.mapper.TeacherMapper;
import com.mycomp.mybatis.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/*
 * Mybatis Generator代码生成器生成的代码的测试
 */

public class MyTest2 {

    @Test
    public void test() {
        SqlSession sqlSession = MybatisUtils.openSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);

        // QBC的形式进行查询
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria1 = teacherExample.createCriteria();
        criteria1.andTeacherNameLike("%老师%").andTeacherIdGreaterThan(2);

        // or: 需要新的criteria
        TeacherExample.Criteria criteria2 = teacherExample.createCriteria();
        criteria2.andTeacherIdLessThan(10);
        teacherExample.or(criteria2);

        List<Teacher> teachers = mapper.selectByExample(teacherExample);
        for (Teacher teacher : teachers) {
            System.out.println(teacher.getTeacherName());
        }

        sqlSession.close();
    }

}

package com.mycomp.mybatis.test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mycomp.mybatis.domain.Customer;
import com.mycomp.mybatis.mapper.CustomerMapper;
import com.mycomp.mybatis.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/*
 * 分页插件的测试使用
 */

public class MyTest3 {

    @Test
    public void test1() {
        SqlSession sqlSession = MybatisUtils.openSession();
        CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);

        // 设置分页
        Page<Customer> page = PageHelper.startPage(2, 2);

        List<Customer> customers = mapper.getAllCustomers();

        // 分页导航信息, 查询数据之后添加
        PageInfo<Customer> pageInfo = new PageInfo<>(customers, 3);

        System.out.println("分页数据: ");
        for (Customer customer : pageInfo.getList()) {
            System.out.println(customer);
        }

        sqlSession.close();

        // 分页相关属性
        System.out.println("当前页: " + pageInfo.getPageNum());
        System.out.println("每页显示记录数: " + pageInfo.getPageSize());
        System.out.println("总页数: " + pageInfo.getPages());
        System.out.println("总记录数: " + pageInfo.getTotal());
        System.out.println("是否有上一页: " + pageInfo.isHasPreviousPage());
        System.out.println("是否有下一页: " + pageInfo.isHasNextPage());
        System.out.println("导航页码: " + Arrays.toString(pageInfo.getNavigatepageNums()));
    }

}

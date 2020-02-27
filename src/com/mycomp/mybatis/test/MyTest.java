package com.mycomp.mybatis.test;

/*
 * Mybatis缓存:
 * MyBatis中的缓存分为两种: 一级缓存和二级缓存;
 * 一级缓存是sqlSession级别的，二级缓存是mapper级别的(或者叫namespace级别的);
 *
 * 一级缓存:
 * 1. 本地缓存 (默认开启);
 * 2. 在sqlSession没有关闭之前, 再去查询时, 会从缓存当中取出数据, 不会重新发送新的sql;
 *
 * 一级缓存失效:
 * 1. 如果在查询之前, 执行了增\删\改, 缓存就会失效;
 * 2. 手动清空缓存:
 * 3. 如果两次的查询条件不一样, 缓存也会失效;
 * 4. 如果两个查询在不同的sqlsession当中;
 *
 * 二级缓存:
 * 1. 全局作用域缓存, 一个namespace对应一个缓存;
 * 2. 如果会话关闭, 一级缓存的数据会被保存到二级缓存中;
 * 3. 不同namespace查出的数据, 会放到自己对应的缓存中;
 * 3. 现在默认也是打开的;
 *
 * 二级缓存使用步骤:
 * 1. 确保在配置文件当中开启二级缓存;
 * 2. 在对应的mapper中添加cache标签:
 *    (1). eviction: 回收策略
 *         a. LRU: 最近最少使用, 移除最长时间不用的对象
 *         b. FIFO: 先进先出, 按对象进入缓存的顺序移除对象
 *         c. SOFT: 软引入, 移除基本垃圾回收器状态和软引入规则的对象
 *         d. WEAK: 弱引用, 移除基本垃圾回收器状态和弱引用规则的对象
 *    (2). flushInterval: 刷新间隔, 默认不清空
 *    (3). readOnly: 是否只读
 *         a. true: 告诉Mybatis是只读操作, 不去修改数据, Mybatis为了加快获取速度, 会直接将缓存的引用将给用, 不安全, 速度快
 *         b. false: 非只读,有可能修改数据, Mybatis会利用序列化和反序列化复制一份给你, 速度慢些
 *    (4). size: 可以存放多少个元素
 *    (5). type: 可以用来指定自定义的缓存(使用第三方的缓存)
 * 3. POJO需要实现Serializable接口
 *
 * 二级缓存注意事项:
 * 查询的数据都会先放到一级缓存当中, 只有会话关闭, 一级缓存中的数据才会转称到二级缓存中
 *
 * 二级缓存相关属性:
 * 1. cacheEnabled: 只能控制二级缓存的开关
 * 2. select中useCache: 控制的也是二级缓存是否使用
 * 3. 增删改标签中flushCache:
 *    (1). 一级和二级都会被清空
 *    (2). 增删改flushCache默认为true
 *    (3). 查询flushCache默认为false
 * 4. sqlSession.clearCache(): 只清除当前session的一级缓存
 * 5. localCacheScope: 本地缓存作用域, 取值: SESSION, STATEMENT
 *
 * 缓存使用顺序
 * 1. 先到二级缓存当中查找
 * 2. 如果二级缓存中没有,就去找一级缓存
 * 3. 如果一级缓存中也没有就去到数据库当中查询
 */

import com.mycomp.mybatis.domain.Customer;
import com.mycomp.mybatis.mapper.CustomerMapper;
import com.mycomp.mybatis.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class MyTest {

    @Test
    public void test1() {
        SqlSession sqlSession = MybatisUtils.openSession();
        CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);

        System.out.println("第一次查询: ");
        Customer customer1 = mapper.getCustomerById(1);
        System.out.println(customer1);

        // 执行增/删/改, 下一次查询一级缓存会失效
        // System.out.println("执行插入操作");
        // Customer insertCust = new Customer();
        // insertCust.setCust_name("新用户");
        // mapper.insertCustomer(insertCust);

        // 手动清空一级缓存, 也会使其失效
        sqlSession.clearCache();

        // sqlSession级别缓存(一级缓存):
        // 当前sql会话没有关闭, 这时再次查询相同语句, 会从缓存中取出, 而不是去再次执行sql语句
        System.out.println("第二次查询: ");
        Customer customer2 = mapper.getCustomerById(1);
        System.out.println(customer2);

        // 它们其实是同一个对象
        System.out.println("两次查询得到的对象是否是同一个: " + (customer1 == customer2));

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test2() {
        SqlSession sqlSession1 = MybatisUtils.openSession();
        CustomerMapper mapper1 = sqlSession1.getMapper(CustomerMapper.class);
        System.out.println("第一次查询: ");
        Customer customer1 = mapper1.getCustomerById(1);
        System.out.println(customer1);

        // 只有会话关闭时, 才会把一级缓存的数据保存到二级缓存中
        // sqlSession1.close();

        // 第二次查询, 是从二级缓存中取出的(前提是二级换存中有数据, 也就是前一个会话关闭了)
        SqlSession sqlSession2 = MybatisUtils.openSession();
        CustomerMapper mapper2 = sqlSession2.getMapper(CustomerMapper.class);
        System.out.println("第二次查询: ");
        Customer customer2 = mapper2.getCustomerById(1);
        System.out.println(customer2);
        sqlSession2.close();

        System.out.println("两次查询得到的对象是否是同一个: " + (customer1 == customer2));
    }

}

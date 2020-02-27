package com.mycomp.mybatis.mapper;

import com.mycomp.mybatis.domain.Customer;

import java.util.List;

public interface CustomerMapper {

    Customer getCustomerById(Integer id);

    void insertCustomer(Customer customer);

    List<Customer> getAllCustomers();

}

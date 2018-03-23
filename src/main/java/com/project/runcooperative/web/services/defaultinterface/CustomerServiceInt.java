package com.project.runcooperative.web.services.defaultinterface;

import com.project.runcooperative.web.entities.CustomerEntity;

import java.util.List;

public interface CustomerServiceInt {

    public void save(CustomerEntity customer);
    public void delete();

    boolean AuthenticateCustomer(String email,String Password);
    public CustomerEntity findCustomerByEmailAndAccoutNumber(String email, String Accountnumber);

    public List<CustomerEntity> getAllCustomers();

    public CustomerEntity getCustomerById(Long id);

    public CustomerEntity getCustomerByEmail(String email);


}

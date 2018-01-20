package com.project.runcooperative.web.services;

import com.project.runcooperative.web.entities.AccountEntity;
import com.project.runcooperative.web.entities.CustomerEntity;
import com.project.runcooperative.web.repositories.CustomerRepository;
import com.project.runcooperative.web.services.defaultinterface.CustomerServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomerService implements CustomerServiceInt {

    @Autowired
    CustomerRepository customerrepository;

    @Override
    public void save(CustomerEntity customer) {

       customerrepository.save(customer);
    }

    @Override
    public void delete() {

    }

    @Override
    public CustomerEntity findCustomerByEmailAndAccoutNumber(String email, String Accountnumber) {

        CustomerEntity c = new CustomerEntity();

        List<CustomerEntity> customers = getAllCustomers();

        for (CustomerEntity customer: customers) {

            if(customer.getEmailAddress().equalsIgnoreCase(email)){
                for (AccountEntity account: customer.getAccounts()) {

                    if (account.getAccountNumber().equalsIgnoreCase(Accountnumber)){
                        c = customer;
                        return c;
                    }

                }

            }
        }
        return c;
    }

    @Override
    public List<CustomerEntity> getAllCustomers() {

        return (List<CustomerEntity>) customerrepository.findAll();
    }

    @Override
    public CustomerEntity getCustomerById(Long id) {

        return customerrepository.findOne(id);

    }
}

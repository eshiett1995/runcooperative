package com.project.runcooperative.web.services;

import com.project.runcooperative.web.entities.AccountEntity;
import com.project.runcooperative.web.entities.CustomerEntity;
import com.project.runcooperative.web.services.defaultinterface.AccountNumberServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class AccountNumberService implements AccountNumberServiceInt {

    @Autowired
    CustomerService customerService;

    public String generateToken(int length) {

        String numbers = "1234567890";

        //String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";


        String otp;

        Random randomGenerator = new Random();

        Integer indexOne = randomGenerator.nextInt(length);

        Integer indexTwo = randomGenerator.nextInt(length);

        if(indexOne.equals(indexTwo)){

            while(indexOne.equals(indexTwo)){

                indexTwo = randomGenerator.nextInt(length);
                if(!indexOne.equals(indexTwo))
                    break;
            }
        }

        char[] temporaryOTP = new char[length];

        for(Integer indexNumber = 0; indexNumber < length; indexNumber++) {

            if (indexNumber.equals(indexOne) || indexNumber.equals(indexTwo)) {

                temporaryOTP[indexNumber] = numbers.charAt(randomGenerator.nextInt(numbers.length()));

            } else {

                temporaryOTP[indexNumber] = numbers.charAt(randomGenerator.nextInt(numbers.length()));

            }
        }

        otp =  String.valueOf(temporaryOTP);

        return otp ;
    }

    @Override
    public Boolean generateUniqueToken(String token) {

        boolean isUnique = true;

        List<CustomerEntity> customerEntities = new ArrayList<>();

        customerEntities = customerService.getAllCustomers();

            for (CustomerEntity c: customerEntities) {

                for (AccountEntity a:c.getAccounts()) {

                    if(a.getAccountNumber().equals(token)){

                        isUnique = false;
                    }

                }

            }

            return isUnique;

    }

    public String getAccountNumber(){

        String AccountNumber = generateToken(7);

        Boolean isUnique = false;

        while (!isUnique){

            AccountNumber = generateToken(7);

            if(generateUniqueToken(AccountNumber) == false){


            }else{
                isUnique = true;
                return AccountNumber;
            }
        }

        return AccountNumber;
    }
}

package com.sales.market.service.mail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void sendEmmailTest(){
        emailService.sendSimpleMessage("csorialopez11@gmail.com", "test", "message test");
    }
}
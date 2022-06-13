package com.exfi.reporthandler.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MailNotifier {

    //todo

    @Async
    public void sendEmail(List<String> emails) {
        System.out.println("emails1");
    }

}

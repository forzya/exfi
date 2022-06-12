package com.exfi.managergroup;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class EmailGenerator {

    List<String> endEmails = List.of("yandex.ru", "mail.ru", "gmail.com");

    public String generate() {
        String uuid = UUID.randomUUID().toString();
        String end = getRandomEndEmail();
        return uuid + "@" + end;
    }

    private String getRandomEndEmail() {
        Random generator = new Random();
        int randomIndex = generator.nextInt(endEmails.size());
        return endEmails.get(randomIndex);
    }

}

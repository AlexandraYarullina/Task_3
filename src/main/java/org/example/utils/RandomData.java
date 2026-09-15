package org.example.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomData {
    public static String getRandomEmail() {
        String email = RandomStringUtils.randomAlphabetic(8) + "@yandex.ru";
        return email;
    }

    public static String getRandomPassword() {
        String password = RandomStringUtils.randomAlphanumeric(8);
        return password;
    }

    public static String getRandomIncorrectPassword() {
        String incorrectPassword = RandomStringUtils.randomAlphanumeric(5);
        return incorrectPassword;
    }

    public static String getRandomName() {
        String name = RandomStringUtils.randomAlphabetic(10);
        return name;
    }
}

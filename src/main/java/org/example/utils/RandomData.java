package org.example.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomData {
    public static String EMAIL = RandomStringUtils.randomAlphabetic(8) + "@yandex.ru";
    public static String PASSWORD = RandomStringUtils.randomAlphabetic(8);
    public static String INCORRECT_PASSWORD = RandomStringUtils.randomAlphabetic(5);
    public static String NAME = RandomStringUtils.randomAlphabetic(10);
}

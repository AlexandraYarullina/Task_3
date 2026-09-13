package org.example;

import org.example.pageObject.MainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest extends BaseTest{
    MainPage mainPage;

    @BeforeEach
    public void SetUp() {
        mainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Проверка перехода в раздел Начинки")
    public void checkActiveFillingInConstructor() {
        // открытие главной страницы
        mainPage.openMainPage();
        // ожидание загрузки главной страницы
        mainPage.waitForLoad();
        // переход в раздел Начинки
        mainPage.checkSectionFillingClick();
        // проверка, что раздел Начинки активна
        assertTrue(mainPage.sectionFillingIsDisplayed(), "Раздел 'Начинки' не активен");
    }

    @Test
    @DisplayName("Проверка перехода в раздел 'Соусы'")
    public void checkActiveSauceInConstructor(){
        // открытие главной страницы
        mainPage.openMainPage();
        // ожидание загрузки главной страницы
        mainPage.waitForLoad();
        // переход в раздел Соусы
        mainPage.checkSectionSauceClick();
        // проверка, что раздел Соусы активна
        assertTrue(mainPage.sectionSauceIsDisplayed(), "Раздел 'Соусы' не активен");
    }

    @Test
    @DisplayName("Проверка перехода в раздел 'Булки'")
    public void checkActiveBunInConstructor(){
        // открытие главной страницы
        mainPage.openMainPage();
        // ожидание загрузки главной страницы
        mainPage.waitForLoad();
        // переход в раздел Соусы
        mainPage.checkSectionSauceClick();
        // переход в раздел Булки
        mainPage.checkSectionBunClick();
        // проверка, что раздел Булки активна
        assertTrue(mainPage.sectionBunIsDisplayed(), "Раздел 'Булки' не активен");
    }
}

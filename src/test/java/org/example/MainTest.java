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
        mainPage.openMainPage();
        mainPage.waitForLoad();
        mainPage.checkSectionFillingClick();
        assertTrue(mainPage.sectionFillingIsDisplayed(), "Раздел 'Начинки' не активен");
    }

    @Test
    @DisplayName("Проверка перехода в раздел 'Соусы'")
    public void checkActiveSauceInConstructor(){
        mainPage.openMainPage();
        mainPage.waitForLoad();
        mainPage.checkSectionSauceClick();
        assertTrue(mainPage.sectionSauceIsDisplayed(), "Раздел 'Соусы' не активен");
    }

    @Test
    @DisplayName("Проверка перехода в раздел 'Булки'")
    public void checkActiveBunInConstructor(){
        mainPage.openMainPage();
        mainPage.waitForLoad();
        mainPage.checkSectionSauceClick();
        mainPage.checkSectionBunClick();
        assertTrue(mainPage.sectionBunIsDisplayed(), "Раздел 'Булки' не активен");
    }
}

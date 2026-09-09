package org.example;

import org.example.pageObject.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {
    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    RecoveryPasswordPage recoveryPasswordPage;


    @BeforeEach
    public void SetUp() {
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        registrationPage = new RegistrationPage(driver);
        recoveryPasswordPage = new RecoveryPasswordPage(driver);
    }

    @Test
    @DisplayName("Авторизация по кнопке «Войти в аккаунт» на главной странице")
    public void checkLoginMainPage() {
        // открытие главной страницы
        mainPage.openMainPage();
        // ожидание загрузки главной страницы
        mainPage.waitForLoad();
        // нажатие на кнопку 'Войти в аккаунт'
        mainPage.loginMainPageButtonClick();
        // ожидание загрузки страницы авторизации
        loginPage.waitForLoad();
        // заполняем поле email
        loginPage.setEmailField(EMAIL);
        // заполняем поле пароль
        loginPage.setPasswordField(PASSWORD);
        // нажатие на кнопку 'Войти'
        loginPage.loginLoginPageButtonClick();
        // ожидание загрузки главной страницы
        mainPage.waitForLoad();
        // проверка видимости надписи раздела 'Булки'
        assertTrue(mainPage.sectionBunIsDisplayed(), "Авторизация прошла не успешно");
    }

    @Test
    @DisplayName("Авторизация через кнопку «Личный кабинет»")
    public void checkLoginUserProfilePage() {
        // открытие главной страницы
        mainPage.openMainPage();
        // ожидание загрузки главной страницы
        mainPage.waitForLoad();
        // нажатие на кнопку 'Личный кабинет'
        mainPage.personalAccountButtonClick();
        // ожидание загрузки страницы авторизации
        loginPage.waitForLoad();
        // заполняем поле email
        loginPage.setEmailField(EMAIL);
        // заполняем поле пароль
        loginPage.setPasswordField(PASSWORD);
        // нажатие на кнопку 'Войти'
        loginPage.loginLoginPageButtonClick();
        // ожидание загрузки главной страницы
        mainPage.waitForLoad();
        // проверка видимости надписи раздела 'Булки'
        assertTrue(mainPage.sectionBunIsDisplayed(), "Авторизация прошла не успешно");
    }

    @Test
    @DisplayName("Авторизация через кнопку на странице регистрации")
    public void checkLoginRegistrationPage() {
        // открытие главной страницы
        registrationPage.openRegistrationPage();
        // ожидание загрузки страницы регистрации
        registrationPage.waitForLoad();
        // нажатие на кнопку 'Личный кабинет'
        registrationPage.loginRegistrationPageButtonClick();
        // ожидание загрузки страницы авторизации
        loginPage.waitForLoad();
        // заполняем поле email
        loginPage.setEmailField(EMAIL);
        // заполняем поле пароль
        loginPage.setPasswordField(PASSWORD);
        // нажатие на кнопку 'Войти'
        loginPage.loginLoginPageButtonClick();
        // ожидание загрузки главной страницы
        mainPage.waitForLoad();
        // проверка видимости надписи раздела 'Булки'
        assertTrue(mainPage.sectionBunIsDisplayed(), "Авторизация прошла не успешно");
    }
    @Test
    @DisplayName("Авторизация через кнопку в форме восстановления пароля")
    public void checkLoginRecoveryPasswordPage() {
        // открытие главной страницы
        recoveryPasswordPage.openRecoveryPasswordPage();
        // ожидание загрузки страницы восстановление пароля
        recoveryPasswordPage.waitForLoad();
        // нажатие на кнопку 'Войти'
        recoveryPasswordPage.loginRecoveryPasswordPageButtonClick();
        // ожидание загрузки страницы авторизации
        loginPage.waitForLoad();
        // заполняем поле email
        loginPage.setEmailField(EMAIL);
        // заполняем поле пароль
        loginPage.setPasswordField(PASSWORD);
        // нажатие на кнопку 'Войти'
        loginPage.loginLoginPageButtonClick();
        // ожидание загрузки главной страницы
        mainPage.waitForLoad();
        // проверка видимости надписи раздела 'Булки'
        assertTrue(mainPage.sectionBunIsDisplayed(), "Авторизация прошла не успешно");
    }
}

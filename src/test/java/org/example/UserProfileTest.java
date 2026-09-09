package org.example;

import org.example.pageObject.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserProfileTest extends BaseTest {
    MainPage mainPage;
    UserProfilePage userProfilePage;
    RegistrationPage registrationPage;
    LoginPage loginPage;

    @BeforeEach
    public void SetUp() {
        mainPage = new MainPage(driver);
        userProfilePage = new UserProfilePage(driver);
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);

        //Авторизация пользователя и переход в личный кабинет
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
        // нажатие на кнопку 'Личный кабинет'
        mainPage.personalAccountButtonClick();
        // ожидание загрузки страницы "Личный кабинет"
        userProfilePage.waitForLoad();
    }

    @Test
    @DisplayName("Проверка перехода по клику на «Личный кабинет»")
    public void checkTransitionClickToPersonalAccount() {
        // проверка видимости кнопки 'Профиль'
        assertTrue(userProfilePage.isProfileButtonVisible());
    }

    @Test
    @DisplayName("Проверка перехода по клику на «Конструктор»")
    public void checkTransitionClickToConstructor() {
        // нажатие на кнопку «Конструктор»
        userProfilePage.constructorButtonClick();
        // ожидание загрузки главной страницы
        mainPage.waitForLoad();
        // проверка видимости надписи раздела 'Булки'
        assertTrue(mainPage.sectionBunIsDisplayed(), "Авторизация прошла не успешно");
    }

    @Test
    @DisplayName("Проверка перехода по клику на логотип Stellar Burgers")
    public void checkTransitionClickToLogo() {
        // нажатие на кнопку «Конструктор»
        userProfilePage.logoButtonClick();
        // ожидание загрузки главной страницы
        mainPage.waitForLoad();
        // проверка видимости надписи раздела 'Булки'
        assertTrue(mainPage.sectionBunIsDisplayed(), "Авторизация прошла не успешно");
    }

    @Test
    @DisplayName("Проверка выход по кнопке «Выйти» в личном кабинете")
    public void checkLogoutButtonInPersonalAccount() {
        // нажатие на кнопку «Конструктор»
        userProfilePage.logoutButtonClick();
        // ожидание загрузки страницы авторизации
        loginPage.waitForLoad();
        // проверка видимости надписи 'Войти'
        assertTrue(loginPage.registrationLabelIsDisplayed(), "Переход на страницу авторизации не произошёл");
    }
}

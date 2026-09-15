package org.example;

import org.example.apirequest.UserRequest;
import org.example.pojo.DataUser;
import org.example.pageObject.*;
import org.example.utils.RandomData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.utils.Constants.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {
    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    RecoveryPasswordPage recoveryPasswordPage;
    UserRequest userRequest = new UserRequest();
    DataUser dataUser = new DataUser(RandomData.getRandomEmail(), RandomData.getRandomPassword(),RandomData.getRandomName());
    private String accessToken;

        @BeforeEach
    public void setUp() {
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        registrationPage = new RegistrationPage(driver);
        recoveryPasswordPage = new RecoveryPasswordPage(driver);
        userRequest.setUp();
        userRequest.setDataUser(dataUser);
        // создаём пользователя через API
        userRequest.createUser();
        // проверяем, что пользователь создан
        userRequest.loginUser()
                    .then().assertThat().body("success", equalTo(true))
                    .statusCode(OK_STATUS_COD);
        accessToken = userRequest.loginUser().then().extract().path("accessToken");
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
        loginPage.setEmailField(dataUser.getEmail());
        // заполняем поле пароль
        loginPage.setPasswordField(dataUser.getPassword());
        // нажатие на кнопку 'Войти'
        loginPage.loginLoginPageButtonClick();
        // ожидание загрузки главной страницы
        mainPage.waitForLoad();
        // проверка видимости кнопки 'Оформить заказ'
        assertTrue(mainPage.orderButtonIsDisplayed(), "Авторизация прошла не успешно");
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
        loginPage.setEmailField(dataUser.getEmail());
        // заполняем поле пароль
        loginPage.setPasswordField(dataUser.getPassword());
        // нажатие на кнопку 'Войти'
        loginPage.loginLoginPageButtonClick();
        // ожидание загрузки главной страницы
        mainPage.waitForLoad();
        // проверка видимости кнопки 'Оформить заказ'
        assertTrue(mainPage.orderButtonIsDisplayed(), "Авторизация прошла не успешно");
    }

    @Test
    @DisplayName("Авторизация через кнопку на странице регистрации")
    public void checkLoginRegistrationPage() {
        // открытие страницы регистрации
        registrationPage.openRegistrationPage();
        //ожидание загрузки страницы регистрации
        registrationPage.waitForLoad();
        // нажатие на кнопку 'Войти'
        registrationPage.loginRegistrationPageButtonClick();
        // ожидание загрузки страницы авторизации
        loginPage.waitForLoad();
        // заполняем поле email
        loginPage.setEmailField(dataUser.getEmail());
        // заполняем поле пароль
        loginPage.setPasswordField(dataUser.getPassword());
        // нажатие на кнопку 'Войти'
        loginPage.loginLoginPageButtonClick();
        // ожидание загрузки главной страницы
        mainPage.waitForLoad();
        // проверка видимости кнопки 'Оформить заказ'
        assertTrue(mainPage.orderButtonIsDisplayed(), "Авторизация прошла не успешно");
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
        loginPage.setEmailField(dataUser.getEmail());
        // заполняем поле пароль
        loginPage.setPasswordField(dataUser.getPassword());
        // нажатие на кнопку 'Войти'
        loginPage.loginLoginPageButtonClick();
        // ожидание загрузки главной страницы
        mainPage.waitForLoad();
        // проверка видимости кнопки 'Оформить заказ'
        assertTrue(mainPage.orderButtonIsDisplayed(), "Авторизация прошла не успешно");
    }

    @AfterEach
    @DisplayName("Удаление пользователя по токену")
    public void deleteUser() {
        if (accessToken!=null) {
            userRequest.deleteUser(accessToken)
                    .then().assertThat().statusCode(ACCEPTED_STATUS_COD);
        }
    }
}

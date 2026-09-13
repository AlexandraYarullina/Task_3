package org.example;

import org.example.APIRequest.UserRequest;
import org.example.POJO.DataUser;
import org.example.pageObject.LoginPage;
import org.example.pageObject.MainPage;
import org.example.pageObject.RegistrationPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.utils.Constants.*;
import static org.example.utils.RandomData.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest extends BaseTest {
    RegistrationPage registrationPage;
    LoginPage loginPage;
    MainPage mainPage;
    UserRequest userRequest = new UserRequest();
    DataUser dataUser = new DataUser(EMAIL, PASSWORD, NAME);
    private String accessToken;


    @BeforeEach
    public void SetUp(){
        registrationPage = new RegistrationPage(driver);
        loginPage=new LoginPage(driver);
        mainPage=new MainPage(driver);
        userRequest.setUp();
        userRequest.setDataUser(dataUser);
    }

    @Test
    @DisplayName("Проверка успешной регистрации")
    public void checkRegistrationContent() {
        // открытие страницы регистрации
        registrationPage.openRegistrationPage();
        // заполнение данными для регистрации
        registrationPage.orderPersonalInfo(NAME,EMAIL,PASSWORD);
        // нажатие на кнопку Регистрация
        registrationPage.RegistrationButtonClick();
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

        userRequest.loginUser()
                .then().assertThat().body("success", equalTo(true))
                .statusCode(OK_STATUS_COD);
        accessToken = userRequest.loginUser().then().extract().path("accessToken");
    }

    @Test
    @DisplayName("Проверка не успешной регистрации, ввод некорректного пароля")
    public void checkErrorWrongPassword() {
        // открытие страницы регистрации
        registrationPage.openRegistrationPage();
        // заполнение данными для регистрации
        registrationPage.orderPersonalInfo(NAME,EMAIL,INCORRECT_PASSWORD);
        // нажатие на кнопку Регистрация
        registrationPage.RegistrationButtonClick();
        // проверяем что появилась ошибка для некорректном пароля
        assertTrue(registrationPage.isIncorrectPasswordLabelVisible(), "Ошибка не появилась при вводе неккоректного пароля");
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

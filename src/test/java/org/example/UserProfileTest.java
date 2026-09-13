package org.example;

import org.example.APIRequest.UserRequest;
import org.example.POJO.DataUser;
import org.example.pageObject.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.utils.Constants.*;
import static org.example.utils.RandomData.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserProfileTest extends BaseTest {
    MainPage mainPage;
    UserProfilePage userProfilePage;
    RegistrationPage registrationPage;
    LoginPage loginPage;
    UserRequest userRequest = new UserRequest();
    DataUser dataUser = new DataUser(EMAIL, PASSWORD, NAME);
    private String accessToken;

    @BeforeEach
    public void SetUp() {
        mainPage = new MainPage(driver);
        userProfilePage = new UserProfilePage(driver);
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        userRequest.setUp();
        userRequest.setDataUser(dataUser);
        //Регистрация и авторизация пользователя
        // создаём пользователя через API
        userRequest.createUser();
        userRequest.loginUser()
                .then().assertThat().body("success", equalTo(true))
                .statusCode(OK_STATUS_COD);

        // открытие страницы авторизации
        loginPage.openLoginPage();
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
        accessToken = userRequest.loginUser().then().extract().path("accessToken");
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
    @AfterEach
    @DisplayName("Удаление пользователя по токену")
    public void deleteUser() {
        if (accessToken!=null) {
            userRequest.deleteUser(accessToken)
                    .then().statusCode(ACCEPTED_STATUS_COD);
        }
    }
}

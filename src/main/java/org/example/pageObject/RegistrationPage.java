package org.example.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.utils.Constants.URL_REGISTRATION_PAGE;

// класс для заполнения формы регистрации
public class RegistrationPage{
    private WebDriver driver;

    // надписть Регистрация
    private By registrationLabel = By.xpath("//h2[text()='Регистрация']");
    // локатор для поля ввода имени
    private By nameField = By.xpath("//label[text()='Имя']/../input");
    // локатор для поля ввода email
    private By emailField = By.xpath("//label[text()='Email']/../input");
    // локатор для поля ввода пароля
    private By passwordField = By.xpath("//label[text()='Пароль']/../input");
    // локатор для поля кнопки зарегистрироваться
    private By registrationButton = By.xpath("//button[text()='Зарегистрироваться']");
    // локатор для кнопки Войти
    private By loginRegistrationPageButton=By.xpath("//a[@class='Auth_link__1fOlj' and text()='Войти']");
    // локатор для сообщения о неккоректном пароле
    private By incorrectPasswordLabel = By.xpath("//p[@class='input__error text_type_main-default' and text()='Некорректный пароль']");

    // конструктор класса
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие страницы регистрации")
    public void openRegistrationPage(){
        driver.get(URL_REGISTRATION_PAGE);
    }

    @Step("Ожидание загрузки страницы Регистрации")
    public void waitForLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(driver.findElement(registrationLabel)));
    }

    @Step("Ввод имени")
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Ввод email")
    public void setEmail(String email){
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void setPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажатие на кнопку зарегестрироваться")
    public void RegistrationButtonClick(){
        driver.findElement(registrationButton).click();
    }

    @Step("Ввод имени, email и фамилии")
    public void orderPersonalInfo(String name, String email, String password){
        setName(name);
        setEmail(email);
        setPassword(password);
    }

    @Step("Нажатие на кнопку Войти")
    public void loginRegistrationPageButtonClick(){
        driver.findElement(loginRegistrationPageButton).click();
    }

    @Step("Проверка видимости надписи 'Неправильный пароль'")
    public boolean isIncorrectPasswordLabelVisible() {
        return driver.findElement(incorrectPasswordLabel).isDisplayed();
    }
}

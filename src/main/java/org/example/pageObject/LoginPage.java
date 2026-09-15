package org.example.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.utils.Constants.URL_LOGIN_PAGE;

// класс для авторизации
public class LoginPage {
  private  WebDriver driver;

    // надписть Вход
    private By registrationLabel = By.xpath("//h2[text()='Вход']");
    // локатор для поля ввода email
    private By emailField= By.xpath("//label[text()='Email']/../input");
    // локатор для поля ввода пароля
    private By passwordField = By.xpath("//label[text()='Пароль']/../input");
    // локатор для поля кнопки Войти
    private By loginLoginPageButton= By.xpath("//button[text()='Войти']");
    // локатор для поля Восстановить пароль
    private By recoveryPasswordButton= By.xpath("//a[text()='Восстановить пароль']");

    // конструктор класса
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

  @Step("Открытие страницы авторизации")    //
    public void openLoginPage(){
        driver.get(URL_LOGIN_PAGE);
    }

  @Step("Ввод email")
    public void setEmailField(String email){
        driver.findElement(emailField).sendKeys(email);
    }

  @Step("Ввода пароля")
    public void setPasswordField(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

  @Step("Нажатие на кнопку Войти")
    public void loginLoginPageButtonClick(){
        driver.findElement(loginLoginPageButton).click();
    }

  @Step("Ожидание загрузки страницы авторизации")
  public void waitForLoad() {
    new WebDriverWait(driver, Duration.ofSeconds(3))
            .until(ExpectedConditions.visibilityOfElementLocated(registrationLabel));
  }

   @Step("Проверка видимости надписи 'Войти'")
  public boolean registrationLabelIsDisplayed() {
    return driver.findElement(registrationLabel).isDisplayed();
  }
}

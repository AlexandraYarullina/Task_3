package org.example.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.utils.Constants.URL_RECOVERY_PASSWORD_PAGE;

    // класс Восстановление пароля
public class RecoveryPasswordPage {
    private WebDriver driver;

    // надписть Восстановление пароля
    private By textRecoveryPassword = By.xpath("//h2[text()='Восстановление пароля']");
    // Кнопка Войти
    private By loginRecoveryPasswordPageButton = By.xpath(".//a[text()='Войти']");

    // конструктор класса
    public RecoveryPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие страницы Восстановление пароля")
    public void openRecoveryPasswordPage(){
        driver.get(URL_RECOVERY_PASSWORD_PAGE);
    }

    @Step("Ожидание загрузки страницы Восстановление пароля")
    public void waitForLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(driver.findElement(textRecoveryPassword)));
    }

    @Step("Нажатие на кнопку Войти")
    public void loginRecoveryPasswordPageButtonClick () {
        driver.findElement(loginRecoveryPasswordPageButton).click();
    }
}

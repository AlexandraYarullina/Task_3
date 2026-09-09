package org.example.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.utils.Constants.URL_REGISTRATION_PAGE;

// класс Личный Кабинет
public class UserProfilePage {
    private WebDriver driver;

    // локатор для нопки Конструктор
    private By constructorButton = By.xpath("//p[text()='Конструктор']");
    //локатор для Логотипа Stellar Burgers
    private By logoButton = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']/a");
    //локатор для копки Профиль
    private By profileButton = By.xpath("//a[@href='/account/profile']");
    // локатор для кнопки Выход
    private By logoutButton = By.xpath("//button[@class='Account_button__14Yp3 text text_type_main-medium text_color_inactive' and text()='Выход']");

    // конструктор класса
    public UserProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие личного кабинета")
    public void openUserProfilePage(){
        driver.get(URL_REGISTRATION_PAGE);
    }

    @Step("Ожидание загрузки страницы Личный Кабинет")
    public void waitForLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(profileButton));
    }

    @Step("Нажатие на кнопку Конструктор")
    public void constructorButtonClick(){
        driver.findElement(constructorButton).click();
    }

    @Step("Нажатие на кнопку Логотип Stellar Burgers")
    public void logoButtonClick(){
        driver.findElement(logoButton).click();
    }

    @Step("Нажатие на кнопку Выход")
    public void logoutButtonClick(){
        driver.findElement(logoutButton).click();
    }

    @Step("Проверка видимости кнопки 'Профиль'")
    public boolean isProfileButtonVisible() {
        return driver.findElement(profileButton).isDisplayed();
    }
}

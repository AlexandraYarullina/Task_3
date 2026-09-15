package org.example.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.utils.Constants.URL_MAIN_PAGE;

// класс главной страницы
public class MainPage {
    private WebDriver driver;

    // локатор для кнопки Войти в аккаунт
    private By loginMainPageButton = By.xpath("//button[text()='Войти в аккаунт']");
    // локатор для копки Личный Кабинет
    private By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']");
    // локатор для раздела Булки
    private By sectionBun = By.xpath(".//span[text()='Булки']/parent::div");
    // локатор для раздела Соусы
    private By sectionSauce =  By.xpath(".//span[text()='Соусы']/parent::div");
    // локатор для раздела Начинки
    private By sectionFilling = By.xpath(".//span[text()='Начинки']/parent::div");
    // локатор для копки Оформить заказ
    private By orderButton = By.xpath(".//button[text()='Оформить заказ']");

    // конструктор класса
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие страницы авторизации")
    public void openMainPage() {
        driver.get(URL_MAIN_PAGE);
    }

    @Step("Ожидание загрузки главной страницы")
    public void waitForLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(sectionBun));
    }

    @Step("Нажатие на кнопку Войти в аккаунт")
    public void loginMainPageButtonClick() {
        driver.findElement(loginMainPageButton).click();
    }

    @Step("Нажатие на кнопку Личный кабинет")
    public void personalAccountButtonClick() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Нажатие на раздел Булки")
    public void checkSectionBunClick () {
        driver.findElement(sectionBun).click();
    }

    @Step("Нажатие на раздел Соусы")
    public void checkSectionSauceClick () {
        driver.findElement(sectionSauce).click();
    }

    @Step("Нажатие на раздел Начинки")
    public void checkSectionFillingClick () {
        driver.findElement(sectionFilling).click();
    }

    @Step("Проверка активен ли раздел 'Булки'")
    public boolean isBunsTabActive() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.attributeContains(sectionBun, "class", "tab_tab_type_current"));
    }

    @Step("Проверка активен ли раздел 'Соусы'")
    public boolean isSaucesTabActive() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.attributeContains(sectionSauce, "class", "tab_tab_type_current"));
    }

    @Step("Проверка активен ли раздел 'Начинки'")
    public boolean isFillingsTabActive() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.attributeContains(sectionFilling, "class", "tab_tab_type_current"));
    }

    @Step("Проверка видимости кнопки 'Оформить заказ', авторизованного пользователя")
    public boolean orderButtonIsDisplayed() {
        return driver.findElement(orderButton).isDisplayed();
    }
}


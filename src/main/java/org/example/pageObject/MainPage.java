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
    private By personalAccountButton = By.xpath("//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Личный Кабинет']");
    // локатор для раздела Булки
    private By sectionBun = By.xpath(".//span[text()='Булки']");
    // локатор для раздела Соусы
    private By sectionSauce = By.xpath(".//span[text()='Соусы']");
    // локатор для раздела Начинки
    private By sectionFilling = By.xpath(".//span[text()='Начинки']");
    //Кнопка Оформить заказ
    private By orderButton = By.xpath(".//button[text()='Оформить заказ']");
    //Кнопка раздела Начинки - активная
    private By activeFillingButton = By.xpath("//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']");
    //Кнопка раздела Соусы - активная
    private By activeSauceButton = By.xpath("//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']");
    //Кнопка раздела Булки - активная
    private By activeBunButton = By.xpath("//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']");

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
        waitForVisibility(sectionBun);
        driver.findElement(sectionBun).click();
    }

    @Step("Нажатие на раздел Соусы")
    public void checkSectionSauceClick () {
        waitForVisibility(sectionSauce);
        driver.findElement(sectionSauce).click();
    }

    @Step("Нажатие на раздел Начинки")
    public void checkSectionFillingClick () {
        waitForVisibility(sectionFilling);
        driver.findElement(sectionFilling).click();
    }

    @Step("Ожидание загрузки элементов")
    public void waitForVisibility(By element) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    @Step("Проверка видимости надписи раздела 'Булки'")
    public boolean sectionBunIsDisplayed() {
        return driver.findElement(activeBunButton).isDisplayed();
    }

    @Step("Проверка видимости надписи раздела 'Соусы'")
    public boolean sectionSauceIsDisplayed() {
        return driver.findElement(activeSauceButton).isDisplayed();
    }

    @Step("Проверка видимости надписи раздела 'Начинки'")
    public boolean sectionFillingIsDisplayed() {
        return driver.findElement(activeFillingButton).isDisplayed();
    }
}


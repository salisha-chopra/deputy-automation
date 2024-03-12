package org.pages;

import org.apache.commons.io.FileUtils;
import org.components.PageMenu;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

public abstract class WebPage {

    protected static WebDriver driver;

    protected WebDriverWait wait;
    protected JavascriptExecutor jse;
    protected Actions actions;

    public WebPage(WebDriver driver){
        this.driver = driver;

        //this = PageFactory.initElements(driver, this.getClass());
        //wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        //jse = (JavascriptExecutor) this.driver;

    }
    public void applySleep(int seconds){
        try {
            Thread.sleep(seconds * 1000L);
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
    public boolean isElementPresent(String selector) {
        try {
            if (driver.findElement(By.cssSelector(selector)) != null) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("element with selector " + selector + " is not present ");
        }
        return false;
    }

    public void logout() {
        WebElement logoutDropdown = driver.findElement(By.cssSelector("li#head-me > a"));
        logoutDropdown.click();
        WebElement logoutButton = driver.findElement(By.cssSelector("a#btnLogout"));
        logoutButton.click();

    }

    public void closeTrialConfirmDialog() {
        WebElement clickCrossTrial = null;
        try {
            clickCrossTrial = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector
                    ("div[class = 'banner banner--trial u-no-rhythm'] > div > div:nth-child(3) >span")));
        } catch (TimeoutException ex) {
           return ;
        }
        clickCrossTrial.click();
    }

    public void scrollIntoViewJS(WebElement elm) {
        jse.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", elm);
    }

    public void clickUsingActions(WebElement elm) {
        actions.moveToElement(elm).click().build().perform();
    }

    public void clickUsingJS(WebElement elm) {
        jse.executeScript("arguments[0].click();", elm);
    }

    public void waitUnitlClickable(WebElement elm) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(elm));
        } catch (TimeoutException ex) {
            System.out.println("");
        }
    }

    public void pressKey(Keys key) {
        actions.sendKeys(key).build().perform();
    }
    public void closeTaskHeader() {
        WebElement taskHeader = driver.findElement(By.cssSelector("div[class='header-close']"));
        try {
            taskHeader.click();
        }catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }



}

package org.pages.people;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.WebPage;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class PersonalPage extends WebPage {

    JavascriptExecutor jse;

    @FindBy(css = "button[data-vue-test='personal-edit-btn']")
    protected WebElement editButton;
    @FindBy(css = "div[data-vue-test='emp-pronouns'] input")
    protected WebElement pronoun;

    @FindBy(css = "ul[class = 'co-scrollbar__view co-select-dropdown__list'] > li")
    protected List<WebElement> pronounList;
//    @FindBy(css = "div[data-vue-test='emp-dob'] input")
//    protected WebElement empDateOfBirth;
//
//    @FindBy(css = "div[class = 'people-form-edit__body'] > div > div > div:nth-child(2)")
//    protected WebElement editContactForm;
//    protected WebElement address;
//    protected WebElement postalcode;
//    protected WebElement city;
//    protected WebElement country;
//    @FindBy(css = "div[class = 'co-notification__content'] > p")
//    protected WebElement saveSuccessMessage;
//    protected String successMessage;
//
//    @FindBy(css = "div[data-vue-test='ppl-form-view-close'] > i")
//    protected WebElement closeButton;
//
      protected WebDriverWait wait;
//
//
//    @FindBy(css = "button[data-vue-test='personal-save-btn']")
//    protected WebElement saveButton;
//    @FindBy(css = "div [data-v-0df7364e] > div  ul > li")
//    protected List<WebElement> countryList;
    public PersonalPage(WebDriver driver) {
        super(driver);
        jse = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void editEmployeeData() {
//        applySleep(5);
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(20));
        fluentWait.until((ExpectedCondition<Boolean>) driver ->
                ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        Random random = new Random();
        Actions action = new Actions(driver);
        //wait.until()
        jse.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", editButton);

        action.moveToElement(editButton).click().build().perform();
        wait.until(ExpectedConditions.visibilityOf(pronoun));

        //editButton.click();
        jse.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", pronoun);
        action.moveToElement(pronoun).click().build().perform();
        //pronoun.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(pronounList));
        if(pronounList.size() > 0) {

            int randomEmployeePronoun = random.nextInt(pronounList.size());
            if(randomEmployeePronoun >= 0 && (randomEmployeePronoun <= pronounList.size() - 1)) {
                pronounList.get(randomEmployeePronoun).click();
            }
        }
//        /*jse.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", empDateOfBirth);
//        empDateOfBirth.sendKeys("21-04-1992");*/
//        System.out.println(editContactForm);
//        address = editContactForm.findElement(By.cssSelector
//                ("div:nth-child(2) >form > div:nth-child(3) input[class='co-input__inner']"));
//        jse.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", address);
//        address.clear();
//        address.sendKeys(generateRandomNumber() + " boulevard de maisonneuve");
//        postalcode = editContactForm.findElement(By.cssSelector("div:nth-child(2) > " +
//                "form > div:nth-child(4) > div input"));
//        postalcode.clear();
//        postalcode.sendKeys("H3H 2N3");
//        city = editContactForm.findElement(By.cssSelector("div:nth-child(2) > " +
//                "form > div:nth-child(5) > div input"));
//        city.clear();
//        city.sendKeys("Montreal");
//
//        country = editContactForm.findElement(By.cssSelector("div:nth-child(2) > " +
//                "form > div:nth-child(7) > div input"));
//        jse.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", country);
//        action.moveToElement(country).click().perform();
//        if(countryList.size() > 0){
//            int randomCountrySelect = random.nextInt(countryList.size());
//            if(randomCountrySelect >= 0 && (randomCountrySelect <= countryList.size() - 1)) {
//                jse.executeScript("arguments[0].scrollIntoViewIfNeeded(true);",
//                        countryList.get(randomCountrySelect));
//                action.moveToElement(countryList.get(randomCountrySelect)).click().perform();
//            }
//        }
//        if(saveButton.isEnabled()) {
//            saveButton.click();
//        }
//        try{
//            wait.until(ExpectedConditions.visibilityOf(saveSuccessMessage));
//            saveSuccessMessage = driver.findElement(By.cssSelector("div[class = 'co-notification__content'] > p"));
//            successMessage = saveSuccessMessage.getText();
//            jse.executeScript("arguments[0].click()",closeButton);
//        }catch(Exception e) {
//            System.out.println(e.getMessage());
//        }

        //action.moveToElement(closeButton).click().perform();

    }

    private int generateRandomNumber() {
        int min = 1000;
        int max = 9999;
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
//    public String getSuccessMessage() {
//        return successMessage;
//    }
}

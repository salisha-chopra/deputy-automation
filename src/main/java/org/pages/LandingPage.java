package org.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LandingPage extends WebPage {
    //@FindBy(css = "div[class='vfm__content modal-content'] button[class='modal__close-peach']")
    @FindBy(css = "div[class='vfm__content modal-content'] button[class='modal__close-peach']")
    protected WebElement popup;
    @FindBy(partialLinkText = "Log in")
    protected WebElement loginLink;
    protected WebElement acceptAllCookies;
    //FindBy(css = "div[class='drift-widget-message-preview-wrapper'] button > svg")
   // @FindBy( =  )
    protected WebElement helpPopup;
    @FindBy(css = "label[class='DSLInput_label'][for='email']")
    protected List<WebElement> labels;
    //Actions actions;
    //@FindBy(css = "iframe[class = 'drift-frame-controller']")
    protected WebElement closeHelpIframe;
    JavascriptExecutor jse;
    WebDriverWait wait;
    public LandingPage(WebDriver driver){
        super(driver);
    }

    public void closeHelpPopup(){
        try {
            if(isElementPresent("iframe[class = 'drift-frame-controller']")) {
                closeHelpIframe = driver.findElement(By.cssSelector("iframe[class = 'drift-frame-controller']"));
                driver.switchTo().frame(closeHelpIframe);
                if(isElementPresent("div[class='drift-widget-message-preview-wrapper']>div>button>svg")) {
                    helpPopup = driver.findElement(By.cssSelector("div[class='drift-widget-message-preview-wrapper']>" +
                            "div>button>svg"));
                    System.out.println("help popup found");
                    helpPopup.click();
                    driver.switchTo().defaultContent();
                }
            }else {
                System.out.println("no iframe for close help dialog");
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void checkPopUp(){
        try {
            applySleep(5);
            //if(isElementPresent("div[class='vfm__content modal-content'] button[class='modal__close-peach']")) {
            if(popup != null) {
                popup.click();
            }
        } catch (Exception e) {
            System.out.println("popup is not present" +e.getMessage());
        }
    }
    public void acceptCookies(){
        //applySleep(5);
        try {
            System.out.println(driver);
            jse = (JavascriptExecutor) driver;
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            applySleep(5);
            acceptAllCookies = (WebElement) jse.executeScript("return document.querySelector('#usercentrics-root')." +
                    "shadowRoot.querySelector('button[data-testid=\"uc-accept-all-button\"]')");
            if(acceptAllCookies != null) {
                closeHelpPopup();
                acceptAllCookies.click();
                System.out.println("Accepted all cookies");
            }
        } catch (Exception e) {
            System.out.println("Cookies popup or accept all button not found: " + e.getMessage());
        }
    }
    public void loginLinkClick(){

            loginLink.click();

        applySleep(4);
    }
    //
    public String getTitle(){
        return driver.getTitle();
    }
    public List<WebElement> getInputLabels(){
        return labels;
    }

}

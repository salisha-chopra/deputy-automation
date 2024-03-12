package org.pages;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends WebPage {

    @FindBy(id = "login-email")
    protected WebElement email;

    @FindBy(id = "login-password")
    protected WebElement password;

    @FindBy(id = "btnLoginSubmit")
    protected WebElement loginButton;

    @FindBy(css = "#my-dashboard-summary>h3")
    protected WebElement dashboardText;

    protected WebElement closeTaskDonePopup;

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void loginSuccessful(String email, String password){
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until((ExpectedCondition<Boolean>) driver ->
                ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));

        this.email.sendKeys(email);

        this.password.sendKeys(password);

        if(loginButton.isDisplayed() && loginButton.isEnabled()){
            loginButton.click();
        }
    }
    public String getDashBoardData(){
        return dashboardText.getText();
    }


}

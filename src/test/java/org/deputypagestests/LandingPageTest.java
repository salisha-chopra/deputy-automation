package org.deputypagestests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.pages.LandingPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;


public class LandingPageTest extends BaseTest {
    LandingPage landingPage;
    List<WebElement> loginInputLabels;

    @BeforeClass
    public void verifyPopup(){
        String url = "https://once.deputy.com/my/login";
        driver.get(url);
        System.out.println(driver);
        landingPage = PageFactory.initElements(driver, LandingPage.class);
        landingPage.acceptCookies();
        landingPage.checkPopUp();
    }
    @Test(priority = 1)
    public void loginLinkClick(){
        Assert.assertEquals(landingPage.getTitle(), "Scheduling, Timesheet & Time Clock Software — Deputy");
        landingPage.loginLinkClick();
        loginInputLabels = landingPage.getInputLabels();
        Assert.assertEquals(loginInputLabels.get(0).getText(), "Username or Email");
        Assert.assertEquals(loginInputLabels.get(1).getText(), "Password");
    }
}

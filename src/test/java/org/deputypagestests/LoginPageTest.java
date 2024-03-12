package org.deputypagestests;

import org.openqa.selenium.support.PageFactory;
import org.pages.LandingPage;
import org.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginPageTest extends BaseTest {
    LoginPage loginPage;
    String dashboardText;

    @BeforeClass
    public void openApplication(){
        String url = "https://once.deputy.com/my/login";
        driver.get(url);
        System.out.println(driver);
        LandingPage landingPage = PageFactory.initElements(driver, LandingPage.class);
        landingPage.acceptCookies();
        landingPage.checkPopUp();
        landingPage.loginLinkClick();
    }

    @Test(priority = 1 )
    public void testLoginSuccessful(){
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.loginSuccessful("fmegan7715+test114@gmail.com", "123123Aa@");
        dashboardText = loginPage.getDashBoardData();
        Assert.assertEquals(dashboardText, "Dashboard");
    }
}

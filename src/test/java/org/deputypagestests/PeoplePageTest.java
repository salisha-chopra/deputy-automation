package org.deputypagestests;

import org.openqa.selenium.support.PageFactory;
import org.pages.LandingPage;
import org.pages.LoginPage;
import org.pages.people.Employee;
import org.pages.people.PeoplePage;
import org.pages.people.PersonalPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;

public class PeoplePageTest extends BaseTest {
    PeoplePage peoplePage;
    Employee employeePage;
    PersonalPage personalPage;
    @BeforeClass
    public void openApplication(){
        String url = "https://once.deputy.com/my/login";
        driver.get(url);
        System.out.println(driver);
//        LandingPage landingPage = PageFactory.initElements(driver, LandingPage.class);
//        landingPage.acceptCookies();
//        landingPage.checkPopUp();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//        landingPage.loginLinkClick();
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.loginSuccessful("fmegan7715+test114@gmail.com", "123123Aa@");
        peoplePage = PageFactory.initElements(driver, PeoplePage.class);
        peoplePage.openMenu();
        peoplePage.closeTaskHeader();
    }
    @Test(priority = 1)
    public void addTeamMemberTest(){
        peoplePage.addTeamMember();
        Assert.assertEquals(peoplePage.getMemberCreatedMessage(), peoplePage.getData());
    }


    @Test(priority = 2)
    public void verifyHeaderNamesTest() {
        peoplePage.verifyHeaderNames();
        ArrayList<String> displayOptions = new ArrayList<>();
        displayOptions = peoplePage.getSelectedDisplayOptions();
        ArrayList<String> displayHeaderNames = new ArrayList<>();
        displayHeaderNames = peoplePage.getHeaderNames();
        Assert.assertEquals(displayOptions, displayHeaderNames);
        //Assert.assertTrue(isDeleted, "task was deleted sucessfully");
    }

    @Test(priority = 3)
    public void editPersonalInfoTest() {

        peoplePage.goToEditData();
        personalPage = PageFactory.initElements(driver, PersonalPage.class);
        personalPage.editEmployeeData();
        //Assert.assertEquals(personalPage.getSuccessMessage(), "Saved successfully!");
    }

    @Test(priority = 4)
    public void uploadMemberFile() {
        employeePage = peoplePage.goToEmployeeUpload();
        employeePage.addEmployeeFileUpload();
        Assert.assertTrue(employeePage.getEmployeeListSize() < employeePage.getFinalEmployeeListSize());
    }


}

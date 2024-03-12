package org.deputypagestests;

import org.openqa.selenium.support.PageFactory;
import org.pages.LandingPage;
import org.pages.LoginPage;
import org.pages.TasksPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;

public class TasksPageTest extends BaseTest {
    TasksPage tasksPage;
    @BeforeClass
    public void openApplication(){
        System.out.println("in open application");
        String url = "https://once.deputy.com/my/login";
//        driver.get(url);
        driver.navigate().to(url);
        System.out.println(driver);
//        LandingPage landingPage = PageFactory.initElements(driver, LandingPage.class);
//        landingPage.acceptCookies();
//        landingPage.checkPopUp();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//        landingPage.loginLinkClick();
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.loginSuccessful("fmegan7715+test114@gmail.com", "123123Aa@");
        tasksPage = PageFactory.initElements(driver, TasksPage.class);
        tasksPage.openMenu();
    }
    @Test(priority = 1)
    public void createTaskTest(){
        tasksPage.createTask();
        ArrayList<String> taskTitles = new ArrayList<>();
        taskTitles = tasksPage.getTitle();
        String actualTitle = tasksPage.getNewlyAddedTask();
        System.out.println("tasktitle for assert:" + taskTitles.get(taskTitles.indexOf(actualTitle)));

        Assert.assertEquals(taskTitles.get(taskTitles.indexOf(actualTitle)) , actualTitle);
        Assert.assertEquals(tasksPage.getIncompleteTaskTotal() + 1, tasksPage.getCurrentIncompleteCount());
    }


    @Test(priority = 3)
    public void deleteTask() {
        boolean isDeleted = tasksPage.deleteTask();
        Assert.assertTrue(isDeleted, "task was deleted sucessfully");
    }

    @Test(priority = 2)
    public void editTaskTest() {
        String updatedData = tasksPage.changeExistingTaskData();
        ArrayList<String> taskTitles = tasksPage.getTitle();
        Assert.assertEquals(taskTitles.get(taskTitles.indexOf(updatedData)) , updatedData);
    }
    @AfterClass
    public void logout() {
        tasksPage.logout();
    }
}

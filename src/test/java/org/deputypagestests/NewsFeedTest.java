package org.deputypagestests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.pages.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//@Listeners(CustomListener.class)
public class NewsFeedTest extends BaseTest {
    //NewsFeedPageAlisha newsFeedPageA;
    NewsFeedPage newsFeedPage;
    List<WebElement> commentsPosted;

    ArrayList<String> comments;

    @BeforeClass
    public void openApplication(){
        String url = "https://once.deputy.com/my/login";
        driver.get(url);
        System.out.println(driver);
//        LandingPage landingPage = PageFactory.initElements(driver, LandingPage.class);
//        landingPage.acceptCookies();
//        landingPage.checkPopUp();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        landingPage.loginLinkClick();
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.loginSuccessful("fmegan7715+test114@gmail.com", "123123Aa@");

        newsFeedPage = PageFactory.initElements(driver, NewsFeedPage.class);
        newsFeedPage.openMenu();
        try {
            newsFeedPage.closePopupOther();
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
        try {
            newsFeedPage.closeTaskHeader();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        //newsFeedPage = PageFactory.initElements(driver, NewsFeedPage.class);

        //newsFeedPageA = PageFactory.initElements(driver, NewsFeedPageAlisha.class);
        //newsFeedPageA.openMenu();
        //newsFeedPage = PageFactory.initElements(driver, NewsFeedPageNew.class);
        //newsFeedPage.initialize();


    }
//    @Test
//    public void addCommentOnPostTest() {
//        Random rand = new Random();
//        int requiredPostIndex = rand.nextInt(newsFeedPage.getTotalNumberOfPosts());
//        System.out.println("generated index is: " +requiredPostIndex);
//        NewsFeedPost requiredPost = newsFeedPage.getPost(requiredPostIndex);
//        requiredPost.addComment("Montreal");
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            System.out.println("baba");
//        }
//        System.out.println("John: " +requiredPost.getLatestComment() + " Rambo");
//        Assert.assertEquals(requiredPost.getLatestComment(), "Montreal");
//    }

    @Test(priority = 1)
    public void createPostTest(){
        newsFeedPage.createPost();
        Assert.assertEquals(newsFeedPage.getPostContent(),newsFeedPage.getPostData());
    }

    @Test(priority = 2)
    public void commentOnAPost() {
        newsFeedPage.commentOnPost();
        Assert.assertEquals(newsFeedPage.getCommentContent(), newsFeedPage.getLatestCommentPosted());
    }

    @Test(priority = 4)
    public void deletePost() {
        newsFeedPage.deletePost();
        Assert.assertTrue(newsFeedPage.getOriginalPostCount() > newsFeedPage.getPostCountAfterDelete());
    }

    @Test(priority = 3)
    public void reactToAPost() {
        newsFeedPage.reactToPost();
        Assert.assertEquals("1 reaction", newsFeedPage.getReactionString());
    }
   // @Test(priority = 2)
    //public void commentOnAnyPost(){
     //   int randomPostIndex = 1;
//        NewsFeedPost selectedPost = newsFeedPage.getNewsFeedPost(randomPostIndex);
//        selectedPost.addComment("How are the things?");
//
//        Assert.assertEquals(selectedPost.getLatestComment(), "How are the things?");

//        newsFeedPage.commentOnPost();
//        commentsPosted = newsFeedPage.getCommentPosted();
//        comments = newsFeedPage.getCommentProvided();
//        if(commentsPosted.size() == comments.size()){
//            for(int i = 0; i < commentsPosted.size(); i++){
//                Assert.assertEquals(commentsPosted.get(i).getText(), comments.get(i));
//            }
//        }

//    }
//
//    @Test(priority = 3)
//    public void reactToPost(){
//        newsFeedPage.reactToPost();
//    }
    @AfterClass
    public void logout() {
        newsFeedPage.logout();
    }
}

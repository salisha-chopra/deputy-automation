package org.pages;

//import org.components.NewsFeedPost;
import org.components.PageMenu;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class NewsFeedPageAlisha extends WebPage {

    //protected List<NewsFeedPost>  newsFeedPosts;
    @FindBy(css = "div[class='newsfeed-posts'] > div")
    protected List<WebElement> allPostsAsWebElements;
    @FindBy(css = "#newsfeed-master > div > div > div > button")
    protected WebElement createPostButton;
    PageMenu pageMenu;
    @FindBy(css = ".newsfeed-post-message__body > div:nth-child(2) > div > div > div > div > input.co-input__inner")
    protected WebElement shareInput;
    @FindBy(css = ".wa-textarea-upload__textarea")
    protected WebElement postContent;
    @FindBy(css = "div[class = 'co-select-dropdown__wrap co-scrollbar__wrap'] > ul > li")
    protected WebElement shareWithAll;
    protected List<WebElement> checkBoxes;
    @FindBy(css = ".wa-textarea-upload__upload-input > div > div >button.co-button.co-button--default")
    protected  WebElement attachment;
    @FindBy(css = "button[data-test-id = 'bulk-action-accept-button']")
    protected  WebElement postButton;
    @FindBy(css = ".newsfeed-posts > div > div > div > div[class='newsfeed-post-content']")
    protected List<WebElement> checkPosts;
    protected String postData;
    @FindBy(css = "div.newsfeed-post__input-wrapper > div > input.co-input__inner")
    protected List<WebElement> commentOnPosts;
    @FindBy(css = "div.newsfeed-post__input-wrapper > button")
    protected List<WebElement> commentButtons;
    @FindBy(css = "div[class='newsfeed-posts'] > div > div[class='newsfeed-post']> div>" +
            "div[class='newsfeed-post-extra']>div> .newsfeed-post-comment>" +
            "div>.newsfeed-comment-container>div[class='newsfeed-comment-content']")
    protected List<WebElement> postComments;
    //JavascriptExecutor jse;

    protected ArrayList<String> comments;
    String commentPosted;
    //WebDriverWait wait;
    @FindBy(css = "div[class='newsfeed-posts'] > div > div[class='newsfeed-post']> div> " +
            "div[class='newsfeed-post-footer']> button[class='co-button co-button--default']")
    protected List<WebElement> reactList;

    @FindBy(css = "div[class = 'co-popover u-no-rhythm co-popper is-light'] > div[class='reaction-button-wrapper'] ")
    protected List<WebElement> reactionListParent;
    public NewsFeedPageAlisha(WebDriver driver){
        super(driver);
        pageMenu = PageFactory.initElements(driver, PageMenu.class);


    }

//    public NewsFeedPost getNewsFeedPost(int index) {
//        newsFeedPosts = new ArrayList<>();
//        for(int i = 0; i < allPostsAsWebElements.size(); i++) {
//            NewsFeedPost currentPost = new NewsFeedPost(allPostsAsWebElements.get(i));
//            newsFeedPosts.add(currentPost);
//        }
//        if(index < newsFeedPosts.size()) {
//            return newsFeedPosts.get(index);
//        }
//        return null;
//    }


    public void openMenu(){
        pageMenu.goToMenu("News Feed");
    }
    public void createPost(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);
        System.out.println("in create post");
        jse = (JavascriptExecutor) driver;
        //jse.executeScript("window.scrollBy(0,400)");

        WebElement clickCrossTrial = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector
                ("div[class = 'banner banner--trial u-no-rhythm'] > div > div:nth-child(3) >span")));
        clickCrossTrial.click();
        System.out.println(createPostButton);


        //jse.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", createPostButton);
        //actions.moveToElement(createPostButton);
        //actions.perform();
        try {
            jse.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", createPostButton);
            actions.moveToElement(createPostButton).click().perform();
            //createPostButton.click();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        shareInput.sendKeys("");
        WebElement shareWithAllElement = wait.until(ExpectedConditions.
                elementToBeClickable(shareWithAll));
        actions.moveToElement(shareWithAllElement).click().perform();
        //shareWithAll.click();
        actions.moveToElement(shareInput).perform();
        actions.sendKeys(Keys.ESCAPE).perform();
        postContent.sendKeys("this is a new post " + Math.random());
        postData = new String(postContent.getAttribute("value"));
        System.out.println("postdata is:"+postData);
        jse.executeScript("arguments[0].scrollIntoView(true);", postContent);
        //checkBoxes = driver.findElements(By.cssSelector
        //       ("div.newsfeed-post-message__checkboxes > div > div > label > span > input"));
        //System.out.println(checkBoxes.size());

        //jse.executeScript
        //    ("document.querySelectorAll('div.newsfeed-post-message__body--title')[0].click()");
        /*WebElement checkBox = wait1.until(ExpectedConditions.visibilityOf(checkBoxes.get(1)));
        if(checkBoxes.get(1).isSelected()){
            System.out.println("hiii");
            checkBoxes.get(0).click();
            checkBoxes.get(1).click();
        }*/
        attachment.click();
        String filePath = "C:\\designs\\design1.jpg";
        StringSelection stringSelection = new StringSelection(filePath);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        try {
            Robot robot = new Robot();
            robot.delay(2000);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.delay(1000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(2000);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        System.out.println("post content 1:" +postContent.getAttribute("value"));
        //postButton = wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector
         //       ("button[data-test-id = 'bulk-action-accept-button']")));
        try{
            System.out.println("in postbutton click");
            WebElement postButtonClick = wait.until(ExpectedConditions.
                    elementToBeClickable(postButton));
            actions.moveToElement(postButtonClick).click().perform();
            //postButton.click();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        //checkPosts = wait1.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector
        //        (".newsfeed-posts > div > div > div > div[class='newsfeed-post-content']")));
        //checkPosts = driver.findElements(By.cssSelector(".newsfeed-posts > div > div > div > div[class='newsfeed-post-content']"));
        System.out.println(checkPosts.size());
        for(int i = 0; i<checkPosts.size();i++){
            System.out.println(checkPosts.get(i).getText());
        }
    }
    public String getPostData(){
        return postData;
    }
    public String getPostContent(){
        return checkPosts.get(0).getText();
    }
    public void commentOnPost(){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        comments = new ArrayList<>();
        System.out.println(commentOnPosts.size());
        if(commentOnPosts.size() > 0 ){
            for(int i = 0; i < commentOnPosts.size(); i++){
                jse.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", commentOnPosts.get(i));
                commentPosted = "this is the comment "+ Math.random();
                comments.add(i,commentPosted);
                WebElement commentInputField = wait.until(ExpectedConditions.elementToBeClickable(commentOnPosts.get(i)));
                commentInputField.sendKeys(commentPosted);
                if(commentButtons.get(i).isEnabled()){
                    commentButtons.get(i).click();
                }
            }
        }

    }

    public List<WebElement> getCommentPosted(){
        System.out.println("weblist");
        for(int i = 0 ; i < postComments.size(); i++){
            System.out.println(postComments.get(i).getText());
        }
        return postComments;
    }

    public ArrayList<String> getCommentProvided(){
        System.out.println("arraylist");
        for(int i = 0 ; i < comments.size(); i++){
            System.out.println(comments.get(i));
        }
        return comments;
    }

    public void reactToPost(){
        jse = (JavascriptExecutor)driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        if(reactList.size() > 0){
            for(int i = 0 ; i < reactList.size(); i++){
                WebElement element = reactList.get(i);
                try {
                    if(element.isEnabled() && element.isDisplayed()){
                        element = wait.until(ExpectedConditions.elementToBeClickable(element));
                        jse.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", element);
                        element.click();
                        WebElement reactionParent = reactionListParent.get(i);
                        List<WebElement> reactions = reactionParent.findElements(By.cssSelector("span"));
                        System.out.println(reactions.size());
                        System.out.println(reactions.get(3).getAttribute("class"));
                        Random rand = new Random();
                        int randomNumber = rand.nextInt(5);
                        WebElement elementToClick = reactions.get(randomNumber);
                        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
                        jse.executeScript("arguments[0].click();",elementToClick);
                    }
                }catch (StaleElementReferenceException e){
                    System.out.println(e.getMessage());
                }


            }
        }

    }

}

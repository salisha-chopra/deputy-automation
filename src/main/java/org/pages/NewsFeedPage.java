package org.pages;



import org.components.PageMenu;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class NewsFeedPage extends WebPage {
    private static Logger logger = LoggerFactory.getLogger(NewsFeedPage.class);

    @FindBy(css = "div[class='newsfeed-posts'] > div")
    protected List<WebElement> allPostsAsWebElements;
    @FindBy(css = "#newsfeed-master > div > div > div > button")
    protected WebElement createPostButton;
    PageMenu pageMenu;
    @FindBy(css = ".newsfeed-post-message__body > div:nth-child(2) > div > div > div > div > " +
            "input.co-input__inner")
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

    @FindBy(css = "div[class='newsfeed-posts'] > div")
    protected List<WebElement> postsList;

    protected String postData;
//    @FindBy(css = "div.newsfeed-post__input-wrapper > div > input.co-input__inner")
//    protected List<WebElement> commentOnPosts;
//    @FindBy(css = "div.newsfeed-post__input-wrapper > button")
//    protected List<WebElement> commentButtons;

    protected List<WebElement> allCommentsPosted;

    protected String commentContent;

    protected WebElement postCommentButton;
    //JavascriptExecutor jse;

    protected ArrayList<String> comments;
    protected String latestCommentPosted;
    //WebDriverWait wait;
//    @FindBy(css = "div[class='newsfeed-posts'] > div > div[class='newsfeed-post']> div> " +
//            "div[class='newsfeed-post-footer']> button[class='co-button co-button--default']")
//    protected List<WebElement> reactList;
    protected WebElement postCommentInput;

    protected int originalPostCount;

    protected int postCountAfterDelete;

    protected WebElement deletePost;
    protected String reactionString;
    //protected String
    @FindBy(css = "div[class = 'co-popover u-no-rhythm co-popper is-light'] > div[class='reaction-button-wrapper']")
    protected List<WebElement> reactions;

    public NewsFeedPage(WebDriver driver){
        super(driver);
        pageMenu = PageFactory.initElements(driver, PageMenu.class);
    }
    public void openMenu(){
        pageMenu.goToMenu("News Feed");
    }

    private void initializeStandardData() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        System.out.println("in create post");
        jse = (JavascriptExecutor) driver;
    }

    public void createPost(){
        initializeStandardData();
        closeTrialConfirmDialog();
        System.out.println(createPostButton);

        scrollIntoViewJS(createPostButton);
        clickUsingActions(createPostButton);

        shareInput.sendKeys("");
        waitUnitlClickable(shareWithAll);
        clickUsingActions(shareWithAll);

        pressKey(Keys.ESCAPE);

        postContent.sendKeys("this is a new post " + Math.random());
        postData = new String(postContent.getAttribute("value"));
        logger.info("Post content attribute :" + postData);
        System.out.println("postdata is:"+postData);
        jse.executeScript("arguments[0].scrollIntoView(true);", postContent);

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
        checkBoxes = driver.findElements(By.cssSelector
                ("div.newsfeed-post-message__checkboxes input[class = 'co-checkbox__original']"));
        System.out.println(checkBoxes.size());

        /*jse.executeScript
            ("document.querySelectorAll('div.newsfeed-post-message__body--title')[0].click()");*/
        /*WebElement checkBox = wait.until(ExpectedConditions.visibilityOf(checkBoxes.get(1)));
        if(checkBoxes.get(1).isSelected()){
            System.out.println("hiii");
            checkBoxes.get(0).click();
            checkBoxes.get(1).click();
        }*/
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
        applySleep(2);
        checkPosts = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector
                (".newsfeed-posts > div > div > div > div[class='newsfeed-post-content']")));
        checkPosts = driver.findElements(By.cssSelector(".newsfeed-posts > div > div > div > div[class='newsfeed-post-content']"));
        System.out.println(checkPosts.size());
        for(int i = 0; i < checkPosts.size();i++){
            System.out.println(checkPosts.get(i).getText());
        }
    }
    public void commentOnPost(){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println("no of posts: " + postsList.size());
        Random random = new Random();
        int randomPostIndex = random.nextInt(postsList.size());
        if(randomPostIndex >= 0 && randomPostIndex <= postsList.size() - 1) {
            postCommentInput = postsList.get(randomPostIndex).findElement(
                    By.cssSelector("div[class='newsfeed-post'] div[class='newsfeed-post-extra'] " +
                            "> div > div:nth-child(2) input"));
            wait.until(ExpectedConditions.visibilityOf(postCommentInput));
            jse.executeScript("arguments[0].scrollIntoViewIfNeeded(true)", postCommentInput);
            commentContent = "This is the comment: " +  generateRandomNumber();
            System.out.println(commentContent);
            postCommentInput.sendKeys(commentContent);
            System.out.println("post comment is:" + postCommentInput);
            logger.info(commentContent);
            postCommentButton = postsList.get(randomPostIndex).findElement(
                    By.cssSelector("div[class='newsfeed-post'] div[class='newsfeed-post-extra'] > " +
                            "div > div:nth-child(2) button"));
            wait.until(ExpectedConditions.elementToBeClickable(postCommentButton));
            jse.executeScript("arguments[0].click()", postCommentButton);
            //postCommentButton.click();
            applySleep(2);
            allCommentsPosted = postsList.get(randomPostIndex).findElements(
                    By.cssSelector("div[class='newsfeed-post-comment'] div[class='newsfeed-comment-content']"));

            if(allCommentsPosted.size() > 0) {
                latestCommentPosted = allCommentsPosted.get(allCommentsPosted.size() - 1).getText();
                System.out.println(latestCommentPosted);
            }
        }
//        comments = new ArrayList<>();
//        System.out.println(commentOnPosts.size());
//        if(commentOnPosts.size() > 0 ){
//            for(int i = 0; i < commentOnPosts.size(); i++){
//                jse.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", commentOnPosts.get(i));
//                commentPosted = "this is the comment "+ Math.random();
//                comments.add(i,commentPosted);
//                WebElement commentInputField = wait.until(ExpectedConditions.elementToBeClickable(commentOnPosts.get(i)));
//                commentInputField.sendKeys(commentPosted);
//                if(commentButtons.get(i).isEnabled()){
//                    commentButtons.get(i).click();
//                }
//            }
//        }
    }

    public void deletePost() {
        applySleep(2);
        jse = (JavascriptExecutor) driver;
        Random random = new Random();
        Actions action = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        originalPostCount = postsList.size();
        System.out.println("original post list before delete is: " +originalPostCount);
        int randomPostIndex = 0;
        if(postsList.size() > 0) {
            if(postsList.size() == 1) {
                randomPostIndex = 0;
            }else {
                randomPostIndex = random.nextInt(postsList.size() - 1);
            }
        }
        if(randomPostIndex >= 0 && randomPostIndex <= postsList.size() - 1) {
            deletePost = postsList.get(randomPostIndex).findElement(
                    By.cssSelector("div[class='newsfeed-post'] "));
            System.out.println("delete post web element" +deletePost);
            System.out.println("\n\n");
            System.out.println("The post being deleted : " + deletePost.findElement(
                    By.cssSelector("div[class='newsfeed-post-content']")).getText());
            System.out.println("\n\n");

            logger.info("delete post web element" +deletePost);
            logger.info("\n\n");
            logger.info("The post being deleted : " + deletePost.findElement(
                    By.cssSelector("div[class='newsfeed-post-content']")).getText());
            logger.info("\n\n");

            String deletedPostContent = deletePost.findElement(
                    By.cssSelector("div[class='newsfeed-post-content']")).getText();

            //action.moveToElement(deletePost).build().perform();
            //applySleep(1);
            action.moveToElement(deletePost).build().perform();
            applySleep(2);
            WebElement deleteIcon = deletePost.findElement(By.cssSelector("svg[class='co-icon newsfeed-post-delete']"));
            action.moveToElement(deleteIcon).click().build().perform();
            applySleep(10);

        }
        try {
            applySleep(4);
            wait.until((WebDriver driver) -> {
                driver.navigate().refresh();
                return driver.findElements(By.cssSelector("div[class='newsfeed-posts'] > div")).size();
            });
            postsList = driver.findElements(By.cssSelector("div[class='newsfeed-posts'] > div"));
            postCountAfterDelete = postsList.size();
            System.out.println("post count after delete " +postCountAfterDelete);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
    public String getPostData(){
        return postData;
    }
    public int getOriginalPostCount() {
        return originalPostCount;
    }
    public int getPostCountAfterDelete() {
        return postCountAfterDelete;
    }
    public String getPostContent(){
        return checkPosts.get(0).getText();
    }
    private int generateRandomNumber() {
        int min = 1;
        int max = 9999;
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
    public String getLatestCommentPosted() {
        return latestCommentPosted;
    }
    public String getCommentContent() {
        return commentContent;
    }

    public void reactToPost(){
        Actions action = new Actions(driver);
        applySleep(2);
        jse = (JavascriptExecutor)driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Random random = new Random();
        postsList = driver.findElements(By.cssSelector("div[class='newsfeed-posts'] > div"));
        System.out.println("postslist size: " +postsList.size());
        int randomIndex = 0;
        try{
            if(postsList.size() > 0) {
                if (postsList.size() == 1) {
                    randomIndex = 0;
                } else {
                    randomIndex = random.nextInt(postsList.size());
                }
            }
            if (randomIndex >= 0 && randomIndex <= postsList.size() - 1) {
                WebElement selectedPostToWorkOn = postsList.get(randomIndex);
                // reactionControllerForPost
                System.out.println("random index = " +randomIndex);
                System.out.println(selectedPostToWorkOn);
                scrollIntoViewJS(selectedPostToWorkOn);
                applySleep(1);
                int randomNumber = random.nextInt(5);
                if(randomNumber >= 0 && randomNumber < 5) {
                    chooseReation(selectedPostToWorkOn, randomNumber);
                }

                //applySleep(3);
                //clickReactionButton.click();
//                WebElement reactionParent = reactions.get(randomIndex);
//                List<WebElement> reactionsList = reactionParent.findElements(By.cssSelector("span"));
//                System.out.println(reactionsList.size());
//                System.out.println(reactionsList.get(3).getAttribute("class"));
//                Random rand = new Random();
//                int randomNumber = rand.nextInt(5);
//                WebElement elementToClick = reactionsList.get(randomNumber);
//                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
//                jse.executeScript("arguments[0].click();",elementToClick);
                //System.out.println("reactions size: " + reactionsList.size());
                //int randomReactionIndex = random.nextInt(reactions.size() - 1);
//                WebElement reactionToClick = reactions.get(randomIndex);
//                reactionToClick.click();
                //System.out.println("reaction to click: " +reactionToClick.getAttribute("class"));
                //action.moveToElement(reactionToClick).click().build().perform();
                //jse.executeScript("arguments[0].click();", reactionToClick);
                //System.out.println("index selected is: " + randomReactionIndex);
                //applySleep(1);
                //reactionToClick.click();

                //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
                //jse.executeScript("arguments[0].click();", reactionToClick);
                applySleep(2);
                WebElement reactionResult = selectedPostToWorkOn.findElement(
                        By.cssSelector("div[class='newsfeed-post-reactions'] > span[class='newsfeed-post-reactions-count']"));
                reactionString = reactionResult.getText();

                List<WebElement> reactButtonsForComments = selectedPostToWorkOn.findElements(By.cssSelector("div[class='newsfeed-comment-reactions-wrapper'] > span > a"));
                if(reactButtonsForComments.size() != 0) {
                    reactButtonsForComments.get(0).click();
                }
            }

        }catch (StaleElementReferenceException e) {
            System.out.println(e.getMessage());
        }

    }

    public void chooseReation(WebElement parentElm, int reactionNumber) {

        WebElement reactionControllor = parentElm.findElement(By.cssSelector("div.newsfeed-post-footer > button"));
        clickUsingJS(reactionControllor);
        applySleep(2);
        List<WebElement> allReactions = driver.findElements(By.cssSelector
                ("div[class='co-popover u-no-rhythm co-popper is-light'][aria-hidden='false'] > div > span"));
        System.out.println(allReactions.size());
        if(reactionNumber == 0) {
            allReactions.get(0).click();
        } else if(reactionNumber == 1) {
            allReactions.get(1).click();
        } else if(reactionNumber == 2) {
            allReactions.get(2).click();
        } else if(reactionNumber == 3) {
            allReactions.get(3).click();
        } else if(reactionNumber == 4) {
            allReactions.get(4).click();
        }
    }
    public String getReactionString() {
        return reactionString;
    }


    public void closePopupOther() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#pendo-close-guide-abbabac6")));
        WebElement popupElement = driver.findElement(By.cssSelector("button#pendo-close-guide-abbabac6"));
        try {
            popupElement.click();
        }catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
}

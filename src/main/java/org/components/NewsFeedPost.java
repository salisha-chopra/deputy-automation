package org.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;

public class NewsFeedPost {

    //protected WebDriver driver;
    protected WebElement currentFeedRepElement;


    @FindBy(css = "div.newsfeed-post-author > a")
    protected WebElement author;
    @FindBy(css = "div.newsfeed-post-author > div.newsfeed-post-sub-text" )
    protected WebElement updatedTime;
    @FindBy(css = "div.newsfeed-post-author > div > span")
    protected WebElement location;
    @FindBy(css = "div:nth-child(3)")
    protected WebElement postText;

    protected WebElement image;

    @FindBy(css = "div.newsfeed-post-footer > button" )
    protected WebElement reactionController;
    @FindBy(css = "div.newsfeed-post-reactions > span:nth-child(2)" )
    protected WebElement actualReaction;
    @FindBy(css = "div.newsfeed-comment-content" )
    protected List<WebElement> comments;

    public NewsFeedPost(WebElement postWebElement) {
        currentFeedRepElement = postWebElement;
    }

    public String getAuthor() {
        return author.getText();
    }

    public String getUpdatedDate() {
        return updatedTime.getText();
    }

    public String getLocation() {
        return location.getText();
    }

    public boolean hasImage() {
        try {
            currentFeedRepElement.findElement(By.cssSelector("div.newsfeed-post-attachments img"));
        } catch(NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public String getImageSrc() {
        if(hasImage()) {
            image = currentFeedRepElement.findElement(By.cssSelector("div.newsfeed-post-attachments img"));
            return image.getAttribute("src");
        }
        return null;
    }
    public String getReactionText() {
        return actualReaction.getText();
    }

    public List<WebElement> getCommentsWebElements() {
        return comments;
    }

    public String getComment(int index) {
        if(index < getCommentsWebElements().size()) {
            return getCommentsWebElements().get(index).getText();
        }
        return null;
    }

    public void addReation(String reactionType) {
        //"div.newsfeed-post-reactions > span:nth-child(2)"
        reactionController.click();
        List<WebElement> allReactions = currentFeedRepElement.findElements(By.cssSelector("div.reaction-button-wrapper > span"));
        if(reactionType.equalsIgnoreCase("Like")) {
            allReactions.get(0).click();
        } else if(reactionType.equalsIgnoreCase("Joy")) {
            allReactions.get(1).click();
        } else if(reactionType.equalsIgnoreCase("Celebrate")) {
            allReactions.get(2).click();
        } else if(reactionType.equalsIgnoreCase("Seen")) {
            allReactions.get(3).click();
        } else if(reactionType.equalsIgnoreCase("Love")) {
            allReactions.get(4).click();
        }
    }

    public void addComment(String comment) {
        WebElement commentTextArea = currentFeedRepElement.findElement(
                By.cssSelector("div.newsfeed-post__input-wrapper > div > input"));
        commentTextArea.sendKeys(comment);
        WebElement commentPostButton = currentFeedRepElement.findElement(
                By.cssSelector("div.newsfeed-post__input-wrapper > button"));
        commentPostButton.click();
    }

    public String getLatestComment() {
        comments = currentFeedRepElement.findElements(By.cssSelector("div.newsfeed-comment-content"));
        return comments.get(getTotalComments()-1).getText();
    }

    public int getTotalComments() {
        System.out.println(comments.size());
        return comments.size();
    }
}

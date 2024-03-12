package org.pages;

import org.components.NewsFeedPost;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class NewsFeedPageNew extends WebPage{
    @FindBy(css = "div.newsfeed-posts > div" )
    List<WebElement> postElements;
    protected List<NewsFeedPost> postList;
    public NewsFeedPageNew(WebDriver driver) {
        super(driver);

    }
    public void initialize() {
        postList = new ArrayList<>();
        postElements = driver.findElements(By.cssSelector("div.newsfeed-posts > div"));
        for(WebElement currentPostElement : postElements) {
            NewsFeedPost post = PageFactory.initElements(currentPostElement, NewsFeedPost.class);
            postList.add(post);
        }
    }
    public NewsFeedPost getPost(int index) {
        if(index < postList.size() && index >= 0) {
            return postList.get(index);
        }
        return null;
    }
    public int getTotalNumberOfPosts() {
        return postList.size();
    }
}

package org.components;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;

public class PageMenu {
    @FindBy(css = "#main-nav > li > a" )
    protected List<WebElement> menuBarList;
    @FindBy(css = "a[href='#team']")
    protected WebElement peopleMenu;
    protected WebDriver driver;
    protected WebDriverWait wait;
    public PageMenu(WebDriver driver){
        this.driver = driver;
    }

    public void goToMenu(String menuName){
        /*for(int i = 0; i < menuBarList.size(); i++){
            System.out.println(menuBarList.get(i).getText());
        }*/
        if(menuName.equalsIgnoreCase("News Feed")){
            menuBarList.get(1).click();
        }else if(menuName.equalsIgnoreCase("Tasks")){
            menuBarList.get(2).click();
        }else if(menuName.equalsIgnoreCase("Locations")){
            menuBarList.get(3).click();
        }else if(menuName.equalsIgnoreCase("Schedule")){
            menuBarList.get(4).click();
        }else if(menuName.equalsIgnoreCase("Report")){
            menuBarList.get(6).click();
        }else if(menuName.equalsIgnoreCase("People")) {
            peopleMenu.click();
        }
    }




}

package org.pages.people;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.WebPage;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

public class Employee extends WebPage {

    protected WebElement selectFileButton;

    //@FindBy(css = "iframe[src $= 'flatfile']")
    //protected WebElement iframe;

    @FindBy(css = "iframe")
    protected List<WebElement> iframes;

    @FindBy(css = "div[class='Styles__PinnedColumnsWrapper-sc-1xnrq3j-2 guBBsU'] > div > div")
    protected List<WebElement> confirmRowCount;

    @FindBy(css = "div[class='Wrapper--15fxyhn JgRsw'] > button")
    protected List<WebElement> continueCloseButtons;

    @FindBy(css = "button[class='FooterButton--1qsp41b jZRuOL']")
    protected WebElement continueButton;
    @FindBy(css = "div[class='TableWrapper--qzkpbi kixlHT'] > table > tbody > tr")
    protected List<WebElement> compareColumns;

    protected int countRowFinal;

    JavascriptExecutor jse;
    Actions action;
    @FindBy(css = "div[data-testid='filter-with-count'] > div > button:nth-child(3) > span > span")
    protected WebElement errorButton;

    @FindBy(css = "button[class='FooterButton--1qsp41b jZRuOL']")
    protected WebElement importRecords;

    protected String finalSuccessMessage;
    protected String successMessage;
    //@FindBy(css = "div[class='ShowOnLarge--1puvins beeZnH'] div[class='HeaderWrap--1kyw95q iaMkmU']")
    protected WebElement finalMessageDiv;

    @FindBy(css = "div[class='ButtonGroup--1n3uyl9 hUWsZW'] button")
    protected WebElement finalSubmitButton;

    protected List<WebElement> employeeList;
    protected int employeeListSize;
    protected int finalEmployeeListSize;
    public Employee(WebDriver driver) {
        super(driver);
        jse = (JavascriptExecutor) driver;
        action = new Actions(driver);
    }
    public void addEmployeeFileUpload() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            employeeList = driver.findElements(By.cssSelector("div[class='people-module-table__body'] > div"));
            wait.until(ExpectedConditions.visibilityOfAllElements(employeeList));
            employeeListSize = employeeList.size();
            System.out.println("initial employee list: " +employeeListSize);
            System.out.println(iframes.size());
            if(!iframes.isEmpty()) {
                for(int i = 0; i < iframes.size(); i++) {
                    System.out.println(iframes.get(i));
                    System.out.println(iframes.get(i).getAttribute("src"));
                }
            }
            if(!iframes.isEmpty())
            {
                wait.until(ExpectedConditions.visibilityOf(iframes.get(5)));
                System.out.println("found element: " +iframes.get(5));
                jse.executeScript("arguments[0].scrollIntoView(true);", iframes.get(5));
                driver.switchTo().frame(iframes.get(5));
            }
            if(continueCloseButtons.size() > 0) {
                WebElement closeButton = continueCloseButtons.get(1);
                closeButton.click();
                //selectFileButton.click();
            }
            WebElement dropzoneSpace = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div[class='DropzoneSpace--n7ykua gfUtLy'] > " +
                            "div button[class='ButtonBase--11gydp3 eXypNe']")));
            action.moveToElement(dropzoneSpace).perform();
            selectFileButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("div[class='DropzoneSpace--n7ykua gfUtLy'] > " +
                            "div button[class='ButtonBase--11gydp3 eXypNe']")));
            System.out.println(selectFileButton);
            selectFileButton.click();
            applySleep(5);
            System.out.println("file attachment codeC:\\Users\\alish\\Downloads\\DeputyPremiumEmployeeSync.xlsx" +
                    "");
            String filePath = "C:\\Users\\alish\\Downloads\\DeputyPremiumEmployeeSync.xlsx";
            StringSelection stringSelection = new StringSelection(filePath);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            try {
                System.out.println("in try block");
                Robot robot = new Robot();
                //robot.delay(2000);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                robot.delay(2000);
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.delay(1000);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                robot.delay(2000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            wait.until(ExpectedConditions.visibilityOfAllElements(confirmRowCount));
            System.out.println(confirmRowCount.size());
            countRowFinal = confirmRowCount.size() - 2;
            System.out.println(countRowFinal);
            if(confirmRowCount.size() > 0) {
                continueButton.click();
            }

            //iframes = driver.findElements(By.cssSelector())
            //driver.switchTo().frame(iframes.get(5));
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.cssSelector("div[class='TableWrapper--qzkpbi kixlHT'] > table > tbody > tr")));
            compareColumns = driver.findElements(By.cssSelector
                    ("div[class='TableWrapper--qzkpbi kixlHT'] > table > tbody > tr"));
            System.out.println(compareColumns.size());
            wait.until(ExpectedConditions.visibilityOfAllElements(compareColumns));
            System.out.println("compareColums: " + compareColumns.size());
            for(int i = 0 ; i < compareColumns.size(); i++) {
                jse.executeScript("arguments[0].scrollIntoViewIfNeeded(true)",compareColumns.get(i));
                if(i == 27) {
                    break;
                }
                String leftElementValue = compareColumns.get(i).findElement(
                        By.cssSelector("td:nth-child(2) > span > div")).getText();
                String rightElementValue = compareColumns.get(i).findElement(
                        By.cssSelector("td:nth-child(4) > div div[class=' css-150tbnd-singleValue']")).getText();

                if(leftElementValue.contains(rightElementValue)) {
//                        System.out.println(leftElementValue + " match " + rightElementValue);
                }

            }
            continueButton.click();
            wait.until(ExpectedConditions.visibilityOf(errorButton));
            String errorValue = errorButton.getText();
            if(errorValue.equalsIgnoreCase("0")) {
                importRecords.click();
            }
            applySleep(3);

            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("div[class='ShowOnLarge--1puvins beeZnH'] div[class='HeaderWrap--1kyw95q iaMkmU']")));
            finalMessageDiv = driver.findElement(
                    By.cssSelector("div[class='ShowOnLarge--1puvins beeZnH'] div[class='HeaderWrap--1kyw95q iaMkmU']"));
            System.out.println(finalMessageDiv.getText());
            finalSuccessMessage = finalMessageDiv.getText();
            System.out.println(finalSuccessMessage);
            applySleep(2);
            wait.until(ExpectedConditions.visibilityOf(finalSubmitButton));
            finalSubmitButton.click();
            driver.switchTo().defaultContent();
            applySleep(2);
            employeeList = driver.findElements(By.cssSelector("div[class='people-module-table__body'] > div"));
            wait.until(ExpectedConditions.visibilityOfAllElements(employeeList));
            finalEmployeeListSize = employeeList.size();
            System.out.println("final employee list size: "+finalEmployeeListSize);



        } catch (TimeoutException e) {
            // Log error and handle timeout exception
            System.err.println("Timeout occurred while waiting for element to be clickable.");
            e.printStackTrace();
        }


    }
    public int getEmployeeListSize() {
        return employeeListSize;
    }
    public int getFinalEmployeeListSize() {
        return finalEmployeeListSize;
    }
}

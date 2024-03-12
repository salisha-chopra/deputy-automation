package org.pages.people;


import org.components.PageMenu;
import org.components.People;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.WebPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PeoplePage extends WebPage {

    PageMenu pageMenu;
    People people;
    protected  WebDriverWait wait;
    @FindBy(css = "button[data-vue-test = 'people-module-add-people-dropdown']")
    protected WebElement addPeopleDropdown;

    @FindBy(css = "ul[class='co-dropdown-menu'] > li[class $='people-dropdown-option']")
    protected List<WebElement> optionsToAddPeople;

    @FindBy(css = "input[data-vue-test='add-employee-first-name']")
    protected WebElement firstname;

    @FindBy(css = "input[data-vue-test='add-employee-last-name']")
    protected WebElement lastname;

    @FindBy(css = "div[data-vue-test='add-employee-mobile'] input")
    protected WebElement mobile;

    @FindBy(css = "input[data-vue-test='add-employee-email']")
    protected WebElement email;

    @FindBy(css = "div[class='co-select __input-field'] input")
    protected WebElement mainLocation;

    @FindBy(css = "div[class = 'co-select-dropdown__wrap co-scrollbar__wrap'] " +
            "li[data-vue-test = 'add-employee-location-dropdown-option']  ")
    protected List<WebElement> mainLocationList;
    @FindBy(css = "div[class='co-select __locations'] input")
    protected WebElement otherLocation;

    @FindBy(css = "ul[class='co-scrollbar__view co-select-dropdown__list'] > " +
            "li[data-vue-test='add-employee-locations-dropdown-option']")
    protected List<WebElement> otherLocationList;

    @FindBy(css = "div[data-vue-test='add-employee-access-level-dropdown'] input")
    protected WebElement employeeAccessLevel;

    @FindBy(css = "div li[data-vue-test='add-employee-access-level-dropdown-option']")
    protected List<WebElement> employeeAccessList;

    @FindBy(css = "button[data-vue-test='button-add-invite'] > span")
    protected WebElement addTeamMember;
    @FindBy(css = "div[class = 'co-notification__content'] > p")
    protected WebElement teamMemberCreatedMessage;
    protected String memberCreatedMessage;
    @FindBy(css = "div[data-vue-test = 'ppl-form-view-close'] > i")
    protected WebElement closeDialog;

    protected String firstName;
    protected String lastName;
    protected String emailId;
    protected String mobileNumber;
    @FindBy(css = "button[data-vue-test='ppl-list-display']")
    protected WebElement clickDisplay;
    Employee employee;

    @FindBy(css = "ul[class='people-module-column-toggle__list'] > li" )
    protected List<WebElement> selectDisplayOptions;

    protected ArrayList<String> selectedDisplayOptions;

    @FindBy(css = "thead[class='people-module-table__heading'] > tr > th:not([style='display: none;'])")
    protected List<WebElement> tableHeaderNames;

    protected ArrayList<String> headerNames;
//    @FindBy(css = "ul[data-v-fbd25d56] > li[data-vue-test='emp-edit-btn'])")
//    protected List<WebElement> editUserData;

    protected int randomDisplayOption;

    PersonalPage personalPage;
    JavascriptExecutor jse;

    @FindBy(css = "div[class='people-module-table__body'] > div[class = 'people-module-table__row']")
    protected List<WebElement> employeeList;

    @FindBy(css = "div[class='__instruction'] > button")
    protected WebElement uploadFileButton;

    Actions action;

    public PeoplePage(WebDriver driver){
        super(driver);
        pageMenu = PageFactory.initElements(driver, PageMenu.class);
        people = new People();
    }
    public void openMenu(){
        pageMenu.goToMenu("People");
    }

    public void addTeamMember() {
        addPeopleDropdown.click();
        optionsToAddPeople.get(0).click();
        firstName = "firstname" + generateRandomNumber();
        people.setFirstname(firstName);
        firstname.clear();
        firstname.sendKeys(people.getFirstname());
        lastName = "lastname" + generateRandomNumber();
        people.setLastname(lastName);
        lastname.clear();
        lastname.sendKeys(people.getLastname());
        emailId = "firstname" + "+" + "lastname" + generateRandomNumber() + "@gmail.com";
        people.setEmail(emailId);
        email.clear();
        email.sendKeys(people.getEmail());
        mobileNumber = generateMobileNumber();
        people.setMobile(mobileNumber);
        mobile.clear();
        mobile.sendKeys(people.getMobile());
        mainLocation.click();
        Random rand = new Random();
        int randomLocationNumber;
        if(mainLocationList.size() == 1) {
            randomLocationNumber = 0;
        } else {
            randomLocationNumber = rand.nextInt(mainLocationList.size());
        }

        String mainLocationName = mainLocationList.get(randomLocationNumber).findElement(By.cssSelector("span")).
                getText();
        people.setMainLocation(mainLocationName);
        System.out.println(mainLocationName);
        mainLocationList.get(randomLocationNumber).click();
        employeeAccessLevel.click();
        System.out.println(employeeAccessList.size());
        int randomEmployeeAccessNumber = rand.nextInt(employeeAccessList.size());
        String employeeAccessValue = employeeAccessList.get(randomEmployeeAccessNumber).
                findElement(By.cssSelector("span")).getText();
        System.out.println(employeeAccessValue);
        people.setEmployeeAccess(employeeAccessValue);
        employeeAccessList.get(randomEmployeeAccessNumber).click();
        otherLocation.click();
        System.out.println("otherlocation test: " + otherLocationList.size());
        String otherLocationText = "";
        int randomOtherLocationIndex;

        if(otherLocationList.size() == 0) {
            wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust timeout as needed
            try {
                WebElement otherLocationElement = wait.until(ExpectedConditions.elementToBeClickable(otherLocation));
                System.out.println("otherLocation is clickable");
                // Re-locate the element before clicking
                otherLocationElement = driver.findElement(By.cssSelector("div[class='co-select __locations'] input"));
                otherLocationElement.click();
                otherLocationElement.sendKeys(Keys.ESCAPE);
            } catch (Exception e) {
                System.out.println("otherLocation is not displayed or clickable");
            }
        }else {
            if(otherLocationList.size() == 1) {
                randomOtherLocationIndex = 0;
            } else {
                randomOtherLocationIndex = rand.nextInt(otherLocationList.size());
            }
            otherLocationList.get(randomOtherLocationIndex).findElement(By.cssSelector("svg")).click();
            otherLocationText = otherLocationList.get(randomOtherLocationIndex).findElement(
                    By.cssSelector("span")).getText();
        }
        people.setOtherLocationList(otherLocationText);
        if(addTeamMember.isEnabled()) {
            addTeamMember.click();
        }
        wait.until(ExpectedConditions.visibilityOf(teamMemberCreatedMessage));
        memberCreatedMessage = teamMemberCreatedMessage.getText();
        applySleep(1);
        wait.until(ExpectedConditions.elementToBeClickable(closeDialog));
        jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()",closeDialog);
        //closeDialog.click();
    }

    private boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public void verifyHeaderNames() {

        Random random = new Random();
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.elementToBeClickable(clickDisplay));
        applySleep(1);
        jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", clickDisplay);
        //clickDisplay.click();
        System.out.println(selectDisplayOptions.size());
        selectedDisplayOptions = new ArrayList<>();
        for(int i = 0; i < selectDisplayOptions.size() ; i++) {
            WebElement option = selectDisplayOptions.get(i).findElement(By.cssSelector("label > span:nth-child(1)"));
            String getAttribute = option.getAttribute("class");
            if(getAttribute.contains("is-checked")) {
                option.click();
            }
        }
        applySleep(1);
        selectedDisplayOptions.add("Name");
        selectedDisplayOptions.add("Access");
        int numSelected = 0;
        while (numSelected < 3 && numSelected < selectDisplayOptions.size())
        {
            randomDisplayOption = random.nextInt(selectDisplayOptions.size());
            if(randomDisplayOption >= 0 && (randomDisplayOption <= selectDisplayOptions.size() - 1)) {
                WebElement checkOption = selectDisplayOptions.get(randomDisplayOption).findElement(
                        By.cssSelector("label > span:nth-child(1)"));
                if(!checkOption.isSelected()) {
                    try {
                        //checkOption.click();
                        jse.executeScript("arguments[0].click()", checkOption);
                        String optionSelected = selectDisplayOptions.get(randomDisplayOption).findElement(
                                By.cssSelector("label > span:nth-child(2)")).getText();
                        selectedDisplayOptions.add(optionSelected);
                        numSelected++;
                    } catch (Exception e) {
                        System.out.println("Error occurred while clicking option: " + e.getMessage());
                    }

                }
            }
        }
        //selectedDisplayOptions.add("\u00A0");
        System.out.println(selectedDisplayOptions.size());
        headerNames = new ArrayList<>();
        for( int i = 0 ; i < tableHeaderNames.size() - 1 ; i++) {
            System.out.println(tableHeaderNames.get(i).getText());
            headerNames.add(tableHeaderNames.get(i).getText());
        }
        Collections.sort(headerNames);
        System.out.println(headerNames);
        Collections.sort(selectedDisplayOptions);
        System.out.println(selectedDisplayOptions);
    }

    public String getData() {
        return people.getFirstname() + " " + people.getLastname() + " successfully added";
    }
    public String getMemberCreatedMessage() {
        return memberCreatedMessage;
    }
    //private int generateRandomNumber;
    private int generateRandomNumber() {
        int min = 1000;
        int max = 9999;
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    private String generateMobileNumber() {
        Random rand = new Random();
        StringBuilder mobileNumber = new StringBuilder();
        mobileNumber.append(rand.nextInt(8) + 2);
        for (int i = 0; i < 9; i++) {
            mobileNumber.append(rand.nextInt(10));
        }
        return mobileNumber.toString();
    }
    public ArrayList<String> getSelectedDisplayOptions() {
        return selectedDisplayOptions;
    }
    public ArrayList<String> getHeaderNames() {
        return headerNames;
    }
    public void goToEditData() {
        System.out.println("in goto edit page");
        jse = (JavascriptExecutor) driver;
        Random random = new Random();
        Actions action = new Actions(driver);
        System.out.println(employeeList.size());

        int randomEmployeeSelection = 0;
        if(employeeList.size() > 0) {
            if(employeeList.size() == 1) {
                randomEmployeeSelection = 0;
            } else {
                randomEmployeeSelection = random.nextInt(employeeList.size());
            }
        }
        if(randomEmployeeSelection >= 0 && (randomEmployeeSelection <= employeeList.size() - 1)) {
            System.out.println("in selecting an employee");
            WebElement selectEmployeeLink = employeeList.get(randomEmployeeSelection).findElement(
                    By.cssSelector("div:nth-child(1) span[data-test-id='people__table-column-name-details-link']"));
            applySleep(2);
            System.out.println("select employee link element" + selectEmployeeLink);
            jse.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", selectEmployeeLink);
            //jse.executeScript("arguments[0].click();", selectEmployeeLink);

            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(selectEmployeeLink));
            selectEmployeeLink.click();
            applySleep(3);
            //action.moveToElement(selectEmployeeLink).click().build().perform();
            //editUserData.get(0).click();
        }
        System.out.println("random employee: " + randomEmployeeSelection);

        //return personalPage;
    }
    public Employee goToEmployeeUpload() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        Actions action = new Actions(driver);
        applySleep(1);
        //applySleep(5);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        System.out.println(addPeopleDropdown);
        //wait.until(ExpectedConditions.elementToBeClickable(addPeopleDropdown));

        addPeopleDropdown = driver.findElement(By.cssSelector("button[data-vue-test = 'people-module-add-people-dropdown']"));
        jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()",addPeopleDropdown);
        //jse.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", addPeopleDropdown);
        optionsToAddPeople.get(3).click();
        jse.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", uploadFileButton);
        //jse.executeScript(")
        uploadFileButton.click();
        employee = PageFactory.initElements(driver, Employee.class);
        return employee;

    }

}

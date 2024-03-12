package org.pages;

import org.components.DateExecution;
import org.components.NewsFeedPost;
import org.components.PageMenu;
import org.components.TasksAssigned;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TasksPage extends WebPage{
    PageMenu pageMenu;
    TasksAssigned tasksAssigned;

    @FindBy(css = "div[id='desktop-task-wrapper'] button[class='btn btn-primary dropdown-toggle']")
    protected WebElement addTaskButton;

    @FindBy(css = "div[id='desktop-task-wrapper'] ul[class='dropdown-menu dropdown-menu-right'] > li")
    protected List<WebElement> addTaskDropdownList;

    @FindBy(css = "input#task-personal-title")
    protected WebElement taskTitle;

    @FindBy(css = "input#task-personal-duedate")
    protected WebElement dueDate;

    @FindBy(css = "textarea#task-personal-description")
    protected WebElement notes;

    @FindBy(css = "#s2id_task-personal-assigned")
    protected WebElement assignTo;

    @FindBy(id = "add-task-btn" )
    protected WebElement saveTaskButton;

//    @FindBy(css = "#select2-drop ul > li")
//    protected List<WebElement> assignToList;
    @FindBy(css = "div#personal-task-list > div")
    protected List<WebElement> taskList;

    @FindBy(css = "div[class='js-task-list pb20'] > ul > li > div:nth-child(3) > p > span")
    protected WebElement incompleteTaskCount;

//  protected List<TasksAssigned> tasksAssignedList;

    protected String taskTitleValue;
//    protected String dueDateToCompleteTask;
    protected String notesData;
    protected int incompleteTaskTotal;
    protected int currentIncompleteCount;
    protected String taskTitleEditedValue;

    protected ArrayList<String> taskTitleList;

    //@FindBy(css = "a[class = 'red u-textCaps js-confm-del-link']")
    protected WebElement confirmDeleteLink;

    @FindBy(css = "div[class = 'modal in']  button[class = 'btn btn-close']")
    protected WebElement closeTaskDialogButton;

    DateExecution dateExecution;
    @FindBy(css = "div[class='js-task-list pb20'] > ul > li > div > a" )
    protected List<WebElement> taskForList;
    public TasksPage(WebDriver driver){
        super(driver);
        pageMenu = PageFactory.initElements(driver, PageMenu.class);
        dateExecution = PageFactory.initElements(driver, DateExecution.class);

    }
    public void createTask(){
        tasksAssigned = new TasksAssigned();
        Actions action = new Actions(driver);
        if(addTaskButton.isDisplayed() && addTaskButton.isEnabled()){
            addTaskButton.click();
        }
        try{
            if(incompleteTaskCount.getText().isEmpty()) {
                incompleteTaskTotal = 0;

            }else {
                incompleteTaskTotal = Integer.parseInt(incompleteTaskCount.getText()) ;
            }

            System.out.println("previous incomplete task" +incompleteTaskTotal);
            addTaskDropdownList.get(1).click();
            taskTitleValue = "This is the title for task " + Math.random();
            tasksAssigned.setTaskTitle(taskTitleValue);
            taskTitle.sendKeys(tasksAssigned.getTaskTitle());
            /*action.moveToElement(assignTo).click().perform();
            action.moveToElement(assignToList.get(1)).click().perform();
            applySleep(2);*/

            System.out.println(addTaskDropdownList.size());
            System.out.println(addTaskDropdownList.get(1).getText());
            System.out.println(dateExecution.getDueDate());
            tasksAssigned.setDueDate(getDueDateForTaskCompletion());

            //System.out.println(dueDateToCompleteTask);
            dueDate.sendKeys(tasksAssigned.getDueDate());
            notesData = "This is the note " + Math.random();
            tasksAssigned.setNotes(notesData);
            notes.sendKeys(tasksAssigned.getNotes());

            saveTaskButton.click();
            applySleep(2);
            incompleteTaskCount = driver.findElement(
                    By.cssSelector("div[class='js-task-list pb20'] > ul > li > div:nth-child(3) > p > span"));
            currentIncompleteCount = Integer.parseInt(incompleteTaskCount.getText());
            System.out.println("current incomplete task" +currentIncompleteCount);
            try {
                applySleep(2);
                //taskForList = driver.findElements(By.cssSelector());
                System.out.println(taskForList.size());
                System.out.println(taskForList.get(0).getText());
                try {
                    action.moveToElement(taskForList.get(0)).click().build().perform();
                }catch (StaleElementReferenceException e) {
                    System.out.println(e.getMessage());
                }
            }catch (NoSuchElementException e){
                System.out.println(e.getMessage());
            }
        }catch (NoSuchElementException elementException){
            System.out.println(elementException.getMessage());
        }
    }

    public ArrayList<String> getTitle() {
        System.out.println("taskList size is: " +taskList.size());
        taskTitleList = new ArrayList<>();
        for(WebElement currentElement : taskList) {
            String title = currentElement.findElement(
                    By.cssSelector("div > div[class ^= 'm-tasks-st-input']")).getText();
            taskTitleList.add(title);
        }
        try {
            closeTaskDialogButton.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return taskTitleList;
    }

    public String changeExistingTaskData() {
        Actions action = new Actions(driver);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        action.moveToElement(taskForList.get(0)).click().build().perform();
        Random rand = new Random();
        int randomTaskIndex = 0;
        if(taskList.size() > 0) {
            if(taskList.size() == 1) {
                randomTaskIndex = 0;
            }else{
                randomTaskIndex = rand.nextInt(taskList.size());
            }
        }
        System.out.println(randomTaskIndex);
        if(randomTaskIndex < taskList.size() && randomTaskIndex >= 0) {
            WebElement clickEdit = taskList.get(randomTaskIndex).findElement(By.cssSelector("a"));
            clickEdit.click();
            taskTitleEditedValue = "This is the updated title " + Math.random();
            tasksAssigned.setTaskTitle(taskTitleEditedValue);
            taskTitle.clear();
            taskTitle.sendKeys(tasksAssigned.getTaskTitle());
            saveTaskButton.click();
            try {
                System.out.println("open dialog for validation again");
                System.out.println(taskForList.size());
                System.out.println(taskList.get(0).getText());
                action.moveToElement(taskForList.get(0)).click().build().perform();
            }catch (StaleElementReferenceException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(tasksAssigned.getTaskTitle());
        return tasksAssigned.getTaskTitle();

    }
    public boolean deleteTask() {
        incompleteTaskTotal = getTaskCount() ;
        System.out.println("previous incomplete task before delete" +incompleteTaskTotal);
        Actions action = new Actions(driver);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        action.moveToElement(taskForList.get(0)).click().perform();
        System.out.println(taskList.size());
        String taskTitleToDelete;
        Random rand = new Random();
        int randomTaskIndex = 0;
        if(taskList.size() > 0) {
            if(taskList.size() == 1) {
                randomTaskIndex = 0;
            }else{
                randomTaskIndex = rand.nextInt(taskList.size());
            }
        }
        System.out.println(randomTaskIndex);
        if(randomTaskIndex < taskList.size() && randomTaskIndex >= 0){
            System.out.println("in delete if condition");
            WebElement current = taskList.get(randomTaskIndex);
            taskTitleToDelete = current.findElement(By.cssSelector("div > div[class ^= 'm-tasks-st-input']")).getText();
            WebElement delete = current.findElement(By.cssSelector("div > span[data-original-title='Delete']"));
            delete.click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            confirmDeleteLink = current.findElement(By.cssSelector(" div > div[class ^= 'confirm-trash'] > a"));
            System.out.println(confirmDeleteLink.getText());
            action.moveToElement(confirmDeleteLink).click().perform();
            closeTaskDialogButton.click();
            //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            applySleep(2);
            currentIncompleteCount = getTaskCount();
            System.out.println("current task count after delete "+currentIncompleteCount);
            if (incompleteTaskTotal - 1 != currentIncompleteCount) {
                return false;
            }
        }
        return true;
    }

    private int getTaskCount() {
        WebElement taskCountElement = driver.findElement(
                By.cssSelector("div[class='js-task-list pb20'] > ul > li > div:nth-child(3) > p > span"));
        if(taskCountElement.getText().isEmpty()) {
            return 0;
        }
        return Integer.parseInt(taskCountElement.getText());
    }

    private void refreshTaskList() {
        taskList = driver.findElements(By.cssSelector("div#personal-task-list > div"));
        System.out.println("Refreshing taskList. New size: " + taskList.size());
    }
    public String getNewlyAddedTask() {
        return taskTitleValue;
    }
    public String getDueDateForTaskCompletion(){
        return dateExecution.getDueDate();
    }
    public int getIncompleteTaskTotal() {
        return incompleteTaskTotal;
    }

    public int getCurrentIncompleteCount() {
        return currentIncompleteCount;
    }

    public void openMenu(){
        pageMenu.goToMenu("Tasks");
    }
}

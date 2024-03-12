package org.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TasksAssigned {
//    protected WebElement currentTask;
//
//
//    @FindBy(css = "div.col-xs-12.m-tasks-add-st.pt0.margin-none > div > div.m-tasks-st-input.pb10.pt10.u-inline-block.margin-none")
//    protected WebElement taskTitle;
//    @FindBy(css = "div.col-xs-12.m-tasks-add-st.pt0.margin-none > a" )
//    protected WebElement editButton;
//    @FindBy(css = "div.col-xs-12.m-tasks-add-st.pt0.margin-none > span[data-original-title='Delete']")
//    protected WebElement deleteButton;
    protected String taskTitle;

    protected String dueDate;

    protected String notes;

    protected String assignTo;

    public  TasksAssigned() {
        taskTitle = "";
        dueDate = "";
        notes = "";
        assignTo = "";
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }
    public String getTaskTitle() {
        return taskTitle;
    }
    public void setAssignTo(String assignTo) {
        this.assignTo = assignTo;
    }
    public String getAssignTo() {
        return assignTo;
    }
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    public String getDueDate() {
        return dueDate;
    }
    public void setNotes(String notes) {
        this.notes = notes;

    }
    public String getNotes() {
        return notes;
    }
}

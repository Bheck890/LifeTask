package com.mobilegroup3.lifetaskhelper.task;

public class Task {

    /*
    Need to work on:

    Required Backend testing:
    When user presses on the info button,
    it should respond as it is going to select the information about the task

    Button for adding a new Task -
    Edit Task view that is after the task is created -
    View for when the task is preformed to update and record info -
    View for when pressed on the task it will show information about the prior events. -

    View for Contact us set up message from the app, to the email.
    Simplest view for the about page

    some custom art work and simple changing, for the menu bar options.


    Background and Future Issues:
    when saved have it in a format for the notification handler to push a notification.
    Date format for Notification Reminder
    Location coordinates way that can identify Location

    way to have app running in background checking for dates and location


    (After Action for data creation and edit, display is finished)
    Preformed action, Recording screen to record notes and set new reminder information.

    (After SQL File system created)
    Delete Task, Button in edit page.

     */


    private int id = 0;

    // Title of task
    private String Title = " ";

    //Since the task was performed
    private String dateSinceUpdate = "Never Started";

    //Note of the task Preformed
    private String taskNote = " ";

    //Day and time to remind the User (Running in background and remind when time matches)
    private String dateToRemindUser = " ";

    //Coordinates to check if user is close to area.
    private String locationReminder = " ";

    public Task(int id, String name){
        this.id = id;
        this.Title = name;
    }

    public Task(int id, String name, String update){
        this.id = id;
        this.Title = name;
        this.dateSinceUpdate = update;
    }

    @Override
    public String toString() {
        return "Task: " + getTitle();
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String name) {
        Title = name;
    }

    public String getDateSinceUpdate() {
        //when have sql data have it calculate days since last update.
        if(dateSinceUpdate.equals("Never Started"))
            return dateSinceUpdate;

        return "Preformed: " + dateSinceUpdate + " ago";
    }

    public int getId() {
        return id;
    }


    public String getTaskNote() {
        return taskNote;
    }

    public void setTaskNote(String taskNote) {
        this.taskNote = taskNote;
    }

    public String getDateToRemindUser() {
        return dateToRemindUser;
    }

    public void setDateToRemindUser(String dateToRemindUser) {
        this.dateToRemindUser = dateToRemindUser;
    }

    public String getLocationReminder() {
        return locationReminder;
    }

    public void setLocationReminder(String locationReminder) {
        this.locationReminder = locationReminder;
    }
}

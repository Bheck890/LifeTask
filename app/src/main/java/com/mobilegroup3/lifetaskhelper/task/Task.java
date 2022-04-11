package com.mobilegroup3.lifetaskhelper.task;

public class Task {

    /*
    Need to work on:

    Required Backend testing:!!@@@!!!!!
    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    Now its to Record the Tasks in SQL.
    Retrieve the list of Tasks into the task ListView
    and Retrieve the info in the Task. when press the Info button.
    then to be able to update and save the Task back to the Database when press save in settings.
    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    then its recording a new Task Event that the user Records the action was done.
    (Bunch of SQL stuff)
    then its Retrieving the times that event was recorded
    then I guess Setting up to check the users location when it matches a task location

    Then its basically done. :)

    Just a bit of SQL Left.

    Side Tasks:
    some custom art work and simple changing, for the menu bar options.
    Setup to Email an email in contact us: not Needed
    but if you want to make it email you or me would be cool


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

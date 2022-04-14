package com.mobilegroup3.lifetaskhelper.task;

import android.graphics.Color;

public class Task {

    /*
    Need to work on:
    Improvements on the Clients Data and Indicators
    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    Needed for the Group project needed to complete: (Complete Application)

    1. Simple use of the words from the assignment Requirements.
    - when the action is preformed the device location is recorded, and put into action database
    - if you can reverse the coordinates into an address then
        it adds the address into the Location Field with coordinates,
        else it would just be coordinates.

    2. Notification to remind the User of the specified reminder (if there is extra time and make it cool)
     - have the date and time set to remind the user, needs to make an action task as their reminder is activated,
         it just needs a system to notify the user.
         if the date matches the date that they specified
         **(mainly) if their location matches the address that they want to be notified at**
            way to have app running in background checking for dates and location


    //------------------------- (BHeck) I want to do,
            or you guys can do it if you got time (they are pretty simple)

    (A) Small Feature to add, might be hard?
     - See how you can edit the Task from the info page and have it updated to the Information pane,

    (B) UPDATE (Date and Location) inside the task database from a new action.
    - after the action is preformed the action is to replace a new reminder for the task
       based on what the task was prior
       [if date its a new date] else location then a new location notification spot
       (Simple Update from the edit update system)

    (C) (Date Comparison) Have the system compare the current date
     - update Task Message date info, to the last time the action was preformed for that task
        then to appear on the task list.

    Things to fix
    - When the task is made have it create an optional first action saying it got done now
        or to mark will be preformed later
    - In Edit mode if you switch between date and location 0:0 Appears in the time box
    //-------------------------

    Contact us page is broken?
    - why is it not displaying?

    ---------------------------------------------------------------
    Project Future things:

    Future Features
    - add a area where you can add info for inputting an action before the default current date.
        in case you did it yesterday but you forgot to track the action
    - if the user changed the task and then press back button, then there should be a notice
        saying the changes are not saved.

    Visible cosmetics
    - Message informing the user to add a new Task when there are no tasks
        (Have it first have the user start by adding a Task, only on start up of install.)
    - when there are no Actions preformed have it just show a screen with title and inform the user
        to preform a task so there can be some data.
    - if there is no (Description or Location) for the Action have the text on the screen shift
        up, or not be shown if its not needed.
    - Better Color choices?
    - Verify it looks good on a Device in dark mode.

    Possible Future Looks
    - Info page to have a graph of the date specified in month format
        to show how many times a month or week the action may occur
    - Update Reminder button to be Red if the date surpasses the current date,
        as it would be overdue

    Backend Bugs:
     - When you delete a task have it also delete the Actions that were also for that Task
     - during Development there was an issue of index out of bounds for adding 3 and removing 2
        then the index gets out of wack and needs to be inspected.
    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


    Side Tasks:
    some custom art work and simple changing, for the menu bar options.
    Setup to Email an email in contact us: not Needed
    but if you want to make it email you or me would be cool


    Background and Future Issues:
    when saved have it in a format for the notification handler to push a notification.
    Date format for Notification Reminder
    Location coordinates way that can identify Location



     */


    private int id = 0;

    // Title of task
    private String Title = "";

    //////////////////////////////
    //Since the task was performed
    private String dateSinceUpdate = "Never Started";

    //Note From the task Preformed
    private String taskNote = "";

    //Day and time to remind the User (Running in background and remind when time matches)
    private String dateToRemindUser = " ";

    //Coordinates to check if user is close to area.
    private String locationReminder = " ";
    //////////////////////////////

    private double latitude;
    private double longitude;
    private String address;
    private Boolean enable_address;
    private Boolean address_verified;
    private String date;
    private int hour;
    private int minute;


    //Basic
    public Task(int id, String name){
        this.id = id;
        this.Title = name;
    }

    //Set title and Date
    public Task(int id, String name, String update){
        this.id = id;
        this.Title = name;
        this.dateSinceUpdate = update;
    }

    //All SQL stuff
    public Task(int id,
                String title,
                double latitude,
                double longitude,
                String address,
                Boolean enable_address,
                Boolean address_verified,
                String date,
                int hour,
                int minute){
        this.id = id;
        this.Title = title;
        //this.dateSinceUpdate = update;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.enable_address = enable_address;
        this.address_verified = address_verified;
        this.date = date;
        this.hour = hour;
        this.minute = minute;
    }

    //All SQL stuff
    public void updateTask(int id,
                String title,
                double latitude,
                double longitude,
                String address,
                Boolean enable_address,
                Boolean address_verified,
                String date,
                int hour,
                int minute){
        this.id = id;
        this.Title = title;
        //this.dateSinceUpdate = update;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.enable_address = enable_address;
        this.address_verified = address_verified;
        this.date = date;
        this.hour = hour;
        this.minute = minute;
    }

    @Override
    public String toString() {

        return "\nID: " + getId() +
                "\nTask: " + getTitle() +
               "\nlatitude: " + getLatitude() +
                "\nlong: " + getLongitude() +
                "\naddress: " + getAddress() +
                "\nenableAddress: " + getEnable_address() +
                "\naddressVerified: " + getAddress_verified() +
                "\ndate: " + getDate() +
                "\nhour: " + getHour() +
                "\nmin: " +getMinute();
    }

    //-------------------------------------------------------------

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


    //-------------------------------------------------------------

    public String getTaskNote() {
        return taskNote;
    }

    public void setTaskNote(String taskNote) {
        this.taskNote = taskNote;
    }

    //-------------------------------------------------------------

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

    //-------------------------------------------------------------


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getEnable_address() {
        return enable_address;
    }

    public void setEnable_address(Boolean enable_address) {
        this.enable_address = enable_address;
    }

    public Boolean getAddress_verified() {
        return address_verified;
    }

    public void setAddress_verified(Boolean address_verified) {
        this.address_verified = address_verified;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getReminderColor(){
        if(getEnable_address()) //Location
            return (Color.GREEN);
        else if(!getDate().isEmpty()) //Date
            return (Color.BLUE);
        else // No Reminder
            return (Color.WHITE); //(View.INVISIBLE);
    }
}

package com.mobilegroup3.lifetaskhelper.task;

public class Actions {

    //Action ID
    private int actionID = 0;

    //Task ID
    private int taskID = 0;

    //Note From the task Preformed
    private String actionNote = "";

    //Date when user Preformed Action
    private final String date;

    //Data where the user was when Preformed
    private String Location;

    //Input Data from Database
    public Actions(int actionID,
                   int taskID,
                   String description,
                   String date,
                   String location
                   ) {
        this.actionID = actionID;
        this.taskID = taskID;
        this.actionNote = description;
        this.date = date;
        this.Location = location;
    }

    //Set Description and Date (Default New Task)
    public Actions( int taskID,
                   String description,
                   String date,
                   int hour,
                   int minute)
    {
        String date1 = date;
        //this.actionID = actionID;
        this.taskID = taskID;
        this.actionNote = description;

        if(hour > 12) {
            int hrS = hour;
            if (hour > 12)
                hrS -= 12;
            if(minute < 10){
                String minS = String.format("%02d", minute);
                date1 += " - " + hour + ":" + minS;
            }
            else{
                date1 += " - " + hour + ":" + minute;
            }
        }
        else if(minute < 10){
            String minS = String.format("%02d", minute); //adds a zero in front of the min number
            date1 += " - " + hour + ":" + minS;
            System.out.println("@@@@@@@@@@@@@-Action Time Under 10");
        }
        else {
            date1 += " - " + hour + ":" + minute;
            System.out.println("@@@@@@@@@@@@@-Action Time Default");
        }

        this.date = date1;
        this.Location = "N/A"; //Empty Location
    }

    //Set Action with Defaults and the Location Data (New task Created with location)
    public Actions(int taskID,
                   String description,
                   String date,
                   int hour,
                   int minute,
                   double latitude,
                   double longitude,
                   String address)
    {
        String date1 = date;
        //this.actionID = actionID;
        this.taskID = taskID;
        this.actionNote = description;

        if(minute < 10){
            String minS = String.format("%02d", minute); //adds a zero in front of the min number
            date1 += " - " + hour + ":" + minS;
            System.out.println("@@@@@@@@@@@@@-Action Time Under 10");
        }
        else {
            date1 += " - " + hour + ":" + minute;
            System.out.println("@@@@@@@@@@@@@-Action Time Default");
        }
        this.date = date1;
        this.Location = address + "(lat: " + latitude + ", long: " + longitude + ")";
    }

    @Override
    public String toString() { //List data that shows the day the task was done
        return "date: " + getDate();
    }

    public String ActionString() { //Debug to output the data of the object
        return "\nAction_ID: " + getActionID() +
                "\nTask_ID: " + getTaskID() +
                "\nactionNote: " + getActionNote() +
                "\ndate: " + getDate() +
                "\nLocation: " + getLocation();
    }

    public int getTaskID() {
        return taskID;
    }

    public int getActionID() {
        return actionID;
    }

    public String getActionNote() {
        return actionNote;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return Location;
    }



}

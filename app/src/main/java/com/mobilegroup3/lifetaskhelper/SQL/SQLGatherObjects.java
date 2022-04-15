package com.mobilegroup3.lifetaskhelper.SQL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.mobilegroup3.lifetaskhelper.task.ActionViewAdapter;
import com.mobilegroup3.lifetaskhelper.task.Actions;
import com.mobilegroup3.lifetaskhelper.task.Task;
import com.mobilegroup3.lifetaskhelper.task.TaskListView;

import java.util.ArrayList;

public class SQLGatherObjects {

    //Database number Used for Testing and a new Database if the old one has bad Information
    // or if the database had a bug and want to rest the values

    //Recommend to reset to 1 after you uninstall the application on the device that is being used.
    public static int DatabaseNumber = 1;

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    //Action Database Variables.
    public static ArrayList<Actions> action = new ArrayList<>();
    private static SQLiteOpenHelper actionDatabase;
    public static final String A_TB_NAME = ActionDatabaseHelper.TB_NAME; // the name of our Table

    //Database Systems that manipulate the Data.
    private SQLiteDatabase action_db;
    private Cursor DB_cursor;
    private ActionViewAdapter listAdapter = null;

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    //Task Database Variables.
    public static ArrayList<Task> tasks = new ArrayList<>();
    private static SQLiteOpenHelper taskDatabase;
    private static final String T_TB_NAME = TaskDatabaseHelper.TB_NAME; // the name of our Table
    private TaskListView listView;

    //Database Systems that manipulate the Data.
    private SQLiteDatabase db;
    private Cursor cursor;
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    public void gatherTaskObjects(Context context ) {

        //After Actions are Gathered, the Task list can show helpful Information
        if(tasks.isEmpty()) {
            taskDatabase = new TaskDatabaseHelper(context); //creates instance of task Database
            try {
                System.out.println("@@@@@@@@@@@@-Initiate Task Database");

                db = taskDatabase.getReadableDatabase();
                cursor = db.query (T_TB_NAME,
                        new String[] {
                                "_id",
                                "TITLE",
                                "LATITUDE",
                                "LONGITUDE",
                                "ADDRESS",
                                "ENABLE_ADDRESS",
                                "ADDRESS_VERIFIED",
                                "DATE",
                                "HOUR",
                                "MINUTE"
                        }, //
                        null, null, null, null, null);

                //Move to the first record in the Cursor
                if (cursor.moveToFirst()) {
                    while (!cursor.isAfterLast()) {
                        System.out.println("@@@@@@@@@@@@-Reading Task Database Line");
                        int _id = cursor.getInt(0);
                        String title = cursor.getString(1);
                        double latitude = cursor.getDouble(2);
                        double longitude = cursor.getInt(3);
                        String address = cursor.getString(4);
                        boolean enable_address = (cursor.getInt(5) == 1);
                        boolean address_verified = (cursor.getInt(6) == 1);
                        String date = cursor.getString(7);
                        int hour = cursor.getInt(8);
                        int min = cursor.getInt(9);

                        tasks.add(new Task(
                                _id,
                                title,
                                latitude,
                                longitude,
                                address,
                                enable_address,
                                address_verified,
                                date,
                                hour,
                                min
                        ));
                        cursor.moveToNext();
                    }
                }

                //taskAdapter = new TaskViewAdapter(context, tasks);

                /*
                //Output the tasks for Debugging verification
                for (Task Tas: tasks) {
                    System.out.println("@@@@@@@@@@@@-" + Tas);
                }
                 */

                //listView.setAdapter(taskAdapter);

                //Close Database Variables
                cursor.close();
                db.close();
                //System.out.println("@@@@@@@@@@@@- Closed Task Database Connection - @@@@@@@@@");


            } catch (SQLiteException e) {
                System.out.println("@@@@@@@@@@@@-Error Reading Task Database");
                Toast toast = Toast.makeText(context, "Task Database unavailable", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

    }

    private void gatherActionObjects() {



    }





}

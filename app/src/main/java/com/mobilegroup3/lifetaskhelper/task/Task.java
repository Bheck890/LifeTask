package com.mobilegroup3.lifetaskhelper.task;

public class Task {
    private String Name = " ";

    public Task(String name){
        this.Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "Task: " + getName();
    }
}

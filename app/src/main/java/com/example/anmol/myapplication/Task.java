package com.example.anmol.myapplication;

/**
 * Created by Anmol on 01-05-2016.
 */
public class Task {
    private String taskName;
    private int status;
    private String time;

    Task(){

    }

    Task(String taskName,String time,int status)
    {
        this.taskName=taskName;
        this.time=time;
        this.status=status;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}

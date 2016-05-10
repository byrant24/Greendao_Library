package com.example.anmol.myapplication;

/**
 * Created by Anmol on 01-05-2016.
 */
public class Task {
    private int id;
    private String taskName;
    private int status;
    private String time;
    private String email;

    Task(){

    }

    Task(int id,String taskName,String time,int status,String email)
    {
        this.id=id;
        this.taskName=taskName;
        this.time=time;
        this.status=status;
        this.email=email;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getEmail() {
        return email;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void  setEmail(String email)
    {
        this.email=email;
    }

    public int getStatus() {
        return status;
    }

    public void setId(int id)
    {
        this.id=id;
    }
    public int getId()
    {
        return id;
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

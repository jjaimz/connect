package com.connect.tasks;

public class Task {
    private int taskid;
    private String taskowner;
    private String task;
    private String attributes;
    private String executeon;
    private String status;

    public Task() {
    }
    public int getTaskid() {
        return taskid;
    }

    public void setTaskId(int taskid) {
        this.taskid = taskid;
    }

    public String getTaskowner() {
        return taskowner;
    }

    public void setTaskowner(String taskowner) {
        this.taskowner = taskowner;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getExecuteon() {
        return executeon;
    }

    public void setExecuteon(String executeon) {
        this.executeon = executeon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskid=" + taskid +
                ", taskowner='" + taskowner + '\'' +
                ", task='" + task + '\'' +
                ", attributes='" + attributes + '\'' +
                ", executeon='" + executeon + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

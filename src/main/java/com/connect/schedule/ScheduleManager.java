package com.connect.schedule;

import com.connect.tasks.Task;

import java.util.List;

public class ScheduleManager {
    private List<Task> tasks;

    public ScheduleManager(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}

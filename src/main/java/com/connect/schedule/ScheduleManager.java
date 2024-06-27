package com.connect.schedule;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import com.connect.LifecycleObject;
import com.google.inject.Inject;


public class ScheduleManager implements LifecycleObject {
    private List<Task> tasks;
    private ScheduledExecutorService executor;

    @Inject
    public ScheduleManager(List<Task> tasks) {
        this.tasks = tasks;
        this.executor = Executors.newScheduledThreadPool(this.tasks.size());
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        this.executor = Executors.newScheduledThreadPool(this.tasks.size());
    }

    @Override
    public void start() {
        System.out.println("Schedule Manager started");
        List<ScheduledFuture> futures = tasks.stream().map(task -> executor.schedule(task, 3, TimeUnit.SECONDS))
                .collect(Collectors.toList());

        for (ScheduledFuture future : futures) {
            try {
                System.out.println("Result: " + future.get());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stop() throws Exception {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
        System.out.println("Schedule Manager stopped");
    }
}

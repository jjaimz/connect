package com.connect.schedule;

import java.util.concurrent.Callable;

public class Task implements Callable<String> {

    public String call() throws Exception {
        return "task executed";
    }
}

package com.connect;

import com.connect.schedule.Task;
import com.google.inject.*;
import com.connect.schedule.ScheduleManager;

import java.util.ArrayList;
import java.util.List;


public class Main {
    private static Injector injector;

    public static void main(String[] args) throws Exception {

        try {
            injector = Guice.createInjector(new MainModule());

            var services = new ArrayList<LifecycleObject>();

            for (var serviceClass : List.of(ScheduleManager.class, NettyServer.class)) {
                var service = injector.getInstance(serviceClass);
                if (service != null) {
                    new Thread(() -> {
                        try {
                            service.start();
                        } catch (Exception e) {
                            System.out.println("Failed to start service: " + serviceClass.getSimpleName() + e);
                        }
                    }).start();
                    services.add(service);
                }
            }

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Stopping services");
                for (var service : services) {
                    try {
                        service.stop();
                    } catch (Exception e) {
                        System.out.println("Failed to stop service" + e);
                    }
                }
            }));

        } catch (Exception e) {
            System.out.println("Failed in main method " + e);
        }

    }

    public static class MainModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(ScheduleManager.class).asEagerSingleton();
            bind(NettyServer.class).toInstance(new NettyServer(1092));

            List<Task> tasks = new ArrayList<>();
            tasks.add(new Task());
            tasks.add(new Task());
            tasks.add(new Task());

            // Bind the list of tasks
            bind(new TypeLiteral<List<Task>>() {}).toInstance(tasks);
        }
    }
}


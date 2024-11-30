package com.mycompany.todo;

import org.objectweb.fractal.api.control.IllegalLifeCycleException;

public class TODO {

    public static void main(String[] args) {
        AppController appController = new AppController();
        try {
            appController.startFc();
        } catch (IllegalLifeCycleException e) {
            System.err.println("Error starting the application: " + e.getMessage());
        }
    }
}

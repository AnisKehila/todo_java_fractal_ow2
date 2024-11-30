package com.mycompany.todo;

import com.mycompany.todo.components.TaskManagerImpl;
import com.mycompany.todo.components.UIController; 
import javafx.application.Application;
import org.objectweb.fractal.api.control.IllegalLifeCycleException;
import org.objectweb.fractal.api.control.LifeCycleController;

public class AppController implements LifeCycleController {

    @Override
    public String getFcState() {
        return "Started";
    }
    @Override
    public void startFc() throws IllegalLifeCycleException {
        try {
            TaskManagerImpl taskManager = new TaskManagerImpl();
            UIController.setTaskManager(taskManager);
            Application.launch(UIController.class);  // Start the JavaFX application
        } catch (Exception e) {
            throw new IllegalLifeCycleException("Error starting the JavaFX application: " + e.getMessage());
        }
    }

    @Override
    public void stopFc() throws IllegalLifeCycleException {
        try {
            System.out.println("Stopping the application...");
        } catch (Exception e) {
            throw new IllegalLifeCycleException("Error stopping the application: " + e.getMessage());
        }
    }
}

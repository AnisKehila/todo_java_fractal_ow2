package com.mycompany.todo.components;

import com.mycompany.todo.interfaces.TaskManagerInterface;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.objectweb.fractal.api.Component;


public class UIController extends Application {
    private static TaskManagerInterface taskManager;

    public static void setTaskManager(TaskManagerInterface manager) {
        taskManager = manager;
    }

    @Override
    public void start(Stage primaryStage) {
        TextField taskInput = new TextField();
        taskInput.setPromptText("Enter a new task...");
        
        Button addButton = new Button("Add Task");
        
        ListView<Task> taskList = new ListView<>();
        taskList.getItems().setAll(taskManager.getTasks());

        addButton.setOnAction(event -> {
            String task = taskInput.getText();
            if (!task.isEmpty() && taskManager != null) {
                taskManager.addTask(task);
                taskInput.clear();
                taskList.getItems().setAll(taskManager.getTasks());
            }
        });

        taskList.setCellFactory((ListView<Task> param) -> new ListCell<Task>() {
            private CheckBox checkBox = new CheckBox();
            private Button deleteButton = new Button("Delete");

            @Override
            protected void updateItem(Task task, boolean empty) {
                super.updateItem(task, empty);
                
                if (empty || task == null) {
                    setGraphic(null);
                } else {
                    checkBox.setText(task.getTitle());
                    checkBox.setSelected(task.isCompleted());
                    
                    checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                        @Override
                        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                            taskManager.changeTaskStatus(task.getTitle(), newValue);
                        }
                    });
                    deleteButton.setOnAction(event -> {
                        taskManager.removeTask(task.getTitle());
                        taskList.getItems().setAll(taskManager.getTasks());
                    });

                    HBox hbox = new HBox(10, checkBox);
                    hbox.setAlignment(Pos.CENTER_LEFT);
                    HBox.setHgrow(checkBox, javafx.scene.layout.Priority.ALWAYS);
                    HBox deleteBox = new HBox(deleteButton);
                    deleteBox.setAlignment(Pos.CENTER_RIGHT);
                    hbox.getChildren().add(deleteBox);
                    setGraphic(hbox);
                }
            }
        });

        VBox layout = new VBox(10, taskInput, addButton, taskList);
        Scene scene = new Scene(layout, 400, 300);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("To-Do List");
        primaryStage.show();
    }
}

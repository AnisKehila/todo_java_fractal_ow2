package com.mycompany.todo.components;

import com.mycompany.todo.interfaces.TaskManagerInterface;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManagerImpl implements TaskManagerInterface {

    private List<Task> tasks;
    private static final String TASKS_FILE = "tasks.dat"; // File to store tasks

    // Constructor to initialize the tasks list and load tasks from file
    public TaskManagerImpl() {
        tasks = new ArrayList<>();
        loadTasks();  // Load tasks from the file on initialization
    }

    @Override
    public void addTask(String title) {
        Task task = new Task(title);
        tasks.add(task);
        saveTasks(); // Save tasks every time a new task is added
    }

    @Override
    public void removeTask(String title) {
        tasks.removeIf(task -> task.getTitle().equals(title));
        saveTasks();
    }

    @Override
    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public void changeTaskStatus(String title, boolean value) {
        for (Task task : tasks) {
            if (task.getTitle().equals(title)) {
                task.setCompleted(value);
                break;
            }
        }
        saveTasks();
    }

    private void saveTasks() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(TASKS_FILE))) {
            out.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load tasks from the file
    private void loadTasks() {
        File file = new File(TASKS_FILE);
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(TASKS_FILE))) {
                tasks = (List<Task>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

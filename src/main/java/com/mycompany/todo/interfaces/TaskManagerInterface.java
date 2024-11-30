package com.mycompany.todo.interfaces;
import com.mycompany.todo.components.Task;
public interface TaskManagerInterface {
    void addTask(String title);
    java.util.List<Task> getTasks();
    void removeTask(String title);    
    void changeTaskStatus(String title,  boolean value);

}
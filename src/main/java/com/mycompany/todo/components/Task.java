
package com.mycompany.todo.components;

import com.mycompany.todo.interfaces.TaskInterface;
import java.io.Serializable;
import org.objectweb.fractal.api.Component;
import org.objectweb.fractal.api.NoSuchInterfaceException;
import org.objectweb.fractal.api.Type;

public class Task  implements Serializable, TaskInterface, Component {
    private String title;
    private boolean completed;

    public Task(String title) {
        this.title = title;
        this.completed = false;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public boolean isCompleted() {
        return completed;
    }

    @Override
    public void setCompleted(boolean isCompleted) {
        this.completed = isCompleted;
    }

    @Override
    public Type getFcType() {
        return null;
    }

    @Override
    public Object[] getFcInterfaces() {
        return null;

    }

    @Override
    public Object getFcInterface(String string) throws NoSuchInterfaceException {
        return null;
    }
    
}

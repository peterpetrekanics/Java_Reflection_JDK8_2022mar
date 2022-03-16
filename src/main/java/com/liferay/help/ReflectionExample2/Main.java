package com.liferay.help.ReflectionExample2;

import java.lang.reflect.Method;

// ReflectionExample2
//
// How does reflection help in this example?
// Besides this current class, there is another class in this project:
// the Trainer class. We are going to create a new instance of the Trainer class,
// and we are going to give an initial value for the trainer name (John Doe).
//
// By using reflection, we can find the "setName" method and we can use it
// to change the trainer's name.
//
// This is the exact logic that we have used in this Liferay request:
// https://github.com/liferay-workflow/liferay-portal/pull/316/files

public class Main {
    public static void main(String[] args) {
        Trainer trainer = new Trainer("John Doe", 42);

        System.out.println("Trainer name before our change: " + trainer.getName());

        try {
            Method method = Trainer.class.getDeclaredMethod("setName", String.class);
            method.invoke(trainer, "Jack Doe");
        } catch (Exception e) {
            throw new IllegalStateException("Error while using reflection.");
        }

        System.out.println("Trainer name after our change: " + trainer.getName());
    }
}

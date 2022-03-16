package com.liferay.help.ReflectionExample1;

import java.lang.reflect.Field;

// ReflectionExample1
//
// How does reflection help in this example?
// Besides this current class, there is another class in this project:
// the Trainer class. We are going to create a new instance of the Trainer class,
// but we are going to give an invalid initial value for the trainer name (null).
//
// By using reflection, we can find the "name" attribute and we can modify it's
// value to a valid name.
//
// This is the exact logic that we have used in this Liferay request:
// https://github.com/liferay-core-infra/liferay-portal/pull/352/files

public class Main {
    public static void main(String[] args) {

        Trainer trainer = new Trainer(null, 42);

        try{
            Field[] fields = Trainer.class.getDeclaredFields();
            for (Field field : fields) {
                if (field.getType().isAssignableFrom(String.class)) {
                    field.setAccessible(true);
                    System.out.println("field.getName(): " + field.getName());

                    String name = (String) field.get(trainer);
                    System.out.println("Trainer name before our change: " + name);
                    if (name == null) {
                        field.set(trainer, "John Doe");
                    }
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException("Error while using reflection.");
        }

        System.out.println("Trainer name after our change: " + trainer.getName());
    }
}

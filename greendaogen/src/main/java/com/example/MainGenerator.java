package com.example;
import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class MainGenerator {

    private static final String PROJECT_DIR = System.getProperty("user.dir");
    public static void main(String args[]) throws Exception
    {
        Schema schema= new Schema(1,"com.example.anmol.myapplication");
        schema.enableKeepSectionsByDefault();
        addTables(schema);

        new DaoGenerator().generateAll(schema, PROJECT_DIR + "\\app\\src\\main\\java");

    }
    private static void addTables(Schema schema)
    {
        Entity task_todo = schema.addEntity("task_todo");
        task_todo.addIdProperty().notNull().autoincrement();
        task_todo.addStringProperty("name_task");
        task_todo.addStringProperty("time_task");
        task_todo.addIntProperty("task_status");

    }


}


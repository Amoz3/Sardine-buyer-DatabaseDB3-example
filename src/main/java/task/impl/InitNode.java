package task.impl;

import classes.Age;
import classes.Database;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import task.AbstractTask;

public class InitNode extends AbstractTask {
    @Override
    public boolean accept() {
        return !config.isHasInit();
    }

    @Override
    public int execute() {
        // i had to change the init method to return true on PSQLException for if statement to work, i may have edited the
        // Database.java on github to include that i may not have, just keep it in mind
        if (Database.init()) {
            // this creates 3 connections put could be done in 1 sql query, i should make a method that takes an array of strings.
            Database.updateCol("gp", String.valueOf(Inventory.count("Coins") + Bank.count("Coins")));
            Database.updateCol("task", config.getStatus());
            Database.updateCol("age", Age.getAge());
            Database.updateCol("sardines", String.valueOf(Inventory.count("Raw sardine")));
            config.setSardineCount(config.getSardineCount() + Inventory.count("Raw sardine"));
            log("database entry initialised");
            config.setHasInit(true);
        }
        return 1000;
    }
}

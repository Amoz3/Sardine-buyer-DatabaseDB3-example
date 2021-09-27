package task.impl;

import classes.Database;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.depositbox.DepositBox;
import org.dreambot.api.methods.walking.impl.Walking;
import task.AbstractTask;

public class DepositNode extends AbstractTask {
    @Override
    public boolean accept() {
        return Inventory.isFull();
    }

    @Override
    public int execute() {
        config.setStatus("Depositing");

        if (DEPOSITBOX.contains(getLocalPlayer())) {
            Database.updateCol("task", config.getStatus());
            if (DepositBox.openClosest()) {
                DepositBox.depositAll("Raw sardine");
            }
        } else { // walk to SHOP
            if (Walking.shouldWalk()) {
                if (!Walking.isRunEnabled()) {
                    if (Walking.getRunEnergy() > 30) {
                        Walking.toggleRun();
                    }
                }
                Walking.walk(DEPOSITBOX.getRandomTile());
            }
        }
        return 200;
    }
}

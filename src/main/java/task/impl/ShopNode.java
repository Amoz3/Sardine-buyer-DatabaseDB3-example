package task.impl;

import classes.Database;
import config.Config;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.Shop;
import org.dreambot.api.methods.walking.impl.Walking;
import task.AbstractTask;

public class ShopNode extends AbstractTask {
    @Override
    public boolean accept() {
        return (Inventory.contains("Coins") && !Inventory.isFull());
    }

    @Override
    public int execute() {
        config.setStatus("Shopping.");
        if (FISHSHOP.contains(getLocalPlayer())) {
            // add db update here as to not slow down walking
            Database.updateCol("task", config.getStatus());
            if (Shop.open("Gerrant")) {
                if (Shop.count("Raw sardine") > 27) {
                    Shop.purchaseFifty("Raw sardine"); // probably should check for enough coins or handle that in chat listener but once again this is a test script and im lazy
                    sleep(3000);

                    log("sardine count is currently: " + config.getSardineCount());
                    config.addToSardineCount(Inventory.count("Raw sardine")); // this method was made to fix an error that didnt exist but im not getting rid of it
                    log("sardine count set to: " + config.getSardineCount());
                    Database.updateCol("sardines", String.valueOf(config.getSardineCount()));
                    Database.updateCol("gp", String.valueOf(Inventory.count("Coins")));
                } else { // this should hop worlds but im too lazy to add that so its just gonna stop
                    log("stores out!");
                    scriptManager.stop();
                }
            }
        } else { // walk to SHOP
            if (Walking.shouldWalk()) {
                if (!Walking.isRunEnabled()) {
                    if (Walking.getRunEnergy() > 30) {
                        Walking.toggleRun();
                    }
                }
                Walking.walk(FISHSHOP.getRandomTile());
            }
        }
        return 100;
    }
}

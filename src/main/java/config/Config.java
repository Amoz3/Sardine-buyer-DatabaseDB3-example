package config;

import static org.dreambot.api.methods.MethodProvider.log;

/**
 * singleton class to hold global settings
 */

public class Config {
    public static final Config config = new Config();
    private String status = "loading script...";
    private boolean hasInit = false;
    private int sardineCount = 0;

    private Config(){} // hide constructor

    public static Config getConfig() {
        return config;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isHasInit() {
        return hasInit;
    }

    public void setHasInit(boolean hasInit) {
        this.hasInit = hasInit;
    }

    public int getSardineCount() {
        return sardineCount;
    }

    public void setSardineCount(int sardineCount) {
        this.sardineCount = sardineCount;
    }

    public void addToSardineCount(int sards) {
        this.sardineCount += sards;
    }
}

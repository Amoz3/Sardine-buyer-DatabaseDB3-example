package task;

import config.Config;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.script.ScriptManager;
import org.dreambot.api.script.TaskNode;


public abstract class AbstractTask extends TaskNode {
    // Gets an instance of Config object to use in TaskNode
    protected Config config = Config.getConfig();
    protected ScriptManager scriptManager = ScriptManager.getScriptManager();
    // areas
    protected Area FISHSHOP = new Area(3011, 3222, 3017, 3229);
    protected Area DEPOSITBOX = new Area(3048, 3234, 3041, 3237);

    @Override
    public abstract boolean accept();

    @Override
    public abstract int execute();
}

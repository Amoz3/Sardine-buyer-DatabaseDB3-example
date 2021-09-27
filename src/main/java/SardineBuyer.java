import config.Config;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.impl.TaskScript;
import task.impl.DepositNode;
import task.impl.InitNode;
import task.impl.ShopNode;

import java.awt.*;


@ScriptManifest(name = "Sardine buyer w/Database integration",
        description = "testing out my psql database logging methods!",
        author = "camalCase",
        version = 1.0, category = Category.MISC,
        image = "")


public class SardineBuyer extends TaskScript {
    Config config = Config.getConfig();
    @Override
    public void onStart() {
        addNodes( new InitNode(),
                new ShopNode(),
                new DepositNode());

    }

    @Override
    public void onPaint(Graphics g) {
        g.drawString("task: " + config.getStatus(), 10, 365);
        g.drawString("sard count: " + config.getSardineCount(), 10, 395);


    }
}


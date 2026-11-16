package augustus.modules.player;

import client.event.EventMotionUpdate;
import client.event.MotionUpdateListener;
import client.module.Category;
import client.module.Module;
import java.awt.Color;

public class AntiHunger extends Module implements MotionUpdateListener {
    public AntiHunger() {
        super("AntiHunger", "GegenHunger", new Color(66, 135, 245), Category.PLAYER);
    }

    @Override
    public void onEnable() {
        this.registerEvent(18);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onMotionUpdate(EventMotionUpdate event) {
        event.setState(false);
    }
}

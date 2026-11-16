package augustus.modules.misc;

import client.Client;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.util.MsgManager;
import client.util.RayTraceUtil;
import client.util.TimeHelper;
import java.awt.Color;

public class AquaMessenger extends Module implements TickListener {
    public boolean pendingSend = false;
    public TimeHelper timer = new TimeHelper();

    public AquaMessenger() {
        super("AquaMessenger", "WasserBote", new Color(52, 235, 195), Category.MISC);
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
        MsgManager.setActive(false);
    }

    @Override
    public void onPreDisable() {
        this.pendingSend = true;
    }

    @Override
    public void onPreTick() {
        if (this.timer.hasReached(10000L)) {
            if (!Client.getClient().getString7().isEmpty()) {
                if (this.pendingSend) {
                    RayTraceUtil.applyString("Attention: chat messages are unsecure!");
                    MsgManager.setActive(true);
                    this.timer.reset();
                    this.pendingSend = false;
                }
            }
        }
    }
}

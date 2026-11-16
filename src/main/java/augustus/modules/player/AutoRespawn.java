package augustus.modules.player;

import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.setting.ActionSetting;
import client.util.TimeHelper;
import java.awt.Color;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiScreen;

public class AutoRespawn extends Module implements TickListener {
    public TimeHelper respawnTimer = new TimeHelper();
    public TimeHelper timer2 = new TimeHelper();
    public ActionSetting settingDelay = new ActionSetting(this, "Delay", 1000.0, 2000.0, 0.0, 5000.0, 0);

    public AutoRespawn() {
        super("AutoRespawn", "AutomatischerRespawn", new Color(82, 222, 124), Category.PLAYER);
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onRunTick() {
        if (minecraft.currentScreen instanceof GuiGameOver) {
            minecraft.thePlayer.updateState4();
            minecraft.displayGuiScreen((GuiScreen)null);
            this.respawnTimer.reset();
            this.timer2.reset();
        }
    }
}

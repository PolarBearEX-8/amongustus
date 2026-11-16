package augustus.modules.fun;

import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.setting.ModeSetting;
import client.setting.RangeSetting;
import client.util.BooleanSupplier;
import client.util.TimeHelper;
import java.awt.Color;

public class DeathCommand extends Module implements TickListener {
    public RangeSetting settingDelay;
    public ModeSetting settingMessage;
    public TimeHelper timer;
    public boolean expanded2;
    public boolean enabled2;

    public DeathCommand() {
        super("DeathCommand", "Tötungsbefehl", new Color(156, 194, 255), Category.FUN);
        this.settingDelay = new RangeSetting(this, "Delay", 0.0, 0.0, 0.0, 2000.0, 0).getRangeSetting2();
        this.settingMessage = new ModeSetting(this, "Message", "/warp ffa", new String[0]).getModeSetting(() -> canExecute5());
        this.timer = new TimeHelper();
        this.expanded2 = false;
        this.enabled2 = false;
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
        this.expanded2 = false;
        this.enabled2 = false;
    }

    @Override
    public void onClientTick() {
        if (minecraft.thePlayer != null) {
            if (!(minecraft.thePlayer.getFloat2() > 0.0F)) {
                if (!this.enabled2) {
                    this.expanded2 = false;
                    this.timer.reset();
                }

                this.enabled2 = true;
            } else {
                this.enabled2 = false;
            }

            if (this.timer.hasReached((long)this.settingDelay.getIndexOffset2())) {
                if (!this.expanded2) {
                    minecraft.thePlayer.sendChatMessage(this.settingMessage.getSelected());
                    this.expanded2 = true;
                }
            }
        }
    }

    private static boolean canExecute5() {
        return false;
    }
}

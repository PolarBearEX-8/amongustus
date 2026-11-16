package augustus.modules.player;

import client.event.UpdateStateListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import java.awt.Color;
import net.minecraft.network.play.client.C03PacketPlayer;

public class AntiFire extends Module implements UpdateStateListener {
    public BooleanSetting settingOnlyOnGround = new BooleanSetting(this, "OnlyOnGround", false);
    public NumberSetting settingMaxPacketsPerTick = new NumberSetting(this, "MaxPacketsPerTick", 20.0, 0.0, 100.0, 0);

    public AntiFire() {
        super("AntiFire", "GegenFeuer", new Color(252, 186, 3), Category.PLAYER);
    }

    @Override
    public void onEnable() {
        this.registerEvent(43);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onPostUpdateState() {
        int index = (minecraft.thePlayer.canExecute2() ? 1 : 0);
        if ((double)index < this.settingMaxPacketsPerTick.getValue()) {
            minecraft.thePlayer.netHandlerPlayClient.handlePacket2(new C03PacketPlayer(minecraft.thePlayer.onGround));
            index++;
        }
    }
}

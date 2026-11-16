package augustus.modules.player;

import client.event.EventPacket;
import client.event.PacketListener;
import client.module.Category;
import client.module.Module;
import client.setting.MultiSelectSetting;
import client.setting.NoScreenClosingScreenType;
import java.awt.Color;
import net.minecraft.network.play.server.S2EPacketCloseWindow;

public class NoScreenClosing extends Module implements PacketListener {
    public MultiSelectSetting settingScreensToNoClose = new MultiSelectSetting(this, "ScreensToNoClose", NoScreenClosingScreenType.values());

    public NoScreenClosing() {
        super("NoScreenClosing", "KeinBildschirmSchließen", new Color(255, 153, 64), Category.PLAYER);
    }

    @Override
    public void onEnable() {
        this.registerEvent(26);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        if (packet.getPacket() instanceof S2EPacketCloseWindow) {
            packet.cancel();
        }
    }
}

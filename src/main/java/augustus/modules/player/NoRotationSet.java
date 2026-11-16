package augustus.modules.player;

import client.Client;
import client.module.Category;
import client.module.Module;
import client.setting.ModeSetting;
import java.awt.Color;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;

public class NoRotationSet extends Module {
    public ModeSetting settingMode = new ModeSetting(this, "Mode", "Default", new String[]{"Default", "Test"});

    public NoRotationSet() {
        super("NoRotationSet", "KeineRotationssetzung", new Color(26, 108, 135), Category.PLAYER);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    public static boolean checkS08PacketPlayerPosLook(S08PacketPlayerPosLook s08PacketPlayerPosLook) {
        return Client.getClient().getModuleManager().noRotationSet.settingMode.getSelected().equalsIgnoreCase("Test");
    }
}

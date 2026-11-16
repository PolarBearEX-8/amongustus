package augustus.modules.render;

import client.module.Category;
import client.module.Module;
import client.network.NetworkPlayerInfo;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.SettingContainer;
import java.awt.Color;

public class Protector extends Module {
    public BooleanSetting settingTime = new BooleanSetting(this, "Time", true);
    public ModeSetting settingNameGeneration = new ModeSetting(this, "NameGeneration", "List", new String[]{"List", "Esound", "Generated"});
    public int tickDelay3;
    public int tickDelay;
    public int tickDelay2;
    public int tickDelay1;
    public static int tickDelay4;

    public Protector() {
        super("Protector", "Versicherung", Color.yellow, Category.RENDER);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
        if (minecraft.thePlayer != null) {
            for (NetworkPlayerInfo networkPlayerInfo : ((Iterable<NetworkPlayerInfo>) minecraft.getNetHandlerPlayClient().getCollection())) {
                networkPlayerInfo.state6 = false;
                networkPlayerInfo.updateState();
            }
        }
    }

    @Override
    public void onPreDisable() {
        this.tickDelay3 = SettingContainer.settingProvider.getInt2(-1000, 1000);
        this.tickDelay = SettingContainer.settingProvider.getInt2(-1000, 1000);
        this.tickDelay2 = SettingContainer.settingProvider.getInt2(10, 60);
        this.tickDelay1 = SettingContainer.settingProvider.getInt2(0, 11);
        if (minecraft.thePlayer != null) {
            for (NetworkPlayerInfo networkPlayerInfo : ((Iterable<NetworkPlayerInfo>) minecraft.getNetHandlerPlayClient().getCollection())) {
                networkPlayerInfo.state6 = false;
                networkPlayerInfo.updateState();
            }
        }
    }

    public int getIndex() {
        return this.tickDelay1;
    }

    public static void processAction(int index) {
        tickDelay4 = index;
    }

    public int getIndex2() {
        return this.tickDelay;
    }

    public static int onWorldTick2() {
        return tickDelay4;
    }

    public static int getInt2() {
        return onWorldTick2() == 0 ? 72 : 0;
    }

    public int getIndex3() {
        return this.tickDelay2;
    }

    public int getIndex4() {
        return this.tickDelay3;
    }
}

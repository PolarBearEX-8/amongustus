package augustus.modules.render;

import client.event.EventChunkLoad;
import client.event.EventChunkLoadCallback;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.util.BooleanSupplier;
import client.util.ConsumerTask;
import java.awt.Color;
import net.minecraft.client.Minecraft;

public class FullBright extends Module implements EventChunkLoadCallback {
    public ModeSetting settingMode;
    public BooleanSetting settingOther;
    public float motionMultiplier;

    public FullBright() {
        super("FullBright", "VolleHelligkeit", new Color(188, 213, 25), Category.RENDER);
        this.settingMode = new ModeSetting(this, "Mode", "NightVision", new String[]{"NightVision", "Gamma"}).getModeSetting(() -> this.onModeAction());
        BooleanSetting booleanSetting = new BooleanSetting(this, "Other", true);
        this.settingOther = (BooleanSetting)booleanSetting.getSetting(() -> this.isModeVisible());
        this.motionMultiplier = 1.0F;
    }

    @Override
    public void onEnable() {
        this.registerEvent(22);
    }

    @Override
    public void onDisable() {
        minecraft.gameSettings.scaleFactor100 = Math.min(this.motionMultiplier, 1.0F);
    }

    @Override
    public void onPreDisable() {
        this.motionMultiplier = minecraft.gameSettings.scaleFactor100;
        if (this.settingMode.getSelected().equals("Gamma")) {
            minecraft.gameSettings.scaleFactor100 = 10.0F;
        }
    }

    @Override
    public void onChunkLoad(EventChunkLoad event) {
        if (!this.settingOther.isEnabled()) {
            event.setActive(true);
        }
    }

    @Override
    public void onChunkLoad2(EventChunkLoad event) {
        if (!this.settingMode.getSelected().equals("Gamma")) {
            event.setActive(true);
        }
    }

    private void onModeAction() {
        if (this.isToggled()) {
            if (this.settingMode.getSelected().equals("Gamma")) {
                minecraft.gameSettings.scaleFactor100 = 10.0F;
            } else {
                if (minecraft.gameSettings.scaleFactor100 > 1.0F) {
                    minecraft.gameSettings.scaleFactor100 = Math.min(this.motionMultiplier, 1.0F);
                }
            }
        } else {
            if (minecraft.gameSettings.scaleFactor100 > 1.0F) {
                minecraft.gameSettings.scaleFactor100 = Math.min(this.motionMultiplier, 1.0F);
            }
        }
    }

    private boolean isModeVisible() {
        return this.settingMode.getSelected().equals("NightVision");
    }
}

package augustus.modules.render;

import client.Client;
import client.event.EventJump;
import client.event.EventJumpCallback;
import client.event.EventPacket;
import client.event.EventTimeUpdate;
import client.event.EventTimeUpdateCallback;
import client.event.PacketListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.NumberSetting;
import client.util.BooleanSupplier;
import client.util.ConsumerTask;
import client.util.RayTraceUtil;
import java.awt.Color;
import net.minecraft.network.play.server.S03PacketTimeUpdate;

public class Ambiance extends Module implements PacketListener, EventJumpCallback, EventTimeUpdateCallback {
    public NumberSetting settingTime = new NumberSetting(this, "Time", 1900.0, 0.0, 24000.0, 0);
    public NumberSetting settingTimeSpeed = new NumberSetting(this, "TimeSpeed", 0.0, 0.0, 100.0, 1);
    public NumberSetting settingRain = new NumberSetting(this, "Rain", 0.0, 0.0, 1.0, 2);
    public BooleanSetting settingSnow = new BooleanSetting(this, "Snow", false);
    public NumberSetting settingThunder = new NumberSetting(this, "Thunder", 0.0, 0.0, 1.0, 2);
    public BooleanSetting settingCustomWorldColor;
    public ColorSetting settingWorldColor;
    public BooleanSetting settingCustomFogColor;
    public ColorSetting settingFogColor;
    public BooleanSetting settingCustomFogDistance;
    public NumberSetting settingFogDistance;
    public long timestampMillis;
    public float motionMultiplier1;
    public float motionMultiplier;

    public Ambiance() {
        super("Ambiance", "Ambiente", new Color(224, 93, 40), Category.RENDER);
        BooleanSetting booleanSetting = new BooleanSetting(this, "CustomWorldColor", false);
        this.settingCustomWorldColor = (BooleanSetting)booleanSetting.getSetting(() -> this.onThunderAction());
        Client client = Client.getClient();
        ColorSetting colorSetting = new ColorSetting(this, "WorldColor", client.getColor());
        this.settingWorldColor = (ColorSetting)colorSetting.getSetting(() -> this.settingCustomWorldColor.isEnabled());
        this.settingCustomFogColor = new BooleanSetting(this, "CustomFogColor", false);
        Client client2 = Client.getClient();
        ColorSetting colorSetting2 = new ColorSetting(this, "FogColor", client2.getColor());
        this.settingFogColor = (ColorSetting)colorSetting2.getSetting(() -> this.settingCustomFogColor.isEnabled());
        this.settingCustomFogDistance = new BooleanSetting(this, "CustomFogDistance", false);
        this.settingFogDistance = new NumberSetting(this, "FogDistance", 192.0, 0.0, 1000.0, 0).getNumberSetting(() -> this.settingCustomFogDistance.isEnabled());
        this.motionMultiplier1 = 0.0F;
        this.motionMultiplier = 0.0F;
    }

    @Override
    public void onEnable() {
        this.registerEvent(26);
        this.registerEvent(44);
        this.registerEvent(45);
    }

    @Override
    public void onDisable() {
        this.timestampMillis = 0L;
        if (minecraft.theWorld != null) {
            minecraft.theWorld.updateFloat(this.motionMultiplier1);
            minecraft.theWorld.updateFloat2(this.motionMultiplier);
        }
    }

    @Override
    public void onPreDisable() {
        this.timestampMillis = 0L;
        if (minecraft.theWorld != null) {
            this.motionMultiplier1 = minecraft.theWorld.getFloat6(1.0F);
            this.motionMultiplier = minecraft.theWorld.getFloat7(1.0F);
        }
    }

    @Override
    public void onJump(EventJump event) {
        if (this.settingCustomWorldColor.isEnabled()) {
            this.settingWorldColor.getInt12(0);
            event.processAction2((float)this.settingWorldColor.getInt7());
            event.processAction((float)this.settingWorldColor.getInt10());
            event.processAction3((float)this.settingWorldColor.getInt13());
        }
    }

    @Override
    public void onTimeUpdate(EventTimeUpdate event) {
        long time2;
        if (!(this.settingTimeSpeed.getValue() > 0.0)) {
            time2 = 0L;
        } else {
            double timestampMillis2 = (double)this.timestampMillis;
            double value = this.settingTimeSpeed.getValue();
            time2 = (long)(timestampMillis2 + value);
        }

        this.timestampMillis = time2;
        event.updateState((long)(this.settingTime.getValue() + (double)this.timestampMillis));
        if (this.timestampMillis > 24000L) {
            this.timestampMillis = 0L;
        }
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        if (packet.getPacket() instanceof S03PacketTimeUpdate) {
            packet.cancel();
        }
    }

    @Override
    public void onJump2(EventJump event) {
        if (this.settingCustomFogColor.isEnabled()) {
            this.settingFogColor.getInt12(0);
            event.processAction2((float)this.settingFogColor.getInt7());
            event.processAction((float)this.settingFogColor.getInt10());
            event.processAction3((float)this.settingFogColor.getInt13());
        }
    }

    private void onThunderAction() {
        if (Client.getClient().getModuleManager().fullBright.isToggled()) {
            if (Client.getClient().getModuleManager().fullBright.settingMode.getSelected().equals("Gamma")) {
                if (this.settingCustomWorldColor.isEnabled()) {
                    RayTraceUtil.applyString("Disable gamma mode in FullBright module");
                }
            }
        }
    }
}

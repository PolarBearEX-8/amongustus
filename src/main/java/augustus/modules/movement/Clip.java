package augustus.modules.movement;

import client.event.EventPacket;
import client.event.PacketListener;
import client.event.RenderListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.util.BooleanSupplier;
import client.util.Packet;
import client.util.TimeHelper;
import java.awt.Color;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;

public class Clip extends Module implements RenderListener, PacketListener {
    public ModeSetting settingMode;
    public BooleanSetting settingAutoDisable;
    public NumberSetting settingTime;
    public TimeHelper timer;

    public Clip() {
        super("Clip", "Clip", new Color(135, 168, 50), Category.MOVEMENT);
        String[] textArray = new String[3];
        textArray[0] = "Intave1";
        textArray[1] = "Intave2";
        textArray[2] = "IntaveInstant";
        this.settingMode = new ModeSetting(this, "Mode", "IntaveInstant", textArray);
        this.settingAutoDisable = new BooleanSetting(this, "AutoDisable", true);
        this.settingTime = new NumberSetting(this, "Time", 50.0, 0.0, 1000.0, 0).getNumberSetting(() -> this.settingAutoDisable.isEnabled());
        this.timer = new TimeHelper();
    }

    @Override
    public void onEnable() {
        this.registerEvent(32);
        this.registerEvent(26);
    }

    @Override
    public void onDisable() {
        if (this.settingMode.getSelected().equals("IntaveInstant")) {
            minecraft.getTimer().scaleFactor2 = 1.0F;
        }
    }

    @Override
    public void onPreDisable() {
        minecraft.thePlayer.motionY = 0.9;
        this.timer.reset();
        if (this.settingMode.getSelected().equals("IntaveInstant")) {
            minecraft.getTimer().scaleFactor2 = 100.0F;
        }
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (packet2 instanceof S08PacketPlayerPosLook && this.settingMode.getSelected().equals("IntaveInstant")) {
            packet.cancel();
        }

        if (packet2 instanceof S08PacketPlayerPosLook && this.settingMode.getSelected().equals("Intave1")) {
            ((S08PacketPlayerPosLook)packet2).setYawRotation(1.0F);
        }

        if (packet2 instanceof S32PacketConfirmTransaction) {
            if (this.settingMode.getSelected().equals("Intave2") || this.settingMode.getSelected().equals("IntaveInstant")) {
                packet.cancel();
            }
        }
    }

    @Override
    public void onRender3D(float partialTicks) {
        if (this.settingAutoDisable.isEnabled()) {
            this.timer.reset();
            this.setActive(false);
        }
    }
}

package augustus.modules.player;

import client.ClientGlobals;
import client.event.EventPacket;
import client.event.PacketListener;
import client.event.TickListener;
import client.event.UpdateStateListener;
import client.module.Category;
import client.module.Module;
import client.rotation.Rotation;
import client.rotation.RotationManager;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.util.BooleanSupplier;
import client.util.Packet;
import client.util.SimulatedPlayer;
import client.util.TimeHelper;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Regen extends Module implements UpdateStateListener, PacketListener, TickListener {
    public ModeSetting settingMode;
    public NumberSetting settingHealth;
    public NumberSetting settingMinHunger;
    public NumberSetting settingPackets;
    public BooleanSetting settingGroundCheck;
    public BooleanSetting settingStopSendPacketsIfHpReached;
    public NumberSetting settingMinDelay;
    public BooleanSetting settingNotWhileKillAura;
    public BooleanSetting settingNotWhileScaffold;
    public TimeHelper timer;

    public Regen() {
        super("Regen", "Regeneration", new Color(224, 111, 49), Category.PLAYER);
        String[] textArray = new String[4];
        textArray[0] = "Default";
        textArray[1] = "Legit";
        textArray[2] = "Grim-1.17";
        textArray[3] = "/heal";
        this.settingMode = new ModeSetting(this, "Mode", "Default", textArray);
        this.settingHealth = new NumberSetting(this, "Health", 20.0, 0.0, 20.0, 0);
        this.settingMinHunger = new NumberSetting(this, "MinHunger", 5.0, 0.0, 20.0, 0).getNumberSetting(() -> this.isMinHungerVisible());
        this.settingPackets = new NumberSetting(this, "Packets", 100.0, 0.0, 200.0, 0).getNumberSetting(() -> this.isPacketsVisible());
        this.settingGroundCheck = (BooleanSetting)new BooleanSetting(this, "GroundCheck", false).getSetting(() -> this.isPacketsVisible3());
        this.settingStopSendPacketsIfHpReached = (BooleanSetting)new BooleanSetting(this, "StopSendPacketsIfHpReached", false).getSetting(() -> this.isPacketsVisible2());
        this.settingMinDelay = new NumberSetting(this, "MinDelay", 30000.0, 0.0, 60000.0, 0).getNumberSetting(() -> this.isMinDelayVisible());
        this.settingNotWhileKillAura = new BooleanSetting(this, "NotWhileKillAura", false);
        this.settingNotWhileScaffold = new BooleanSetting(this, "NotWhileScaffold", true);
        this.timer = new TimeHelper();
    }

    @Override
    public void onEnable() {
        this.registerEvent(43);
        this.registerEvent(26);
        this.registerEvent(40, 10000);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onPacketSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (minecraft.theWorld != null) {
            int index = (this.settingMode.getSelected().equals("Grim-1.17") ? 1 : 0);
            double offset = (double)index;
            if (offset < this.settingPackets.getValue()) {
                if (this.checkCondition()) {
                    minecraft.thePlayer.netHandlerPlayClient.handlePacket2(packet2);
                }

                index++;
            }
        }
    }

    @Override
    public void onPreUpdateState() {
        String selected = this.settingMode.getSelected();
        this.applyString4(selected);
        byte state = ((byte)(this.checkCondition() ? 1 : 0));
        if ((double)state < this.settingPackets.getValue()) {
            int index = (this.checkCondition() ? 1 : 0);
            index++;
        }
    }

    @Override
    public void onPreTick() {
        if (this.settingMode.getSelected().equals("Grim-1.17")) {
            RotationManager rotationManager = ClientGlobals.rotationManager;
            float yaw = ClientGlobals.rotationManager.getRotation3().yaw;
            float pitch = ClientGlobals.rotationManager.getRotation3().pitch;
            float value = pitch - 0.0F;
            RotationManager rotationManager2 = ClientGlobals.rotationManager;
            RotationManager rotationManager3 = ClientGlobals.rotationManager;
            Rotation rotation8 = SimulatedPlayer.getRotation8(yaw, value, rotationManager2.getRotation3().pitch, rotationManager3.getRotation3().pitch);
            rotationManager.applyRotation2(rotation8, 1, 1898960900, this);
        }
    }

    @Override
    public void onPostTick() {
        if (minecraft.thePlayer != null) {
            if (this.settingMode.getSelected().equals("/heal")) {
                return;
            }

            minecraft.thePlayer.sendChatMessage("/heal");
            this.timer.reset();
        }
    }

    private boolean isMinDelayVisible() {
        return this.settingMode.getSelected().equals("/heal");
    }

    private int getInt2() {
        String selected = this.settingMode.getSelected();
switch (selected) {
            case "Default":
                minecraft.thePlayer.netHandlerPlayClient.handlePacket2(new C03PacketPlayer(true));
                break;
            case "Legit":
                minecraft.thePlayer.setSprinting(false);
        }

        return 0;
    }

    private boolean isPacketsVisible() {
        return this.settingMode.getSelected().equals("/heal");
    }

    private boolean isPacketsVisible2() {
        return this.settingMode.getSelected().equals("/heal");
    }

    private boolean checkCondition() {
        double offset;
        return (
                (offset = (double)minecraft.thePlayer.getFoodLevelManager().getIndex() - this.settingMinHunger.getValue()) == 0.0
                    ? 0
                    : (offset < 0.0 ? -1 : 1)
            )
            != 0;
    }

    private boolean isPacketsVisible3() {
        return this.settingMode.getSelected().equals("/heal");
    }

    private boolean isMinHungerVisible() {
        return this.settingMode.getSelected().equals("/heal");
    }
}

package augustus.modules.player;

import client.event.EventMove;
import client.event.EventMoveEntity;
import client.event.EventPacket;
import client.event.EventStateUpdate;
import client.event.MoveEntityListener;
import client.event.MoveListener;
import client.event.PacketListener;
import client.event.UpdateStateListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.Setting;
import client.util.BooleanSupplier;
import client.util.Packet;
import java.awt.Color;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C05PacketPlayerLook;

public class AirStuck extends Module implements MoveEntityListener, MoveListener, UpdateStateListener, PacketListener {
    public ModeSetting settingMode;
    public BooleanSetting settingSneak;
    public BooleanSetting settingOnlyInAir;
    public static boolean activeState;

    public AirStuck() {
        super("AirStuck", "LuftStecken", new Color(78, 186, 112), Category.PLAYER);
        String[] textArray = new String[4];
        textArray[0] = "NoRotate";
        textArray[1] = "CanRotate";
        textArray[2] = "Intave";
        textArray[3] = "NewNoRotate";
        this.settingMode = new ModeSetting(this, "Mode", "NoRotate", textArray);
        this.settingSneak = new BooleanSetting(this, "Sneak", true);
        this.settingOnlyInAir = (BooleanSetting)new BooleanSetting(this, "OnlyInAir", true).getSetting(() -> this.isModeVisible());
    }

    @Override
    public void onEnable() {
        this.registerEvent(20);
        this.registerEvent(21);
        this.registerEvent(43);
        this.registerEvent(26);
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
        if (packet2 instanceof C03PacketPlayer) {
            if (!minecraft.thePlayer.onGround || !this.settingOnlyInAir.isEnabled()) {
                String selected = this.settingMode.getSelected();
                switch (selected) {
                    case "NoRotate":
                        packet.cancel();
                        return;
                    case "CanRotate":
                        if (packet2 instanceof C05PacketPlayerLook) {
                            return;
                        }

                        packet.cancel();
                        return;
                }
            }
        }
    }

    @Override
    public void onUpdateState(EventStateUpdate event) {
        this.applyString4(this.settingMode.getSelected());
        minecraft.thePlayer.motionY = 0.0;
        minecraft.thePlayer.motionX = 0.0;
        minecraft.thePlayer.motionZ = 0.0;
        if (this.settingMode.getSelected().equals("NewNoRotate")) {
            if (!minecraft.thePlayer.onGround || !this.settingOnlyInAir.isEnabled()) {
                event.cancel();
            }
        }
    }

    @Override
    public void onPostMoveEntity(EventMoveEntity event) {
        event.setCoordOffset2(0.0);
        event.setCoordOffset1(0.0);
        event.setCoordOffset(0.0);
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        packet.getPacket();
        if (minecraft.thePlayer == null) {
            this.setActive(false);
        }

        if (this.settingMode.getSelected().equals("Intave")) {
            packet.cancel();
        }
    }

    @Override
    public void onMove(EventMove event) {
        if (this.settingSneak.isEnabled()) {
            event.setState4(true);
        }
    }

    private boolean isModeVisible() {
        return this.settingMode.getSelected().equals("Intave");
    }

    public static void setActive4(boolean enabled) {
        activeState = enabled;
    }

    public static boolean canExecute5() {
        return activeState;
    }

    public static boolean canExecute6() {
        return !canExecute5();
    }
}

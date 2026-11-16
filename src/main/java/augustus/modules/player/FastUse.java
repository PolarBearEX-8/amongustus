package augustus.modules.player;

import client.ClientGlobals;
import client.event.EventPacket;
import client.event.EventSlowDown;
import client.event.PacketListener;
import client.event.SlowDownListener;
import client.event.TickListener;
import client.event.UpdateStateListener;
import client.module.Category;
import client.module.Module;
import client.rotation.Rotation;
import client.rotation.RotationManager;
import client.setting.BooleanSetting;
import client.setting.FastUseFoodType;
import client.setting.ModeSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.util.BooleanSupplier;
import client.util.MoveUtil;
import client.util.Packet;
import client.util.SimulatedPlayer;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemMilkBucket;
import net.minecraft.item.ItemPotion;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C06PacketPlayerPosLook;
import net.minecraft.util.MathHelper;

public class FastUse extends Module implements UpdateStateListener, SlowDownListener, PacketListener, TickListener {
    public ModeSetting settingMode;
    public ModeSetting settingPacketMode;
    public BooleanSetting settingOnlyWhileMoving;
    public NumberSetting settingPacketLoops;
    public NumberSetting settingPackets;
    public NumberSetting settingGrimPackets;
    public NumberSetting settingNewPackets;
    public NumberSetting settingTimePackets;
    public BooleanSetting settingOnlyAtFullLife;
    public BooleanSetting settingOnlyOnGround;
    public MultiSelectSetting settingItems;
    public int keyCode2;

    public FastUse() {
        super("FastUse", "SchnellBenutzen", new Color(173, 121, 81), Category.PLAYER);
        String[] textArray = new String[4];
        textArray[0] = "Packet";
        textArray[1] = "TimeManipulation";
        textArray[2] = "NewPacket";
        textArray[3] = "Grim-1.17";
        this.settingMode = new ModeSetting(this, "Mode", "Packet", textArray);
        String[] textArray2 = new String[2];
        textArray2[0] = "Default";
        textArray2[1] = "Legit";
        this.settingPacketMode = new ModeSetting(this, "PacketMode", "Default", textArray2).getModeSetting(() -> this.isPacketModeVisible());
        this.settingOnlyWhileMoving = new BooleanSetting(this, "OnlyWhileMoving", false);
        this.settingPacketLoops = new NumberSetting(this, "PacketLoops", 1.0, 1.0, 10.0, 0).getNumberSetting(() -> this.isPacketLoopsVisible());
        this.settingPackets = new NumberSetting(this, "Packets", 20.0, 2.0, 31.0, 0).getNumberSetting(() -> this.isPacketsVisible());
        this.settingGrimPackets = new NumberSetting(this, "GrimPackets", 20.0, 2.0, 31.0, 0).getNumberSetting("Packets").getNumberSetting(() -> this.isGrimPacketsVisible());
        this.settingNewPackets = new NumberSetting(this, "NewPackets", 20.0, 2.0, 31.0, 0).getNumberSetting("Packets").getNumberSetting(() -> this.isNewPacketsVisible());
        this.settingTimePackets = new NumberSetting(this, "TimePackets", 20.0, 2.0, 31.0, 0).getNumberSetting("Packets").getNumberSetting(() -> this.isTimePacketsVisible());
        this.settingOnlyAtFullLife = new BooleanSetting(this, "OnlyAtFullLife", false);
        this.settingOnlyOnGround = new BooleanSetting(this, "OnlyOnGround", false);
        FastUseFoodType[] fastUseFoodTypeArray = FastUseFoodType.values();
        this.settingItems = new MultiSelectSetting(this, "Items", fastUseFoodTypeArray);
        this.keyCode2 = 0;
    }

    @Override
    public void onEnable() {
        this.registerEvent(43);
        this.registerEvent(41);
        this.registerEvent(26);
        this.registerEvent(40, 10001);
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
        if (this.settingMode.getSelected().equals("Grim-1.17")) {
            if (this.canExecute5()) {
                if (packet2 instanceof C06PacketPlayerPosLook) {
                    int grimPackets = (int)this.settingGrimPackets.getValue();
                    if (minecraft.thePlayer.getEntityId2() < grimPackets) {
                        grimPackets = minecraft.thePlayer.getEntityId2();
                    }

                    minecraft.thePlayer.setEntityId2(minecraft.thePlayer.getEntityId2() - grimPackets);

                    for (int i = 0; i < grimPackets; i++) {
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket2(packet2);
                    }
                }
            }
        }
    }

    @Override
    public void onSlowDown(EventSlowDown event) {
    }

    @Override
    public void onCustomSlowDown(EventSlowDown event) {
        byte state = ((byte)(this.settingMode.getSelected().equalsIgnoreCase("TimeManipulation") ? 1 : 0));
        if (minecraft.thePlayer.getInt10() == 32 - state) {
            event.processAction(state);
        }
    }

    @Override
    public void onPostUpdateState() {
        if (!this.canExecute5()) {
            this.keyCode2 = 0;
        } else {
            System.out.println(minecraft.thePlayer.getEntityId2());
            String selected = this.settingMode.getSelected();
switch (selected) {
            case "Packet":
                int int124 = MathHelper.getInt12((int)this.settingPackets.getValue(), 2, 31);
                boolean enabled = false;
                double int10 = (double)minecraft.thePlayer.getInt10();
                double offset = (double)int124;
                if (!(int10 > 32.0 - offset * this.settingPacketLoops.getValue())) {
                        return;
                    }
                if (0 < int124) {
                        int index = 0 + this.getInt2();
                        this.keyCode2++;
                        int index2 = minecraft.thePlayer.getInt10() + this.keyCode2;

                        while (true) {
                            if (index2 >= 32) {
                                enabled = true;
                            }

                            if (enabled || ++index >= int124) {
                                break;
                            }

                            index += this.getInt2();
                            this.keyCode2++;
                            index2 = minecraft.thePlayer.getInt10() + this.keyCode2;
                        }
                    }
                if (!enabled) {
                        return;
                    }
                minecraft.playerController.processAction2(minecraft.thePlayer);
                this.keyCode2 = 0;
                return;
            case "NewPacket":
                int newPackets = (int)this.settingNewPackets.getValue();
                if (minecraft.thePlayer.getEntityId2() < newPackets) {
                        newPackets = minecraft.thePlayer.getEntityId2();
                    }
                minecraft.thePlayer.setEntityId2(minecraft.thePlayer.getEntityId2() - newPackets);
                for (int i = 0; i < newPackets; i++) {
                        this.getInt2();
                    }
                return;
        }
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

    private boolean isGrimPacketsVisible() {
        return this.settingMode.getSelected().equals("Grim-1.17");
    }

    private int getInt2() {
        String selected = this.settingPacketMode.getSelected();
switch (selected) {
            case "Default":
                minecraft.thePlayer.netHandlerPlayClient.handlePacket2(new C03PacketPlayer(true));
                break;
            case "Legit":
                minecraft.thePlayer.setSprinting(false);
        }

        return 0;
    }

    private boolean canExecute5() {
        if (minecraft.theWorld == null) {
            return false;
        } else if (minecraft.thePlayer == null) {
            return false;
        } else if (!minecraft.thePlayer.onGround && this.settingOnlyOnGround.isEnabled()) {
            return false;
        } else if (!minecraft.thePlayer.isUsingItem()) {
            return false;
        } else if (minecraft.thePlayer.getHeldItem() == null) {
            return false;
        } else {
            if ((!(minecraft.thePlayer.getHeldItem().getItem() instanceof ItemFood) || !FastUseFoodType.checkFastUseFoodType(FastUseFoodType.FOOD))
                && (
                    !(minecraft.thePlayer.getHeldItem().getItem() instanceof ItemPotion)
                            && !(minecraft.thePlayer.getHeldItem().getItem() instanceof ItemMilkBucket)
                        || !FastUseFoodType.checkFastUseFoodType(FastUseFoodType.OTHER)
                )) {
                if (!(minecraft.thePlayer.getHeldItem().getItem() instanceof ItemBow)) {
                    return false;
                }

                if (!FastUseFoodType.checkFastUseFoodType(FastUseFoodType.BOW)) {
                    return false;
                }
            }

            if (!MoveUtil.canExecute() && this.settingOnlyWhileMoving.isEnabled()) {
                return false;
            } else {
                return !minecraft.thePlayer.isPlayerSleeping() ? true : !this.settingOnlyAtFullLife.isEnabled();
            }
        }
    }

    private boolean isTimePacketsVisible() {
        return this.settingMode.getSelected().equals("TimeManipulation");
    }

    private boolean isPacketsVisible() {
        return this.settingMode.getSelected().equals("Packet");
    }

    private boolean isNewPacketsVisible() {
        return this.settingMode.getSelected().equals("NewPacket");
    }

    private boolean isPacketModeVisible() {
        return this.settingMode.getSelected().equals("Packet");
    }

    private boolean isPacketLoopsVisible() {
        return this.settingMode.getSelected().equals("Packet");
    }
}

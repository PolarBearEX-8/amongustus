package augustus.modules.combat;

import augustus.modes.combat.VelocityBufferMode;
import augustus.modes.combat.VelocityCustomMode;
import augustus.modes.combat.VelocityGrimMode;
import augustus.modes.combat.VelocityIntaveMode;
import augustus.modes.combat.VelocityJumpMode;
import augustus.modes.combat.VelocityPacketMode;
import augustus.modes.combat.VelocityPolarMode;
import augustus.modes.combat.VelocityReduceMode;
import augustus.modes.combat.VelocityReverseMode;
import augustus.modes.movement.JesusBasicMode;
import augustus.modes.movement.JesusBoundingboxMode;
import augustus.modes.movement.JesusCustommotionMode;
import augustus.modes.movement.JesusIntaveMode;
import augustus.modes.movement.JesusIntavePolarflagMode;
import augustus.modes.movement.JesusLegitMode;
import augustus.modes.movement.JesusPushMode;
import augustus.modes.movement.JesusPushgroundMode;
import client.ClientGlobals;
import client.event.EventMove;
import client.event.EventPacket;
import client.event.EventSendPacket;
import client.event.MoveListener;
import client.event.NetworkPacketListener;
import client.event.PacketListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.network.PingSpoofPacketType;
import client.setting.BooleanSetting;
import client.setting.DynamicModeSetting;
import client.setting.ModeSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.Packet;
import client.util.TimeHelper;
import java.awt.Color;
import java.lang.reflect.Array;
import java.util.function.BiFunction;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S29PacketSoundEffect;

public class Velocity extends Module implements MoveListener, PacketListener, TickListener, NetworkPacketListener {
    public DynamicModeSetting settingMode;
    public BooleanSetting settingNotWhileSPressed;
    public BooleanSetting settingNotWhileLongJump;
    public NumberSetting settingLag;
    public BooleanSetting settingIgnoreFire;
    public BooleanSetting settingCancelDamageSoundsFromOtherPlayers;
    public SettingGroup settingPacketCancelingGroup;
    public MultiSelectSetting settingPacketToCancel;
    public TimeHelper timer;
    public int keyCode2;
    public boolean expanded2;

    public Velocity() {
        super("Velocity", "Rückstoß", new Color(83, 102, 109, 255), Category.COMBAT);
        BiFunction[] biFunctionArray = new BiFunction[17];
        BiFunction biFunction = JesusBasicMode.getBiFunction();
        biFunctionArray[0] = biFunction;
        BiFunction biFunction2 = JesusLegitMode.getBiFunction();
        biFunctionArray[1] = biFunction2;
        BiFunction biFunction3 = JesusPushgroundMode.getBiFunction();
        biFunctionArray[2] = biFunction3;
        BiFunction biFunction4 = JesusPushMode.getBiFunction();
        biFunctionArray[3] = biFunction4;
        BiFunction biFunction5 = JesusIntavePolarflagMode.getBiFunction();
        biFunctionArray[4] = biFunction5;
        BiFunction biFunction6 = JesusIntaveMode.getBiFunction();
        biFunctionArray[5] = biFunction6;
        BiFunction biFunction7 = VelocityIntaveMode.getBiFunction();
        biFunctionArray[6] = biFunction7;
        BiFunction biFunction8 = VelocityJumpMode.getBiFunction();
        biFunctionArray[7] = biFunction8;
        BiFunction biFunction9 = VelocityPacketMode.getBiFunction();
        biFunctionArray[8] = biFunction9;
        BiFunction biFunction10 = VelocityBufferMode.getBiFunction();
        biFunctionArray[9] = biFunction10;
        BiFunction biFunction11 = VelocityCustomMode.getBiFunction();
        biFunctionArray[10] = biFunction11;
        BiFunction biFunction12 = VelocityReduceMode.getBiFunction();
        biFunctionArray[11] = biFunction12;
        BiFunction biFunction13 = JesusCustommotionMode.getBiFunction();
        biFunctionArray[12] = biFunction13;
        BiFunction biFunction14 = JesusBoundingboxMode.getBiFunction();
        biFunctionArray[13] = biFunction14;
        BiFunction biFunction15 = VelocityReverseMode.getBiFunction();
        biFunctionArray[14] = biFunction15;
        BiFunction biFunction16 = VelocityGrimMode.getBiFunction();
        biFunctionArray[15] = biFunction16;
        BiFunction biFunction17 = VelocityPolarMode.getBiFunction();
        biFunctionArray[16] = biFunction17;
        this.settingMode = new DynamicModeSetting(this, "Mode", "Basic", biFunctionArray);
        this.settingNotWhileSPressed = new BooleanSetting(this, "NotWhileSPressed", false);
        this.settingNotWhileLongJump = new BooleanSetting(this, "NotWhileLongJump", false);
        this.settingLag = new NumberSetting(this, "Lag", 0.0, 0.0, 400.0, 0);
        this.settingIgnoreFire = new BooleanSetting(this, "IgnoreFire", true);
        this.settingCancelDamageSoundsFromOtherPlayers = new BooleanSetting(this, "CancelDamageSoundsFromOtherPlayers", false);
        this.settingPacketCancelingGroup = (SettingGroup)new SettingGroup(this, "PacketCancelingGroup", true).getSetting("PacketCanceling");
        PingSpoofPacketType[] pingSpoofPacketTypeArray = PingSpoofPacketType.values();
        this.settingPacketToCancel = new MultiSelectSetting(this, "PacketToCancel", pingSpoofPacketTypeArray, this.settingPacketCancelingGroup).getMultiSelectSetting("PacketsToCancel");
        this.timer = new TimeHelper();
        this.keyCode2 = 0;
        this.expanded2 = false;
        PingSpoofPacketType[] pingSpoofPacketTypeArray2 = PingSpoofPacketType.values();
        int length = Array.getLength(pingSpoofPacketTypeArray2);

        for (int i = 0; i < length; i++) {
            Object object = pingSpoofPacketTypeArray2[i];
            String text = ((PingSpoofPacketType)object).getText();
            SettingGroup settingGroup3 = new SettingGroup(this, text, this.settingPacketCancelingGroup).getSettingGroup(() -> ((PingSpoofPacketType)object).isSelected());
            StringBuilder stringBuilder = new StringBuilder();
            String text2 = ((PingSpoofPacketType)object).getText();
            String text3 = stringBuilder.append(text2).append("CancelType").toString();
            String[] textArray = new String[4];
            textArray[0] = "None";
            textArray[1] = "OnlyOnGround";
            textArray[2] = "OnlyInAir";
            textArray[3] = "Always";
            ModeSetting modeSetting2 = new ModeSetting(this, text3, "None", textArray, settingGroup3).getModeSetting("CancelType");
            PingSpoofPacketType.handlePacket((PingSpoofPacketType)object, modeSetting2);
            StringBuilder stringBuilder2 = new StringBuilder();
            String text4 = ((PingSpoofPacketType)object).getText();
            String text5 = stringBuilder2.append(text4).append("PacketCount").toString();
            NumberSetting numberSetting2 = new NumberSetting(this, text5, 6.0, 1.0, 10.0, 0, settingGroup3);
            NumberSetting numberSetting4 = numberSetting2.getNumberSetting("PacketCount").getNumberSetting(() -> checkPingSpoofPacketType((PingSpoofPacketType)object));
            PingSpoofPacketType.handlePacket((PingSpoofPacketType)object, numberSetting4);
        }
    }

    @Override
    public void onEnable() {
        this.registerEvent(21);
        this.registerEvent(26);
        this.registerEvent(40);
        this.registerEvent(25);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onNetworkPacket(EventSendPacket packet) {
        if (this.canExecute5()) {
            Packet packet2 = packet.getPacket();
            if (this.settingLag.getValue() > 0.0
            && packet2 instanceof S12PacketEntityVelocity
            && ((S12PacketEntityVelocity)packet2).getEntityId() == minecraft.thePlayer.getEntityId()) {
                this.timer.reset();
            }

            if (!this.timer.hasReachedDouble(this.settingLag.getValue())) {
                packet.setCancelled(minecraft.thePlayer != null && minecraft.thePlayer.ticksExisted > 20);
            } else {
                packet.setCancelled(false);
            }
        }
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        if (minecraft.thePlayer != null && this.canExecute5()) {
            Packet packet2 = packet.getPacket();
            if (minecraft.thePlayer != null) {
                if (minecraft.thePlayer.ticksExisted > 20) {
                    this.onPacket2(packet);
                }
            }

            if (!(packet2 instanceof S29PacketSoundEffect)) {
                return;
            }

            if (this.settingCancelDamageSoundsFromOtherPlayers.isEnabled()) {
                S29PacketSoundEffect s29PacketSoundEffect = (S29PacketSoundEffect)packet2;
                if (!s29PacketSoundEffect.getText().equalsIgnoreCase("game.player.hurt") && !s29PacketSoundEffect.getText().equalsIgnoreCase("game.player.die")) {
                    return;
                }

                packet.cancel();
            }
        }
    }

    @Override
    public void onMove(EventMove event) {
        float scaleFactor7 = event.getScaleFactor7();
        this.expanded2 = scaleFactor7 < 0.0F;
    }

    @Override
    public void onRunTick() {
        this.applyString4(this.settingMode.getSelected());
        this.keyCode2 = 0;
    }

    private static boolean checkPingSpoofPacketType(PingSpoofPacketType pingSpoofPacketTypeKind) {
        return !PingSpoofPacketType.handlePacket5(pingSpoofPacketTypeKind).getSelected().equals("None");
    }

    public void onPacket2(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (packet2 instanceof S12PacketEntityVelocity) {
            S12PacketEntityVelocity s12PacketEntityVelocity = (S12PacketEntityVelocity)packet2;
            if (s12PacketEntityVelocity.getEntityId() == minecraft.thePlayer.getEntityId() && s12PacketEntityVelocity.getEntityId3() != 0 && s12PacketEntityVelocity.getEntityId4() != 0) {
                for (PingSpoofPacketType pingSpoofPacketTypeKind : PingSpoofPacketType.values()) {
                    if (this.handlePacket(pingSpoofPacketTypeKind)) {
                        PingSpoofPacketType.handlePacket(pingSpoofPacketTypeKind, (int)PingSpoofPacketType.handlePacket(pingSpoofPacketTypeKind).getValue());
                    }
                }

                return;
            }
        } else if (packet2 instanceof S08PacketPlayerPosLook) {
            PingSpoofPacketType[] pingSpoofPacketTypeArray = PingSpoofPacketType.values();
            int length = pingSpoofPacketTypeArray.length;

            for (int i = 0; i < length; i++) {
                PingSpoofPacketType.handlePacket(pingSpoofPacketTypeArray[i], 0);
            }

            return;
        }

        for (PingSpoofPacketType pingSpoofPacketTypeKind2 : PingSpoofPacketType.values()) {
            if (packet2.getClass().equals(PingSpoofPacketType.handlePacket4(pingSpoofPacketTypeKind2))) {
                if (!this.handlePacket(pingSpoofPacketTypeKind2)) {
                    return;
                }

                if (PingSpoofPacketType.handlePacket3(pingSpoofPacketTypeKind2) > 0) {
                    packet.cancel();
                    PingSpoofPacketType.handlePacket2(pingSpoofPacketTypeKind2);
                }

                return;
            }
        }
    }

    private boolean canExecute5() {
        if (minecraft.thePlayer == null) {
            return false;
        } else if (this.settingNotWhileSPressed.isEnabled() && this.expanded2) {
            return false;
        } else if (minecraft.thePlayer.canExecute2() && this.settingIgnoreFire.isEnabled()) {
            return false;
        } else {
            return !ClientGlobals.moduleManager.longJump.isToggled() ? true : !this.settingNotWhileLongJump.isEnabled();
        }
    }

    private boolean handlePacket(PingSpoofPacketType pingSpoofPacketTypeKind) {
        if (!pingSpoofPacketTypeKind.isSelected()) {
            return false;
        } else if (PingSpoofPacketType.handlePacket5(pingSpoofPacketTypeKind).getSelected().equals("Always")) {
            return true;
        } else if (PingSpoofPacketType.handlePacket5(pingSpoofPacketTypeKind).getSelected().equals("OnlyInAir") && !minecraft.thePlayer.onGround) {
            return true;
        } else {
            return !PingSpoofPacketType.handlePacket5(pingSpoofPacketTypeKind).getSelected().equals("OnlyOnGround") ? false : minecraft.thePlayer.onGround;
        }
    }
}

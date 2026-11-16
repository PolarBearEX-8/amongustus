package augustus.modules.misc;

import client.Client;
import client.event.ClickListener;
import client.event.EventClick;
import client.event.EventMotionUpdate;
import client.event.EventPacket;
import client.event.EventSendPacket;
import client.event.EventSneak;
import client.event.MotionUpdateListener;
import client.event.NetworkPacketListener;
import client.event.PacketListener;
import client.event.RenderListener;
import client.event.SneakListener;
import client.event.TickListener;
import client.event.UpdateStateListener;
import client.module.Category;
import client.module.Module;
import client.setting.ActionSetting;
import client.setting.BooleanSetting;
import client.setting.DisablerIncomingPacketsSetting;
import client.setting.DisablerOutgoingPacketsSetting;
import client.setting.DisablerPacketFilter;
import client.setting.DisablerSuccessfullyLoadedType;
import client.setting.DisablerUndefinedType;
import client.setting.ModeSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.RangeSetting;
import client.setting.Setting;
import client.setting.SettingContainer;
import client.setting.SettingGroup;
import client.setting.SettingProvider;
import client.util.BooleanSupplier;
import client.util.LvlManager;
import client.util.MoveUtil;
import client.util.MovingObjectType;
import client.util.Packet;
import client.util.PlayerDiggingAction;
import client.util.PlayerDiggingActionType;
import client.util.TimeHelper;
import client.util.UseEntityAction;
import java.awt.Color;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityActionType;
import net.minecraft.entity.EntityBoat;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayerAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0CPacketInput;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.network.play.client.C13PacketPlayerAbilities;
import net.minecraft.network.play.client.C18PacketSpectate;
import net.minecraft.network.play.client.C19PacketResourcePackStatus;
import net.minecraft.network.play.client.ClientPacketType;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;
import net.minecraft.network.play.server.ServerPacketType;
import net.minecraft.potion.Potion;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;

public class Disabler
extends Module
implements UpdateStateListener,
RenderListener,
PacketListener,
TickListener,
MotionUpdateListener,
ClickListener,
SneakListener,
NetworkPacketListener {
    public SettingGroup settingCustomPacketCanceling;
    public SettingGroup settingIncomingPackets;
    public MultiSelectSetting settingIncomingPacketsToCancel;
    public SettingGroup settingOutgoingPackets;
    public MultiSelectSetting settingOutGoingPacketsToCancel;
    public SettingGroup settingDetailedPackets;
    public MultiSelectSetting settingEntityActionsToCancel;
    public MultiSelectSetting settingDiggingPacket;
    public BooleanSetting settingPingSpoof;
    public MultiSelectSetting settingPingSpoofPackets;
    public ActionSetting settingDelay;
    public SettingGroup settingPlace;
    public BooleanSetting settingPlace1;
    public BooleanSetting settingNotWhileAttack1;
    public BooleanSetting settingNotWhileScaffold1;
    public BooleanSetting settingOnlyWhileMove1;
    public BooleanSetting settingPlace2;
    public BooleanSetting settingNotWhileAttack2;
    public BooleanSetting settingNotWhileScaffold2;
    public BooleanSetting settingOnlyWhileMove2;
    public BooleanSetting settingPlace3;
    public BooleanSetting settingNotWhileAttack3;
    public BooleanSetting settingNotWhileScaffold3;
    public BooleanSetting settingOnlyWhileMove3;
    public BooleanSetting settingRoyalPixels;
    public BooleanSetting settingSpectate;
    public BooleanSetting settingRide;
    public BooleanSetting settingKarhuClicking;
    public BooleanSetting settingMMCReachSemi;
    public BooleanSetting settingOnGroundTrue;
    public BooleanSetting settingTp;
    public RangeSetting settingTPDelay;
    public RangeSetting settingTPY;
    public RangeSetting settingTPXZ;
    public BooleanSetting settingTest;
    public BooleanSetting settingResourcePack;
    public BooleanSetting settingHover;
    public BooleanSetting settingGrimSpec;
    public BooleanSetting settingGrimSpecAlways;
    public BooleanSetting settingSneak;
    public ModeSetting settingSneakType;
    public NumberSetting settingSneakDelay;
    public CopyOnWriteArrayList copyOnWriteArrayList;
    public HashMap entryMap;
    public int tickDelay1;
    public int tickDelay;
    public boolean activeState;
    public CopyOnWriteArrayList copyOnWriteArrayList2;
    public boolean enabled2;
    public boolean visible2;
    public TimeHelper timer;
    public TimeHelper timer2;
    public CopyOnWriteArrayList copyOnWriteArrayList3;
    public boolean expanded2;

    public Disabler() {
        super("Disabler", "Deaktivierer", new Color(73, 127, 163), Category.MISC);
        this.settingCustomPacketCanceling = new SettingGroup(this, "CustomPacketCanceling");
        this.settingIncomingPackets = new SettingGroup(this, "IncomingPackets", this.settingCustomPacketCanceling);
        ServerPacketType[] serverPacketTypeArray = ServerPacketType.values();
        this.settingIncomingPacketsToCancel = new DisablerIncomingPacketsSetting(
        this, this, "IncomingPacketsToCancel", serverPacketTypeArray, this.settingIncomingPackets
        );
        this.settingOutgoingPackets = new SettingGroup(this, "OutgoingPackets", this.settingCustomPacketCanceling);
        ClientPacketType[] clientPacketTypeArray = ClientPacketType.values();
        this.settingOutGoingPacketsToCancel = new DisablerOutgoingPacketsSetting(
        this, this, "OutGoingPacketsToCancel", clientPacketTypeArray, this.settingOutgoingPackets
        );
        this.settingDetailedPackets = new SettingGroup(this, "DetailedPackets", this.settingCustomPacketCanceling);
        EntityActionType[] entityActionTypeArray = EntityActionType.values();
        this.settingEntityActionsToCancel = new MultiSelectSetting(this, "EntityActionsToCancel", entityActionTypeArray, this.settingDetailedPackets);
        PlayerDiggingAction[] playerDiggingActionArray = PlayerDiggingAction.values();
        this.settingDiggingPacket = new MultiSelectSetting(this, "DiggingPacket", playerDiggingActionArray, this.settingDetailedPackets);
        this.settingPingSpoof = new BooleanSetting(this, "PingSpoof", false);
        DisablerPacketFilter[] disablerPacketFilterArray = DisablerPacketFilter.values();
        this.settingPingSpoofPackets = new MultiSelectSetting(this, "PingSpoofPackets", disablerPacketFilterArray).getMultiSelectSetting(() -> this.settingPingSpoof.isEnabled());
        ActionSetting actionSetting = new ActionSetting(this, "Delay", 1000.0, 1500.0, 1.0, 10000.0, 0);
        this.settingDelay = actionSetting.getActionSetting(() -> this.settingPingSpoof.isEnabled());
        this.settingPlace = new SettingGroup(this, "Place");
        this.settingPlace1 = new BooleanSetting(this, "Place1", false, this.settingPlace);
        BooleanSetting booleanSetting3 = new BooleanSetting(this, "NotWhileAttack1", true, this.settingPlace);
        BooleanSetting setting = (BooleanSetting)booleanSetting3.getSetting("NotWhileAttack");
        Setting setting2 = setting.getSetting(() -> this.settingPlace1.isEnabled());
        this.settingNotWhileAttack1 = (BooleanSetting)setting2;
        BooleanSetting booleanSetting4 = new BooleanSetting(this, "NotWhileScaffold1", true, this.settingPlace);
        BooleanSetting setting3 = (BooleanSetting)booleanSetting4.getSetting("NotWhileScaffold");
        Setting setting4 = setting3.getSetting(() -> this.settingPlace1.isEnabled());
        this.settingNotWhileScaffold1 = (BooleanSetting)setting4;
        BooleanSetting booleanSetting5 = new BooleanSetting(this, "OnlyWhileMove1", true, this.settingPlace);
        this.settingOnlyWhileMove1 = (BooleanSetting)(((BooleanSetting)booleanSetting5.getSetting("OnlyWhileMove")).getSetting(() -> this.settingPlace1.isEnabled()));
        this.settingPlace2 = new BooleanSetting(this, "Place2", false, this.settingPlace);
        BooleanSetting booleanSetting7 = new BooleanSetting(this, "NotWhileAttack2", true, this.settingPlace);
        this.settingNotWhileAttack2 = (BooleanSetting)(((BooleanSetting)booleanSetting7.getSetting("NotWhileAttack")).getSetting(() -> this.settingPlace2.isEnabled()));
        BooleanSetting booleanSetting8 = new BooleanSetting(this, "NotWhileScaffold2", true, this.settingPlace);
        this.settingNotWhileScaffold2 = (BooleanSetting)(((BooleanSetting)booleanSetting8.getSetting("NotWhileScaffold")).getSetting(() -> this.settingPlace2.isEnabled()));
        BooleanSetting booleanSetting9 = new BooleanSetting(this, "OnlyWhileMove2", true, this.settingPlace);
        this.settingOnlyWhileMove2 = (BooleanSetting)(((BooleanSetting)booleanSetting9.getSetting("OnlyWhileMove")).getSetting(() -> this.settingPlace2.isEnabled()));
        this.settingPlace3 = new BooleanSetting(this, "Place3", false, this.settingPlace);
        BooleanSetting booleanSetting11 = new BooleanSetting(this, "NotWhileAttack3", true, this.settingPlace);
        this.settingNotWhileAttack3 = (BooleanSetting)(((BooleanSetting)booleanSetting11.getSetting("NotWhileAttack")).getSetting(() -> this.settingPlace3.isEnabled()));
        BooleanSetting booleanSetting12 = new BooleanSetting(this, "NotWhileScaffold3", true, this.settingPlace);
        this.settingNotWhileScaffold3 = (BooleanSetting)(((BooleanSetting)booleanSetting12.getSetting("NotWhileScaffold")).getSetting(() -> this.settingPlace3.isEnabled()));
        BooleanSetting booleanSetting13 = new BooleanSetting(this, "OnlyWhileMove3", true, this.settingPlace);
        this.settingOnlyWhileMove3 = (BooleanSetting)(((BooleanSetting)booleanSetting13.getSetting("OnlyWhileMove")).getSetting(() -> this.settingPlace3.isEnabled()));
        this.settingRoyalPixels = new BooleanSetting(this, "RoyalPixels", false);
        this.settingSpectate = new BooleanSetting(this, "Spectate", false);
        this.settingRide = new BooleanSetting(this, "Ride", false);
        this.settingKarhuClicking = new BooleanSetting(this, "KarhuClicking", false);
        this.settingMMCReachSemi = new BooleanSetting(this, "MMCReachSemi", false);
        this.settingOnGroundTrue = new BooleanSetting(this, "OnGroundTrue", false);
        this.settingTp = new BooleanSetting(this, "Tp", false);
        this.settingTPDelay = new RangeSetting(this, "TPDelay", 1000.0, 1500.0, 1.0, 10000.0, 0)
        .getRangeSetting2().getRangeSetting(15343, () -> this.settingTp.isEnabled());
        this.settingTPY = new RangeSetting(this, "TPY", 1024.0, 2048.0, 1024.0, 2048.0, 0)
        .getRangeSetting2().getRangeSetting(15343, () -> this.settingTp.isEnabled());
        this.settingTPXZ = new RangeSetting(this, "TPXZ", 1024.0, 2048.0, 1024.0, 2048.0, 0)
        .getRangeSetting2().getRangeSetting(15343, () -> this.settingTp.isEnabled());
        this.settingTest = new BooleanSetting(this, "Test", false);
        this.settingResourcePack = new BooleanSetting(this, "ResourcePack", false);
        this.settingHover = new BooleanSetting(this, "Hover", false);
        this.settingGrimSpec = new BooleanSetting(this, "GrimSpec", false);
        this.settingGrimSpecAlways = (BooleanSetting)new BooleanSetting(this, "grimSpecAlways", false).getSetting(() -> this.settingGrimSpec.isEnabled());
        this.settingSneak = new BooleanSetting(this, "Sneak", false);
        String[] textArray = new String[3];
        textArray[0] = "StartStop";
        textArray[1] = "Start";
        textArray[2] = "Stop";
        this.settingSneakType = new ModeSetting(this, "SneakType", "StartStop", textArray).getModeSetting(() -> this.settingSneak.isEnabled());
        NumberSetting numberSetting = new NumberSetting(this, "SneakDelay", 10.0, 1.0, 40.0, 0);
        NumberSetting numberSetting2 = numberSetting.getNumberSetting(() -> this.settingSneak.isEnabled());
        this.settingSneakDelay = numberSetting2;
        this.copyOnWriteArrayList = new CopyOnWriteArrayList();
        this.entryMap = new HashMap();
        this.tickDelay1 = 0;
        this.tickDelay = 0;
        this.activeState = false;
        this.copyOnWriteArrayList2 = new CopyOnWriteArrayList();
        this.enabled2 = false;
        this.visible2 = false;
        this.timer = new TimeHelper();
        this.timer2 = new TimeHelper();
        this.copyOnWriteArrayList3 = new CopyOnWriteArrayList();
        this.expanded2 = false;
        ServerPacketType[] serverPacketTypeArray2 = ServerPacketType.values();
        int length = Array.getLength(serverPacketTypeArray2);

        for (int i = 0; i < length; i++) {
            Object object = serverPacketTypeArray2[i];
            String text = ServerPacketType.handlePacket5((ServerPacketType)object);
            SettingGroup settingGroup7 = new SettingGroup(this, text, this.settingIncomingPackets).getSettingGroup(() -> checkServerPacketType((ServerPacketType)object));
            StringBuilder stringBuilder = new StringBuilder();
            String text2 = ServerPacketType.handlePacket5((ServerPacketType)object);
            String text3 = stringBuilder.append(text2).append("Interval").toString();
            NumberSetting numberSetting3 = new NumberSetting(this, text3, 0.0, 0.0, 20.0, 0, settingGroup7);
            NumberSetting numberSetting4 = numberSetting3.getNumberSetting("Interval");
            ServerPacketType.handlePacket((ServerPacketType)object, numberSetting4);
            StringBuilder stringBuilder2 = new StringBuilder();
            String text4 = ServerPacketType.handlePacket5((ServerPacketType)object);
            String text5 = stringBuilder2.append(text4).append("Always").toString();
            BooleanSetting booleanSetting27 = new BooleanSetting(this, text5, true, settingGroup7);
            BooleanSetting setting20 = (BooleanSetting)booleanSetting27.getSetting("Always");
            ServerPacketType.handlePacket((ServerPacketType)object, setting20);
            StringBuilder stringBuilder3 = new StringBuilder();
            String text6 = ServerPacketType.handlePacket5((ServerPacketType)object);
            String text7 = stringBuilder3.append(text6).append("StartTick").toString();
            StringBuilder stringBuilder4 = new StringBuilder();
            String text8 = ServerPacketType.handlePacket5((ServerPacketType)object);
            String text9 = stringBuilder4.append(text8).append("EndTick").toString();
            ActionSetting actionSetting2 = new ActionSetting(this, text7, text9, 0.0, 100.0, 0.0, 100.0, 0, settingGroup7);
            actionSetting2.getActionSetting("StartTick", "EndTick");
            StringBuilder stringBuilder5 = new StringBuilder();
            String text10 = ServerPacketType.handlePacket5((ServerPacketType)object);
            String text11 = stringBuilder5.append(text10).append("CancelCondition").toString();
            String[] textArray2 = new String[3];
            textArray2[0] = "Always";
            textArray2[1] = "OnlyOnGround";
            textArray2[2] = "OnlyInAir";
            ModeSetting modeSetting4 = new ModeSetting(this, text11, "Always", textArray2, settingGroup7).getModeSetting("CancelCondition");
            ServerPacketType.handlePacket((ServerPacketType)object, modeSetting4);
        }

        ClientPacketType[] clientPacketTypeArray2 = ClientPacketType.values();
        int length2 = Array.getLength(clientPacketTypeArray2);
        Disabler disabler2 = this;
        ClientPacketType[] clientPacketTypeArray3 = clientPacketTypeArray2;

        for (int i2 = 0; i2 < length2; i2 += 1) {
            Object object2 = clientPacketTypeArray3[i2];
            String text12 = ClientPacketType.handlePacket7((ClientPacketType)object2);
            SettingGroup settingGroup9 = new SettingGroup(disabler2, text12, disabler2.settingOutgoingPackets).getSettingGroup(() -> checkClientPacketType((ClientPacketType)object2));
            StringBuilder stringBuilder6 = new StringBuilder();
            String text13 = ClientPacketType.handlePacket7((ClientPacketType)object2);
            String text14 = stringBuilder6.append(text13).append("Interval").toString();
            NumberSetting numberSetting5 = new NumberSetting(disabler2, text14, 0.0, 0.0, 20.0, 0, settingGroup9);
            NumberSetting numberSetting6 = numberSetting5.getNumberSetting("Interval");
            ClientPacketType.handlePacket((ClientPacketType)object2, numberSetting6);
            StringBuilder stringBuilder7 = new StringBuilder();
            String text15 = ClientPacketType.handlePacket7((ClientPacketType)object2);
            String text16 = stringBuilder7.append(text15).append("Always").toString();
            BooleanSetting booleanSetting28 = new BooleanSetting(disabler2, text16, true, settingGroup9);
            BooleanSetting setting21 = (BooleanSetting)booleanSetting28.getSetting("Always");
            ClientPacketType.handlePacket((ClientPacketType)object2, setting21);
            StringBuilder stringBuilder8 = new StringBuilder();
            String text17 = ClientPacketType.handlePacket7((ClientPacketType)object2);
            String text18 = stringBuilder8.append(text17).append("StartTick").toString();
            StringBuilder stringBuilder9 = new StringBuilder();
            String text19 = ClientPacketType.handlePacket7((ClientPacketType)object2);
            String text20 = stringBuilder9.append(text19).append("EndTick").toString();
            ActionSetting actionSetting3 = new ActionSetting(disabler2, text18, text20, 0.0, 100.0, 0.0, 100.0, 0, settingGroup9);
            actionSetting3.getActionSetting("StartTick", "EndTick");
            StringBuilder stringBuilder10 = new StringBuilder();
            String text21 = ClientPacketType.handlePacket7((ClientPacketType)object2);
            String text22 = stringBuilder10.append(text21).append("CancelCondition").toString();
            String[] textArray3 = new String[3];
            textArray3[0] = "Always";
            textArray3[1] = "OnlyOnGround";
            textArray3[2] = "OnlyInAir";
            ModeSetting modeSetting6 = new ModeSetting(disabler2, text22, "Always", textArray3, settingGroup9).getModeSetting("CancelCondition");
            ClientPacketType.handlePacket((ClientPacketType)object2, modeSetting6);
        }

        EntityActionType[] entityActionTypeArray2 = EntityActionType.values();
        int length3 = Array.getLength(entityActionTypeArray2);
        Disabler disabler3 = disabler2;
        EntityActionType[] entityActionTypeArray3 = entityActionTypeArray2;

        for (int i3 = 0; i3 < length3; i3 += 1) {
            Object object3 = entityActionTypeArray3[i3];
            String text23 = EntityActionType.getText((EntityActionType)object3);
            SettingGroup settingGroup11 = new SettingGroup(disabler3, text23, disabler3.settingDetailedPackets).getSettingGroup(() -> checkEntityActionType((EntityActionType)object3));
            StringBuilder stringBuilder11 = new StringBuilder();
            String text24 = EntityActionType.getText((EntityActionType)object3);
            String text25 = stringBuilder11.append(text24).append("Interval").toString();
            NumberSetting numberSetting7 = new NumberSetting(disabler3, text25, 0.0, 0.0, 20.0, 0, settingGroup11);
            NumberSetting numberSetting8 = numberSetting7.getNumberSetting("Interval");
            EntityActionType.getNumberSetting((EntityActionType)object3, numberSetting8);
            StringBuilder stringBuilder12 = new StringBuilder();
            String text26 = EntityActionType.getText((EntityActionType)object3);
            String text27 = stringBuilder12.append(text26).append("Always").toString();
            BooleanSetting booleanSetting29 = new BooleanSetting(disabler3, text27, true, settingGroup11);
            BooleanSetting setting22 = (BooleanSetting)booleanSetting29.getSetting("Always");
            EntityActionType.getBooleanSetting((EntityActionType)object3, setting22);
            StringBuilder stringBuilder13 = new StringBuilder();
            String text28 = EntityActionType.getText((EntityActionType)object3);
            String text29 = stringBuilder13.append(text28).append("StartTick").toString();
            StringBuilder stringBuilder14 = new StringBuilder();
            String text30 = EntityActionType.getText((EntityActionType)object3);
            String text31 = stringBuilder14.append(text30).append("EndTick").toString();
            ActionSetting actionSetting4 = new ActionSetting(disabler3, text29, text31, 0.0, 100.0, 0.0, 100.0, 0, settingGroup11);
            actionSetting4.getActionSetting("StartTick", "EndTick");
            StringBuilder stringBuilder15 = new StringBuilder();
            String text32 = EntityActionType.getText((EntityActionType)object3);
            String text33 = stringBuilder15.append(text32).append("CancelCondition").toString();
            String[] textArray4 = new String[3];
            textArray4[0] = "Always";
            textArray4[1] = "OnlyOnGround";
            textArray4[2] = "OnlyInAir";
            ModeSetting modeSetting8 = new ModeSetting(disabler3, text33, "Always", textArray4, settingGroup11).getModeSetting("CancelCondition");
            EntityActionType.getModeSetting((EntityActionType)object3, modeSetting8);
        }

        PlayerDiggingAction[] playerDiggingActionArray2 = PlayerDiggingAction.values();
        int length4 = Array.getLength(playerDiggingActionArray2);
        Disabler disabler4 = disabler3;
        PlayerDiggingAction[] playerDiggingActionArray3 = playerDiggingActionArray2;

        for (int i4 = 0; i4 < length4; i4 += 1) {
            Object object4 = playerDiggingActionArray3[i4];
            String text34 = PlayerDiggingAction.getText((PlayerDiggingAction)object4);
            SettingGroup settingGroup13 = new SettingGroup(disabler4, text34, disabler4.settingDetailedPackets).getSettingGroup(() -> checkPlayerDiggingAction2((PlayerDiggingAction)object4));
            StringBuilder stringBuilder16 = new StringBuilder();
            String text35 = PlayerDiggingAction.getText((PlayerDiggingAction)object4);
            String text36 = stringBuilder16.append(text35).append("Interval").toString();
            NumberSetting numberSetting9 = new NumberSetting(disabler4, text36, 0.0, 0.0, 20.0, 0, settingGroup13);
            NumberSetting numberSetting10 = numberSetting9.getNumberSetting("Interval");
            PlayerDiggingAction.getNumberSetting((PlayerDiggingAction)object4, numberSetting10);
            StringBuilder stringBuilder17 = new StringBuilder();
            String text37 = PlayerDiggingAction.getText((PlayerDiggingAction)object4);
            String text38 = stringBuilder17.append(text37).append("Always").toString();
            BooleanSetting booleanSetting30 = new BooleanSetting(disabler4, text38, true, settingGroup13);
            BooleanSetting setting23 = (BooleanSetting)booleanSetting30.getSetting("Always");
            PlayerDiggingAction.getBooleanSetting((PlayerDiggingAction)object4, setting23);
            StringBuilder stringBuilder18 = new StringBuilder();
            String text39 = PlayerDiggingAction.getText((PlayerDiggingAction)object4);
            String text40 = stringBuilder18.append(text39).append("StartTick").toString();
            StringBuilder stringBuilder19 = new StringBuilder();
            String text41 = PlayerDiggingAction.getText((PlayerDiggingAction)object4);
            String text42 = stringBuilder19.append(text41).append("EndTick").toString();
            new ActionSetting(disabler4, text40, text42, 0.0, 100.0, 0.0, 100.0, 0, settingGroup13).getActionSetting("StartTick", "EndTick");
            StringBuilder stringBuilder20 = new StringBuilder();
            String text43 = PlayerDiggingAction.getText((PlayerDiggingAction)object4);
            String text44 = stringBuilder20.append(text43).append("CancelCondition").toString();
            String[] textArray5 = new String[3];
            textArray5[0] = "Always";
            textArray5[1] = "OnlyOnGround";
            textArray5[2] = "OnlyInAir";
            ModeSetting modeSetting10 = new ModeSetting(disabler4, text44, "Always", textArray5, settingGroup13).getModeSetting("CancelCondition");
            PlayerDiggingAction.getModeSetting((PlayerDiggingAction)object4, modeSetting10);
        }
    }

    @Override
    public void onEnable() {
        this.registerEvent(43);
        this.registerEvent(32);
        this.registerEvent(26);
        this.registerEvent(40);
        this.registerEvent(18);
        this.registerEvent(7);
        this.registerEvent(39);
        this.registerEvent(25);
    }

    @Override
    public void onDisable() {
        if (this.settingPingSpoof.isEnabled() && minecraft.thePlayer != null && minecraft.theWorld != null && !this.copyOnWriteArrayList.isEmpty()) {
            Iterator iterator = this.copyOnWriteArrayList.iterator();

            while (iterator.hasNext()) {
                Packet packet = (Packet)iterator.next();

                try {
                    minecraft.thePlayer.netHandlerPlayClient.handlePacket2(packet);
                    iterator.remove();
                    this.entryMap.remove(packet);
                } catch (Exception e) {
                }
            }
        }

        this.copyOnWriteArrayList.clear();
        if (!this.copyOnWriteArrayList2.isEmpty()) {
            if (minecraft.thePlayer == null) {
                return;
            }

            if (minecraft.theWorld == null) {
                return;
            }

            if (minecraft.thePlayer.netHandlerPlayClient == null) {
                return;
            }

            if (minecraft.getNetHandlerPlayClient() == null) {
                return;
            }

            if (minecraft.getNetHandlerPlayClient().getNetworkManager() == null) {
                return;
            }

            if (minecraft.getNetHandlerPlayClient().getNetworkManager().getINetHandler() != null) {
                this.copyOnWriteArrayList2.forEach(object -> handlePacket((Packet)object));
                this.copyOnWriteArrayList2.clear();
            }
        }
    }

    @Override
    public void onPreDisable() {
        this.copyOnWriteArrayList.clear();
        this.entryMap.clear();
        this.settingTPDelay.recalculate();
        this.settingTPY.recalculate();
        this.settingTPXZ.recalculate();
        this.timer.reset();
        this.activeState = false;
    }

    @Override
    public void onSneak(EventSneak event) {
        if (this.settingTest.isEnabled()) {
            if (minecraft.objectMouseOver == null) {
                return;
            }

            MovingObjectPosition objectMouseOver = minecraft.objectMouseOver;
            MovingObjectType movingObjectTypeKind = MovingObjectType.ENTITY;
            if (objectMouseOver.typeOfHit != movingObjectTypeKind) {
                return;
            }

            Entity entityHit = minecraft.objectMouseOver.entityHit;
            if (!(entityHit instanceof EntityBoat) && (byte)((entityHit instanceof EntityMinecart) ? 1 : 0) == 0) {
                return;
            }

            if (minecraft.thePlayer.ticksExisted % 6 == 0) {
                event.setActive(true);
            }

            float value;
            if (entityHit instanceof EntityBoat) {
                value = ((EntityBoat)entityHit).getFloat();
            } else {
                value = ((EntityMinecart)entityHit).getFloat();
            }

            float doubleVal = (float)minecraft.thePlayer.getAuxiliaryListener(SharedMonsterAttributes.extendedListener).getDouble();
            float scaleFactor = LvlManager.getScaleFactor(minecraft.thePlayer.getItemStack(), DisablerUndefinedType.UNDEFINED);
            if (doubleVal <= 0.0F && scaleFactor <= 0.0F) {
                return;
            }

            if (minecraft.thePlayer.fallDistance > 0.0F
            && !minecraft.thePlayer.onGround
            && !minecraft.thePlayer.canExecute9()
            && !minecraft.thePlayer.isInWater()) {
                minecraft.thePlayer.checkPotion(Potion.potion16);
            }

            float value2 = doubleVal + scaleFactor;
            if (value > 40.0F - value2 * 10.0F) {
                event.setActive(true);
            }
        }
    }

    @Override
    public void onPacketSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (packet2 instanceof C02PacketUseEntity && ((C02PacketUseEntity)packet2).getUseEntityAction() == UseEntityAction.ATTACK) {
            this.enabled2 = true;
        }

        if (this.settingPingSpoof.isEnabled()) {
            Class classEntry = packet2.getClass();

            for (DisablerPacketFilter disablerPacketFilter : DisablerPacketFilter.values()) {
                if (classEntry.equals(DisablerPacketFilter.handlePacket2(disablerPacketFilter)) && DisablerPacketFilter.handlePacket(disablerPacketFilter)) {
                    this.copyOnWriteArrayList.add(packet2);
                    this.entryMap.put(packet2, System.currentTimeMillis());
                    packet.cancel();
                    break;
                }
            }
        }

        if (packet2 instanceof C03PacketPlayer && this.settingSpectate.isEnabled()) {
            minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C18PacketSpectate(minecraft.thePlayer.getEntityUniqueID()));
        }

        if (packet2 instanceof C03PacketPlayer && this.settingRide.isEnabled()) {
            minecraft.thePlayer.netHandlerPlayClient.handlePacket2(packet2);
            minecraft.thePlayer
            .netHandlerPlayClient
            .handlePacket2(
            new C0CPacketInput(
            minecraft.thePlayer.scaleFactor24,
            minecraft.thePlayer.scaleFactor3,
            minecraft.thePlayer.secondaryFloatEntry.state,
            minecraft.thePlayer.secondaryFloatEntry.state2
            )
            );
            packet.cancel();
        }

        if (this.settingKarhuClicking.isEnabled()) {
            if (packet2 instanceof C0APacketAnimation) {
                if (minecraft.objectMouseOver != null) {
                    MovingObjectPosition objectMouseOver = minecraft.objectMouseOver;
                    if (objectMouseOver.typeOfHit == MovingObjectType.MISS) {
                        this.updateState();
                    } else {
                        MovingObjectType typeOfHit = minecraft.objectMouseOver.typeOfHit;
                        MovingObjectType movingObjectTypeKind = MovingObjectType.ENTITY;
                        if (typeOfHit == movingObjectTypeKind) {
                            this.visible2 = true;
                        }
                    }
                }
            }

            if (packet2 instanceof C03PacketPlayer && this.visible2) {
                this.updateState();
                this.visible2 = false;
            }
        }

        if (this.settingOnGroundTrue.isEnabled()) {
            if (packet2 instanceof C03PacketPlayer) {
                ((C03PacketPlayer)packet2).setOnGround(true);
            }
        }
    }

    @Override
    public void onClick(EventClick event) {
        if (this.settingTest.isEnabled()) {
            if (minecraft.objectMouseOver != null) {
                if (minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY) {
                    Entity entityHit = minecraft.objectMouseOver.entityHit;
                    if (entityHit instanceof EntityBoat || entityHit instanceof EntityMinecart) {
                        if (minecraft.thePlayer.ticksExisted % 6 == 0) {
                            event.updateInt(1);
                        }

                        float value = entityHit instanceof EntityBoat ? ((EntityBoat)entityHit).getFloat() : ((EntityMinecart)entityHit).getFloat();
                        float doubleVal = (float)minecraft.thePlayer.getAuxiliaryListener(SharedMonsterAttributes.extendedListener).getDouble();
                        float scaleFactor = LvlManager.getScaleFactor(minecraft.thePlayer.getItemStack(), DisablerUndefinedType.UNDEFINED);
                        if (doubleVal > 0.0F || scaleFactor > 0.0F) {
                            if (minecraft.thePlayer.fallDistance > 0.0F
                            && !minecraft.thePlayer.onGround
                            && !minecraft.thePlayer.canExecute9()
                            && !minecraft.thePlayer.isInWater()) {
                                minecraft.thePlayer.checkPotion(Potion.potion16);
                            }

                            float value2 = doubleVal + scaleFactor;
                            if (value > 40.0F - value2 * 10.0F) {
                                event.updateInt(1);
                                event.updateInt2(1);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onPacketPostReceive(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (this.checkPacket(packet2)) {
            packet.cancel();
        }
    }

    @Override
    public void onPostNetworkPacket(EventSendPacket packet) {
        if (this.settingMMCReachSemi.isEnabled()) {
            packet.setIndexOffset2(500);
        }
    }

    @Override
    public void onMotionUpdate(EventMotionUpdate event) {
        if (this.settingSneak.isEnabled()) {
            this.tickDelay++;
            if (this.tickDelay % (int)Math.max(this.settingSneakDelay.getValue(), 1.0) == 0) {
                if (this.settingSneakType.getSelected().contains("Start")) {
                    minecraft.thePlayer.netHandlerPlayClient.handlePacket2(new C0BPacketEntityAction(minecraft.thePlayer, EntityPlayerAction.START_SNEAKING));
                }

                if (this.settingSneakType.getSelected().contains("Stop")) {
                    minecraft.thePlayer.netHandlerPlayClient.handlePacket2(new C0BPacketEntityAction(minecraft.thePlayer, EntityPlayerAction.STOP_SNEAKING));
                }
            }
        }

        if (this.settingHover.isEnabled() && minecraft.thePlayer.ticksExisted % 2 == 0) {
            if (minecraft.thePlayer.onGround && event.getCoordOffset1() % 1.0 == 0.0) {
                double offset = Math.random() * 1.0E-4;
                double offset2 = event.getCoordOffset1() + offset;
                event.setCoordOffset1(offset2);
                event.setState(false);
            }
        }

        if (this.settingTp.isEnabled()) {
            TimeHelper timer3 = this.timer;
            long time2 = (long)this.settingTPDelay.getIndexOffset2();
            if (timer3.hasReached(time2)) {
                this.timer.reset();
                double coordOffset1 = event.getCoordOffset1();
                int indexOffset2 = this.settingTPY.getIndexOffset2();
                double offset3 = (double)(indexOffset2 * SettingContainer.settingProvider.getInt2(0, 100) % 2);
                double offset4 = coordOffset1 + offset3;
                double offset5 = offset4 == 0.0 ? -1.0 : 1.0;
                event.setCoordOffset1(offset5);
                double coordOffset3 = event.getCoordOffset3();
                double offset6 = this.settingTPXZ.recalculate();
                double offset7 = offset6 * (double)SettingContainer.settingProvider.getInt2(0, 100) % 2.0;
                double offset8 = coordOffset3 + offset7;
                double offset9 = offset8 == 0.0 ? -1.0 : 1.0;
                event.setCoordOffset3(offset9);
                double coordOffset = event.getCoordOffset();
                double offset10 = this.settingTPXZ.recalculate();
                double offset11 = offset10 * (double)SettingContainer.settingProvider.getInt2(0, 100) % 2.0;
                double offset12 = coordOffset + offset11;
                double offset13 = offset12 == 0.0 ? -1.0 : 1.0;
                event.setCoordOffset(offset13);
                this.settingTPDelay.recalculate();
                this.settingTPY.recalculate();
            }
        }
    }

    @Override
    public void onPacket(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (this.checkPacket2(packet2)) {
            packet.cancel();
        }

        if (packet2 instanceof C0BPacketEntityAction) {
            C0BPacketEntityAction c0BPacketEntityAction = (C0BPacketEntityAction)packet2;

            for (EntityActionType entityActionTypeKind : EntityActionType.values()) {
                if (c0BPacketEntityAction.getEntityPlayerAction() == EntityActionType.getEntityPlayerAction(entityActionTypeKind) && EntityActionType.checkEntityActionType(entityActionTypeKind)) {
                    EntityActionType.getIndex2(entityActionTypeKind);
                    boolean completed = false;
                    if (!EntityActionType.getModeSetting(entityActionTypeKind).getSelected().equals("Always")) {
                        if (EntityActionType.getModeSetting(entityActionTypeKind).getSelected().equals("OnlyOnGround")) {
                            if (minecraft.thePlayer.onGround) {
                                completed = true;
                            }
                        }
                        if (!completed) {
                            if (!EntityActionType.getModeSetting(entityActionTypeKind).getSelected().equals("OnlyInAir")) {
                                break;
                            }
                            if (minecraft.thePlayer.onGround) {
                                break;
                            }
                        }
                    }


                    if (EntityActionType.getNumberSetting(entityActionTypeKind).getValue() > 0.0) {
                        double offset = (double)EntityActionType.getIndex(entityActionTypeKind);
                        NumberSetting numberSetting = EntityActionType.getNumberSetting(entityActionTypeKind);
                        if (offset % numberSetting.getValue() != 0.0) {
                            break;
                        }
                    }

                    if (!EntityActionType.getBooleanSetting(entityActionTypeKind).isEnabled()) {
                        double ticksExisted = (double)minecraft.thePlayer.ticksExisted;
                        ActionSetting actionSetting = EntityActionType.getActionSetting(entityActionTypeKind);
                        if (ticksExisted <= actionSetting.getDouble3()) {
                            break;
                        }

                        double ticksExisted2 = (double)minecraft.thePlayer.ticksExisted;
                        ActionSetting actionSetting2 = EntityActionType.getActionSetting(entityActionTypeKind);
                        if (ticksExisted2 >= actionSetting2.getDouble6()) {
                            break;
                        }
                    }

                    packet.cancel();
                    break;
                }
            }
        }

        if (packet2 instanceof C07PacketPlayerDigging) {
            C07PacketPlayerDigging c07PacketPlayerDigging = (C07PacketPlayerDigging)packet2;

            for (PlayerDiggingAction playerDiggingAction : PlayerDiggingAction.values()) {
                if (c07PacketPlayerDigging.getPlayerDiggingActionType() == PlayerDiggingAction.getPlayerDiggingActionType(playerDiggingAction)
                && PlayerDiggingAction.checkPlayerDiggingAction(playerDiggingAction)) {
                    PlayerDiggingAction.getIndex(playerDiggingAction);
                    boolean completed = false;
                    if (!PlayerDiggingAction.getModeSetting(playerDiggingAction).getSelected().equals("Always")) {
                        if (PlayerDiggingAction.getModeSetting(playerDiggingAction).getSelected().equals("OnlyOnGround")) {
                            if (minecraft.thePlayer.onGround) {
                                completed = true;
                            }
                        }
                        if (!completed) {
                            if (!PlayerDiggingAction.getModeSetting(playerDiggingAction).getSelected().equals("OnlyInAir")) {
                                break;
                            }
                            if (minecraft.thePlayer.onGround) {
                                break;
                            }
                        }
                    }


                    if (PlayerDiggingAction.getNumberSetting(playerDiggingAction).getValue() > 0.0) {
                        double offset2 = (double)PlayerDiggingAction.getIndex2(playerDiggingAction);
                        NumberSetting numberSetting2 = PlayerDiggingAction.getNumberSetting(playerDiggingAction);
                        if (offset2 % numberSetting2.getValue() != 0.0) {
                            break;
                        }
                    }

                    if (!PlayerDiggingAction.getBooleanSetting(playerDiggingAction).isEnabled()) {
                        double ticksExisted3 = (double)minecraft.thePlayer.ticksExisted;
                        ActionSetting actionSetting3 = PlayerDiggingAction.getActionSetting(playerDiggingAction);
                        if (ticksExisted3 <= actionSetting3.getDouble3()) {
                            break;
                        }

                        double ticksExisted4 = (double)minecraft.thePlayer.ticksExisted;
                        ActionSetting actionSetting4 = PlayerDiggingAction.getActionSetting(playerDiggingAction);
                        if (ticksExisted4 >= actionSetting4.getDouble6()) {
                            break;
                        }
                    }

                    packet.cancel();
                    break;
                }
            }
        }

        if (this.settingMMCReachSemi.isEnabled()) {
            if (packet2 instanceof C0FPacketConfirmTransaction) {
                this.tickDelay1++;
                if (this.tickDelay1 % 2 == 0) {
                    packet.cancel();
                }
            }
        }
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        Packet packet2;
        packet2 = packet.getPacket();
        boolean completed = false;
        if (this.settingGrimSpec.isEnabled() && minecraft.thePlayer != null) {
            if (!((packet2 instanceof S08PacketPlayerPosLook && minecraft.thePlayer.invulnerableManager != null) && ((minecraft.thePlayer.invulnerableManager).state))) {
                if (!this.settingGrimSpecAlways.isEnabled()) {
                    completed = true;
                }
            }
            if (!completed) {
                this.activeState = true;
            }
        }


        if (packet2 instanceof S32PacketConfirmTransaction) {
            if (this.activeState) {
                if (this.settingGrimSpec.isEnabled()) {
                    packet.cancel();
                    this.copyOnWriteArrayList2.add(packet2);
                }
            }
        }
    }

    @Override
    public void onRender3D(float partialTicks) {
        if (!this.settingPingSpoof.isEnabled()) {
            if (!this.copyOnWriteArrayList.isEmpty()) {
                this.copyOnWriteArrayList.forEach(object -> handlePacket2((Packet)object));
                this.copyOnWriteArrayList.clear();
            }
        } else if (!this.copyOnWriteArrayList.isEmpty()) {
            Iterator iterator = this.copyOnWriteArrayList.iterator();
            double double3 = this.settingDelay.getDouble3();
            double double6 = this.settingDelay.getDouble6();
            long long2 = SettingContainer.settingProvider.getLong2(double3, double6);
            long time2 = System.currentTimeMillis();

            while (iterator.hasNext()) {
                Object object2 = iterator.next();
                Packet packet = (Packet)object2;

                try {
                    if ((Long)this.entryMap.get(packet) + long2 < time2) {
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket2(packet);
                        this.entryMap.remove(packet);
                        iterator.remove();
                    }
                } catch (Exception e) {
                }
            }

        }
    }

    @Override
    public void onPostUpdateState() {
        if (this.settingRoyalPixels.isEnabled()) {
            C13PacketPlayerAbilities c13PacketPlayerAbilities = new C13PacketPlayerAbilities();
            c13PacketPlayerAbilities.setActive(true);
            c13PacketPlayerAbilities.setActive2(true);
            minecraft.thePlayer.netHandlerPlayClient.handlePacket(c13PacketPlayerAbilities);
        }

        if (this.checkBooleanSetting(this.settingPlace1, this.settingNotWhileAttack1, this.settingNotWhileScaffold1, this.settingOnlyWhileMove1)) {
            minecraft.thePlayer
            .netHandlerPlayClient
            .handlePacket2(new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 255, new ItemStack(Items.waterBucket), 0.0F, 0.5F, 0.0F));
            NetHandlerPlayClient netHandlerPlayClient = minecraft.thePlayer.netHandlerPlayClient;
            double offset = minecraft.thePlayer.posY - 1.5;
            BlockPos blockPos = new BlockPos(minecraft.thePlayer.posX, offset, minecraft.thePlayer.posZ);
            netHandlerPlayClient.handlePacket2(
            new C08PacketPlayerBlockPlacement(
            blockPos, 1, new ItemStack(Blocks.stone.processAction4(minecraft.theWorld, new BlockPos(-1, -1, -1))), 0.0F, 0.94F, 0.0F
            )
            );
        }

        if (this.checkBooleanSetting(this.settingPlace2, this.settingNotWhileAttack2, this.settingNotWhileScaffold2, this.settingOnlyWhileMove2)) {
            minecraft.thePlayer.netHandlerPlayClient.handlePacket2(new C08PacketPlayerBlockPlacement(minecraft.thePlayer.getItemStack()));
        }

        if (this.checkBooleanSetting(this.settingPlace3, this.settingNotWhileAttack3, this.settingNotWhileScaffold3, this.settingOnlyWhileMove3)) {
            minecraft.thePlayer
            .netHandlerPlayClient
            .handlePacket2(new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 255, new ItemStack(Items.waterBucket), 0.0F, 0.5F, 0.0F));
        }

        if (this.settingResourcePack.isEnabled()) {
            minecraft.thePlayer.netHandlerPlayClient.handlePacket2(new C19PacketResourcePackStatus("", DisablerSuccessfullyLoadedType.ACCEPTED));
        }

        this.enabled2 = false;
    }

    private static boolean checkClientPacketType(ClientPacketType clientPacketTypeKind) {
        return ClientPacketType.checkClientPacketType(clientPacketTypeKind);
    }

    private static boolean checkEntityActionType(EntityActionType entityActionTypeKind) {
        return EntityActionType.checkEntityActionType(entityActionTypeKind);
    }

    public boolean checkPacket(Packet packet) {
        if (minecraft.thePlayer == null) {
            return false;
        } else {
            Class classEntry = packet.getClass();

            for (ServerPacketType serverPacketTypeKind : ServerPacketType.values()) {
                if (classEntry.equals(ServerPacketType.handlePacket2(serverPacketTypeKind))) {
                    ServerPacketType.handlePacket3(serverPacketTypeKind);
                    if (!ServerPacketType.checkServerPacketType(serverPacketTypeKind)) {
                        return false;
                    }

                    boolean completed = false;
                    if (!ServerPacketType.handlePacket6(serverPacketTypeKind).getSelected().equals("Always")) {
                        if (ServerPacketType.handlePacket6(serverPacketTypeKind).getSelected().equals("OnlyOnGround")) {
                            if (minecraft.thePlayer.onGround) {
                                completed = true;
                            }
                        }
                        if (!completed) {
                            if (!ServerPacketType.handlePacket6(serverPacketTypeKind).getSelected().equals("OnlyInAir")) {
                                return false;
                            }
                            if (minecraft.thePlayer.onGround) {
                                return false;
                            }
                        }
                    }


                    if (ServerPacketType.handlePacket4(serverPacketTypeKind).getValue() > 0.0) {
                        double offset = (double)ServerPacketType.handlePacket7(serverPacketTypeKind);
                        NumberSetting setting = ServerPacketType.handlePacket4(serverPacketTypeKind);
                        if (offset % setting.getValue() != 0.0) {
                            return false;
                        }
                    }

                    if (ServerPacketType.handlePacket(serverPacketTypeKind).isEnabled()) {
                        return true;
                    }

                    double ticksExisted = (double)minecraft.thePlayer.ticksExisted;
                    ActionSetting actionSetting = ServerPacketType.handlePacket8(serverPacketTypeKind);
                    if (ticksExisted <= actionSetting.getDouble3()) {
                        return false;
                    }

                    double ticksExisted2 = (double)minecraft.thePlayer.ticksExisted;
                    ActionSetting actionSetting2 = ServerPacketType.handlePacket8(serverPacketTypeKind);
                    if (ticksExisted2 < actionSetting2.getDouble6()) {
                        return true;
                    }

                    return false;
                }
            }

            return false;
        }
    }

    private static boolean checkServerPacketType(ServerPacketType serverPacketTypeKind) {
        return ServerPacketType.checkServerPacketType(serverPacketTypeKind);
    }

    public boolean checkPacket2(Packet packet) {
        Class classEntry = packet.getClass();

        for (ClientPacketType clientPacketTypeKind : ClientPacketType.values()) {
            if (classEntry.equals(ClientPacketType.handlePacket(clientPacketTypeKind))) {
                ClientPacketType.handlePacket3(clientPacketTypeKind);
                if (!ClientPacketType.checkClientPacketType(clientPacketTypeKind)) {
                    return false;
                }

                boolean completed = false;
                if (!ClientPacketType.handlePacket8(clientPacketTypeKind).getSelected().equals("Always")) {
                    if (ClientPacketType.handlePacket8(clientPacketTypeKind).getSelected().equals("OnlyOnGround")) {
                        if (minecraft.thePlayer.onGround) {
                            completed = true;
                        }
                    }
                    if (!completed) {
                        if (!ClientPacketType.handlePacket8(clientPacketTypeKind).getSelected().equals("OnlyInAir")) {
                            return false;
                        }
                        if (minecraft.thePlayer.onGround) {
                            return false;
                        }
                    }
                }


                if (ClientPacketType.handlePacket5(clientPacketTypeKind).getValue() > 0.0) {
                    double offset = (double)ClientPacketType.handlePacket6(clientPacketTypeKind);
                    NumberSetting setting = ClientPacketType.handlePacket5(clientPacketTypeKind);
                    if (offset % setting.getValue() != 0.0) {
                        return false;
                    }
                }

                if (ClientPacketType.handlePacket2(clientPacketTypeKind).isEnabled()) {
                    return true;
                }

                double ticksExisted = (double)minecraft.thePlayer.ticksExisted;
                ActionSetting actionSetting = ClientPacketType.handlePacket4(clientPacketTypeKind);
                if (ticksExisted <= actionSetting.getDouble3()) {
                    return false;
                }

                double ticksExisted2 = (double)minecraft.thePlayer.ticksExisted;
                ActionSetting actionSetting2 = ClientPacketType.handlePacket4(clientPacketTypeKind);
                if (ticksExisted2 < actionSetting2.getDouble6()) {
                    return true;
                }

                return false;
            }
        }

        return false;
    }

    private static boolean checkEntityActionTypeKind2(EntityActionType entityActionTypeKind) {
        return !EntityActionType.getBooleanSetting(entityActionTypeKind).isEnabled();
    }

    private void updateState() {
        minecraft.thePlayer
        .netHandlerPlayClient
        .handlePacket(new C07PacketPlayerDigging(PlayerDiggingActionType.START_DESTROY_BLOCK, BlockPos.origin, EnumFacing.UP));
        minecraft.thePlayer
        .netHandlerPlayClient
        .handlePacket(new C07PacketPlayerDigging(PlayerDiggingActionType.START_DESTROY_BLOCK, BlockPos.origin, EnumFacing.UP));
    }

    private static void handlePacket(Packet packet) {
        try {
            packet.processAction(minecraft.getNetHandlerPlayClient().getNetworkManager().getINetHandler());
        } catch (Throwable e) {
        }
    }

    private static boolean handlePacket(ClientPacketType clientPacketTypeKind) {
        return !ClientPacketType.handlePacket2(clientPacketTypeKind).isEnabled();
    }

    public boolean checkBooleanSetting(BooleanSetting setting, BooleanSetting setting2, BooleanSetting setting3, BooleanSetting setting4) {
        if (setting.isEnabled()) {
            if (minecraft.thePlayer == null) {
                return false;
            }

            if (minecraft.theWorld != null) {
                if (setting2.isEnabled() && this.enabled2) {
                    return false;
                }

                if (Client.getClient().getModuleManager().scaffold.isToggled() && setting3.isEnabled()) {
                    return false;
                }

                if (MoveUtil.canExecute()) {
                    return true;
                }

                if (!setting4.isEnabled()) {
                    return true;
                }
            }
        }

        return false;
    }

    private static void handlePacket2(Packet packet) {
        minecraft.thePlayer.netHandlerPlayClient.handlePacket2(packet);
    }

    private static boolean handlePacket(ServerPacketType serverPacketTypeKind) {
        return !ServerPacketType.handlePacket(serverPacketTypeKind).isEnabled();
    }

    private static boolean checkPlayerDiggingAction(PlayerDiggingAction playerDiggingAction) {
        return !PlayerDiggingAction.getBooleanSetting(playerDiggingAction).isEnabled();
    }

    private static boolean checkPlayerDiggingAction2(PlayerDiggingAction playerDiggingAction) {
        return PlayerDiggingAction.checkPlayerDiggingAction(playerDiggingAction);
    }
}

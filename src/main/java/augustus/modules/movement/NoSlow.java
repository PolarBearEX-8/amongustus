package augustus.modules.movement;

import augustus.anticheat.checks.NoSlowCheck;
import augustus.anticheat.checks.SprintCheck;
import augustus.anticheat.checks.VelocityCheck;
import client.ClientGlobals;
import client.event.AttackEntityListener;
import client.event.ClickListener;
import client.event.EventAttackEntity;
import client.event.EventCameraTransform;
import client.event.EventCameraTransformCallback;
import client.event.EventClick;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveInput;
import client.event.EventPacket;
import client.event.EventSendPacket;
import client.event.MotionUpdateListener;
import client.event.MoveInputListener;
import client.event.MoveListener;
import client.event.NetworkPacketListener;
import client.event.PacketListener;
import client.event.TickListener;
import client.event.UpdateStateListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.RangeSetting;
import client.setting.Setting;
import client.setting.SettingContainer;
import client.setting.SettingGroup;
import client.setting.SettingProvider;
import client.util.BooleanSupplier;
import client.util.MoveUtil;
import client.util.Packet;
import client.util.PlayerDiggingActionType;
import client.util.TimeHelper;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.network.play.client.C0CPacketInput;
import net.minecraft.potion.Potion;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class NoSlow
extends Module
implements ClickListener,
MoveListener,
EventCameraTransformCallback,
UpdateStateListener,
MotionUpdateListener,
PacketListener,
AttackEntityListener,
MoveInputListener,
NetworkPacketListener,
TickListener {
    public BooleanSetting settingOnlyWhileMove;
    public BooleanSetting settingStartSlow;
    public BooleanSetting settingEnableIntervals;
    public BooleanSetting settingSwitchOnlyEmptySlot;
    public BooleanSetting settingSwitchGomme;
    public ModeSetting settingSwitchMode;
    public ModeSetting settingSlotMode;
    public ModeSetting settingSpamMode;
    public ModeSetting settingMode;
    public SettingGroup settingSword;
    public MultiSelectSetting settingSword2;
    public NumberSetting settingSwordForward;
    public NumberSetting settingSwordStrafe;
    public ModeSetting settingSwordSlowdownInterval;
    public NumberSetting settingSwordSlowdownIntervalAir;
    public NumberSetting settingSwordSlowdownIntervalGround;
    public NumberSetting settingTimerSword;
    public NumberSetting settingSwordSneakForward;
    public NumberSetting settingSwordSneakStrafe;
    public BooleanSetting settingSwordNullItem;
    public BooleanSetting settingSwordEditFacing;
    public NumberSetting settingSwordEnumFacingID;
    public BooleanSetting settingSwordCustomBlockPos;
    public RangeSetting settingSwordCustomBlockPosX;
    public RangeSetting settingSwordCustomBlockPosY;
    public RangeSetting settingSwordCustomBlockPosZ;
    public BooleanSetting settingSwordCustomFacing;
    public RangeSetting settingSwordCustomFacingX;
    public RangeSetting settingSwordCustomFacingY;
    public RangeSetting settingSwordCustomFacingZ;
    public SettingGroup settingBow;
    public MultiSelectSetting settingBow2;
    public NumberSetting settingBowForward;
    public NumberSetting settingBowStrafe;
    public ModeSetting settingBowSlowdownInterval;
    public NumberSetting settingBowSlowdownIntervalAir;
    public NumberSetting settingBowSlowdownIntervalGround;
    public NumberSetting settingTimerBow;
    public NumberSetting settingBowSneakForward;
    public NumberSetting settingBowSneakStrafe;
    public BooleanSetting settingBowNullItem;
    public BooleanSetting settingBowEditFacing;
    public NumberSetting settingBowEnumFacingID;
    public BooleanSetting settingBowCustomBlockPos;
    public RangeSetting settingBowCustomBlockPosX;
    public RangeSetting settingBowCustomBlockPosY;
    public RangeSetting settingBowCustomBlockPosZ;
    public BooleanSetting settingBowCustomFacing;
    public RangeSetting settingBowCustomFacingX;
    public RangeSetting settingBowCustomFacingY;
    public RangeSetting settingBowCustomFacingZ;
    public SettingGroup settingRest;
    public MultiSelectSetting settingRest2;
    public NumberSetting settingRestForward;
    public NumberSetting settingRestStrafe;
    public ModeSetting settingRestSlowdownInterval;
    public NumberSetting settingRestSlowdownIntervalAir;
    public NumberSetting settingRestSlowdownIntervalGround;
    public NumberSetting settingTimerRest;
    public NumberSetting settingRestSneakForward;
    public NumberSetting settingRestSneakStrafe;
    public BooleanSetting settingRestNullItem;
    public BooleanSetting settingRestEditFacing;
    public NumberSetting settingRestEnumFacingID;
    public BooleanSetting settingRestCustomBlockPos;
    public RangeSetting settingRestCustomBlockPosX;
    public RangeSetting settingRestCustomBlockPosY;
    public RangeSetting settingRestCustomBlockPosZ;
    public BooleanSetting settingRestCustomFacing;
    public RangeSetting settingRestCustomFacingX;
    public RangeSetting settingRestCustomFacingY;
    public RangeSetting settingRestCustomFacingZ;
    public BooleanSetting settingInputForwardLegit;
    public NumberSetting settingInputForward;
    public BooleanSetting settingInputStrafeLegit;
    public NumberSetting settingInputStrafe;
    public BooleanSetting settingInputJumpLegit;
    public BooleanSetting settingInputJump;
    public BooleanSetting settingInputSneakLegit;
    public BooleanSetting settingInputSneak;
    public TimeHelper timer;
    public int tickDelay;
    public ItemStack itemStack;
    public boolean enabled2;
    public boolean activeState;
    public boolean visible2;
    public float motionMultiplier2;
    public float motionMultiplier;
    public int tickDelay6;
    public int tickDelay4;
    public int keyCode2;
    public int tickDelay3;
    public boolean activeState5;
    public boolean activeState1;
    public boolean expanded2;

    public NoSlow() {
        super("NoSlow", "NichtLangsam", new Color(10, 40, 53), Category.MOVEMENT);
        this.settingOnlyWhileMove = new BooleanSetting(this, "OnlyWhileMove", true);
        this.settingStartSlow = new BooleanSetting(this, "StartSlow", false);
        this.settingEnableIntervals = new BooleanSetting(this, "EnableIntervals", false);
        this.settingSwitchOnlyEmptySlot = new BooleanSetting(this, "SwitchOnlyEmptySlot", false);
        this.settingSwitchGomme = new BooleanSetting(this, "SwitchGomme", false);
        String[] textArray = new String[5];
        textArray[0] = "PreAttack";
        textArray[1] = "PostAttack";
        textArray[2] = "PrePosition";
        textArray[3] = "PostPosition";
        textArray[4] = "PrePostPosition";
        this.settingSwitchMode = new ModeSetting(this, "SwitchMode", "PreAttack", textArray);
        String[] textArray2 = new String[4];
        textArray2[0] = "PreAttack";
        textArray2[1] = "PostAttack";
        textArray2[2] = "PrePosition";
        textArray2[3] = "PostPosition";
        this.settingSlotMode = new ModeSetting(this, "SlotMode", "PreAttack", textArray2);
        String[] textArray3 = new String[4];
        textArray3[0] = "PreAttack";
        textArray3[1] = "PostAttack";
        textArray3[2] = "PrePosition";
        textArray3[3] = "PostPosition";
        this.settingSpamMode = new ModeSetting(this, "SpamMode", "PostPosition", textArray3);
        String[] textArray4 = new String[4];
        textArray4[0] = "Legit";
        textArray4[1] = "LegitMotion";
        textArray4[2] = "AllDirection";
        textArray4[3] = "AllDirectionMotion";
        this.settingMode = new ModeSetting(this, "Mode", "Legit", textArray4);
        this.settingSword = new SettingGroup(this, "Sword");
        VelocityCheck[] velocityCheckArray = VelocityCheck.values();
        this.settingSword2 = new MultiSelectSetting(this, "Sword", velocityCheckArray, this.settingSword);
        this.settingSwordForward = new NumberSetting(this, "SwordForward", 0.2, 0.2, 1.0, 2, this.settingSword).getNumberSetting(() -> VelocityCheck.SLOWDOWN.isSelected());
        this.settingSwordStrafe = new NumberSetting(this, "SwordStrafe", 0.2, 0.2, 1.0, 2, this.settingSword).getNumberSetting(() -> VelocityCheck.SLOWDOWN.isSelected());
        String[] textArray5 = new String[3];
        textArray5[0] = "None";
        textArray5[1] = "TicksToNextSlowdownApply";
        textArray5[2] = "TicksToNextDelayApply";
        this.settingSwordSlowdownInterval = new ModeSetting(this, "SwordSlowdownInterval", "None", textArray5, this.settingSword).getModeSetting("SlowdownInterval").getModeSetting(() -> this.isSwordSlowdownIntervalVisible());
        this.settingSwordSlowdownIntervalAir = new NumberSetting(this, "SwordSlowdownIntervalAir", 0.0, 1.0, 10.0, 0, this.settingSword).getNumberSetting("SlowdownIntervalAir").getNumberSetting(() -> this.isSwordSlowdownIntervalAirVisible());
        this.settingSwordSlowdownIntervalGround = new NumberSetting(this, "SwordSlowdownIntervalGround", 0.0, 1.0, 10.0, 0, this.settingSword).getNumberSetting("SlowdownIntervalGround").getNumberSetting(() -> this.isSwordSlowdownIntervalGroundVisible());
        this.settingTimerSword = new NumberSetting(this, "TimerSword", 0.2, 0.1, 2.0, 2, this.settingSword).getNumberSetting(() -> VelocityCheck.TIMER.isSelected());
        this.settingSwordSneakForward = new NumberSetting(this, "SwordSneakForward", 0.3, 0.3, 1.0, 2, this.settingSword).getNumberSetting(() -> VelocityCheck.SNEAK.isSelected());
        this.settingSwordSneakStrafe = new NumberSetting(this, "SwordSneakStrafe", 0.3, 0.3, 1.0, 2, this.settingSword).getNumberSetting(() -> VelocityCheck.SNEAK.isSelected());
        Setting setting = new BooleanSetting(this, "SwordNullItem", false).getSetting(() -> VelocityCheck.INVALID_PACKET.isSelected());
        this.settingSwordNullItem = (BooleanSetting)setting;
        Setting setting2 = new BooleanSetting(this, "SwordEditFacing", false).getSetting(() -> VelocityCheck.INVALID_PACKET.isSelected());
        this.settingSwordEditFacing = (BooleanSetting)setting2;
        this.settingSwordEnumFacingID = new NumberSetting(this, "SwordEnumFacingID", 0.0, 0.0, 5.0, 0).getNumberSetting(() -> this.isSwordEnumFacingIDVisible());
        Setting setting3 = new BooleanSetting(this, "SwordCustomBlockPos", true).getSetting(() -> VelocityCheck.INVALID_PACKET.isSelected());
        this.settingSwordCustomBlockPos = (BooleanSetting)setting3;
        this.settingSwordCustomBlockPosX = new RangeSetting(this, "SwordCustomBlockPosX", 0.0, 0.0, -10.0, 10.0, 8)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isSwordCustomBlockPosXVisible());
        this.settingSwordCustomBlockPosY = new RangeSetting(this, "SwordCustomBlockPosY", 0.0, 0.0, -10.0, 10.0, 8)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isSwordCustomBlockPosYVisible());
        this.settingSwordCustomBlockPosZ = new RangeSetting(this, "SwordCustomBlockPosZ", 0.0, 0.0, -10.0, 10.0, 8)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isSwordCustomBlockPosZVisible());
        this.settingSwordCustomFacing = (BooleanSetting)new BooleanSetting(this, "SwordCustomFacing", false).getSetting(() -> VelocityCheck.INVALID_PACKET.isSelected());
        this.settingSwordCustomFacingX = new RangeSetting(this, "SwordCustomFacingX", 0.0, 0.0, -10.0, 10.0, 8)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isSwordCustomFacingXVisible());
        this.settingSwordCustomFacingY = new RangeSetting(this, "SwordCustomFacingY", 0.0, 0.0, -10.0, 10.0, 8)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isSwordCustomFacingYVisible());
        this.settingSwordCustomFacingZ = new RangeSetting(this, "SwordCustomFacingZ", 0.0, 0.0, -10.0, 10.0, 8)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isSwordCustomFacingZVisible());
        this.settingBow = new SettingGroup(this, "Bow");
        SprintCheck[] sprintCheckArray = SprintCheck.values();
        this.settingBow2 = new MultiSelectSetting(this, "Bow", sprintCheckArray, this.settingBow);
        this.settingBowForward = new NumberSetting(this, "BowForward", 0.2, 0.2, 1.0, 2, this.settingBow).getNumberSetting(() -> SprintCheck.SLOWDOWN.isSelected());
        this.settingBowStrafe = new NumberSetting(this, "BowStrafe", 0.2, 0.2, 1.0, 2, this.settingBow).getNumberSetting(() -> SprintCheck.SLOWDOWN.isSelected());
        String[] textArray6 = new String[3];
        textArray6[0] = "None";
        textArray6[1] = "TicksToNextSlowdownApply";
        textArray6[2] = "TicksToNextDelayApply";
        this.settingBowSlowdownInterval = new ModeSetting(this, "BowSlowdownInterval", "None", textArray6, this.settingBow).getModeSetting("SlowdownInterval").getModeSetting(() -> this.isBowSlowdownIntervalVisible());
        this.settingBowSlowdownIntervalAir = new NumberSetting(this, "BowSlowdownIntervalAir", 0.0, 1.0, 10.0, 0, this.settingBow).getNumberSetting("SlowdownIntervalAir").getNumberSetting(() -> this.isBowSlowdownIntervalAirVisible());
        this.settingBowSlowdownIntervalGround = new NumberSetting(this, "BowSlowdownIntervalGround", 0.0, 1.0, 10.0, 0, this.settingBow).getNumberSetting("SlowdownIntervalGround").getNumberSetting(() -> this.isBowSlowdownIntervalGroundVisible());
        this.settingTimerBow = new NumberSetting(this, "TimerBow", 0.2, 0.1, 2.0, 2, this.settingBow).getNumberSetting(() -> SprintCheck.TIMER.isSelected());
        this.settingBowSneakForward = new NumberSetting(this, "BowSneakForward", 0.3, 0.3, 1.0, 2, this.settingBow).getNumberSetting(() -> SprintCheck.SNEAK.isSelected());
        this.settingBowSneakStrafe = new NumberSetting(this, "BowSneakStrafe", 0.3, 0.3, 1.0, 2, this.settingBow).getNumberSetting(() -> SprintCheck.SNEAK.isSelected());
        this.settingBowNullItem = (BooleanSetting)new BooleanSetting(this, "BowNullItem", false).getSetting(() -> SprintCheck.INVALID_PACKET.isSelected());
        this.settingBowEditFacing = (BooleanSetting)new BooleanSetting(this, "BowEditFacing", false).getSetting(() -> SprintCheck.INVALID_PACKET.isSelected());
        this.settingBowEnumFacingID = new NumberSetting(this, "BowEnumFacingID", 0.0, 0.0, 5.0, 0).getNumberSetting(() -> this.isBowEnumFacingIDVisible());
        this.settingBowCustomBlockPos = (BooleanSetting)new BooleanSetting(this, "BowCustomBlockPos", true).getSetting(() -> SprintCheck.INVALID_PACKET.isSelected());
        this.settingBowCustomBlockPosX = new RangeSetting(this, "BowCustomBlockPosX", 0.0, 0.0, -10.0, 10.0, 8)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isBowCustomBlockPosXVisible());
        this.settingBowCustomBlockPosY = new RangeSetting(this, "BowCustomBlockPosY", 0.0, 0.0, -10.0, 10.0, 8)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isBowCustomBlockPosYVisible());
        this.settingBowCustomBlockPosZ = new RangeSetting(this, "BowCustomBlockPosZ", 0.0, 0.0, -10.0, 10.0, 8)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isBowCustomBlockPosZVisible());
        this.settingBowCustomFacing = (BooleanSetting)new BooleanSetting(this, "BowCustomFacing", false).getSetting(() -> SprintCheck.INVALID_PACKET.isSelected());
        this.settingBowCustomFacingX = new RangeSetting(this, "BowCustomFacingX", 0.0, 0.0, -10.0, 10.0, 8)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isBowCustomFacingXVisible());
        this.settingBowCustomFacingY = new RangeSetting(this, "BowCustomFacingY", 0.0, 0.0, -10.0, 10.0, 8)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isBowCustomFacingYVisible());
        this.settingBowCustomFacingZ = new RangeSetting(this, "BowCustomFacingZ", 0.0, 0.0, -10.0, 10.0, 8)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isBowCustomFacingZVisible());
        this.settingRest = new SettingGroup(this, "Rest");
        NoSlowCheck[] noSlowCheckArray = NoSlowCheck.values();
        this.settingRest2 = new MultiSelectSetting(this, "Rest", noSlowCheckArray, this.settingRest);
        this.settingRestForward = new NumberSetting(this, "RestForward", 0.2, 0.2, 1.0, 2, this.settingRest).getNumberSetting(() -> NoSlowCheck.SLOWDOWN.isSelected());
        this.settingRestStrafe = new NumberSetting(this, "RestStrafe", 0.2, 0.2, 1.0, 2, this.settingRest).getNumberSetting(() -> NoSlowCheck.SLOWDOWN.isSelected());
        String[] textArray7 = new String[3];
        textArray7[0] = "None";
        textArray7[1] = "TicksToNextSlowdownApply";
        textArray7[2] = "TicksToNextDelayApply";
        this.settingRestSlowdownInterval = new ModeSetting(this, "RestSlowdownInterval", "None", textArray7, this.settingRest).getModeSetting("SlowdownInterval").getModeSetting(() -> this.isRestSlowdownIntervalVisible());
        this.settingRestSlowdownIntervalAir = new NumberSetting(this, "RestSlowdownIntervalAir", 0.0, 1.0, 10.0, 0, this.settingRest).getNumberSetting("SlowdownIntervalAir").getNumberSetting(() -> this.isRestSlowdownIntervalAirVisible());
        this.settingRestSlowdownIntervalGround = new NumberSetting(this, "RestSlowdownIntervalGround", 0.0, 1.0, 10.0, 0, this.settingRest).getNumberSetting("SlowdownIntervalGround").getNumberSetting(() -> this.isRestSlowdownIntervalGroundVisible());
        this.settingTimerRest = new NumberSetting(this, "TimerRest", 0.2, 0.1, 2.0, 2, this.settingRest).getNumberSetting(() -> NoSlowCheck.TIMER.isSelected());
        this.settingRestSneakForward = new NumberSetting(this, "RestSneakForward", 0.3, 0.3, 1.0, 2, this.settingRest).getNumberSetting(() -> NoSlowCheck.SNEAK.isSelected());
        this.settingRestSneakStrafe = new NumberSetting(this, "RestSneakStrafe", 0.3, 0.3, 1.0, 2, this.settingRest).getNumberSetting(() -> NoSlowCheck.SNEAK.isSelected());
        this.settingRestNullItem = (BooleanSetting)new BooleanSetting(this, "RestNullItem", false).getSetting(() -> NoSlowCheck.INVALID_PACKET.isSelected());
        this.settingRestEditFacing = (BooleanSetting)new BooleanSetting(this, "RestEditFacing", false).getSetting(() -> NoSlowCheck.INVALID_PACKET.isSelected());
        this.settingRestEnumFacingID = new NumberSetting(this, "RestEnumFacingID", 0.0, 0.0, 5.0, 0).getNumberSetting(() -> this.isRestEnumFacingIDVisible());
        this.settingRestCustomBlockPos = (BooleanSetting)new BooleanSetting(this, "RestCustomBlockPos", true).getSetting(() -> NoSlowCheck.INVALID_PACKET.isSelected());
        this.settingRestCustomBlockPosX = new RangeSetting(this, "RestCustomBlockPosX", 0.0, 0.0, -10.0, 10.0, 8)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isRestCustomBlockPosXVisible());
        this.settingRestCustomBlockPosY = new RangeSetting(this, "RestCustomBlockPosY", 0.0, 0.0, -10.0, 10.0, 8)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isRestCustomBlockPosYVisible());
        this.settingRestCustomBlockPosZ = new RangeSetting(this, "RestCustomBlockPosZ", 0.0, 0.0, -10.0, 10.0, 8)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isRestCustomBlockPosZVisible());
        this.settingRestCustomFacing = (BooleanSetting)new BooleanSetting(this, "RestCustomFacing", false).getSetting(() -> NoSlowCheck.INVALID_PACKET.isSelected());
        this.settingRestCustomFacingX = new RangeSetting(this, "RestCustomFacingX", 0.0, 0.0, -10.0, 10.0, 8)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isRestCustomFacingXVisible());
        this.settingRestCustomFacingY = new RangeSetting(this, "RestCustomFacingY", 0.0, 0.0, -10.0, 10.0, 8)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isRestCustomFacingYVisible());
        this.settingRestCustomFacingZ = new RangeSetting(this, "RestCustomFacingZ", 0.0, 0.0, -10.0, 10.0, 8)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isRestCustomFacingZVisible());
        this.settingInputForwardLegit = (BooleanSetting)new BooleanSetting(this, "InputForwardLegit", false).getSetting(() -> canExecute8());
        this.settingInputForward = new NumberSetting(this, "InputForward", 0.0, -1.0, 1.0, 2).getNumberSetting(() -> this.isInputForwardVisible());
        this.settingInputStrafeLegit = (BooleanSetting)new BooleanSetting(this, "InputStrafeLegit", false).getSetting(() -> canExecute7());
        this.settingInputStrafe = new NumberSetting(this, "InputStrafe", 0.0, -1.0, 1.0, 2).getNumberSetting(() -> this.isInputStrafeVisible2());
        this.settingInputJumpLegit = (BooleanSetting)new BooleanSetting(this, "InputJumpLegit", false).getSetting(() -> canExecute9());
        this.settingInputJump = (BooleanSetting)new BooleanSetting(this, "InputJump", false).getSetting(() -> this.isInputStrafeVisible3());
        this.settingInputSneakLegit = (BooleanSetting)new BooleanSetting(this, "InputSneakLegit", false).getSetting(() -> canExecute6());
        this.settingInputSneak = (BooleanSetting)new BooleanSetting(this, "InputSneak", false).getSetting(() -> this.isInputStrafeVisible());
        this.timer = new TimeHelper();
        this.tickDelay = 0;
        this.itemStack = (ItemStack)null;
        this.enabled2 = true;
        this.tickDelay6 = 0;
        this.tickDelay4 = 0;
        this.keyCode2 = 0;
        this.tickDelay3 = 0;
        this.activeState5 = false;
        this.activeState1 = false;
        this.expanded2 = false;
    }

    @Override
    public void onEnable() {
        this.registerEvent(7);
        this.registerEvent(21);
        this.registerEvent(23);
        this.registerEvent(43);
        this.registerEvent(18);
        this.registerEvent(26);
        this.registerEvent(1);
        this.registerEvent(36, 18);
        this.registerEvent(25);
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
        minecraft.getTimer().scaleFactor2 = 1.0F;
        this.activeState5 = false;
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onCameraTransform(EventCameraTransform event) {
        ItemStack itemStack2 = this.getItemStack();
        if (!this.checkItemStack(itemStack2)) {
            Item item = itemStack2.getItem();
            if ((this.checkItem3(item) ? 1 : 0) >= 0) {
                float restForward = (float)this.settingRestForward.getValue();
                event.processAction2(restForward);
                float restStrafe = (float)this.settingRestStrafe.getValue();
                event.processAction(restStrafe);
            }
        }
    }

    @Override
    public void onMoveInput(EventMoveInput event) {
        ItemStack itemStack2 = this.getItemStack();
        if (!this.checkItemStack(itemStack2)) {
            itemStack2.getItem();
            if (MoveUtil.canExecute()) {
                event.setActive(true);
            }
        }
    }

    @Override
    public void onClick(EventClick event) {
        this.getItemStack();
        this.tickDelay = 0;
    }

    @Override
    public void onAttackEntity(EventAttackEntity event) {
    }

    @Override
    public void onPacketSend(EventPacket packet) {
        Item item = null;
        boolean enabled = false;
        C08PacketPlayerBlockPlacement c08PacketPlayerBlockPlacement = null;
        boolean completed4 = false;
        Packet packet2 = null;
        boolean completed3 = false;
        boolean enabled3 = false;
        boolean completed2 = false;
        boolean completed = false;
        packet2 = packet.getPacket();
        ItemStack itemStack2 = null;
        NoSlow noSlow = null;
        if (packet2 instanceof C08PacketPlayerBlockPlacement && !minecraft.thePlayer.isUsingItem()) {
            C08PacketPlayerBlockPlacement c08PacketPlayerBlockPlacement2 = (C08PacketPlayerBlockPlacement)packet2;
            if (c08PacketPlayerBlockPlacement2.getEntityId() == 255) {
                ItemStack itemStack3 = c08PacketPlayerBlockPlacement2.getItemStack();
                if (this.checkItemStack2(itemStack3)) {
                    if (minecraft.thePlayer.indexOffset41 < 2) {
                        if (minecraft.thePlayer.onGround) {
                            minecraft.thePlayer.closeScreen();
                        }

                        this.expanded2 = true;
                    }
                }
            }
            if (!(packet2 instanceof C03PacketPlayer)) {
                completed = true;
            }
            if (!completed) {
                itemStack2 = this.getItemStack();
                noSlow = this;
            }
        } else {
            if (!(packet2 instanceof C03PacketPlayer)) {
                boolean enabled4 = NoSlowCheck.GRIM.isSelected();
                if (!enabled4) {
                    boolean enabled5 = packet2 instanceof C08PacketPlayerBlockPlacement;
                    if (!enabled5) {
                        return;
                    }
                    c08PacketPlayerBlockPlacement = (C08PacketPlayerBlockPlacement)packet2;
                    if (c08PacketPlayerBlockPlacement.getEntityId() != 255) {
                        return;
                    }
                    ItemStack itemStack4 = c08PacketPlayerBlockPlacement.getItemStack();
                    if (this.checkItemStack(itemStack4)) {
                        return;
                    }
                    item = itemStack4.getItem();
                    if (item == null) {
                        return;
                    }
                    enabled = this.checkItem3(item);
                    completed4 = true;
                }
                if (!completed4) {
                    enabled3 = packet2 instanceof C08PacketPlayerBlockPlacement;
                    completed2 = true;
                }
            }
            if (!completed4) {
                if (!completed2) {
                    itemStack2 = this.getItemStack();
                    noSlow = this;
                }
            }
        }
        if (!completed4) {
            if (!completed2) {
                if (!completed) {
                    if (noSlow.checkItemStack(itemStack2)) {
                        this.tickDelay = 0;
                        return;
                    }
                    Item item2 = itemStack2.getItem();
                    C03PacketPlayer c03PacketPlayer = (C03PacketPlayer)packet2;
                    if (this.checkItem3(item2)) {
                        if (VelocityCheck.ON_GROUND.isSelected()) {
                            c03PacketPlayer.setOnGround(true);
                        }
                    } else if (this.checkItem2(item2)) {
                        if (SprintCheck.ON_GROUND.isSelected()) {
                            c03PacketPlayer.setOnGround(true);
                        }
                    } else if (this.checkItem(item2)) {
                        if (NoSlowCheck.ON_GROUND.isSelected()) {
                            c03PacketPlayer.setOnGround(true);
                        }
                        if (!NoSlowCheck.GRIM.isSelected()) {
                            completed3 = true;
                        }
                        if (!completed3) {
                            enabled3 = packet2 instanceof C08PacketPlayerBlockPlacement;
                            completed2 = true;
                        }
                    }
                }
                if (!completed3) {
                    if (!completed2) {
                        if (!NoSlowCheck.GRIM.isSelected()) {
                            boolean enabled6 = packet2 instanceof C08PacketPlayerBlockPlacement;
                            if (!enabled6) {
                                return;
                            }
                            c08PacketPlayerBlockPlacement = (C08PacketPlayerBlockPlacement)packet2;
                            if (c08PacketPlayerBlockPlacement.getEntityId() != 255) {
                                return;
                            }
                            ItemStack itemStack5 = c08PacketPlayerBlockPlacement.getItemStack();
                            if (this.checkItemStack(itemStack5)) {
                                return;
                            }
                            item = itemStack5.getItem();
                            if (item == null) {
                                return;
                            }
                            enabled = this.checkItem3(item);
                            completed4 = true;
                        }
                        if (!completed4) {
                            enabled3 = packet2 instanceof C08PacketPlayerBlockPlacement;
                        }
                    }
                }
            }
            if (!completed4) {
                if (!completed3) {
                    if (enabled3 || packet2 instanceof C07PacketPlayerDigging) {
                        ItemStack itemStack6 = this.getItemStack();
                        if (!this.checkItemStack3(itemStack6, false)) {
                            Item item3 = itemStack6.getItem();
                            if (this.checkItem(item3)) {
                                if (packet2 instanceof C08PacketPlayerBlockPlacement) {
                                    minecraft.thePlayer.netHandlerPlayClient.handlePacket2(new C08PacketPlayerBlockPlacement(minecraft.thePlayer.getHeldItem()));
                                    minecraft.thePlayer
                                    .netHandlerPlayClient
                                    .handlePacket2(new C07PacketPlayerDigging(PlayerDiggingActionType.DROP_ITEM, BlockPos.origin, EnumFacing.DOWN));
                                    packet.cancel();
                                    KeyBinding.setKeyBindState(minecraft.gameSettings.keyBindUseItem.getKeyCode(), false);
                                    this.activeState5 = true;
                                } else if (this.activeState5
                                && ((C07PacketPlayerDigging)packet2).getPlayerDiggingActionType() == PlayerDiggingActionType.RELEASE_USE_ITEM) {
                                    this.activeState5 = false;
                                    packet.cancel();
                                }
                            }
                        }
                    }
                }
                boolean enabled7 = packet2 instanceof C08PacketPlayerBlockPlacement;
                if (!enabled7) {
                    return;
                }
                c08PacketPlayerBlockPlacement = (C08PacketPlayerBlockPlacement)packet2;
                if (c08PacketPlayerBlockPlacement.getEntityId() != 255) {
                    return;
                }
                ItemStack itemStack7 = c08PacketPlayerBlockPlacement.getItemStack();
                if (this.checkItemStack(itemStack7)) {
                    return;
                }
                item = itemStack7.getItem();
                if (item == null) {
                    return;
                }
                enabled = this.checkItem3(item);
            }
        }


        if (enabled) {
            if (VelocityCheck.INVALID_PACKET.isSelected()) {
                if (this.settingSwordNullItem.isEnabled()) {
                    c08PacketPlayerBlockPlacement.setItemStack(null);
                }

                if (this.settingSwordEditFacing.isEnabled()) {
                    c08PacketPlayerBlockPlacement.setEntityId((int)this.settingSwordEnumFacingID.getValue());
                }

                if (this.settingSwordCustomBlockPos.isEnabled()) {
                    double offset = this.settingSwordCustomBlockPosX.recalculate();
                    double offset2 = this.settingSwordCustomBlockPosY.recalculate();
                    c08PacketPlayerBlockPlacement.setBlockPos2(new BlockPos(offset, offset2, this.settingSwordCustomBlockPosZ.recalculate()));
                }

                if (this.settingSwordCustomFacing.isEnabled()) {
                    c08PacketPlayerBlockPlacement.setYawRotation2((float)this.settingSwordCustomFacingX.recalculate());
                    c08PacketPlayerBlockPlacement.setYawRotation((float)this.settingSwordCustomFacingY.recalculate());
                    c08PacketPlayerBlockPlacement.setYawRotation1((float)this.settingSwordCustomFacingZ.recalculate());
                }
            }
        } else if (this.checkItem2(item)) {
            if (SprintCheck.INVALID_PACKET.isSelected()) {
                if (this.settingBowNullItem.isEnabled()) {
                    c08PacketPlayerBlockPlacement.setItemStack(null);
                }

                if (this.settingBowEditFacing.isEnabled()) {
                    c08PacketPlayerBlockPlacement.setEntityId((int)this.settingBowEnumFacingID.getValue());
                }

                if (this.settingBowCustomBlockPos.isEnabled()) {
                    double offset3 = this.settingBowCustomBlockPosX.recalculate();
                    double offset4 = this.settingBowCustomBlockPosY.recalculate();
                    c08PacketPlayerBlockPlacement.setBlockPos2(new BlockPos(offset3, offset4, this.settingBowCustomBlockPosZ.recalculate()));
                }

                if (this.settingBowCustomFacing.isEnabled()) {
                    c08PacketPlayerBlockPlacement.setYawRotation2((float)this.settingBowCustomFacingX.recalculate());
                    c08PacketPlayerBlockPlacement.setYawRotation((float)this.settingBowCustomFacingY.recalculate());
                    c08PacketPlayerBlockPlacement.setYawRotation1((float)this.settingBowCustomFacingZ.recalculate());
                }
            }
        } else if (this.checkItem(item)) {
            if (NoSlowCheck.INVALID_PACKET.isSelected()) {
                if (this.settingRestNullItem.isEnabled()) {
                    c08PacketPlayerBlockPlacement.setItemStack(null);
                }

                if (this.settingRestEditFacing.isEnabled()) {
                    c08PacketPlayerBlockPlacement.setEntityId((int)this.settingRestEnumFacingID.getValue());
                }

                if (this.settingRestCustomBlockPos.isEnabled()) {
                    double offset5 = this.settingRestCustomBlockPosX.recalculate();
                    double offset6 = this.settingRestCustomBlockPosY.recalculate();
                    c08PacketPlayerBlockPlacement.setBlockPos2(new BlockPos(offset5, offset6, this.settingRestCustomBlockPosZ.recalculate()));
                }

                if (this.settingRestCustomFacing.isEnabled()) {
                    c08PacketPlayerBlockPlacement.setYawRotation2((float)this.settingRestCustomFacingX.recalculate());
                    c08PacketPlayerBlockPlacement.setYawRotation((float)this.settingRestCustomFacingY.recalculate());
                    c08PacketPlayerBlockPlacement.setYawRotation1((float)this.settingRestCustomFacingZ.recalculate());
                }
            }
        }
    }

    @Override
    public void onPostNetworkPacket(EventSendPacket packet) {
        Packet packet2 = packet.getPacket();
        if (VelocityCheck.BLINK.isSelected()) {
            if (this.tickDelay6 > 0) {
                packet.setCancelled(true);
            }

            if (packet2 instanceof C03PacketPlayer) {
                if (minecraft.thePlayer.isBlocking()) {
                    int index = this.tickDelay6 + 1;
                    this.tickDelay6 = index;
                } else {
                    this.tickDelay6 = 0;
                }
            }

            if (this.tickDelay6 > 3) {
                this.tickDelay6 = 0;
            }
        }
    }

    @Override
    public void onMotionUpdate(EventMotionUpdate event) {
        boolean enabled;
        Item item;
        if (!SprintCheck.SWITCH_CUSTOM.isSelected()) {
            if (NoSlowCheck.SWITCH_CUSTOM.isSelected()) {
                if (minecraft.thePlayer.indexOffset41 == 2 && this.expanded2) {
                    this.expanded2 = false;
                    minecraft.thePlayer.netHandlerPlayClient.handlePacket2(new C08PacketPlayerBlockPlacement(minecraft.thePlayer.getItemStack()));
                } else if (minecraft.thePlayer.isUsingItem()) {
                    ItemStack itemStack2 = this.getItemStack();
                    if (this.checkItemStack2(itemStack2)) {
                        double offset = event.getCoordOffset1() + 1.0E-14;
                        event.setCoordOffset1(offset);
                    }
                }
            }

            ItemStack itemStack3 = this.getItemStack();
            if (this.checkItemStack(itemStack3)) {
                this.activeState5 = false;
                return;
            }

            item = itemStack3.getItem();
            enabled = this.checkItem3(item);
        } else {
            if (minecraft.thePlayer.indexOffset41 == 2 && this.expanded2) {
                this.expanded2 = false;
                minecraft.thePlayer.netHandlerPlayClient.handlePacket2(new C08PacketPlayerBlockPlacement(minecraft.thePlayer.getItemStack()));
            } else if (minecraft.thePlayer.isUsingItem()) {
                ItemStack itemStack4 = this.getItemStack();
                if (this.checkItemStack2(itemStack4)) {
                    double offset2 = event.getCoordOffset1() + 1.0E-14;
                    event.setCoordOffset1(offset2);
                }
            }

            ItemStack itemStack5 = this.getItemStack();
            if (this.checkItemStack(itemStack5)) {
                this.activeState5 = false;
                return;
            }

            item = itemStack5.getItem();
            enabled = this.checkItem3(item);
        }

        if (enabled) {
            if (VelocityCheck.INPUT.isSelected()) {
                this.processAction();
            }

            if (MoveUtil.canExecute() && minecraft.thePlayer.ticksExisted % 5 == 0 && VelocityCheck.INVALID_DELAY.isSelected()) {
                minecraft.thePlayer.netHandlerPlayClient.handlePacket2(new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 1, null, 0.0F, 0.0F, 0.0F));
            }

            if (VelocityCheck.TOGGLE.isSelected()) {
                minecraft.thePlayer
                .netHandlerPlayClient
                .handlePacket(new C07PacketPlayerDigging(PlayerDiggingActionType.RELEASE_USE_ITEM, BlockPos.origin, EnumFacing.DOWN));
            }

            if (VelocityCheck.SWITCH.isSelected()
            && (
            this.settingSwitchMode.getSelected().equals("PrePosition")
            || this.settingSwitchMode.getSelected().equals("PrePostPosition")
            )) {
                int indexOffset = minecraft.thePlayer.inventoryPlayer.currentItem;
                int int2 = this.getInt2(indexOffset);
                if (int2 >= 0) {
                    minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C09PacketHeldItemChange(int2));
                    if (this.settingSwitchMode.getSelected().equals("PrePosition")) {
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C09PacketHeldItemChange(minecraft.thePlayer.inventoryPlayer.currentItem));
                    }
                }
            }

            if (VelocityCheck.SLOT.isSelected() && this.settingSlotMode.getSelected().equals("PrePosition")) {
                minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C09PacketHeldItemChange(minecraft.thePlayer.inventoryPlayer.currentItem));
            }

            if (VelocityCheck.SPAM.isSelected()) {
                if (this.settingSpamMode.getSelected().equals("PrePosition")) {
                    minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C08PacketPlayerBlockPlacement(minecraft.thePlayer.inventoryPlayer.getCurrentItem()));
                }
            }
        } else if (this.checkItem2(item)) {
            if (SprintCheck.INPUT.isSelected()) {
                this.processAction();
            }

            if (SprintCheck.TOGGLE.isSelected()) {
                minecraft.thePlayer
                .netHandlerPlayClient
                .handlePacket(new C07PacketPlayerDigging(PlayerDiggingActionType.RELEASE_USE_ITEM, BlockPos.origin, EnumFacing.DOWN));
            }

            if (SprintCheck.SWITCH.isSelected()
            && (
            this.settingSwitchMode.getSelected().equals("PrePosition")
            || this.settingSwitchMode.getSelected().equals("PrePostPosition")
            )) {
                int indexOffset2 = minecraft.thePlayer.inventoryPlayer.currentItem;
                int int22 = this.getInt2(indexOffset2);
                if (int22 >= 0) {
                    minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C09PacketHeldItemChange(int22));
                    if (this.settingSwitchMode.getSelected().equals("PrePosition")) {
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C09PacketHeldItemChange(minecraft.thePlayer.inventoryPlayer.currentItem));
                    }
                }
            }

            if (SprintCheck.PLACE.isSelected()) {
                minecraft.thePlayer
                .netHandlerPlayClient
                .handlePacket(new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), EnumFacing.UP.getIndexOffset1(), null, 0.0F, 0.0F, 0.0F));
            }

            if (SprintCheck.SLOT.isSelected() && this.settingSlotMode.getSelected().equals("PrePosition")) {
                minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C09PacketHeldItemChange(minecraft.thePlayer.inventoryPlayer.currentItem));
            }

            if (SprintCheck.SPAM.isSelected()) {
                if (this.settingSpamMode.getSelected().equals("PrePosition")) {
                    minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C08PacketPlayerBlockPlacement(minecraft.thePlayer.inventoryPlayer.getCurrentItem()));
                }
            }
        } else if (this.checkItem(item)) {
            if (NoSlowCheck.INPUT.isSelected()) {
                this.processAction();
            }

            if (NoSlowCheck.TOGGLE.isSelected()) {
                minecraft.thePlayer
                .netHandlerPlayClient
                .handlePacket(new C07PacketPlayerDigging(PlayerDiggingActionType.RELEASE_USE_ITEM, BlockPos.origin, EnumFacing.DOWN));
            }

            if (NoSlowCheck.SWITCH.isSelected()
            && (
            this.settingSwitchMode.getSelected().equals("PrePosition")
            || this.settingSwitchMode.getSelected().equals("PrePostPosition")
            )) {
                int indexOffset3 = minecraft.thePlayer.inventoryPlayer.currentItem;
                int int23 = this.getInt2(indexOffset3);
                if (int23 >= 0 && !ClientGlobals.moduleManager.scaffold.isToggled()) {
                    minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C09PacketHeldItemChange(int23));
                    if (this.settingSwitchMode.getSelected().equals("PrePosition")) {
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C09PacketHeldItemChange(minecraft.thePlayer.inventoryPlayer.currentItem));
                    }
                }
            }

            if (NoSlowCheck.SLOT.isSelected() && this.settingSlotMode.getSelected().equals("PrePosition")) {
                minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C09PacketHeldItemChange(minecraft.thePlayer.inventoryPlayer.currentItem));
            }

            if (NoSlowCheck.SPAM.isSelected() && this.settingSpamMode.getSelected().equals("PrePosition")) {
                minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C08PacketPlayerBlockPlacement(minecraft.thePlayer.inventoryPlayer.getCurrentItem()));
            }

            if (NoSlowCheck.SPAM2.isSelected()) {
                if (MoveUtil.canExecute()) {
                    if (minecraft.thePlayer.ticksExisted % 5 == 0) {
                        minecraft.thePlayer
                        .netHandlerPlayClient
                        .handlePacket(new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 1, null, 0.0F, 0.0F, 0.0F));
                    }
                }
            }
        }
    }

    @Override
    public void onPostAttackEntity() {
        ItemStack itemStack2 = this.getItemStack();
        if (!this.checkItemStack(itemStack2)) {
            Item item = itemStack2.getItem();
            if (this.checkItem3(item)) {
                if (VelocityCheck.SWITCH.isSelected() && this.settingSwitchMode.getSelected().equals("PostAttack")) {
                    int indexOffset = minecraft.thePlayer.inventoryPlayer.currentItem;
                    int index;
                    if ((index = this.getInt2(indexOffset)) >= 0 && !ClientGlobals.moduleManager.scaffold.isToggled()) {
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C09PacketHeldItemChange(index));
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C09PacketHeldItemChange(minecraft.thePlayer.inventoryPlayer.currentItem));
                    }
                }

                if (VelocityCheck.SLOT.isSelected() && this.settingSlotMode.getSelected().equals("PostAttack")) {
                    minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C09PacketHeldItemChange(minecraft.thePlayer.inventoryPlayer.currentItem));
                }

                if (VelocityCheck.SPAM.isSelected() && this.settingSpamMode.getSelected().equals("PostAttack")) {
                    minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C08PacketPlayerBlockPlacement(minecraft.thePlayer.inventoryPlayer.getCurrentItem()));
                }
            } else if (this.checkItem2(item)) {
                if (SprintCheck.SWITCH.isSelected() && this.settingSwitchMode.getSelected().equals("PostAttack")) {
                    int indexOffset2 = minecraft.thePlayer.inventoryPlayer.currentItem;
                    int index2;
                    if ((index2 = this.getInt2(indexOffset2)) >= 0 && !ClientGlobals.moduleManager.scaffold.isToggled()) {
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C09PacketHeldItemChange(index2));
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C09PacketHeldItemChange(minecraft.thePlayer.inventoryPlayer.currentItem));
                    }
                }

                if (SprintCheck.SLOT.isSelected() && this.settingSlotMode.getSelected().equals("PostAttack")) {
                    minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C09PacketHeldItemChange(minecraft.thePlayer.inventoryPlayer.currentItem));
                }

                if (SprintCheck.SPAM.isSelected() && this.settingSpamMode.getSelected().equals("PostAttack")) {
                    minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C08PacketPlayerBlockPlacement(minecraft.thePlayer.inventoryPlayer.getCurrentItem()));
                }
            } else if (this.checkItem(item)) {
                if (NoSlowCheck.SWITCH.isSelected() && this.settingSwitchMode.getSelected().equals("PostAttack")) {
                    int indexOffset3 = minecraft.thePlayer.inventoryPlayer.currentItem;
                    int index3;
                    if ((index3 = this.getInt2(indexOffset3)) >= 0 && !ClientGlobals.moduleManager.scaffold.isToggled()) {
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C09PacketHeldItemChange(index3));
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C09PacketHeldItemChange(minecraft.thePlayer.inventoryPlayer.currentItem));
                    }
                }

                if (NoSlowCheck.SLOT.isSelected() && this.settingSlotMode.getSelected().equals("PostAttack")) {
                    minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C09PacketHeldItemChange(minecraft.thePlayer.inventoryPlayer.currentItem));
                }

                if (NoSlowCheck.SPAM.isSelected() && this.settingSpamMode.getSelected().equals("PostAttack")) {
                    minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C08PacketPlayerBlockPlacement(minecraft.thePlayer.inventoryPlayer.getCurrentItem()));
                }
            }
        }
    }

    @Override
    public void onPreMoveInput(EventMoveInput event) {
        ItemStack itemStack2 = this.getItemStack();
        if (!this.checkItemStack(itemStack2)) {
            itemStack2.getItem();
            if (this.settingMode.getSelected().equals("AllDirectionMotion")) {
                event.setActive4(true);
            }
        }
    }

    @Override
    public void onMove(EventMove event) {
        this.motionMultiplier = this.motionMultiplier2;
        this.visible2 = this.activeState;
        this.motionMultiplier2 = event.getScaleFactor7();
        this.activeState = event.isState4();
        ItemStack itemStack2 = this.getItemStack();
        if (!this.checkItemStack(itemStack2)) {
            Item item = itemStack2.getItem();
            if (this.checkItem3(item)) {
                event.setState4(true);
                double value = this.settingRestSneakForward.getValue();
                event.setCoordOffset6(value);
                double value2 = this.settingRestSneakStrafe.getValue();
                event.setCoordOffset(value2);
            }
        }
    }

    @Override
    public void onPostMoveInput(EventMoveInput event) {
        ItemStack itemStack2 = this.getItemStack();
        if (!this.checkItemStack(itemStack2)) {
            itemStack2.getItem();
            if (this.settingMode.getSelected().equals("AllDirection")) {
                event.setActive5(true);
            }
        }
    }

    @Override
    public void onPostUpdateState() {
        ItemStack itemStack2 = this.getItemStack();
        if (this.checkItemStack(itemStack2)) {
            this.activeState1 = false;
            minecraft.getTimer().scaleFactor2 = 1.0F;
        }
    }

    @Override
    public void onRunTick() {
        ItemStack itemStack2 = this.getItemStack();
        if (!this.checkItemStack(itemStack2) && itemStack2.getItem() instanceof ItemSword) {
            minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C08PacketPlayerBlockPlacement(minecraft.thePlayer.getHeldItem()));
        }
    }

    @Override
    public void onPreMotionUpdate() {
        this.getItemStack();
        this.enabled2 = true;
    }

    @Override
    public void onPreTick() {
        int index = this.tickDelay4 + 1;
        this.tickDelay4 = index;
        if (minecraft.thePlayer.onGround) {
            this.tickDelay3++;
        } else {
            int index2 = this.keyCode2 + 1;
            this.keyCode2 = index2;
        }

        ItemStack itemStack2 = this.getItemStack();
        if (this.checkItemStack(itemStack2)) {
            this.keyCode2 = 0;
            this.tickDelay3 = 0;
            this.tickDelay4 = 0;
        }
    }

    private boolean checkItemStack(ItemStack itemStack2) {
        return this.checkItemStack3(itemStack2, true);
    }

    private boolean checkItem(Item item) {
        return item instanceof ItemFood;
    }

    private ItemStack getItemStack() {
        return minecraft.thePlayer.getHeldItem();
    }

    private boolean checkModeSetting(ModeSetting setting, NumberSetting setting2, NumberSetting setting3) {
        return this.settingEnableIntervals.isEnabled();
    }

    private boolean checkItem2(Item item) {
        return item instanceof ItemBow;
    }

    private boolean checkItemStack2(ItemStack itemStack2) {
        Item item = itemStack2.getItem();
        return this.checkItem2(item);
    }

    private boolean isRestCustomFacingXVisible() {
        return NoSlowCheck.INVALID_PACKET.isSelected();
    }

    private boolean checkItemStack3(ItemStack itemStack2, boolean enabled) {
        return itemStack2 != null ? minecraft.thePlayer.isUsingItem() : true;
    }

    private boolean isRestCustomFacingYVisible() {
        return NoSlowCheck.INVALID_PACKET.isSelected();
    }

    private boolean checkItem3(Item item) {
        return item instanceof ItemSword;
    }

    private void processAction() {
        minecraft.thePlayer
        .netHandlerPlayClient
        .handlePacket(
        new C0CPacketInput(
        this.settingInputStrafeLegit.isEnabled() ? minecraft.thePlayer.scaleFactor24 : (float)this.settingInputStrafe.getValue(),
        this.settingInputForwardLegit.isEnabled() ? minecraft.thePlayer.scaleFactor3 : (float)this.settingInputForward.getValue(),
        this.settingInputJumpLegit.isEnabled() ? minecraft.thePlayer.secondaryFloatEntry.state : this.settingInputJump.isEnabled(),
        this.settingInputSneakLegit.isEnabled() ? minecraft.thePlayer.secondaryFloatEntry.state2 : this.settingInputSneak.isEnabled()
        )
        );
    }

    private boolean isBowSlowdownIntervalGroundVisible() {
        return this.settingBowSlowdownInterval.getSelected().equals("None");
    }

    private int getInt2(int index) {
        ArrayList arrayList = new ArrayList();
        int index2 = 36;
        int index3;
        if (36 < minecraft.thePlayer.container.itemList2.size()) {
            ItemStack itemStack2 = minecraft.thePlayer.container.getSlot(index2).getStack();

            while (true) {
                if (itemStack2 == null) {
                    int index4 = index + 36;
                    if (index2 != index4) {
                        arrayList.add(index2 - 36);
                    }
                }

                index2++;
                if (index2 >= minecraft.thePlayer.container.itemList2.size()) {
                    if (!arrayList.isEmpty()) {
                        int index5 = arrayList.size() - 1;
                        return (Integer)arrayList.get(SettingContainer.settingProvider.getInt2(0, index5));
                    }

                    if (this.settingSwitchOnlyEmptySlot.isEnabled()) {
                        return -1;
                    }

                    if (!this.settingSwitchGomme.isEnabled()) {
                        int int2 = SettingContainer.settingProvider.getInt2(0, 8);
                        if (int2 == index) {
                            this.getInt2(index);
                        }

                        return int2;
                    }

                    index3 = index;
                    break;
                }

                itemStack2 = minecraft.thePlayer.container.getSlot(index2).getStack();
            }
        } else {
            if (!arrayList.isEmpty()) {
                int index6 = arrayList.size() - 1;
                return (Integer)arrayList.get(SettingContainer.settingProvider.getInt2(0, index6));
            }

            if (this.settingSwitchOnlyEmptySlot.isEnabled()) {
                return -1;
            }

            if (!this.settingSwitchGomme.isEnabled()) {
                int int22 = SettingContainer.settingProvider.getInt2(0, 8);
                if (int22 == index) {
                    this.getInt2(index);
                }

                return int22;
            }

            index3 = index;
        }

        int index7 = index3 == 0 ? 1 : -1;
        return index + index7;
    }

    private boolean isInputStrafeVisible() {
        return this.settingInputSneakLegit.isVisible();
    }

    private boolean isSwordCustomFacingZVisible() {
        return VelocityCheck.INVALID_PACKET.isSelected();
    }

    private boolean isRestEnumFacingIDVisible() {
        return NoSlowCheck.INVALID_PACKET.isSelected();
    }

    private boolean isSwordCustomFacingXVisible() {
        return VelocityCheck.INVALID_PACKET.isSelected();
    }

    private static boolean canExecute6() {
        return VelocityCheck.INPUT.isSelected();
    }

    private boolean isSwordCustomBlockPosXVisible() {
        return VelocityCheck.INVALID_PACKET.isSelected();
    }

    private boolean isRestCustomBlockPosYVisible() {
        return NoSlowCheck.INVALID_PACKET.isSelected();
    }

    private boolean isSwordSlowdownIntervalGroundVisible() {
        return this.settingSwordSlowdownInterval.getSelected().equals("None");
    }

    private boolean isInputStrafeVisible2() {
        return this.settingInputStrafeLegit.isVisible();
    }

    private boolean isRestSlowdownIntervalGroundVisible() {
        return this.settingRestSlowdownInterval.getSelected().equals("None");
    }

    private boolean isRestCustomFacingZVisible() {
        return NoSlowCheck.INVALID_PACKET.isSelected();
    }

    private boolean isRestSlowdownIntervalAirVisible() {
        return this.settingRestSlowdownInterval.getSelected().equals("None");
    }

    private boolean isSwordCustomFacingYVisible() {
        return VelocityCheck.INVALID_PACKET.isSelected();
    }

    private boolean isBowSlowdownIntervalVisible() {
        return SprintCheck.SLOWDOWN.isSelected();
    }

    private static boolean canExecute7() {
        return VelocityCheck.INPUT.isSelected();
    }

    private static boolean canExecute8() {
        return VelocityCheck.INPUT.isSelected();
    }

    private boolean isRestSlowdownIntervalVisible() {
        return NoSlowCheck.SLOWDOWN.isSelected();
    }

    private boolean isBowEnumFacingIDVisible() {
        return SprintCheck.INVALID_PACKET.isSelected();
    }

    private boolean isSwordEnumFacingIDVisible() {
        return VelocityCheck.INVALID_PACKET.isSelected();
    }

    private boolean isRestCustomBlockPosXVisible() {
        return NoSlowCheck.INVALID_PACKET.isSelected();
    }

    private boolean isRestCustomBlockPosZVisible() {
        return NoSlowCheck.INVALID_PACKET.isSelected();
    }

    private boolean isBowCustomBlockPosXVisible() {
        return SprintCheck.INVALID_PACKET.isSelected();
    }

    private boolean isBowCustomFacingYVisible() {
        return SprintCheck.INVALID_PACKET.isSelected();
    }

    private boolean isSwordCustomBlockPosZVisible() {
        return VelocityCheck.INVALID_PACKET.isSelected();
    }

    private boolean isBowCustomBlockPosYVisible() {
        return SprintCheck.INVALID_PACKET.isSelected();
    }

    private boolean isBowCustomFacingXVisible() {
        return SprintCheck.INVALID_PACKET.isSelected();
    }

    private boolean isSwordSlowdownIntervalVisible() {
        return VelocityCheck.SLOWDOWN.isSelected();
    }

    private boolean isSwordSlowdownIntervalAirVisible() {
        return this.settingSwordSlowdownInterval.getSelected().equals("None");
    }

    private static boolean canExecute9() {
        return VelocityCheck.INPUT.isSelected();
    }

    private boolean isInputForwardVisible() {
        return this.settingInputForwardLegit.isVisible();
    }

    private boolean isBowCustomBlockPosZVisible() {
        return SprintCheck.INVALID_PACKET.isSelected();
    }

    private boolean canExecute5() {
        float value = 0.8F;
        boolean riding = minecraft.thePlayer.isRiding();
        if ((float)minecraft.thePlayer.getFoodLevelManager().getIndex() > 6.0F
        || minecraft.thePlayer.invulnerableManager.state2) {
            boolean completed = false;
            boolean enabled = false;
            if (minecraft.thePlayer.onGround && !this.visible2 && this.motionMultiplier < value && this.motionMultiplier2 >= value && !riding) {
                if (!minecraft.thePlayer.checkPotion(Potion.potion16)) {
                    riding = true;
                }
                if (riding || !(this.motionMultiplier2 >= value)) {
                    completed = true;
                }
                if (!completed) {
                    enabled = minecraft.thePlayer.checkPotion(Potion.potion16);
                }
            } else {
                if (riding || !(this.motionMultiplier2 >= value)) {
                    if (riding) {
                        if (!(this.motionMultiplier2 < value)) {
                            if (minecraft.thePlayer.isCollidedHorizontally) {
                            }
                        } else {
                            riding = false;
                        }
                    }

                    return riding & true;
                }

                enabled = minecraft.thePlayer.checkPotion(Potion.potion16);
            }
            if (!completed) {
                if (!enabled) {
                    riding = true;
                }
            }


            if (riding) {
                if (!(this.motionMultiplier2 < value)) {
                    if (minecraft.thePlayer.isCollidedHorizontally) {
                    }
                } else {
                    riding = false;
                }
            }

            return riding & true;
        } else {
            if (minecraft.thePlayer.onGround && !this.visible2 && this.motionMultiplier < value) {
                int index = Float.compare(this.motionMultiplier2, value);
            }

            if (!riding) {
                int index2 = Float.compare(this.motionMultiplier2, value);
            }

            if (riding && (this.motionMultiplier2 < value || minecraft.thePlayer.isCollidedHorizontally)) {
                riding = false;
            }

            return riding & true;
        }
    }

    private boolean isBowCustomFacingZVisible() {
        return SprintCheck.INVALID_PACKET.isSelected();
    }

    private boolean isSwordCustomBlockPosYVisible() {
        return VelocityCheck.INVALID_PACKET.isSelected();
    }

    private boolean isInputStrafeVisible3() {
        return this.settingInputJumpLegit.isVisible();
    }

    private boolean isBowSlowdownIntervalAirVisible() {
        return this.settingBowSlowdownInterval.getSelected().equals("None");
    }
}

package net.augustus.modules.movement;

import java.awt.Color;
import java.util.ArrayList;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.ClickListener;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.events.listeners.SprintListener;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.MotionListener;
import net.augustus.utils.math.RandomNumberGenerator;
import net.augustus.events.EventClick;
import net.augustus.settings.Setting;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.events.EventPacketDelay;
import net.augustus.utils.MoveUtil;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.events.EventSlowDown;
import net.augustus.events.listeners.PacketDelayListener;
import net.augustus.utils.world.BlockUtil;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.listeners.SlowDownListener;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.events.EventMoveInput;
import net.augustus.events.EventAttackEntity;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.AttackEntityListener;
import net.augustus.settings.options.NoSlowBowOption;
import net.augustus.events.EventSprint;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.options.NoSlowRestItemOption;
import net.augustus.events.EventMotion;
import net.augustus.settings.options.NoSlowSwordOption;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.network.play.client.C0CPacketInput;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.potion.Potion;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class NoSlow
   extends Module
   implements ClickListener,
   MoveInputListener,
   SlowDownListener,
   UpdateListener,
   MotionListener,
   PacketListener,
   AttackEntityListener,
   SprintListener,
   PacketDelayListener,
   TickListener {
   public final BooleanValue onlyWhileMove;
   public final BooleanValue startSlow;
   public final BooleanValue enableIntervals;
   public final BooleanValue switchOnlyEmptySlot;
   public final BooleanValue switchGomme;
   public final StringValue switchMode;
   public final StringValue slotMode;
   public final StringValue spamMode;
   public final StringValue mode;
   private final SettingGroup sword;
   private final MultiOptionSetting swordOptions;
   public final DoubleValue swordForward;
   public final DoubleValue swordStrafe;
   public final StringValue swordSlowdownInterval;
   public final DoubleValue swordSlowdownIntervalAir;
   public final DoubleValue swordSlowdownIntervalGround;
   public final DoubleValue timerSword;
   public final DoubleValue swordSneakForward;
   public final DoubleValue swordSneakStrafe;
   public final DoubleValue swordMaxBlinkTicks;
   public final BooleanValue swordNullItem;
   public final BooleanValue swordEditFacing;
   public final DoubleValue swordEnumFacingID;
   public final BooleanValue swordCustomBlockPos;
   public final RandomizedRangeSettings swordCustomBlockPosX;
   public final RandomizedRangeSettings swordCustomBlockPosY;
   public final RandomizedRangeSettings swordCustomBlockPosZ;
   public final BooleanValue swordCustomFacing;
   public final RandomizedRangeSettings swordCustomFacingX;
   public final RandomizedRangeSettings swordCustomFacingY;
   public final RandomizedRangeSettings swordCustomFacingZ;
   private final SettingGroup bow;
   public final MultiOptionSetting bowOptions;
   public final DoubleValue bowForward;
   public final DoubleValue bowStrafe;
   public final StringValue bowSlowdownInterval;
   public final DoubleValue bowSlowdownIntervalAir;
   public final DoubleValue bowSlowdownIntervalGround;
   public final DoubleValue timerBow;
   public final DoubleValue bowSneakForward;
   public final DoubleValue bowSneakStrafe;
   public final BooleanValue bowNullItem;
   public final BooleanValue bowEditFacing;
   public final DoubleValue bowEnumFacingID;
   public final BooleanValue bowCustomBlockPos;
   public final RandomizedRangeSettings bowCustomBlockPosX;
   public final RandomizedRangeSettings bowCustomBlockPosY;
   public final RandomizedRangeSettings bowCustomBlockPosZ;
   public final BooleanValue bowCustomFacing;
   public final RandomizedRangeSettings bowCustomFacingX;
   public final RandomizedRangeSettings bowCustomFacingY;
   public final RandomizedRangeSettings bowCustomFacingZ;
   private final SettingGroup rest;
   public final MultiOptionSetting restOptions;
   public final DoubleValue restForward;
   public final DoubleValue restStrafe;
   public final StringValue restSlowdownInterval;
   public final DoubleValue restSlowdownIntervalAir;
   public final DoubleValue restSlowdownIntervalGround;
   public final DoubleValue timerRest;
   public final DoubleValue restSneakForward;
   public final DoubleValue restSneakStrafe;
   public final BooleanValue restNullItem;
   public final BooleanValue restEditFacing;
   public final DoubleValue restEnumFacingID;
   public final BooleanValue restCustomBlockPos;
   public final RandomizedRangeSettings restCustomBlockPosX;
   public final RandomizedRangeSettings restCustomBlockPosY;
   public final RandomizedRangeSettings restCustomBlockPosZ;
   public final BooleanValue restCustomFacing;
   public final RandomizedRangeSettings restCustomFacingX;
   public final RandomizedRangeSettings restCustomFacingY;
   public final RandomizedRangeSettings restCustomFacingZ;
   private final BooleanValue inputForwardLegit;
   private final DoubleValue inputForward;
   private final BooleanValue inputStrafeLegit;
   private final DoubleValue inputStrafe;
   private final BooleanValue inputJumpLegit;
   private final BooleanValue inputJump;
   private final BooleanValue inputSneakLegit;
   private final BooleanValue inputSneak;
   private final TimeHelper timeHelper;
   private int bugBypassState;
   private ItemStack lastUsedItemStack;
   private boolean shouldSendUnToggleRelease;
   private boolean sneakInput;
   private boolean lastSneakInput;
   private float forwardInput;
   private float lastForwardInput;
   private int swordBlinkTicks;
   private int usingTicks;
   private int airUsingTicks;
   private int groundUsingTicks;
   private boolean grimRestItemActive;
   private transient boolean blink2Delaying;
   private transient int blink2Ticks;
   private boolean timerModified;
   public boolean pendingCustomSwitchPlace;

   public NoSlow() {
       super("NoSlow", "NichtLangsam", new Color(10, 40, 53), Categorys.MOVEMENT);
      this.onlyWhileMove = new BooleanValue(this, "OnlyWhileMove", true);
      this.startSlow = new BooleanValue(this, "StartSlow", false);
      this.enableIntervals = new BooleanValue(this, "EnableIntervals", false);
      this.switchOnlyEmptySlot = new BooleanValue(this, "SwitchOnlyEmptySlot", false);
      this.switchGomme = new BooleanValue(this, "SwitchGomme", false);
      this.switchMode = new StringValue(
         this, "SwitchMode", "PreAttack", new String[]{"PreAttack", "PostAttack", "PrePosition", "PostPosition", "PrePostPosition"}
      );
      this.slotMode = new StringValue(this, "SlotMode", "PreAttack", new String[]{"PreAttack", "PostAttack", "PrePosition", "PostPosition"});
      this.spamMode = new StringValue(this, "SpamMode", "PostPosition", new String[]{"PreAttack", "PostAttack", "PrePosition", "PostPosition"});
      this.mode = new StringValue(this, "Mode", "Legit", new String[]{"Legit", "LegitMotion", "AllDirection", "AllDirectionMotion"});
      this.sword = new SettingGroup(this, "Sword");
      this.swordOptions = new MultiOptionSetting(this, "Sword", NoSlowSwordOption.values(), this.sword);
      this.swordForward = new DoubleValue(this, "SwordForward", 0.2, 0.2, 1.0, 2, this.sword).visibleWhen(NoSlowSwordOption.SLOWDOWN::isSelected);
      this.swordStrafe = new DoubleValue(this, "SwordStrafe", 0.2, 0.2, 1.0, 2, this.sword).visibleWhen(NoSlowSwordOption.SLOWDOWN::isSelected);
      StringValue setting = (StringValue)new StringValue(
         this, "SwordSlowdownInterval", "None", new String[]{"None", "TicksToNextSlowdownApply", "TicksToNextDelayApply"}, this.sword
      ).withDisplayName("SlowdownInterval");
      SettingVisibilityCondition condition3 = () -> {
         return NoSlowSwordOption.SLOWDOWN.isSelected() && this.enableIntervals.getValue();
      };
      this.swordSlowdownInterval = (StringValue)setting.withVisibility(condition3);
      DoubleValue swordSlowdownIntervalAir2 = new DoubleValue(this, "SwordSlowdownIntervalAir", 0.0, 1.0, 10.0, 0, this.sword).named("SlowdownIntervalAir");
      SettingVisibilityCondition condition4 = () -> {
         return !this.swordSlowdownInterval.getSelectedValue().equals("None") && NoSlowSwordOption.SLOWDOWN.isSelected() && this.enableIntervals.getValue();
      };
      this.swordSlowdownIntervalAir = swordSlowdownIntervalAir2.visibleWhen(condition4);
      this.swordSlowdownIntervalGround = new DoubleValue(this, "SwordSlowdownIntervalGround", 0.0, 1.0, 10.0, 0, this.sword).named("SlowdownIntervalGround").visibleWhen(() -> this.isSwordSlowdownIntervalGroundVisible());
      this.timerSword = new DoubleValue(this, "TimerSword", 0.2, 0.1, 2.0, 2, this.sword).visibleWhen(NoSlowSwordOption.TIMER::isSelected);
      this.swordSneakForward = new DoubleValue(this, "SwordSneakForward", 0.3, 0.3, 1.0, 2, this.sword).visibleWhen(NoSlowSwordOption.SNEAK::isSelected);
      this.swordSneakStrafe = new DoubleValue(this, "SwordSneakStrafe", 0.3, 0.3, 1.0, 2, this.sword).visibleWhen(NoSlowSwordOption.SNEAK::isSelected);
      this.swordMaxBlinkTicks = new DoubleValue(this, "SwordMaxBlinkTicks", 1.0, 0.0, 20.0, 0, this.sword).visibleWhen(NoSlowSwordOption.BLINK2::isSelected);
      this.swordNullItem = (BooleanValue)new BooleanValue(this, "SwordNullItem", false).withVisibility(NoSlowSwordOption.INVALID_PACKET::isSelected);
      this.swordEditFacing = (BooleanValue)new BooleanValue(this, "SwordEditFacing", false).withVisibility(NoSlowSwordOption.INVALID_PACKET::isSelected);
      this.swordEnumFacingID = new DoubleValue(this, "SwordEnumFacingID", 0.0, 0.0, 5.0, 0).visibleWhen(() -> this.isSwordEnumFacingIdVisible());
      this.swordCustomBlockPos = (BooleanValue)new BooleanValue(this, "SwordCustomBlockPos", true).withVisibility(NoSlowSwordOption.INVALID_PACKET::isSelected);
      this.swordCustomBlockPosX = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "SwordCustomBlockPosX", 0.0, 0.0, -10.0, 10.0, 8)).withoutNotInARow().withVisibility(() -> this.isSwordCustomBlockPosXVisible());
      this.swordCustomBlockPosY = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "SwordCustomBlockPosY", 0.0, 0.0, -10.0, 10.0, 8)).withoutNotInARow().withVisibility(() -> this.isSwordCustomBlockPosYVisible());
      this.swordCustomBlockPosZ = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "SwordCustomBlockPosZ", 0.0, 0.0, -10.0, 10.0, 8)).withoutNotInARow().withVisibility(() -> this.isSwordCustomBlockPosZVisible());
      this.swordCustomFacing = (BooleanValue)new BooleanValue(this, "SwordCustomFacing", false).withVisibility(NoSlowSwordOption.INVALID_PACKET::isSelected);
      this.swordCustomFacingX = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "SwordCustomFacingX", 0.0, 0.0, -10.0, 10.0, 8)).withoutNotInARow().withVisibility(() -> this.isSwordCustomFacingXVisible());
      this.swordCustomFacingY = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "SwordCustomFacingY", 0.0, 0.0, -10.0, 10.0, 8)).withoutNotInARow().withVisibility(() -> this.isSwordCustomFacingYVisible());
      this.swordCustomFacingZ = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "SwordCustomFacingZ", 0.0, 0.0, -10.0, 10.0, 8)).withoutNotInARow().withVisibility(() -> this.isSwordCustomFacingZVisible());
      this.bow = new SettingGroup(this, "Bow");
      this.bowOptions = new MultiOptionSetting(this, "Bow", NoSlowBowOption.values(), this.bow);
      this.bowForward = new DoubleValue(this, "BowForward", 0.2, 0.2, 1.0, 2, this.bow).visibleWhen(NoSlowBowOption.SLOWDOWN::isSelected);
      this.bowStrafe = new DoubleValue(this, "BowStrafe", 0.2, 0.2, 1.0, 2, this.bow).visibleWhen(NoSlowBowOption.SLOWDOWN::isSelected);
      this.bowSlowdownInterval = (StringValue)((StringValue)new StringValue(
         this, "BowSlowdownInterval", "None", new String[]{"None", "TicksToNextSlowdownApply", "TicksToNextDelayApply"}, this.bow
      ).withDisplayName("SlowdownInterval")).withVisibility(() -> this.isBowSlowdownIntervalVisible());
      this.bowSlowdownIntervalAir = new DoubleValue(this, "BowSlowdownIntervalAir", 0.0, 1.0, 10.0, 0, this.bow).named("SlowdownIntervalAir").visibleWhen(() -> this.isBowSlowdownIntervalAirVisible());
      this.bowSlowdownIntervalGround = new DoubleValue(this, "BowSlowdownIntervalGround", 0.0, 1.0, 10.0, 0, this.bow).named("SlowdownIntervalGround").visibleWhen(() -> this.isBowSlowdownIntervalGroundVisible());
      this.timerBow = new DoubleValue(this, "TimerBow", 0.2, 0.1, 2.0, 2, this.bow).visibleWhen(NoSlowBowOption.TIMER::isSelected);
      this.bowSneakForward = new DoubleValue(this, "BowSneakForward", 0.3, 0.3, 1.0, 2, this.bow).visibleWhen(NoSlowBowOption.SNEAK::isSelected);
      DoubleValue bowSneakStrafeSetting = new DoubleValue(this, "BowSneakStrafe", 0.3, 0.3, 1.0, 2, this.bow);
      this.bowSneakStrafe = bowSneakStrafeSetting.visibleWhen(NoSlowBowOption.SNEAK::isSelected);
      this.bowNullItem = (BooleanValue)new BooleanValue(this, "BowNullItem", false).withVisibility(NoSlowBowOption.INVALID_PACKET::isSelected);
      this.bowEditFacing = (BooleanValue)new BooleanValue(this, "BowEditFacing", false).withVisibility(NoSlowBowOption.INVALID_PACKET::isSelected);
      bowSneakStrafeSetting = new DoubleValue(this, "BowEnumFacingID", 0.0, 0.0, 5.0, 0);
      this.bowEnumFacingID = bowSneakStrafeSetting.visibleWhen(() -> this.isBowEnumFacingIdVisible());
      this.bowCustomBlockPos = (BooleanValue)new BooleanValue(this, "BowCustomBlockPos", true).withVisibility(NoSlowBowOption.INVALID_PACKET::isSelected);
      this.bowCustomBlockPosX = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "BowCustomBlockPosX", 0.0, 0.0, -10.0, 10.0, 8)).withoutNotInARow().withVisibility(() -> this.isBowCustomBlockPosXVisible());
      this.bowCustomBlockPosY = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "BowCustomBlockPosY", 0.0, 0.0, -10.0, 10.0, 8)).withoutNotInARow().withVisibility(() -> this.isBowCustomBlockPosYVisible());
      this.bowCustomBlockPosZ = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "BowCustomBlockPosZ", 0.0, 0.0, -10.0, 10.0, 8)).withoutNotInARow().withVisibility(() -> this.isBowCustomBlockPosZVisible());
      this.bowCustomFacing = (BooleanValue)new BooleanValue(this, "BowCustomFacing", false).withVisibility(NoSlowBowOption.INVALID_PACKET::isSelected);
      this.bowCustomFacingX = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "BowCustomFacingX", 0.0, 0.0, -10.0, 10.0, 8)).withoutNotInARow().withVisibility(() -> this.isBowCustomFacingXVisible());
      this.bowCustomFacingY = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "BowCustomFacingY", 0.0, 0.0, -10.0, 10.0, 8)).withoutNotInARow().withVisibility(() -> this.isBowCustomFacingYVisible());
      this.bowCustomFacingZ = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "BowCustomFacingZ", 0.0, 0.0, -10.0, 10.0, 8)).withoutNotInARow().withVisibility(() -> this.isBowCustomFacingZVisible());
      this.rest = new SettingGroup(this, "Rest");
      this.restOptions = new MultiOptionSetting(this, "Rest", NoSlowRestItemOption.values(), this.rest);
      bowSneakStrafeSetting = new DoubleValue(this, "RestForward", 0.2, 0.2, 1.0, 2, this.rest);
      this.restForward = bowSneakStrafeSetting.visibleWhen(NoSlowRestItemOption.SLOWDOWN::isSelected);
      bowSneakStrafeSetting = new DoubleValue(this, "RestStrafe", 0.2, 0.2, 1.0, 2, this.rest);
      this.restStrafe = bowSneakStrafeSetting.visibleWhen(NoSlowRestItemOption.SLOWDOWN::isSelected);
      StringValue setting2 = (StringValue)new StringValue(
         this, "RestSlowdownInterval", "None", new String[]{"None", "TicksToNextSlowdownApply", "TicksToNextDelayApply"}, this.rest
      ).withDisplayName("SlowdownInterval");
      SettingVisibilityCondition condition42 = () -> {
         return NoSlowRestItemOption.SLOWDOWN.isSelected() && this.enableIntervals.getValue();
      };
      this.restSlowdownInterval = (StringValue)setting2.withVisibility(condition42);
      bowSneakStrafeSetting = new DoubleValue(this, "RestSlowdownIntervalAir", 0.0, 1.0, 10.0, 0, this.rest);
      bowSneakStrafeSetting = bowSneakStrafeSetting.named("SlowdownIntervalAir");
      this.restSlowdownIntervalAir = bowSneakStrafeSetting.visibleWhen(() -> this.isRestSlowdownIntervalAirVisible());
      bowSneakStrafeSetting = new DoubleValue(this, "RestSlowdownIntervalGround", 0.0, 1.0, 10.0, 0, this.rest);
      bowSneakStrafeSetting = bowSneakStrafeSetting.named("SlowdownIntervalGround");
      this.restSlowdownIntervalGround = bowSneakStrafeSetting.visibleWhen(() -> this.isRestSlowdownIntervalGroundVisible());
      bowSneakStrafeSetting = new DoubleValue(this, "TimerRest", 0.2, 0.1, 2.0, 2, this.rest);
      this.timerRest = bowSneakStrafeSetting.visibleWhen(NoSlowRestItemOption.TIMER::isSelected);
      bowSneakStrafeSetting = new DoubleValue(this, "RestSneakForward", 0.3, 0.3, 1.0, 2, this.rest);
      this.restSneakForward = bowSneakStrafeSetting.visibleWhen(NoSlowRestItemOption.SNEAK::isSelected);
      bowSneakStrafeSetting = new DoubleValue(this, "RestSneakStrafe", 0.3, 0.3, 1.0, 2, this.rest);
      this.restSneakStrafe = bowSneakStrafeSetting.visibleWhen(NoSlowRestItemOption.SNEAK::isSelected);
      this.restNullItem = (BooleanValue)new BooleanValue(this, "RestNullItem", false).withVisibility(NoSlowRestItemOption.INVALID_PACKET::isSelected);
      this.restEditFacing = (BooleanValue)new BooleanValue(this, "RestEditFacing", false).withVisibility(NoSlowRestItemOption.INVALID_PACKET::isSelected);
      bowSneakStrafeSetting = new DoubleValue(this, "RestEnumFacingID", 0.0, 0.0, 5.0, 0);
      this.restEnumFacingID = bowSneakStrafeSetting.visibleWhen(() -> this.isRestEnumFacingIdVisible());
      this.restCustomBlockPos = (BooleanValue)new BooleanValue(this, "RestCustomBlockPos", true).withVisibility(NoSlowRestItemOption.INVALID_PACKET::isSelected);
      this.restCustomBlockPosX = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "RestCustomBlockPosX", 0.0, 0.0, -10.0, 10.0, 8)).withoutNotInARow().withVisibility(() -> this.isRestCustomBlockPosXVisible());
      this.restCustomBlockPosY = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "RestCustomBlockPosY", 0.0, 0.0, -10.0, 10.0, 8)).withoutNotInARow().withVisibility(() -> this.isRestCustomBlockPosYVisible());
      RandomizedRangeSettings randomizedRangeSettings15 = (new RandomizedRangeSettings(this, "RestCustomBlockPosZ", 0.0, 0.0, -10.0, 10.0, 8)).withoutNotInARow();
      SettingVisibilityCondition condition54 = () -> {
         return NoSlowRestItemOption.INVALID_PACKET.isSelected() && this.restCustomBlockPos.getValue();
      };
      this.restCustomBlockPosZ = (RandomizedRangeSettings)randomizedRangeSettings15.withVisibility(condition54);
      this.restCustomFacing = (BooleanValue)new BooleanValue(this, "RestCustomFacing", false).withVisibility(NoSlowRestItemOption.INVALID_PACKET::isSelected);
      this.restCustomFacingX = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "RestCustomFacingX", 0.0, 0.0, -10.0, 10.0, 8)).withoutNotInARow().withVisibility(() -> this.isRestCustomFacingXVisible());
      this.restCustomFacingY = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "RestCustomFacingY", 0.0, 0.0, -10.0, 10.0, 8)).withoutNotInARow().withVisibility(() -> this.isRestCustomFacingYVisible());
      this.restCustomFacingZ = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "RestCustomFacingZ", 0.0, 0.0, -10.0, 10.0, 8)).withoutNotInARow().withVisibility(() -> this.isRestCustomFacingZVisible());
      BooleanValue booleanValue13 = new BooleanValue(this, "InputForwardLegit", false);
      SettingVisibilityCondition condition59 = () -> {
         return NoSlowSwordOption.INPUT.isSelected() || NoSlowBowOption.INPUT.isSelected() || NoSlowRestItemOption.INPUT.isSelected();
      };
      this.inputForwardLegit = (BooleanValue)booleanValue13.withVisibility(condition59);
      bowSneakStrafeSetting = new DoubleValue(this, "InputForward", 0.0, -1.0, 1.0, 2);
      this.inputForward = bowSneakStrafeSetting.visibleWhen(() -> this.isInputForwardVisible());
      BooleanValue booleanValue14 = new BooleanValue(this, "InputStrafeLegit", false);
      SettingVisibilityCondition condition61 = () -> {
         return NoSlowSwordOption.INPUT.isSelected() || NoSlowBowOption.INPUT.isSelected() || NoSlowRestItemOption.INPUT.isSelected();
      };
      this.inputStrafeLegit = (BooleanValue)booleanValue14.withVisibility(condition61);
      bowSneakStrafeSetting = new DoubleValue(this, "InputStrafe", 0.0, -1.0, 1.0, 2);
      this.inputStrafe = bowSneakStrafeSetting.visibleWhen(() -> this.isInputStrafeVisible());
      BooleanValue booleanValue15 = new BooleanValue(this, "InputJumpLegit", false);
      SettingVisibilityCondition condition63 = () -> {
         return NoSlowSwordOption.INPUT.isSelected() || NoSlowBowOption.INPUT.isSelected() || NoSlowRestItemOption.INPUT.isSelected();
      };
      this.inputJumpLegit = (BooleanValue)booleanValue15.withVisibility(condition63);
      BooleanValue booleanValue16 = new BooleanValue(this, "InputJump", false);
      SettingVisibilityCondition condition64 = () -> {
         return this.inputJumpLegit.isVisible() && !this.inputJumpLegit.getValue();
      };
      this.inputJump = (BooleanValue)booleanValue16.withVisibility(condition64);
      BooleanValue booleanValue17 = new BooleanValue(this, "InputSneakLegit", false);
      SettingVisibilityCondition condition65 = () -> {
         return NoSlowSwordOption.INPUT.isSelected() || NoSlowBowOption.INPUT.isSelected() || NoSlowRestItemOption.INPUT.isSelected();
      };
      this.inputSneakLegit = (BooleanValue)booleanValue17.withVisibility(condition65);
      this.inputSneak = (BooleanValue)new BooleanValue(this, "InputSneak", false).withVisibility(() -> this.isInputSneakVisible());
      this.timeHelper = new TimeHelper();
      this.bugBypassState = 0;
      this.lastUsedItemStack = null;
      this.shouldSendUnToggleRelease = true;
      this.swordBlinkTicks = 0;
      this.usingTicks = 0;
      this.airUsingTicks = 0;
      this.groundUsingTicks = 0;
      this.grimRestItemActive = false;
      this.blink2Delaying = false;
      this.blink2Ticks = 0;
      this.timerModified = false;
      this.pendingCustomSwitchPlace = false;
   }

   @Override
   public void onEnable() {
      this.blink2Delaying = false;
   }

   @Override
   public void onDisable() {
      if (mc != null && mc.getTimer() != null) {
         mc.getTimer().timerSpeed = 1.0F;
      }
      this.grimRestItemActive = false;
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(21);
      this.toggleEventSubscription(23);
      this.toggleEventSubscription(43);
      this.toggleEventSubscription(18);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(1);
      this.toggleEventSubscription(36, 18);
      this.toggleEventSubscription(25);
      this.toggleEventSubscription(40);
   }

   @Override
   public void onEarlyTick() {
      this.usingTicks++;
      if (mc.thePlayer.onGround) {
         this.groundUsingTicks++;
      } else {
         this.airUsingTicks++;
      }

      if (this.shouldSkipNoSlow(this.getCurrentHeldItem())) {
         this.airUsingTicks = 0;
         this.groundUsingTicks = 0;
         this.usingTicks = 0;
      }
   }

   @Override
   public void onPacketSend(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof C08PacketPlayerBlockPlacement && !mc.thePlayer.isUsingItem()) {
         C08PacketPlayerBlockPlacement packet2 = (C08PacketPlayerBlockPlacement)packet;
         if (packet2.getPlacedBlockDirection() == 255) {
            if (this.isSwitchCustomItem(packet2.getStack()) && mc.thePlayer.airTicks < 2) {
               if (mc.thePlayer.onGround) {
                  mc.thePlayer.jump();
               }

               this.pendingCustomSwitchPlace = true;
            }
         }
      }

      if (packet instanceof C03PacketPlayer) {
         ItemStack currentHeldItem = this.getCurrentHeldItem();
         if (this.shouldSkipNoSlow(currentHeldItem)) {
            this.bugBypassState = 0;
            return;
         }

         Item item = currentHeldItem.getItem();
         C03PacketPlayer packet3 = (C03PacketPlayer)packet;
         if (this.isSwordItem(item)) {
            if (NoSlowSwordOption.ON_GROUND.isSelected()) {
               packet3.setOnGround(true);
            }
         } else if (this.isBowItem(item)) {
            if (NoSlowBowOption.ON_GROUND.isSelected()) {
               packet3.setOnGround(true);
            }
         } else if (this.isConsumableRestItem(item, currentHeldItem) && NoSlowRestItemOption.ON_GROUND.isSelected()) {
               packet3.setOnGround(true);
            }
      }

      if (NoSlowRestItemOption.GRIM.isSelected() && (packet instanceof C08PacketPlayerBlockPlacement || packet instanceof C07PacketPlayerDigging)) {
         ItemStack currentHeldItem2 = this.getCurrentHeldItem();
         if (!this.shouldSkipNoSlow(currentHeldItem2, false)) {
            if (this.isConsumableRestItem(currentHeldItem2.getItem(), currentHeldItem2)) {
               if (packet instanceof C08PacketPlayerBlockPlacement) {
                  mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C08PacketPlayerBlockPlacement(mc.thePlayer.getCurrentEquippedItem()));
                  mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.DROP_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
                  event.cancel();
                  KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), false);
                  this.grimRestItemActive = true;
               } else if (this.grimRestItemActive && ((C07PacketPlayerDigging)packet).getStatus() == C07PacketPlayerDigging.Action.RELEASE_USE_ITEM) {
                  this.grimRestItemActive = false;
                  event.cancel();
               }
            }
         }
      }

      if (packet instanceof C08PacketPlayerBlockPlacement) {
         C08PacketPlayerBlockPlacement packet4 = (C08PacketPlayerBlockPlacement)packet;
         if (packet4.getPlacedBlockDirection() == 255) {
            ItemStack stack2 = packet4.getStack();
            if (!this.shouldSkipNoSlow(stack2)) {
               Item item4 = stack2.getItem();
               if (item4 != null) {
                  if (this.isSwordItem(item4)) {
                     if (!NoSlowSwordOption.INVALID_PACKET.isSelected()) {
                        return;
                     }

                     if (this.swordNullItem.getValue()) {
                        packet4.setStack(null);
                     }

                     if (this.swordEditFacing.getValue()) {
                        packet4.setPlacedBlockDirection((int)this.swordEnumFacingID.getValue());
                     }

                     if (this.swordCustomBlockPos.getValue()) {
                        packet4.setPosition(new BlockPos(this.swordCustomBlockPosX.nextValue(), this.swordCustomBlockPosY.nextValue(), this.swordCustomBlockPosZ.nextValue()));
                     }

                     if (this.swordCustomFacing.getValue()) {
                        packet4.setFacingX((float)this.swordCustomFacingX.nextValue());
                        packet4.setFacingY((float)this.swordCustomFacingY.nextValue());
                        packet4.setFacingZ((float)this.swordCustomFacingZ.nextValue());
                     }
                  } else if (this.isBowItem(item4)) {
                     if (!NoSlowBowOption.INVALID_PACKET.isSelected()) {
                        return;
                     }

                     if (this.bowNullItem.getValue()) {
                        packet4.setStack(null);
                     }

                     if (this.bowEditFacing.getValue()) {
                        packet4.setPlacedBlockDirection((int)this.bowEnumFacingID.getValue());
                     }

                     if (this.bowCustomBlockPos.getValue()) {
                        packet4.setPosition(new BlockPos(this.bowCustomBlockPosX.nextValue(), this.bowCustomBlockPosY.nextValue(), this.bowCustomBlockPosZ.nextValue()));
                     }

                     if (this.bowCustomFacing.getValue()) {
                        packet4.setFacingX((float)this.bowCustomFacingX.nextValue());
                        packet4.setFacingY((float)this.bowCustomFacingY.nextValue());
                        packet4.setFacingZ((float)this.bowCustomFacingZ.nextValue());
                     }
                  } else if (this.isConsumableRestItem(item4, stack2)) {
                        if (!NoSlowRestItemOption.INVALID_PACKET.isSelected()) {
                           return;
                        }

                        if (this.restNullItem.getValue()) {
                           packet4.setStack(null);
                        }

                        if (this.restEditFacing.getValue()) {
                           packet4.setPlacedBlockDirection((int)this.restEnumFacingID.getValue());
                        }

                        if (this.restCustomBlockPos.getValue()) {
                           packet4.setPosition(new BlockPos(this.restCustomBlockPosX.nextValue(), this.restCustomBlockPosY.nextValue(), this.restCustomBlockPosZ.nextValue()));
                        }

                        if (this.restCustomFacing.getValue()) {
                           packet4.setFacingX((float)this.restCustomFacingX.nextValue());
                           packet4.setFacingY((float)this.restCustomFacingY.nextValue());
                           packet4.setFacingZ((float)this.restCustomFacingZ.nextValue());
                        }
                     }
               }
            }
         }
      }
   }

   @Override
   public void onOutgoingPacketDelay(EventPacketDelay event) {
      Packet packet = event.getPacket();
      if (NoSlowSwordOption.BLINK.isSelected()) {
         if (this.swordBlinkTicks > 0) {
            event.setDelayed(true);
         }

         if (packet instanceof C03PacketPlayer) {
            EntityPlayerSP thePlayer = mc.thePlayer;
            if (thePlayer != null && thePlayer.isBlocking()) {
               this.swordBlinkTicks++;
            } else {
               this.swordBlinkTicks = 0;
            }
         }

         if (this.swordBlinkTicks > 3) {
            this.swordBlinkTicks = 0;
         }
      }

      if (NoSlowSwordOption.BLINK2.isSelected()) {
         if (packet instanceof C08PacketPlayerBlockPlacement || packet instanceof C07PacketPlayerDigging || packet instanceof C03PacketPlayer) {
ItemStack currentHeldItem = this.getCurrentHeldItem();
if (currentHeldItem != null) {
if (this.isSwordItem(currentHeldItem.getItem())) {
if (packet instanceof C08PacketPlayerBlockPlacement) {
                     C08PacketPlayerBlockPlacement packet2 = (C08PacketPlayerBlockPlacement)packet;
                     if (packet2.getPlacedBlockDirection() == 255
                        && packet2.getPosition().equals(C08PacketPlayerBlockPlacement.INVALID_BLOCK_POS)
                        && packet2.getPlacedBlockOffsetX() == 0.0F
                        && packet2.getPlacedBlockOffsetY() == 0.0F
                        && packet2.getPlacedBlockOffsetZ() == 0.0F) {
                        this.blink2Delaying = false;
                        this.blink2Ticks = 0;
                     }
                  }

                  if (packet instanceof C07PacketPlayerDigging) {
                     C07PacketPlayerDigging packet3 = (C07PacketPlayerDigging)packet;
                     if (packet3.getStatus() == C07PacketPlayerDigging.Action.RELEASE_USE_ITEM && packet3.getPosition().equals(BlockPos.ORIGIN) && packet3.getFacing() == EnumFacing.DOWN) {
                        this.blink2Delaying = true;
                        this.blink2Ticks = 0;
                     }
                  }

                  if (packet instanceof C03PacketPlayer) {
                     if ((double)this.blink2Ticks >= this.swordMaxBlinkTicks.getValue() && this.blink2Delaying) {
                        this.blink2Delaying = false;
                        this.blink2Ticks = 0;
                     }

                     this.blink2Ticks++;
                  }
} else if (this.blink2Delaying) {
               this.blink2Delaying = false;
            }
} else if (this.blink2Delaying) {
               this.blink2Delaying = false;
            }
}

         if (this.blink2Delaying) {
            event.setDelayed(true);
         }
      }
   }

   @Override
   public void onSprint(EventSprint event) {
      ItemStack currentHeldItem = this.getCurrentHeldItem();
      if (this.shouldSkipNoSlow(currentHeldItem)) {
         this.timeHelper.reset();
      } else if (this.timeHelper.reached(400L) || !this.startSlow.getValue()) {
            Item item = currentHeldItem.getItem();
            if (this.mode.getSelectedValue().equals("AllDirection")) {
               if ((!this.isSwordItem(item) || !NoSlowSwordOption.SPRINT.isSelected()) && (!this.isBowItem(item) || !NoSlowBowOption.SPRINT.isSelected())) {
                  if (!this.isConsumableRestItem(item, currentHeldItem) || !NoSlowRestItemOption.SPRINT.isSelected()) {
                     return;
                  }
               }

               event.setForceSprint(true);
            }
         }
   }

   @Override
   public void onMotionSprint(EventSprint event) {
      ItemStack currentHeldItem = this.getCurrentHeldItem();
      if (this.shouldSkipNoSlow(currentHeldItem)) {
         this.timeHelper.reset();
      } else if (this.timeHelper.reached(400L) || !this.startSlow.getValue()) {
            Item item = currentHeldItem.getItem();
            if (!this.mode.getSelectedValue().equals("AllDirectionMotion")) {
               if (!this.mode.getSelectedValue().equals("LegitMotion")) {
                  return;
               }

               if (!this.canLegitSprint()) {
                  return;
               }
            }

            if ((!this.isSwordItem(item) || !NoSlowSwordOption.SPRINT.isSelected()) && (!this.isBowItem(item) || !NoSlowBowOption.SPRINT.isSelected())) {
               if (!this.isConsumableRestItem(item, currentHeldItem) || !NoSlowRestItemOption.SPRINT.isSelected()) {
                  return;
               }
            }

            event.setMotionSprint(true);
         }
   }

   @Override
   public void onJumpSprint(EventSprint event) {
      ItemStack currentHeldItem = this.getCurrentHeldItem();
      if (this.shouldSkipNoSlow(currentHeldItem)) {
         this.timeHelper.reset();
      } else if (this.timeHelper.reached(400L) || !this.startSlow.getValue()) {
            Item item = currentHeldItem.getItem();
            if (MoveUtil.isMoving()) {
               if (!this.mode.getSelectedValue().equals("AllDirectionMotion")) {
                  if (!this.mode.getSelectedValue().equals("LegitMotion")) {
                     return;
                  }

                  if (!this.canLegitSprint()) {
                     return;
                  }
               }

               if ((!this.isSwordItem(item) || !NoSlowSwordOption.SPRINT.isSelected()) && (!this.isBowItem(item) || !NoSlowBowOption.SPRINT.isSelected())) {
                  if (!this.isConsumableRestItem(item, currentHeldItem) || !NoSlowRestItemOption.SPRINT.isSelected()) {
                     return;
                  }
               }

               event.setOmniJumpSprint(true);
            }
         }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      this.lastForwardInput = this.forwardInput;
      this.lastSneakInput = this.sneakInput;
      this.forwardInput = event.getMoveForward();
      this.sneakInput = event.isSneak();
      ItemStack currentHeldItem = this.getCurrentHeldItem();
      if (this.shouldSkipNoSlow(currentHeldItem)) {
         this.timeHelper.reset();
      } else if (this.timeHelper.reached(400L) || !this.startSlow.getValue()) {
            Item item = currentHeldItem.getItem();
            if (this.isSwordItem(item)) {
               if (NoSlowSwordOption.SNEAK.isSelected()) {
                  event.setSneak(true);
                  event.setSneakForwardMultiplier(this.swordSneakForward.getValue());
                  event.setSneakStrafeMultiplier(this.swordSneakStrafe.getValue());
               }
            } else if (this.isBowItem(item)) {
               if (NoSlowBowOption.SNEAK.isSelected()) {
                  event.setSneak(true);
                  event.setSneakForwardMultiplier(this.bowSneakForward.getValue());
                  event.setSneakStrafeMultiplier(this.bowSneakStrafe.getValue());
               }
            } else if (this.isConsumableRestItem(item, currentHeldItem) && NoSlowRestItemOption.SNEAK.isSelected()) {
                  event.setSneak(true);
                  event.setSneakForwardMultiplier(this.restSneakForward.getValue());
                  event.setSneakStrafeMultiplier(this.restSneakStrafe.getValue());
               }
         }
   }

   @Override
   public void onSlowDown(EventSlowDown event) {
      ItemStack currentHeldItem = this.getCurrentHeldItem();
      if (this.shouldSkipNoSlow(currentHeldItem)) {
         this.timeHelper.reset();
      } else if (this.timeHelper.reached(400L) || !this.startSlow.getValue()) {
            Item item = currentHeldItem.getItem();
            if (this.isSwordItem(item)) {
               if (this.mode.getSelectedValue().equals("Legit")) {
                  event.setAllowSprint(NoSlowSwordOption.SPRINT.isSelected());
               }

               if (NoSlowSwordOption.SLOWDOWN.isSelected()) {
                  DoubleValue doubleSetting = this.swordSlowdownIntervalGround;
                  DoubleValue doubleSetting2 = this.swordSlowdownIntervalAir;
                  if (this.shouldApplySlowdownByInterval(this.swordSlowdownInterval, doubleSetting2, doubleSetting)) {
                     if (this.switchOnlyEmptySlot.getValue() && NoSlowSwordOption.SWITCH.isSelected()) {
                        if (this.findSwitchHotbarSlot(mc.thePlayer.inventory.currentItem) < 0) {
                           return;
                        }
                     }

                     event.setForwardMultiplier((float)this.swordForward.getValue());
                     event.setStrafeMultiplier((float)this.swordStrafe.getValue());
                  }
               }
            } else if (this.isBowItem(item)) {
               if (this.mode.getSelectedValue().equals("Legit")) {
                  event.setAllowSprint(NoSlowBowOption.SPRINT.isSelected());
               }

               if (NoSlowBowOption.SLOWDOWN.isSelected()) {
                  DoubleValue doubleSetting3 = this.bowSlowdownIntervalGround;
                  DoubleValue bowSlowdownIntervalAir2 = this.bowSlowdownIntervalAir;
                  if (this.shouldApplySlowdownByInterval(this.bowSlowdownInterval, bowSlowdownIntervalAir2, doubleSetting3)) {
                     if (this.switchOnlyEmptySlot.getValue() && NoSlowBowOption.SWITCH.isSelected()) {
                        if (this.findSwitchHotbarSlot(mc.thePlayer.inventory.currentItem) < 0) {
                           return;
                        }
                     }

                     event.setForwardMultiplier((float)this.bowForward.getValue());
                     event.setStrafeMultiplier((float)this.bowStrafe.getValue());
                  }
               }
            } else if (this.isConsumableRestItem(item, currentHeldItem)) {
                  if (NoSlowRestItemOption.UN_TOGGLE.isSelected() && this.shouldSendUnToggleRelease) {
                     mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.UP));
                     this.shouldSendUnToggleRelease = false;
                  }

                  if (this.mode.getSelectedValue().equals("Legit")) {
                     event.setAllowSprint(NoSlowRestItemOption.SPRINT.isSelected());
                  }

                  if (NoSlowRestItemOption.SLOWDOWN.isSelected()) {
                     DoubleValue doubleSetting4 = this.restSlowdownIntervalGround;
                     DoubleValue doubleSetting5 = this.restSlowdownIntervalAir;
                     if (this.shouldApplySlowdownByInterval(this.restSlowdownInterval, doubleSetting5, doubleSetting4)) {
                        if (this.switchOnlyEmptySlot.getValue() && NoSlowRestItemOption.SWITCH.isSelected()) {
                           if (this.findSwitchHotbarSlot(mc.thePlayer.inventory.currentItem) < 0) {
                              return;
                           }
                        }

                        event.setForwardMultiplier((float)this.restForward.getValue());
                        event.setStrafeMultiplier((float)this.restStrafe.getValue());
                     }
                  }
               }
         }
   }

   @Override
   public void onPreMotion(EventMotion event) {
      if (NoSlowBowOption.SWITCH_CUSTOM.isSelected() || NoSlowRestItemOption.SWITCH_CUSTOM.isSelected()) {
         if (mc.thePlayer.airTicks == 2 && this.pendingCustomSwitchPlace) {
            this.pendingCustomSwitchPlace = false;
            mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C08PacketPlayerBlockPlacement(mc.thePlayer.getHeldItem()));
         } else if (mc.thePlayer.isUsingItem()) {
            if (this.isSwitchCustomItem(this.getCurrentHeldItem())) {
               event.setY(event.getY() + 1.0E-14);
            }
         }
      }

      ItemStack currentHeldItem2 = this.getCurrentHeldItem();
      if (this.shouldSkipNoSlow(currentHeldItem2)) {
         this.grimRestItemActive = false;
      } else {
         Item item = currentHeldItem2.getItem();
         if (this.isSwordItem(item)) {
            if (NoSlowSwordOption.INPUT.isSelected()) {
               this.sendCustomInputPacket();
            }

            if (MoveUtil.isMoving() && mc.thePlayer.ticksExisted % 5 == 0 && NoSlowSwordOption.INVALID_DELAY.isSelected()) {
               mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 1, null, 0.0F, 0.0F, 0.0F));
            }

            if (NoSlowSwordOption.TOGGLE.isSelected()) {
               mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
            }

            if (NoSlowSwordOption.SWITCH.isSelected() && (this.switchMode.getSelectedValue().equals("PrePosition") || this.switchMode.getSelectedValue().equals("PrePostPosition"))) {
               int n = this.findSwitchHotbarSlot(mc.thePlayer.inventory.currentItem);
               if (n >= 0) {
                  mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(n));
                  if (this.switchMode.getSelectedValue().equals("PrePosition")) {
                     mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
                  }
               }
            }

            if (NoSlowSwordOption.SLOT.isSelected() && this.slotMode.getSelectedValue().equals("PrePosition")) {
               mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
            }

            if (NoSlowSwordOption.SPAM.isSelected() && this.spamMode.getSelectedValue().equals("PrePosition")) {
               mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(mc.thePlayer.inventory.getCurrentItem()));
            }
         } else if (this.isBowItem(item)) {
            if (NoSlowBowOption.INPUT.isSelected()) {
               this.sendCustomInputPacket();
            }

            if (NoSlowBowOption.TOGGLE.isSelected()) {
               mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
            }

            if (NoSlowBowOption.SWITCH.isSelected() && (this.switchMode.getSelectedValue().equals("PrePosition") || this.switchMode.getSelectedValue().equals("PrePostPosition"))) {
               int n2 = this.findSwitchHotbarSlot(mc.thePlayer.inventory.currentItem);
               if (n2 >= 0) {
                  mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(n2));
                  if (this.switchMode.getSelectedValue().equals("PrePosition")) {
                     mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
                  }
               }
            }

            if (NoSlowBowOption.PLACE.isSelected()) {
               mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), EnumFacing.UP.getIndex(), null, 0.0F, 0.0F, 0.0F));
            }

            if (NoSlowBowOption.SLOT.isSelected() && this.slotMode.getSelectedValue().equals("PrePosition")) {
               mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
            }

            if (NoSlowBowOption.SPAM.isSelected() && this.spamMode.getSelectedValue().equals("PrePosition")) {
               mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(mc.thePlayer.inventory.getCurrentItem()));
            }
         } else if (this.isConsumableRestItem(item, currentHeldItem2)) {
               if (NoSlowRestItemOption.INPUT.isSelected()) {
                  this.sendCustomInputPacket();
               }

               if (NoSlowRestItemOption.TOGGLE.isSelected()) {
                  mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
               }

               if (NoSlowRestItemOption.SWITCH.isSelected() && (this.switchMode.getSelectedValue().equals("PrePosition") || this.switchMode.getSelectedValue().equals("PrePostPosition"))) {
                  int n3 = this.findSwitchHotbarSlot(mc.thePlayer.inventory.currentItem);
                  if (n3 >= 0 && !ClientManagers.moduleManager.scaffold.isToggled()) {
                     mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(n3));
                     if (this.switchMode.getSelectedValue().equals("PrePosition")) {
                        mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
                     }
                  }
               }

               if (NoSlowRestItemOption.SLOT.isSelected() && this.slotMode.getSelectedValue().equals("PrePosition")) {
                  mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
               }

               if (NoSlowRestItemOption.SPAM.isSelected() && this.spamMode.getSelectedValue().equals("PrePosition")) {
                  mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(mc.thePlayer.inventory.getCurrentItem()));
               }

               if (NoSlowRestItemOption.SPAM2.isSelected() && MoveUtil.isMoving() && mc.thePlayer.ticksExisted % 5 == 0) {
                  mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 1, null, 0.0F, 0.0F, 0.0F));
               }
            }
      }
   }

   private boolean shouldApplySlowdownByInterval(StringValue booleansSetting, DoubleValue stringValue, DoubleValue stringValue2) {
      if (this.enableIntervals.getValue() && !booleansSetting.getSelectedValue().equalsIgnoreCase("None")) {
         if (stringValue.getValue() == stringValue2.getValue()) {
            if (stringValue.getValue() == 0.0) {
               return true;
            } else {
               int n = (int)(stringValue.getValue() + 1.0);
               return booleansSetting.getSelectedValue().equalsIgnoreCase("TicksToNextSlowdownApply") == (this.usingTicks % n == 0);
            }
         } else if (mc.thePlayer.onGround) {
            if (stringValue2.getValue() == 0.0) {
               return true;
            } else {
               int n2 = (int)(stringValue2.getValue() + 1.0);
               return booleansSetting.getSelectedValue().equalsIgnoreCase("TicksToNextSlowdownApply") == (this.groundUsingTicks % n2 == 0);
            }
         } else if (stringValue.getValue() == 0.0) {
            return true;
         } else {
            int n3 = (int)(stringValue.getValue() + 1.0);
            return booleansSetting.getSelectedValue().equalsIgnoreCase("TicksToNextSlowdownApply") == (this.airUsingTicks % n3 == 0);
         }
      } else {
         return true;
      }
   }

   private int findSwitchHotbarSlot(int n) {
      ArrayList list = new ArrayList();

      for (int i = 36; i < mc.thePlayer.inventoryContainer.inventorySlots.size(); i++) {
         if (mc.thePlayer.inventoryContainer.getSlot(i).getStack() == null && i != n + 36) {
            list.add(i - 36);
         }
      }

      if (!list.isEmpty()) {
         return (Integer)list.get(DefaultRandomGenerator.instance.nextInt((byte)0, list.size() - 1));
      } else if (this.switchOnlyEmptySlot.getValue()) {
         return -1;
      } else if (this.switchGomme.getValue()) {
         return n + (n == 0 ? 1 : -1);
      } else {
         int n4 = DefaultRandomGenerator.instance.nextInt((byte)0, (byte)8);
         if (n4 == n) {
            this.findSwitchHotbarSlot(n);
         }

         return n4;
      }
   }

   private boolean canLegitSprint() {
      float f = 0.8F;
      boolean isSprinting = mc.thePlayer.isSprinting();
      boolean flag = (float)mc.thePlayer.getFoodStats().getFoodLevel() > 6.0F || mc.thePlayer.capabilities.allowFlying;
      if (mc.thePlayer.onGround
         && !this.lastSneakInput
         && this.lastForwardInput < f
         && this.forwardInput >= f
         && !isSprinting
         && flag
         && !mc.thePlayer.isPotionActive(Potion.blindness)) {
         isSprinting = true;
      }

      if (!isSprinting && this.forwardInput >= f && flag && !mc.thePlayer.isPotionActive(Potion.blindness)) {
         isSprinting = true;
      }

      if (isSprinting && (this.forwardInput < f || mc.thePlayer.isCollidedHorizontally && !flag)) {
         isSprinting = false;
      }

      return isSprinting;
   }

   @Override
   public void onPostMotion() {
      ItemStack currentHeldItem = this.getCurrentHeldItem();
      if (this.shouldSkipNoSlow(currentHeldItem)) {
         this.shouldSendUnToggleRelease = true;
      } else {
         Item item = currentHeldItem.getItem();
         if (this.isSwordItem(item)) {
            if (NoSlowSwordOption.TOGGLE.isSelected()) {
               mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(mc.thePlayer.inventory.getCurrentItem()));
            }

            if (NoSlowSwordOption.SWITCH.isSelected() && this.switchMode.getSelectedValue().equals("PostPosition")) {
               int n = this.findSwitchHotbarSlot(mc.thePlayer.inventory.currentItem);
               if (n >= 0 && !ClientManagers.moduleManager.scaffold.isToggled()) {
                  mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(n));
                  mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
               }
            }

            if (NoSlowSwordOption.SLOT.isSelected() && this.slotMode.getSelectedValue().equals("PostPosition")) {
               mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
            }

            if (NoSlowSwordOption.SPAM.isSelected() && this.spamMode.getSelectedValue().equals("PostPosition")) {
               mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(mc.thePlayer.inventory.getCurrentItem()));
            }
         } else if (this.isBowItem(item)) {
            if (NoSlowBowOption.TOGGLE.isSelected()) {
               mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(mc.thePlayer.inventory.getCurrentItem()));
            }

            if (NoSlowBowOption.SWITCH.isSelected() && this.switchMode.getSelectedValue().equals("PostPosition")) {
               int n2 = this.findSwitchHotbarSlot(mc.thePlayer.inventory.currentItem);
               if (n2 >= 0 && !ClientManagers.moduleManager.scaffold.isToggled()) {
                  mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(n2));
                  mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
               }
            }

            if (NoSlowBowOption.SLOT.isSelected() && this.slotMode.getSelectedValue().equals("PostPosition")) {
               mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
            }

            if (NoSlowBowOption.SPAM.isSelected() && this.spamMode.getSelectedValue().equals("PostPosition")) {
               mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(mc.thePlayer.inventory.getCurrentItem()));
            }
         } else if (this.isConsumableRestItem(item, currentHeldItem)) {
               if (NoSlowRestItemOption.TOGGLE.isSelected()) {
                  mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(mc.thePlayer.inventory.getCurrentItem()));
               }

               if (NoSlowRestItemOption.SWITCH.isSelected() && this.switchMode.getSelectedValue().equals("PostPosition")) {
                  int n3 = this.findSwitchHotbarSlot(mc.thePlayer.inventory.currentItem);
                  if (n3 >= 0 && !ClientManagers.moduleManager.scaffold.isToggled()) {
                     mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(n3));
                     mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
                  }
               }

               if (NoSlowRestItemOption.SLOT.isSelected() && this.slotMode.getSelectedValue().equals("PostPosition")) {
                  mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
               }

               if (NoSlowBowOption.SPAM.isSelected() && this.spamMode.getSelectedValue().equals("PostPosition")) {
                  mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(mc.thePlayer.inventory.getCurrentItem()));
               }
            }
      }
   }

   @Override
   public void onPreUpdate() {
      ItemStack currentHeldItem = this.getCurrentHeldItem();
      if (this.shouldSkipNoSlow(currentHeldItem)) {
         if (this.timerModified) {
            this.timerModified = false;
            mc.getTimer().timerSpeed = 1.0F;
         }
      } else {
         Item item = currentHeldItem.getItem();
         if (this.isSwordItem(item)) {
            if (NoSlowSwordOption.TIMER.isSelected()) {
               mc.getTimer().timerSpeed = (float)this.timerSword.getValue();
               this.timerModified = true;
            } else {
               mc.getTimer().timerSpeed = 1.0F;
            }
         } else if (this.isBowItem(item)) {
            if (NoSlowBowOption.TIMER.isSelected()) {
               mc.getTimer().timerSpeed = (float)this.timerBow.getValue();
               this.timerModified = true;
            } else {
               mc.getTimer().timerSpeed = 1.0F;
            }
         } else if (this.isConsumableRestItem(item, currentHeldItem)) {
               if (NoSlowRestItemOption.TIMER.isSelected()) {
                  mc.getTimer().timerSpeed = (float)this.timerRest.getValue();
                  this.timerModified = true;
               } else {
                  mc.getTimer().timerSpeed = 1.0F;
               }
            }
      }
   }

   @Override
   public void onTick() {
      ItemStack currentHeldItem = this.getCurrentHeldItem();
      if (!this.shouldSkipNoSlow(currentHeldItem)) {
         Item item = currentHeldItem.getItem();
         if (item instanceof ItemSword && NoSlowSwordOption.GRIM.isSelected()) {
            mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(mc.thePlayer.getCurrentEquippedItem()));
         }

         if (this.isSwordItem(item)) {
            if (NoSlowSwordOption.SWITCH.isSelected() && this.switchMode.getSelectedValue().equals("PostAttack")) {
               int n = this.findSwitchHotbarSlot(mc.thePlayer.inventory.currentItem);
               if (n >= 0 && !ClientManagers.moduleManager.scaffold.isToggled()) {
                  mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(n));
                  mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
               }
            }

            if (NoSlowSwordOption.SLOT.isSelected() && this.slotMode.getSelectedValue().equals("PostAttack")) {
               mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
            }

            if (NoSlowSwordOption.SPAM.isSelected() && this.spamMode.getSelectedValue().equals("PostAttack")) {
               mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(mc.thePlayer.inventory.getCurrentItem()));
            }
         } else if (this.isBowItem(item)) {
            if (NoSlowBowOption.SWITCH.isSelected() && this.switchMode.getSelectedValue().equals("PostAttack")) {
               int n2 = this.findSwitchHotbarSlot(mc.thePlayer.inventory.currentItem);
               if (n2 >= 0 && !ClientManagers.moduleManager.scaffold.isToggled()) {
                  mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(n2));
                  mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
               }
            }

            if (NoSlowBowOption.SLOT.isSelected() && this.slotMode.getSelectedValue().equals("PostAttack")) {
               mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
            }

            if (NoSlowBowOption.SPAM.isSelected() && this.spamMode.getSelectedValue().equals("PostAttack")) {
               mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(mc.thePlayer.inventory.getCurrentItem()));
            }
         } else if (this.isConsumableRestItem(item, currentHeldItem)) {
               if (NoSlowRestItemOption.SWITCH.isSelected() && this.switchMode.getSelectedValue().equals("PostAttack")) {
                  int n3 = this.findSwitchHotbarSlot(mc.thePlayer.inventory.currentItem);
                  if (n3 >= 0 && !ClientManagers.moduleManager.scaffold.isToggled()) {
                     mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(n3));
                     mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
                  }
               }

               if (NoSlowRestItemOption.SLOT.isSelected() && this.slotMode.getSelectedValue().equals("PostAttack")) {
                  mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
               }

               if (NoSlowRestItemOption.SPAM.isSelected() && this.spamMode.getSelectedValue().equals("PostAttack")) {
                  mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(mc.thePlayer.inventory.getCurrentItem()));
               }
            }
      }
   }

   @Override
   public void onClick(EventClick event) {
      ItemStack currentHeldItem = this.getCurrentHeldItem();
      if (this.shouldSkipNoSlow(currentHeldItem)) {
         this.bugBypassState = 0;
      } else {
         Item item = currentHeldItem.getItem();
         if (this.lastUsedItemStack != null && !this.lastUsedItemStack.equals(currentHeldItem)) {
            this.bugBypassState = 0;
         }

         if (this.isSwordItem(item)) {
            if (NoSlowSwordOption.GRIM.isSelected()) {
               mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, new BlockPos(0, 0, 0), EnumFacing.DOWN));
               mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange((mc.thePlayer.inventory.currentItem + 1) % 8));
               mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
               mc.playerController.currentPlayerItem = mc.thePlayer.inventory.currentItem;
            }

            if (NoSlowSwordOption.SWITCH.isSelected() && (this.switchMode.getSelectedValue().equals("PreAttack") || this.switchMode.getSelectedValue().equals("PrePostPosition"))) {
               int n = this.findSwitchHotbarSlot(mc.thePlayer.inventory.currentItem);
               if (n >= 0) {
                  if (this.switchMode.getSelectedValue().equals("PreAttack")) {
                     mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(n));
                  }

                  mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
                  mc.playerController.currentPlayerItem = mc.thePlayer.inventory.currentItem;
               }
            }

            if (NoSlowSwordOption.SLOT.isSelected() && this.slotMode.getSelectedValue().equals("PreAttack")) {
               mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
            }

            if (NoSlowSwordOption.SPAM.isSelected() && this.spamMode.getSelectedValue().equals("PreAttack")) {
               mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(mc.thePlayer.inventory.getCurrentItem()));
            }

            if (NoSlowSwordOption.BUG.isSelected()) {
               event.setAllowRightClick(false);
               if (this.bugBypassState != 1) {
                  mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C16PacketClientStatus(C16PacketClientStatus.EnumState.OPEN_INVENTORY_ACHIEVEMENT));
                  mc.thePlayer.stopUsingItem();
                  mc.thePlayer.closeScreen();
                  event.cancel();
                  this.bugBypassState = 1;
               }
            }
         } else if (this.isBowItem(item)) {
            if (NoSlowBowOption.SWITCH.isSelected() && (this.switchMode.getSelectedValue().equals("PreAttack") || this.switchMode.getSelectedValue().equals("PrePostPosition"))) {
               int n2 = this.findSwitchHotbarSlot(mc.thePlayer.inventory.currentItem);
               if (n2 >= 0) {
                  if (this.switchMode.getSelectedValue().equals("PreAttack")) {
                     mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(n2));
                  }

                  mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
                  mc.playerController.currentPlayerItem = mc.thePlayer.inventory.currentItem;
               }
            }

            if (NoSlowBowOption.SLOT.isSelected() && this.slotMode.getSelectedValue().equals("PreAttack")) {
               mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
            }

            if (NoSlowBowOption.SPAM.isSelected() && this.spamMode.getSelectedValue().equals("PreAttack")) {
               mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(mc.thePlayer.inventory.getCurrentItem()));
            }
         } else if (this.isConsumableRestItem(item, currentHeldItem)) {
               if (NoSlowRestItemOption.SWITCH.isSelected() && (this.switchMode.getSelectedValue().equals("PreAttack") || this.switchMode.getSelectedValue().equals("PrePostPosition"))) {
                  int n3 = this.findSwitchHotbarSlot(mc.thePlayer.inventory.currentItem);
                  if (n3 >= 0 && !ClientManagers.moduleManager.scaffold.isToggled()) {
                     if (this.switchMode.getSelectedValue().equals("PreAttack")) {
                        mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(n3));
                     }

                     mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
                     mc.playerController.currentPlayerItem = mc.thePlayer.inventory.currentItem;
                  }
               }

               if (NoSlowRestItemOption.SLOT.isSelected() && this.slotMode.getSelectedValue().equals("PreAttack")) {
                  mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
               }

               if (NoSlowRestItemOption.SPAM.isSelected() && this.spamMode.getSelectedValue().equals("PreAttack")) {
                  mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(mc.thePlayer.inventory.getCurrentItem()));
               }

               if (NoSlowRestItemOption.BUG.isSelected()) {
                  event.setAllowRightClick(false);
                  if (this.bugBypassState != 3) {
                     mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C16PacketClientStatus(C16PacketClientStatus.EnumState.OPEN_INVENTORY_ACHIEVEMENT));
                     mc.thePlayer.stopUsingItem();
                     mc.thePlayer.closeScreen();
                     event.cancel();
                     this.bugBypassState = 3;
                  }
               }
            }

         this.lastUsedItemStack = currentHeldItem;
      }
   }

   private boolean shouldSkipNoSlow(ItemStack stack) {
      return this.shouldSkipNoSlow(stack, true);
   }

   private boolean shouldSkipNoSlow(ItemStack stack, boolean flag) {
      return stack == null
         || !mc.thePlayer.isUsingItem() && flag
         || mc.thePlayer.moveForward == 0.0F && mc.thePlayer.moveStrafing == 0.0F && this.onlyWhileMove.getValue()
         || ClientManagers.moduleManager.airStuck.isToggled()
         || BlockUtil.isScaffoldActiveAndValid();
   }

   private ItemStack getCurrentHeldItem() {
      return mc.thePlayer.getCurrentEquippedItem();
   }

   private void sendCustomInputPacket() {
      mc.thePlayer
         .sendQueue
         .addToSendQueue(
            new C0CPacketInput(
               this.inputStrafeLegit.getValue() ? mc.thePlayer.moveStrafing : (float)this.inputStrafe.getValue(),
               this.inputForwardLegit.getValue() ? mc.thePlayer.moveForward : (float)this.inputForward.getValue(),
               this.inputJumpLegit.getValue() ? mc.thePlayer.movementInput.jump : this.inputJump.getValue(),
               this.inputSneakLegit.getValue() ? mc.thePlayer.movementInput.sneak : this.inputSneak.getValue()
            )
         );
   }

   @Override
   public void onAttackEntity(EventAttackEntity event) {
   }

   private boolean isSwitchCustomItem(ItemStack stack) {
      if (stack == null) {
         return false;
      } else {
         Item item = stack.getItem();
         if (!this.isBowItem(item) || !NoSlowBowOption.SWITCH_CUSTOM.isSelected()) {
            if (!this.isConsumableRestItem(item, stack) || !NoSlowRestItemOption.SWITCH_CUSTOM.isSelected()) {
               return false;
            }
         }

         return true;
      }
   }

   private boolean isConsumableRestItem(Item item, ItemStack stack) {
      return (item instanceof ItemFood || item instanceof ItemPotion && !ItemPotion.isSplash(stack.getMetadata()) || item instanceof ItemBucketMilk);
   }

   private boolean isBowItem(Item item) {
      return item instanceof ItemBow;
   }

   private boolean isSwordItem(Item item) {
      return item instanceof ItemSword;
   }

   private boolean isInputSneakVisible() {
      return this.inputSneakLegit.isVisible() && !this.inputSneakLegit.getValue();
   }

   private boolean isInputStrafeVisible() {
      return this.inputStrafeLegit.isVisible() && !this.inputStrafeLegit.getValue();
   }

   private boolean isInputForwardVisible() {
      return this.inputForwardLegit.isVisible() && !this.inputForwardLegit.getValue();
   }

   private boolean isRestCustomFacingZVisible() {
      return NoSlowRestItemOption.INVALID_PACKET.isSelected() && this.restCustomFacing.getValue();
   }

   private boolean isRestCustomFacingYVisible() {
      return NoSlowRestItemOption.INVALID_PACKET.isSelected() && this.restCustomFacing.getValue();
   }

   private boolean isRestCustomFacingXVisible() {
      return NoSlowRestItemOption.INVALID_PACKET.isSelected() && this.restCustomFacing.getValue();
   }

   private boolean isRestCustomBlockPosYVisible() {
      return NoSlowRestItemOption.INVALID_PACKET.isSelected() && this.restCustomBlockPos.getValue();
   }

   private boolean isRestCustomBlockPosXVisible() {
      return NoSlowRestItemOption.INVALID_PACKET.isSelected() && this.restCustomBlockPos.getValue();
   }

   private boolean isRestEnumFacingIdVisible() {
      return NoSlowRestItemOption.INVALID_PACKET.isSelected() && this.restEditFacing.getValue();
   }

   private boolean isRestSlowdownIntervalGroundVisible() {
      return !this.restSlowdownInterval.getSelectedValue().equals("None") && NoSlowRestItemOption.SLOWDOWN.isSelected() && this.enableIntervals.getValue();
   }

   private boolean isRestSlowdownIntervalAirVisible() {
      return !this.restSlowdownInterval.getSelectedValue().equals("None") && NoSlowRestItemOption.SLOWDOWN.isSelected() && this.enableIntervals.getValue();
   }

   private boolean isBowCustomFacingZVisible() {
      return NoSlowBowOption.INVALID_PACKET.isSelected() && this.bowCustomFacing.getValue();
   }

   private boolean isBowCustomFacingYVisible() {
      return NoSlowBowOption.INVALID_PACKET.isSelected() && this.bowCustomFacing.getValue();
   }

   private boolean isBowCustomFacingXVisible() {
      return NoSlowBowOption.INVALID_PACKET.isSelected() && this.bowCustomFacing.getValue();
   }

   private boolean isBowCustomBlockPosZVisible() {
      return NoSlowBowOption.INVALID_PACKET.isSelected() && this.bowCustomBlockPos.getValue();
   }

   private boolean isBowCustomBlockPosYVisible() {
      return NoSlowBowOption.INVALID_PACKET.isSelected() && this.bowCustomBlockPos.getValue();
   }

   private boolean isBowCustomBlockPosXVisible() {
      return NoSlowBowOption.INVALID_PACKET.isSelected() && this.bowCustomBlockPos.getValue();
   }

   private boolean isBowEnumFacingIdVisible() {
      return NoSlowBowOption.INVALID_PACKET.isSelected() && this.bowEditFacing.getValue();
   }

   private boolean isBowSlowdownIntervalGroundVisible() {
      return !this.bowSlowdownInterval.getSelectedValue().equals("None") && NoSlowBowOption.SLOWDOWN.isSelected() && this.enableIntervals.getValue();
   }

   private boolean isBowSlowdownIntervalAirVisible() {
      return !this.bowSlowdownInterval.getSelectedValue().equals("None") && NoSlowBowOption.SLOWDOWN.isSelected() && this.enableIntervals.getValue();
   }

   private boolean isBowSlowdownIntervalVisible() {
      return NoSlowBowOption.SLOWDOWN.isSelected() && this.enableIntervals.getValue();
   }

   private boolean isSwordCustomFacingZVisible() {
      return NoSlowSwordOption.INVALID_PACKET.isSelected() && this.swordCustomFacing.getValue();
   }

   private boolean isSwordCustomFacingYVisible() {
      return NoSlowSwordOption.INVALID_PACKET.isSelected() && this.swordCustomFacing.getValue();
   }

   private boolean isSwordCustomFacingXVisible() {
      return NoSlowSwordOption.INVALID_PACKET.isSelected() && this.swordCustomFacing.getValue();
   }

   private boolean isSwordCustomBlockPosZVisible() {
      return NoSlowSwordOption.INVALID_PACKET.isSelected() && this.swordCustomBlockPos.getValue();
   }

   private boolean isSwordCustomBlockPosYVisible() {
      return NoSlowSwordOption.INVALID_PACKET.isSelected() && this.swordCustomBlockPos.getValue();
   }

   private boolean isSwordCustomBlockPosXVisible() {
      return NoSlowSwordOption.INVALID_PACKET.isSelected() && this.swordCustomBlockPos.getValue();
   }

   private boolean isSwordEnumFacingIdVisible() {
      return NoSlowSwordOption.INVALID_PACKET.isSelected() && this.swordEditFacing.getValue();
   }

   private boolean isSwordSlowdownIntervalGroundVisible() {
      return !this.swordSlowdownInterval.getSelectedValue().equals("None") && NoSlowSwordOption.SLOWDOWN.isSelected() && this.enableIntervals.getValue();
   }


}

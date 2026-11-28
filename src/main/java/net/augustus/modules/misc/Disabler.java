package net.augustus.modules.misc;

import net.minecraft.util.MovingObjectPosition;
import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.ClickListener;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.events.listeners.MotionListener;
import net.augustus.utils.math.RandomNumberGenerator;
import net.augustus.events.EventClick;
import net.augustus.settings.RangeSetting;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.events.EventPacketDelay;
import net.augustus.settings.options.DisablerEntityActionOption;
import net.augustus.utils.MoveUtil;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.settings.options.DisablerDiggingActionOption;
import net.augustus.events.listeners.PacketDelayListener;
import net.augustus.events.EventSwing;
import net.augustus.settings.options.DisablerOutgoingPacketOption;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.Augustus;
import net.augustus.settings.options.DisablerIncomingPacketOption;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.options.DisablerPingSpoofPacketOption;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.events.EventMotion;
import net.augustus.events.listeners.SwingListener;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
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
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;
import net.minecraft.potion.Potion;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class Disabler extends Module implements UpdateListener, RenderListener, PacketListener, TickListener, MotionListener, ClickListener, SwingListener, PacketDelayListener {
   private final SettingGroup customPacketCanceling;
   private final SettingGroup incomingPackets;
   private final MultiOptionSetting incomingPacketsToCancel;
   private final SettingGroup outgoingPackets;
   private final MultiOptionSetting outGoingPacketsToCancel;
   private final SettingGroup detailedPackets;
   private final MultiOptionSetting entityActionsToCancel;
   private final MultiOptionSetting diggingPacket;
   private final BooleanValue pingSpoof;
   private final MultiOptionSetting pingSpoofPackets;
   private final RangeSetting delay;
   private final SettingGroup place;
   private final BooleanValue place1;
   private final BooleanValue notWhileAttack1;
   private final BooleanValue notWhileScaffold1;
   private final BooleanValue onlyWhileMove1;
   private final BooleanValue onlyWhileSpeedModule1;
   private final BooleanValue place2;
   private final BooleanValue notWhileAttack2;
   private final BooleanValue notWhileScaffold2;
   private final BooleanValue onlyWhileMove2;
   private final BooleanValue onlyWhileSpeedModule2;
   private final BooleanValue place3;
   private final BooleanValue notWhileAttack3;
   private final BooleanValue notWhileScaffold3;
   private final BooleanValue onlyWhileMove3;
   private final BooleanValue onlyWhileSpeedModule3;
   private final BooleanValue royalPixels;
   private final BooleanValue spectate;
   private final BooleanValue ride;
   private final BooleanValue karhuClicking;
   private final BooleanValue mmcReachSemi;
   private final BooleanValue onGroundTrue;
   private final BooleanValue tp;
   private final RandomizedRangeSettings tpDelay;
   private final RandomizedRangeSettings tpy;
   private final RandomizedRangeSettings tpxz;
   public final BooleanValue test;
   public final BooleanValue resourcePack;
   private final BooleanValue hover;
   private final BooleanValue grimSpec;
   private final BooleanValue grimSpecAlways;
   private final BooleanValue minibloxMoveFix;
   private final BooleanValue sneak;
   private final StringValue sneakType;
   private final DoubleValue sneakDelay;
   private final CopyOnWriteArrayList spoofedPackets;
   private final HashMap spoofedPacketTimestamps;
   private int transactionCounter;
   private int sneakTickCounter;
   private boolean cancelingTransactions;
   private final CopyOnWriteArrayList heldTransactionPackets;
   private final TimeHelper unusedTimerA;
   private boolean attackedThisTick;
   private boolean pendingSwingDigPackets;
   private final TimeHelper positionJitterTimer;
   private final TimeHelper unusedTimerB;
   private final CopyOnWriteArrayList unusedPacketList;
   private boolean unusedStateFlag;

   public Disabler() {
       super("Disabler", "Deaktivierer", new Color(73, 127, 163), Categorys.MISC);
      this.customPacketCanceling = new SettingGroup(this, "CustomPacketCanceling");
      this.incomingPackets = new SettingGroup(this, "IncomingPackets", this.customPacketCanceling);
      this.incomingPacketsToCancel = new DisablerIncomingPacketCancelSetting(this, this, "IncomingPacketsToCancel", DisablerIncomingPacketOption.values(), this.incomingPackets);
      this.outgoingPackets = new SettingGroup(this, "OutgoingPackets", this.customPacketCanceling);
      this.outGoingPacketsToCancel = new DisablerOutgoingPacketCancelSetting(this, this, "OutGoingPacketsToCancel", DisablerOutgoingPacketOption.values(), this.outgoingPackets);
      this.detailedPackets = new SettingGroup(this, "DetailedPackets", this.customPacketCanceling);
      this.entityActionsToCancel = new MultiOptionSetting(this, "EntityActionsToCancel", DisablerEntityActionOption.values(), this.detailedPackets);
      this.diggingPacket = new MultiOptionSetting(this, "DiggingPacket", DisablerDiggingActionOption.values(), this.detailedPackets);
      this.pingSpoof = new BooleanValue(this, "PingSpoof", false);
      MultiOptionSetting multiOptionSetting = new MultiOptionSetting(this, "PingSpoofPackets", DisablerPingSpoofPacketOption.values());
      BooleanValue pingSpoof2 = this.pingSpoof;
      BooleanValue booleanValue = pingSpoof2;
      this.pingSpoofPackets = multiOptionSetting.visibleWhen(() -> booleanValue.getValue());
      RangeSetting rangeSetting = new RangeSetting(this, "Delay", 1000.0, 1500.0, 1.0, 10000.0, 0);
      pingSpoof2 = this.pingSpoof;
      BooleanValue booleanValue2 = pingSpoof2;
      this.delay = rangeSetting.visibleWhen(() -> booleanValue2.getValue());
      this.place = new SettingGroup(this, "Place");
      this.place1 = new BooleanValue(this, "Place1", false, this.place);
      BooleanValue booleanValue4 = (BooleanValue)new BooleanValue(this, "NotWhileAttack1", true, this.place).withDisplayName("NotWhileAttack");
      BooleanValue place12 = this.place1;
      this.notWhileAttack1 = (BooleanValue)booleanValue4.withVisibility(() -> place12.getValue());
      BooleanValue booleanValue7 = (BooleanValue)new BooleanValue(this, "NotWhileScaffold1", true, this.place).withDisplayName("NotWhileScaffold");
      BooleanValue place13 = this.place1;
      this.notWhileScaffold1 = (BooleanValue)booleanValue7.withVisibility(() -> place13.getValue());
      BooleanValue booleanValue10 = (BooleanValue)new BooleanValue(this, "OnlyWhileMove1", true, this.place).withDisplayName("OnlyWhileMove");
      BooleanValue place14 = this.place1;
      this.onlyWhileMove1 = (BooleanValue)booleanValue10.withVisibility(() -> place14.getValue());
      BooleanValue booleanValue13 = (BooleanValue)new BooleanValue(this, "OnlyWhileSpeedModule1", true, this.place).withDisplayName("OnlyWhileSpeedModule");
      BooleanValue place15 = this.place1;
      this.onlyWhileSpeedModule1 = (BooleanValue)booleanValue13.withVisibility(() -> place15.getValue());
      this.place2 = new BooleanValue(this, "Place2", false, this.place);
      BooleanValue booleanValue16 = (BooleanValue)new BooleanValue(this, "NotWhileAttack2", true, this.place).withDisplayName("NotWhileAttack");
      BooleanValue place22 = this.place2;
      this.notWhileAttack2 = (BooleanValue)booleanValue16.withVisibility(() -> place22.getValue());
      BooleanValue booleanValue19 = (BooleanValue)new BooleanValue(this, "NotWhileScaffold2", true, this.place).withDisplayName("NotWhileScaffold");
      BooleanValue place23 = this.place2;
      this.notWhileScaffold2 = (BooleanValue)booleanValue19.withVisibility(() -> place23.getValue());
      BooleanValue booleanValue22 = (BooleanValue)new BooleanValue(this, "OnlyWhileMove2", true, this.place).withDisplayName("OnlyWhileMove");
      BooleanValue place24 = this.place2;
      this.onlyWhileMove2 = (BooleanValue)booleanValue22.withVisibility(() -> place24.getValue());
      BooleanValue booleanValue25 = (BooleanValue)new BooleanValue(this, "OnlyWhileSpeedModule2", true, this.place).withDisplayName("OnlyWhileSpeedModule");
      BooleanValue place25 = this.place2;
      this.onlyWhileSpeedModule2 = (BooleanValue)booleanValue25.withVisibility(() -> place25.getValue());
      this.place3 = new BooleanValue(this, "Place3", false, this.place);
      BooleanValue booleanValue28 = (BooleanValue)new BooleanValue(this, "NotWhileAttack3", true, this.place).withDisplayName("NotWhileAttack");
      BooleanValue place32 = this.place3;
      this.notWhileAttack3 = (BooleanValue)booleanValue28.withVisibility(() -> place32.getValue());
      BooleanValue booleanValue31 = (BooleanValue)new BooleanValue(this, "NotWhileScaffold3", true, this.place).withDisplayName("NotWhileScaffold");
      BooleanValue place33 = this.place3;
      this.notWhileScaffold3 = (BooleanValue)booleanValue31.withVisibility(() -> place33.getValue());
      BooleanValue booleanValue34 = (BooleanValue)new BooleanValue(this, "OnlyWhileMove3", true, this.place).withDisplayName("OnlyWhileMove");
      BooleanValue place34 = this.place3;
      this.onlyWhileMove3 = (BooleanValue)booleanValue34.withVisibility(() -> place34.getValue());
      BooleanValue booleanValue37 = (BooleanValue)new BooleanValue(this, "OnlyWhileSpeedModule3", true, this.place).withDisplayName("OnlyWhileSpeedModule");
      BooleanValue place35 = this.place3;
      this.onlyWhileSpeedModule3 = (BooleanValue)booleanValue37.withVisibility(() -> place35.getValue());
      this.royalPixels = new BooleanValue(this, "RoyalPixels", false);
      this.spectate = new BooleanValue(this, "Spectate", false);
      this.ride = new BooleanValue(this, "Ride", false);
      this.karhuClicking = new BooleanValue(this, "KarhuClicking", false);
      this.mmcReachSemi = new BooleanValue(this, "MMCReachSemi", false);
      this.onGroundTrue = new BooleanValue(this, "OnGroundTrue", false);
      this.tp = new BooleanValue(this, "Tp", false);
      RandomizedRangeSettings randomizedRangeSettings = (new RandomizedRangeSettings(this, "TPDelay", 1000.0, 1500.0, 1.0, 10000.0, 0)).withoutNotInARow();
      BooleanValue tp2 = this.tp;
      this.tpDelay = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> tp2.getValue());
      RandomizedRangeSettings randomizedRangeSettings2 = (new RandomizedRangeSettings(this, "TPY", 1024.0, 2048.0, 1024.0, 2048.0, 0)).withoutNotInARow();
      BooleanValue tp3 = this.tp;
      this.tpy = (RandomizedRangeSettings)randomizedRangeSettings2.withVisibility(() -> tp3.getValue());
      RandomizedRangeSettings randomizedRangeSettings3 = (new RandomizedRangeSettings(this, "TPXZ", 1024.0, 2048.0, 1024.0, 2048.0, 0)).withoutNotInARow();
      BooleanValue tp4 = this.tp;
      this.tpxz = (RandomizedRangeSettings)randomizedRangeSettings3.withVisibility(() -> tp4.getValue());
      this.test = new BooleanValue(this, "Test", false);
      this.resourcePack = new BooleanValue(this, "ResourcePack", false);
      this.hover = new BooleanValue(this, "Hover", false);
      this.grimSpec = new BooleanValue(this, "GrimSpec", false);
      BooleanValue booleanValue42 = new BooleanValue(this, "grimSpecAlways", false);
      BooleanValue grimSpec2 = this.grimSpec;
      this.grimSpecAlways = (BooleanValue)booleanValue42.withVisibility(() -> grimSpec2.getValue());
      this.minibloxMoveFix = new BooleanValue(this, "MinibloxMoveFix", false);
      this.sneak = new BooleanValue(this, "Sneak", false);
      StringValue setting = new StringValue(this, "SneakType", "StartStop", new String[]{"StartStop", "Start", "Stop"});
      BooleanValue sneak2 = this.sneak;
      this.sneakType = (StringValue)setting.withVisibility(() -> sneak2.getValue());
      DoubleValue sneakDelaySetting = new DoubleValue(this, "SneakDelay", 10.0, 1.0, 40.0, 0);
      BooleanValue sneak3 = this.sneak;
      this.sneakDelay = sneakDelaySetting.visibleWhen(() -> sneak3.getValue());
      this.spoofedPackets = new CopyOnWriteArrayList();
      this.spoofedPacketTimestamps = new HashMap();
      this.transactionCounter = 0;
      this.sneakTickCounter = 0;
      this.cancelingTransactions = false;
      this.heldTransactionPackets = new CopyOnWriteArrayList();
      this.unusedTimerA = new TimeHelper();
      this.attackedThisTick = false;
      this.pendingSwingDigPackets = false;
      this.positionJitterTimer = new TimeHelper();
      this.unusedTimerB = new TimeHelper();
      this.unusedPacketList = new CopyOnWriteArrayList();
      this.unusedStateFlag = false;

      for (DisablerIncomingPacketOption option : DisablerIncomingPacketOption.values()) {
         SettingGroup settingGroup = new SettingGroup(this, DisablerIncomingPacketOption.getOptionName(option), this.incomingPackets).visibleWhen(() -> DisablerIncomingPacketOption.isOptionSelected(option));
         DisablerIncomingPacketOption.setIntervalSetting(option, new DoubleValue(this, DisablerIncomingPacketOption.getOptionName(option) + "Interval", 0.0, 0.0, 20.0, 0, settingGroup).named("Interval"));
         DisablerIncomingPacketOption.setInvertIntervalSetting(option, (BooleanValue)new BooleanValue(this, DisablerIncomingPacketOption.getOptionName(option) + "InvertInterval", false, settingGroup).withDisplayName("InvertInterval"));
         DisablerIncomingPacketOption.setAlwaysSetting(option, (BooleanValue)new BooleanValue(this, DisablerIncomingPacketOption.getOptionName(option) + "Always", true, settingGroup).withDisplayName("Always"));
         RangeSetting rangeSetting2 = new RangeSetting(
            this, DisablerIncomingPacketOption.getOptionName(option) + "StartTick", DisablerIncomingPacketOption.getOptionName(option) + "EndTick", 0.0, 100.0, 0.0, 100.0, 0, settingGroup
         );
         String str16 = "EndTick";
         DisablerIncomingPacketOption.setTickRangeSetting(option, rangeSetting2.withBoundNames("StartTick", str16).visibleWhen(() -> isIncomingTickRangeVisible(option)));
         StringValue setting2 = (StringValue)new StringValue(
            this, DisablerIncomingPacketOption.getOptionName(option) + "CancelCondition", "Always", new String[]{"Always", "OnlyOnGround", "OnlyInAir"}, settingGroup
         ).withDisplayName("CancelCondition");
         DisablerIncomingPacketOption.setCancelConditionSetting(option, setting2);
      }

      for (DisablerOutgoingPacketOption option4 : DisablerOutgoingPacketOption.values()) {
         SettingGroup settingGroup2 = new SettingGroup(this, DisablerOutgoingPacketOption.getOptionName(option4), this.outgoingPackets).visibleWhen(() -> DisablerOutgoingPacketOption.isOptionSelected(option4));
         DisablerOutgoingPacketOption.setIntervalSetting(option4, new DoubleValue(this, DisablerOutgoingPacketOption.getOptionName(option4) + "Interval", 0.0, 0.0, 20.0, 0, settingGroup2).named("Interval"));
         DisablerOutgoingPacketOption.setInvertIntervalSetting(option4, (BooleanValue)new BooleanValue(this, DisablerOutgoingPacketOption.getOptionName(option4) + "InvertInterval", false, settingGroup2).withDisplayName("InvertInterval"));
         DisablerOutgoingPacketOption.setAlwaysSetting(option4, (BooleanValue)new BooleanValue(this, DisablerOutgoingPacketOption.getOptionName(option4) + "Always", true, settingGroup2).withDisplayName("Always"));
         RangeSetting rangeSetting5 = new RangeSetting(
            this, DisablerOutgoingPacketOption.getOptionName(option4) + "StartTick", DisablerOutgoingPacketOption.getOptionName(option4) + "EndTick", 0.0, 100.0, 0.0, 100.0, 0, settingGroup2
         );
         String str22 = "EndTick";
         DisablerOutgoingPacketOption.setTickRangeSetting(option4, rangeSetting5.withBoundNames("StartTick", str22).visibleWhen(() -> isOutgoingTickRangeVisible(option4)));
         StringValue setting3 = (StringValue)new StringValue(
            this, DisablerOutgoingPacketOption.getOptionName(option4) + "CancelCondition", "Always", new String[]{"Always", "OnlyOnGround", "OnlyInAir"}, settingGroup2
         ).withDisplayName("CancelCondition");
         DisablerOutgoingPacketOption.setCancelConditionSetting(option4, setting3);
      }

      for (DisablerEntityActionOption option7 : DisablerEntityActionOption.values()) {
         SettingGroup settingGroup3 = new SettingGroup(this, DisablerEntityActionOption.getOptionName(option7), this.detailedPackets).visibleWhen(() -> DisablerEntityActionOption.isOptionSelected(option7));
         DisablerEntityActionOption.setIntervalSetting(option7, new DoubleValue(this, DisablerEntityActionOption.getOptionName(option7) + "Interval", 0.0, 0.0, 20.0, 0, settingGroup3).named("Interval"));
         DisablerEntityActionOption.setInvertIntervalSetting(option7, (BooleanValue)new BooleanValue(this, DisablerEntityActionOption.getOptionName(option7) + "InvertInterval", false, settingGroup3).withDisplayName("InvertInterval"));
         DisablerEntityActionOption.setAlwaysSetting(option7, (BooleanValue)new BooleanValue(this, DisablerEntityActionOption.getOptionName(option7) + "Always", true, settingGroup3).withDisplayName("Always"));
         RangeSetting rangeSetting8 = new RangeSetting(
            this, DisablerEntityActionOption.getOptionName(option7) + "StartTick", DisablerEntityActionOption.getOptionName(option7) + "EndTick", 0.0, 100.0, 0.0, 100.0, 0, settingGroup3
         );
         String str28 = "EndTick";
         DisablerEntityActionOption.setTickRangeSetting(option7, rangeSetting8.withBoundNames("StartTick", str28).visibleWhen(() -> isEntityActionTickRangeVisible(option7)));
         StringValue setting4 = (StringValue)new StringValue(
            this, DisablerEntityActionOption.getOptionName(option7) + "CancelCondition", "Always", new String[]{"Always", "OnlyOnGround", "OnlyInAir"}, settingGroup3
         ).withDisplayName("CancelCondition");
         DisablerEntityActionOption.setCancelConditionSetting(option7, setting4);
      }

      for (DisablerDiggingActionOption option8 : DisablerDiggingActionOption.values()) {
         SettingGroup settingGroup4 = new SettingGroup(this, DisablerDiggingActionOption.getOptionName(option8), this.detailedPackets).visibleWhen(() -> DisablerDiggingActionOption.isOptionSelected(option8));
         DisablerDiggingActionOption.setIntervalSetting(option8, new DoubleValue(this, DisablerDiggingActionOption.getOptionName(option8) + "Interval", 0.0, 0.0, 20.0, 0, settingGroup4).named("Interval"));
         DisablerDiggingActionOption.setInvertIntervalSetting(option8, (BooleanValue)new BooleanValue(this, DisablerDiggingActionOption.getOptionName(option8) + "InvertInterval", false, settingGroup4).withDisplayName("InvertInterval"));
         DisablerDiggingActionOption.setAlwaysSetting(option8, (BooleanValue)new BooleanValue(this, DisablerDiggingActionOption.getOptionName(option8) + "Always", true, settingGroup4).withDisplayName("Always"));
         RangeSetting rangeSetting11 = new RangeSetting(
            this, DisablerDiggingActionOption.getOptionName(option8) + "StartTick", DisablerDiggingActionOption.getOptionName(option8) + "EndTick", 0.0, 100.0, 0.0, 100.0, 0, settingGroup4
         );
         String str34 = "EndTick";
         DisablerDiggingActionOption.setTickRangeSetting(option8, rangeSetting11.withBoundNames("StartTick", str34).visibleWhen(() -> isDiggingTickRangeVisible(option8)));
         StringValue setting5 = (StringValue)new StringValue(
            this, DisablerDiggingActionOption.getOptionName(option8) + "CancelCondition", "Always", new String[]{"Always", "OnlyOnGround", "OnlyInAir"}, settingGroup4
         ).withDisplayName("CancelCondition");
         DisablerDiggingActionOption.setCancelConditionSetting(option8, setting5);
      }
   }

   public static void noopHook() {
   }

   @Override
   public void onEnable() {
      this.spoofedPackets.clear();
      this.spoofedPacketTimestamps.clear();
      this.tpDelay.nextValue();
      this.tpy.nextValue();
      this.tpxz.nextValue();
      this.positionJitterTimer.reset();
      this.cancelingTransactions = false;
   }

   @Override
   public void onDisable() {
      if (this.pingSpoof.getValue() && mc.thePlayer != null && mc.theWorld != null && !this.spoofedPackets.isEmpty()) {
         Iterator it = this.spoofedPackets.iterator();

         while (it.hasNext()) {
            Packet packet = (Packet)it.next();

            try {
               mc.thePlayer.sendQueue.addToSendQueueUnchecked(packet);
               it.remove();
               this.spoofedPacketTimestamps.remove(packet);
            } catch (Exception e) {
            }
         }
      }

      this.spoofedPackets.clear();
      if (!this.heldTransactionPackets.isEmpty()
         && mc.thePlayer != null
         && mc.theWorld != null
         && mc.thePlayer.sendQueue != null
         && mc.getNetHandler() != null
         && mc.getNetHandler().getNetworkManager() != null
         && mc.getNetHandler().getNetworkManager().getNetHandler() != null) {
         this.heldTransactionPackets.forEach(obj -> {
            try {
               ((Packet)obj).processPacket(mc.getNetHandler().getNetworkManager().getNetHandler());
            } catch (Throwable e2) {
            }
         });
         this.heldTransactionPackets.clear();
      }
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(43);
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(18);
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(39);
      this.toggleEventSubscription(25);
   }

   @Override
   public void onOutgoingPacketDelay(EventPacketDelay event) {
      if (this.mmcReachSemi.getValue()) {
         event.setOutgoingDelayMs((short)500);
      }
   }

   @Override
   public void onIncomingPacketDelay(EventPacketDelay event) {
   }

   @Override
   public void onClick(EventClick event) {
      if (this.test.getValue() && mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
         Entity entityHit = mc.objectMouseOver.entityHit;
         if (entityHit instanceof EntityBoat || entityHit instanceof EntityMinecart) {
            if (mc.thePlayer.ticksExisted % 6 == 0) {
               event.addLeftClicks((byte)1);
            }

            float f = entityHit instanceof EntityBoat ? ((EntityBoat)entityHit).getDamageTaken() : ((EntityMinecart)entityHit).getDamage();
            float attributeValue = (float)mc.thePlayer.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
            float modifierForCreature = EnchantmentHelper.getModifierForCreature(mc.thePlayer.getHeldItem(), EnumCreatureAttribute.UNDEFINED);
            if (attributeValue > 0.0F || modifierForCreature > 0.0F) {
               if (mc.thePlayer.fallDistance > 0.0F && !mc.thePlayer.onGround && !mc.thePlayer.isOnLadder() && !mc.thePlayer.isInWater()) {
                  mc.thePlayer.isPotionActive(Potion.blindness);
               }

               attributeValue += modifierForCreature;
               if (f > 40.0F - attributeValue * 10.0F) {
                  event.addLeftClicks((byte)1);
                  event.addRightClicks((byte)1);
               }
            }
         }
      }
   }

   @Override
   public void onSwing(EventSwing event) {
      if (this.test.getValue() && mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
         Entity entityHit = mc.objectMouseOver.entityHit;
         if (entityHit instanceof EntityBoat || entityHit instanceof EntityMinecart) {
            if (mc.thePlayer.ticksExisted % 6 == 0) {
               event.setCancelClientSwing(true);
            }

            float f = entityHit instanceof EntityBoat ? ((EntityBoat)entityHit).getDamageTaken() : ((EntityMinecart)entityHit).getDamage();
            float attributeValue = (float)mc.thePlayer.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
            float modifierForCreature = EnchantmentHelper.getModifierForCreature(mc.thePlayer.getHeldItem(), EnumCreatureAttribute.UNDEFINED);
            if (attributeValue > 0.0F || modifierForCreature > 0.0F) {
               if (mc.thePlayer.fallDistance > 0.0F && !mc.thePlayer.onGround && !mc.thePlayer.isOnLadder() && !mc.thePlayer.isInWater()) {
                  mc.thePlayer.isPotionActive(Potion.blindness);
               }

               attributeValue += modifierForCreature;
               if (f > 40.0F - attributeValue * 10.0F) {
                  event.setCancelClientSwing(true);
               }
            }
         }
      }
   }

   @Override
   public void onPreMotion(EventMotion event) {
      if (this.sneak.getValue()) {
         this.sneakTickCounter++;
         if (this.sneakTickCounter % (int)Math.max(this.sneakDelay.getValue(), 1.0) == 0) {
            if (this.sneakType.getSelectedValue().contains("Start")) {
               mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.START_SNEAKING));
            }

            if (this.sneakType.getSelectedValue().contains("Stop")) {
               mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.STOP_SNEAKING));
            }
         }
      }

      if (this.hover.getValue() && mc.thePlayer.ticksExisted % 2 == 0 && mc.thePlayer.onGround && event.getY() % 1.0 == 0.0) {
         double d = Math.random() * 1.0E-4;
         event.setY(event.getY() + d);
         event.setOnGround(false);
      }

      if (this.tp.getValue()) {
         if (this.positionJitterTimer.reached((long)this.tpDelay.getLastIntValue())) {
            this.positionJitterTimer.reset();
            double y = event.getY();
            event.setY(y + (double)(this.tpy.getLastIntValue() * DefaultRandomGenerator.instance.nextInt((byte)0, (byte)100) % 2) == 0.0 ? -1.0 : 1.0);
            y = event.getX();
            event.setX(y + this.tpxz.nextValue() * (double)DefaultRandomGenerator.instance.nextInt((byte)0, (byte)100) % 2.0 == 0.0 ? -1.0 : 1.0);
            y = event.getZ();
            event.setZ(y + this.tpxz.nextValue() * (double)DefaultRandomGenerator.instance.nextInt((byte)0, (byte)100) % 2.0 == 0.0 ? -1.0 : 1.0);
            this.tpDelay.nextValue();
            this.tpy.nextValue();
            return;
         }
      }
   }

   @Override
   public void onPacketReceiveNetty(EventPacket event) {
      if (this.shouldCancelIncomingPacket(event.getPacket())) {
         event.cancel();
      }
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      Packet packet = event.getPacket();
      if (this.grimSpec.getValue()
         && mc.thePlayer != null
         && (packet instanceof S08PacketPlayerPosLook && mc.thePlayer.capabilities != null && mc.thePlayer.capabilities.isFlying || this.grimSpecAlways.getValue())) {
         this.cancelingTransactions = true;
      }

      if (packet instanceof S32PacketConfirmTransaction && this.cancelingTransactions && this.grimSpec.getValue()) {
         event.cancel();
         this.heldTransactionPackets.add(packet);
      }
   }

   @Override
   public void onPacketSend(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof C02PacketUseEntity && ((C02PacketUseEntity)packet).getAction() == net.minecraft.network.play.client.C02PacketUseEntity.Action.ATTACK) {
         this.attackedThisTick = true;
      }

      if (this.pingSpoof.getValue()) {
         Class clazz = packet.getClass();

         for (DisablerPingSpoofPacketOption option : DisablerPingSpoofPacketOption.values()) {
            if (clazz.equals(DisablerPingSpoofPacketOption.getPacketClass(option)) && DisablerPingSpoofPacketOption.isOptionSelected(option)) {
               this.spoofedPackets.add(packet);
               this.spoofedPacketTimestamps.put(packet, System.currentTimeMillis());
               event.cancel();
               break;
            }
         }
      }

      if (packet instanceof C03PacketPlayer && this.spectate.getValue()) {
         mc.thePlayer.sendQueue.addToSendQueue(new C18PacketSpectate(mc.thePlayer.getUniqueID()));
      }

      if (packet instanceof C03PacketPlayer && this.ride.getValue()) {
         mc.thePlayer.sendQueue.addToSendQueueUnchecked(packet);
         mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C0CPacketInput(mc.thePlayer.moveStrafing, mc.thePlayer.moveForward, mc.thePlayer.movementInput.jump, mc.thePlayer.movementInput.sneak));
         event.cancel();
      }

      if (this.karhuClicking.getValue()) {
         if (packet instanceof C0APacketAnimation && mc.objectMouseOver != null) {
            if (mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.MISS) {
               this.sendDuplicateDigPackets();
            } else if (mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
               this.pendingSwingDigPackets = true;
            }
         }

         if (packet instanceof C03PacketPlayer && this.pendingSwingDigPackets) {
            this.sendDuplicateDigPackets();
            this.pendingSwingDigPackets = false;
         }
      }

      if (this.onGroundTrue.getValue() && packet instanceof C03PacketPlayer) {
         ((C03PacketPlayer)packet).setOnGround(true);
      }
   }

   private void sendDuplicateDigPackets() {
      mc.thePlayer
         .sendQueue
         .addToSendQueue(
            new C07PacketPlayerDigging(net.minecraft.network.play.client.C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, BlockPos.ORIGIN, EnumFacing.UP)
         );
      mc.thePlayer
         .sendQueue
         .addToSendQueue(
            new C07PacketPlayerDigging(net.minecraft.network.play.client.C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, BlockPos.ORIGIN, EnumFacing.UP)
         );
   }

   @Override
   public void onPacketSendNetty(EventPacket event) {
      Packet packet = event.getPacket();
      if (this.shouldCancelOutgoingPacket(packet)) {
         event.cancel();
      }

      if (packet instanceof C0BPacketEntityAction) {
         C0BPacketEntityAction packet2 = (C0BPacketEntityAction)packet;

         for (DisablerEntityActionOption option : DisablerEntityActionOption.values()) {
            if (packet2.getAction() == DisablerEntityActionOption.getAction(option) && DisablerEntityActionOption.isOptionSelected(option)) {
               DisablerEntityActionOption.incrementPacketCount(option);
               if (DisablerEntityActionOption.getCancelConditionSetting(option).getSelectedValue().equals("Always")
                  || DisablerEntityActionOption.getCancelConditionSetting(option).getSelectedValue().equals("OnlyOnGround") && mc.thePlayer.onGround
                  || DisablerEntityActionOption.getCancelConditionSetting(option).getSelectedValue().equals("OnlyInAir") && !mc.thePlayer.onGround) {
                  if (this.matchesCancelModulo(DisablerEntityActionOption.getIntervalSetting(option), DisablerEntityActionOption.getInvertIntervalSetting(option), DisablerEntityActionOption.getPacketCount(option))) {
                     if (!DisablerEntityActionOption.getAlwaysSetting(option).getValue()) {
                        if (!((double)mc.thePlayer.ticksExisted > DisablerEntityActionOption.getTickRangeSetting(option).getMinValue())) {
                           break;
                        }

                        if (!(((double)mc.thePlayer.ticksExisted) < DisablerEntityActionOption.getTickRangeSetting(option).getMaxValue())) {
                           break;
                        }
                     }

                     event.cancel();
                  }
               }
               break;
            }
         }
      }

      if (packet instanceof C07PacketPlayerDigging) {
         C07PacketPlayerDigging packet3 = (C07PacketPlayerDigging)packet;

         for (DisablerDiggingActionOption option2 : DisablerDiggingActionOption.values()) {
            if (packet3.getStatus() == DisablerDiggingActionOption.getAction(option2) && DisablerDiggingActionOption.isOptionSelected(option2)) {
               DisablerDiggingActionOption.incrementPacketCount(option2);
               if (DisablerDiggingActionOption.getCancelConditionSetting(option2).getSelectedValue().equals("Always")
                  || DisablerDiggingActionOption.getCancelConditionSetting(option2).getSelectedValue().equals("OnlyOnGround") && mc.thePlayer.onGround
                  || DisablerDiggingActionOption.getCancelConditionSetting(option2).getSelectedValue().equals("OnlyInAir") && !mc.thePlayer.onGround) {
                  if (this.matchesCancelModulo(DisablerDiggingActionOption.getIntervalSetting(option2), DisablerDiggingActionOption.getInvertIntervalSetting(option2), DisablerDiggingActionOption.getPacketCount(option2))) {
                     if (!DisablerDiggingActionOption.getAlwaysSetting(option2).getValue()) {
                        if (!((double)mc.thePlayer.ticksExisted > DisablerDiggingActionOption.getTickRangeSetting(option2).getMinValue())) {
                           break;
                        }

                        if (!(((double)mc.thePlayer.ticksExisted) < DisablerDiggingActionOption.getTickRangeSetting(option2).getMaxValue())) {
                           break;
                        }
                     }

                     event.cancel();
                  }
               }
               break;
            }
         }
      }

      if (this.mmcReachSemi.getValue() && packet instanceof C0FPacketConfirmTransaction) {
         this.transactionCounter++;
         if (this.transactionCounter % 2 == 0) {
            event.cancel();
         }
      }
   }

   @Override
   public void onPostUpdate() {
      if (this.minibloxMoveFix.getValue()) {
         mc.thePlayer.sendQueue.addToSendQueue(new C0CPacketInput(mc.thePlayer.moveStrafing, mc.thePlayer.moveForward, mc.thePlayer.movementInput.jump, mc.thePlayer.movementInput.sneak));
      }
   }

   @Override
   public void onPreUpdate() {
      if (this.royalPixels.getValue()) {
         C13PacketPlayerAbilities packet = new C13PacketPlayerAbilities();
         packet.setAllowFlying(true);
         packet.setFlying(true);
         mc.thePlayer.sendQueue.addToSendQueue(packet);
      }

      BooleanValue onlyWhileSpeedModule12 = this.onlyWhileSpeedModule1;
      BooleanValue onlyWhileMove12 = this.onlyWhileMove1;
      BooleanValue notWhileScaffold12 = this.notWhileScaffold1;
      BooleanValue notWhileAttack12 = this.notWhileAttack1;
      if (this.shouldSendPlacePacket(this.place1, notWhileAttack12, notWhileScaffold12, onlyWhileMove12, onlyWhileSpeedModule12)) {
         mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 255, new ItemStack(Items.water_bucket), 0.0F, 0.5F, 0.0F));
         mc.thePlayer
            .sendQueue
            .addToSendQueueUnchecked(
               new C08PacketPlayerBlockPlacement(
                  new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1.5, mc.thePlayer.posZ),
                  1,
                  new ItemStack(Blocks.stone.getItem(mc.theWorld, new BlockPos(-1, -1, -1))),
                  0.0F,
                  0.94F,
                  0.0F
               )
            );
      }

      BooleanValue onlyWhileSpeedModule22 = this.onlyWhileSpeedModule2;
      BooleanValue onlyWhileMove22 = this.onlyWhileMove2;
      BooleanValue notWhileScaffold22 = this.notWhileScaffold2;
      BooleanValue notWhileAttack22 = this.notWhileAttack2;
      if (this.shouldSendPlacePacket(this.place2, notWhileAttack22, notWhileScaffold22, onlyWhileMove22, onlyWhileSpeedModule22)) {
         mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C08PacketPlayerBlockPlacement(mc.thePlayer.getHeldItem()));
      }

      BooleanValue onlyWhileSpeedModule32 = this.onlyWhileSpeedModule3;
      BooleanValue onlyWhileMove32 = this.onlyWhileMove3;
      BooleanValue notWhileScaffold32 = this.notWhileScaffold3;
      BooleanValue notWhileAttack32 = this.notWhileAttack3;
      if (this.shouldSendPlacePacket(this.place3, notWhileAttack32, notWhileScaffold32, onlyWhileMove32, onlyWhileSpeedModule32)) {
         mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 255, new ItemStack(Items.water_bucket), 0.0F, 0.5F, 0.0F));
      }

      if (this.resourcePack.getValue()) {
         mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C19PacketResourcePackStatus("", net.minecraft.network.play.client.C19PacketResourcePackStatus.Action.ACCEPTED));
      }

      this.attackedThisTick = false;
   }

   public boolean shouldSendPlacePacket(BooleanValue booleanValue, BooleanValue booleanValue2, BooleanValue booleanValue3, BooleanValue booleanValue4, BooleanValue booleanValue5) {
      return booleanValue.getValue()
         && mc.thePlayer != null
         && mc.theWorld != null
         && (!booleanValue2.getValue() || !this.attackedThisTick)
         && (!Augustus.getInstance().getModuleManager().scaffold.isToggled() || !booleanValue3.getValue())
         && (MoveUtil.isMoving() || !booleanValue4.getValue())
         && (Augustus.getInstance().getModuleManager().speed.isToggled() || !booleanValue5.getValue());
   }

   public boolean shouldCancelIncomingPacket(Packet packet) {
      if (mc.thePlayer == null) {
         return false;
      } else {
         Class clazz = packet.getClass();

         for (DisablerIncomingPacketOption option : DisablerIncomingPacketOption.values()) {
            if (clazz.equals(DisablerIncomingPacketOption.getPacketClass(option))) {
               DisablerIncomingPacketOption.incrementPacketCount(option);
               if (DisablerIncomingPacketOption.isOptionSelected(option)
                  && (
                     DisablerIncomingPacketOption.getCancelConditionSetting(option).getSelectedValue().equals("Always")
                        || DisablerIncomingPacketOption.getCancelConditionSetting(option).getSelectedValue().equals("OnlyOnGround") && mc.thePlayer.onGround
                        || DisablerIncomingPacketOption.getCancelConditionSetting(option).getSelectedValue().equals("OnlyInAir") && !mc.thePlayer.onGround
                  )) {
                  if (this.matchesCancelModulo(DisablerIncomingPacketOption.getIntervalSetting(option), DisablerIncomingPacketOption.getInvertIntervalSetting(option), DisablerIncomingPacketOption.getPacketCount(option))) {
                     if (DisablerIncomingPacketOption.getAlwaysSetting(option).getValue()) {
                        return true;
                     }

                     if ((double)mc.thePlayer.ticksExisted > DisablerIncomingPacketOption.getTickRangeSetting(option).getMinValue()) {
                        if (((double)mc.thePlayer.ticksExisted) < DisablerIncomingPacketOption.getTickRangeSetting(option).getMaxValue()) {
                           return true;
                        }
                     }
                  }
               }

               return false;
            }
         }

         return false;
      }
   }

   public boolean shouldCancelOutgoingPacket(Packet packet) {
      Class clazz = packet.getClass();

      for (DisablerOutgoingPacketOption option : DisablerOutgoingPacketOption.values()) {
         if (clazz.equals(DisablerOutgoingPacketOption.getPacketClass(option))) {
            DisablerOutgoingPacketOption.incrementPacketCount(option);
            if (DisablerOutgoingPacketOption.isOptionSelected(option)
               && (
                  DisablerOutgoingPacketOption.getCancelConditionSetting(option).getSelectedValue().equals("Always")
                     || DisablerOutgoingPacketOption.getCancelConditionSetting(option).getSelectedValue().equals("OnlyOnGround") && mc.thePlayer.onGround
                     || DisablerOutgoingPacketOption.getCancelConditionSetting(option).getSelectedValue().equals("OnlyInAir") && !mc.thePlayer.onGround
               )) {
               if (this.matchesCancelModulo(DisablerOutgoingPacketOption.getIntervalSetting(option), DisablerOutgoingPacketOption.getInvertIntervalSetting(option), DisablerOutgoingPacketOption.getPacketCount(option))) {
                  if (DisablerOutgoingPacketOption.getAlwaysSetting(option).getValue()) {
                     return true;
                  }

                  if ((double)mc.thePlayer.ticksExisted > DisablerOutgoingPacketOption.getTickRangeSetting(option).getMinValue()) {
                     if (((double)mc.thePlayer.ticksExisted) < DisablerOutgoingPacketOption.getTickRangeSetting(option).getMaxValue()) {
                        return true;
                     }
                  }
               }
            }

            return false;
         }
      }

      return false;
   }

   @Override
   public void onRender3D(float partialTicks) {
      if (this.pingSpoof.getValue()) {
         if (!this.spoofedPackets.isEmpty()) {
            Iterator it = this.spoofedPackets.iterator();
            long l = DefaultRandomGenerator.instance.nextLong(this.delay.getMinValue(), this.delay.getMaxValue());
            long currentTimeMs = System.currentTimeMillis();

            while (it.hasNext()) {
               Packet packet = (Packet)it.next();

               try {
                  long l3 = (Long)this.spoofedPacketTimestamps.get(packet);
                  if (l3 + l < currentTimeMs) {
                     mc.thePlayer.sendQueue.addToSendQueueUnchecked(packet);
                     this.spoofedPacketTimestamps.remove(packet);
                     it.remove();
                  }
               } catch (Exception e) {
               }
            }
         }
      } else if (!this.spoofedPackets.isEmpty()) {
         this.spoofedPackets.forEach(obj -> mc.thePlayer.sendQueue.addToSendQueueUnchecked((Packet)obj));
         this.spoofedPackets.clear();
      }
   }

   private boolean matchesCancelModulo(DoubleValue stringValue, BooleanValue booleanValue, int n) {
      return booleanValue.getValue()
         ? stringValue.getValue() <= 0.0 || (double)n % stringValue.getValue() != 0.0
         : stringValue.getValue() <= 0.0 || (double)n % stringValue.getValue() == 0.0;
   }

   private static boolean isDiggingTickRangeVisible(DisablerDiggingActionOption option) {
      return !DisablerDiggingActionOption.getAlwaysSetting(option).getValue();
   }

   private static boolean isEntityActionTickRangeVisible(DisablerEntityActionOption option) {
      return !DisablerEntityActionOption.getAlwaysSetting(option).getValue();
   }

   private static boolean isOutgoingTickRangeVisible(DisablerOutgoingPacketOption option) {
      return !DisablerOutgoingPacketOption.getAlwaysSetting(option).getValue();
   }

   private static boolean isIncomingTickRangeVisible(DisablerIncomingPacketOption option) {
      return !DisablerIncomingPacketOption.getAlwaysSetting(option).getValue();
   }

}

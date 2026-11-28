package net.augustus.modules.legit;

import java.awt.Color;
import java.util.concurrent.atomic.AtomicBoolean;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.ClickListener;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.ClientManagers;
import net.augustus.events.EventClick;
import net.augustus.events.EventPacketDelay;
import net.augustus.utils.rotation.RayCastUtil;
import net.augustus.utils.combat.EntityTargetFilter;
import net.augustus.events.listeners.PacketDelayListener;
import net.augustus.utils.network.ServerPingUtil;
import net.augustus.utils.combat.TargetFilterPresets;
import net.augustus.utils.rotation.RayTraceUtil;
import net.augustus.events.EventAttackEntity;
import net.augustus.events.listeners.AttackEntityListener;
import net.augustus.settings.TargetSelectionSettings;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.rotation.Rotation;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.MovingObjectPosition;

public class AutoBlock extends Module implements ClickListener, PacketDelayListener, AttackEntityListener {
   private final StringValue mode;
   private final DoubleValue startBlockingAdditionalRange;
   private final DoubleValue maximumPlayerHurtTime;
   private final DoubleValue maximumHurtTime;
   private final DoubleValue ticksToTryHitAfterUnblock;
   private final RandomizedRangeSettings maximumLagDuration;
   private final BooleanValue subtractOwnPingFromLagDuration;
   private final BooleanValue onlyOnRightClick;
   private final BooleanValue forceHit;
   private final BooleanValue interactAutoBlock;
   private final TargetSelectionSettings targetSelectionSettings;
   private final AtomicBoolean active;
   private EntityLivingBase target;
   private final TimeHelper targetResetTimer;
   private final TimeHelper lagTimer;
   private int blockCooldownTicks;
   private int unblockHitTicks;
   private final AtomicBoolean lagBlocking;
   private boolean delayingPackets;

   public AutoBlock() {
       super("AutoBlock", "AutomatisiertesBlockieren", Color.green, Categorys.LEGIT);
      this.mode = new StringValue(this, "Mode", "Default", new String[]{"Default", "Lag"});
      this.startBlockingAdditionalRange = new DoubleValue(this, "StartBlockingAdditionalRange", 0.5, 0.0, 2.0, 2);
      this.maximumPlayerHurtTime = new DoubleValue(this, "MaximumPlayerHurtTime", 4.0, 3.0, 10.0, 0);
      this.maximumHurtTime = new DoubleValue(this, "MaximumHurtTime", 1.0, 4.0, 0.0, 0);
      this.ticksToTryHitAfterUnblock = new DoubleValue(this, "TicksToTryHitAfterUnblock", 2.0, 0.0, 4.0, 0);
      this.maximumLagDuration = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "MaximumLagDuration", 250.0, 350.0, 0.0, 1000.0, 0)).withoutNotInARow().withVisibility(() -> this.isLagMode());
      this.subtractOwnPingFromLagDuration = new BooleanValue(this, "SubtractOwnPingFromLagDuration", false);
      this.onlyOnRightClick = (BooleanValue)(new BooleanValue(this, "OnlyOnRightClick", true)).markVisualOnly();
      this.forceHit = new BooleanValue(this, "ForceHit", true);
      this.interactAutoBlock = new BooleanValue(this, "InteractAutoBlock", true);
      this.targetSelectionSettings = new TargetSelectionSettings(this, TargetFilterPresets.createCombatFilter());
      this.active = new AtomicBoolean(false);
      this.targetResetTimer = new TimeHelper();
      this.lagTimer = new TimeHelper();
      this.blockCooldownTicks = 0;
      this.unblockHitTicks = 0;
      this.lagBlocking = new AtomicBoolean(false);
      this.delayingPackets = false;
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(7, 90000);
      this.toggleEventSubscription(25);
      this.toggleEventSubscription(1);
   }

   @Override
   public void onOutgoingPacketDelay(EventPacketDelay event) {
      if (this.active.get() && this.lagBlocking.get()) {
         if (this.isLagMode()) {
            Packet packet = event.getPacket();
            if (this.lagTimer.reached((long)this.getEffectiveLagDuration()) && this.delayingPackets) {
               this.delayingPackets = false;
               this.lagBlocking.set(false);
            }

            if (this.delayingPackets) {
               event.setDelayed(true);
            }

            if (packet instanceof C03PacketPlayer && !this.delayingPackets) {
               this.delayingPackets = true;
               this.lagTimer.reset();
               this.maximumLagDuration.nextValue();
            }
         }
      }
   }

   @Override
   public void onClick(EventClick event) {
      this.updateTarget();
      this.active.set(this.canAutoBlock());
      if (this.active.get()) {
         switch (this.mode.getSelectedValue()) {
            case "Default":
               if (!this.isPlayerHurtTimeWithinLimit()) {
                  event.setRightMouseDown(false);
               } else if (!this.shouldUnblockToAttack()) {
                  if (!mc.thePlayer.isUsingItem()) {
                     this.triggerBlock(event);
                  } else {
                     event.setRightMouseDown(true);
                  }
               } else {
                  this.unblockHitTicks = (int)this.ticksToTryHitAfterUnblock.getValue();
                  event.setRightMouseDown(false);
                  if (this.forceHit.getValue() && this.isAimingAtLivingEntity() && event.getLeftClicks() <= 0 && mc.gameSettings.keyBindAttack.getPressTime() <= 0 && !mc.thePlayer.isUsingItem()) {
                     event.addLeftClicks((byte)1);
                     event.setBreakingBlock(true);
                  }

                  if ((event.getLeftClicks() > 0 || mc.gameSettings.keyBindAttack.getPressTime() > 0) && this.isAimingAtLivingEntity() && !mc.thePlayer.isUsingItem()) {
                     this.triggerBlock(event);
                     this.blockCooldownTicks = 6;
                  }
               }
               break;
            case "Lag":
               if (!this.isPlayerHurtTimeWithinLimit()) {
                  event.setRightMouseDown(false);
                  this.lagBlocking.set(false);
                  this.delayingPackets = false;
               } else if (!this.shouldUnblockToAttack()) {
                  if (!this.lagBlocking.get()) {
                     this.triggerBlock(event);
                     this.lagBlocking.set(true);
                     this.delayingPackets = false;
                  } else {
                     event.setRightMouseDown(false);
                  }
               } else {
                  this.unblockHitTicks = (int)this.ticksToTryHitAfterUnblock.getValue();
                  event.setRightMouseDown(false);
                  if (this.forceHit.getValue() && this.isAimingAtLivingEntity() && event.getLeftClicks() <= 0 && mc.gameSettings.keyBindAttack.getPressTime() <= 0 && !mc.thePlayer.isUsingItem()) {
                     event.addLeftClicks((byte)1);
                     event.setBreakingBlock(true);
                  }

                  if ((event.getLeftClicks() > 0 || mc.gameSettings.keyBindAttack.getPressTime() > 0) && this.isAimingAtLivingEntity() && !mc.thePlayer.isUsingItem()) {
                     this.triggerBlock(event);
                     this.lagBlocking.set(true);
                     this.delayingPackets = false;
                     this.blockCooldownTicks = 6;
                  }
               }
         }
      }

      this.blockCooldownTicks--;
      this.unblockHitTicks--;
   }

   @Override
   public void onAttackEntity(EventAttackEntity event) {
      if (event.getTarget() instanceof EntityLivingBase) {
         this.target = (EntityLivingBase)event.getTarget();
         this.targetResetTimer.reset();
      }
   }

   private void triggerBlock(EventClick event) {
      event.addRightClicks((byte)1);
      if (!this.interactAutoBlock.getValue()) {
         event.setRightClickAction(() -> sendUseHeldItem());
      }
   }

   private boolean shouldUnblockToAttack() {
      if ((double)this.target.clientHurtTime <= this.maximumHurtTime.getValue() && this.blockCooldownTicks <= 0) {
         if (this.isAimingAtLivingEntity()) {
            return true;
         }

         if (this.canRayCastTargetInTicks((byte)1) || this.unblockHitTicks > 0) {
            return true;
         }
      }

      return false;
   }

   private boolean canAutoBlock() {
      return this.isRightClickSatisfied() && this.isHoldingSword() && this.target != null && this.isKillAuraNotBlocking();
   }

   private boolean isKillAuraNotBlocking() {
      return !ClientManagers.moduleManager.killAura.isToggled() || ClientManagers.moduleManager.killAura.target == null || ClientManagers.moduleManager.killAura.blockMode.getSelectedValue().equals("None");
   }

   private boolean isPlayerHurtTimeWithinLimit() {
      return (double)mc.thePlayer.hurtTime <= this.maximumPlayerHurtTime.getValue();
   }

   private boolean isRightClickSatisfied() {
      return !this.onlyOnRightClick.getValue() || mc.gameSettings.keyBindUseItem.isPressed() || mc.gameSettings.keyBindUseItem.isKeyDown();
   }

   private boolean isHoldingSword() {
      return mc.thePlayer.getHeldItem() != null && mc.thePlayer.getHeldItem().getItem() instanceof ItemSword;
   }

   private boolean canRayCastTargetInTicks(int n) {
      MovingObjectPosition hit = RayCastUtil.rayCastPredicted(n, ClientManagers.rotationController.getCurrentRotation(), this.getReachDistance());
      return hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && hit.entityHit instanceof EntityLivingBase;
   }

   private boolean isAimingAtLivingEntity() {
      return mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && mc.objectMouseOver.entityHit instanceof EntityLivingBase;
   }

   private boolean isLagMode() {
      return this.mode.getSelectedValue().equals("Lag");
   }

   private void updateTarget() {
      if (this.targetResetTimer.reached(600L)) {
         this.target = null;
      }

      if (this.target == null) {
         Rotation previousRotation = ClientManagers.rotationController.getPreviousRotation();
         double d = 1.0;
         double d2 = 6.0;
         MovingObjectPosition hit = RayTraceUtil.rayTraceEntity(1.0F, previousRotation, d2, d);
         if (hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && hit.entityHit instanceof EntityLivingBase) {
            this.target = (EntityLivingBase)hit.entityHit;
         }
      }

      if (!this.targetSelectionSettings.getTargetFilter().matches(this.target)) {
         this.target = null;
      }

      if (this.target != null && (double)mc.thePlayer.getDistanceToEntityBox(this.target) > this.getReachDistance() + this.startBlockingAdditionalRange.getValue()) {
         this.target = null;
      }
   }

   private double getReachDistance() {
      return ClientManagers.moduleManager.range.isToggled() ? ClientManagers.moduleManager.range.getCurrentRange() : (mc.playerController.isInCreativeMode() ? 4.5 : 3.0);
   }

   private int getEffectiveLagDuration() {
      return this.subtractOwnPingFromLagDuration.getValue() ? Math.max(0, this.maximumLagDuration.getLastIntValue() - ServerPingUtil.getPing()) : this.maximumLagDuration.getLastIntValue();
   }

   private static void sendUseHeldItem() {
      if (!mc.playerController.isHittingBlock() || ClientManagers.moduleManager.fixes.isPlaceWhileBreakEnabled()) {
         mc.rightClickDelayTimer = (byte)4;
         ItemStack currentItem = mc.thePlayer.inventory.getCurrentItem();
         if (currentItem != null && mc.playerController.sendUseItem(mc.thePlayer, mc.theWorld, currentItem)) {
            mc.entityRenderer.itemRenderer.resetEquippedProgress2();
         }
      }
   }

}

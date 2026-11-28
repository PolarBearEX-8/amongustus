package net.augustus.modules.combat;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.rotation.RotationEngine;
import net.augustus.events.listeners.ClickListener;
import net.augustus.settings.LookDownDisableSettings;
import net.augustus.utils.simulation.EntityMovementPredictor;
import net.augustus.utils.simulation.TrajectoryPredictionUtil;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.SilentSlotListener;
import net.augustus.events.EventClick;
import net.augustus.utils.rotation.MoveFixMode;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.utils.combat.EntityTargetFilter;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.utils.combat.TargetFilterPresets;
import net.augustus.utils.combat.EntityTargetSelector;
import net.augustus.events.EventMoveInput;
import net.augustus.utils.simulation.ProjectileSimulator;
import net.augustus.events.listeners.TickListener;
import net.augustus.utils.player.ContainerSlotQuery;
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.settings.TargetSelectionSettings;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.rotation.Rotation;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class ProjectileAimBot extends Module implements TickListener, ClickListener, SilentSlotListener, MoveInputListener {
   private final SettingGroup rotations;
   private final DoubleValue maxTargetLookFovDiff;
   private final BooleanValue combat;
   private final BooleanValue predict;
   private final BooleanValue silentSlotSwitching;
   private final BooleanValue onlyAtKillAura;
   private final BooleanValue onlyKillAuraTarget;
   private final DoubleValue aimRange;
   private final BooleanValue assistAtRightClick;
   private final TargetSelectionSettings targetSelection;
   private final LookDownDisableSettings lookDownDisable;
   private EntityLivingBase target;
   private int predictedFlightTicks;
   private int ticksSinceThrow;
   private final RotationEngine rotationEngine;
   private AxisAlignedBB predictedTargetBox;
   private int lastPredictionTick;

   public ProjectileAimBot() {
       super("ProjectileAimBot", "ProjektilZielhilfe", new Color(250, 233, 85), Categorys.COMBAT);
      this.rotations = new SettingGroup(this, "Rotations", true);
      this.maxTargetLookFovDiff = new DoubleValue(this, "MaxTargetLookFovDiff", 180.0, 20.0, 180.0, 0);
      this.combat = new BooleanValue(this, "Combat", true);
      this.predict = new BooleanValue(this, "Predict", true);
      this.silentSlotSwitching = new BooleanValue(this, "SilentSlotSwitching", true);
      BooleanValue booleanValue = new BooleanValue(this, "OnlyAtKillAura", true);
      BooleanValue combat2 = this.combat;
      this.onlyAtKillAura = (BooleanValue)booleanValue.withVisibility(() -> combat2.getValue());
      this.onlyKillAuraTarget = (BooleanValue)new BooleanValue(this, "OnlyKillAuraTarget", true).withVisibility(() -> this.isOnlyKillAuraTargetVisible());
      this.aimRange = new DoubleValue(this, "AimRange", 10.0, 3.0, 15.0, 1).visibleWhen(() -> this.isAimRangeVisible());
      this.assistAtRightClick = new BooleanValue(this, "AssistAtRightClick", false);
      this.targetSelection = (new TargetSelectionSettings(this, TargetFilterPresets.createCombatFilter())).withSorting();
      this.lookDownDisable = new LookDownDisableSettings(this);
      this.target = null;
      this.predictedFlightTicks = 0;
      this.ticksSinceThrow = 0;
      this.rotationEngine = new RotationEngine(this, this.rotations);
      this.predictedTargetBox = null;
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
      this.target = null;
      this.targetSelection.setCurrentTarget((Entity)null);
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(34);
      this.toggleEventSubscription(21);
   }

   @Override
   public void onClick(EventClick event) {
      if (mc.isNotInGui()) {
         this.ticksSinceThrow++;
         if (this.isHoldingProjectileForAssist() && event.isRightMouseDown()) {
            event.setAllowRightClick(false);
         }

         if (this.target != null && ClientManagers.rotationController.isOwnedBy(this)) {
            if (mc.thePlayer.ticksExisted - 1 == this.lastPredictionTick || this.predict.getValue()) {
               if (!this.willProjectileHitTarget(this.target)) {
                  return;
               }
            }

            if (this.ticksSinceThrow > 10) {
               this.ticksSinceThrow = 0;
               event.addRightClicks((byte)1);
               event.setAllowRightClick(true);
            }
         }
      }
   }

   private boolean willProjectileHitTarget(EntityLivingBase entity) {
      ProjectileSimulator projectileSimulator = new ProjectileSimulator();
      EntityPlayerSP thePlayer = mc.thePlayer;
      ItemStack currentEquippedItem = mc.thePlayer.getCurrentEquippedItem();
      float lastReportedPitch = mc.thePlayer.lastReportedPitch;
      projectileSimulator.initFromShooterAngles(thePlayer, currentEquippedItem, mc.thePlayer.lastReportedYaw, lastReportedPitch);
      return projectileSimulator.simulateThrowableHitTick((short)200, entity, this.predictedTargetBox) != Integer.MAX_VALUE;
   }

   @Override
   public void onPostSilentSlot() {
      if (mc.isNotInGui()) {
         if (this.target != null && this.combat.getValue() && ClientManagers.rotationController.isOwnedBy(this)) {
            int n = this.findProjectileHotbarSlot();
            if (n >= 0) {
               SilentSlotManager.requestSlot(this, (byte)-1, n, this.silentSlotSwitching.getValue());
            }
         }
      }
   }

   @Override
   public void onEarlyTick() {
      EntityLivingBase target2 = this.target;
      this.targetSelection.setCurrentTarget(target2);
      this.target = null;
      if (mc.isNotInGui() && this.findProjectileHotbarSlot() >= 0) {
         this.target = this.findTarget();
         if (this.target != null) {
            if (((double)RotationUtil.getEntityLookAngleToPlayer(this.target)) > this.maxTargetLookFovDiff.getValue()) {
               this.target = null;
            }
         }

         if (this.target != null) {
            if (!EntityMovementPredictor.hasInvalidPositionHistory(this.target)) {
               if (!mc.gameSettings.keyBindUseItem.isKeyDown() && !this.combat.getValue()) {
                  this.target = null;
                  return;
               }

               if (mc.gameSettings.keyBindUseItem.isKeyDown()) {
                  if (!this.isHoldingProjectileForAssist()) {
                     this.target = null;
                     return;
                  }
               }

               Rotation rotation = this.calculateProjectileRotation(this.target, (byte)3);
               if (rotation.yaw == 0.0F && rotation.pitch == 0.0F) {
                  this.target = null;
                  return;
               }

               if (!this.predict.getValue()) {
                  if (!this.isHoldingProjectileForAssist() && ClientManagers.moduleManager.killAura.isToggled() && ClientManagers.moduleManager.killAura.target != null && this.combat.getValue()) {
                     return;
                  }
               }

               Rotation rotation2 = this.rotationEngine.rotateTowards(rotation, ClientManagers.rotationController.getPreviousRotation());
               ClientManagers.rotationController.requestRotation(rotation2, (byte)0, this);
               return;
            }
         }
      }
   }

   private boolean isHoldingProjectileForAssist() {
      if (this.assistAtRightClick.getValue() && mc.thePlayer.getCurrentEquippedItem() != null) {
         Item item = mc.thePlayer.getCurrentEquippedItem().getItem();
         return item instanceof ItemSnowball || item instanceof ItemEgg;
      } else {
         return false;
      }
   }

   private int findProjectileHotbarSlot() {
      ContainerSlotQuery containerSlotQuery = ContainerSlotQuery.of(mc.thePlayer.inventoryContainer);
      containerSlotQuery = containerSlotQuery.fromSlot((byte)36);
      Predicate predicate = obj -> {
         return obj instanceof ItemSnowball || obj instanceof ItemEgg;
      };
      OptionalInt optionalInt = containerSlotQuery.filterItem(predicate).findFirstSlotIndex();
      return optionalInt.isPresent() ? optionalInt.getAsInt() - 36 : -1;
   }

   private Rotation calculateProjectileRotation(EntityLivingBase entity, int n) {
      ItemStack stack = this.isHoldingProjectileForAssist()
         ? mc.thePlayer.getCurrentEquippedItem()
         : (this.findProjectileHotbarSlot() < 0 ? null : mc.thePlayer.inventoryContainer.getSlot(this.findProjectileHotbarSlot() + 36).getStack());
      if (stack == null) {
         return new Rotation(0.0F, 0.0F);
      } else {
         this.predictedFlightTicks = 1;
         double d = (double)(entity.width / 2.0F);
         float f = 0.0F;
         float f2 = 0.0F;
         ArrayList list = new ArrayList();
         int n2 = mc.thePlayer.ticksExisted + 1;
         int ticksExisted = mc.thePlayer.ticksExisted;
         int ticksExisted2 = mc.thePlayer.ticksExisted;
         ArrayList list2 = TrajectoryPredictionUtil.predictPlayerPositions(mc.thePlayer, ticksExisted2, ticksExisted, (byte)0, n2);
         int n3;
         Vec3 clampedPosition = TrajectoryPredictionUtil.getClampedPosition(list2, (byte)0);

         for (int i = 0; i < n; i++) {
            ArrayList list3;
            if (this.predictedFlightTicks <= list.size() && this.predictedFlightTicks > 0) {
               list3 = list;
            } else {
               n3 = mc.thePlayer.ticksExisted + this.predictedFlightTicks;
               int n4 = n3;
               int ticksExisted3 = mc.thePlayer.ticksExisted;
               list3 = list = TrajectoryPredictionUtil.predictTargetPositions(mc.thePlayer, entity, ticksExisted3, n4, true);
            }

            Vec3 clampedPosition2 = TrajectoryPredictionUtil.getClampedPosition(list3, this.predictedFlightTicks);
            double xCoord = clampedPosition2.xCoord;
            double yCoord = clampedPosition2.yCoord;
            double zCoord = clampedPosition2.zCoord;
            double d2 = xCoord - clampedPosition.xCoord;
            double d3 = yCoord + (double)this.target.getEyeHeight() - (clampedPosition.yCoord + (double)mc.thePlayer.getEyeHeight());
            double d4 = zCoord - clampedPosition.zCoord;
            float f3 = (float)Math.toDegrees(Math.atan2(d4, d2)) - 90.0F;
            float f4 = (float)(-(MathHelper.atan2(d3, (double)MathHelper.sqrt_double(d2 * d2 + d4 * d4)) * 180.0 / Math.PI));
            float f5 = RotationUtil.updateAngle(ClientManagers.rotationController.getPreviousRotation().yaw, f3, 180.0F);
            if (!this.predict.getValue()) {
               if (!this.isHoldingProjectileForAssist() && this.combat.getValue()) {
                  float f6 = 2.0F;
                  float f7 = -2.0F;
                  float f8 = f5 + DefaultRandomGenerator.instance.nextFloat(f7, f6);
                  float f9 = 2.0F;
                  float f10 = -2.0F;
                  return new Rotation(
                     f8, RotationUtil.updateAngle(ClientManagers.rotationController.getPreviousRotation().pitch, f4 + DefaultRandomGenerator.instance.nextFloat(f10, f9), 180.0F)
                  );
               }
            }

            for (float j = 0.0F; j < 60.0F; j += 0.2F) {
               boolean flag2 = false;

               for (int k = -1; k < 2; k += 2) {
                  float f11 = f4 + j * (float)k;
                  ProjectileSimulator projectileSimulator = new ProjectileSimulator();
                  double zCoord2 = clampedPosition.zCoord;
                  double yCoord2 = clampedPosition.yCoord;
                  double xCoord2 = clampedPosition.xCoord;
                  projectileSimulator.initWithAngles(mc.thePlayer, stack, xCoord2, yCoord2, zCoord2, f5, f11);
                  int n5 = projectileSimulator.simulateThrowableHitTick((byte)100, entity, new AxisAlignedBB(xCoord - d, yCoord + 0.2, zCoord - d, xCoord + d, yCoord + (double)entity.height, zCoord + d));
                  if (n5 != Integer.MAX_VALUE) {
                     this.predictedFlightTicks = n5;
                     flag2 = true;
                     f = f5;
                     f2 = f11;
                     this.predictedTargetBox = new AxisAlignedBB(xCoord - d, yCoord, zCoord - d, xCoord + d, yCoord + (double)entity.height, zCoord + d);
                     this.lastPredictionTick = mc.thePlayer.ticksExisted;
                     break;
                  }
               }

               if (flag2) {
                  break;
               }
            }
         }

         return new Rotation(f, f2);
      }
   }

   private EntityLivingBase findTarget() {
      if (this.lookDownDisable.shouldDisable()) {
         return null;
      } else if (this.combat.getValue() && ClientManagers.moduleManager.killAura.isToggled() && ClientManagers.moduleManager.killAura.target != null) {
         return (double)mc.thePlayer.getDistanceToEntityBox(ClientManagers.moduleManager.killAura.target) < 3.3 ? null : ClientManagers.moduleManager.killAura.target;
      } else {
         if (mc.gameSettings.keyBindUseItem.isKeyDown()) {
            EntityTargetSelector entityTargetSelector = EntityTargetSelector.of(mc.theWorld.loadedEntityList);
            entityTargetSelector = entityTargetSelector.withTargetFilter(this.targetSelection.getTargetFilter()).filterFocus();
            Optional optional = entityTargetSelector.sortBy(this.targetSelection.createTargetComparatorKey(ClientManagers.rotationController.getPreviousRotation(), -1.0)).findBest();
            if (optional.isPresent()) {
               return (EntityLivingBase)optional.get();
            }
         }

         if (this.combat.getValue() && (ClientManagers.moduleManager.killAura.isToggled() && !this.onlyKillAuraTarget.getValue() || !this.onlyAtKillAura.getValue())) {
            EntityTargetSelector entityTargetSelector2 = EntityTargetSelector.of(mc.theWorld.loadedEntityList);
            entityTargetSelector2 = entityTargetSelector2.withTargetFilter(this.targetSelection.getTargetFilter()).filterFocus();
            entityTargetSelector2 = entityTargetSelector2.maxDistance(this.aimRange.getValue());
            Optional optional2 = entityTargetSelector2.sortBy(this.targetSelection.createTargetComparatorKey(ClientManagers.rotationController.getPreviousRotation(), -1.0)).findBest();
            EntityLivingBase entity = optional2.isPresent() && optional2.get() instanceof EntityLivingBase ? (EntityLivingBase)optional2.get() : null;
            if (entity != null && (double)mc.thePlayer.getDistanceToEntityBox(entity) < 3.2) {
               entity = null;
            }

            return entity;
         } else {
            return null;
         }
      }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (ClientManagers.rotationController.isOwnedBy(this)) {
         event.setMoveFixMode(MoveFixMode.DEFAULT);
      }
   }

   private boolean isAimRangeVisible() {
      return this.combat.getValue() && !this.onlyKillAuraTarget.getValue();
   }

   private boolean isOnlyKillAuraTargetVisible() {
      return this.combat.getValue() && this.onlyAtKillAura.getValue();
   }

}

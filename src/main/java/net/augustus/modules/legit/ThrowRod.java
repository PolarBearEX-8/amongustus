package net.augustus.modules.legit;

import net.minecraft.util.MovingObjectPosition;
import java.awt.Color;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.ClickListener;
import net.augustus.settings.WeaponFilterSettings;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.LookDownDisableSettings;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.SilentSlotListener;
import net.augustus.events.EventClick;
import net.augustus.settings.RangeSetting;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.utils.combat.EntityTargetFilter;
import net.augustus.events.EventLoadWorld;
import net.augustus.utils.world.BlockUtil;
import net.augustus.utils.combat.TargetFilterPresets;
import net.augustus.utils.combat.EntityTargetSelector;
import net.augustus.utils.TickCounter;
import net.augustus.utils.player.ContainerSlotQuery;
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.settings.TargetSelectionSettings;
import net.augustus.utils.rotation.Rotation;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class ThrowRod extends Module implements SilentSlotListener, ClickListener, LoadWorldListener {
   public final RangeSetting distanceToTarget;
   private final DoubleValue maxTargetLookFovDiff;
   public final DoubleValue fovYaw;
   public final DoubleValue fovPitch;
   private final RandomizedRangeSettings reactionTickTimeWorld;
   private final RandomizedRangeSettings reactionTickTimeEntity;
   private final RandomizedRangeSettings rodExtendReactionTime;
   private final TargetSelectionSettings targetSelectionSettings;
   public final BooleanValue ignoreEatingTargets;
   public final BooleanValue disableWhileScaffold;
   public final BooleanValue onlyWhileKillAuraHasTarget;
   public final WeaponFilterSettings weaponWhitelist;
   public final LookDownDisableSettings lookDownDisableSettings;
   private final BooleanValue onlyAtClicking;
   private final BooleanValue automatedSlotSwitching;
   public final BooleanValue silentSlotSwitching;
   private final TimeHelper timeHelper;
   private final TickCounter worldReactionCounter;
   private final TickCounter entityReactionCounter;
   private final TickCounter ticksSinceLastClick;
   private final TickCounter rodHeldCounter;
   private boolean trackingHook;
   private boolean rodCast;
   private boolean hitWorld;
   private boolean needsFallbackSlotUpdate;
   private int fallbackHotbarSlot;
   private EntityLivingBase target;
   private int selectedHotbarSlot;

   public ThrowRod() {
       super("ThrowRod", "SchmeißeAngel", new Color(202, 242, 109), Categorys.LEGIT);
      this.distanceToTarget = new RangeSetting(this, "DistanceToTarget", 3.2, 15.0, 0.0, 20.0, 2);
      this.maxTargetLookFovDiff = new DoubleValue(this, "MaxTargetLookFovDiff", 180.0, 20.0, 180.0, 0);
      this.fovYaw = new DoubleValue(this, "FovYaw", 90.0, 0.0, 180.0, 0);
      this.fovPitch = new DoubleValue(this, "FovPitch", 90.0, 0.0, 180.0, 0);
      this.reactionTickTimeWorld = new RandomizedRangeSettings(this, "ReactionTickTimeWorld", 0.0, 0.0, 0.0, 5.0, 0);
      this.reactionTickTimeEntity = new RandomizedRangeSettings(this, "ReactionTickTimeEntity", 0.0, 0.0, 0.0, 5.0, 0);
      this.rodExtendReactionTime = new RandomizedRangeSettings(this, "RodExtendReactionTime", 0.0, 0.0, 0.0, 5.0, 0);
      this.targetSelectionSettings = (new TargetSelectionSettings(this, TargetFilterPresets.createCombatFilter())).withSorting();
      this.ignoreEatingTargets = new BooleanValue(this, "IgnoreEatingTargets", true);
      this.disableWhileScaffold = new BooleanValue(this, "DisableWhileScaffold", true);
      this.onlyWhileKillAuraHasTarget = new BooleanValue(this, "OnlyWhileKillAuraHasTarget", false);
      this.weaponWhitelist = new WeaponFilterSettings(this, "WeaponWhitelist");
      this.lookDownDisableSettings = new LookDownDisableSettings(this);
      this.onlyAtClicking = new BooleanValue(this, "OnlyAtClicking", true);
      this.automatedSlotSwitching = new BooleanValue(this, "AutomatedSlotSwitching", true);
      this.silentSlotSwitching = new BooleanValue(this, "SilentSlotSwitching", true);
      this.timeHelper = new TimeHelper();
      this.worldReactionCounter = new TickCounter();
      this.entityReactionCounter = new TickCounter();
      this.ticksSinceLastClick = new TickCounter();
      this.rodHeldCounter = new TickCounter();
      this.trackingHook = true;
      this.rodCast = false;
      this.hitWorld = false;
      this.needsFallbackSlotUpdate = false;
      this.fallbackHotbarSlot = -1;
      this.target = null;
      this.selectedHotbarSlot = -1;
   }

   @Override
   public void onEnable() {
      this.needsFallbackSlotUpdate = true;
   }

   @Override
   public void onDisable() {
      this.target = null;
      this.targetSelectionSettings.setCurrentTarget((Entity)null);
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(34);
      this.toggleEventSubscription(17);
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      this.targetSelectionSettings.setCurrentTarget((Entity)null);
      this.target = null;
   }

   @Override
   public void onClick(EventClick event) {
      if (!this.isHoldingRod()) {
         this.rodHeldCounter.reset();
      }

      if (mc.isNotInGui()) {
         if (event.getLeftClicks() > 0 || mc.gameSettings.keyBindAttack.isKeyDown() || mc.gameSettings.keyBindAttack.getPressTime() > 0) {
            this.ticksSinceLastClick.reset();
         }

         boolean hasActiveFishHook = this.hasActiveFishHook();
         if (this.isHoldingRod()) {
            if (!hasActiveFishHook && !this.rodCast && this.target != null && mc.objectMouseOver != null && !this.isCrosshairOnEntity()) {
               if (this.rodHeldCounter.hasReached(this.rodExtendReactionTime.getLastIntValue())) {
                  event.addRightClicks((byte)1);
                  this.rodCast = true;
                  this.trackingHook = true;
                  this.needsFallbackSlotUpdate = true;
                  this.timeHelper.reset();
                  this.rodExtendReactionTime.nextValue();
               }
            }
         } else {
            this.rodCast = false;
         }
      }
   }

   @Override
   public void onPostSilentSlot() {
      this.selectedHotbarSlot = -1;
      this.ticksSinceLastClick.increment();
      this.worldReactionCounter.increment();
      this.entityReactionCounter.increment();
      this.rodHeldCounter.increment();
      EntityLivingBase target2 = this.target;
      this.targetSelectionSettings.setCurrentTarget(target2);
      this.target = this.findTarget();
      if (this.target != null && this.automatedSlotSwitching.getValue()) {
         this.selectedHotbarSlot = this.findRodHotbarSlot();
      }

      if (this.rodCast && this.timeHelper.reached(150L) && this.trackingHook) {
            EntityFishHook entityFishHook = this.findOwnFishHook();
            if (entityFishHook != null) {
               BlockPos pos = new BlockPos(entityFishHook.posX, entityFishHook.posY + 0.5, entityFishHook.posZ);
               if (entityFishHook.onGround || entityFishHook.inGround || entityFishHook.isDead || mc.theWorld.getBlockState(pos.down()).getBlock().isFullBlock()) {
                  this.worldReactionCounter.reset();
                  this.hitWorld = true;
                  this.trackingHook = false;
               }

               if (this.target != null) {
                  Vec3 positionEyes = mc.thePlayer.getPositionEyes(1.0F);
                  double distanceToEntityBox = (double)mc.thePlayer.getDistanceToEntityBox(this.target);
                  double hookDist = positionEyes.distanceTo(
                     new Vec3((double)((float)entityFishHook.serverPosX / 32.0F), (double)((float)entityFishHook.serverPosY / 32.0F), (double)((float)entityFishHook.serverPosZ / 32.0F))
                  );
                  if ((double)mc.thePlayer.getDistanceToEntityBox(entityFishHook) > distanceToEntityBox
                     || hookDist > this.distanceToTarget.getMaxValue()
                     || this.target.hurtTime == 10) {
                     this.entityReactionCounter.reset();
                     this.hitWorld = false;
                     this.trackingHook = false;
                  }
               }
            }
         }

      if (this.rodCast && !this.trackingHook) {
         boolean entityReady = this.entityReactionCounter.hasReached(this.reactionTickTimeEntity.getLastIntValue()) && !this.hitWorld;
         boolean worldReady = this.worldReactionCounter.hasReached(this.reactionTickTimeWorld.getLastIntValue()) && this.hitWorld;
         if (entityReady || worldReady) {
            this.selectedHotbarSlot = this.getFallbackHotbarSlot();
            this.rodCast = false;
            this.reactionTickTimeEntity.nextInt();
            this.reactionTickTimeWorld.nextInt();
         }
      }

      if (this.selectedHotbarSlot != -1) {
         SilentSlotManager.requestSlot(this, (byte)9, this.selectedHotbarSlot, this.silentSlotSwitching.getValue());
      }
   }

   private boolean isCrosshairOnEntity() {
      return mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY;
   }

   private int getFallbackHotbarSlot() {
      if (!this.needsFallbackSlotUpdate) {
         return this.fallbackHotbarSlot;
      } else {
         this.needsFallbackSlotUpdate = false;
         this.fallbackHotbarSlot = -1;
         int n = this.findRodHotbarSlot();
         if (SilentSlotManager.realHotbarSlot != n) {
            this.fallbackHotbarSlot = SilentSlotManager.realHotbarSlot;
         } else {
            ContainerSlotQuery containerSlotQuery = ContainerSlotQuery.of(mc.thePlayer.inventoryContainer);
            OptionalInt optionalInt = containerSlotQuery.fromSlot((byte)36).emptyOnly().findFirstSlotIndex();
            if (optionalInt.isPresent()) {
               this.fallbackHotbarSlot = optionalInt.getAsInt() - 36;
            } else {
               containerSlotQuery = ContainerSlotQuery.of(mc.thePlayer.inventoryContainer);
               containerSlotQuery = containerSlotQuery.fromSlot((byte)36);
               Predicate predicate = obj -> {
                  return !(obj instanceof ItemFood) && !(obj instanceof ItemSkull);
               };
               OptionalInt optionalInt2 = containerSlotQuery.filterItem(predicate).findFirstSlotIndex();
               if (optionalInt2.isPresent()) {
                  this.fallbackHotbarSlot = optionalInt2.getAsInt() - 36;
               } else {
                  int n2 = 0;

                  do {
                     this.fallbackHotbarSlot = DefaultRandomGenerator.instance.nextInt((byte)0, (byte)8);
                  } while (this.fallbackHotbarSlot == n && ++n2 < 10);
               }
            }
         }

         return this.fallbackHotbarSlot;
      }
   }

   public int findRodHotbarSlot() {
      ContainerSlotQuery containerSlotQuery = ContainerSlotQuery.of(mc.thePlayer.inventoryContainer);
      containerSlotQuery = containerSlotQuery.fromSlot((byte)36);
      OptionalInt optionalInt = containerSlotQuery.filterItem(obj -> obj instanceof ItemFishingRod).findFirstSlotIndex();
      return optionalInt.isPresent() ? optionalInt.getAsInt() - 36 : -1;
   }

   private boolean isHoldingRod() {
      return mc.thePlayer.getCurrentEquippedItem() != null && mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemFishingRod;
   }

   private boolean hasActiveFishHook() {
      return this.findOwnFishHook() != null;
   }

   private EntityFishHook findOwnFishHook() {
      if (mc.thePlayer != null && mc.thePlayer.fishEntity != null) {
         return mc.thePlayer.fishEntity;
      } else if (mc.theWorld == null) {
         return null;
      } else {
         for (Entity entity : (Iterable<Entity>)(mc.theWorld.loadedEntityList)) {
            if (entity instanceof EntityFishHook) {
               EntityFishHook entityFishHook = (EntityFishHook)entity;
               if (entityFishHook.angler.getName().equals(mc.session.getUsername())) {
                  return entityFishHook;
               }
            }
         }

         return null;
      }
   }

   private EntityLivingBase findTarget() {
      ItemStack heldItemStack = SilentSlotManager.heldItemStack;
      if (!this.weaponWhitelist.isItemAllowed(heldItemStack)) {
         return null;
      } else if (this.lookDownDisableSettings.shouldDisable()) {
         return null;
      } else if (BlockUtil.isScaffoldActiveAndValid() && this.disableWhileScaffold.getValue()) {
         return null;
      } else if ((!ClientManagers.moduleManager.killAura.isToggled() || ClientManagers.moduleManager.killAura.target == null) && this.onlyWhileKillAuraHasTarget.getValue()) {
         return null;
      } else if (this.isCrosshairOnEntity()) {
         return null;
      } else if (this.ticksSinceLastClick.hasReached((byte)3) && this.onlyAtClicking.getValue()) {
            return null;
         } else {
            EntityTargetSelector entityTargetSelector = EntityTargetSelector.of(mc.theWorld.loadedEntityList);
            entityTargetSelector = entityTargetSelector.withTargetFilter(this.targetSelectionSettings.getTargetFilter());
            entityTargetSelector = entityTargetSelector.throughWalls(false);
            entityTargetSelector = entityTargetSelector.maxDistance(this.distanceToTarget.getMaxValue());
            entityTargetSelector = entityTargetSelector.minDistance(this.distanceToTarget.getMinValue());
            entityTargetSelector = entityTargetSelector.withinFov(this.fovYaw.getValue(), this.fovPitch.getValue()).filterFocus();
            entityTargetSelector = entityTargetSelector.filter(obj -> this.isValidTargetState((Entity)obj));
            Predicate predicate2 = obj2 -> {
               if (obj2 instanceof EntityLivingBase) {
                  return !(((double)RotationUtil.getEntityLookAngleToPlayer((EntityLivingBase)obj2)) > this.maxTargetLookFovDiff.getValue());
               } else {
                  return false;
               }
            };
            entityTargetSelector = entityTargetSelector.filter(predicate2);
            Optional optional = entityTargetSelector.sortBy(this.targetSelectionSettings.createTargetComparatorKey(ClientManagers.rotationController.getPreviousRotation(), -1.0)).findBest();
            return optional.isPresent() && optional.get() instanceof EntityLivingBase ? (EntityLivingBase)optional.get() : null;
         }
   }

   private boolean isValidTargetState(Entity entity) {
      return !entity.isEating() || !this.ignoreEatingTargets.getValue();
   }

}

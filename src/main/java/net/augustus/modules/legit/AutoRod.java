package net.augustus.modules.legit;

import java.awt.Color;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.rotation.RotationEngine;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.events.listeners.ClickListener;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.settings.WeaponFilterSettings;
import net.augustus.settings.LookDownDisableSettings;
import net.augustus.utils.simulation.EntityMovementPredictor;
import net.augustus.events.EventMove;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.SilentSlotListener;
import net.augustus.events.EventClick;
import net.augustus.settings.ColorSetting;
import net.augustus.utils.rotation.MoveFixMode;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.events.listeners.MoveListener;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.utils.combat.EntityTargetFilter;
import net.augustus.events.EventLoadWorld;
import net.augustus.utils.rotation.RotationController;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.utils.rotation.EntityBoundsUtil;
import net.augustus.utils.combat.TargetFilterPresets;
import net.augustus.utils.combat.EntityTargetSelector;
import net.augustus.events.EventMoveInput;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.Augustus;
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
import net.augustus.utils.TimeHelper;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class AutoRod extends Module implements LoadWorldListener, MoveListener, ClickListener, TickListener, MoveInputListener, RenderListener, SilentSlotListener {
   private final SettingGroup rotations;
   private final DoubleValue maxTargetLookFovDiff;
   public final DoubleValue fovYaw;
   public final DoubleValue fovPitch;
   public final DoubleValue maxRodRange;
   public final BooleanValue moveFix;
   private final TargetSelectionSettings targetSelectionSettings;
   public final BooleanValue ignoreEatingTargets;
   public final BooleanValue combat;
   public final BooleanValue click;
   public final BooleanValue silentSlotSwitching;
   public final BooleanValue disableWhileScaffold;
   public final BooleanValue onlyWhileKillAuraHasTarget;
   public final BooleanValue rightClickIfRodOnTarget;
   public final WeaponFilterSettings weaponWhitelist;
   public final LookDownDisableSettings lookDownDisableSettings;
   public final BooleanValue esp;
   public final ColorSetting espColor;
   public final BlurShadowSettings blurShadowSettings;
   private final TimeHelper timeHelper;
   private int ticksSinceCast;
   private int selectedHotbarSlot;
   private EntityLivingBase target;
   private Vec3 predictedTargetPos;
   private int predictedFlightTicks;
   private boolean rodCast;
   private boolean pendingRightClickReel;
   private boolean needsFallbackSlotUpdate;
   private int fallbackHotbarSlot;
   private boolean hookHitTarget;
   private final RotationEngine rotationEngine;

   public AutoRod() {
       super("AutoRod", "AutomatischeAngel", new Color(203, 232, 162), Categorys.LEGIT);
      this.rotations = new SettingGroup(this, "Rotations", true);
      this.maxTargetLookFovDiff = new DoubleValue(this, "MaxTargetLookFovDiff", 180.0, 20.0, 180.0, 0);
      this.fovYaw = (DoubleValue)(new DoubleValue(this, "FovYaw", 90.0, 0.0, 180.0, 0)).markVisualOnly();
      this.fovPitch = (DoubleValue)(new DoubleValue(this, "FovPitch", 90.0, 0.0, 180.0, 0)).markVisualOnly();
      this.maxRodRange = new DoubleValue(this, "MaxRodRange", 10.0, 0.0, 20.0, 2);
      this.moveFix = new BooleanValue(this, "MoveFix", true);
      this.targetSelectionSettings = (new TargetSelectionSettings(this, TargetFilterPresets.createCombatFilter())).withSorting();
      this.ignoreEatingTargets = new BooleanValue(this, "IgnoreEatingTargets", true);
      this.combat = new BooleanValue(this, "Combat", true);
      this.click = new BooleanValue(this, "Click", true);
      this.silentSlotSwitching = new BooleanValue(this, "SilentSlotSwitching", true);
      this.disableWhileScaffold = new BooleanValue(this, "DisableWhileScaffold", true);
      this.onlyWhileKillAuraHasTarget = new BooleanValue(this, "OnlyWhileKillAuraHasTarget", false);
      this.rightClickIfRodOnTarget = new BooleanValue(this, "RightClickIfRodOnTarget", false);
      this.weaponWhitelist = new WeaponFilterSettings(this, "WeaponWhitelist");
      this.lookDownDisableSettings = new LookDownDisableSettings(this);
      this.esp = new BooleanValue(this, "ESP", true);
      ColorSetting colorSetting = new ColorSetting(this, "ESPColor", Color.GREEN);
      BooleanValue esp2 = this.esp;
      this.espColor = (ColorSetting)colorSetting.withVisibility(() -> esp2.getValue());
      this.blurShadowSettings = (new BlurShadowSettings(this)).disableBlurByDefault().withCustomShadowColor(Color.black);
      this.timeHelper = new TimeHelper();
      this.ticksSinceCast = 0;
      this.selectedHotbarSlot = -1;
      this.target = null;
      this.predictedTargetPos = null;
      this.predictedFlightTicks = 0;
      this.rodCast = false;
      this.pendingRightClickReel = false;
      this.needsFallbackSlotUpdate = false;
      this.fallbackHotbarSlot = -1;
      this.hookHitTarget = false;
      this.rotationEngine = new RotationEngine(this, this.rotations);
   }

   @Override
   public void onEnable() {
      this.predictedFlightTicks = 0;
      this.rodCast = false;
      this.needsFallbackSlotUpdate = true;
   }

   @Override
   public void onDisable() {
      this.target = null;
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(28);
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(21);
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(34);
      this.toggleEventSubscription(17);
   }

   @Override
   public void onEarlyTick() {
      if (!mc.gameSettings.keyBindAttack.isKeyDown()) {
         this.timeHelper.reset();
      }

      EntityLivingBase target2 = this.target;
      this.targetSelectionSettings.setCurrentTarget(target2);
      this.target = null;
      if (this.canUseRod() && (!ClientManagers.moduleManager.scaffold.isToggled() || !this.disableWhileScaffold.getValue())) {
         this.target = this.findTarget();
         if (this.target != null) {
            if (((double)RotationUtil.getEntityLookAngleToPlayer(this.target)) > this.maxTargetLookFovDiff.getValue()) {
               this.target = null;
               return;
            }

            double[] doubles = new double[]{this.target.posX, this.target.posY, this.target.posZ};
            this.predictedFlightTicks = this.estimateImpactTicks(this.target, (byte)4) + 2;
            if (this.predictedFlightTicks >= 0) {
               int predictedFlightTicks2 = this.predictedFlightTicks;
               doubles = EntityMovementPredictor.predictFullState(this.target, predictedFlightTicks2);
            }

            this.predictedTargetPos = new Vec3(doubles[0], doubles[1], doubles[2]);
            double d2 = doubles[0] - mc.thePlayer.posX;
            double d3 = doubles[1] + (double)this.target.height / 1.7 - (mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight());
            double d4 = doubles[2] - mc.thePlayer.posZ;
            float f = (float)Math.toDegrees(Math.atan2(d4, d2)) - 90.0F;
            double d5 = (double)MathHelper.sqrt_double(d2 * d2 + d4 * d4);
            double distanceToEntity = (double)mc.thePlayer.getDistanceToEntity(this.target);
            float f2 = (float)((double)((float)(-(Math.atan2(d3, d5) * 180.0 / Math.PI))) - 0.55F * Math.max(0.0, distanceToEntity - 3.0));
            Rotation rotation = this.rotationEngine.rotateTowards(f, f2, ClientManagers.rotationController.getPreviousRotation());
            if (distanceToEntity > 4.0) {
               for (int i = -20; i < 20; i++) {
                  ProjectileSimulator projectileSimulator = new ProjectileSimulator();
                  EntityPlayerSP thePlayer = mc.thePlayer;
                  ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(this.findRodHotbarSlot() + 36).getStack();
                  float f3 = rotation.pitch + (float)i;
                  projectileSimulator.initFromShooterAngles(thePlayer, stack, rotation.yaw, f3);
                  double d6 = (double)(this.target.width / 2.0F + 0.1F);
                  if (projectileSimulator.willFishingHookHitTarget((byte)100, this.target, new AxisAlignedBB(
                     doubles[0] - d6, doubles[1], doubles[2] - d6, doubles[0] + d6, doubles[1] + (double)(this.target.height / 2.0F), doubles[2] + d6
                  ))) {
                     RotationController rotationController = ClientManagers.rotationController;
                     Rotation rotation2 = RotationUtil.applyGcdFix(
                        rotation.yaw, rotation.pitch + (float)i, ClientManagers.rotationController.getPreviousRotation().yaw, ClientManagers.rotationController.getPreviousRotation().pitch
                     );
                     rotationController.requestRotation(rotation2, (byte)0, this);
                     return;
                  }
               }
            }

            ClientManagers.rotationController.requestRotation(rotation, (byte)0, this);
         }
      }
   }

   @Override
   public void onClick(EventClick event) {
      if (this.target != null) {
         EntityFishHook entityFishHook = this.findOwnFishHook();
         if (entityFishHook == null && !this.rodCast && this.target.hurtTime < this.predictedFlightTicks) {
            event.addRightClicks((byte)1);
            this.ticksSinceCast = 0;
            this.rodCast = true;
            this.needsFallbackSlotUpdate = true;
         }

         if (entityFishHook != null && this.rightClickIfRodOnTarget.getValue() && !this.rodCast && this.ticksSinceCast > 3 && this.pendingRightClickReel) {
            event.addRightClicks((byte)1);
            this.pendingRightClickReel = false;
         }
      }
   }

   @Override
   public void onPostSilentSlot() {
      this.selectedHotbarSlot = this.findRodHotbarSlot();
      if (this.target != null) {
         EntityFishHook entityFishHook = this.findOwnFishHook();
         if (entityFishHook != null) {
            BlockPos pos = new BlockPos(entityFishHook.posX, entityFishHook.posY + 0.5, entityFishHook.posZ);
            Vec3 positionEyes = mc.thePlayer.getPositionEyes(1.0F);
            double distanceToEntityBox = (double)mc.thePlayer.getDistanceToEntityBox(this.target);
            double distanceToEntityBox2 = (double)mc.thePlayer.getDistanceToEntityBox(entityFishHook);
            Vec3 vec = new Vec3((double)((float)entityFishHook.serverPosX / 32.0F), (double)((float)entityFishHook.serverPosY / 32.0F), (double)((float)entityFishHook.serverPosZ / 32.0F));
            if (distanceToEntityBox2 > distanceToEntityBox
               || positionEyes.distanceTo(vec) > this.maxRodRange.getValue()
               || entityFishHook.onGround
               || entityFishHook.inGround
               || this.target.hurtTime == 10
               || entityFishHook.isDead
               || mc.theWorld.getBlockState(pos.down()).getBlock().isFullBlock()) {
               if (this.target.hurtTime == 10) {
                  this.hookHitTarget = true;
               }

               if (EntityBoundsUtil.getEntityBoundingBoxAt(entityFishHook, vec, true).intersectsWith(EntityBoundsUtil.getEntityBoundingBoxAt(this.target, new Vec3(
                  (double)((float)this.target.serverPosX / 32.0F), (double)((float)this.target.serverPosY / 32.0F), (double)((float)this.target.serverPosZ / 32.0F)
               ), true))) {
                  this.hookHitTarget = true;
               }

               if (this.ticksSinceCast > 3) {
                  if (!this.hookHitTarget || !this.rightClickIfRodOnTarget.getValue()) {
                     this.selectedHotbarSlot = this.getFallbackHotbarSlot();
                  } else if (this.rodCast) {
                     this.pendingRightClickReel = true;
                  }

                  this.rodCast = false;
               }
            }
         }
      } else {
         if (this.hasActiveFishHook() && this.ticksSinceCast > 3) {
            this.selectedHotbarSlot = this.getFallbackHotbarSlot();
            this.rodCast = false;
         }
      }

      if (this.selectedHotbarSlot != this.findRodHotbarSlot() || mc.playerController.currentPlayerItem != this.findRodHotbarSlot()) {
         this.rodCast = false;
      }

      if (mc.thePlayer.inventory.currentItem != this.findRodHotbarSlot() && !this.combat.getValue() || this.combat.getValue() && this.selectedHotbarSlot != this.findRodHotbarSlot()) {
         if (this.hasActiveFishHook()) {
            this.selectedHotbarSlot = this.getFallbackHotbarSlot();
            this.rodCast = false;
         }
      }

      this.ticksSinceCast++;
      if (this.target != null && !mc.thePlayer.isUsingItem()) {
         SilentSlotManager.requestSlot(this, (byte)2, this.selectedHotbarSlot, this.silentSlotSwitching.getValue());
      }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (this.target != null && this.moveFix.getValue() && ClientManagers.rotationController.isOwnedBy(this)) {
         event.setMoveFixMode(MoveFixMode.DEFAULT);
      }
   }

   @Override
   public void onMoveFlying(EventMove event) {
      if (this.target != null && !this.moveFix.getValue()) {
         event.setYaw(Augustus.getInstance().getYawPitchHelper().realYaw);
      }
   }

   @Override
   public void onJumpMove(EventMove event) {
      if (this.target != null && !this.moveFix.getValue()) {
         event.setYaw(Augustus.getInstance().getYawPitchHelper().realYaw);
      }
   }

   @Override
   public void onRender3D(float partialTicks) {
      RenderUtil.begin3DRender();
      if (this.esp.getValue() && this.target != null && this.predictedTargetPos != null) {
         float f2 = (float)((double)((float)this.predictedTargetPos.xCoord) - mc.getRenderManager().getRenderPosX());
         float f3 = (float)((double)((float)this.predictedTargetPos.yCoord) - mc.getRenderManager().getRenderPosY());
         float f4 = (float)((double)((float)this.predictedTargetPos.zCoord) - mc.getRenderManager().getRenderPosZ());
         float f5 = this.target.width / 2.0F;
         BlurShadowManager.enableOneShotPass(this.blurShadowSettings.isBlurActive(), this.blurShadowSettings.isShadowActive());
         BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderPredictedTargetBox(f2, f5, f3, f4, shaderRenderPass));
      }

      RenderUtil.end3DRender();
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      this.target = null;
      this.targetSelectionSettings.setCurrentTarget((Entity)null);
   }

   private int estimateImpactTicks(EntityLivingBase entity, int n) {
      int n2 = -1;

      for (int i = 0; i < n; i++) {
         double d = entity.posX - mc.thePlayer.posX;
         double d2 = entity.posY + (double)this.target.height / 1.7 - (mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight());
         double d3 = entity.posZ - mc.thePlayer.posZ;
         double[] doubles = EntityMovementPredictor.predictFullState(this.target, n2);
         if (n2 != -1) {
            d = doubles[0] - mc.thePlayer.posX;
            d2 = doubles[1] + (double)this.target.height / 1.7 - (mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight());
            d3 = doubles[2] - mc.thePlayer.posZ;
         }

         float f = (float)Math.toDegrees(Math.atan2(d3, d)) - 90.0F;
         double d4 = (double)MathHelper.sqrt_double(d * d + d3 * d3);
         double distanceToEntity = (double)mc.thePlayer.getDistanceToEntity(this.target);
         float f2 = (float)((double)((float)(-(Math.atan2(d2, d4) * 180.0 / Math.PI))) - 0.55F * Math.max(0.0, distanceToEntity - 3.0));
         float f3 = RotationUtil.updateAngle(ClientManagers.rotationController.getPreviousRotation().yaw, f, 180.0F);
         float f4 = RotationUtil.updateAngle(ClientManagers.rotationController.getPreviousRotation().pitch, f2, 180.0F);
         ProjectileSimulator projectileSimulator = new ProjectileSimulator();
         projectileSimulator.initFromShooterAngles(mc.thePlayer, mc.thePlayer.inventoryContainer.getSlot(this.findRodHotbarSlot() + 36).getStack(), f3, f4);
         double d5 = (double)(entity.width / 2.0F + 0.1F);
         n2 = projectileSimulator.simulateFishingHookHitTick((byte)100, entity, new AxisAlignedBB(
            doubles[0] - d5, doubles[1] - 0.1F, doubles[2] - d5, doubles[0] + d5, doubles[1] + (double)entity.height + 0.1F, doubles[2] + d5
         ));
         if (n2 == Integer.MAX_VALUE) {
            return 0;
         }
      }

      return n2;
   }

   public boolean canUseRod() {
      if ((
            mc.thePlayer.getCurrentEquippedItem() == null
               || !(mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemFood)
                  && !(mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword)
                  && !(mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemPotion)
                  && !(mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBucketMilk)
               || !mc.gameSettings.keyBindUseItem.isKeyDown()
         )
         && mc.isNotInGui()
         && (mc.thePlayer.getCurrentEquippedItem() != null && mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemFishingRod || this.combat.getValue())
         && this.selectedHotbarSlot != -1
         && this.findRodHotbarSlot() != -1
         && !mc.gameSettings.keyBindUseItem.isKeyDown()) {
         if ((this.timeHelper.reached(100L) || !this.click.getValue())
            && (!this.onlyWhileKillAuraHasTarget.getValue() || ClientManagers.moduleManager.killAura.isToggled() && ClientManagers.moduleManager.killAura.target != null)) {
            ItemStack heldItemStack = SilentSlotManager.heldItemStack;
            if (this.weaponWhitelist.isItemAllowed(heldItemStack) && !this.lookDownDisableSettings.shouldDisable()) {
               return true;
            }
         }
      }

      return false;
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

   public int findRodHotbarSlot() {
      ContainerSlotQuery containerSlotQuery = ContainerSlotQuery.of(mc.thePlayer.inventoryContainer);
      containerSlotQuery = containerSlotQuery.fromSlot((byte)36);
      OptionalInt optionalInt = containerSlotQuery.filterItem(obj -> obj instanceof ItemFishingRod).findFirstSlotIndex();
      return optionalInt.isPresent() ? optionalInt.getAsInt() - 36 : -1;
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

   public EntityLivingBase getTarget() {
      return this.target;
   }

   private EntityLivingBase findTarget() {
      EntityTargetSelector entityTargetSelector = EntityTargetSelector.of(mc.theWorld.loadedEntityList);
      entityTargetSelector = entityTargetSelector.withTargetFilter(this.targetSelectionSettings.getTargetFilter());
      entityTargetSelector = entityTargetSelector.throughWalls(false);
      entityTargetSelector = entityTargetSelector.maxDistance(this.maxRodRange.getValue());
      entityTargetSelector = entityTargetSelector.withinFov(this.fovYaw.getValue(), this.fovPitch.getValue()).filterFocus();
      entityTargetSelector = entityTargetSelector.filter(obj -> this.isValidTargetState((Entity)obj));
      Optional optional = entityTargetSelector.sortBy(this.targetSelectionSettings.createTargetComparatorKey(ClientManagers.rotationController.getPreviousRotation(), -1.0)).findBest();
      EntityLivingBase entity = optional.isPresent() && optional.get() instanceof EntityLivingBase ? (EntityLivingBase)optional.get() : null;
      if (this.combat.getValue() && entity != null && (double)mc.thePlayer.getDistanceToEntityBox(entity) < 3.2) {
         entity = null;
      }

      return entity;
   }

   private boolean isValidTargetState(Entity entity) {
      return !entity.isEating() || !this.ignoreEatingTargets.getValue();
   }

   private void renderPredictedTargetBox(float f, float f2, float f3, float f4, ShaderRenderPass shaderRenderPass) {
      RenderUtil.setGlColor(this.blurShadowSettings.resolvePassColor(shaderRenderPass, this.espColor.getColorInt((byte)0)));
      RenderUtil.drawFilledBox(f - f2, f3, f4 - f2, f + f2, f3 + this.target.height, f4 + f2);
   }

}

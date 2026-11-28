package net.augustus.modules.modes.autoeagle;

import net.augustus.events.EventBlockPlace;
import net.augustus.events.EventClick;
import net.augustus.events.EventMouseMove;
import net.augustus.events.EventMoveInput;
import net.augustus.events.listeners.BlockPlaceListener;
import net.augustus.events.listeners.ClickListener;
import net.augustus.events.listeners.MouseMoveListener;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.listeners.TickListener;

import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.MoveUtil;
import net.augustus.utils.TickCounter;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.utils.rotation.MouseDeltaInterpolator;
import net.augustus.utils.rotation.Rotation;
import java.util.function.BiFunction;
import net.augustus.modules.legit.AutoEagle;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.utils.TimeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class MoonWalkAutoEagleMode extends AutoEagleMode implements MoveInputListener, BlockPlaceListener, MouseMoveListener, TickListener, ClickListener {
   private final RandomizedRangeSettings moonWalkWideness;
   private final RandomizedRangeSettings startDistToBlock;
   private final RandomizedRangeSettings postPlacementStrafeTicks;
   private final RandomizedRangeSettings minMoonwalkTicks;
   private final RandomizedRangeSettings directionChangeDelay;
   private final BooleanValue lockMouse;
   private final BooleanValue minimalMouseMovement;
   private final BooleanValue sneakEveryXBlocks;
   private final RandomizedRangeSettings blocksBetweenSneaks;
   private final RandomizedRangeSettings sneakDuration;
   private int strafeDirection;
   private boolean wasOverFullBlock;
   private boolean wasNearEdge;
   private int ticksSinceBlockPlace;
   private int ticksSinceEdgeLeave;
   private int ticksSinceDirectionChange;
   private int lastStrafeInput;
   private TimeHelper timeHelper;
   private TickCounter tickCounter;
   private MouseDeltaInterpolator mouseDeltaInterpolator;
   private int nextMicroMoveDelayMs;
   private int microMoveDurationTicks;
   private int blocksPlacedSinceSneak;
   private int sneakStartDelayTicks;
   private int remainingSneakTicks;

   public MoonWalkAutoEagleMode(AutoEagle autoEagle, SettingGroup doubleValue) {
       super(autoEagle, doubleValue, "MoonWalk");
      this.moonWalkWideness = (RandomizedRangeSettings)(new RandomizedRangeSettings(this.parentModule, "MoonWalkWideness", 0.08, 0.15, 0.0, 0.3, 2, this.modeSetting)).withoutNotInARow().withVisibility(() -> this.isSelected());
      this.startDistToBlock = (RandomizedRangeSettings)((RandomizedRangeSettings)(new RandomizedRangeSettings(this.parentModule, "StartDistToBlock", 0.05, 0.2, -0.2, 0.5, 2, this.modeSetting)).withoutNotInARow().withDisplayNamePrefix("StartDistToBlock")).withVisibility(() -> this.isSelected());
      RandomizedRangeSettings randomizedRangeSettings4 = (new RandomizedRangeSettings(this.parentModule, "PostPlacementStrafeTicks", 0.0, 1.0, 0.0, 8.0, 0, this.modeSetting)).withMaxInARow();
      double d = 15.0;
      double d2 = 0.0;
      double d3 = 8.0;
      double d4 = 0.0;
      double d5 = 8.0;
      this.postPlacementStrafeTicks = (RandomizedRangeSettings)randomizedRangeSettings4.withOutliers(5.0, d5, d4, d3, d2, d, false).withVisibility(() -> this.isSelected());
      RandomizedRangeSettings randomizedRangeSettings6 = (new RandomizedRangeSettings(this.parentModule, "MinMoonwalkTicks", 3.0, 4.0, 0.0, 10.0, 0, this.modeSetting)).withMaxInARow();
      double d7 = 15.0;
      double d8 = 0.0;
      double d9 = 10.0;
      double d10 = 0.0;
      double d11 = 12.0;
      this.minMoonwalkTicks = (RandomizedRangeSettings)randomizedRangeSettings6.withOutliers(8.0, d11, d10, d9, d8, d7, false).withVisibility(() -> this.isSelected());
      this.directionChangeDelay = (RandomizedRangeSettings)(new RandomizedRangeSettings(this.parentModule, "DirectionChangeDelay", 0.0, 1.0, 0.0, 3.0, 0, this.modeSetting)).withMaxInARow().withVisibility(() -> this.isSelected());
      this.lockMouse = (BooleanValue)new BooleanValue(this.parentModule, "LockMouse", false, this.modeSetting).withVisibility(() -> this.isSelected());
      this.minimalMouseMovement = (BooleanValue)new BooleanValue(this.parentModule, "MinimalMouseMovement", false, this.modeSetting).withVisibility(() -> this.isSelected());
      this.sneakEveryXBlocks = (BooleanValue)new BooleanValue(this.parentModule, "SneakEveryXBlocks", false, this.modeSetting).withVisibility(() -> this.isSelected());
      RandomizedRangeSettings randomizedRangeSettings9 = (new RandomizedRangeSettings(this.parentModule, "BlocksBetweenSneaks", 30.0, 32.0, 0.0, 128.0, 0, this.modeSetting)).withMaxInARow();
      SettingVisibilityCondition condition9 = () -> {
         return this.sneakEveryXBlocks.getValue() && this.isSelected();
      };
      this.blocksBetweenSneaks = (RandomizedRangeSettings)randomizedRangeSettings9.withVisibility(condition9);
      this.sneakDuration = (RandomizedRangeSettings)(new RandomizedRangeSettings(this.parentModule, "SneakDuration", 11.0, 15.0, 0.0, 20.0, 0, this.modeSetting)).withMaxInARow().withVisibility(() -> this.isSneakDurationVisible());
      this.strafeDirection = 1;
      this.wasOverFullBlock = false;
      this.wasNearEdge = false;
      this.ticksSinceBlockPlace = 0;
      this.ticksSinceEdgeLeave = 0;
      this.ticksSinceDirectionChange = 0;
      this.lastStrafeInput = 0;
      this.timeHelper = new TimeHelper();
      this.tickCounter = new TickCounter();
      this.mouseDeltaInterpolator = new MouseDeltaInterpolator();
      this.nextMicroMoveDelayMs = 0;
      this.microMoveDurationTicks = 0;
      this.blocksPlacedSinceSneak = 0;
      this.sneakStartDelayTicks = DefaultRandomGenerator.instance.nextInt((byte)1, (byte)3);
      this.remainingSneakTicks = 0;
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(21);
      this.toggleEventSubscription(60);
      this.toggleEventSubscription(19);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(7, 10);
   }

   @Override
   public void onEnable() {
      this.strafeDirection = Math.random() < 0.5 ? -1 : 1;
      this.lastStrafeInput = this.strafeDirection;
      this.wasOverFullBlock = false;
      this.ticksSinceBlockPlace = 0;
      this.ticksSinceEdgeLeave = 0;
      this.ticksSinceDirectionChange = 0;
      this.blocksPlacedSinceSneak = 0;
      this.blocksBetweenSneaks.nextValue();
      this.remainingSneakTicks = 0;
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onPostBlockPlace(EventBlockPlace event) {
      this.blocksPlacedSinceSneak++;
      this.postPlacementStrafeTicks.nextValue();
      this.ticksSinceBlockPlace = 0;
   }

   @Override
   public void onEarlyTick() {
      this.mouseDeltaInterpolator.clearTargetDelta();
      if (this.isInMoonWalkPitchRange() && MoveUtil.isMoving() && this.minimalMouseMovement.getValue()) {
         long nextMicroMoveDelayMs2 = (long)this.nextMicroMoveDelayMs;
         if (this.timeHelper.reached(nextMicroMoveDelayMs2) && mc.theWorld.getBlockState(mc.thePlayer.posX, mc.thePlayer.posY - 0.5, mc.thePlayer.posZ).getBlock().getMaterial() != Material.air
            )
          {
            this.tickCounter.increment();
            int microMoveDurationTicks2 = this.microMoveDurationTicks;
            if (this.tickCounter.hasReached(microMoveDurationTicks2)) {
               this.nextMicroMoveDelayMs = DefaultRandomGenerator.instance.nextInt((short)750, (short)1500);
               this.microMoveDurationTicks = DefaultRandomGenerator.instance.nextInt((byte)1, (byte)2);
               this.tickCounter.reset();
               this.timeHelper.reset();
            }

            double d = 0.2;
            double d2 = -0.2;
            float f = DefaultRandomGenerator.instance.nextFloat(d2, d);
            double d3 = 0.2;
            double d4 = -0.2;
            float f2 = DefaultRandomGenerator.instance.nextFloat(d4, d3);
            float f3 = mc.thePlayer.rotationYaw + f;
            float f4 = mc.thePlayer.rotationPitch + f2;
            if ((double)f4 >= 80.4) {
               f4 = mc.thePlayer.rotationPitch - f2;
            } else if ((double)f4 <= 80.0) {
               f4 = mc.thePlayer.rotationPitch - f2;
            }

            this.mouseDeltaInterpolator.setTargetRotationBounded(f3, f4, new Rotation(mc.thePlayer.rotationYaw - 10.0F, 80.0F), new Rotation(mc.thePlayer.rotationYaw + 10.0F, 80.4F));
         }
      }
   }

   @Override
   public void onMouseMove(EventMouseMove event) {
      if (this.isInMoonWalkPitchRange() && MoveUtil.isMoving() && mc.gameSettings.keyBindUseItem.isKeyDown() && this.lockMouse.getValue()) {
         event.setDeltaY((byte)0);
         event.setDeltaX((byte)0);
      }

      if (this.minimalMouseMovement.getValue()) {
         this.mouseDeltaInterpolator.onMouseMove(event);
      }
   }

   @Override
   public void onPostTick() {
      this.mouseDeltaInterpolator.onTick();
   }

   @Override
   public void onClick(EventClick event) {
      if (this.remainingSneakTicks > 0 && this.sneakStartDelayTicks == 0 && ((AutoEagle)this.parentModule).isPitchAboveMinimum()) {
         event.setBlockAllClicks(true);
         event.setIgnoreVanillaClicks(true);
      }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (!((AutoEagle)this.parentModule).isPitchAboveMinimum()) {
         ((AutoEagle)this.parentModule).eagling = false;
         this.blocksPlacedSinceSneak = 0;
         this.remainingSneakTicks = 0;
      } else {
         if (!((AutoEagle)this.parentModule).onlyWhileSneak.getValue()) {
            event.setSneak(true);
         }

         ((AutoEagle)this.parentModule).eagling = ((AutoEagle)this.parentModule).fastPlace.getValue() && (event.isSneak() || this.isInMoonWalkPitchRange()) && ((AutoEagle)this.parentModule).isPitchAboveMinimum();
         if (!event.isSneak() && ((AutoEagle)this.parentModule).onlyWhileSneak.getValue()) {
            this.blocksPlacedSinceSneak = 0;
            this.remainingSneakTicks = 0;
         } else {
            this.updateMoonWalkMovement(event);
         }
      }
   }

   private void updateMoonWalkMovement(EventMoveInput event) {
      if (this.isInMoonWalkPitchRange() && MoveUtil.isMoving()) {
         if (mc.gameSettings.keyBindUseItem.isKeyDown()) {
            event.setSneak(false);
         }

         if (this.sneakEveryXBlocks.getValue()) {
            this.handlePeriodicSneak(event);
         } else {
            this.remainingSneakTicks = 0;
         }

         if (this.remainingSneakTicks > 0) {
            return;
         }

         BlockPos pos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ).down();
         boolean isFullBlock = mc.theWorld.getBlockState(pos).getBlock().isFullBlock();
         if (this.wasOverFullBlock != isFullBlock) {
            this.startDistToBlock.nextValue();
            this.moonWalkWideness.nextValue();
         }

         this.wasOverFullBlock = isFullBlock;
         boolean flag2 = false;

         for (AxisAlignedBB box : (Iterable<AxisAlignedBB>)(mc.theWorld.getCollidingBoundingBoxesCapped(mc.thePlayer, mc.thePlayer.getEntityBoundingBox().offset(0.0, -1.0, 0.0)))) {
            if (mc.thePlayer
               .getEntityBoundingBox()
               .expand(-0.3, 0.0, -0.3)
               .offset(0.0, -1.0, 0.0)
               .intersectsWith(
                  box.expand(
                     mc.thePlayer.getHorizontalFacing() != EnumFacing.EAST && mc.thePlayer.getHorizontalFacing() != EnumFacing.WEST ? 0.0 : -this.startDistToBlock.getLastValue(),
                     0.0,
                     mc.thePlayer.getHorizontalFacing() != EnumFacing.NORTH && mc.thePlayer.getHorizontalFacing() != EnumFacing.SOUTH ? 0.0 : -this.startDistToBlock.getLastValue()
                  )
               )) {
               flag2 = true;
               break;
            }
         }

         if (!flag2 && this.wasNearEdge) {
            this.ticksSinceEdgeLeave = 0;
            this.minMoonwalkTicks.nextValue();
         }

         this.ticksSinceEdgeLeave++;
         this.ticksSinceBlockPlace++;
         if (flag2) {
            if (this.ticksSinceBlockPlace <= this.postPlacementStrafeTicks.getLastIntValue() || this.ticksSinceEdgeLeave <= this.minMoonwalkTicks.getLastIntValue()) {
               this.updateStrafeForFacing(event, pos);
            }
         } else {
            this.updateStrafeForFacing(event, pos);
         }

         this.wasNearEdge = flag2;
      } else {
         this.remainingSneakTicks = 0;
         this.ticksSinceEdgeLeave = 0;
         this.ticksSinceBlockPlace = 0;
         this.blocksPlacedSinceSneak = 0;
      }

      this.lastStrafeInput = (int)event.getMoveStrafe();
   }

   private void handlePeriodicSneak(EventMoveInput event) {
      if (this.blocksPlacedSinceSneak >= this.blocksBetweenSneaks.getLastIntValue()) {
         this.blocksPlacedSinceSneak = 0;
         this.remainingSneakTicks = (int)this.sneakDuration.nextValue();
         this.sneakStartDelayTicks = DefaultRandomGenerator.instance.nextInt((byte)3, (byte)4);
      }

      if (this.sneakStartDelayTicks > 0) {
         this.sneakStartDelayTicks--;
      }

      if (this.remainingSneakTicks > 0 && this.sneakStartDelayTicks == 0) {
         this.remainingSneakTicks--;
         event.setSneak(true);
      }
   }

   private void updateStrafeForFacing(EventMoveInput event, BlockPos pos) {
      switch (mc.thePlayer.getHorizontalFacing()) {
         case EAST:
            this.computeStrafeAlongZ(event, pos, mc.thePlayer.posZ, true);
            break;
         case WEST:
            this.computeStrafeAlongZ(event, pos, mc.thePlayer.posZ, false);
            break;
         case NORTH:
            this.computeStrafeAlongX(event, pos, mc.thePlayer.posX, true);
            break;
         case SOUTH:
            this.computeStrafeAlongX(event, pos, mc.thePlayer.posX, false);
      }
   }

   private boolean isInMoonWalkPitchRange() {
      return (double)mc.thePlayer.rotationPitch < 80.5 && (double)mc.thePlayer.rotationPitch > 79.8;
   }

   private void computeStrafeAlongX(EventMoveInput event, BlockPos pos, double d, boolean flag) {
      double lastValue = this.moonWalkWideness.getLastValue();
      byte b = 0;
      if (flag) {
         if ((double)pos.getX() + 0.5 - lastValue > d) {
            b = -1;
         } else if ((double)pos.getX() + 0.5 + lastValue < d) {
            b = 1;
         }
      } else if ((double)pos.getX() + 0.5 + lastValue < d) {
         b = -1;
      } else if ((double)pos.getX() + 0.5 - lastValue > d) {
         b = 1;
      }

      this.applyDelayedStrafeDirection(event, b);
   }

   private void computeStrafeAlongZ(EventMoveInput event, BlockPos pos, double d, boolean flag) {
      double lastValue = this.moonWalkWideness.getLastValue();
      byte b = 0;
      if (flag) {
         if ((double)pos.getZ() + 0.5 - lastValue > d) {
            b = -1;
         } else if ((double)pos.getZ() + 0.5 + lastValue < d) {
            b = 1;
         }
      } else if ((double)pos.getZ() + 0.5 + lastValue < d) {
         b = -1;
      } else if ((double)pos.getZ() + 0.5 - lastValue > d) {
         b = 1;
      }

      this.applyDelayedStrafeDirection(event, b);
   }

   private void applyDelayedStrafeDirection(EventMoveInput event, int n) {
      this.ticksSinceDirectionChange++;
      if (this.lastStrafeInput != 0 && n != 0 && this.lastStrafeInput != n) {
         this.ticksSinceDirectionChange = 0;
         int n2 = this.directionChangeDelay.nextInt();
         this.strafeDirection = 0;
         if (n2 > 0) {
            if (mc.theWorld.getBlockState(mc.thePlayer.posX, mc.thePlayer.posY - 0.5, mc.thePlayer.posZ).getBlock().getMaterial() == Material.air && mc.thePlayer.onGround) {
               this.ticksSinceDirectionChange = 100;
            }
         }
      }

      if (this.ticksSinceDirectionChange >= this.directionChangeDelay.getLastIntValue()) {
         if (n != 0) {
            this.strafeDirection = n;
         }

         event.setMoveStrafe((float)this.strafeDirection);
      }
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new MoonWalkAutoEagleMode((AutoEagle)obj, (SettingGroup)obj2);
   }

   private boolean isSneakDurationVisible() {
      return this.sneakEveryXBlocks.getValue() && this.isSelected();
   }

}

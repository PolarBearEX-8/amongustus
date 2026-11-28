package net.augustus.modules.movement;

import net.minecraft.network.play.client.C03PacketPlayer;
import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.MotionListener;
import net.augustus.settings.Setting;
import net.augustus.events.listeners.StepListener;
import net.augustus.utils.MoveUtil;
import net.augustus.modules.movement.helper.StepBlockCollisionUtil;
import net.augustus.events.listeners.MoveEntityListener;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.events.EventMoveInput;
import net.augustus.Augustus;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.EventStep;
import net.augustus.events.EventMoveEntity;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.events.EventMotion;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;

public class Step extends Module implements TickListener, MoveEntityListener, UpdateListener, MotionListener, StepListener, MoveInputListener {
   public StringValue modes;
   public final DoubleValue height;
   public final DoubleValue delay;
   public final DoubleValue timer;
   public final BooleanValue slowDown;
   private final TimeHelper stepDelayTimer;
   public final TimeHelper postStepTimer;
   private boolean canStep;
   private boolean timerModified;
   private double preMovePosX;
   private double preMovePosZ;
   private int ticksSinceVulcanStep;
   private int onGroundTicks;
   private boolean hypixelStepping;

   public Step() {
       super("Step", "Schritt", new Color(129, 147, 164), Categorys.MOVEMENT);
      this.modes = new StringValue(
         this, "Modes", "Vanilla", new String[]{"Vanilla", "NCPPacket", "Verus", "Vulcan", "Intave", "IntaveTest", "Legit", "Hypixel"}
      );
      this.height = new DoubleValue(this, "Height", 1.0, 1.0, 5.0, 2).visibleWhen(() -> this.isHeightVisible());
      this.delay = new DoubleValue(this, "Delay", 400.0, 0.0, 1000.0, 0).visibleWhen(() -> this.isDelayVisible());
      this.timer = new DoubleValue(this, "Timer", 0.3, 0.05, 1.0, 2).visibleWhen(() -> this.isTimerVisible());
      this.slowDown = (BooleanValue)new BooleanValue(this, "SlowDown", true).withVisibility(() -> this.isSlowDownVisible());
      this.stepDelayTimer = new TimeHelper();
      this.postStepTimer = new TimeHelper();
      this.preMovePosX = 0.0;
      this.preMovePosZ = 0.0;
      this.ticksSinceVulcanStep = 0;
      this.onGroundTicks = 0;
      this.hypixelStepping = false;
   }

   @Override
   public void onEnable() {
      this.hypixelStepping = false;
   }

   @Override
   public void onDisable() {
      if (mc.thePlayer != null) {
         mc.thePlayer.stepHeight = 0.6F;
      }

      mc.getTimer().timerSpeed = 1.0F;
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(20);
      this.toggleEventSubscription(43);
      this.toggleEventSubscription(18);
      this.toggleEventSubscription(37);
      this.toggleEventSubscription(21);
   }

   @Override
   public void onLateTick() {
   }

   @Override
   public void onPreMoveEntity(EventMoveEntity event) {
      this.preMovePosX = mc.thePlayer.posX;
      this.preMovePosZ = mc.thePlayer.posZ;
   }

   @Override
   public void onPostMoveEntity(EventMoveEntity event) {
      this.onGroundTicks = mc.thePlayer.onGround ? this.onGroundTicks + 1 : 0;
      if (this.modes.getSelectedValue().equals("Legit")) {
         AutoWalk autoWalk = Augustus.getInstance().getModuleManager().autoWalk;
         float rotationYaw = mc.thePlayer.rotationYaw;
         if (autoWalk.isToggled()
            && autoWalk.pathFinding.getValue()
            && autoWalk.movementmode.getSelectedValue().equalsIgnoreCase("silent")
            && System.currentTimeMillis() - autoWalk.lastSilentMoveTime < 100L) {
            mc.thePlayer.rotationYaw = autoWalk.silentMoveYaw;
         }

         mc.thePlayer.updateAutoJump((float)(mc.thePlayer.posX - this.preMovePosX) * 1.6F, (float)(mc.thePlayer.posZ - this.preMovePosZ) * 1.6F);
         mc.thePlayer.rotationYaw = rotationYaw;
      }
   }

   @Override
   public void onPreUpdate() {
      this.setSuffix(this.modes.getSelectedValue());
      if (this.modes.getSelectedValue().equals("Hypixel")) {
         if (mc.thePlayer.onGround) {
            if (this.canStepUpHeight(1.0) && MoveUtil.isMoving()) {
               mc.thePlayer.jump();
               this.hypixelStepping = true;
               return;
            }
         }

         if (mc.thePlayer.airTicks == 3 && this.hypixelStepping) {
            mc.thePlayer.motionY = 0.0;
         } else if (mc.thePlayer.airTicks == 4 && this.hypixelStepping) {
            mc.thePlayer.motionY = 0.0;
            MoveUtil.setSpeed(0.4F, false);
            if (!this.slowDown.getValue()) {
               this.hypixelStepping = false;
            }
         } else if (mc.thePlayer.onGround && this.hypixelStepping && this.slowDown.getValue()) {
            MoveUtil.setSpeed(0.2F, false);
            this.hypixelStepping = false;
         }
      }
   }

   @Override
   public void onPreMotion(EventMotion event) {
      this.ticksSinceVulcanStep++;
      switch (this.modes.getSelectedValue()) {
         case "Intave":
            if (this.canStepUpHeight(this.height.getValue())) {
               if (this.stepDelayTimer.reached(6000L) && !mc.gameSettings.keyBindJump.isKeyDown()) {
                  this.canStep = false;
                  mc.thePlayer.jump();
                  mc.thePlayer.motionY = Math.min(mc.thePlayer.motionY, 0.6);
               }
            }

            if (!this.canStep) {
               if (!this.canStepUpHeight(this.height.getValue())) {
                  this.stepDelayTimer.reset();
                  this.canStep = true;
               }
            }
            break;
         case "IntaveTest":
            if (this.canStepUpHeight(this.height.getValue())) {
               if (mc.thePlayer.onGround) {
                  mc.thePlayer.motionY = 0.429;
                  PlayerUtil.triggerJumpStat();
               }

               if (mc.thePlayer.posY % 1.0 > 0.7) {
                  MoveUtil.addMotion(0.11, false);
               }
            }
            break;
         case "Verus":
            if (mc.thePlayer.ticksSinceJump > 11) {
               mc.thePlayer.stepHeight = 1.0F;
            } else {
               mc.thePlayer.stepHeight = 0.6F;
            }
            break;
         case "Vulcan":
            if (mc.thePlayer.ticksSinceJump > 40 && mc.thePlayer.motionY <= 0.0 && this.onGroundTicks > 4) {
               mc.thePlayer.stepHeight = 1.0F;
            } else {
               mc.thePlayer.stepHeight = 0.6F;
            }
      }
   }

   @Override
   public void onPreStep(EventStep event) {
      if (this.timerModified) {
         mc.getTimer().timerSpeed = 1.0F;
         this.postStepTimer.reset();
         this.timerModified = false;
      }

      switch (this.modes.getSelectedValue()) {
         case "Vanilla":
         case "NCPPacket":
            event.setStepHeight((float)this.height.getValue());
            if (!this.stepDelayTimer.reached((long)this.delay.getValue())) {
               event.setStepHeight(0.6F);
               this.canStep = false;
            } else {
               this.canStep = true;
               this.stepDelayTimer.reset();
            }
      }
   }

   @Override
   public void onPostStep() {
      Vec3 vec = new Vec3(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
      switch (this.modes.getSelectedValue()) {
         case "Vanilla":
            if (mc.thePlayer.getEntityBoundingBox().minY - vec.yCoord > 0.6 && this.canStep && this.timer.getValue() != 1.0) {
               mc.getTimer().timerSpeed = (float)this.timer.getValue();
               this.timerModified = true;
            }
            break;
         case "NCPPacket":
            if (mc.thePlayer.getEntityBoundingBox().minY - vec.yCoord > 0.6 && this.canStep) {
               double d = mc.thePlayer.getEntityBoundingBox().minY - vec.yCoord;
               byte b = 2;
               if (d == 1.0) {
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(vec.xCoord, vec.yCoord + 0.42, vec.zCoord, false));
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(vec.xCoord, vec.yCoord + 0.75, vec.zCoord, false));
                  b = 3;
               } else if (d < 0.65) {
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(vec.xCoord, vec.yCoord + 0.35, vec.zCoord, false));
               } else if (d < 0.76) {
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(vec.xCoord, vec.yCoord + 0.42, vec.zCoord, false));
               } else if (d < 0.878) {
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(vec.xCoord, vec.yCoord + 0.42, vec.zCoord, false));
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(vec.xCoord, vec.yCoord + 0.73, vec.zCoord, false));
                  b = 3;
               } else if (d < 1.26) {
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(vec.xCoord, vec.yCoord + 0.425, vec.zCoord, false));
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(vec.xCoord, vec.yCoord + 0.779, vec.zCoord, false));
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(vec.xCoord, vec.yCoord + 1.06, vec.zCoord, false));
                  b = 4;
               } else if (d < 1.6) {
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(vec.xCoord, vec.yCoord + 0.425, vec.zCoord, false));
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(vec.xCoord, vec.yCoord + 0.779, vec.zCoord, false));
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(vec.xCoord, vec.yCoord + 1.0538, vec.zCoord, false));
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(vec.xCoord, vec.yCoord + 1.265, vec.zCoord, false));
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(vec.xCoord, vec.yCoord + 1.3, vec.zCoord, false));
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(vec.xCoord, vec.yCoord + 1.25, vec.zCoord, false));
                  b = 7;
               } else if (d < 2.1) {
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(vec.xCoord, vec.yCoord + 0.425, vec.zCoord, false));
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(vec.xCoord, vec.yCoord + 0.82, vec.zCoord, false));
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(vec.xCoord, vec.yCoord + 0.7, vec.zCoord, false));
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(vec.xCoord, vec.yCoord + 0.595, vec.zCoord, false));
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(vec.xCoord, vec.yCoord + 1.01, vec.zCoord, false));
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(vec.xCoord, vec.yCoord + 1.37, vec.zCoord, false));
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(vec.xCoord, vec.yCoord + 1.657, vec.zCoord, false));
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(vec.xCoord, vec.yCoord + 1.87, vec.zCoord, false));
                  b = 10;
               }

               if (this.timer.getValue() != 1.0) {
                  mc.getTimer().timerSpeed = (float)Math.max(d == 1.0 ? this.timer.getValue() : this.timer.getValue() * 3.43333 / (double)b, 0.1);
                  this.timerModified = true;
               }
            }
            break;
         case "Verus":
            if ((double)mc.thePlayer.stepHeight <= 0.6 || mc.thePlayer.getEntityBoundingBox().minY - vec.yCoord <= 0.6) {
               return;
            }

            mc.getTimer().timerSpeed = 0.5F;
            mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.5, mc.thePlayer.posZ, true));
            this.timerModified = true;
            break;
         case "Vulcan":
            if ((double)mc.thePlayer.stepHeight <= 0.6 || mc.thePlayer.getEntityBoundingBox().minY - vec.yCoord <= 0.6) {
               return;
            }

            mc.getTimer().timerSpeed = 0.5F;
            mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.5, mc.thePlayer.posZ, true));
            this.timerModified = true;
            this.ticksSinceVulcanStep = 0;
      }
   }

   private boolean canStepUpHeight(double d) {
      if (mc.thePlayer.isCollidedHorizontally) {
         BlockPos pos = null;

         for (int i = 0; i < 6; i++) {
            if (StepBlockCollisionUtil.isSolidBlock(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - (double)i, mc.thePlayer.posZ))) {
               pos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - (double)i + 1.0, mc.thePlayer.posZ);
               break;
            }
         }

         if (pos == null) {
            return false;
         } else {
            float f = (float)Math.toDegrees(MoveUtil.getDirection());
            EnumFacing horizontalFacing = mc.thePlayer.getHorizontalFacing(f);
            BlockPos pos2 = pos.offset(horizontalFacing);
            double d2 = 0.0;

            for (int j = 0; (double)j <= d + 3.0; j++) {
               if (StepBlockCollisionUtil.isSolidBlock(pos2)) {
                  Block block = mc.theWorld.getBlockState(pos2).getBlock();
                  d2 += block.getBlockBoundsMaxY();
                  if (d2 > d) {
                     return false;
                  }

                  if (j > 0 && !StepBlockCollisionUtil.isSolidBlock(pos2.down())) {
                     return false;
                  }
               } else {
                  if (!StepBlockCollisionUtil.isSolidBlock(pos2.down())
                     && ((double)pos2.down().getY() >= mc.thePlayer.posY || !mc.thePlayer.onGround)
                     && !StepBlockCollisionUtil.isSolidBlock(pos2)) {
                     BlockPos pos3 = pos.offset(horizontalFacing);
                     Block block2 = mc.theWorld.getBlockState(pos3).getBlock();
                     if (block2.isFullBlock()) {
                        return mc.thePlayer.posY + d > (double)pos3.getY() + block2.getBlockBoundsMaxY() - 1.0;
                     }
                  }

                  d2++;
               }

               pos2 = pos2.up();
            }

            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (this.modes.getSelectedValue().equals("Vulcan") && this.ticksSinceVulcanStep < 5) {
         event.setJump(false);
      }
   }

   private boolean isSlowDownVisible() {
      return this.modes.getSelectedValue().equals("Hypixel");
   }

   private boolean isTimerVisible() {
      return this.modes.getSelectedValue().equals("NCPPacket") || this.modes.getSelectedValue().equals("Vanilla");
   }

   private boolean isDelayVisible() {
      return this.modes.getSelectedValue().equals("NCPPacket") || this.modes.getSelectedValue().equals("Vanilla");
   }

   private boolean isHeightVisible() {
      return !this.modes.getSelectedValue().equals("Verus") && !this.modes.getSelectedValue().equals("Vulcan");
   }


}

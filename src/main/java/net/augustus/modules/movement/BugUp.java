package net.augustus.modules.movement;

import de.florianmichael.dietrichevents2.StateTypes;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.ClickListener;
import net.augustus.events.listeners.BlockBoundingBoxListener;
import net.augustus.events.EventMove;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.SilentSlotListener;
import net.augustus.utils.math.RandomNumberGenerator;
import net.augustus.events.EventClick;
import net.augustus.settings.Setting;
import net.augustus.settings.RangeSetting;
import net.augustus.events.EventPacketDelay;
import net.augustus.events.listeners.StepListener;
import net.augustus.utils.MoveUtil;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.events.listeners.MoveListener;
import net.augustus.events.EventBlockBoundingBox;
import net.augustus.events.MoveEventType;
import net.augustus.events.EventSlowDown;
import net.augustus.events.listeners.PacketDelayListener;
import net.augustus.events.listeners.MoveEntityListener;
import net.augustus.utils.world.BlockUtil;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.viaversion.ViaAugustus;
import net.augustus.events.listeners.SlowDownListener;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.events.EventMoveInput;
import net.augustus.utils.TickCounter;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.EventStep;
import net.augustus.events.EventMoveEntity;
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockSlime;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

public class BugUp extends Module implements MoveEntityListener, UpdateListener, PacketListener, TickListener, BlockBoundingBoxListener, MoveInputListener, PacketDelayListener, ClickListener, SilentSlotListener {
   public StringValue mode;
   public final DoubleValue maxDistance;
   public final BooleanValue bugUpOnlyIfFallingIntoVoid;
   private final DoubleValue fallDistance;
   public final RangeSetting backFlagDist;
   public final RangeSetting blinkPackets;
   private final TimeHelper actionCooldownTimer;
   private double[] lastGroundPos;
   public boolean buggingUp;
   private double collisionFloorY;
   private int blinkedPacketCount;
   private boolean blinking;
   private boolean wasOnGround;
   private int maxBlinkPackets;
   private boolean receivedPosLook;
   private boolean platformReady;
   private boolean fallingIntoVoid;
   private int blazeRodSlot;
   private final TimeHelper platformClickTimer;
   private final TickCounter tickCounter;
   private int platformSwitchDelay;
   private final ArrayList queuedNcpPackets;

   public BugUp() {
       super("BugUp", "Zurücksetzten", Color.DARK_GRAY, Categorys.MOVEMENT);
      this.mode = new StringValue(
         this,
         "Mode",
         "OnGround",
         new String[]{
            "Teleport",
            "SetPosition",
            "OnGround",
            "OnGroundSpoof",
            "Speed",
            "Jump",
            "Collision",
            "AirStuck",
            "BlinkOutgoing",
            "BlinkBoth",
            "AutoPlatform",
            "NCP"
         }
      );
      this.maxDistance = new DoubleValue(this, "MaxDistance", 15.0, 3.0, 30.0, 0).named("MaxFallDistanceWithoutBugUp").visibleWhen(() -> this.isMaxDistanceSettingVisible());
      this.bugUpOnlyIfFallingIntoVoid = (BooleanValue)new BooleanValue(this, "BugUpOnlyIfFallingIntoVoid", false).withVisibility(() -> this.isOnlyVoidSettingVisible());
      DoubleValue fallDistanceSetting = new DoubleValue(this, "FallDistance", 4.0, 3.0, 15.0, 0);
      SettingVisibilityCondition condition3 = () -> {
         return this.mode.getSelectedValue().equals("NCP");
      };
      this.fallDistance = fallDistanceSetting.visibleWhen(condition3);
      this.backFlagDist = new RangeSetting(this, "BackFlagDist", 2.0, 3.0, 0.0, 10.0, 0).visibleWhen(() -> this.isBackFlagDistVisible());
      this.blinkPackets = new RangeSetting(this, "BlinkPackets", 16.0, 16.0, 1.0, 20.0, 0).visibleWhen(() -> this.isBlinkPacketsVisible());
      this.actionCooldownTimer = new TimeHelper();
      this.lastGroundPos = new double[3];
      this.buggingUp = false;
      this.collisionFloorY = 0.0;
      this.blinkedPacketCount = 0;
      this.blinking = false;
      this.wasOnGround = false;
      this.receivedPosLook = false;
      this.platformReady = false;
      this.fallingIntoVoid = false;
      this.blazeRodSlot = -1;
      this.platformClickTimer = new TimeHelper();
      this.tickCounter = new TickCounter();
      this.platformSwitchDelay = 1;
      this.queuedNcpPackets = new ArrayList();
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
      this.buggingUp = false;
      this.blinking = false;
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(20);
      this.toggleEventSubscription(43);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(3);
      this.toggleEventSubscription(21);
      this.toggleEventSubscription(25);
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(34);
   }

   @Override
   public void onTick() {
      this.setSuffix(this.mode.getSelectedValue());
      if (mc.thePlayer.onGround) {
         this.lastGroundPos = new double[]{mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ};
      }

      if (this.shouldBugUp()) {
         this.buggingUp = true;
         switch (this.mode.getSelectedValue()) {
            case "Teleport":
               if (this.actionCooldownTimer.reached(200L)) {
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.lastGroundPos[0], this.lastGroundPos[1], this.lastGroundPos[2], true));
                  this.actionCooldownTimer.reset();
               }
               break;
            case "SetPosition":
               mc.thePlayer.setPosition(this.lastGroundPos[0], this.lastGroundPos[1], this.lastGroundPos[2]);
               break;
            case "OnGround":
               mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
               break;
            case "AirStuck":
               mc.gameSettings.keyBindForward.setPressed(false);
               mc.gameSettings.keyBindBack.setPressed(false);
               mc.gameSettings.keyBindLeft.setPressed(false);
               mc.gameSettings.keyBindRight.setPressed(false);
               break;
            case "Speed":
               MoveUtil.setSpeed(1.0F);
               break;
            case "Jump":
               mc.thePlayer.jump();
         }
      } else {
         this.buggingUp = false;
      }
   }

   @Override
   public void onPreMoveEntity(EventMoveEntity event) {
      if (!this.shouldBugUp()) {
         this.collisionFloorY = mc.thePlayer.posY + event.getY();
      } else {
         switch (this.mode.getSelectedValue()) {
            case "AirStuck":
               event.setX(0.0);
               event.setY(0.0);
               event.setZ(0.0);
            case "Collision":
         }
      }
   }

   @Override
   public void onPreUpdate() {
      if (this.shouldBugUp() && this.mode.getSelectedValue().equals("AirStuck")) {
         mc.thePlayer.motionY = 0.0;
         mc.thePlayer.motionX = 0.0;
         mc.thePlayer.motionZ = 0.0;
      }
   }

   @Override
   public void onPostUpdate() {
      if ((this.mode.getSelectedValue().equals("BlinkOutgoing") || this.mode.getSelectedValue().equals("BlinkBoth"))
         && mc.thePlayer.ticksExisted > 50
         && mc.playerController.gameIsSurvivalOrAdventure()) {
         if (this.wasOnGround) {
            if ((this.actionCooldownTimer.reached(2000L) || this.receivedPosLook) && !BlockUtil.isScaffoldActiveAndValid()) {
               this.blinking = this.predictWillFallIntoVoid();
               this.blinkedPacketCount = 0;
               if (this.blinking) {
                  this.receivedPosLook = false;
                  this.maxBlinkPackets = Math.max(DefaultRandomGenerator.instance.nextInt(this.blinkPackets.getMinValue(), this.blinkPackets.getMaxValue()), 1);
               }
            }
         }

         if (this.blinking && this.blinkedPacketCount > this.maxBlinkPackets) {
            this.blinking = false;
            this.actionCooldownTimer.reset();
            this.blinkedPacketCount = 0;
         }

         if (BlockUtil.isScaffoldActiveAndValid()) {
            this.blinkedPacketCount = 0;
            this.blinking = false;
         }
      }

      this.wasOnGround = mc.thePlayer.onGround;
   }

   @Override
   public void onPacketSend(EventPacket event) {
      Packet packet = event.getPacket();
      if (mc.thePlayer != null && mc.theWorld != null) {
         if (this.shouldBugUp()) {
            switch (this.mode.getSelectedValue()) {
               case "AirStuck":
                  if (packet instanceof C03PacketPlayer) {
                     event.cancel();
                  }
                  break;
               case "OnGroundSpoof":
                  if (packet instanceof C03PacketPlayer) {
                     ((C03PacketPlayer)packet).setOnGround(true);
                  }
            }
         }

         if (this.mode.getSelectedValue().equals("NCP") && !ClientManagers.moduleManager.speed.isToggled() && packet instanceof C03PacketPlayer) {
            if (!this.isBlockBelow()) {
               if ((double)mc.thePlayer.fallDistance < this.fallDistance.getValue()) {
                  event.cancel();
                  this.queuedNcpPackets.add(event.getPacket());
               } else if (!this.queuedNcpPackets.isEmpty()) {
                  this.queuedNcpPackets.forEach(obj -> {
                     C03PacketPlayer packet3 = (C03PacketPlayer)obj;
                     packet3.setPositionY(mc.thePlayer.lastGroundY);
                     mc.thePlayer.sendQueue.addToSendQueueUnchecked(packet3);
                  });
                  this.queuedNcpPackets.clear();
               }
            } else if (!this.queuedNcpPackets.isEmpty()) {
               this.queuedNcpPackets.forEach(obj2 -> mc.thePlayer.sendQueue.addToSendQueueUnchecked((Packet)obj2));
               this.queuedNcpPackets.clear();
            }
         }

         if (packet instanceof C03PacketPlayer && this.blinking && (this.mode.getSelectedValue().equals("BlinkOutgoing") || this.mode.getSelectedValue().equals("BlinkBoth"))) {
            this.blinkedPacketCount++;
         }

         if (packet instanceof S08PacketPlayerPosLook) {
            this.receivedPosLook = true;
         }
      }
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      if (event.getPacket() instanceof S08PacketPlayerPosLook) {
         this.buggingUp = false;
      }
   }

   @Override
   public void onBlockBoundingBox(EventBlockBoundingBox event) {
      if (this.buggingUp && this.mode.getSelectedValue().equals("Collision") && event.getBlock() instanceof BlockAir && (double)(event.getBlockPos().getY() + 1) < this.collisionFloorY) {
         BlockPos blockPos = event.getBlockPos();
         event.setBoundingBox(new AxisAlignedBB(
            (double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), (double)(blockPos.getX() + 1), (double)(blockPos.getY() + 1), (double)(blockPos.getZ() + 1)
         ));
      }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if ((this.mode.getSelectedValue().equals("BlinkOutgoing") || this.mode.getSelectedValue().equals("BlinkBoth")) && this.blinking) {
         event.setMoveForward(1.0F);
      }
   }

   @Override
   public void onOutgoingPacketDelay(EventPacketDelay event) {
      if ((this.mode.getSelectedValue().equals("BlinkOutgoing") || this.mode.getSelectedValue().equals("BlinkBoth")) && this.blinking) {
         event.setDelayed(true);
      }
   }

   @Override
   public void onIncomingPacketDelay(EventPacketDelay event) {
      if (this.mode.getSelectedValue().equals("BlinkBoth") && this.blinking) {
         event.setDelayed(true);
      }
   }

   @Override
   public void onClick(EventClick event) {
      if (this.mode.getSelectedValue().equalsIgnoreCase("AutoPlatform") && this.blazeRodSlot >= 0) {
         int platformSwitchDelay2 = this.platformSwitchDelay;
         if (this.tickCounter.hasReached(platformSwitchDelay2)) {
            if (this.platformClickTimer.reached(1000L)) {
               event.addRightClicks((byte)1);
               this.platformClickTimer.reset();
            }
         }
      }
   }

   @Override
   public void onPostSilentSlot() {
      this.tickCounter.increment();
      this.blazeRodSlot = -1;
      if (this.mode.getSelectedValue().equalsIgnoreCase("AutoPlatform") && this.shouldBugUp()) {
         for (int i = 0; i < 9; i++) {
            ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(i + 36).getStack();
            if (stack != null && stack.getItem().equals(Items.blaze_rod)) {
               this.blazeRodSlot = i;
               this.platformReady = false;
               break;
            }
         }

         if (mc.thePlayer.inventory.currentItem != this.blazeRodSlot) {
            this.platformSwitchDelay = DefaultRandomGenerator.instance.nextInt((byte)1, (byte)3);
            this.tickCounter.reset();
         }

         if (this.blazeRodSlot >= 0) {
            SilentSlotManager.requestSlot(this, (byte)-3, this.blazeRodSlot);
         }
      }
   }

   private boolean isBlockBelow() {
      if (mc.thePlayer.posY < 0.0) {
         return false;
      } else {
         for (int i = 0; i < (int)mc.thePlayer.posY + 2; i += 2) {
            AxisAlignedBB box = mc.thePlayer.getEntityBoundingBox().offset(0.0, (double)(-i), 0.0);
            if (!mc.theWorld.getCollidingBoundingBoxesCapped(mc.thePlayer, box).isEmpty()) {
               return true;
            }
         }

         return false;
      }
   }

   private boolean shouldBugUp() {
      if (mc.thePlayer == null) {
         return false;
      } else {
         if (!ClientManagers.moduleManager.longJump.isToggled() && !ClientManagers.moduleManager.fly.isToggled()) {
            if (!(mc.thePlayer.fallDistance < DefaultRandomGenerator.instance.nextFloat(this.backFlagDist.getMinValue(), this.backFlagDist.getMaxValue())) || this.buggingUp) {
               if (this.buggingUp && this.mode.getSelectedValue().equals("Collision")) {
                  return true;
               }

               return this.predictWillFallIntoVoid();
            }
         }

         return false;
      }
   }

   private boolean predictWillFallIntoVoid() {
      EntityPlayerSP thePlayer = mc.thePlayer;
      double posX = thePlayer.posX;
      double posY = thePlayer.posY;
      double posZ = thePlayer.posZ;
      double motionX = thePlayer.motionX;
      double motionY = thePlayer.motionY;
      double motionZ = thePlayer.motionZ;
      boolean jumping = thePlayer.jumping;
      int jumpTicks = thePlayer.getJumpTicks();
      boolean onGround = thePlayer.onGround;
      float moveStrafe = thePlayer.movementInput.moveStrafe;
      float moveForward = thePlayer.movementInput.moveForward;
      AxisAlignedBB entityBoundingBox = thePlayer.getEntityBoundingBox();
      AxisAlignedBB box = new AxisAlignedBB(entityBoundingBox.minX, entityBoundingBox.minY, entityBoundingBox.minZ, entityBoundingBox.maxX, entityBoundingBox.maxY, entityBoundingBox.maxZ);
      boolean isCollidedVertically = thePlayer.isCollidedVertically;
      boolean isSprinting = thePlayer.isSprinting();

      for (int i = 0; i <= 200; i++) {
         moveStrafe = thePlayer.movementInput.moveStrafe;
         moveForward = thePlayer.movementInput.moveForward;
         EventSlowDown event = SlowDownListener.onSlowDown(0.2F, 0.2F);
         if (thePlayer.isUsingItem() && !thePlayer.isRiding()) {
            moveStrafe *= event.getStrafeMultiplier();
            moveForward *= event.getForwardMultiplier();
         }

         if (jumpTicks > 0) {
            jumpTicks--;
         }

         double motionZeroThreshold = ViaAugustus.getMotionZeroThreshold();
         if (Math.abs(motionX) < motionZeroThreshold) {
            motionX = 0.0;
         }

         if (Math.abs(motionY) < motionZeroThreshold) {
            motionY = 0.0;
         }

         if (Math.abs(motionZ) < motionZeroThreshold) {
            motionZ = 0.0;
         }

         if (jumping) {
            if (onGround && jumpTicks == 0) {
               motionY = (double)thePlayer.getJumpUpwardsMotion();
               if (thePlayer.isPotionActive(Potion.jump)) {
                  motionY += (double)((float)(thePlayer.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
               }

               if (isSprinting) {
                  float f = thePlayer.rotationYaw * (float) (Math.PI / 180.0);
                  motionX -= (double)(MathHelper.sin(f) * 0.2F);
                  motionZ += (double)(MathHelper.cos(f) * 0.2F);
               }

               jumpTicks = 10;
            }
         } else {
            jumpTicks = 0;
         }

         moveStrafe *= 0.98F;
         moveForward *= 0.98F;
         float f2 = 0.91F;
         if (onGround) {
            f2 = thePlayer.entityWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(posX), MathHelper.floor_double(posY) - 1, MathHelper.floor_double(posZ)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         float f3 = 0.16277136F / (f2 * f2 * f2);
         float f4;
         if (onGround) {
            f4 = thePlayer.getAIMoveSpeed() * f3;
         } else {
            f4 = thePlayer.jumpMovementFactor;
         }

         EventMove event2 = MoveListener.onMove(thePlayer.rotationYaw, MoveEventType.MOVE_FLYING);
         float f5 = moveStrafe * moveStrafe + moveForward * moveForward;
         if (f5 >= 1.0E-4F) {
            f5 = MathHelper.sqrt_float(f5);
            if (f5 < 1.0F) {
               f5 = 1.0F;
            }

            f5 = f4 / f5;
            moveStrafe *= f5;
            moveForward *= f5;
            float f6 = MathHelper.sin(event2.getYaw() * (float) Math.PI / 180.0F);
            float f7 = MathHelper.cos(event2.getYaw() * (float) Math.PI / 180.0F);
            motionX += (double)(moveStrafe * f7 - moveForward * f6);
            motionZ += (double)(moveForward * f7 + moveStrafe * f6);
         }

         f2 = 0.91F;
         if (onGround) {
            f2 = mc.theWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(posX), MathHelper.floor_double(posY) - 1, MathHelper.floor_double(posZ)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         double d = motionX;
         double d2 = motionY;
         double d3 = motionZ;
         double d4 = motionX;
         double d5 = motionZ;
         boolean isSneaking = thePlayer.isSneaking();
         if (thePlayer.onGround && isSneaking) {
            double d6;
            for (d6 = 0.05; d != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(thePlayer, box.offset(d, -1.0, 0.0)).isEmpty(); d4 = d) {
               if (d < d6 && d >= -d6) {
                  d = 0.0;
               } else if (d > 0.0) {
                  d -= d6;
               } else {
                  d += d6;
               }
            }

            for (; d3 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(thePlayer, box.offset(0.0, -1.0, d3)).isEmpty(); d5 = d3) {
               if (d3 < d6 && d3 >= -d6) {
                  d3 = 0.0;
               } else if (d3 > 0.0) {
                  d3 -= d6;
               } else {
                  d3 += d6;
               }
            }

            for (; d != 0.0 && d3 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(thePlayer, box.offset(d, -1.0, d3)).isEmpty(); d5 = d3) {
               if (d < d6 && d >= -d6) {
                  d = 0.0;
               } else if (d > 0.0) {
                  d -= d6;
               } else {
                  d += d6;
               }

               d4 = d;
               if (d3 < d6 && d3 >= -d6) {
                  d3 = 0.0;
               } else if (d3 > 0.0) {
                  d3 -= d6;
               } else {
                  d3 += d6;
               }
            }
         }

         List list = mc.theWorld.getCollidingBoundingBoxesCapped(thePlayer, box.addCoord(d, motionY, d3));
         AxisAlignedBB box2 = box;

         for (AxisAlignedBB box3 : (Iterable<AxisAlignedBB>)(list)) {
            d2 = box3.calculateYOffset(box, d2);
         }

         box = box.offset(0.0, d2, 0.0);
         boolean flag2 = onGround || motionY != d2 && motionY < 0.0;

         for (AxisAlignedBB box4 : (Iterable<AxisAlignedBB>)(list)) {
            d = box4.calculateXOffset(box, d);
         }

         box = box.offset(d, 0.0, 0.0);

         for (AxisAlignedBB box5 : (Iterable<AxisAlignedBB>)(list)) {
            d3 = box5.calculateZOffset(box, d3);
         }

         box = box.offset(0.0, 0.0, d3);
         EventStep event3 = StepListener.onStep(thePlayer.stepHeight, StateTypes.PRE);
         if (event3.getStepHeight() > 0.0F && flag2 && (d4 != d || d5 != d3)) {
            double d7 = d;
            double d8 = d2;
            double d9 = d3;
            AxisAlignedBB box6 = box;
            d2 = (double)event3.getStepHeight();
            List list2 = mc.theWorld.getCollidingBoundingBoxesCapped(thePlayer, box2.addCoord(d4, d2, d5));
            AxisAlignedBB box7 = box2.addCoord(d4, 0.0, d5);
            double d10 = d2;

            for (AxisAlignedBB box8 : (Iterable<AxisAlignedBB>)(list2)) {
               d10 = box8.calculateYOffset(box7, d10);
            }

            AxisAlignedBB box9 = box2.offset(0.0, d10, 0.0);
            double d11 = d4;

            for (AxisAlignedBB box10 : (Iterable<AxisAlignedBB>)(list2)) {
               d11 = box10.calculateXOffset(box9, d11);
            }

            box9 = box9.offset(d11, 0.0, 0.0);
            double d12 = d5;

            for (AxisAlignedBB box11 : (Iterable<AxisAlignedBB>)(list2)) {
               d12 = box11.calculateZOffset(box9, d12);
            }

            box9 = box9.offset(0.0, 0.0, d12);
            AxisAlignedBB box12 = box2;
            double d13 = d2;

            for (AxisAlignedBB box13 : (Iterable<AxisAlignedBB>)(list2)) {
               d13 = box13.calculateYOffset(box12, d13);
            }

            box12 = box12.offset(0.0, d13, 0.0);
            double d14 = d4;

            for (AxisAlignedBB box14 : (Iterable<AxisAlignedBB>)(list2)) {
               d14 = box14.calculateXOffset(box12, d14);
            }

            box12 = box12.offset(d14, 0.0, 0.0);
            double d15 = d5;

            for (AxisAlignedBB box15 : (Iterable<AxisAlignedBB>)(list2)) {
               d15 = box15.calculateZOffset(box12, d15);
            }

            box12 = box12.offset(0.0, 0.0, d15);
            if ((d11 * d11 + d12 * d12) > (d14 * d14 + d15 * d15)) {
               d = d11;
               d3 = d12;
               d2 = -d10;
               box = box9;
            } else {
               d = d14;
               d3 = d15;
               d2 = -d13;
               box = box12;
            }

            for (AxisAlignedBB box16 : (Iterable<AxisAlignedBB>)(list2)) {
               d2 = box16.calculateYOffset(box, d2);
            }

            box = box.offset(0.0, d2, 0.0);
            if (d7 * d7 + d9 * d9 >= d * d + d3 * d3) {
               d = d7;
               d2 = d8;
               d3 = d9;
               box = box6;
            }
         }

         posX = (box.minX + box.maxX) / 2.0;
         posY = box.minY;
         posZ = (box.minZ + box.maxZ) / 2.0;
         isCollidedVertically = motionY != d2;
         onGround = isCollidedVertically && motionY < 0.0;
         int n = MathHelper.floor_double(posX);
         int n2 = MathHelper.floor_double(posY - 0.2F);
         int n3 = MathHelper.floor_double(posZ);
         BlockPos pos = new BlockPos(n, n2, n3);
         Block block = mc.theWorld.getBlockState(pos).getBlock();
         if (block.getMaterial() == Material.air) {
            Block block2 = mc.theWorld.getBlockState(pos.down()).getBlock();
            if (block2 instanceof BlockFence || block2 instanceof BlockWall || block2 instanceof BlockFenceGate) {
               block = block2;
               pos = pos.down();
            }
         }

         if (d4 != d) {
            motionX = 0.0;
         }

         if (d5 != d3) {
            motionZ = 0.0;
         }

         if (motionY != d2) {
            if (block instanceof BlockSlime) {
               if (thePlayer.isSneaking()) {
                  motionY = 0.0;
               } else if (motionY < 0.0) {
                  motionY = -motionY;
               }
            } else {
               motionY = 0.0;
            }
         }

         if (thePlayer.entityWorld.isRemote
            && (
               !thePlayer.entityWorld.isBlockLoaded(new BlockPos((int)posX, 0, (int)posZ))
                  || !thePlayer.entityWorld.getChunkFromBlockCoords(new BlockPos((int)posX, 0, (int)posZ)).isLoaded()
            )) {
            if (posY > 0.0) {
               motionY = -0.1;
            } else {
               motionY = 0.0;
            }
         } else {
            motionY -= 0.08;
         }

         motionY *= 0.98F;
         motionX *= (double)f2;
         motionZ *= (double)f2;
         if (onGround) {
            return false;
         }

         if (Math.abs(mc.thePlayer.posY - posY) > this.maxDistance.getValue() && !this.bugUpOnlyIfFallingIntoVoid.getValue() || posY < 0.0) {
            break;
         }
      }

      return true;
   }

   private boolean isBlinkPacketsVisible() {
      return this.mode.getSelectedValue().equals("BlinkOutgoing") || this.mode.getSelectedValue().equals("BlinkBoth");
   }

   private boolean isBackFlagDistVisible() {
      return !this.mode.getSelectedValue().equals("BlinkOutgoing") && !this.mode.getSelectedValue().equals("BlinkBoth");
   }

   private boolean isOnlyVoidSettingVisible() {
      return !this.mode.getSelectedValue().equals("NCP");
   }

   private boolean isMaxDistanceSettingVisible() {
      return !this.mode.getSelectedValue().equals("NCP");
   }

}

package net.augustus.modules.player;

import net.minecraft.util.MovingObjectPosition;
import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.BlockBoundingBoxListener;
import net.augustus.events.EventPacketDelay;
import net.augustus.events.EventBlockBoundingBox;
import net.augustus.events.listeners.PacketDelayListener;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.events.EventMoveInput;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class Teleport extends Module implements UpdateListener, PacketListener, PacketDelayListener, BlockBoundingBoxListener, MoveInputListener {
   public StringValue mode;
   public BooleanValue autoDisable;
   private final TimeHelper oldVulcanTimer;
   private final TimeHelper vulcanAirWalkTimer;
   private double[] targetPos;
   private boolean teleportPending;
   private boolean teleportCompleted;
   private boolean delayingPackets;
   private boolean vulcanPostJumpPhase;
   private int airFloorY;
   private boolean vulcanNewActive;
   private int vulcanNewPacketCount;
   private boolean vulcanNewTeleported;

   public Teleport() {
       super("Teleport", "Raumreise", new Color(141, 232, 5), Categorys.PLAYER);
      this.mode = new StringValue(this, "Mode", "Vulcan", new String[]{"OldKarhu", "OldVulcan", "Vulcan", "VulcanNew", "Vanilla"});
      this.autoDisable = new BooleanValue(this, "AutoDisable", true);
      this.oldVulcanTimer = new TimeHelper();
      this.vulcanAirWalkTimer = new TimeHelper();
      this.targetPos = new double[3];
      this.teleportCompleted = false;
      this.vulcanPostJumpPhase = false;
      this.airFloorY = 0;
      this.vulcanNewActive = false;
      this.vulcanNewPacketCount = 0;
      this.vulcanNewTeleported = false;
   }

   public void onEnable() {
      this.targetPos = new double[]{1.0 / 0.0, 1.0 / 0.0, 1.0 / 0.0};
      this.teleportPending = false;
      this.teleportCompleted = false;
      this.delayingPackets = false;
      this.vulcanNewActive = false;
      this.vulcanNewPacketCount = 0;
      this.vulcanNewTeleported = false;
      this.vulcanPostJumpPhase = false;
   }

   public void onDisable() {
      this.targetPos = new double[3];
      this.teleportPending = false;
      this.delayingPackets = false;
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(43);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(25);
      this.toggleEventSubscription(3);
      this.toggleEventSubscription(21);
   }

   public void onPreUpdate() {
      this.setSuffix(this.mode.getSelectedValue());
      if (mc.gameSettings.keyBindAttack.isKeyDown() && mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && this.targetPos[0] == 1.0 / 0.0) {
         BlockPos blockPos = mc.objectMouseOver.getBlockPos();
         Block block = mc.theWorld.getBlockState(blockPos).getBlock();
         this.targetPos = new double[]{
            (double)mc.objectMouseOver.getBlockPos().getX() + 0.5,
            (double)mc.objectMouseOver.getBlockPos().getY() + block.getBlockBoundsMaxY(),
            (double)mc.objectMouseOver.getBlockPos().getZ() + 0.5
         };
         this.teleportPending = true;
         this.oldVulcanTimer.reset();
      }

      if (this.mode.getSelectedValue().equals("Vulcan") && mc.thePlayer.onGround && this.vulcanPostJumpPhase) {
         mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY + 2.0, mc.thePlayer.posZ);
         this.vulcanPostJumpPhase = false;
         this.vulcanAirWalkTimer.reset();
         this.airFloorY = (int)mc.thePlayer.posY;
      }

      if (this.mode.getSelectedValue().equals("VulcanNew") && mc.thePlayer.onGround && this.vulcanNewTeleported && !this.vulcanPostJumpPhase) {
         if (this.vulcanAirWalkTimer.reached(500L)) {
            this.vulcanPostJumpPhase = true;
            mc.thePlayer.jump();
            this.airFloorY = (int)mc.thePlayer.posY + 1;
            this.vulcanNewPacketCount = 0;
         }
      }

      if (this.teleportPending) {
         switch (this.mode.getSelectedValue()) {
            case "OldKarhu":
               if (!mc.thePlayer.onGround) {
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.targetPos[0], this.targetPos[1] + 0.3, this.targetPos[2], true));
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.targetPos[0], this.targetPos[1] + 0.3, this.targetPos[2], false));
                  this.teleportPending = false;
                  this.teleportCompleted = true;
                  this.targetPos = new double[]{1.0 / 0.0, 1.0 / 0.0, 1.0 / 0.0};
               }

               if (mc.thePlayer.onGround) {
                  mc.thePlayer.jump();
               }
               break;
            case "Vanilla":
               mc.thePlayer.setPosition(this.targetPos[0], this.targetPos[1] + 0.3, this.targetPos[2]);
               mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.targetPos[0], this.targetPos[1] + 0.3, this.targetPos[2], false));
               this.teleportPending = false;
               this.teleportCompleted = true;
               break;
            case "Vulcan":
               this.delayingPackets = true;
               mc.thePlayer.setPosition(this.targetPos[0], this.targetPos[1] + 1.0, this.targetPos[2]);
               this.teleportPending = false;
               this.vulcanPostJumpPhase = true;
               break;
            case "VulcanNew":
               if (mc.thePlayer.onGround) {
                  mc.thePlayer.jump();
                  this.teleportPending = false;
                  this.vulcanNewActive = true;
                  this.airFloorY = (int)mc.thePlayer.posY + 1;
               }
         }
      }

      if (this.autoDisable.getValue() && this.teleportCompleted) {
         this.toggle();
      }

      if (!this.autoDisable.getValue() && this.teleportCompleted) {
         this.targetPos = new double[]{1.0 / 0.0, 1.0 / 0.0, 1.0 / 0.0};
      }
   }

   public void onPacketReceive(EventPacket event) {
      Packet packet = event.getPacket();
      switch (this.mode.getSelectedValue()) {
         case "OldVulcan":
            if (packet instanceof S08PacketPlayerPosLook && this.teleportPending) {
               S08PacketPlayerPosLook packet2 = (S08PacketPlayerPosLook)packet;
               if (packet2.getX() == this.targetPos[0] && packet2.getY() == this.targetPos[1] && packet2.getZ() == this.targetPos[2]) {
                  this.teleportPending = false;
                  this.teleportCompleted = true;
                  this.targetPos = new double[]{1.0 / 0.0, 1.0 / 0.0, 1.0 / 0.0};
               }
            }
            break;
         case "VulcanNew":
            if (this.vulcanNewActive && packet instanceof S08PacketPlayerPosLook) {
               event.cancel();
               if (this.vulcanNewPacketCount > 1 && !this.vulcanNewTeleported) {
                  mc.thePlayer.setPosition(this.targetPos[0], this.targetPos[1] + 0.3, this.targetPos[2]);
                  this.vulcanNewTeleported = true;
                  this.airFloorY = (int)mc.thePlayer.posY;
                  this.vulcanNewPacketCount = 0;
                  this.vulcanAirWalkTimer.reset();
               }

               this.vulcanNewPacketCount++;
            }
      }
   }

   public void onPacketSend(EventPacket event) {
      Packet packet = event.getPacket();
      if (this.mode.getSelectedValue().equals("OldVulcan") && packet instanceof C03PacketPlayer && this.teleportPending) {
         C03PacketPlayer packet2 = (C03PacketPlayer)packet;
         packet2.setPositionX(this.targetPos[0]);
         packet2.setPositionY(this.targetPos[1]);
         packet2.setPositionZ(this.targetPos[2]);
         mc.thePlayer.setPosition(this.targetPos[0], this.targetPos[1], this.targetPos[2]);
         mc.thePlayer.onGround = true;
         if (this.oldVulcanTimer.reached(1000L)) {
            this.teleportPending = false;
            this.teleportCompleted = true;
            this.targetPos = new double[]{1.0 / 0.0, 1.0 / 0.0, 1.0 / 0.0};
         }
      }
   }

   public void onOutgoingPacketDelay(EventPacketDelay event) {
      if (this.mode.getSelectedValue().equals("Vulcan") && this.delayingPackets) {
         event.setDelayed(true);
      }
   }

   public void onBlockBoundingBox(EventBlockBoundingBox event) {
      switch (this.mode.getSelectedValue()) {
         case "Vulcan":
            if (!this.vulcanAirWalkTimer.reached(1500L) && event.getBlock() instanceof BlockAir && event.getBlockPos().getY() < this.airFloorY) {
               BlockPos blockPos = event.getBlockPos();
               AxisAlignedBB box = new AxisAlignedBB(
                  (double)blockPos.getX(),
                  (double)blockPos.getY(),
                  (double)blockPos.getZ(),
                  (double)(blockPos.getX() + 1),
                  (double)(blockPos.getY() + 1),
                  (double)(blockPos.getZ() + 1)
               );
               event.setBoundingBox(box);
            }
            break;
         case "VulcanNew":
            if ((this.vulcanNewActive && !this.vulcanNewTeleported && this.vulcanNewPacketCount < 1 || this.vulcanPostJumpPhase) && event.getBlock() instanceof BlockAir && event.getBlockPos().getY() < this.airFloorY) {
               BlockPos blockPos2 = event.getBlockPos();
               AxisAlignedBB box2 = new AxisAlignedBB(
                  (double)blockPos2.getX(),
                  (double)blockPos2.getY(),
                  (double)blockPos2.getZ(),
                  (double)(blockPos2.getX() + 1),
                  (double)(blockPos2.getY() + 1),
                  (double)(blockPos2.getZ() + 1)
               );
               event.setBoundingBox(box2);
            }
      }
   }

   public void onPreMoveInput(EventMoveInput event) {
      if (this.mode.getSelectedValue().equals("Vulcan") && !this.vulcanAirWalkTimer.reached(1500L)) {
event.setMoveForward(1.0F);
} else if (this.delayingPackets && !this.vulcanPostJumpPhase) {
            this.delayingPackets = false;
            this.teleportCompleted = true;
            this.targetPos = new double[]{1.0 / 0.0, 1.0 / 0.0, 1.0 / 0.0};
         }

      if (this.mode.getSelectedValue().equals("VulcanNew") && this.vulcanNewActive) {
         if (!this.vulcanNewTeleported || this.vulcanPostJumpPhase) {
            event.setMoveForward(1.0F);
         }

         if (this.vulcanNewActive && !this.vulcanNewTeleported && this.vulcanNewPacketCount >= 1) {
            event.setMoveForward(0.0F);
            event.setMoveStrafe(0.0F);
         }

         if (this.vulcanNewPacketCount > 40 && this.vulcanNewTeleported && this.vulcanPostJumpPhase) {
            this.teleportCompleted = true;
            this.vulcanNewActive = false;
            this.vulcanNewTeleported = false;
            this.vulcanNewPacketCount = 0;
            this.vulcanPostJumpPhase = false;
            this.targetPos = new double[]{1.0 / 0.0, 1.0 / 0.0, 1.0 / 0.0};
         }
      }
   }

}

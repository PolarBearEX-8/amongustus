package net.augustus.modules.modes.fly;

import net.augustus.ClientManagers;
import net.augustus.events.EventBlockBoundingBox;
import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.modules.movement.Fly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockWeb;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class GroundCollisionFlyMode extends FlyMode {
   public double collisionFloorY = 0.0;

   public GroundCollisionFlyMode(String str, Fly fly2) {
      super(str, fly2);
   }

   public void onEnable() {
      this.collisionFloorY = mc.thePlayer.posY - 0.5;
   }

   public void onDisable() {
   }

   public void onPreUpdate() {
   }

   public void onMotion(EventMotion event) {
   }

   public void onMoveEntity(EventMoveEntity event) {
   }

   public void onBlockBoundingBox(EventBlockBoundingBox event) {
      Block block = event.getBlock();
      if ((block instanceof BlockAir || block instanceof BlockLiquid && this.fly.onWater.getValue() || block instanceof BlockWeb && this.fly.onWeb.getValue())
         && (double)event.getBlockPos().getY() < this.collisionFloorY) {
         BlockPos blockPos = event.getBlockPos();
         AxisAlignedBB box = new AxisAlignedBB(
            (double)blockPos.getX(),
            (double)blockPos.getY(),
            (double)blockPos.getZ(),
            (double)(blockPos.getX() + 1),
            (double)blockPos.getY() + ClientManagers.moduleManager.fly.collisionY.getValue(),
            (double)(blockPos.getZ() + 1)
         );
         event.setBoundingBox(box);
      }
   }
}

package net.augustus.modules.modes.fly;

import net.augustus.events.EventBlockBoundingBox;
import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.modules.movement.Fly;
import net.minecraft.block.BlockAir;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class UniversoCraftFlyMode extends FlyMode {
   private double collisionFloorY = 0.0;

   public UniversoCraftFlyMode(String str, Fly fly2) {
      super(str, fly2);
   }

   public void onEnable() {
      this.collisionFloorY = mc.thePlayer.posY - 0.9;
   }

   public void onDisable() {
   }

   public void onPreUpdate() {
   }

   public void onMotion(EventMotion event) {
      BlockPos pos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 0.9, mc.thePlayer.posZ);
      if (!mc.theWorld.getBlockState(pos).getBlock().isFullBlock()) {
         event.setOnGround(false);
      }
   }

   public void onMoveEntity(EventMoveEntity event) {
   }

   public void onBlockBoundingBox(EventBlockBoundingBox event) {
      if (event.getBlock() instanceof BlockAir && (double)event.getBlockPos().getY() < this.collisionFloorY) {
         BlockPos blockPos = event.getBlockPos();
         event.setBoundingBox(new AxisAlignedBB(
            (double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), (double)(blockPos.getX() + 1), (double)(blockPos.getY() + 1), (double)(blockPos.getZ() + 1)
         ));
      }
   }
}

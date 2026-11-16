package cc.baritone.utils;

import client.util.MovingObjectType;
import java.util.Optional;
import net.minecraft.block.ExtraBlockAdapter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public interface StaticListener {
   default BetterBlockPosXSYSZSBlockPos getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS() {
      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos = new BetterBlockPosXSYSZSBlockPos(
         this.getEntityPlayerSP().posX, this.getEntityPlayerSP().posY + 0.1251, this.getEntityPlayerSP().posZ
      );

      try {
         if (this.getWorld().getBlockState(betterBlockPosXSYSZSBlockPos).getBlock() instanceof ExtraBlockAdapter) {
            return betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos3();
         }
      } catch (NullPointerException nullPointerException) {
      }

      return betterBlockPosXSYSZSBlockPos;
   }

   default Vec3 getVec2() {
      return new Vec3(this.getEntityPlayerSP().posX, this.getEntityPlayerSP().posY, this.getEntityPlayerSP().posZ);
   }

   EntityPlayerSP getEntityPlayerSP();

   default Vec3 getVecAsVec3() {
      return new Vec3(
         this.getEntityPlayerSP().posX, this.getEntityPlayerSP().posY + (double)this.getEntityPlayerSP().getHeight2(), this.getEntityPlayerSP().posZ
      );
   }

   MovingObjectPosition getMovingObjectPosition();

   NativeListener getNativeListener();

   Minecraft getMc();

   DefaultListener getDefaultListener();

   default Vec3 getVec3() {
      return new Vec3(this.getEntityPlayerSP().motionX, this.getEntityPlayerSP().motionY, this.getEntityPlayerSP().motionZ);
   }

   World getWorld();

   BetterBlockPosXSYSZSBlockPos getBetterBlockPosXSYSZSBlockPos();

   default Optional getOptional() {
      MovingObjectPosition movingObjectPosition = this.getMovingObjectPosition();
      return movingObjectPosition != null && movingObjectPosition.typeOfHit == MovingObjectType.BLOCK ? Optional.of(movingObjectPosition.getBlockPos()) : Optional.empty();
   }

   default boolean matchesBlockPos(BlockPos blockPos) {
      return this.getOptional().equals(Optional.of(blockPos));
   }

   default DefaultCustom getDefaultCustom() {
      return new DefaultCustom(this.getEntityPlayerSP().rotationYaw, this.getEntityPlayerSP().rotationPitch);
   }

   static double computeValue(boolean flag) {
      return flag ? 1.54 : 1.62;
   }
}

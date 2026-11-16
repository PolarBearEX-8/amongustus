package cc.baritone.utils;

import client.util.IBlockState;
import net.minecraft.block.BlockFire;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public final class Vec3Helper {
   public static Vec3 resolveVec3(BlockPos blockPos) {
      return new Vec3((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5);
   }

   public static Vec3 resolveVec3(World world, BlockPos blockPos) {
      IBlockState blockState = world.getBlockState(blockPos);
      AxisAlignedBB boundingBox = blockState.getBlock().processAction(world, blockPos);
      double offset = (boundingBox.minX + boundingBox.maxX) / 2.0;
      double offset2 = (boundingBox.minY + boundingBox.maxY) / 2.0;
      double offset3 = (boundingBox.minZ + boundingBox.maxZ) / 2.0;
      if (blockState.getBlock() instanceof BlockFire) {
         offset2 = boundingBox.minY;
      }

      return new Vec3(offset, offset2, offset3);
   }

   private Vec3Helper() {
   }

   public static double computeValue(Entity entity, BlockPos blockPos) {
      return computeValue(blockPos, entity.posX, (double)blockPos.getY() + 0.5, entity.posZ);
   }

   public static double computeValueByEntity(Entity entity, BlockPos blockPos) {
      return computeValue(blockPos, entity.posX, entity.posY, entity.posZ);
   }

   public static double computeValue(BlockPos blockPos, double posX, double posY, double posZ) {
      double offset = (double)blockPos.getX() + 0.5 - posX;
      double offset2 = (double)blockPos.getY() + 0.5 - posY;
      double offset3 = (double)blockPos.getZ() + 0.5 - posZ;
      return Math.sqrt(offset * offset + offset2 * offset2 + offset3 * offset3);
   }
}

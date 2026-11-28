package baritone.api.utils;

import net.minecraft.block.BlockFire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public final class VecUtils {
   public static Vec3 calculateBlockCenter(World world, BlockPos pos) {
      IBlockState blockState = world.getBlockState(pos);
      AxisAlignedBB selectedBoundingBox = blockState.getBlock().getSelectedBoundingBox(world, pos);
      double d = (selectedBoundingBox.minX + selectedBoundingBox.maxX) / 2.0;
      double d2 = (selectedBoundingBox.minY + selectedBoundingBox.maxY) / 2.0;
      double d3 = (selectedBoundingBox.minZ + selectedBoundingBox.maxZ) / 2.0;
      if (blockState.getBlock() instanceof BlockFire) {
         d2 = selectedBoundingBox.minY;
      }

      return new Vec3(d, d2, d3);
   }

   private VecUtils() {
   }

   public static double entityFlatDistanceToCenter(Entity entity, BlockPos pos) {
      return distanceToCenter(pos, entity.posX, (double)pos.getY() + 0.5, entity.posZ);
   }

   public static double distanceToCenter(BlockPos pos, double d, double d2, double d3) {
      double d4 = (double)pos.getX() + 0.5 - d;
      double d5 = (double)pos.getY() + 0.5 - d2;
      double d6 = (double)pos.getZ() + 0.5 - d3;
      return Math.sqrt(d4 * d4 + d5 * d5 + d6 * d6);
   }

   public static Vec3 getBlockPosCenter(BlockPos pos) {
      return new Vec3((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5);
   }

   public static double entityDistanceToCenter(Entity entity, BlockPos pos) {
      return distanceToCenter(pos, entity.posX, entity.posY, entity.posZ);
   }
}

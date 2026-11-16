package cc.baritone.utils;

import client.util.MovingObjectType;
import java.util.Optional;
import net.minecraft.block.BlockFire;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public final class BreadthFirstRecorder {
   public static final double value = Math.PI / 180.0;
   public static final float factor = (float) (Math.PI / 180.0);
   public static final double value2 = 180.0 / Math.PI;
   public static final float factor2 = (float) (180.0 / Math.PI);
   public static final Vec3[] vec3Array = new Vec3[]{
      new Vec3(0.5, 0.0, 0.5), new Vec3(0.5, 1.0, 0.5), new Vec3(0.5, 0.5, 0.0), new Vec3(0.5, 0.5, 1.0), new Vec3(0.0, 0.5, 0.5), new Vec3(1.0, 0.5, 0.5)
   };

   public static DefaultCustom resolveDefaultCustom(Vec3 vec, Vec3 vec2, DefaultCustom defaultCustom) {
      return resolveDefaultCustom(defaultCustom, resolveDefaultCustom(vec, vec2));
   }

   public static Optional resolveOptional(StaticListener staticListener, BlockPos blockPos, double offset, boolean flag) {
      return resolveOptional(staticListener, blockPos, Vec3Helper.resolveVec3(staticListener.getWorld(), blockPos), offset, flag);
   }

   @Deprecated
   public static Vec3 resolveVec3(DefaultCustom defaultCustom) {
      return resolveVecByDefaultCustom(defaultCustom);
   }

   public static Vec3 resolveVecByDefaultCustom(DefaultCustom defaultCustom) {
      float factor3 = MathHelper.cos(-defaultCustom.getFactor() * (float) (Math.PI / 180.0) - (float) Math.PI);
      float factor4 = MathHelper.abs(-defaultCustom.getFactor() * (float) (Math.PI / 180.0) - (float) Math.PI);
      float factor5 = -MathHelper.cos(-defaultCustom.getFactor2() * (float) (Math.PI / 180.0));
      float factor6 = MathHelper.abs(-defaultCustom.getFactor2() * (float) (Math.PI / 180.0));
      return new Vec3((double)(factor4 * factor5), (double)factor6, (double)(factor3 * factor5));
   }

   @Deprecated
   public static Optional resolveOptional(EntityPlayerSP player, BlockPos blockPos, double offset) {
      return resolveOptional(player, blockPos, offset, false);
   }

   private static DefaultCustom resolveDefaultCustom(Vec3 vec, Vec3 vec2) {
      double[] offsetArray = new double[]{vec.xCoord - vec2.xCoord, vec.yCoord - vec2.yCoord, vec.zCoord - vec2.zCoord};
      double doubleItem = MathHelper.getDouble6(offsetArray[0], -offsetArray[2]);
      double offset = Math.sqrt(offsetArray[0] * offsetArray[0] + offsetArray[2] * offsetArray[2]);
      double doubleItem2 = MathHelper.getDouble6(offsetArray[1], offset);
      return new DefaultCustom((float)(doubleItem * (180.0 / Math.PI)), (float)(doubleItem2 * (180.0 / Math.PI)));
   }

   public static DefaultCustom resolveDefaultCustom(BlockPos blockPos, BlockPos blockPos2) {
      return resolveDefaultCustom(new Vec3(blockPos), new Vec3(blockPos2));
   }

   public static Optional resolveOptional(StaticListener staticListener, BlockPos blockPos, boolean flag) {
      return resolveOptionalByStaticListener(staticListener, blockPos, staticListener.getDefaultListener().getValue(), flag);
   }

   public static Optional resolveOptional(StaticListener staticListener, BlockPos blockPos, Vec3 vec, double offset, boolean flag) {
      DefaultCustom defaultCustom = resolveDefaultCustom(
         flag ? CoreCustom.resolveVec3(staticListener.getEntityPlayerSP()) : staticListener.getEntityPlayerSP().getVec35(1.0F), vec, staticListener.getDefaultCustom()
      );
      DefaultCustom defaultCustom2 = SettingsTxtManager.getExtraListener()
         .resolveActiveListenerByEntityPlayerSP(staticListener.getEntityPlayerSP())
         .getGlobalListener()
         .getStandardListener()
         .resolveDefaultCustom(defaultCustom);
      MovingObjectPosition hitResult = CoreCustom.resolveMovingObjectPosition(staticListener.getEntityPlayerSP(), defaultCustom2, offset, flag);
      if (hitResult != null && hitResult.typeOfHit == MovingObjectType.BLOCK) {
         if (hitResult.getBlockPos().equals(blockPos)) {
            return Optional.of(defaultCustom);
         }

         if (staticListener.getWorld().getBlockState(blockPos).getBlock() instanceof BlockFire && hitResult.getBlockPos().equals(blockPos.down())) {
            return Optional.of(defaultCustom);
         }
      }

      return Optional.empty();
   }

   @Deprecated
   public static Optional resolveOptional(EntityPlayerSP player, BlockPos blockPos, double offset, boolean flag) {
      return resolveOptionalByStaticListener(
         SettingsTxtManager.getExtraListener().resolveActiveListenerByEntityPlayerSP(player).getStaticListener(), blockPos, offset, flag
      );
   }

   public static Optional resolveOptionalByStaticListener(StaticListener staticListener, BlockPos blockPos, double offset, boolean flag) {
      Optional optional = resolveOptional(staticListener, blockPos, offset, flag);
      if (optional.isPresent()) {
         return optional;
      } else {
         AxisAlignedBB boundingBox = staticListener.getWorld().getBlockState(blockPos).getBlock().processAction(staticListener.getWorld(), blockPos);

         for (Vec3 vec : vec3Array) {
            double offset2 = (boundingBox.minX - (double)blockPos.getX()) * vec.xCoord + (boundingBox.maxX - (double)blockPos.getX()) * (1.0 - vec.xCoord);
            double offset3 = (boundingBox.minY - (double)blockPos.getY()) * vec.yCoord + (boundingBox.maxY - (double)blockPos.getY()) * (1.0 - vec.yCoord);
            double offset4 = (boundingBox.minZ - (double)blockPos.getZ()) * vec.zCoord + (boundingBox.maxZ - (double)blockPos.getZ()) * (1.0 - vec.zCoord);
            Optional optional2 = resolveOptional(staticListener, blockPos, new Vec3(blockPos).getVec312(offset2, offset3, offset4), offset, flag);
            if (optional2.isPresent()) {
               return optional2;
            }
         }

         return Optional.empty();
      }
   }

   private BreadthFirstRecorder() {
   }

   public static Optional resolveOptional(StaticListener staticListener, BlockPos blockPos) {
      return resolveOptional(staticListener, blockPos, false);
   }

   @Deprecated
   public static Optional resolveOptional(Entity entity, BlockPos blockPos, Vec3 vec, double offset, boolean flag) {
      DefaultCustom defaultCustom = resolveDefaultCustom(
         flag ? CoreCustom.resolveVec3(entity) : entity.getVec35(1.0F), vec, new DefaultCustom(entity.rotationYaw, entity.rotationPitch)
      );
      MovingObjectPosition hitResult = CoreCustom.resolveMovingObjectPosition(entity, defaultCustom, offset, flag);
      if (hitResult != null && hitResult.typeOfHit == MovingObjectType.BLOCK) {
         if (hitResult.getBlockPos().equals(blockPos)) {
            return Optional.of(defaultCustom);
         }

         if (entity.worldObj.getBlockState(blockPos).getBlock() instanceof BlockFire && hitResult.getBlockPos().equals(blockPos.down())) {
            return Optional.of(defaultCustom);
         }
      }

      return Optional.empty();
   }

   public static Optional resolveOptional(StaticListener staticListener, BlockPos blockPos, double offset) {
      return resolveOptionalByStaticListener(staticListener, blockPos, offset, false);
   }

   @Deprecated
   public static Optional resolveOptional(Entity entity, BlockPos blockPos, double offset, boolean flag) {
      return resolveOptional(entity, blockPos, Vec3Helper.resolveVec3(entity.worldObj, blockPos), offset, flag);
   }

   public static DefaultCustom resolveDefaultCustom(DefaultCustom defaultCustom, DefaultCustom defaultCustom2) {
      return defaultCustom.matchesDefaultCustom(defaultCustom2)
         ? new DefaultCustom(defaultCustom.getFactor(), defaultCustom2.getFactor2())
         : defaultCustom2.resolveDefaultCustomByDefaultCustom(defaultCustom).getDefaultCustom().resolveDefaultCustom(defaultCustom);
   }
}

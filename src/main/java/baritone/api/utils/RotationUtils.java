package baritone.api.utils;

import baritone.api.BaritoneAPI;
import baritone.api.IBaritone;
import java.util.Optional;
import net.minecraft.block.BlockFire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public final class RotationUtils {
   public static final double DEG_TO_RAD = Math.PI / 180.0;
   public static final float DEG_TO_RAD_F = (float) (Math.PI / 180.0);
   public static final double RAD_TO_DEG = 180.0 / Math.PI;
   public static final float RAD_TO_DEG_F = (float) (180.0 / Math.PI);
   private static final Vec3[] BLOCK_SIDE_MULTIPLIERS = new Vec3[]{
      new Vec3(0.5, 0.0, 0.5), new Vec3(0.5, 1.0, 0.5), new Vec3(0.5, 0.5, 0.0), new Vec3(0.5, 0.5, 1.0), new Vec3(0.0, 0.5, 0.5), new Vec3(1.0, 0.5, 0.5)
   };

   private static Rotation calcRotationFromVec3d(Vec3 vec, Vec3 vec2) {
      double[] doubles = new double[]{vec.xCoord - vec2.xCoord, vec.yCoord - vec2.yCoord, vec.zCoord - vec2.zCoord};
      double d = MathHelper.atan2(doubles[0], -doubles[2]);
      double d2 = Math.sqrt(doubles[0] * doubles[0] + doubles[2] * doubles[2]);
      double d3 = MathHelper.atan2(doubles[1], d2);
      return new Rotation((float)(d * (180.0 / Math.PI)), (float)(d3 * (180.0 / Math.PI)));
   }

   public static Rotation wrapAnglesToRelative(Rotation rotation, Rotation rotation2) {
      return rotation.yawIsReallyClose(rotation2) ? new Rotation(rotation.getYaw(), rotation2.getPitch()) : rotation2.subtract(rotation).normalize().add(rotation);
   }

   @Deprecated
   public static Optional reachable(EntityPlayerSP player, BlockPos pos, double d) {
      return reachable(player, pos, d, false);
   }

   @Deprecated
   public static Optional reachableCenter2(Entity entity, BlockPos pos, double d, boolean flag) {
      return reachableOffset(entity, pos, VecUtils.calculateBlockCenter(entity.entityWorld, pos), d, flag);
   }

   private RotationUtils() {
   }

   @Deprecated
   public static Optional reachable(EntityPlayerSP player, BlockPos pos, double d, boolean flag) {
      return reachable2(BaritoneAPI.getProvider().getBaritoneForPlayer(player).getPlayerContext(), pos, d, flag);
   }

   public static Vec3 calcLookDirectionFromRotation(Rotation rotation) {
      float f = MathHelper.cos(-rotation.getYaw() * (float) (Math.PI / 180.0) - (float) Math.PI);
      float f2 = MathHelper.sin(-rotation.getYaw() * (float) (Math.PI / 180.0) - (float) Math.PI);
      float f3 = -MathHelper.cos(-rotation.getPitch() * (float) (Math.PI / 180.0));
      float f4 = MathHelper.sin(-rotation.getPitch() * (float) (Math.PI / 180.0));
      return new Vec3((double)(f2 * f3), (double)f4, (double)(f * f3));
   }

   public static Optional reachable3(IPlayerContext playerContext, BlockPos pos, double d) {
      return reachable2(playerContext, pos, d, false);
   }

   @Deprecated
   public static Optional reachableOffset(Entity entity, BlockPos pos, Vec3 vec, double d, boolean flag) {
      Rotation rotation = calcRotationFromVec3d(flag ? RayTraceUtils.inferSneakingEyePosition(entity) : entity.getPositionEyes(1.0F), vec, new Rotation(entity.rotationYaw, entity.rotationPitch));
      MovingObjectPosition hit = RayTraceUtils.rayTraceTowards(entity, rotation, d, flag);
      if (hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
         if (hit.getBlockPos().equals(pos)) {
            return Optional.of(rotation);
         }

         if (entity.entityWorld.getBlockState(pos).getBlock() instanceof BlockFire && hit.getBlockPos().equals(pos.down())) {
            return Optional.of(rotation);
         }
      }

      return Optional.empty();
   }

   @Deprecated
   public static Vec3 calcVec3dFromRotationOld(Rotation rotation) {
      return calcLookDirectionFromRotation(rotation);
   }

   public static Optional reachableCenter(IPlayerContext playerContext, BlockPos pos, double d, boolean flag) {
      return reachableOffset2(playerContext, pos, VecUtils.calculateBlockCenter(playerContext.world(), pos), d, flag);
   }

   public static Optional reachable2(IPlayerContext playerContext, BlockPos pos, double d, boolean flag) {
      Optional optional = reachableCenter(playerContext, pos, d, flag);
      if (optional.isPresent()) {
         return optional;
      } else {
         AxisAlignedBB selectedBoundingBox = playerContext.world().getBlockState(pos).getBlock().getSelectedBoundingBox(playerContext.world(), pos);

         for (Vec3 vec : BLOCK_SIDE_MULTIPLIERS) {
            double d2 = (selectedBoundingBox.minX - (double)pos.getX()) * vec.xCoord + (selectedBoundingBox.maxX - (double)pos.getX()) * (1.0 - vec.xCoord);
            double d3 = (selectedBoundingBox.minY - (double)pos.getY()) * vec.yCoord + (selectedBoundingBox.maxY - (double)pos.getY()) * (1.0 - vec.yCoord);
            double d4 = (selectedBoundingBox.minZ - (double)pos.getZ()) * vec.zCoord + (selectedBoundingBox.maxZ - (double)pos.getZ()) * (1.0 - vec.zCoord);
            optional = reachableOffset2(playerContext, pos, new Vec3(pos).addVector(d2, d3, d4), d, flag);
            if (optional.isPresent()) {
               return optional;
            }
         }

         return Optional.empty();
      }
   }

   public static Optional reachable2(IPlayerContext playerContext, BlockPos pos, boolean flag) {
      return reachable2(playerContext, pos, playerContext.playerController().getBlockReachDistance(), flag);
   }

   public static Rotation calcRotationFromCoords(BlockPos pos, BlockPos pos2) {
      return calcRotationFromVec3d(new Vec3(pos), new Vec3(pos2));
   }

   public static Optional reachable(IPlayerContext playerContext, BlockPos pos) {
      return reachable2(playerContext, pos, false);
   }

   public static Optional reachableOffset2(IPlayerContext playerContext, BlockPos pos, Vec3 vec, double d, boolean flag) {
      Rotation rotation = calcRotationFromVec3d(flag ? RayTraceUtils.inferSneakingEyePosition(playerContext.player()) : playerContext.player().getPositionEyes(1.0F), vec, playerContext.playerRotations());
      Rotation rotation2 = BaritoneAPI.getProvider().getBaritoneForPlayer(playerContext.player()).getLookBehavior().getAimProcessor().peekRotation(rotation);
      MovingObjectPosition hit = RayTraceUtils.rayTraceTowards(playerContext.player(), rotation2, d, flag);
      if (hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
         if (hit.getBlockPos().equals(pos)) {
            return Optional.of(rotation);
         }

         if (playerContext.world().getBlockState(pos).getBlock() instanceof BlockFire && hit.getBlockPos().equals(pos.down())) {
            return Optional.of(rotation);
         }
      }

      return Optional.empty();
   }

   public static Rotation calcRotationFromVec3d(Vec3 vec, Vec3 vec2, Rotation rotation) {
      return wrapAnglesToRelative(rotation, calcRotationFromVec3d(vec, vec2));
   }
}

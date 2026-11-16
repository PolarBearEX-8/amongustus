package client.util;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class AuxiliaryIntBufferEntry {
   public static final IntBuffer intBuffer = VectorMatcher.resolveIntBuffer(16);
   public static final FloatBuffer floatBuffer3 = VectorMatcher.resolveFloatBuffer(16);
   public static final FloatBuffer floatBuffer = VectorMatcher.resolveFloatBuffer(16);
   public static final FloatBuffer floatBuffer2 = VectorMatcher.resolveFloatBuffer(3);
   public static Vec3 vec3 = new Vec3(0.0, 0.0, 0.0);
   public static float factor4;
   public static float factor5;
   public static float factor;
   public static float factor2;
   public static float factor3;

   public static float getFactor5() {
      return factor5;
   }

   public static float getFactor4() {
      return factor4;
   }

   public static Vec3 resolveVecByEntity(Entity entity, double offset) {
      double offset2 = entity.prevPosX + (entity.posX - entity.prevPosX) * offset;
      double offset3 = entity.prevPosY + (entity.posY - entity.prevPosY) * offset;
      double offset4 = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * offset;
      double offset5 = offset2 + vec3.xCoord;
      double offset6 = offset3 + vec3.yCoord;
      double offset7 = offset4 + vec3.zCoord;
      return new Vec3(offset5, offset6, offset7);
   }

   public static Vec3 getVec3() {
      return vec3;
   }

   public static float getFactor() {
      return factor;
   }

   public static Block resolveBlock(World world, Entity entity, float factor6) {
      Vec3 vec = resolveVecByEntity(entity, (double)factor6);
      int count = MathHelper.ceiling_double_int(vec.xCoord);
      int count2 = MathHelper.ceiling_double_int(vec.yCoord);
      int count3 = MathHelper.ceiling_double_int(vec.zCoord);
      IBlockState iBlockState = world.getIBlockState(count, count2, count3);
      Block block = iBlockState.getBlock();
      if (block.getMaterial().isActive2()) {
         float factor7 = 0.0F;
         if (iBlockState.getBlock() instanceof BlockLiquid) {
            factor7 = BlockLiquid.computeFactor((Integer)iBlockState.getComparable(BlockLiquid.auxiliaryImmutableSetEntry)) - 0.11111111F;
         }

         float factor8 = (float)(count2 + 1) - factor7;
         if (vec.yCoord >= (double)factor8) {
            block = world.getIBlockState(count, count2 + 1, count3).getBlock();
         }
      }

      return block;
   }

   public static float getFactor3() {
      return factor3;
   }

   public static float getFactor2() {
      return factor2;
   }

   public static void applyEntityPlayer(EntityPlayer player, boolean flag) {
      GlStateManager.glStateAction2(2982, floatBuffer3);
      GlStateManager.glStateAction2(2983, floatBuffer);
      GL11.glGetInteger(2978, intBuffer);
      float factor6 = (float)((intBuffer.get(0) + intBuffer.get(2)) / 2);
      float factor7 = (float)((intBuffer.get(1) + intBuffer.get(3)) / 2);
      GLU.gluUnProject(factor6, factor7, 0.0F, floatBuffer3, floatBuffer, intBuffer, floatBuffer2);
      vec3 = new Vec3((double)floatBuffer2.get(0), (double)floatBuffer2.get(1), (double)floatBuffer2.get(2));
      int count = flag ? 1 : 0;
      float pitch = player.rotationPitch;
      float yaw = player.rotationYaw;
      factor4 = MathHelper.cos(yaw * (float) Math.PI / 180.0F) * (float)(1 - count * 2);
      factor = MathHelper.abs(yaw * (float) Math.PI / 180.0F) * (float)(1 - count * 2);
      factor2 = -factor * MathHelper.abs(pitch * (float) Math.PI / 180.0F) * (float)(1 - count * 2);
      factor3 = factor4 * MathHelper.abs(pitch * (float) Math.PI / 180.0F) * (float)(1 - count * 2);
      factor5 = MathHelper.cos(pitch * (float) Math.PI / 180.0F);
   }
}

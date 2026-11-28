package net.optifine.shaders;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.ViewFrustum;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

public class ShadowUtils {
   public static Iterator makeShadowChunkIterator(WorldClient world, double d, Entity entity, int n, ViewFrustum viewFrustum) {
      float shadowRenderDistance = Shaders.getShadowRenderDistance();
      if (shadowRenderDistance > 0.0F && shadowRenderDistance < (float)((n - 1) * 16)) {
         int n2 = MathHelper.ceiling_float_int(shadowRenderDistance / 16.0F) + 1;
         float celestialAngleRadians = world.getCelestialAngleRadians((float)d);
         float f = Shaders.sunPathRotation * (float) (Math.PI / 180.0);
         float f2 = celestialAngleRadians > (float) (Math.PI / 2) && celestialAngleRadians < (float) (Math.PI * 3.0 / 2.0) ? celestialAngleRadians + (float) Math.PI : celestialAngleRadians;
         float f3 = -MathHelper.sin(f2);
         float f4 = MathHelper.cos(f2) * MathHelper.cos(f);
         float f5 = -MathHelper.cos(f2) * MathHelper.sin(f);
         BlockPos pos = new BlockPos(
            MathHelper.floor_double(entity.posX) >> 4, MathHelper.floor_double(entity.posY) >> 4, MathHelper.floor_double(entity.posZ) >> 4
         );
         BlockPos pos2 = pos.add((double)(-f3 * (float)n2), (double)(-f4 * (float)n2), (double)(-f5 * (float)n2));
         BlockPos pos3 = pos.add((double)(f3 * (float)n), (double)(f4 * (float)n), (double)(f5 * (float)n));
         return new IteratorRenderChunks(viewFrustum, pos2, pos3, n2, n2);
      } else {
         List list = Arrays.asList(viewFrustum.renderChunks);
         return list.iterator();
      }
   }
}

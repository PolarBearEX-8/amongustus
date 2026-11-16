package client.util;

import client.render.RenderGlobalEntry;
import java.util.Arrays;
import java.util.Iterator;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ImplementedIterator;
import net.minecraft.util.MathHelper;

public class RayTraceSerializer {
   public static Iterator resolveIterator(WorldClient world, double offset, Entity entity, int count, RenderGlobalEntry renderGlobalEntry) {
      float factor = PreEndHandManager.getFactor();
      if (factor > 0.0F && factor < (float)((count - 1) * 16)) {
         int count2 = MathHelper.computeCount(factor / 16.0F) + 1;
         float factor2 = world.computeFactor2((float)offset);
         float factor3 = PreEndHandManager.factor35 * (float) (Math.PI / 180.0);
         float factor4 = factor2 > (float) (Math.PI / 2) && factor2 < (float) (Math.PI * 3.0 / 2.0) ? factor2 + (float) Math.PI : factor2;
         float factor5 = -MathHelper.abs(factor4);
         float factor6 = MathHelper.cos(factor4) * MathHelper.cos(factor3);
         float factor7 = -MathHelper.cos(factor4) * MathHelper.abs(factor3);
         BlockPos blockPos = new BlockPos(
            MathHelper.ceiling_double_int(entity.posX) >> 4, MathHelper.ceiling_double_int(entity.posY) >> 4, MathHelper.ceiling_double_int(entity.posZ) >> 4
         );
         BlockPos blockPos2 = blockPos.resolveBlockPos((double)(-factor5 * (float)count2), (double)(-factor6 * (float)count2), (double)(-factor7 * (float)count2));
         BlockPos blockPos3 = blockPos.resolveBlockPos((double)(factor5 * (float)count), (double)(factor6 * (float)count), (double)(factor7 * (float)count));
         return new ImplementedIterator(renderGlobalEntry, blockPos2, blockPos3, count2, count2);
      } else {
         return Arrays.asList(renderGlobalEntry.renderChunkArray).iterator();
      }
   }
}

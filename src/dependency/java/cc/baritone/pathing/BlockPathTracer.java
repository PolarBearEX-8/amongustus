package cc.baritone.pathing;

import client.util.MinecraftAccessor;
import client.util.TimeHelper;
import java.util.HashMap;
import java.util.PriorityQueue;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Vec3;

public class BlockPathTracer {
   public static TracedBlockPath traceBlockPath(PathPoint pathPoint, PathPoint pathPoint2, int count) {
      HashMap map = new HashMap();
      PriorityQueue priorityQueue = new PriorityQueue();
      TracedBlockPath tracedBlockPath = new TracedBlockPath(
         pathPoint,
         0.0F,
         (float)Math.sqrt(
            new Vec3((double)pathPoint.getBlockPos().getX() + 0.5, (double)pathPoint.getBlockPos().getY() + 0.5, (double)pathPoint.getBlockPos().getZ() + 0.5)
               .getCoordinate4(
                  new Vec3((double)pathPoint2.getBlockPos().getX() + 0.5, (double)pathPoint2.getBlockPos().getY() + 0.5, (double)pathPoint2.getBlockPos().getZ() + 0.5)
               )
         ),
         null,
         count
      );
      map.put(pathPoint.getBlockPos(), tracedBlockPath);
      priorityQueue.add(tracedBlockPath);

      for (int index = 0; !priorityQueue.isEmpty(); index++) {
         TracedBlockPath tracedBlockPath2 = (TracedBlockPath)priorityQueue.poll();
         if (tracedBlockPath2.getPathPoint().getBlockPos().checkBlockPos2(pathPoint2.getBlockPos())) {
            return tracedBlockPath2;
         }

         for (PathPoint pathPoint3 : (Iterable<PathPoint>)(Iterable)(tracedBlockPath2.getList())) {
            Block block = Minecraft.getMinecraft().theWorld.getBlockState(pathPoint3.getBlockPos()).getBlock();
            float factor = block.equals(Blocks.block95)
               ? 100000.0F
               : (
                  block instanceof BlockBed
                     ? 0.0F
                     : (
                        block.getMaterial() == Material.coreItemEntry
                           ? 1.0E-7F
                           : 1.0F - block.getScaleFactor(MinecraftAccessor.minecraft.thePlayer, MinecraftAccessor.minecraft.theWorld, pathPoint3.getBlockPos())
                     )
               );
            if (factor != 2.1474836E9F) {
               float factor2 = tracedBlockPath2.getFactor() + factor;
               TracedBlockPath tracedBlockPath3 = (TracedBlockPath)map.get(pathPoint3.getBlockPos());
               if (tracedBlockPath3 == null) {
                  float factor3 = (float)Math.sqrt(
                     new Vec3((double)pathPoint3.getBlockPos().getX() + 0.5, (double)pathPoint3.getBlockPos().getY() + 0.5, (double)pathPoint3.getBlockPos().getZ() + 0.5)
                        .getCoordinate4(
                           new Vec3((double)pathPoint2.getBlockPos().getX() + 0.5, (double)pathPoint2.getBlockPos().getY() + 0.5, (double)pathPoint2.getBlockPos().getZ() + 0.5)
                        )
                  );
                  TracedBlockPath tracedBlockPath4 = new TracedBlockPath(pathPoint3, factor2, factor3, tracedBlockPath2, count);
                  map.put(pathPoint3.getBlockPos(), tracedBlockPath4);
                  priorityQueue.add(tracedBlockPath4);
               } else if (factor2 < tracedBlockPath3.getFactor()) {
                  tracedBlockPath3.applyFloat(factor2);
                  tracedBlockPath3.setTracedBlockPath(tracedBlockPath2);
                  priorityQueue.remove(tracedBlockPath3);
                  priorityQueue.add(tracedBlockPath3);
               }
            }
         }

         if (index > 5000) {
            return null;
         }
      }

      System.err.println("No Way Found");
      return null;
   }
}

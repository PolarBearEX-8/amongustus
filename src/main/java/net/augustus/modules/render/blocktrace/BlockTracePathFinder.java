package net.augustus.modules.render.blocktrace;

import java.util.HashMap;
import java.util.PriorityQueue;
import net.augustus.utils.TimeHelper;
import net.augustus.utils.interfaces.MC;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.Vec3;

public class BlockTracePathFinder {

   public static BlockTraceNode findPath(TraceBlockPos traceBlockPos, TraceBlockPos traceBlockPos2, int n) {
      HashMap map = new HashMap();
      PriorityQueue priorityQueue = new PriorityQueue();
      BlockTraceNode blockTraceNode = new BlockTraceNode(
         traceBlockPos,
         0.0F,
         (float)Math.sqrt(
            new Vec3((double)traceBlockPos.getBlockPos().getX() + 0.5, (double)traceBlockPos.getBlockPos().getY() + 0.5, (double)traceBlockPos.getBlockPos().getZ() + 0.5)
               .distanceTo(new Vec3((double)traceBlockPos2.getBlockPos().getX() + 0.5, (double)traceBlockPos2.getBlockPos().getY() + 0.5, (double)traceBlockPos2.getBlockPos().getZ() + 0.5))
         ),
         null,
         n
      );
      map.put(traceBlockPos.getBlockPos(), blockTraceNode);
      priorityQueue.add(blockTraceNode);

      for (int i = 0; !priorityQueue.isEmpty(); i++) {
         BlockTraceNode blockTraceNode2 = (BlockTraceNode)priorityQueue.poll();
         if (blockTraceNode2.getTraceBlockPos().getBlockPos().equalsPos(traceBlockPos2.getBlockPos())) {
            return blockTraceNode2;
         }

         for (TraceBlockPos traceBlockPos4 : (Iterable<TraceBlockPos>)(blockTraceNode2.getNeighbors())) {
            Block block = Minecraft.getMinecraft().theWorld.getBlockState(traceBlockPos4.getBlockPos()).getBlock();
            float f = block.equals(Blocks.bedrock)
               ? 100000.0F
               : (
                  block instanceof BlockBed
                     ? 0.0F
                     : (block.getMaterial() == Material.air ? 1.0E-7F : 1.0F - block.getPlayerRelativeBlockHardness(MC.mc.thePlayer, MC.mc.theWorld, traceBlockPos4.getBlockPos()))
               );
            if (f != 2.1474836E9F) {
               float f2 = blockTraceNode2.getGCost() + f;
               BlockTraceNode blockTraceNode3 = (BlockTraceNode)map.get(traceBlockPos4.getBlockPos());
               if (blockTraceNode3 == null) {
                  float f3 = (float)Math.sqrt(
                     new Vec3((double)traceBlockPos4.getBlockPos().getX() + 0.5, (double)traceBlockPos4.getBlockPos().getY() + 0.5, (double)traceBlockPos4.getBlockPos().getZ() + 0.5)
                        .distanceTo(new Vec3((double)traceBlockPos2.getBlockPos().getX() + 0.5, (double)traceBlockPos2.getBlockPos().getY() + 0.5, (double)traceBlockPos2.getBlockPos().getZ() + 0.5))
                  );
                  blockTraceNode3 = new BlockTraceNode(traceBlockPos4, f2, f3, blockTraceNode2, n);
                  map.put(traceBlockPos4.getBlockPos(), blockTraceNode3);
                  priorityQueue.add(blockTraceNode3);
               } else if (f2 < blockTraceNode3.getGCost()) {
                  blockTraceNode3.setGCost(f2);
                  blockTraceNode3.setBlockTraceNode(blockTraceNode2);
                  priorityQueue.remove(blockTraceNode3);
                  priorityQueue.add(blockTraceNode3);
               }
            }
         }

         if (i > 5000) {
            return null;
         }
      }

      System.err.println("No Way Found");
      return null;
   }

}

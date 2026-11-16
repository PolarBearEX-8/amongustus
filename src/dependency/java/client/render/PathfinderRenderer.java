package client.render;

import client.Client;
import client.network.CosmeticsPayload;
import client.util.PathNode;
import java.util.ArrayList;
import net.minecraft.block.BlockPlaceData;

public class PathfinderRenderer extends PathRenderer {
   public int indexOffset;

   public PathfinderRenderer(BlockPlaceData blockPlaceData, BlockPlaceData blockPlaceData2, boolean flag, boolean flag2, boolean flag3) {
      super(blockPlaceData, blockPlaceData2, flag, flag2, flag3);
   }

   public ArrayList getArrayList3(long value) {
      if (CosmeticsPayload.checkCondition(6)) {
         return null;
      } else {
         this.indexOffset++;
         if (!minecraft.theWorld.checkIndex(this.blockPlaceData.indexOffset1 >> 4, this.blockPlaceData.indexOffset >> 4, false)) {
            return new ArrayList();
         } else {
            long timestamp = System.currentTimeMillis();
            int indexOffset2 = this.blockPlaceData2.indexOffset1;
            int indexOffset3 = this.blockPlaceData2.indexOffset2;
            int indexOffset4 = this.blockPlaceData2.indexOffset;
            long value2 = PathRenderer.computeTimestamp(this.blockPlaceData2.indexOffset1, this.blockPlaceData2.indexOffset2, this.blockPlaceData2.indexOffset);
            int count = indexOffset4;
            int count2 = indexOffset3;
            int count3 = indexOffset2;
            Object entry = null;
            this.resolvePathNode((PathNode)entry, count3, count2, count, value2, false);

            while (System.currentTimeMillis() - timestamp <= value) {
               if (this.priorityQueue.isEmpty()) {
                  Client.getClient().getClass();
                  return new ArrayList();
               }

               PathNode pathNode = (PathNode)this.priorityQueue.poll();
               if (this.value5 > pathNode.getValue4()) {
                  this.value5 = pathNode.getValue4();
               }

               if (pathNode.getValue4() < this.coordOffset) {
                  Client.getClient().getClass();
                  ArrayList list = this.resolveArrayList(pathNode);
                  if (this.state1) {
                     boolean enabled = this.enabled5;
                     boolean enabled2 = this.enabled2;
                     list = this.resolveArrayList(list, enabled2, enabled);
                  }

                  return list;
               }

               this.applyPathNode(pathNode);
            }

            return null;
         }
      }
   }
}

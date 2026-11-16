package client.util;

import java.util.ArrayList;
import net.minecraft.block.BlockPlaceData;

public class PathNode implements Comparable {
   public long timestamp;
   public double value;
   public PathNode pathNode;
   public boolean enabled;
   public double value2;
   public double value3;
   public BlockPlaceData blockPlaceData;
   public ArrayList arrayList = new ArrayList(8);
   public double value4;

   public PathNode(PathNode pathNode2, BlockPlaceData blockPlaceData2, double offset, double offset2, long value5) {
      this.enabled = false;
      this.pathNode = pathNode2;
      this.blockPlaceData = blockPlaceData2;
      this.value3 = offset;
      this.value4 = offset2;
      this.value2 = offset + offset2;
      this.timestamp = value5;
   }

   public ArrayList getArrayList() {
      return this.arrayList;
   }

   public double getDouble() {
      return this.value3;
   }

   public void applyDouble(double offset) {
      this.value3 = offset;
      this.value2 = offset + this.value4;
   }

   public BlockPlaceData getBlockPlaceData() {
      return this.blockPlaceData;
   }

   public void setPathNode(PathNode pathNode2) {
      this.pathNode = pathNode2;
   }

   public double getValue2() {
      return this.value2;
   }

   public int computeCount(PathNode pathNode2) {
      if (pathNode2.value2 > this.value2) {
         return -1;
      } else {
         return pathNode2.value2 < this.value2 ? 1 : Double.compare(this.value4, pathNode2.value4);
      }
   }

   public void updateState() {
      if (!this.enabled) {
         this.pathNode.updateState();
         BlockPlaceData blockPlaceData2 = this.blockPlaceData;
         double offset = this.pathNode.value3 + this.pathNode.blockPlaceData.getCoordinate(blockPlaceData2) / this.value;
         this.applyDouble(offset);
         this.enabled = true;
      }
   }

   @Override
   public int compareTo(Object entry) {
      PathNode pathNode2 = (PathNode)entry;
      return this.computeCount(pathNode2);
   }

   public double getValue4() {
      return this.value4;
   }

   public PathNode getPathNode() {
      return this.pathNode;
   }
}
